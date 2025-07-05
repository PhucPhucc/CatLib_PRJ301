<%-- 
    Document   : _menuManager
    Created on : Jun 29, 2025, 5:33:14 PM
    Author     : tvphu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="flex-1 border-r-2 border-gray-300 px-6 lg:block sm:hidden">
    <p class="font-semibold text-4xl py-6 tracking-wider text-center">
        Manager List
    </p>
    <ul class="text-2xl">
        <li>
            <a
                href="${pageContext.request.contextPath}/admin/dashboard"
                class="hover:font-semibold hover:underline hover:underline-offset-2 block pb-1"
                >Dashboard</a
            >
        </li>
        <li>
            <a
                href="${pageContext.request.contextPath}/admin/book-manager"
                class="hover:font-semibold hover:underline hover:underline-offset-2 block pb-1"
                >Books</a
            >
        </li>
        <li>
            <a
                href="${pageContext.request.contextPath}/admin/dashboard"
                class="hover:font-semibold hover:underline hover:underline-offset-2 block pb-1"
                >Authors</a
            >
        </li>
        <li>
            <a
                href="${pageContext.request.contextPath}/admin/dashboard"
                class="hover:font-semibold hover:underline hover:underline-offset-2 block pb-1"
                >Categories</a
            >
        </li>
        <li>
            <a
                href="${pageContext.request.contextPath}/admin/dashboard"
                class="hover:font-semibold hover:underline hover:underline-offset-2 block"
                >Users</a
            >
        </li>
    </ul>
</div>
