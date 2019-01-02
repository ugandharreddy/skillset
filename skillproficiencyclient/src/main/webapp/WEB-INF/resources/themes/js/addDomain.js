function saveDomain()
{
	//alert("called to save methodlogy");
		var	newDomain=document.getElementById("domainText").value;
		//alert(newDomain);
		var	plEmail=document.getElementById("plEmailText").value;
		//alert(plEmail);
		if(newDomain.trim()==""&&plEmail.trim()==""){
			alert("Please specify the Domain And PlEmailId");
		}
		else if(newDomain.trim()==""){
			alert("Please specify the domain");
		}
		else if(plEmail.trim()==""){
			alert("please Specify the PLEmailId");
		}
		else if(newDomain.trim()!=""&&plEmail.trim()!=""){
			if(plEmail.indexOf("@attra.com.au")!=-1){
				//alert("yes");
				path1=document.getElementById("contextPaht").value;
					$.ajax({
					url: path1+"/addDomain/"+newDomain+"/"+plEmail,
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
}
$(document).ready(function(){
	var count=0;
	$("#Domain_list").on("click",".editDomain",function(){
		 var domainId = $(this).attr('domainId');
		 //alert("dsfsd"+domainId);
		 if(count==0){
				var domain=$(this).parent().parent().find("td:nth-child(1)").text();
				var plEmail=$(this).parent().parent().find("td:nth-child(2)").text();
				var append1="<input class='form-control' value='"+domain+"' style='width:100%'/>";
				$(this).val("Update");
				$(this).parent().parent().find("td:nth-child(1)").html(append1);
				var append2="<input  class='form-control' type='text'   id='plEmailText1' style='width:100%' value='"+plEmail+"'><ul id='uolId' style='display:none;' ></ul>";
				$(this).parent().parent().find("td:nth-child(2)").html(append2);
				count++;
		}
		else if(count>=1){
			var certifiedBy="null";
			var	editedDomain=$(this).parent().parent().find("td:nth-child(1)").find("input").val();
			var plEmail=$(this).parent().parent().find("td:nth-child(2)").find("input").val();
			//alert(editedDomain+"sdf"+plEmail);
			//alert(editedAow);
			path1=document.getElementById("contextPaht").value;
			if(plEmail.indexOf("@attra.com.au")!=-1){
				//alert("yes");
				$.ajax({
					url: path1+"/updateDomain/"+editedDomain+"/"+plEmail+"/"+domainId,
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

//search logic
$(document).ready(function(){
	$("#plEmailText").on("input",function() {
	path1=document.getElementById("contextPaht").value;
			if ($(this).val().length >= 3) {
				console.log("this is for employee auto search");
				var SearchwithEmailID = $(this).val();
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