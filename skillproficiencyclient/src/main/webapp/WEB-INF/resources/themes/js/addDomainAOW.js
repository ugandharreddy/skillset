
$(document).ready(function(){
		var count=0;
		$("#saveButton").on("click",function(){
			DomainAOW=$("#textField").val();
			if(DomainAOW.trim()==""){
				alert("please provide the data");
				return false;
			}
			addedInformation="<div class='col-lg-12 col-sm-12 col-md-12 col-xs-12 text-center' style='margin-bottom:20px;'><div class='col-lg-6 col-sm-6 col-md-6 col-xs-6'><input class='form-control' type='text' value='"+DomainAOW+"' disabled></div><div class='col-lg-6 col-sm-6 col-md-6 col-xs-6'><input type='button' value='Edit' class='btn btn-default btn-danger edit_save"+count+"'></input></div></div>";
			$("#textField").val("");
			$("#entered_information").append(addedInformation);
			$(".edit_save"+count).on("click",function(){
				if($(this).val()=="Edit"){
					$(this).parent().parent().find("div:first-child").find("input").prop("disabled",false);
					$(this).val("Save");
				}else{
					$(this).parent().parent().find("div:first-child").find("input").prop("disabled",true);
					$(this).val("Edit");
				}
			});
			count++;
		});
	});