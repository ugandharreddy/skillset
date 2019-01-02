function saveSkill()
{

		var	newStreamId=document.getElementById("Sstream").value;
		//alert(newStreamId);
		var	skill=document.getElementById("skillText").value;
		//alert(skill);
		path1=document.getElementById("contextPaht").value;
		$.ajax({
		url: path1+"/addSkill/"+skill+"/"+newStreamId,
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

$(document).ready(function(){
	var count=0;
	$("#Skill_list").on("click",".editSkill",function(){
		 var techID = $(this).attr('techId');
		 var skillId = $(this).attr('skillId');
		 if(count==0){
				var skill=$(this).parent().parent().find("td:nth-child(3)").text();
				var append1="<input class='form-control' value='"+skill+"' style='width:100%'/>";
				$(this).val("Update");
				$(this).parent().parent().find("td:nth-child(3)").html(append1);
				count++;
		}
		else if(count>=1){
			var editedskill=$(this).parent().parent().find("td:nth-child(3)").find("input").val();
			//alert(editedskill);
			path1=document.getElementById("contextPaht").value;
			$.ajax({
				url: path1+"/updateSkill/"+editedskill+"/"+techID+"/"+skillId,
				type: 'GET',
				success: function (message) {
					alert(message);
					console.log("updated successfully");
					 location.reload(true);
				},error:function (message) {
					console.log("Error message---edit");
				}
			});
		}
	});
});
/*
function editSkill(skillId)
{
	alert(skillId);
	$("#skill"+skillId).removeAttr('disabled');
	$("#editButton"+skillId).hide();
	$("#updateButton"+skillId).show();

		var	editedskill=document.getElementById("skill"+skillId).value;
		alert(editedskill);
		
	
}

function updateSkill(techID,skillId)
{
	path1=document.getElementById("contextPaht").value;
	alert(techID);
	alert(skillId);
	var	editedskill=document.getElementById("skill"+skillId).value;
	alert(editedskill);
	$.ajax({
		url: path1+"/updateSkill/"+editedskill+"/"+techID+"/"+skillId,
		type: 'GET',
		success: function (message) {
			alert(message);
			console.log("updated successfully");
			 location.reload(true);
		},error:function (message) {
			console.log("Error message---edit");
		}
	});
	
}*/
