function saveMethodology()
{	
		//alert("called to save methodlogy");
		var	newMethodology=document.getElementById("methodologyText").value;
		//alert(newMethodology);
		path1=document.getElementById("contextPaht").value;
		$.ajax({
		url: path1+"/addMethodology/"+newMethodology,
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
	$("#Methodology_list").on("click",".editmeth",function(){
		 var methodologyId = $(this).attr('methID');
		if(count==0){
			var methName=$(this).parent().parent().find("td:nth-child(1)").text();
			var append="<input class='form-control' value='"+methName+"' style='width:100%;'/>";
			$(this).val("Update");
			$(this).parent().parent().find("td:nth-child(1)").html(append);
			count++;
		}
		else if(count>=1){
			var	editedMethodology=$(this).parent().parent().find("td:nth-child(1)").find("input").val();
			path1=document.getElementById("contextPaht").value;
			$.ajax({
				url: path1+"/updateMethodology/"+editedMethodology+"/" +methodologyId,
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
function editMethodology(methodologyId)
{
	alert(methodologyId);
	$("#method"+methodologyId).removeAttr('disabled');
	$("#editButton"+methodologyId).hide();
	$("#updateButton"+methodologyId).show();

		var	editedMethodology=document.getElementById("method"+methodologyId).value;
		alert(editedMethodology);
		
		path1=document.getElementById("contextPaht").value;
	
}

function updateMethodology(methodologyId)
{
	alert(methodologyId);
	var	editedMethodology=document.getElementById("method"+methodologyId).value;
	alert(editedMethodology);
	$.ajax({
		url: path1+"/updateMethodology/"+editedMethodology+"/" +methodologyId,
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
