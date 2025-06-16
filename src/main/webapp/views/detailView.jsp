<%-- 
    Document   : detailView
    Created on : Jun 2, 2025, 4:02:48 PM
    Author     : DuyPhuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${book.title} detail</title> <link rel="preconnect" href="https://fonts.googleapis.com">
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css?v=<%= System.currentTimeMillis() %>"/>

  </head>
  <body
      class="relative font-mono smooth-transition bg-[#c7f0fc] bg-center bg-no-repeat bg-cover min-h-screen dark:bg-gray-800 text-black dark:text-white">

    <jsp:include page="_header.jsp"></jsp:include>


        <main class="absolute bottom-0 left-0 right-0 h-1/2 pl-32 flex bg-neutral-100 dark:bg-neutral-800 rounded-tl-[4vw] rounded-tr-[4vw] ring">
          <div class="-translate-y-1/2 w-1/5">
            <img class="rounded-xl w-full ring " src="${book.urlImage}" alt="">
          <p class="text-sm mt-6 pt-2 border-t border-gray-400">tag: <span>${book.categoryName}</span></p>
        </div>
        <div class="w-4/5 px-8">
          <p class="text-5xl font-bold text-[#5f899f] pt-4 pb-2">${book.title}</p>
          <p class="text-neutral-700 dark:text-neutral-400 ">${book.authorName}</p>
          <p class="text-neutral-700 dark:text-neutral-400"><span>${book.publisher}</span> - <span>${book.publishYear}</span></p>
          <p class="text-neutral-500 dark:text-neutral-200 text-balance">${book.description}</p>

        </div>

        <div class="absolute top-12 right-0 p-4 text-right ">
          <p>Fee: <span class="text-neutral-500 pl-2">5.000VND/day</span></p>
          <p>Overdue Fee:  <span class="text-neutral-500">10.000VND/day</span></p>
        </div>

        <div class="absolute bottom-0 right-0 bg-[#4ca6bf] dark:bg-neutral-400 rounded-tl-[3vw]">
          <a onclick="confirmBorrow(${book.bookId})" class="block py-8 px-16 text-3xl text-white font-semibold tracking-wider dark:text-neutral-900 " href="${pageContext.request.contextPath}/borrow?id=${book.bookId}">Borrow now</a>
        </div>
    </main>


    <script>
        document.querySelector('#home a').classList.add("border-b-4");
        document.querySelector('#orders a').classList.remove("border-b-4");
        document.querySelector('#about a').classList.remove("border-b-4");

        function confirmBorrow(id) {

            const result = confirm(`Are you sure you want to borrow this book?`);
            if (result) {
                window.location.href = "${pageContext.request.contextPath}/borrow?id=" + id;
            } else {
                event.preventDefault();
            }

        }
    </script>
  </body>
</html>
