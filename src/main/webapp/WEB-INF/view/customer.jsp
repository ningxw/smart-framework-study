<%--
  Created by IntelliJ IDEA.
  User: nxw
  Date: 18-1-28
  Time: 下午11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="BASE" value="${pageContext.request.contextPath}" />

<html>
<head>
    <title>客户管理</title>
</head>
<body>
    <h1>customer list</h1>
    <table>
        <tr>
            <th>name</th>
            <th>contract</th>
            <th>telephone</th>
            <th>email</th>
            <th>operation</th>
        </tr>
        <c:forEach var="customer" items="${customerList}">
            <tr>
                <td>${customer.name}</td>
                <td>${customer.contract}</td>
                <td>${customer.telephone}</td>
                <td>${customer.email}</td>
                <td>
                    <a href="${BASE}/customer_edit?id=${customer.id}">edit</a>
                    <a href="${BASE}/customer_delete?id=${customer.id}">delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
