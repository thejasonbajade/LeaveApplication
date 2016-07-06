$(document).ready(function () {
    $("#leaveapplicationform").validate({
        rules: {
        	leavetype: {
        		isNotDefault: true
        	},
        	supervisor: {
        		isNotDefault: true
        	},
            startDate: {
                required: true,
                notLaterThanEnd: true
            },
            endDate: {
                required: true,
                notEarlierThanStart: true
            },
            reason: {
                required: true,
                nospecialchar: true,
                letterexist: true
            }
        },
        messages: {
        	leavetype: {
        		isNotDefault: "Please select a type"
        	},
        	supervisor: {
        		isNotDefault: "Please select a supervisor"
        	},
            startDate: {
                required: "This field is required",
                notLaterThanEnd: "Date cannot be later than end date"
            },
            endDate: {
                required: "This field is required",
                notEarlierThanStart: "Date cannot be earlier than start date"
            },
            reason: {
                required: "This field is required",
                nospecialchar: "This field cannot contain special characters",
                letterexist: "This field must contain letters"
            }
        }
    });
    
	$('#leavetype').change(function () {
		if ($('#startdate').val() != "" && $('#endDate').val() != ""){
			checkCredits();
		}
		if ($('#leavetype').val() == "VACATION_LEAVE"){
			$('.panel').attr('class', 'panel panel-default');
			$('#vlpanel').attr('class', 'panel panel-info');
			
		} else if ($('#leavetype').val() == "EMERGENCY_LEAVE"){
			$('.panel').attr('class', 'panel panel-default');
			$('#elpanel').attr('class', 'panel panel-info');
			
		} else if ($('#leavetype').val() == "SICK_LEAVE"){
			$('.panel').attr('class', 'panel panel-default');
			$('#slpanel').attr('class', 'panel panel-info');
			
		} else if ($('#leavetype').val() == "OFFSET"){
			$('.panel').attr('class', 'panel panel-default');
			$('#olpanel').attr('class', 'panel panel-info');
			
		}
    });
    
	$('#endDate').change(function () {
		if ($('#startdate').val() != "" && $('#leavetype').val() != null){
			checkCredits();
			
		}
    });

	$('#startdate').change(function () {
		if ($('#endDate').val() != "" && $('#leavetype').val() != null){
			checkCredits();
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
    if (value == null) {
        return false;
    }
    else {
        return true;
        
    }
});

$.validator.addMethod("notLaterThanEnd", function (value) {
	var endDate = $('#endDate').val();
	var bool = true;
	if (endDate !== ""){
		if (value > endDate){
			bool = false
		} else if (value <= endDate){
			bool = true;
		}
	}
	$("#endDate-error").remove();

	return bool;
});

$.validator.addMethod("notEarlierThanStart", function (value) {
	var startDate = $('#startDate').val();
	var bool = true;
	if (startDate !== ""){
		if (value >= startDate){
			bool = true;
			} else {
			bool = false;
		}
}
	$("#startDate").removeClass("error");
	$("#startdate-error").remove();
	return bool;
});


function checkCredits(){
		$.ajax({
			url : "CheckCredits",
		    data:
		    {
		    	vlcredits: $('#vlpanel .panel-body h3').text(),
		    	slcredits: $('#slpanel .panel-body h3').text(),
		    	elcredits: $('#elpanel .panel-body h3').text(),
		    	olcredits: $('#olpanel .panel-body h3').text(),
		    	leavetype: $('#leavetype').val(),
		    	startdate: $('#startdate').val(),
		      	endDate: $('#endDate').val()
		      },
			success : function(leave) {
				$('#warningdiv').text();
				$('#duration').val(leave.numberOfLeaveDays);
				lwopWarning(leave);
			}
		});
}

function lwopWarning(leave){
	var selected = $('#leavetype').val();
	var credits = getCreditsOfSelectedLeave(selected);
	var lwopCount;
	if (leave.numberOfLeaveDays > credits){
		if (selected === "EMERGENCY_LEAVE"){
			credits += getCreditsOfSelectedLeave("VACATION_LEAVE");
			$('#warningdiv').text("Warning: Your EL balance is not enough to cover the leave. VL will be deducted");
			if (leave.numberOfLeaveDays > credits){
				lwopCount = leave.numberOfLeaveDays - credits;
				$('#warningdiv').text("Warning: " + lwopCount + " day/s of your leave will be LWOP");
			}
		} else {
			lwopCount = leave.numberOfLeaveDays - credits;
			$('#warningdiv').text("Warning: Your balance is not enough to cover the duration. ");
			$('#warningdiv').append(lwopCount + " day/s of your leave will be LWOP");
		}
	}
}

function getCreditsOfSelectedLeave(leavetype){
	var credits;
	if (leavetype === "VACATION_LEAVE"){
		credits = $('#vlpanel .panel-body h3').text();
	} else if (leavetype === "SICK_LEAVE"){
		credits = $('#slpanel .panel-body h3').text();
	} else if (leavetype === "EMERGENCY_LEAVE"){
		credits = $('#elpanel .panel-body h3').text();
	} else if (leavetype === "OFFSET_LEAVE"){
		$('#olpanel .panel-body h3').text();
	}
	console.log("credits" + credits);
	return parseFloat(credits);
}

