$(document).ready(function () {
    $("#leaveapplicationform").validate({
        rules: {
            startdate: {
                required: true
            },
            enddate: {
                required: true,
                notEarlierThanStart: true
            },
            duration: {
                required: true
            },
            reason: {
                required: true,
                nospecialchar: true,
                letterexist: true
            }
        },
        messages: {
            startdate: {
                required: "This field is required"
            },
            enddate: {
                required: "This field is required",
                notEarlierThanStart: "The end date cannot be later than start date"
            },
            duration: {
              required: "This field is required"  
            },
            reason: {
                required: "This field is required",
                nospecialchar: "This field cannot contain special characters",
                letterexist: "This field must contain letters"
            }
        }
    });
});

$.validator.addMethod("nospecialchar", function (value, element) {
    return this.optional(element) || /[A-Za-z0-9 _.,!"'/$]/.test(value);
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

$.validator.addMethod("notEarlierThanStart", function (value) {
	var start = $('#startdate').val();
	if (value >= start){
		return true;
	} else if (value < start){
		return false;
	}
});



