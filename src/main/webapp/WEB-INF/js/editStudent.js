function editStudent(studentId) {
    var firstName = $("#inputFirstName").val();
    var lastName = $("#inputLastName").val();
    var groupIds = []
    $("input[name='groupIds']:checked").each(function () {
        groupIds.push(parseInt($(this).val()));
    });

    var json
    if (groupIds.length > 0) {
        json = '{"firstName" :"' + firstName +
            '", "lastName" :"' + lastName +
            '", "groupIds" :"' + groupIds + '"}';
    } else {
        json = '{"firstName" :"' + firstName +
            '", "lastName" :"' + lastName +
            '", "groupIds" :"' + '0' + '"}';
    }

    $.ajax({
        url: '/student/' + studentId,
        type: 'PUT',
        data: json,
        contentType: "application/json"
    })
        .success(function () {
            alert("Student was edited");
            top.location.href = '/students';
        })

        .error(function () {
            alert("The data have not been validated. " +
                "Make sure that all fields are filled, they contain" +
                " more than 2 characters and don't contain  double space");
        })
}