function saveAccredation()
{
	//alert("called to save Accredation");
	var	newAccredation=document.getElementById("accredationText").value;
	var certifiedBy="null";
	//alert(newAccredation);
//	alert(certifiedBy);
	if(newAccredation.trim()==""){
		alert("Please enter the data");
	}
	
	else{
		path1=document.getElementById("contextPaht").value;
		$.ajax({
			url: path1+"/addAccredation/"+newAccredation+"/"+certifiedBy,
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
	$("#Accre_list").on("click",".editAccre",function(){
		 var accredationId = $(this).attr('accID');
		if(count==0){
			var accId=$(this).parent().parent().find("td:nth-child(1)").text();
			var append="<input class='form-control' value='"+accId+"' style='width:100%'/>";
			$(this).val("Update");
			$(this).parent().parent().find("td:nth-child(1)").html(append);
			count++;
		}
		else if(count>=1){
			var certifiedBy="null";
			var	editedAccredation=$(this).parent().parent().find("td:nth-child(1)").find("input").val();
			//alert(editedAccredation);
			path1=document.getElementById("contextPaht").value;
			$.ajax({
				url: path1+"/updateAccrediation/"+editedAccredation+"/"+certifiedBy+"/"+accredationId,
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
/*function editAccredation(accredationId)
{
	alert(accredationId);
	$("#accrediation"+accredationId).removeAttr('disabled');
	$("#editButton"+accredationId).hide();
	$("#updateButton"+accredationId).show();

	var	editedAccredation=document.getElementById("accrediation"+accredationId).value;
	alert(editedAccredation);
	
	
	
}

function updateAccredation(accredationId)
{
	
	alert(accredationId);
	var certifiedBy="null";
	var	editedAccredation=document.getElementById("accrediation"+accredationId).value;
	alert(editedAccredation);
	$.ajax({
		url: path1+"/updateAccrediation/"+editedAccredation+"/"+certifiedBy+"/"+accredationId,
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


