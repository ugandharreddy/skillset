function saveTool()
{
		var	newToolText=document.getElementById("toolText").value;
		//alert(newToolText);
		var	toolTypeId=document.getElementById("Ttool").value;
		//alert(toolTypeId);
		path1=document.getElementById("contextPaht").value;

		$.ajax({
			url: path1+"/addTool/"+newToolText+"/"+toolTypeId,
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
	var count=0;
	$("#Tool_list").on("click",".editTool",function(){
		 var toolId = $(this).attr('toolID');
		 var tooltypeId = $(this).attr('toolTypeID');
		 if(count==0){
				var tool=$(this).parent().parent().find("td:nth-child(2)").text();
				var append1="<input class='form-control' value='"+tool+"' style='width:100%'/>";
				$(this).val("Update");
				$(this).parent().parent().find("td:nth-child(2)").html(append1);
				count++;
		}
		else if(count>=1){
			var editedTool=$(this).parent().parent().find("td:nth-child(2)").find("input").val();
			//alert("sdf"+editedTool);
			//alert(editedAow);
			path1=document.getElementById("contextPaht").value;
			$.ajax({
				url: path1+"/updateTool/"+editedTool+"/"+tooltypeId+"/"+toolId,
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
function editTool(toolId)
{
	alert(toolId);
	$("#tool"+toolId).removeAttr('disabled');
	$("#editButton"+toolId).hide();
	$("#updateButton"+toolId).show();
}

function updateTool(tooltypeId,toolId)
{
	path1=document.getElementById("contextPaht").value;
	alert(toolId);
	var	editedTool=document.getElementById("tool"+toolId).value;
	alert(editedTool);
	$.ajax({
		url: path1+"/updateTool/"+editedTool+"/"+tooltypeId+"/"+toolId,
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
