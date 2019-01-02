function saveAOW()
{
	//alert("called to save methodlogy");
		var	newAow=document.getElementById("aowText").value;
	//	alert(newAow);
		path1=document.getElementById("contextPaht").value;
		if(newAow.trim()==""){
			alert("Please enter the data");
		}
		else{
		$.ajax({
			url: path1+"/addAow/"+newAow,
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
	$("#Aow_list").on("click",".editAow",function(){
		 var aowId = $(this).attr('areaId');
		if(count==0){
			var aowIdNAme=$(this).parent().parent().find("td:nth-child(1)").text();
			var append="<input class='form-control' value='"+aowIdNAme+"' style='width:100%'/>";
			$(this).val("Update");
			$(this).parent().parent().find("td:nth-child(1)").html(append);
			count++;
		}
		else if(count>=1){
			var	editedAow=$(this).parent().parent().find("td:nth-child(1)").find("input").val();
			//alert(editedAow);
			path1=document.getElementById("contextPaht").value;
			$.ajax({
				url: path1+"/updateAow/"+editedAow+"/" +aowId,
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
/*function editAow(aowId)
{
	alert(aowId);
	$("#aow"+aowId).removeAttr('disabled');
	$("#editButton"+aowId).hide();
	$("#updateButton"+aowId).show();

		var	editedAow=document.getElementById("aow"+aowId).value;
		alert(editedAow);
		
	
}

function updateAow(aowId)
{
	path1=document.getElementById("contextPaht").value;
	alert(aowId);
	var	editedAow=document.getElementById("aow"+aowId).value;
	alert(editedAow);
	$.ajax({
		url: path1+"/updateAow/"+editedAow+"/" +aowId,
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
