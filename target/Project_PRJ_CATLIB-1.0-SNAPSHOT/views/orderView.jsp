<%-- 
    Document   : orderView
    Created on : Jun 9, 2025, 8:20:49 AM
    Author     : DuyPhuc
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Order Page</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        if (localStorage.getItem('theme') === 'dark') {
            document.documentElement.classList.add('dark');
        }
    </script>
    <script>
        tailwind.config = {
            darkMode: 'class'
        }
    </script>
    <style>
      html {
          transition: background-color 0.5s ease, color 0.5s ease;
      }

      .smooth-transition {
          transition-property: color, background-color, border-color, text-decoration-color, fill, stroke;
          transition-duration: 500ms;
          transition-timing-function: ease-in-out;
      }

    </style> 
  </head>
  <body class="font-mono smooth-transition bg-[#c7f0fc] bg-center bg-no-repeat bg-cover min-h-screen dark:bg-gray-800 text-black dark:text-white">

    <jsp:include page="_header.jsp"></jsp:include>

        <main class="order_container">
          <div class="order">
            <div class="order_left">
              <ul>
                <li><a href="">order list</a></li>
                <li><a href="">return list</a></li>
                <li><a href="">all list</a></li>
              </ul>
            </div>
            <div class="order_right">
            <c:forEach items="${orders}" var="order" >
                <div class="order_card">
                  <div class="img_wraper">
                    <img src="${order.imageUrl}" alt="hinh anh">
                  </div>

                  <div class="order_info">
                    <p class="order_title">${order.title}</p>
                    <p class="order_author">${order.authorName}</p>
                    <p class="order_pushish">${order.publisher}</p>
                    <p class="order_date">Borrow Date: <span> ${order.orderDate}</span></p>
                    <p class="order_date">Return date: <span> ${order.returnDate}</span></p>
                  </div>

                  <div class="order_payment">
                    <p class="order_price">Fee: 5.000VND/day</p>
                    <p class="order_late">Late Fee: 10.000VND/day</p>
                    <a href="">Order</a>
                  </div>
                </div>
            </c:forEach>

          </div>
        </div>
    </main>

    <script>
        document.querySelector('#orders a').classList.add("border-b-4");
        document.querySelector('#home a').classList.remove("border-b-4");
        document.querySelector('#about a').classList.remove("border-b-4");

        const html = document.documentElement;
        const btnDark = document.querySelector("#btn-dark");
        if (html.classList.contains('dark')) {
            btnDark.innerHTML = `<img class="size-6" src="https://img.icons8.com/?size=100&id=45475&format=png&color=ffffff" alt="do-not-disturb-2"/>`;
        } else {
            btnDark.innerHTML = `<img class="size-6" src="https://img.icons8.com/external-glyph-silhouettes-icons-papa-vector/78/external-Light-Mode-interface-glyph-silhouettes-icons-papa-vector.png" alt="external-Light-Mode-interface-glyph-silhouettes-icons-papa-vector"/>`;
        }
        function toggleDarkMode() {
            html.classList.toggle('dark');
            if (html.classList.contains('dark')) {
                btnDark.innerHTML = `<img class="size-6" src="https://img.icons8.com/?size=100&id=45475&format=png&color=ffffff" alt="do-not-disturb-2"/>`;
            } else {
                btnDark.innerHTML = `<img class="size-6" src="https://img.icons8.com/external-glyph-silhouettes-icons-papa-vector/78/external-Light-Mode-interface-glyph-silhouettes-icons-papa-vector.png" alt="external-Light-Mode-interface-glyph-silhouettes-icons-papa-vector"/>`;
            }
            localStorage.setItem('theme', html.classList.contains('dark') ? 'dark' : 'light');
        }
    </script>
  </body>
</html>
