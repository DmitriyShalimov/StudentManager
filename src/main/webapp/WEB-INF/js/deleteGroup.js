function deleteGroup(groupId) {
    var verification = confirm("Are you sure you want to delete this group ");
    if (verification == true) {
        $.ajax({
            url: '/group/delete/' + groupId,
            type: 'DELETE'
        })
            .success(function () {
                alert("Group was deleted");
                top.location.href = '/groups';
            })
    }
}