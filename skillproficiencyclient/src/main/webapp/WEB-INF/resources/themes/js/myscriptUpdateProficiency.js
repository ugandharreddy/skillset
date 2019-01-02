$(document).ready(
		
			function() {
				//alert($.datepicker.formatDate('dd-mm-yyyy', new Date()));
				var d = new Date();
				var currentdate = d.getDate() + "-" + (d.getMonth() + 1) + "-"
						+ d.getFullYear();

				$('#startDatePicker').datepicker({
					format : 'dd-mm-yyyy',
					endDate : currentdate

				}).on(
						'changeDate',
						function(e) {
							// Revalidate the start date field
							$(this).datepicker('hide');
							$('#eventForm').formValidation('revalidateField',
									'startDate');

						});
//				$('#endDatePicker').datepicker({
//					format : 'dd-mm-yyyy',
//					endDate : currentdate
//
//				}).on(
//						'changeDate',
//						function(e) {
//							// Revalidate the start date field
//							$(this).datepicker('hide');
//							$('#eventForm').formValidation('revalidateField',
//									'startDate');
//
//						});
				
				
				

			});





function saveSkill() {
	
	var isPrimary="0";
	if($("#isPrime").is(':checked')) 
	{
		isPrimary="1";
	}	
	
	var skillId = $('#pskill').val();
	var experienceYear = $('#pexpY').val();
	var experienceMonth = $('#pexpM').val();;
	var comments=$('#comm').val();
	var selfAssesment=parseInt(document.getElementById("pself").value);//$('#pself').val();
	

	var proficiencyCoreSkill={
			"isPrimary":isPrimary,
			"skillId":skillId,
			"experienceYear":experienceYear,
			"experienceMonth":experienceMonth,
			"comments":comments,
			"selfAssesment":selfAssesment,

	};
	
	var result = confirm("You will not be allowed to change once saved. Are you sure to save?");
	if(result)
		{
	
	var path1 = document.getElementById("contextPaht").value;
	$.ajax({
		type : "POST",
		url : path1 + "/updateCoreSkill",
		data:proficiencyCoreSkill,
		success : function(message) {

alert(message);
location.href=path1+"/updateProficiency/1";

		},
		error : function(xhr) {
			alert("try again");
		}
	});
		}
	else
		{
		return false;
		}
}




function saveDomain() {
	
	var isPrimary="0";
	if($("#isDPrime").is(':checked')) 
	{
		isPrimary="1";
	}	
	
	var dc = $('#Ddomain').val();
	var d = $('#Dsub').val();
	var experienceYear = $('#DexpY').val();
	var experienceMonth = $('#DexpM').val();;
	var comments=$('#commt').val();
	//var selfAssesment=document.getElementById("Drate").value;//$('#Drate').val();
	var selfAssesment=parseInt(document.getElementById("Drate").value);

	var proficiencyDomain={
			"isPrimary":isPrimary,
			"sGroup":dc,
			"dName":d,
			"dExpYears":experienceYear,
			"dExpMonths":experienceMonth,
			"empComments":comments,
			"empRating":selfAssesment,

	};
	
	var result = confirm("You will not be allowed to change once saved. Are you sure to save?");
	if(result)
		{
	var path1 = document.getElementById("contextPaht").value;
	$.ajax({
		type : "POST",
		url : path1 + "/updateDomain",
		data:proficiencyDomain,
		success : function(message) {

alert(message);
location.href=path1+"/updateProficiency/2";

		},
		error : function(xhr) {
			alert("try again");
		}
	});
		}
	else
		{
		return false;
		}
}


