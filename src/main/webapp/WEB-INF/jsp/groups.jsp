<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Groups</title>
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="/js/deleteGroup.js" type="text/javascript"></script>
    <link rel="stylesheet"
          href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet"
          href="/css/style.css" type="text/css"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="/students">Student manager</a>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                        <form class="navbar-form navbar-left" role="search" action="/students/search">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Search" name="name">
                            </div>
                            <button type="submit" class="btn btn-default">Search</button>
                        </form>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="active"><a href="/group/add">Add new group <span
                                    class="sr-only">(current)</span></a>
                            </li>
                        </ul>

                    </div>
                </div>
            </nav>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <br><br>
            <table class="table table-hover tblcolor" cellpadding="10">
                <tr>
                    <th>Id</th>
                    <th>Title</th>
                    <th></th>
                </tr>
                <c:forEach items="${allGroups}" var="group">
                    <tr>
                        <td><c:out value="${group.id}"/></td>
                        <td><c:out value="${group.title}"/></td>
                        <td>
                            <a class="btn btn-default btncolor btn-xs" name="id" href="/group/${group.id}">Edit</a>
                            <a class="btn btn-danger btn-xs" onclick="deleteGroup(${group.id})">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>

