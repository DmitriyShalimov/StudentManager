<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-widh, initial-scale=1.0">
    <title>Edit Student</title>
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.0/jquery.validate.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>"/>
    <script src="/js/validateStudent.js"></script>
    <script src="/js/editStudent.js"></script>
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
                            <li class="active"><a href="/groups">Show all groups <span
                                    class="sr-only">(current)</span></a>
                            </li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="active"><a href="/student/add">Add new student <span
                                    class="sr-only">(current)</span></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    </div>
</div>
<br><br>
<div class="container">
    <div class="row">
        <div class="col-md-2">
            <div class="dropdown">
            </div>
        </div>
        <div class="col-md-5">
            <div class="dropdown">
                <h3>Please change the necessary information</h3>
                <br><br>
                <form class="form-horizontal" id="dataForm" name="dataForm" action="/student/edit" method="post">
                    <input type="hidden" name="id" value="${student.id}">
                    <div class="form-group">
                        <label for="inputFirstName" class="col-sm-3 control-label">First Name</label>

                        <div class="col-sm-10 fieldsize">
                            <input type="text" name="firstName" class="form-control input-sm" id="inputFirstName"
                                   value="${student.firstName}">
                        </div>
                    </div>
                    <br><br>
                    <div class="form-group">
                        <label for="inputLastName" class="col-sm-3 control-label">Last Name</label>

                        <div class="col-sm-10 fieldsize">
                            <input type="text" name="lastName" class="form-control input-sm" id="inputLastName"
                                   value="${student.lastName}">
                        </div>
                    </div>
                </form>
                <br><br><br>
                <a class="btn btn-default btnsize btncolor " onclick="editStudent(${student.id})">Edit</a>
            </div>
        </div>
        <div class="col-md-4">
            <br><br><br><br>
            <h3>Select group</h3>
            <c:forEach items="${allGroups}" var="group">
                <label name="title" class="checkbox-inline">
                    <input type="checkbox" name="groupIds" id="groupIds" value="${group.id}"
                            <c:set var="contains" value="false"/>
                            <c:forEach var="item" items="${student.groups}">
                                <c:if test="${item.title eq group.title}">
                                    <c:set var="contains" value="true"/>
                                </c:if>
                            </c:forEach>
                           <c:if test="${contains == true}">checked="checked"</c:if>
                    /> ${group.title}
                </label>
                <br>
            </c:forEach>
            <br><br>
        </div>
    </div>
</div>
</body>
</html>
