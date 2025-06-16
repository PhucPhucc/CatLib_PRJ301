<%-- 
    Document   : _header
    Created on : Jun 1, 2025, 12:11:37 AM
    Author     : DuyPhuc
--%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<header class="flex justify-between items-center p-6 text-[#5f899f]">
  <a class="flex items-center h-16 bg-white rounded-full px-4 dark:bg-gray-900 dark:text-gray-100 smooth-transition" href="${pageContext.request.contextPath}/home">
    <img class="w-full h-full " src="image/logo.PNG" alt="logo CatLib">
    <p class="text-4xl font-bold py-2">CATLIB</p>
  </a>

  <nav>
    <ul class="flex justify-between gap-4 text-2xl font-semibold tracking-wider border-[#5f899f]">
      <li id="home" class="py-2 px-4 rounded hover:bg-[#5f899f] hover:text-white"><a href="${pageContext.request.contextPath}/home"
                                                                                     class="border-[#5f899f]">Home</a></li>
      <li id="about" class="py-2 px-4 rounded hover:bg-[#5f899f] hover:text-white"><a class="border-[#5f899f]" href="${pageContext.request.contextPath}/about">About us</a></li>
      <li id="orders" class="py-2 px-4 rounded hover:bg-[#5f899f] hover:text-white"><a class="border-[#5f899f]" href="${pageContext.request.contextPath}/order">Stocks</a></li>
    </ul>
  </nav>
  <div class="flex items-center font-medium text-md">
    <button id="btn-dark" class="p-2 rounded-full mr-2 " onclick="toggleDarkMode()">

    </button>

    <c:choose>

        <c:when test = "${loginedUser != null}">
            <div class="relative size-10 rounded-full p-1 dark:bg-neutral-300 smooth-transition profile">
              <img class="w-full" src="https://img.icons8.com/ios-glyphs/30/user--v1.png" alt="user--v1" />
              <div
                  class="absolute top-12 right-0 bg-white dark:bg-neutral-800 dark:text-white w-28 pb-1 rounded-xl ring ring-neutral-500 dark:ring-neutral-300 hidden ">
                <div class="absolute -top-4 right-0 bg-transparent w-12 h-4"></div>
                <ul class="text-right  ">
                  <li class="border-b-2  border-neutral-500 dark:border-neutral-300">
                    <a href="/log-out" class="px-4 pt-2 block hover:underline  hover:underline-offset-2">Profile</a>
                  </li>
                  <li class="border-b-2  border-neutral-500 dark:border-neutral-300">
                    <a href="/log-out" class="px-4 pt-2 block hover:underline  hover:underline-offset-2">Setting</a>
                  </li>
                  <li><a href="${pageContext.request.contextPath}/log-out" class="px-4 pt-2 block hover:underline  hover:underline-offset-2">Log out</a></li>
                </ul>
              </div>
            </div>
        </c:when>

        <c:otherwise>
            <a href="${pageContext.request.contextPath}/sign-up" class="px-4 py-2 mr-2 rounded-full bg-white dark:bg-[#5f899f] dark:hover:bg-[#4ca6bf] dark:text-white smooth-transition">Sign up</a>
            <a href="${pageContext.request.contextPath}/login" class="px-4 py-2 rounded-full bg-[#5f899f] text-white hover:bg-[#4ca6bf] dark:bg-white dark:text-[#5f899f] smooth-transition">Login</a>
        </c:otherwise>

    </c:choose>
  </div>
</header>
