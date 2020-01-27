<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Spring MVC CRUD</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />"
          rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</head>
<body>
<div class="container">
    <div class="col-md-offset-2 col-md-7">
        <h2 class="text-center">Spring MVC
            CRUD</h2>
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Add User</div>
            </div>
            <div class="panel-body">
                <%--@elvariable id="user" type="ru.jm.springmvc.model"--%>
                <form:form action="saveUser" cssClass="form-horizontal"
                           method="post" modelAttribute="user">

                    <!-- need to associate this data with user id -->
                    <form:hidden path="id" />

                    <div class="form-group">
                        <label for="name" class="col-md-3 control-label">
                            Name</label>
                        <div class="col-md-9">
                            <form:input path="name" cssClass="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="role" class="col-md-3 control-label">
                            Role</label>
                        <div class="col-md-9">
                            <form:input path="role" cssClass="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password" class="col-md-3 control-label">
                            Password</label>
                        <div class="col-md-9">
                            <form:input path="password" cssClass="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="login" class="col-md-3 control-label">
                            Login</label>
                        <div class="col-md-9">
                            <form:input path="login" cssClass="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="email" class="col-md-3 control-label">Email</label>
                        <div class="col-md-9">
                            <form:input path="email" cssClass="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="country" class="col-md-3 control-label">Country</label>
                        <div class="col-md-9">
                            <form:input path="country" cssClass="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="mobile" class="col-md-3 control-label">Mobile</label>
                        <div class="col-md-9">
                            <form:input path="mobile" cssClass="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <!-- Button -->
                        <div class="col-md-offset-3 col-md-9">
                            <form:button cssClass="btn btn-primary">Submit</form:button>
                        </div>
                    </div>

                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
