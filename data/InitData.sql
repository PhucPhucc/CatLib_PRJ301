-- Dữ liệu mẫu cho bảng Category
INSERT INTO Category (categoryName) VALUES
(N'Tiểu thuyết'),
(N'Khoa học'),
(N'Lịch sử'),
(N'Tự truyện'),
(N'Truyện tranh');

-- Dữ liệu mẫu cho bảng Author
INSERT INTO Author (AuthorName, Story) VALUES
(N'Nguyễn Nhật Ánh', N'Nguyễn Nhật Ánh là nhà văn nổi tiếng với các tác phẩm về tuổi học trò.'),
(N'Stephen Hawking', N'Stephen Hawking là nhà vật lý lý thuyết và vũ trụ học người Anh.'),
(N'Trần Trọng Kim', N'Trần Trọng Kim là một học giả, nhà sử học và chính khách Việt Nam.'),
(N'Nguyễn Du', N'Nguyễn Du là đại thi hào dân tộc Việt Nam, tác giả của Truyện Kiều.'),
(N'Tô Hoài', N'Tô Hoài là nhà văn Việt Nam nổi tiếng với Dế Mèn Phiêu Lưu Ký.');

-- Dữ liệu mẫu cho bảng Book
INSERT INTO Book (BookID, Title, Price, PublishYear, Description, Publisher, StockQuantity, CategoryID, AuthorID) VALUES
(201, N'Mắt Biếc', 95000.00, 2017, N'Câu chuyện tình yêu đầy lãng mạn và buồn của Ngạn và Hà Lan.', N'Nhà Xuất Bản Trẻ', 50, 1, 101),
(202, N'Lược Sử Thời Gian', 120000.00, 1988, N'Giải thích những khái niệm phức tạp của vật lý vũ trụ một cách dễ hiểu.', N'Nhà Xuất Bản Khoa Học và Kỹ Thuật', 30, 2, 102),
(203, N'Việt Nam Sử Lược', 150000.00, 1920, N'Tóm tắt lịch sử Việt Nam từ khởi thủy đến đầu thế kỷ 20.', N'Nhà Xuất Bản Văn Hóa', 25, 3, 103),
(204, N'Cho Tôi Xin Một Vé Đi Tuổi Thơ', 80000.00, 2008, N'Những câu chuyện hồn nhiên, trong trẻo về tuổi thơ.', N'Nhà Xuất Bản Kim Đồng', 60, 1, 101),
(205, N'Nhà Giả Kim', 75000.00, 1988, N'Câu chuyện về hành trình tìm kiếm kho báu và ý nghĩa cuộc sống.', N'Nhà Xuất Bản Văn Học', 45, 1, 104); -- Sử dụng AuthorID 104 tạm thời vì đây là tác phẩm dịch

-- Dữ liệu mẫu cho bảng Book_Author (để xử lý sách có nhiều tác giả hoặc để liên kết rõ ràng hơn)
-- Giả định Mắt Biếc của Nguyễn Nhật Ánh
INSERT INTO Book_Author (BookID, AuthorID) VALUES (201, 101);
-- Giả định Lược Sử Thời Gian của Stephen Hawking
INSERT INTO Book_Author (BookID, AuthorID) VALUES (202, 102);
-- Giả định Việt Nam Sử Lược của Trần Trọng Kim
INSERT INTO Book_Author (BookID, AuthorID) VALUES (203, 103);
-- Giả định Cho Tôi Xin Một Vé Đi Tuổi Thơ của Nguyễn Nhật Ánh
INSERT INTO Book_Author (BookID, AuthorID) VALUES (204, 101);
-- Giả định Nhà Giả Kim không có tác giả trong danh sách, nên có thể bỏ qua hoặc thêm tác giả tương ứng (ví dụ Paulo Coelho)
-- INSERT INTO Book_Author (BookID, AuthorID) VALUES (205, [ID của Paulo Coelho]);

-- Dữ liệu mẫu cho bảng Users
INSERT INTO Users (username, password, full_name, role, is_active, email, phone) VALUES
(N'admin', N'123', N'Nguyễn Văn Cường', N'admin', 1, N'cuong.nguyen@example.com', N'0912345678'),
(N'user1', N'123', N'Trần Thị Lan', N'user', 1, N'lan.tran@example.com', N'0987654321'),
(N'user_minh', N'password123', N'Lê Minh', N'user', 1, N'minh.le@example.com', N'0901122334'),
(N'admin_huong', N'password123', N'Phạm Thị Hương', N'admin', 1, N'huong.pham@example.com', N'0900001111');

-- Dữ liệu mẫu cho bảng [Order]
-- Lưu ý: UserID cho bảng [Order] sẽ tự động tăng (IDENTITY), nên không cần chỉ định.
-- Cần cập nhật UserID dựa trên các giá trị tự động tạo.
-- Để đơn giản, tôi sẽ giả định các UserID được tạo là 1, 2, 3, 4 theo thứ tự chèn trên.
INSERT INTO BookOrders (OrderID, UserID, BookID, OrderDate, ReturnDate, ActualReturnDate, Bill, Status) VALUES
(301, 1, 201, '2024-05-01', '2024-05-15', '2024-05-14', 95000.00, N'returned'),
(302, 2, 202, '2024-05-05', '2024-05-20', NULL, 120000.00, N'approved'),
(303, 3, 203, '2024-05-10', '2024-05-25', NULL, 150000.00, N'pending'),
(304, 2, 204, '2024-04-20', '2024-05-05', NULL, 80000.00, N'overdue'),
(305, 1, 205, '2024-06-01', '2024-06-10', NULL, 75000.00, N'pending');
