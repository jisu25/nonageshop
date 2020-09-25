<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>  
<%@ include file="../header.jsp" %>  
<%@ include file="sub_img.html"%> 
<%@ include file="sub_menu.jsp" %>       
  <article>
    <h2> My Page(${title}) </h2>
    <form name="formm" method="post">
      <table id="cartList">
      <tr>
        <th>주문일자</th> <th>주문번호</th> <th>상품명</th> <th>결제 금액</th> <th>주문 상세</th> </th>    
      </tr>
      <c:forEach items="${orderList}"  var="od">
      <tr>  
        <td> <fmt:formatDate value="${od.order.orderDate}" type="date"/></td>
        <td> ${od.order.no} </td>    
        <td> ${od.product.name} </td>
        <td> <fmt:formatNumber value="${od.product.salePrice}" type="currency"/> </td>
        <td> <a href="orderDetail.do?no=${od.order.no}"> 조회 </a></td>
      </tr>
      </c:forEach>    
      </table>   
          
      <div class="clear"></div>
      <div id="buttons" style="float: right">
       <input type="button"    value="쇼핑 계속하기"  class="cancel"  onclick="location.href='index.do'"> 
      </div>
    </form>  
  </article>
<%@ include file="../footer.jsp" %>    