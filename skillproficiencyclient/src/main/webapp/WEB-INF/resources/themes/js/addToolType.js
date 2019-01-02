function saveToolType()
{
		var	newTooltype=document.getElementById("tooltypeText").value;
		//alert(newTooltype);
		path1=document.getElementById("contextPaht").value;
		$.ajax({
		url: path1+"/addTooltype/"+newTooltype,
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
	$("#ToolType_list").on("click",".editTooltype",function(){
		 var toolTId = $(this).attr('toolTypeID');
		if(count==0){
			var toolType=$(this).parent().parent().find("td:nth-child(1)").text();
			var append="<input class='form-control' value='"+toolType+"' style='width:100%'/>";
			$(this).val("Update");
			$(this).parent().parent().find("td:nth-child(1)").html(append);
			count++;
		}
		else if(count>=1){
			var certifiedBy="null";
			var	editedTooltype=$(this).parent().parent().find("td:nth-child(1)").find("input").val();
			//alert(editedAccredation);
			path1=document.getElementById("contextPaht").value;
			$.ajax({
				url: path1+"/updateTooltype/"+editedTooltype+"/"+toolTId,
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

/*function editTooltype(toolTId)
{
	alert(toolTId);
	$("#toolType"+toolTId).removeAttr('disabled');
	$("#editButton"+toolTId).hide();
	$("#updateButton"+toolTId).show();

		var	editedTooltype=document.getElementById("toolType"+toolTId).value;
		alert(editedTooltype);
		
	
}

function updateTooltype(toolTId)
{
	path1=document.getElementById("contextPaht").value;
	alert(toolTId);
	var	editedTooltype=document.getElementById("toolType"+toolTId).value;
	alert(editedTooltype);
	$.ajax({
		url: path1+"/updateTooltype/"+editedTooltype+"/"+toolTId,
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
