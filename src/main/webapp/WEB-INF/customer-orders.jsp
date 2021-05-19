<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Customers</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h2>Orders for ${customer.name}&nbsp;&nbsp;
			<a href="<c:url value="/customers/${customer.id}/orders/add"/> " class="btn btn-primary">Add New Order</a>
			<a href="<c:url value="/customers"/>" class="btn btn-primary">Back to Customers List</a></h2>
		<table class="table table-striped">
			<tr>
				<th>Number</th>
				<th>Value</th>
				<th>Placed</th>
				<th>Actions</th>
			</tr>
			<c:forEach items="${customer.orders}" var="order">
				<tr>
					<td><c:out value="${order.number}" /></td>
					<td><c:out value="${order.value}" /></td>
					<td><c:out value="${order.placed}" /></td>
					<td>
					    <a href="<c:url value="/static/customer-order-edit-rest.html#customerId=${customer.id}&orderId=${order.id}"/> " class="btn btn-info">Edit</a>
					    <a href="<c:url value="/customers/${customer.id}/orders/${order.id}/delete"/> " class="btn btn-info">Delete</a>
                    </td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