function saveMethodology() {
	
	var skillId = $('#Mmethodology1').val();
	var experienceYear = $('#MexpY1').val();
	var experienceMonth = $('#Mexp1').val();;
	var comments=$('#Mcomments').val();
	var selfAssesment=parseInt(document.getElementById("Mrate1").value);//$('#Mrate1').val();
	

	var proficiencyMethodology={
			"mName":skillId,
			"mExpYears":experienceYear,
			"mExpMonths":experienceMonth,
			"comments":comments,
			"empRating":selfAssesment,

	};
	var result = confirm("You will not be allowed to change once saved. Are you sure to save?");
	if(result)
		{
	var path1 = document.getElementById("contextPaht").value;
	$.ajax({
		type : "POST",
		url : path1 + "/updateMethodology",
		data:proficiencyMethodology,
		success : function(message) {

alert(message);
location.href=path1+"/updateProficiency/3";

		},
		error : function(xhr) {
			alert("try again");
		}
	});
		}
	else
		{
		return false;
		}
}


function saveTools() {
	var tt = $('#TTtool').val();

	var tool = $('#Ttool').val();
	var experienceYear = $('#TexpY').val();
	var experienceMonth = $('#Texp').val();;
	var comments=$('#Tcomments').val();
	var selfAssesment=parseInt(document.getElementById("Trate").value);//$('#Trate').val();
	

	var proficiencyTool={
			"toolType":tt,
			"toolName":tool,
			"tExpYears":experienceYear,
			"tExpMonths":experienceMonth,
			"empComments":comments,
			"empRating":selfAssesment,

	};
	var result = confirm("You will not be allowed to change once saved. Are you sure to save?");
	if(result)
		{
	var path1 = document.getElementById("contextPaht").value;
	$.ajax({
		type : "POST",
		url : path1 + "/updateTool",
		data:proficiencyTool,
		success : function(message) {

alert(message);
location.href=path1+"/updateProficiency/4";

		},
		error : function(xhr) {
			alert("try again");
		}
	});
		}
	else
		{
		return false;
		}
}




function saveAccer() {
	var aName = $('#Acertified').val();

	var aNo = $('#Aname').val();
	var date = $('#startDatePicker').val();
	

	var accreditationObject={
			"accreditationName":aName,
			"certificateNo":aNo,
			"issueDt":date,

	};
	var result = confirm("You will not be allowed to change once saved. Are you sure to save?");
	if(result)
		{
	var path1 = document.getElementById("contextPaht").value;
	$.ajax({
		type : "POST",
		url : path1 + "/updateAcc",
		data:accreditationObject,
		success : function(message) {

alert(message);
location.href=path1+"/updateProficiency/5";

		},
		error : function(xhr) {
			alert("try again");
		}
	});
		}
	else
		{
		return false;
		}
}

$("#Pareaofwork").change(function() {
	
	$('#pskill').html(" ");
	 $('#pstream').prop('selectedIndex',0);
	 $('#pexpY').prop('selectedIndex',0);
	 $('#pexpM').prop('selectedIndex',0);
	 $('#pself').prop('selectedIndex',0);
	 $('#comments').val("");
});


$("#dareaofwork").change(function() {
	
//	$('#pskill').html(" ");
//	 $('#pstream').prop('selectedIndex',0);
//	 $('#pexpY').prop('selectedIndex',0);
//	 $('#pexpM').prop('selectedIndex',0);
//	 $('#pself').prop('selectedIndex',0);
//	 $('#comments').val("");
});

$("#pstream").change(function() {
	$('#pskill').prop('selectedIndex',0);
				$('#pexpY').prop('selectedIndex',0);
				 $('#pexpM').prop('selectedIndex',0);
				 $('#pself').prop('selectedIndex',0);
				 $('#comments').val("");
			});

$("#pskill").change(function() {
	$('#pexpY').prop('selectedIndex',0);
	 $('#pexpM').prop('selectedIndex',0);
	 $('#pself').prop('selectedIndex',0);
	 $('#comments').val("");
});

$("#dareaofwork").change(function() {
	
	$('#Ddomain').prop('selectedIndex',0);

	$('#Dsub').prop('selectedIndex',0);
	 $('#DexpY').prop('selectedIndex',0);
	 $('#DexpM').prop('selectedIndex',0);
	 $('#Drate').prop('selectedIndex',0);
	 $('#empComments').val("");
});

