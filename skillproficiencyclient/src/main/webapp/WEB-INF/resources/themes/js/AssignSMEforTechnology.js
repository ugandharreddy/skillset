function addSmeForTech()
{
	var streamId = document.getElementById("Sstream").value;
	//alert(streamId);
	var smeName=document.getElementById("smeText").value;
	//alert(smeName);
	path1=document.getElementById("contextPaht").value;
	
	if(streamId=="Select"){
		alert("Please Select Stream");
	}
	
	else if(streamId==""){
		alert("Please Select Area Of Work And Stream");
	}else if(smeName==""){
		alert("Please Select SME EmailID");
	}
	else{
		$.ajax({
			url: path1+"/addSMEforTech/"+streamId+"/"+smeName,
			type: 'GET',
			success: function (message) {
				alert(message);
				console.log("Saved successfully");
				 location.reload(true);
			},error:function (message) {
				console.log("Error message---save");
			}
		});
	}
	
}

$(document).ready(function(){
	var count=0;
	$("#SMETech_list").on("click",".deleteSmeTech",function(){
	   var techID = $(this).attr('techId');
	   var techSmeEmail = $(this).attr('smeId');
	  // alert(techID);
	  // alert(techSmeEmail);
	 	path1=document.getElementById("contextPaht").value;
		$.ajax({
			url: path1+"/deleteSMEforTech/"+techSmeEmail+"/"+techID,
			type: 'GET',
			success: function (message) {
				alert(message);
				console.log("Saved successfully");
				 location.reload(true);
			},error:function (message) {
				console.log("Error message---save");
			}
		});
	});
});
/*
function deleteSme(techSmeEmail,techID){
	alert(techSmeEmail);
	alert(techID);
	path1=document.getElementById("contextPaht").value;
	$.ajax({
		url: path1+"/deleteSMEforTech/"+techSmeEmail+"/"+techID,
		type: 'GET',
		success: function (message) {
			alert(message);
			console.log("Saved successfully");
			 location.reload(true);
		},error:function (message) {
			console.log("Error message---save");
		}
	});
}*/

$(document).ready(function(){
	$("#Aaow").change(function() {
		//alert("gdhs");
		var sval = $('#Aaow').val();
		if (sval == "Select") {
			$('#Sstream').empty();
			// $('#devCenterSelect').empty();
			
		} else {
			// setDevelopmentCenter(sval);
			setStream(sval);

		}
	});
});

function setStream(aowId) {
	//alert("hdf");
	path1=document.getElementById("contextPaht").value;
	//alert(path1);
	$.ajax({
		url : path1 + "/AdminSkillStream/" + aowId,
		type : 'GET',
		success : function(message) {

			var streamName = new Array();
			var streamId = new Array();

			var select, i, option;

			$('#Sstream').empty();
			
			select = document.getElementById('Sstream');
			option = document.createElement('option');
			option.value = "Select";
			option.text = "Select";
			select.add(option);

			for (i = 0; i <= message.length - 1; i++) {
				streamName[i] = JSON.stringify(message[i]['streamName']);
				streamId[i] = JSON.stringify(message[i]['streamId']);
				option = document.createElement('option');

				option.value = streamId[i];
				option.text = streamName[i].replace(/['"]+/g, '');
				select.add(option);
			}

		},
		error : function(xhr, status, error) {
			alert(xhr);
		}
	});

}
//search logic
$(document).ready(function(){
	$("#smeText").on("input",function() {
	path1=document.getElementById("contextPaht").value;
			if ($(this).val().length >= 3) {
				console.log("this is for employee auto search");
				var SearchwithEmailID = $(this).val();
				$.ajax({
					url : path1 + "/SMEmailList/" + SearchwithEmailID,
					dataType : 'json',
					success : function(result) {
						//alert(result.message);
						//alert(result.list);
						result1 = [];
						for (var i = 0; i < result.list.length; i++) {
							result1.push(result.list[i]);
						}
						var list_of_emp_id = "";
						for (var i = 0; i < result1.length; i++) {
							list_of_emp_id += "<li class='emp_id_list'>"
									+ result1[i] + "</li>";
						}
						$("#uol").empty();
						$("#uol").append(list_of_emp_id);
						$("#uol").show();
						$(".emp_id_list").on("click", function() {
							var emp_id = $(this).text();
							$("#smeText").val("");
							$("#smeText").val(emp_id);
							$("#uol").hide();
							$("#uol").empty();
						});
					},
					error : function(error) {
						console.log("error");
					}

				});
			} else {
				$("#uol").hide();
				$("#uol").empty();
				return false;
			}
		});
});