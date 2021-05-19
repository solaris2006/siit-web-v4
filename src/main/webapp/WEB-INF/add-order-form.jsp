
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Customers</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"
          rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"
            integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU="
            crossorigin="anonymous"></script>


</head>
<body>
<div class="container">
    <h2>Add new Order </h2>&nbsp;&nbsp;
    <form:form action="add" modelAttribute="order" method="POST">
        <div class="row mb3">
            <form:label path="number" cssClass="col-sm-2 col-form-label">Order Number </form:label>
            <div class="col-sm-10">
                <form:input type="text"  cssClass="form-control" path="number" placeholder="Order Number"/>
            </div>
        </div>
       <div class="row">
           <button type="submit" class="btn btn-primary">Add Order</button>
       </div>


    </form:form>
</div>
</body>
</html>
