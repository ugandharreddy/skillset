function saveSubDomain()
{
		var	newSubdomain=document.getElementById("subDomainText").value;
		//alert(newSubdomain);
		var	domainId=document.getElementById("Ddomain").value;
		//alert(domainId);
		path1=document.getElementById("contextPaht").value;
		$.ajax({
		url: path1+"/addSubdomain/"+newSubdomain+"/"+domainId,
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
	$("#SubDomain_list").on("click",".editSubDomain",function(){
		 var domainId = $(this).attr('domainId');
		 var subdomainId = $(this).attr('subDomainId');
		// alert("dsfsd"+domainId+"   "+subdomainId);
		 if(count==0){
				var domain=$(this).parent().parent().find("td:nth-child(1)").text();
				var SubDomain=$(this).parent().parent().find("td:nth-child(2)").text();
				var append1="<input class='form-control' value='"+SubDomain+"' style='width:100%'/>";
				$(this).val("Update");
				$(this).parent().parent().find("td:nth-child(2)").html(append1);
				count++;
		}
		else if(count>=1){
			var editedSubdomain=$(this).parent().parent().find("td:nth-child(2)").find("input").val();
			//alert("sdf"+editedSubdomain);
			//alert(editedAow);
			path1=document.getElementById("contextPaht").value;
			$.ajax({
				url: path1+"/updateSubdomain/"+editedSubdomain+"/"+domainId+"/"+subdomainId,
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
/*function editSubdomain(subdomainId)
{
	alert(subdomainId);
	$("#subDomain"+subdomainId).removeAttr('disabled');
	$("#editButton"+subdomainId).hide();
	$("#updateButton"+subdomainId).show();
}

function updateSubdomain(subdomainId,domainId)
{
	path1=document.getElementById("contextPaht").value;
	alert(subdomainId);
	var	editedSubdomain=document.getElementById("subDomain"+subdomainId).value;
	alert(editedSubdomain);
	$.ajax({
		url: path1+"/updateSubdomain/"+editedSubdomain+"/"+domainId+"/"+subdomainId,
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
