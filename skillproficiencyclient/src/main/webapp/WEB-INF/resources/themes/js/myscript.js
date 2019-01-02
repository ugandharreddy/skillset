
function tempPLPL(row){
	
	
	var Experience = row.closest('tr').find('select[id=PPLDisableddomain]').val();
	 
	 if((Experience=="0")){


		 row.closest('tr').find('button[id=error5]').addClass("disabled");
		 row.closest('tr').find('button[value=dd]').removeClass("disabled");
	}
	else{
		 row.closest('tr').find('button[id=error5]').removeClass("disabled");
		 row.closest('tr').find('button[value=dd]').addClass("disabled");
	} 
}

$(document).ready(function() {
	
	$("#dropdown_hover").hover(function(){

	$(".dropdown_custom").addClass("show");
		
	});
	
	$("#dropdown_hover").mouseleave(function(){

	$(".dropdown_custom").removeClass("show");
		
	});
	
	
	$('#hand').css("cursor","pointer");
	

  $('#Pareaofwork,#pexpM,#pstream,#pskill,#pexpY,#pself').on('mouseleave', function () {
    //   $('select[name="field1"]').valid();
	  var Pareaofwork=$("#Pareaofwork").val();
var pstream=$("#pstream").val();
var pskill=$("#pskill").val();
var pexpY=$("#pexpY").val();
var pexpM=$("#pexpM").val();
var pself=$("#pself").val();

 if((Pareaofwork!="0")&&(pstream!="0")&&(pskill!="0")&&(pexpY!="Year")&&(pexpM!="Month")&&(pself!="Select")){

	$("#Technologysave").removeClass("disabled");
	$("#error").addClass("hidden");
	
}
else{
		$("#Technologysave").addClass("disabled");

		$("#error").removeClass("hidden");
} 


 
});

  $('#Sareaofwork,#pexpM1,#pstream1,#pskill1,#pexpY1').on('mouseleave', function () {
    //   $('select[name="field1"]').valid();
	  var Sareaofwork=$("#Sareaofwork").val();
	  var pstream1=$("#pstream1").val();
var pskill1=$("#pskill1").val();
var pexpY1=$("#pexpY1").val();
var pexpM1=$("#pexpM1").val();

 if((Sareaofwork!="0")&&(pstream1!="0")&&(pskill1!="0")&&(pexpY1!="Year")&&(pexpM1!="Month")){

	$("#next2").removeClass("disabled");
	$("#error1").addClass("hidden");
	
}
else{
		$("#next2").addClass("disabled");

		$("#error1").removeClass("hidden");
} 


 
});


  $('#Mmethodology1,#MexpY1,#Mexp1,#Mrate1').on('mouseleave', function () {
    //   $('select[name="field1"]').valid();
var Mmethodology1=$("#Mmethodology1").val();
var MexpY1=$("#MexpY1").val();
var Mexp1=$("#Mexp1").val();
var Mrate1=$("#Mrate1").val();

/*alert(Mmethodology);
alert(MexpY);

alert(Mexp);

alert(Mrate);*/
 if((Mmethodology1!="0")&&(MexpY1!="Year")&&(Mexp1!="Month")&&(Mrate1!="Select")){

$("#Msave1").removeClass("disabled");
	$("#Merror").addClass("hidden");
	
}
else{

	$("#Msave1").addClass("disabled");

		$("#Merror").removeClass("hidden");
} 


 
});


  $('#Ddomain,#Dsub,#DexpY,#DexpM,#Drate').on('mouseleave', function () {
    //   $('select[name="field1"]').valid();
var DexpY=$("#DexpY").val();

var Dsub=$("#Dsub").val();

var Ddomain=$("#Ddomain").val();
var DexpM=$("#DexpM").val();
var Drate=$("#Drate").val();

 if((Ddomain!="Select")&&(DexpY!="Year")&&(DexpM!="Month")&&(Drate!="Select")&&(Dsub!="0")){

	$("#Dsave").removeClass("disabled");
	$("#Derror").addClass("hidden");
	
}
else{
		$("#Dsave").addClass("disabled");

		$("#Derror").removeClass("hidden");
} 


 
});

  $('#Ttool,#TexpY,#Texp,#Trate').on('mouseleave', function () {
    //   $('select[name="field1"]').valid();
var Ttool=$("#Ttool").val();
var TexpY=$("#TexpY").val();
var Texp=$("#Texp").val();
var Trate=$("#Trate").val();

 if((Ttool!="Select")&&(TexpY!="Year")&&(Texp!="Month")&&(Trate!="Select")){

	$("#Tsave").removeClass("disabled");
	$("#Terror").addClass("hidden");
	
}
else{
		$("#Tsave").addClass("disabled");

		$("#Terror").removeClass("hidden");
} 



 
});

 $('#Acertified,#Aname,#startDatePicker').on('change', function () {
    //   $('select[name="field1"]').valid();
var Acertified=$("#Acertified").val();
var Aname=$("#Aname").val();
var Aduration=$("#startDatePicker").val();


 if((Acertified!="")&&(Aname!="")&&(Aduration!="")){

	$("#Asave").removeClass("disabled");
	$("#Aerror").addClass("hidden");
	
}
else{
		$("#Asave").addClass("disabled");

		$("#Aerror").removeClass("hidden");
} 


 });
 
 

 
//end of jquery
});


function uploadFile(){
	/*alert("in upload");*/
	var fileName1=document.getElementById("fileName");
	var len=fileName1.files[0];
	

	var len2=len.size;


	  var fileName = fileName1.value;
	  /*alert(fileName);*/
      var ext = fileName.substring(fileName.lastIndexOf('.') + 1);
      ext=ext.toLowerCase();
      /*alert(ext);*/
	 if(ext=="docx"||ext=="doc")
	{
		if(len2<=3048576){
			
			
		 $("form").submit();
		alert("Uploaded Successfully")
		}
		else{
			alert("File size should not exceed 3MB.!");
		}
	  }else{
		 /* alert("inside else");*/
	 alert('Please Upload docx or doc files only.');
	 f['file'].focus();
	 }
	};









/*function uploadFile(){
	alert("in upload");
	var fileName1=document.getElementById("fileName");
	var len=fileName1.files[0];
	

	var len2=len.size;


	  var fileName = fileName1.value;
	  alert(fileName);
      var ext = fileName.substring(fileName.lastIndexOf('.') + 1);
      ext=ext.toLowerCase();
      alert(ext);
	 if(ext=="docx"||ext=="doc")
	{
		if(len2<=3048576){
			var formData = new FormData($('*uploadResume*')[0]);

			 var path1=document.getElementById("contextPaht").value;
			 $.ajax({
					type:"POST",
					url:path1+"/uploadFile",
					
					data: formdata,
		            mimeTypes:"multipart/form-data",
		            contentType: false,
		            cache: false,
		            processData: false,

					success:function(message){
						if(success)

							alert(success);
						else
							alert(failure);
					}
					 
				 });	
		
		}
		else{
			alert("File size should not exceed 3MB");
		}
	  }else{
		  alert("inside else");
	 alert('Please Upload docx or doc Files Only.');
	
	
	 
	 
	 f['file'].focus();
	 }
	};*/
