function show() {

	var data = document.getElementById("orglevel").value;
	$('.orgShow').hide();
	$('.employeeShow').hide();
	$('.buShow').hide();
	$('.accountShow').hide();
	$('.projectShow').hide();
	$('.showDiv').hide();
	$("#bu").val(0);

	$('#account').empty();
	$('#project').empty();
	
	if (data === "organisation") {
		$('.showDiv').show();
	}
	if (data === "bu") {
		$('.buShow').show();
		$('.showDiv').show();
	}
	if (data === "account") {
		$('.buShow').show();
		$('.accountShow').show();
		$('.showDiv').show();
	}
	if (data === "project") {
		$('.buShow').show();
		$('.accountShow').show();
		$('.projectShow').show();
		$('.showDiv').show();

	}
	if (data === "employee") {
		$('.employeeShow').show();
		$('.showDiv').show();
	}

}

$("#clearBtn").click(function() {
	$("#orglevel").val("select");
	$("#bu").val(0);
	$("#account").val(0);
	$("#project").val(0);
	$("#startDatePicker").val("");
	$("#windowperiod").val("");
	$("#employeename").val("");
});


$(document)
		.ready(
				
				function() {
					$("#employeename")
							.on(
									"input",
									function() {
										path1 = document
												.getElementById("contextPaht").value;
										if ($(this).val().length >= 3) {
											console
													.log("this is for employee auto search");
											var SearchwithEmailID = $(this)
													.val();
											$
													.ajax({
														url : path1
																+ "/getEmpEmailList/"
																+ SearchwithEmailID,
														dataType : 'json',
														success : function(
																result) {
															// alert(result.message);
															// alert(result.list);
															result1 = [];
															for (var i = 0; i < result.list.length; i++) {
																result1
																		.push(result.list[i]);
															}
															var list_of_emp_id = "";
															for (var i = 0; i < result1.length; i++) {
																list_of_emp_id += "<li class='emp_id_list'>"
																		+ result1[i]
																		+ "</li>";
															}
															$("#uol").empty();
															$("#uol")
																	.append(
																			list_of_emp_id);
															$("#uol").show();
															$(".emp_id_list")
																	.on(
																			"click",
																			function() {
																				var emp_id = $(
																						this)
																						.text();
																				$(
																						"#employeename")
																						.val(
																								"");
																				$(
																						"#employeename")
																						.val(
																								emp_id);
																				$(
																						"#uol")
																						.hide();
																				$(
																						"#uol")
																						.empty();
																			});
														},
														error : function(error) {
															console
																	.log("error");
														}

													});
										} else {
											$("#uol").hide();
											$("#uol").empty();
											return false;
										}
									});
				});

$(document).ready(function() {
	
	 $('#startDatePicker').datepicker({
	        startDate: '-0m'
	        //endDate: '+2d'
	    }).on('changeDate', function(ev){
	        $('#startDatePicker').datepicker('hide');
	    });
	
	$("#bu").change(function() {
		$('#account').empty();
		$('#project').empty();

		var sval = $('#bu').val();
		if (sval == "Select") {
			$('#account').empty();
			// $('#devCenterSelect').empty();

		} else {
			// setDevelopmentCenter(sval);
			setAccount(sval);

		}
	});
	  $("#windowperiod").keyup(validate);

});


function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

/*function validate(e) {

  var regex = new RegExp("[0-9\b]+"); [1-365]","g"
  var string = this.value;

  var match = string.match(regex);
  if (!match) {
	  this.value = this.value.replace(regex, '');
		$("#windowperiod").val("");

  }
  
}*/
  
function setAccount(buId) {
	// alert(buId);
	path1 = document.getElementById("contextPaht").value;
	// alert(path1);
	$.ajax({
		url : path1 + "/getAccountList/" + buId,
		type : 'GET',
		success : function(message) {
			 if(message=="")
        	 {
        	 alert("no accounts available");
        	 }
			var dataList = JSON.parse(message);

			// alert(dataList);

			// var selectListData = "<option value='select'></option>";
			var selectListData="";
			selectListData = "<option value='0'>Select</option>";
			for ( var a in dataList) {
				selectListData += "<option value='" + dataList[a].accountID
						+ "'>" + dataList[a].accountName + "</option>";
			}
			$('#account').empty();
			$("#account").append(selectListData);

			

		},
		error : function(xhr, status, error) {
			alert(xhr);
		}
	});

}

	
	$("#account").change(function() {
	
		var sval = $('#account').val();
		$('#project').empty();
		if (sval == "Select") {
			$('#project').empty();
			// $('#devCenterSelect').empty();

		} else {
			// setDevelopmentCenter(sval);
			setProject(sval);

		}
	});


