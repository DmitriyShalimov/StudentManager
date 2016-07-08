$(document).ready(function () {
    $.validator.addMethod(
        "doesNotContainWhiteSpace",
        function (value, element) {
            if (value.indexOf('  ') < 0) {
                return true;
            }
            return false;
        },
        "Contains multiple whitespace chars."
    );

    $("#dataForm").validate({
        rules: {
            title: {
                required: true,
                minlength: 3,
                doesNotContainWhiteSpace: true
            },
            messages: {
                title: {
                    minlength: "Enter at least 3 characters."
                }
            }
        }
    });
})
