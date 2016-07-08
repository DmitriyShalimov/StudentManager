<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-widh, initial-scale=1.0">
    <title>Edit group</title>
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.0/jquery.validate.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="/js/editGroup.js"></script>
    <link rel="stylesheet"
          href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>"/>
    <script src="/js/validateGroup.js"></script>
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
<br><br>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <div class="dropdown">
            </div>
        </div>
        <div class="col-md-6">
            <h3>Please change the necessary data about the student</h3>
            <br><br>
            <form class="form-horizontal" id="dataForm" name="dataForm" action="/group/edit" method="post">
                <input type="hidden" name="id" value="${group.id}">

                <div class="form-group">
                    <label for="inputTitle" class="col-sm-2 control-label">Title</label>
                    <div class="col-sm-10 fieldsize">
                        <input type="text" name="title" class="form-control input-sm" id="inputTitle"
                               value="${group.title}">
                    </div>
                </div>
                <br><br>
                <a class="btn btn-default btnsize btncolor " onclick="editGroup(${group.id})">Edit</a>
            </form>
        </div>
    </div>
</div>
</body>
</html>