function setProject(buId) {
	path1 = document.getElementById("contextPaht").value;
	$.ajax({
		url : path1 + "/getProjectListData/" + buId,
		type : 'GET',
		success : function(message) {
			// alert(message);
             if(message=="")
            	 {
            	 alert("no projects available");
            	 }
			var dataList = JSON.parse(message);
			//alert(dataList);
			

			// var selectListData = "<option value='select'></option>";
			var selectListData="";
			selectListData = "<option value='0'>Select</option>";
			for ( var a in dataList) {
				selectListData += "<option value='" + dataList[a].projectID
						+ "'>" + dataList[a].projectName + "</option>";
			}
			$('#project').empty();
			$("#project").append(selectListData);

		},
		error : function(xhr, status, error) {
			alert(xhr);
		}
	});

}

function saveWindow()
{
	var conditionCheck=0;
	var level = document.getElementById("orglevel").value;
	
	var	bu=document.getElementById("bu").value;
	var	account=document.getElementById("account").value;
	var	project=document.getElementById("project").value;
	var	windowperiod=document.getElementById("windowperiod").value;
	var	employeename=document.getElementById("employeename").value;
	
	var	startDatePicker=document.getElementById("startDatePicker").value;
	var	org1=document.getElementById("org").value;

		path1=document.getElementById("contextPaht").value;
		if(level=="organisation")
		{
				if(windowperiod==""||startDatePicker=="")
					conditionCheck =1;
		}
		else if(level=="bu")
		{
		
		if(windowperiod==""||startDatePicker==""||bu=="0"||(windowperiod&&startDatePicker&&bu)=="")
			conditionCheck =1;
		}
		else if(level=="account")
		{
		if(windowperiod==""||startDatePicker==""||bu=="0"||account=="0"||(windowperiod&&startDatePicker&&bu&&account)=="")
			conditionCheck =1;
		}
		else if(level=="project")
		{
		if(windowperiod==""||startDatePicker==""||bu=="0"||account=="0"||project=="0"||(windowperiod&&startDatePicker&&bu&&account&&project)=="")
			conditionCheck =1;
		}
		else if(level=="employee")
		{
		if(windowperiod==""||startDatePicker==""||employeename==""||(windowperiod&&startDatePicker&&employeename)=="")
			conditionCheck =1;
		//alert(bu);
		/*bu = "";
		account="";
		project="";*/
		
		}
		if (parseInt(windowperiod)<1){
			conditionCheck =2;
		}
		/*if(level=="organisation"){
			bu="";
			account="";
			project="";
			employeename="";
		}
		else if(level=="bu")
		{
			account="";
			project="";
			employeename="";
		}
		else if(level=="account")
		{
			project="";
			employeename="";
		}
		else if(level=="employee")
			{
			bu="";
			account="";
			project="";
			}*/
		
		var dic={
				"buID":bu,
				"accID":account,
				"projectID":project,
				"triggerPeriod":windowperiod,
				"empEmailID":employeename,
				"triggerDate":startDatePicker,
				"organisation":org1
	 }
			
			
		var ss= JSON.stringify(dic);
		var jsonData = JSON.parse(ss);
		
		if(conditionCheck==0)
			{
		$.ajax({
			url: path1+"/updateWindowPeriod/",
			type: 'POST',
			data : jsonData,
			success: function (message) {
				alert(message);
				console.log("Saved successfully");
				 location.reload(true);
			},error:function (message) {
				console.log("Error message---save");
			}
		});
		}
		else if(conditionCheck==1)
			{
			alert("All fields are mandatory");

			}
		else 
		{
		alert("window period cannot be zero");

		}
}
/*$("#windowperiod").on("input",function(){
	var windowperiod = $("#windowperiod").val().trim();
});
$("#startDatePicker").on("input",function(){
	var startDatePicker = $("#startDatePicker").val().trim();
		});
*/
$('#orglevel').change(function(){

	var	orglevel=document.getElementById("orglevel").value;
	//$("#orglevel").val("select");
	//$("#bu").empty();
	//$("#account").empty();
	//$("#project").empty();
	$("#startDatePicker").val("");
	$("#windowperiod").val("");
	$("#employeename").val("");
	if(orglevel=="select")
		{
		document.getElementById("clearBtn").disabled = true;
		document.getElementById("assignBtn").disabled = true;
		}
	
	else{
		document.getElementById("clearBtn").disabled = false;
		document.getElementById("assignBtn").disabled = false;
	}
});



/*$(function(){
    $('#startDatePicker').datepicker({
        startDate: '-0m'
        //endDate: '+2d'
    }).on('changeDate', function(ev){
        $('#startDatePicker').datepicker('hide');
    });

});
*/