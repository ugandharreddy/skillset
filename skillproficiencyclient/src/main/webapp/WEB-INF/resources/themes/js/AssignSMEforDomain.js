function addSmeForDomain()
{
	var domainId = document.getElementById("Ddomain").value;
	//alert(domainId);
	var smeName=document.getElementById("smeText").value;
	//alert(smeName);
	path1=document.getElementById("contextPaht").value;
	if(domainId=="Select"){
		alert("Please Select Domain");
	}else if(smeName==""){
		alert("Please Select SME EmailID");
	}else{
		$.ajax({
			url: path1+"/addSMEforDomain/"+domainId+"/"+smeName,
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
	$("#Datatable_SMEDomain").on("click",".deleteSmeDomain",function(){
	   var domainID = $(this).attr('domainId');
	   var domainSmeEmail = $(this).attr('smeId');
	  // alert(domainID);
	  // alert(domainSmeEmail);
	 	path1=document.getElementById("contextPaht").value;
	 	$.ajax({
			url: path1+"/deleteSMEforDomain/"+domainSmeEmail+"/"+domainID,
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
function deleteSme(domainSmeEmail,domainID){
	alert(domainSmeEmail);
	alert(domainID);
	path1=document.getElementById("contextPaht").value;
	$.ajax({
		url: path1+"/deleteSMEforDomain/"+domainSmeEmail+"/"+domainID,
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
//search logic
$(document).ready(function(){
	$("#smeText").on("input",function() {
	path1=document.getElementById("contextPaht").value;
			if ($(this).val().length >= 3) {
				console.log("this is for employee auto search");
				var SearchwithEmail = $(this).val();
				$.ajax({
					url : path1 + "/SMEmailList/" + SearchwithEmail,
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