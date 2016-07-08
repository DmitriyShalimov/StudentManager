function deleteStudent(studentId) {
    var verification = confirm("Are you sure you want to delete this student?");
    if (verification == true) {
        $.ajax({
            url: '/student/delete/' + studentId,
            type: 'DELETE'
        })
            .success(function () {
                alert("Student was deleted");
                top.location.href = '/students';
            })
    }
}