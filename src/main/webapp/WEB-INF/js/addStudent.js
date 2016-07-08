function addStudent() {
    var firstName = $("#inputFirstName").val();
    var lastName = $("#inputLastName").val();
    var groupIds = []
    $("input[name='groupIds']:checked").each(function () {
        groupIds.push(parseInt($(this).val()));
    });

    if (groupIds.length > 0) {
        var json = '{"firstName" :"' + firstName +
            '", "lastName" :"' + lastName +
            '", "groupIds" :"' + groupIds + '"}';
    }
    else {
        var json = '{"firstName" :"' + firstName +
            '", "lastName" :"' + lastName +
            '", "groupIds" :"' + '0' + '"}';
    }
    
    $.ajax({
        url: '/student/add',
        type: 'POST',
        data: json,
        contentType: "application/json"
    })
        .success(function () {
            alert("New student was added");
            top.location.href = '/students';
        })
        .error(function () {
            alert("The data have not been validated. " +
                "Make sure that all fields are filled, they contain" +
                " more than 2 characters and don't contain  double space");
        })
}