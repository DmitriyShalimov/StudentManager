<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-widh, initial-scale=1.0">
    <title>Find student by name, last name</title>
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>"/>

    <script>
        $(document).ready(function () {
            if (window.location.pathname.indexOf('/findBy') !== -1) {
                $('#findStudentBy')[0].value = window.location.pathname;
            }

            $('.Menu a').map(function (index, element) {
                if (element.pathname === window.location.pathname) {
                    $(element).addClass('active');
                } else {
                    $(element).removeClass('active');
                }
                return null;
            });

            $('#findStudentBy').change(function (e) {
                if (window.location.pathname !== $('#findStudentBy')[0].value) {
                    location.href = $('#findStudentBy')[0].value;
                }
            });
        });
    </script>
    <style>
        #container {
            width: 800px;
            margin: 0px auto;
            margin-top: 40px;
        }

        #container .Menu {
            display: block;
            width: 680px;
            margin: 0px auto;
        }

        #container .form-control {
            display: inline-block;
            width: 420px;
        }

        #container .form-control-sm {
            width: 120px;
        }
    </style>
</head>
<body>
<div id="container">
    <div class="Menu">
        <a class="btn btn-primary btn-smt" href="<c:url value="/studentManager"/>">Students</a>
        <a class="btn btn-primary btn-smt btn-rnd" href="<c:url value="addNewStudent"/>">New Student</a>
        <a class="btn btn-primary btn-smt" href="<c:url value="/groupManager"/>">Groups</a>
        <a class="btn btn-primary btn-smt btn-rnd" href="<c:url value="/addNewGroup"/>">New Group</a>

        <label>Find student by: </label>
        <select id="findStudentBy" class="form-control form-control-sm">
            <option value="/findById">Id</option>
            <option value="/findByName">First Name</option>
            <option value="/findByLastName">Last Name</option>
            <option value="/findByNameLastName">Full Name</option>
        </select>
    </div>
    <br><br>

    <h1>Insert Full Name:</h1>
    <br><br>

    <form class="form-horizontal" action="/findByNameLastName" method="post">
        <div class="form-group">
            <label for="inputFirstName" class="col-sm-2 control-label">First Name</label>

            <div class="col-sm-10">
                <input type="text" name="firstName" class="form-control" id="inputFirstName" placeholder="Name">
            </div>
        </div>
        <br><br>

        <div class="form-group">
            <label for="inputLastName" class="col-sm-2 control-label">Last Name</label>

            <div class="col-sm-10">
                <input type="text" name="lastName" class="form-control" id="inputLastName" placeholder="Last name">
            </div>
        </div>
        <br>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary">Find</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
