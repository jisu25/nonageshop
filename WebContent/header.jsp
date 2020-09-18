<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Nonage Shop</title>
  <link href="css/shopping.css" rel="stylesheet">  
  <script type="text/javascript" src="member/member.js"></script>
  <script type="text/javascript" src="mypage/mypage.js"></script> 
  <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <script>
  	$(function(){
		$.ajax({
			type: "POST",
			url: "category.do",
			dataType: "json",
			success: function(data) {
				var str="<ul>";
				$.each(data, function(i) {
					str += "<li><a href='category.do?kind=" + data[i].kind + "'>" + data[i].name + "</a></li>"  
				});
				str += "</ul>"
				$("#top_menu").append(str);
				$("#sub_menu").append(str);
			}
		});
  	});
  </script>
</head>

<body>
<div id="wrap">
<!--헤더파일 들어가는 곳 시작 -->
  <header>
    <!--로고 들어가는 곳 시작--->  
    <div id="logo">
      <a href="index.do">
        <img src="images/logo.gif" width="180" height="100" alt="nonageshop">
      </a>  
    </div>
    <!--로고 들어가는 곳 끝-->     
    <nav id="catagory_menu">
     <ul>
       <c:choose>
       <c:when test="${empty sessionScope.loginUser}">
       <li>         
         <a href="login.do" style="width:110px;">LOGIN(CUSTOMER</a>   
	     <a href="adminLogin.do" style="width:100px;">| ADMIN)</a>
	   </li>		       
       <li>/</li>
       <li><a href="contract.do">JOIN</a></li>
       </c:when>
       <c:otherwise>
       <li style="color:orange">
         ${sessionScope.loginUser.name}(${sessionScope.loginUser.id})
       </li>
       <li><a href="logout.do">LOGOUT</a></li>
       </c:otherwise>       
       </c:choose>
       <li>/</li>
       <li>
         <a href="cartList.do">CART</a>
       </li><li>/</li>
       <li>
         <a href="mypage.do">MY PAGE</a>
       </li><li>/</li>
       <li>
         <a href="qnaList.do">Q&amp;A(1:1)</a>
       </li>
     </ul>
    </nav>

    <nav id="top_menu">
    </nav>
    <div class="clear"></div>
    <hr>
  </header>
  <!--헤더파일 들어가는 곳 끝 -->