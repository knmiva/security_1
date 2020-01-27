<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head><%@ page isELIgnored="false" %>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Java Mentor</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />"
          rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
<div class="container">
    <div class="col-md-offset-1 col-md-10">
        <h2>User Relationship Manager</h2>
        <hr />

        <input type="button" value="Add User"
               onclick="window.location.href='showForm'; return false;"
               class="btn btn-primary" />
        <br/><br/>
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">User List</div>
            </div>
            <div class="panel-body">
                <table class="table table-striped table-bordered">
                    <tr>
                        <th>Name</th>
                        <th>Role</th>
                        <th>Password</th>
                        <th>Login</th>
                        <th>Email</th>
                        <th>Country</th>
                        <th>Mobile</th>
                        <th>Action</th>
                    </tr>

                    <!-- loop over and print our users -->
                    <c:forEach var="tempUser" items="${users}">

                        <!-- construct an "update" link with user id -->
                        <c:url var="updateLink" value="/updateForm">
                            <c:param name="userId" value="${tempUser.id}" />
                        </c:url>

                        <!-- construct an "delete" link with user id -->
                        <c:url var="deleteLink" value="/delete">
                            <c:param name="userId" value="${tempUser.id}" />
                        </c:url>

                        <tr>
                            <td>${tempUser.name}</td>
                            <td>${tempUser.role}</td>
                            <td>${tempUser.password}</td>
                            <td>${tempUser.login}</td>
                            <td>${tempUser.email}</td>
                            <td>${tempUser.country}</td>
                            <td>${tempUser.mobile}</td>

                            <td>
                                <!-- display the update link --> <a href="${updateLink}">Update</a>
                                | <a href="${deleteLink}"
                                     onclick="if (!(confirm('Are you sure you want to delete this user?'))) return false">Delete</a>
                            </td>

                        </tr>

                    </c:forEach>

                </table>

            </div>
        </div>
    </div>

</div>
</body>
</html>