$("#Ddomain").change(function() {
	

	$('#Dsub').prop('selectedIndex',0);
	 $('#DexpY').prop('selectedIndex',0);
	 $('#DexpM').prop('selectedIndex',0);
	 $('#Drate').prop('selectedIndex',0);
	 $('#empComments').val("");
});
$("#Dsub").change(function() {
	

	
	 $('#DexpY').prop('selectedIndex',0);
	 $('#DexpM').prop('selectedIndex',0);
	 $('#Drate').prop('selectedIndex',0);
	 $('#empComments').val("");
});
$("#Mmethodology1").change(function() {
	

	
	 $('#MexpY1').prop('selectedIndex',0);
	 $('#Mexp1').prop('selectedIndex',0);
	 $('#Mrate1').prop('selectedIndex',0);
	 $('#Mcomments').val("");
});
$("#TTtool").change(function() {
	

	 $('#Ttool').prop('selectedIndex',0);
	 $('#TexpY').prop('selectedIndex',0);
	 $('#Texp').prop('selectedIndex',0);
	 $('#Trate').prop('selectedIndex',0);
	 $('#Tcomments').val("");
});
$("#Ttool").change(function() {
	


	 $('#TexpY').prop('selectedIndex',0);
	 $('#Texp').prop('selectedIndex',0);
	 $('#Trate').prop('selectedIndex',0);
	 $('#Tcomments').val("");Ttool
});

