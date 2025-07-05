    CREATE TRIGGER trg_ApproveRejectOrder
    ON BookOrders
    AFTER INSERT
    AS
    BEGIN
        SET NOCOUNT ON; -- Ngăn không cho các câu lệnh SELECT trong trigger trả về kết quả không mong muốn

        -- Bước 1: Tạo bảng tạm để xử lý thông tin các đơn hàng mới và số lượng sách hiện có
        -- Bảng này giúp chúng ta đánh giá trạng thái và số lượng tồn kho một cách chính xác
        CREATE TABLE #OrderProcessing (
            OrderID INT PRIMARY KEY,
            BookID INT,
            RequestedQuantity INT DEFAULT 1, -- Giả định mỗi đơn hàng mượn 1 cuốn sách
            CurrentStock INT,
            NewStatus NVARCHAR(20) DEFAULT 'rejected' -- Mặc định ban đầu là 'rejected'
        );

        -- Chèn dữ liệu từ bảng 'inserted' (các hàng mới được thêm vào [Order])
        -- cùng với số lượng sách hiện tại từ bảng Book vào bảng tạm
        INSERT INTO #OrderProcessing (OrderID, BookID, CurrentStock)
        SELECT
            I.OrderID,
            I.BookID,
            B.StockQuantity
        FROM
            inserted I
        INNER JOIN
            Book B ON I.BookID = B.BookID;

        -- Bước 2: Xác định các đơn hàng có thể được chấp thuận ('approved')
        -- Chúng ta sử dụng Common Table Expression (CTE) để tính tổng số lượng sách được yêu cầu
        -- cho mỗi cuốn sách trong lô đơn hàng này và so sánh với số lượng tồn kho hiện tại.
        WITH CTE_ApprovedOrders AS (
            SELECT
                OP.OrderID,
                OP.BookID,
                OP.RequestedQuantity,
                OP.CurrentStock,
                -- RunningTotalRequested: Tính tổng số lượng sách yêu cầu tích lũy
                -- cho cùng một cuốn sách, sắp xếp theo OrderID để ưu tiên đơn hàng nào được chèn trước
                SUM(OP.RequestedQuantity) OVER (PARTITION BY OP.BookID ORDER BY OP.OrderID) AS RunningTotalRequested
            FROM
                #OrderProcessing OP
        )
        -- Cập nhật trạng thái 'approved' cho các đơn hàng có thể được xử lý
        -- (tức là tổng số sách yêu cầu tích lũy không vượt quá số lượng tồn kho hiện tại)
        UPDATE T
        SET
            NewStatus = 'approved'
        FROM
            #OrderProcessing T
        INNER JOIN
            CTE_ApprovedOrders C ON T.OrderID = C.OrderID
        WHERE
            C.RunningTotalRequested <= C.CurrentStock;

        -- Bước 3: Cập nhật số lượng sách trong bảng 'Book'
        -- Chỉ trừ số lượng sách cho những đơn hàng đã được 'approved'
        UPDATE B
        SET StockQuantity = B.StockQuantity - COALESCE(ApprovedCount.ApprovedBooks, 0)
        FROM Book B
        INNER JOIN (
            -- Đếm số lượng đơn hàng được duyệt cho mỗi cuốn sách
            SELECT BookID, COUNT(OrderID) AS ApprovedBooks
            FROM #OrderProcessing
            WHERE NewStatus = 'approved'
            GROUP BY BookID
        ) AS ApprovedCount ON B.BookID = ApprovedCount.BookID;

        -- Bước 4: Cập nhật trạng thái cuối cùng vào bảng '[Order]' gốc
        -- Áp dụng trạng thái ('approved' hoặc 'rejected') đã xác định trong bảng tạm vào bảng chính
        UPDATE O
        SET Status = OP.NewStatus
        FROM BookOrders O
        INNER JOIN #OrderProcessing OP ON O.OrderID = OP.OrderID;

        -- Bước 5: Xóa bảng tạm để giải phóng tài nguyên
        DROP TABLE #OrderProcessing;

    END;
