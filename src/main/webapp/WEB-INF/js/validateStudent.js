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
            firstName: {
                required: true,
                minlength: 3,
                doesNotContainWhiteSpace: true
            },
            lastName: {
                required: true,
                minlength: 3,
                doesNotContainWhiteSpace: true
            }
        },
        messages: {
            firstName: {
                minlength: "Enter at least 3 characters."
            },
            lastName: {
                minlength: "Enter at least 3 characters."
            }
        }
    });
})
