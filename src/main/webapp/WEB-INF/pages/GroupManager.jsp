<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>group manager</title>
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>"/>


    <script>
        $(document).ready(function () {

            // if on one of find by pages pre-populate select box
            if (window.location.pathname.indexOf('/pageFindBy') !== -1) {
                $('#findStudentBy')[0].value = window.location.pathname;
            }

            // highlight active tab in primary nav
            $('.Menu a').map(function(index, element) {
                if (element.pathname === window.location.pathname) {
                    $(element).addClass('active');
                } else {
                    $(element).removeClass('active');
                }
                // console.log(element.pathname, window.location.pathname);
                return null;
            });

            // event handler for dropdown filtering students by group
            $('#btnShowGroup').click(function () {
                location.href = "/StudentManager?id=" + $('#inpGroup')[0].value;
            });

            // /pageFindById
            // /pageFindByName
            // /pageFindByLastName
            // /pageFindByNameLastName
            $('#findStudentBy').change(function(e) {
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
        #container .table {
            width: 500px;
            margin: 0px auto;
        }

    </style>
</head>
<body>
<div id="container">
    <div class="Menu">
        <a class="btn btn-primary btn-smt" href="<c:url value="/studentManager"/>">Students</a>
        <a class="btn btn-primary btn-smt btn-rnd" href="<c:url value="/addNewStudent"/>">New Student</a>
        <a class="btn btn-primary btn-smt" href="<c:url value="/groupManager"/>">Groups</a>
        <a class="btn btn-primary btn-smt btn-rnd" href="<c:url value="/addNewGroup"/>">New Group</a>

        <label>Find student by: </label>
        <select id="findStudentBy" class="form-control form-control-sm">
            <option value=""> </option>
            <option value="/findById">Id</option>
            <option value="/findByName">First Name</option>
            <option value="/findByLastName">Last Name</option>
            <option value="/findByNameLastName">Full Name</option>
        </select>
    </div>
    <br><br>
    <table class="table table-hover" cellpadding="10">
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th></th>
        </tr>
        <c:forEach items="${allGroup}" var="Group">
            <tr>
                <td><c:out value="${Group.getId()}"/></td>
                <td><c:out value="${Group.getTitle()}"/></td>
                <td>
                    <a class="btn btn-primary btn-xs" href="/editGroup?id=${Group.getId()}">Edit</a>
                    <a class="btn btn-primary btn-xs" href="/deleteGroup?id=${Group.getId()}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

