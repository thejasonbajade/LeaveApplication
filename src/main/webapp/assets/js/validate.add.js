$(document).ready(function () {
    
	validateAdd();
	otherAddEditFc();
    
});

function validateAdd(){
	$("#addemployeeform").validate({
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
            email: {
                required: true,
                email: true,
                onbemail: true
            },
            contactnumber: {
                digits: true,
                elevenDigitsOnly: true
            },
            employmentdate: {
                required: true,
                date: true,
                notAdvanced: true,
                notLaterThanRegularization: true
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
                date: true,
                notAdvanced: true,
                notEarlierThanEmployment: true
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
            email: {
                required: "This field is required",
                email: "Please input a valid email address",
                onbemail: "Address should have \"@orangeandbronze.com\" domain"
            },
            contactnumber: {
                digits: "This field can only contain numbers",
                elevenDigitsOnly: "This field can only contain 11 digits"
            },
            employmentdate: {
                required: "This field is required",
                notAdvanced: "Date cannot be set in the future",
                notLaterThanRegularization: "Date cannot be later than regularization"
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
                notAdvanced: "Date cannot be set in the future",
                notEarlierThanEmployment: "Date cannot be earlier than employment"
            },
            department: {
                required: "This field is required",
                isNotDefault: "Please select a department"
            }

        }
    });
}

function otherAddEditFc(){
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
    $('#employeestatus').change(function () {
        if ($('#employeestatus').prop('checked')) {
        	 $('#regularizationdate').show();
        } else{
        	$('#regularizationdate').hide();
        	$("#regularizationdate").removeClass("error");
        	$("#regularizationdate-error").remove();
        }
    });    
}

$.validator.addMethod("elevenDigitsOnly", function (value) {
	if (value.length == 11){
		return true;
	} else {
		return false;
	}
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

$.validator.addMethod('email',
        function (value, element) {
            return this.optional(element) || /(^[-!#$%&'*+/=?^_`{}|~0-9A-Z]+(\.[-!#$%&'*+/=?^_`{}|~0-9A-Z]+)*|^"([\001-\010\013\014\016-\037!#-\[\]-\177]|\\[\001-\011\013\014\016-\177])*")@((?:[A-Z0-9](?:[A-Z0-9-]{0,61}[A-Z0-9])?\.)+(?:[A-Z]{2,6}\.?|[A-Z0-9-]{2,}\.?)$)|\[(25[0-5]|2[0-4]\d|[0-1]?\d?\d)(\.(25[0-5]|2[0-4]\d|[0-1]?\d?\d)){3}\]$/i.test(value);
        });

$.validator.addMethod("onbemail", function (value) {
    if (value.indexOf("orangeandbronze.com", value.length - "@orangeandbronze.com".length) !== -1) {
        return true;

    } else {
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

$.validator.addMethod("notEarlierThanEmployment", function (value) {
    var regDate = value;
    var empDate = $('#employmentdate').val();
    if (empDate !== null) {
        if (empDate <= regDate) {
            return true;
        }
        else {
            return false;
        }
    }
});

$.validator.addMethod("notLaterThanRegularization", function (value) {
    var empDate = value;
    var regDate = $('#regularizationdate').val();
    if (regDate !== null) {
        if (empDate <= regDate) {
            return true;
        }
        else {
            return false;
        }
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

$.validator.addMethod("notLaterThanRegularization", function (value) {
	var regDate = $('#regularizationdate').val();
	var bool = true;
	if (regDate !== ""){
		if (value > regDate){
			bool = false
		} else{
			bool = true;
		}
	}
	
	if ($("#regularizationdate-error").text() === "Date cannot be earlier than employment"){
		$("#regularizationdate").removeClass("error");
		$("#regularizationdate-error").remove();
	}

	return bool;
});

$.validator.addMethod("notEarlierThanEmployment", function (value) {
	var empDate = $('#employmentdate').val();
	var bool = true;
	if (empDate !== ""){
		if (value > empDate){
			bool = true;
		} else{
			bool = false;
		}
	}
	if ($("#employmentdate-error").text() === "Date cannot be later than regularization"){
		$("#employmentdate").removeClass("error");
		$("#employmentdate-error").remove();
	}

	return bool;
});