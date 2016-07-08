function addGroup() {
    var title = $("#inputTitle").val();
    var json = '{"title" :"' + title + '"}';

    $.ajax({
        url: '/group/add',
        type: 'POST',
        data: json,
        contentType: "application/json"
    })
        .success(function () {
            alert("New group was added");
            top.location.href = '/groups';
        })
        .error(function () {
            alert("The data have not been validated. " +
                "Make sure that all fields are filled, they contain" +
                " more than 2 characters and don't contain  double space");
        })
}