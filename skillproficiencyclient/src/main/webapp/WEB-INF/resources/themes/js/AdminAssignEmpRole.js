function assignEmp()
{
	//alert("called to save methodlogy");
		var	role=document.getElementById("Rrole").value;
		//alert(role);
		var	empText=document.getElementById("empText").value;
		//alert(empText);
		path1=document.getElementById("contextPaht").value;
		$.ajax({
		url: path1+"/AssignEmpRole/"+empText+"/"+role,
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
	$("#EmpRole_list").on("click",".deleteRole",function(){
		 var roleId = $(this).attr('roleId');
		 var empEmailId = $(this).attr('empId');
		// alert(roleId);
			//alert(empEmailId);
			path1=document.getElementById("contextPaht").value;
				$.ajax({
				url: path1+"/DeleteEmpRole/"+empEmailId+"/"+roleId,
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
/*function deleteRole(roleId,empEmailId)
{
	alert(roleId);
	alert(empEmailId);
	path1=document.getElementById("contextPaht").value;
		$.ajax({
		url: path1+"/DeleteEmpRole/"+empEmailId+"/"+roleId,
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
	$("#empText").on("input",function() {
	path1=document.getElementById("contextPaht").value;
			if ($(this).val().length >= 3) {
				console.log("this is for employee auto search");
				var SearchwithEmailID = $(this).val();
				$.ajax({
					url : path1 + "/EmpEmailList/" + SearchwithEmailID,
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
							$("#empText").val("");
							$("#empText").val(emp_id);
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