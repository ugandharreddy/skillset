function saveStream()
{
		var	newStream=document.getElementById("streamText").value;
	//	alert(newStream);
		var	aowId=document.getElementById("Aaow").value;
		//alert(aowId);
		var	plEmail=document.getElementById("plEmailText").value;
		//alert(plEmail);
		if(plEmail.indexOf("@attra.com.au")!=-1){
			//alert("yes");
			path1=document.getElementById("contextPaht").value;
			$.ajax({
				url: path1+"/addStream/"+newStream+"/"+aowId+"/"+plEmail,
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
		else{
			alert("enter valid PlEmailId");
		}
}

$(document).ready(function(){
	var count=0;
	$("#Technology_list").on("click",".editTech",function(){
		 var aowId = $(this).attr('aowId');
		 var techID = $(this).attr('techId');
		 if(count==0){
				var tech=$(this).parent().parent().find("td:nth-child(2)").text();
				var plEmail=$(this).parent().parent().find("td:nth-child(3)").text();
				var append1="<input class='form-control' value='"+tech+"' style='width:100%'/>";
				$(this).val("Update");
				$(this).parent().parent().find("td:nth-child(2)").html(append1);
				var append2="<input  class='form-control' type='text'   id='plEmailText1' style='width:100%' value='"+plEmail+"'><ul id='uolId' style='display:none;' ></ul>";
				$(this).parent().parent().find("td:nth-child(3)").html(append2);
				count++;
		}
		else if(count>=1){
			var	editedStream=$(this).parent().parent().find("td:nth-child(2)").find("input").val();
			var editedPlemail=$(this).parent().parent().find("td:nth-child(3)").find("input").val();
			//alert(editedAow);
			path1=document.getElementById("contextPaht").value;
			if(editedPlemail.indexOf("@attra.com.au")!=-1){
				//alert("yes");
				$.ajax({
					url: path1+"/updateStream/"+editedStream+"/"+aowId+"/"+editedPlemail+"/"+techID,
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
			else{
				alert("enter valid PlEmailId");
			}
		}
		 
		$("#plEmailText1").on("input",function() {
			path1=document.getElementById("contextPaht").value;
					if ($(this).val().length >= 3) {
						console.log("this is for employee auto search");
						var SearchwithEmailID = $(this).val();
						//alert(SearchwithEmailID);
						$.ajax({
							url : path1 + "/PLEmailList/" + SearchwithEmailID,
							success : function(result) {
								//alert(result);
								var result= JSON.parse(result);
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
								$("#uolId").empty();
								$("#uolId").append(list_of_emp_id);
								$("#uolId").show();
								$(".emp_id_list").on("click", function() {
									var emp_id = $(this).text();
									$("#plEmailText1").val("");
									$("#plEmailText1").val(emp_id);
									$("#uolId").hide();
									$("#uolId").empty();
								});
							},
							error : function(error) {
								console.log("error");
							}
	
						});
					} else {
						$("#uolId").hide();
						$("#uolId").empty();
						return false;
					}
				});

		});
});
/*

function updateStream(aowId,techID)
{
	alert("ghfhgf"+aowId);
	alert("ghfhgf"+techID);
	var	editedStream=document.getElementById("stream"+techID).value;
	alert(editedStream);
	var	editedPlemail=document.getElementById("plemail"+techID).value;
	alert(editedPlemail);
	if(editedPlemail.indexOf("@attra.com.au")!=-1){
		alert("yes");
		path1=document.getElementById("contextPaht").value;
		$.ajax({
			url: path1+"/updateStream/"+editedStream+"/"+aowId+"/"+editedPlemail+"/"+techID,
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
	else{
		alert("enter valid PlEmailId");
	}
}*/

//search logic
$(document).ready(function(){
	$("#plEmailText").on("input",function() {
	path1=document.getElementById("contextPaht").value;
			if ($(this).val().length >= 3) {
				console.log("this is for employee auto search");
				var SearchwithEmailID = $(this).val();
				$.ajax({
					url : path1 + "/PLEmailList/" + SearchwithEmailID,
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
							$("#plEmailText").val("");
							$("#plEmailText").val(emp_id);
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