$("#Acertified").change(function() {
	

	
$('#Aname').val("");
	 $('#startDatePicker').val("");

});















	
	$("#pexpY").change(function() {
		var Experience = document.getElementById("pexpY").value;
	
		var Monthtemp;
		var Experiencetemp;
	
		if (Experience<1){
			Experiencetemp =1;
			
		}
		else if (Experience<2 && Experience>=1){
			Experiencetemp =2;
			
		}
		else if (Experience<5 && Experience>=2){
			Experiencetemp =3;
			
		}
		else if (Experience<8 && Experience>=5){
			Experiencetemp =4;
			
		}
		else {
			Experiencetemp=5;
		}
		
		
		
		if(Experience==expY){
			
			Monthtemp=expM;
			TechMonth(Monthtemp);
		
		}
		if(Experience!=expY){
		
			Monthtemp=11;
			TechMonth(Monthtemp);
			
		}
		
		
	 SelfRating(Experiencetemp);
		
	});
	$("#DexpY").change(function() {
		var Experience = document.getElementById("DexpY").value;
		var Monthtemp;
		var Experiencetemp;
		
	/*	if (Experience<=3){
			Experiencetemp =3;
			
		}
		else if(Experience>3){
			Experiencetemp=5;
		}*/
		if (Experience<1){
			Experiencetemp =1;
			
		}
		else if (Experience<2 && Experience>=1){
			Experiencetemp =2;
			
		}
		else if (Experience<5 && Experience>=2){
			Experiencetemp =3;
			
		}
		else if (Experience<8 && Experience>=5){
			Experiencetemp =4;
			
		}
		else {
			Experiencetemp=5;
		}
		
		if(Experience==expY){
		
			Monthtemp=expM;
		
			DomainMonth(Monthtemp);
		
		}
		if(Experience!=expY){
		
			Monthtemp=11;
			DomainMonth(Monthtemp);
			
		}
		
		SelfRatingDomain(Experiencetemp);
		
	});
	$("#MexpY1").change(function() {
		var Experience = document.getElementById("MexpY1").value;
		var Monthtemp;
		var Experiencetemp;
		/*if (Experience<=3){
			Experiencetemp =3;
			
		}
		else if(Experience>3){
			Experiencetemp=5;
		}*/
		if (Experience<1){
			Experiencetemp =1;
			
		}
		else if (Experience<2 && Experience>=1){
			Experiencetemp =2;
			
		}
		else if (Experience<5 && Experience>=2){
			Experiencetemp =3;
			
		}
		else if (Experience<8 && Experience>=5){
			Experiencetemp =4;
			
		}
		else {
			Experiencetemp=5;
		}
if(Experience==expY){
			
			Monthtemp=expM;
			MethodMonth(Monthtemp);
		
		}
		if(Experience!=expY){
			
			Monthtemp=11;
			MethodMonth(Monthtemp);
			
		}
		
		SelfRatingMethod(Experiencetemp);
		
	});
	$("#TexpY").change(function() {
		var Experience = document.getElementById("TexpY").value;
		var Monthtemp;
		var Experiencetemp;
		/*if (Experience<=3){
			Experiencetemp =3;
			
		}
		else if(Experience>3){
			Experiencetemp=5;
		}*/
		if (Experience<1){
			Experiencetemp =1;
			
		}
		else if (Experience<2 && Experience>=1){
			Experiencetemp =2;
			
		}
		else if (Experience<5 && Experience>=2){
			Experiencetemp =3;
			
		}
		else if (Experience<8 && Experience>=5){
			Experiencetemp =4;
			
		}
		else {
			Experiencetemp=5;
		}
		if(Experience==expY){
			
			Monthtemp=expM;
		
			ToolMonth(Monthtemp);
		
		}
		if(Experience!=expY){
		
			Monthtemp=11;
			ToolMonth(Monthtemp);
			
		}
		
		SelfRatingTool(Experiencetemp);
		
	});

	$("#pstream").change(function() {
		var path1 = document.getElementById("contextPaht").value;

		$.ajax({
			type : "POST",
			url : path1 + "/getSkills/" + this.value,

			success : function(message) {
				var skill = new Array();
				var skillId = new Array();
				var options_str = "";
				var select, i, option;
				$('#pskill').empty();
				select = document.getElementById('pskill');
				option = document.createElement('option');
				option.value = "0";
				option.text = "Select";
				select.add(option);
				for (i = 0; i <= message.length; i++) {

					//remove " in JSON.stringify(message[i]['skill'])	  
					skill[i] = JSON.stringify(message[i]['skill']);
					skillId[i] = JSON.stringify(message[i]['skillId']);
					option = document.createElement('option');

					option.value = skillId[i];

					option.text = skill[i].replace(/['"]+/g, '');
					select.add(option);

				}
			},
			error : function() {

				alert("Data not found");
			}
		});

	});

	$("#Pareaofwork").change(function() {
		var path1 = document.getElementById("contextPaht").value;

		$.ajax({
			type : "POST",
			url : path1 + "/getStreamByAOW/" + this.value,

			success : function(message) {

				var skill = new Array();
				var skillId = new Array();
				var options_str = "";
				var select, i, option;
				$('#pstream').empty();
				select = document.getElementById('pstream');
				option = document.createElement('option');
				option.value = "0";
				option.text = "Select";
				select.add(option);
				for (i = 0; i <= message.length; i++) {

					//remove " in JSON.stringify(message[i]['skill'])	  
					skill[i] = JSON.stringify(message[i]['streamName']);
					skillId[i] = JSON.stringify(message[i]['streamId']);
					option = document.createElement('option');

					option.value = skillId[i];

					option.text = skill[i].replace(/['"]+/g, '');
					select.add(option);

				}
			},
			error : function() {
				$('#pstream').empty();

				alert("Data not found");
			}
		});

	});

	$("#Ddomain").change(function() {
		var path1 = document.getElementById("contextPaht").value;

		$.ajax({
			type : "POST",
			url : path1 + "/getSubdomain/" + this.value,

			success : function(message) {
				
				var skill = new Array();
				var skillId = new Array();
				var options_str = "";
				var select, i, option;
				$('#Dsub').empty();
				select = document.getElementById('Dsub');
				option = document.createElement('option');
				option.value = "0";
				option.text = "Select";
				select.add(option);
				for (i = 0; i <= message.length; i++) {

					//remove " in JSON.stringify(message[i]['skill'])	  
					skill[i] = JSON.stringify(message[i]['domainName']);
					skillId[i] = JSON.stringify(message[i]['domainId']);
					option = document.createElement('option');

					option.value = skillId[i].replace(/['"]+/g, '');

					option.text = skill[i].replace(/['"]+/g, '');
					select.add(option);

				}
			},
			error : function() {
				$('#Dsub').empty();

				alert("Data not found");
			}
		});

	});

	$("#TTtool").change(function() {
		var path1 = document.getElementById("contextPaht").value;
		$.ajax({
			type : "POST",
			url : path1 + "/gettools/" + this.value,

			success : function(message) {
				var skill = new Array();
				var skillId = new Array();
				var options_str = "";
				var select, i, option;
				$('#Ttool').empty();
				select = document.getElementById('Ttool');
				option = document.createElement('option');
				option.value = "0";
				option.text = "Select";
				select.add(option);
				for (i = 0; i <= message.length; i++) {

					//remove " in JSON.stringify(message[i]['skill'])	  
					skill[i] = JSON.stringify(message[i]['toolId']);
					skillId[i] = JSON.stringify(message[i]['toolName']);
					option = document.createElement('option');

					option.value = skill[i].replace(/['"]+/g, '');

					option.text = skillId[i].replace(/['"]+/g, '');
					select.add(option);

				}
			},
			error : function() {
				$('#Ttool').empty();

				alert("Data not found");
			}
		});

	});
	
	$("#pexpY,#pexpM").change(function() {
	var a=document.getElementById("pexpY").value;
	var b=document.getElementById("pexpM").value;
	if((a==0)&&(b==0)){
		$('#pself').prop('selectedIndex',0);
		$("#pself").attr('disabled', true);
		 
	}
	else{
		$("#pself").attr('disabled', false);
	}
		
	});
	$("#DexpY,#DexpM").change(function() {
		var a=document.getElementById("DexpY").value;
		var b=document.getElementById("DexpM").value;
		if((a==0)&&(b==0)){
			$('#Drate').prop('selectedIndex',0);
			$("#Drate").attr('disabled', true);
		
		}
		else{
			$("#Drate").attr('disabled', false);
		}
			
		});
	$("#MexpY1,#Mexp1").change(function() {
		var a=document.getElementById("MexpY1").value;
		var b=document.getElementById("Mexp1").value;
	
		
		if((a==0)&&(b==0)){
			$('#Mrate1').prop('selectedIndex',0);
			$("#Mrate1").attr('disabled', true);
		
		}
		else{
			$("#Mrate1").attr('disabled', false);
		}
			
		});
	$("#TexpY,#Texp").change(function() {
		var a=document.getElementById("TexpY").value;
		var b=document.getElementById("Texp").value;
	
		
		if((a==0)&&(b==0)){
			$('#Trate').prop('selectedIndex',0);
			$("#Trate").attr('disabled', true);
		
		}
		else{
			$("#Trate").attr('disabled', false);
		}
			
		});
	function editDomain(row) {

		row.closest('tr').find('select').removeAttr("disabled");
		//row.closest('tr').find('input[type=checkbox]').removeAttr("disabled");

		row
				.closest('tr')
				.find('td[id=editUpdate]')
				.append(
						'<button class="btn btn-danger" id="DomainUpdate" onClick="updateDomain($(this))" type="save">Update</button>');
		row.closest('tr').find('#Dedit').remove();

	}
	function editMethodology(row) {
		row.closest('tr').find('select').removeAttr("disabled");
		row
				.closest('tr')
				.find('td[id=editUpdate]')
				.append(
						'<button class="btn btn-danger" id="MethodologyUpdate" onClick="updateMethod($(this))" type="save">Update</button>');
		row.closest('tr').find('#Dedit').remove();

	}
	function editTool(row) {

		row.closest('tr').find('select').removeAttr("disabled");

		row
				.closest('tr')
				.find('td[id=editUpdate]')
				.append(
						'<button class="btn btn-danger" id="ToolUpdate" onClick="updateTool($(this))" type="save">Update</button>');
		row.closest('tr').find('#Dedit').remove();

	}
	function editTech(row) {

		row.closest('tr').find('select').removeAttr("disabled");
	//	row.closest('tr').find('input[type=checkbox]').removeAttr("disabled");

		row.closest('tr').find('td[id=editUpdate]').append('<button class="btn btn-danger" onClick="updateTech($(this))" id="TechUpdate" type="save">Update</button>');
		row.closest('tr').find('#Dedit').remove();

	}

	function updateTech(row) {
		var techId = row.closest('tr').find('span[id=TechName]').html();
		var expInyear = row.closest('tr').find('select[id=techenableY]').val();
		var expInMonth = row.closest('tr').find('select[id=techenableM]').val();
		
		var path1 = document.getElementById("contextPaht").value;
		$.ajax({
			type : "POST",
			url : path1 + "/updateTechExp/" + techId + "/" + expInyear + "/"
					+ expInMonth,
			success : function(message) {

row.closest('tr').find('select').prop('disabled', 'disabled');
// row.closest('tr').find('input[type=checkbox]').prop('disabled', 'disabled');
row.closest('tr').find('td[id=editUpdate]').append('<button class="btn btn-danger" onclick="editTech($(this));" type="button" id="Dedit">Edit</button>');
row.closest('tr').find('#TechUpdate').remove();


			},
			error : function() {
				alert("try again");
			}
		});
	}

	function updateDomain(row) {

		var domainId = row.closest('tr').find('span[id=dName]').html();
		var expInyear = row.closest('tr').find('select[id=denableY]').val();
		var expInMonth = row.closest('tr').find('select[id=denableM]').val();

		var path1 = document.getElementById("contextPaht").value;
		$.ajax({
			type : "POST",
			url : path1 + "/updateDomainExp/" + domainId + "/" + expInyear
					+ "/" + expInMonth,
			success : function(message) {
				row.closest('tr').find('select').prop('disabled', 'disabled');
			
				row.closest('tr').find('td[id=editUpdate]').append('<button class="btn btn-danger" onclick="editDomain($(this));" type="submit" id="Dedit">Edit</button>');
				row.closest('tr').find('#DomainUpdate').remove();
			},
			error : function() {
				alert("try again");
			}
		});
	}

	function saveTech(techForm)
	{
		var oForm = techForm;
		
		var path1 = document.getElementById("contextPaht").value;
		$.ajax({
			type : "POST",
			url : path1 + "/saveTech",
			data: oForm,
			success : function(message) {
			//	alert(message);
				
			},
			error : function() {
				alert("try again");
			}
		});
		
		
	}
	
	function updateMethod(row)

	{

		var methodlogyId = row.closest('tr').find('span[id=Mname]').html();
		var expInyear = row.closest('tr').find('select[id=MenableY]').val();
		var expInMonth = row.closest('tr').find('select[id=MenableM]').val();
		var path1 = document.getElementById("contextPaht").value;
		$.ajax({
			type : "POST",
			url : path1 + "/updateMethodExp/" + methodlogyId + "/" + expInyear
					+ "/" + expInMonth,
			success : function(message) {
				row.closest('tr').find('select').prop('disabled', 'disabled');
				row.closest('tr').find('input[type=checkbox]').prop('disabled', 'disabled');
				row.closest('tr').find('td[id=editUpdate]').append('<button class="btn btn-danger" onclick="editMethodology($(this));" type="submit" id="Dedit">Edit</button>');
				row.closest('tr').find('#MethodologyUpdate').remove();
			},
			error : function() {
				alert("try again");
			}
		});
	}

	function updateTool(row) {
		var toolId = row.closest('tr').find('span[id=Tname]').html();
		var expInyear = row.closest('tr').find('select[id=TenableY]').val();
		var expInMonth = row.closest('tr').find('select[id=TenableM]').val();
		var path1 = document.getElementById("contextPaht").value;
		$.ajax({
			type : "POST",
			url : path1 + "/updateToolsExp/" + toolId + "/" + expInyear + "/"
					+ expInMonth,
			success : function(message) {
				row.closest('tr').find('select').prop('disabled', 'disabled');
				row.closest('tr').find('input[type=checkbox]').prop('disabled', 'disabled');
				row.closest('tr').find('td[id=editUpdate]').append('<button class="btn btn-danger" onclick="editTool($(this));" type="submit" id="Dedit">Edit</button>');
				row.closest('tr').find('#ToolUpdate').remove();
			},
			error : function() {
				alert("try again");
			}
		});
	}
	
	function monthYearTech(row){
		
		var Experience = row.closest('tr').find('select[id=techenableY]').val();
		
		
		var Monthtemp;
		var Experiencetemp;
		

	
if(Experience==expY){
			
			Monthtemp=expM;
			
			row.closest('tr').find('select[id=techenableM]').html("<option value='0'>Month</option>");
				for (i = 0; i <= Monthtemp; i++) {

					option[i] = '<option value='+i+'>'
							+ i
							+ '</option>';

					row.closest('tr').find('select[id=techenableM]').append(option[i]);
				}
			
		
		}
		if(Experience!=expY){
		
			Monthtemp=11;
			
			row.closest('tr').find('select[id=techenableM]').html("<option value='0'>Month</option>");
				for (i = 0; i <= Monthtemp; i++) {

					option[i] = '<option value='+i+'>'
							+ i
							+ '</option>';

					row.closest('tr').find('select[id=techenableM]').append(option[i]);
				}
			
			
		}
		
		

		
	}
	
	
function monthYearDomain(row){
		
		var Experience = row.closest('tr').find('select[id=denableY]').val();
		
		
		var Monthtemp;
		var Experiencetemp;
		

	
if(Experience==expY){
			
			Monthtemp=expM;
		
			row.closest('tr').find('select[id=denableM]').html("<option value='0'>Month</option>");
				for (i = 0; i <= Monthtemp; i++) {

					option[i] = '<option value='+i+'>'
							+ i
							+ '</option>';

					row.closest('tr').find('select[id=denableM]').append(option[i]);
				}
			
		
		}
		if(Experience!=expY){
		
			Monthtemp=11;

			row.closest('tr').find('select[id=denableM]').html("<option value='0'>Month</option>");
				for (i = 0; i <= Monthtemp; i++) {

					option[i] = '<option value='+i+'>'
							+ i
							+ '</option>';

					row.closest('tr').find('select[id=denableM]').append(option[i]);
				}
			
			
		}
		
		

		
	}

function monthYearMethod(row){
	
	var Experience = row.closest('tr').find('select[id=MenableY]').val();
	
	
	var Monthtemp;
	var Experiencetemp;
	


if(Experience==expY){
		
		Monthtemp=expM;
	
		row.closest('tr').find('select[id=MenableM]').html("<option value='0'>Month</option>");
			for (i = 0; i <= Monthtemp; i++) {

				option[i] = '<option value='+i+'>'
						+ i
						+ '</option>';

				row.closest('tr').find('select[id=MenableM]').append(option[i]);
			}
		
	
	}
	if(Experience!=expY){
	
		Monthtemp=11;

		row.closest('tr').find('select[id=MenableM]').html("<option value='0'>Month</option>");
			for (i = 0; i <= Monthtemp; i++) {

				option[i] = '<option value='+i+'>'
						+ i
						+ '</option>';

				row.closest('tr').find('select[id=MenableM]').append(option[i]);
			}
		
		
	}
	
	

	
}


function monthYearTool(row){
	
	var Experience = row.closest('tr').find('select[id=TenableY]').val();
	
	
	var Monthtemp;
	var Experiencetemp;
	


if(Experience==expY){
		
		Monthtemp=expM;
	
		row.closest('tr').find('select[id=TenableM]').html("<option value='0'>Month</option>");
			for (i = 0; i <= Monthtemp; i++) {

				option[i] = '<option value='+i+'>'
						+ i
						+ '</option>';

				row.closest('tr').find('select[id=TenableM]').append(option[i]);
			}
		
	
	}
	if(Experience!=expY){
	
		Monthtemp=11;

		row.closest('tr').find('select[id=TenableM]').html("<option value='0'>Month</option>");
			for (i = 0; i <= Monthtemp; i++) {

				option[i] = '<option value='+i+'>'
						+ i
						+ '</option>';

				row.closest('tr').find('select[id=TenableM]').append(option[i]);
			}
		
		
	}
	
	

	
}


