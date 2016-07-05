$(document).ready(function () {
    $("#editprofileform").validate({
        rules: {
            firstname: {
                required: true,
                lettersonly: true,
                letterexist: true
            },
            lastname: {
                required: true,
                lettersonly: true,
                letterexist: true
            },
            contactnumber: {
                required: true,
                digits: true,
                minlength: 11,
                maxlength: 11
            },
            employmentdate: {
                required: true,
                date: true,
                notAdvanced: true
            },
            employeeposition: {
                required: true
            },
            employeestatus: {
                required: true,
                isNotDefault: true
            },
            regularizationdate: {
                required: true,
                notAdvanced: true
            },
            department: {
                required: true,
                isNotDefault: true
            }
        },
        messages: {
            firstname: {
                required: "This field is required",
                lettersonly: "This field can only contain letters, hyphens, and spaces",
                letterexist: "First name must have letters"
            },
            lastname: {
                required: "This field is required",
                lettersonly: "This field can only contain letters, hyphens, and spaces",
                letterexist: "Last name must have letters"
            },
            contactnumber: {
                required: "This field is required",
                digits: "This field can only contain numbers",
                minlength: "This field can only contain 11 digits",
                maxlength: "This field can only contain 11 digits"
            },
            employmentdate: {
                required: "This field is required",
                notAdvanced: "Date cannot be set in the future"
            },
            employeeposition: {
                required: "This field is required"
            },
            employeestatus: {
                required: "This field is required",
                isNotDefault: "Please select a status"
            },
            regularizationdate: {
                required: "This field is required",
                notAdvanced: "Date cannot be set in the future"
            },
            department: {
                required: "This field is required",
                isNotDefault: "Please select a department"
            }

        }
    });

    $('#adminrole').change(function () {
        if ($(this).prop('checked')) {
            $('#hrrole').prop('checked', true);
        }
    });
    $('#hrrole').change(function () {
        if ($('#adminrole').prop('checked')) {
            $('#hrrole').prop('checked', true);
        }
    });

    $('input:radio[name="employeestatus"]').change(
            function () {
                if (this.checked && this.value === 'regular') {
                    $('#regulardatediv').show();
                } else {
                    $('#regulardatediv').hide();
                }

            });
});

$.validator.addMethod("lettersonly", function (value, element) {
    return this.optional(element) || /^[a-zA-Z -]*$/.test(value);
});

$.validator.addMethod("letterexist", function (value, element) {
    if (/[a-zA-Z]/.test(value)) {
        return true;
    }
    else {
        return false;
    }
});

$.validator.addMethod("notAdvanced", function (value) {
    var myDate = value;
    if (Date.parse(myDate) < new Date()) {
        return true;
    }
    else {
        return false;
    }
});

$.validator.addMethod("isNotDefault", function (value) {
    if (value === "default") {
        return false;
    }
    else {
        return true;
    }
});