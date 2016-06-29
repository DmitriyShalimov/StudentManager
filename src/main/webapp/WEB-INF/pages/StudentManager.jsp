<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student manager</title>
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

            $('#btnShowGroup').click(function () {
                location.href = "/studentManager?id=" + $('#inpGroup')[0].value;
            });

            $('#btnSortById').click(function () {
                if (window.location.search != "") {
                    if (window.location.search.indexOf('sortBy') !== -1) {
                        window.location.href = window.location.pathname + window.location.search.substring(0, window.location.search.indexOf('&')) + "&sortId=sortById";
                    } else {
                        window.location.href = window.location.pathname + window.location.search + "&sortId=sortById";
                    }
                } else {
                    window.location.href = window.location.pathname + "?id=allGroup&sortId=sortById";
                }
            });

            $('#btnSortByName').click(function () {
                if (window.location.search != "") {
                    if (window.location.search.indexOf('sortBy') !== -1) {
                        window.location.href = window.location.pathname + window.location.search.substring(0, window.location.search.indexOf('&')) + "&sortId=sortByName";
                    } else {
                        window.location.href = window.location.pathname + window.location.search + "&sortId=sortByName";
                    }
                } else {
                    window.location.href = window.location.pathname + "?id=allGroup&sortId=sortByName";
                }
            });

            $('#btnSortByLastName').click(function () {
                if (window.location.search != "") {
                    if (window.location.search.indexOf('sortBy') !== -1) {
                        window.location.href = window.location.pathname + window.location.search.substring(0, window.location.search.indexOf('&')) + "&sortId=sortByLastName";
                    } else {
                        window.location.href = window.location.pathname + window.location.search + "&sortId=sortByLastName";
                    }
                } else {
                    window.location.href = window.location.pathname + "?id=allGroup&sortId=sortByLastName";
                }
            });

            $('#btnSortByFullName').click(function () {
                if (window.location.search != "") {
                    if (window.location.search.indexOf('sortBy') !== -1) {
                        window.location.href = window.location.pathname + window.location.search.substring(0, window.location.search.indexOf('&')) + "&sortId=sortByFullName";
                    } else {

                        window.location.href = window.location.pathname + window.location.search + "&sortId=sortByFullName";
                    }
                } else {
                    window.location.href = window.location.pathname + "?id=allGroup&sortId=sortByFullName";
                }
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

        #container .table {
            width: 750px;
            margin: 0px auto;
        }

    </style>

</head>
<body>
<div id="container">
    <div class="Menu">
        <a class="btn btn-primary btn-smt" href="<c:url value="/studentManager"/>">Students</a>
        <a class="btn btn-primary btn-smt" href="<c:url value="/addNewStudent"/>">New Student</a>
        <a class="btn btn-primary btn-smt" href="<c:url value="/groupManager"/>">Groups</a>
        <a class="btn btn-primary btn-smt" href="<c:url value="/addNewGroup"/>">New Group</a>

        <label>Find student by: </label>
        <select id="findStudentBy" class="form-control form-control-sm">
            <option value="/findById"></option>
            <option value="/findById">Id</option>
            <option value="/findByName">First Name</option>
            <option value="/findByLastName">Last Name</option>
            <option value="/findByNameLastName">Full Name</option>
        </select>
    </div>
    <br><br>
    <label name="label">Choose Group: </label>
    <br>
    <select name="select" class="form-control form-control-sm" id="inpGroup">
        <option><c:out value="allGroup"/></option>
        <c:forEach items="${allGroup}" var="Group">
            <option><c:out value="${Group.getTitle()}"/></option>
        </c:forEach>
    </select>
    <br>
    <a class="btn btn-primary btn-smt" id="btnShowGroup">Show all students from selected group</a>

    <a class="btn btn-primary btn-smt" id="btnSortById">Sort By Id</a>
    <a class="btn btn-primary btn-smt" id="btnSortByName">Sort By Name</a>
    <a class="btn btn-primary btn-smt" id="btnSortByLastName">Sort By Last Name</a>
    <a class="btn btn-primary btn-smt" id="btnSortByFullName">Sort By Full
        Name</a>
    <br><br>
    <table class="table table-hover" cellpadding="10">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Last Name</th>
            <th>Groups</th>
            <th></th>
        </tr>
        <c:forEach items="${allStudent}" var="Student">
            <tr>
                <td><c:out value="${Student.getId()}"/></td>
                <td><c:out value="${Student.getFirstName()}"/></td>
                <td><c:out value="${Student.getLastName()}"/></td>
                <td>
                    <c:choose>
                    <c:when test="${Student.getListGroup().size()>'1'}">
                        <c:forEach var="item" begin="0" end="${(Student.getListGroup().size()-2)}">
                            <c:out value="${Student.getListGroup().get(item)}, "/>
                        </c:forEach>
                        <c:out value="${Student.getListGroup().get(Student.getListGroup().size()-1)}"/>
                    </c:when>
                    <c:otherwise>
                    <c:forEach var="item" items="${Student.getListGroup()}">
                    <c:out value="${item}"/><p>
                    </c:forEach>
                    </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a class="btn btn-primary btn-xs" href="/editStudent?id=${Student.getId()}">Edit</a>
                    <a class="btn btn-primary btn-xs" href="/deleteStudent?id=${Student.getId()}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

