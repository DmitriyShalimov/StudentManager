<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>"/>
    <script src="/js/sort.js"></script>
    <script src="/js/deleteStudent.js" type="text/javascript"></script>
    <link rel="stylesheet"
          href="/css/style.css" type="text/css"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <nav class="navbar navbar-default ">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="/students">Student manager</a>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <form class="navbar-form navbar-left block2" role="search" action="/students/search">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Search" name="name">
                            </div>
                            <button type="submit" class="btn btn-default">Search</button>
                        </form>
                        <ul class="nav navbar-nav navbar-right block1 ">
                            <li class="active"><a href="/groups">Show all groups <span
                                    class="sr-only">(current)</span></a>
                            </li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right block1">
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
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <div class="dropdown">
                <button class="btn btn-default dropdown-toggle " type="button" id="dropdownMenu1" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="true">
                    Select group
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                    <c:forEach items="${allGroups}" var="group">
                        <li><a href="/students/group/${group.id}">${group.title}</a></li>
                    </c:forEach>
                    <li role="separator" class="divider"></li>
                    <li><a href="/students">All Groups</a></li>
                </ul>
            </div>
        </div>
        <div class="col-md-9">
            <div class="btn-group" role="group" aria-label="sorting">
                <button type="button" class="btn btn-default " id="btnSortAsc">Sort by name ▼</button>
                <button type="button" class="btn btn-default " id="btnSortDesc">Sort by name ▲</button>
                <button type="button" class="btn btn-default " id="btnSortClear">Clear filters</button>
            </div>
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
                    <th>Name</th>
                    <th>Last Name</th>
                    <th>Groups</th>
                    <th></th>
                </tr>
                <c:forEach items="${students}" var="student">
                    <tr>
                        <td><c:out value="${student.id}"/></td>
                        <td><c:out value="${student.firstName}"/></td>
                        <td><c:out value="${student.lastName}"/></td>
                        <td><c:out value="${student.groups}"/></td>
                        <td>
                            <a class="btn btn-default  btncolor btn-xs " href="/student/${student.id}">Edit</a>
                            <a class="btn btn-danger btn-xs" onclick="deleteStudent(${student.id})">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>

