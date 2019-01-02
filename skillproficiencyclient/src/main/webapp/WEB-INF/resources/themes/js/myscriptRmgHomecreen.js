
var isFirstTime=false;

$(window).load(function() {
	$(".panel_custom").hide();
	$('#nodataError').hide();
$('#TechExp').hide();
$('#DomainExp').hide();
$('#TechProfi').hide();
$('#DomainProfi').hide();


function hideOptions()
{
	$("#showEmpLight ").hide();
	$("#freeSearch").val("");
	 $("#rmgskill").val("");
		$("#rmgpname").val("");
		$("#rmgTOOL").val("");
		$("#rmgDomain").val("");
		$("#rmgMethodology").val("");
		
		$('#TechExp').hide();
		$('#DomainExp').hide();
		$('#TechProfi').hide();
		$('#DomainProfi').hide();	

}


$('#isprimaryRMG').change(function() {

var sval=$('#isprimaryRMG').val();

$('#TechExp').hide();
$('#TechProfi').hide();
$('#DomainExp').hide();
$('#DomainProfi').hide();
$("#rmgskill").val("");
$("#rmgDomain").val("");

if(sval=="N"){
	$('#rmg_hide').addClass('hidden');
}
else{
	$('#rmg_hide').removeClass('hidden');
	$("#rmgskill").val("");
	$("#rmgDomain").val("");
	$('#rmgskill').attr('disabled',false);
	$('#rmgDomain').attr('disabled',false);

	if(sval=="D")
		{
		$('#rmgskill').attr('disabled',true);
		
		}
	else
		{
		$('#rmgDomain').attr('disabled',true);
		
		}
	
	
	
}
	});
$('#rmgskill').mouseleave(function() {
	
	var sval=$('#isprimaryRMG').val();

	var rmgskill=$("#rmgskill").val();

	if(sval=="T")
	{
		$('#rmgskill').attr('disabled',false);
		$('#rmgDomain').attr('disabled',true);

		if(rmgskill!="")
		{
			$('#rmgDomain').attr('disabled',false);
		}
		
	}
//	else
//	{
//		$('#rmgskill').attr('disabled',true);
//		$("#rmgskill").val("");
//
//		if(rmgskill=="")
//		{
//			$('#rmgDomain').attr('disabled',true);
//		}
//		if(rmgskill!="")
//		{
//			$('#rmgDomain').attr('disabled',false);
//		}
//		
//		
//	}
	
	



	

		});

$('#rmgDomain').mouseleave(function() {
	
	var rmgDomain=$("#rmgDomain").val();
	var sval=$('#isprimaryRMG').val();


	if(sval=="D")
	{
		$('#rmgDomain').attr('disabled',false);
		$('#rmgskill').attr('disabled',true);

		if(rmgDomain!="")
		{
		$('#rmgskill').attr('disabled',false);
		}
		
	}
//	else
//	{
//		$('#rmgDomain').attr('disabled',true);
//		$("#rmgDomain").val("");
//
//		if(rmgDomain=="")
//		{
//		$('#rmgskill').attr('disabled',true);
//		}
//		if(rmgDomain!="")
//		{
//		$('#rmgskill').attr('disabled',false);
//		}
//		
//		
//	}
	
	


});


	
	$('#skillEmp_wrapper').find('div').first().remove(); 
	
	$('#exportToXlchanged').on('click', function () {

		var tab_text = '<html xmlns:x="urn:schemas-microsoft-com:office:excel">';
	    tab_text = tab_text + '<head><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet>';

	    tab_text = tab_text + '<x:Name>Test Sheet</x:Name>';

	    tab_text = tab_text + '<x:WorksheetOptions><x:Panes></x:Panes></x:WorksheetOptions></x:ExcelWorksheet>';
	    tab_text = tab_text + '</x:ExcelWorksheets></x:ExcelWorkbook></xml></head><body>';

	    tab_text = tab_text + "<table border='1px'>";
	    tab_text = tab_text + $('#allEmp').html();
	    tab_text = tab_text + '</table></body></html>';

	    var data_type = 'data:application/vnd.ms-excel';
	    
	    var ua = window.navigator.userAgent;
	    var msie = ua.indexOf("MSIE ");

	    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)) {
	        if (window.navigator.msSaveBlob) {
	            var blob = new Blob([tab_text], {
	                type: "application/csv;charset=utf-8;"
	            });

	            navigator.msSaveBlob(blob, 'Test file.xls');
	        }
	    } else {
	        $('#exportToXl').attr('href', data_type + ', ' + encodeURIComponent(tab_text));
	        $('#exportToXl').attr('download', 'All Employees Report.xls');
	    }
		
	});
});


// below JS code call when page is loaded.
$(document).ready(function() {
	
	
	
	
	var isFirstTime=true;
	
$('#rmgskill').editableSelect({ effects: 'default' });
	$('#rmgpname').editableSelect({ effects: 'fade' });

	$('#rmgTOOL').editableSelect({ effects: 'fade' });
	$('#rmgMethodology').editableSelect({ effects: 'fade' });
	$('#rmgDomain').editableSelect({ effects: 'fade' });
	$("#showEmpLight").hide();
	// export all emp js
	
	$("#exportToXl").click(function(){
		window.open('data:application/vnd.ms-excel,'+$("#allEmp_wrapper").html());
	});
	
	//RMG validation
	$('#rmgskill,#rmgpname,#rmgTOOL,#rmgMethodology,#rmgDomain,#rmgAcc').on('mouseleave', function () {
	    //   $('select[name="field1"]').valid();
//	$('#rmgskill,#rmgpname,#rmgDomain').on('mouseleave', function () {

	var rmgskill=$("#rmgskill").val();
	var rmgpname=$("#rmgpname").val();
	var rmgTOOL=$("#rmgTOOL").val();
	var rmgMethodology=$("#rmgMethodology").val();
	var rmgDomain=$("#rmgDomain").val();
	var rmgAcc=$("#rmgAcc").val();


	 if((rmgskill!="")||(rmgpname!="")||(rmgTOOL!="")||(rmgMethodology!="")||(rmgDomain!="")||(rmgAcc!="")){

		$("#stbn").removeClass("disabled");
		$("#error").addClass("hidden");
		
	}
	else{
			$("#stbn").addClass("disabled");

			$("#error").removeClass("hidden");
	} 


	 
	});

// RMG home screen JS
//	allEmp_filter
//	dataTables_filter 

 $('#viewAllEmp').on('click', function () {
	 $(".dataTables_info").parent().parent().addClass("pagination_styles");
	 $(".dataTables_paginate").addClass("pagination_numbers");
	 $("#rmgskill").val("");
	$("#rmgpname").val("");
	$("#rmgTOOL").val("");
	$("#rmgDomain").val("");
	$("#rmgMethodology").val("");

	
	$(".panel_custom").show();
	
$("#allEmp").show();
$("#allEmp_paginate,#allEmp_info").show();
$("#skillEmp_paginate,#skillEmp_info").hide();
$("#skillEmp").hide();
$('#nodataError').hide();

}); 
	

$('#stbn').on('click', function () {
	
	var isprimary=document.getElementById('isprimaryRMG').value;
	var skillName=document.getElementById('rmgskill').value;
	var projectName=document.getElementById('rmgpname').value;
	var toolName=document.getElementById('rmgTOOL').value;
	var methodologyName=document.getElementById('rmgMethodology').value;
	var domainName=document.getElementById('rmgDomain').value;
	
	var TechExp=document.getElementById('TechExp').value;
	var DomainExp=document.getElementById('DomainExp').value;
	var TechProfi=document.getElementById('TechProfi').value;
	var DomainProfi=document.getElementById('DomainProfi').value;
	var acc=document.getElementById('rmgAcc').value;


	var techPrimary="NULL"
		var domainPrimary="NULL";	
		
	if(acc=="")
		{
		 acc="NULL";
		}
	
if(skillName==""){
	skillName='NULL';
}
if(projectName==""){
	projectName="NULL";
} 
if(toolName==""){
	toolName="NULL";
} 
if(methodologyName==""){
	methodologyName="NULL";
} 
if(domainName==""){
	domainName="NULL";
} 
if(isprimary=="T"){
	techPrimary="Y";
} 

if(isprimary=="D"){
	domainPrimary="Y";
} 


if(isprimary=="N"){
	 techPrimary="NULL"
	 domainPrimary="NULL";
	} 

$("#isprimaryRMG").val("N");
$('#rmg_hide').addClass('hidden');


	var path1=document.getElementById("contextPaht").value;
//	alert(path1+"/searchEmployeeWithSkill/"+skillName+"/"+projectName+"/"+toolName+"/"+methodologyName+"/"+domainName+"/"+techPrimary+"/"+domainPrimary+"/"+TechExp+"/"+DomainExp+"/"+TechProfi+"/"+DomainProfi);  
	$.ajax({
          type: "POST",
          url:path1+"/searchEmployeeWithSkill/"+skillName+"/"+projectName+"/"+toolName+"/"+methodologyName+"/"+domainName+"/"+techPrimary+"/"+domainPrimary+"/"+TechExp+"/"+DomainExp+"/"+TechProfi+"/"+DomainProfi+"/"+acc,
          success: function(message)
          { 
        	  $('#skillEmpbody').empty();
        	  $('#skillEmpbodySearch').empty();
        	if(message.length==0)
        		{
$('.errorRMG').html("No Data found");
$(".panel_custom").hide();
$("#showEmpLight1").show();
$('#TechExp').hide();
$('#DomainExp').hide();
$('#TechProfi').hide();
$('#DomainProfi').hide();
        		 /* $(".panel_custom").hide();
        		$('#nodataError').show();*/
var path1=document.getElementById("contextPaht").value;
location.href= path1+"/home";
        		}
        	else
        		{
        		
        		$('.errorRMG').html("Search Response" /*+skillName+" "+domainName+" "+projectName+" "+toolName+" "+methodologyName+" "+techPrimary+" "+domainPrimary+" "+TechExp+" "+DomainExp+" "+TechProfi+" "+DomainProfi+" "+acc*/ );
        		$('#nodataError').hide();
        		var div = document.getElementById('createConmiExport');

        		div.innerHTML = '<button class="btn btn-danger pull-right" type="button" id="createConmiExport">Export to Excel</button>';
					
           	 $.each(message, function(i, item) {
           		/* alert("item size = "+item.length());*/
               	 $('#skillEmp th').removeClass("sorting_asc sorting");
               	$("#skillEmp th").removeAttr("onclick");
               	
             	var methodology ="NA";
               	var domain ="NA";
               	var tool ="NA";
               	var technology ="NA";
               	var accerd ="NA";

               	if(item.methodology)
               	{
               	methodology =item.methodology;
               	}
               	if(item.domain)
               	{
               		domain =item.domain;
               		if(item.dExpInyear>0){
               		domain += ' exp:';
               		domain += ' ' + item.dExpInyear;
               		domain += ' Yr(s) ';
            		if(item.dExpInmonth>0){
            			domain += '&';

               		domain += ' ' + item.dExpInmonth;
               		domain += ' Mn(s)';
            		}
               		}
               		else if(item.dExpInmonth>0){
               			domain += ' exp:';
               		domain += ' ' + item.dExpInmonth;
               		domain += ' Mn(s)';
            		}
               		if(item.domainProf>0)
               			{
               		domain += ' Proficiency:';
               		domain += ' ' + item.domainProf;
               			}
            		
//               		domain.concat(", exp:",item.dExpInyear," Years",item.dExpInmonth," Months");
//               		domain.concat(", Proficiency:",item.dProf);
               	}
               	if(item.tool)
               	{
               		tool =item.tool;
               	}
               	
            	if(item.techDesc)
               	{
            		technology =item.techDesc;
//            		technology.concat(", exp:",item.tExpInyear," Years",item.tExpInmonth," Months");
//            		technology.concat(", Proficiency:",item.tProf);
               		if(item.tExpInyear>0){

            		technology += ' exp:';
            		technology += ' ' + item.tExpInyear;
            		technology += ' Yr(s) ';
            		
            		if(item.tExpInmonth>0){
                		technology += '&';

            		technology += ' ' + item.tExpInmonth;
            		technology += ' Mn(s)';
            		}
               		}
               		else if(item.tExpInmonth>0){
               			technology += ' exp:';
            		technology += ' ' + item.tExpInmonth;
            		technology += ' Mn(s)';
            		}
               		if(item.techProf>0){

            		technology += ' Proficiency:';
            		technology += ' ' + item.techProf;
               		}
               	}
            	
            	if(item.accreditation)
               	{
            		accerd =item.accreditation;
               	}
            	
            	var name="NA";
            	var aow="NA";
            	var project="NA";
            	
            	if(item.empMailId)
               	{
            		name =item.empMailId;
               	}
            	if(item.areaOfWork)
               	{
            		aow =item.areaOfWork;
               	}
            	if(item.projectName)
               	{
            		project =item.projectName;
               	}
            	
            	/*$("#skillEmpbody").append("<tr><td>"+item.name+"</td><td >"+item.area+"</td><td >"+item.projectName+"</td><td >"+technology+"</td><td >"+domain+"</td><td >"+methodology+"</td><td >"+tool+"</td><td >"+item.experience+"</td><td><button class='btn btn-default btn-danger' onclick='myFunction("+item.empId+","+item.name+","+item.name+")' >View Details</button</td></td></tr>");*/
            	$("#skillEmpbodySearch").append("<tr><td>"+name+"</td><td >"+aow+"</td><td >"+project+"</td><td >"+technology+"</td><td >"+domain+"</td><td >"+methodology+"</td><td >"+tool+"</td><td>"+accerd+"</td><td><button class='btn btn-default btn-danger' id='mybtn"+i+"' >View Details</button</td></td></tr>");
            	
            	
            	document.getElementById("createConmiExport").addEventListener("click", function(){
            		//"+item.empId+","+item.name+","+item.name+"
            	 
           		var path1=document.getElementById("contextPaht").value;

					location.href= path1+"/ExportSearchEmployeeWithSkill/"+skillName+"/"+projectName+"/"+toolName+"/"+methodologyName+"/"+domainName+"/"+techPrimary+"/"+domainPrimary+"/"+TechExp+"/"+DomainExp+"/"+TechProfi+"/"+DomainProfi+"/"+acc;

            	   
            	});

            	
            	document.getElementById("mybtn"+i).addEventListener("click", function(){
            		//"+item.empId+","+item.name+","+item.name+"
            	   var empId=item.empId;
            	   var name=item.name;
            	   var empMailId=item.empMailId;
           		var path1=document.getElementById("contextPaht").value;

					location.href=path1+"/employeeDetailsById/0/"+empMailId+"/"+empMailId;

            	   
            	});
            	
            
            	
            	
            	
            	//$("#skillEmpbody").append("<tr><td>"+item.name+"</td><td >"+item.area+"</td><td >"+item.projectName+"</td><td >"+item.primarySkill+"</td><td ></td><td ></td><td ></td><td >"+item.experience+"</td><td><button class='btn btn-default btn-danger' onclick='location.href='<%=request.getContextPath()%>/employeeDetailsById/${viewAllEmp.empID}/${viewAllEmp.empName}/${viewAllEmp.emailID}';>View Details</button</td></tr");
               	$(".panel_custom").show();
                 $("#skillEmp").show();
         	$("#allEmp_paginate,#allEmp_info").hide();
             	$("#skillEmp_paginate,#skillEmp_info,.dataTables_empty").hide();
             	$("#allEmp").hide(); 
             	$("#showEmpLight1").show();
           	 });
           	 
           
           	 
        		}
        	
        	//skillName
        	},
        	 error: function(xhr, status, error) {
        		   // alert(xhr.responseText);
        		 alert("data not found");
        			
        		 $("#showEmpLight ").hide();
        			$("#freeSearch").val("");
        			 $("#rmgskill").val("");
        				$("#rmgpname").val("");
        				$("#rmgTOOL").val("");
        				$("#rmgDomain").val("");
        				$("#rmgMethodology").val("");
        				
        				$('#TechExp').hide();
        				$('#DomainExp').hide();
        				$('#TechProfi').hide();
        				$('#DomainProfi').hide();	
        				
        				var path1=document.getElementById("contextPaht").value;
            			location.href= path1+"/home";
        	 }
      });
	
});
 
// Rmg AUTO Search skill
function myFunction(a){
	
	alert("asdf");
}



$('#rmgskill').on('keydown', function (temp) {
	
	var val=document.getElementById('rmgskill').value;

	var path1=document.getElementById("contextPaht").value;
	if(val.trim()=="")
		{
		$('.es-list:first').empty();
		$('#TechExp').hide();

		$('#TechProfi').hide();
		 $('#TechExp').prop('selectedIndex',0);
		 $('#TechProfi').prop('selectedIndex',0);
		
		}
	else{
		

	  $.ajax({
          type: "POST",
          
         url:path1+"/getSkillName/"+val,
    			
          success: function(message)
          {    
        		$('#TechExp').show();

        		$('#TechProfi').show();
        	  var select, i, option,jspath;
        	
			$('.es-list:first').empty();
        	 $.each(message, function(i, item) {
               	 
	  		   	$('.es-list:first').append("<li>"+item+"</li>");
	  		  $('.es-list:first').show();
	  		 
	  		  
	  		
           	 }); 
        	 $('.es-list:first li').hover(function(pa){
        		 var va=$(this).html();
        		 $('#rmgskill').val(va);
        		 $(this).addClass('selected');
	  			
	  		  });
        	 $('.es-list:first li').mouseleave(function(pa){
        		
        		 $(this).removeClass('selected');
	  			
	  		  });
        	
        	},
          error:function(){
        	  alert("No data found");
      		
          }
      });
	
	}
});

// RMG AUTO Search Project name
$('#rmgpname').on('keyup', function (temp) {
	var val=document.getElementById('rmgpname').value;

	var path1=document.getElementById("contextPaht").value;
	if(val.trim()=="")
		{
		$('.es-list').eq(1).empty();	
		
		}
	else{
	  $.ajax({
          type: "POST",
          
         url:path1+"/getProjectame/"+val,
    			
          success: function(message)
          {    
        	  var select, i, option,jspath;
        	
			$('.es-list').eq(1).empty();
			
        	 $.each(message, function(i, item) {
	  		   	$('.es-list').eq(1).append("<li>"+item+"</li>");
	  		 // $("your classs").eq(1);
	  		  $('.es-list').eq(1).show();
	  		
           	 }); 
        	 $('.es-list li').hover(function(pa){
        		 var va=$(this).html();
        		 $('#rmgpname').val(va);
        		 $(this).addClass('selected');
	  			
	  		  });
        	 $('.es-list li').mouseleave(function(pa){
        		
        		 $(this).removeClass('selected');
	  			
	  		  });
        	
        	},
          error:function(){
        	  alert("No data found");   
          }
      });
	
	}
});

//RMG AUTO Search rmgTool name
$('#rmgTOOL').on('keyup', function (temp) {
	var val=document.getElementById('rmgTOOL').value;

	var path1=document.getElementById("contextPaht").value;
	if(val.trim()=="")
		{
		$('.es-list').eq(2).empty();	
		
		}
	else{
		

	  $.ajax({
          type: "POST",
          
         url:path1+"/getRMGtools/"+val,
    			
          success: function(message)
          {   
        	  var select, i, option,jspath;
        	
			$('.es-list').eq(2).empty();
			
        	 $.each(message, function(i, item) {
               	 
	  		   	$('.es-list').eq(2).append("<li>"+item+"</li>");
	  		 // $("your classs").eq(1);
	  		  $('.es-list').eq(2).show();
	  		
           	 }); 
        	 $('.es-list li').hover(function(pa){
        		 var va=$(this).html();
        		 $('#rmgTOOL').val(va);
        		 $(this).addClass('selected');
	  			
	  		  });
        	 $('.es-list li').mouseleave(function(pa){
        		
        		 $(this).removeClass('selected');
	  			
	  		  });
        	
        	},
          error:function(){
        	  alert("No data found");   
          }
      });
	
	}
});


//RMG AUTO Search rmgMethodology name
$('#rmgMethodology').on('keyup', function (temp) {
	var val=document.getElementById('rmgMethodology').value;

	var path1=document.getElementById("contextPaht").value;
	if(val.trim()=="")
		{
		$('.es-list').eq(3).empty();	
		
		}
	else{
		

	  $.ajax({
          type: "POST",
          
         url:path1+"/getRMGMethodologies/"+val,
    			
          success: function(message)
          {   
        	  var select, i, option,jspath;
        	
			$('.es-list').eq(3).empty();
			
        	 $.each(message, function(i, item) {
               	 
	  		   	$('.es-list').eq(3).append("<li>"+item+"</li>");
	  		 // $("your classs").eq(1);
	  		  $('.es-list').eq(3).show();
	  		
           	 }); 
        	 $('.es-list li').hover(function(pa){
        		 var va=$(this).html();
        		 $('#rmgMethodology').val(va);
        		 $(this).addClass('selected');
	  			
	  		  });
        	 $('.es-list li').mouseleave(function(pa){
        		
        		 $(this).removeClass('selected');
	  			
	  		  });
        	
        	},
          error:function(){
  			$('.es-list').eq(3).empty();

        	  alert("No data found");   
          }
      });
	
	}
});

//RMG AUTO Search rmgDomain name
$('#rmgDomain').on('keyup', function (temp) {
	
	$('#DomainExp').show();

	$('#DomainProfi').show();
	var val=document.getElementById('rmgDomain').value;

	var path1=document.getElementById("contextPaht").value;
	if(val.trim()=="")
		{
		$('.es-list').eq(4).empty();	

		$('#DomainExp').hide();

		$('#DomainProfi').hide();
		 $('#DomainExp').prop('selectedIndex',0);
		 $('#DomainProfi').prop('selectedIndex',0);
		}
	else{
		

	  $.ajax({
        type: "POST",
        
       url:path1+"/getRMGDomains/"+val,
  			
        success: function(message)
        {   
      	  var select, i, option,jspath;
      	
			$('.es-list').eq(4).empty();
			
      	 $.each(message, function(i, item) {
             	 
	  		   	$('.es-list').eq(4).append("<li>"+item+"</li>");
	  		 // $("your classs").eq(1);
	  		  $('.es-list').eq(4).show();
	  		
         	 }); 
      	 $('.es-list li').hover(function(pa){
      		 var va=$(this).html();
      		 $('#rmgDomain').val(va);
      		 $(this).addClass('selected');
	  			
	  		  });
      	 $('.es-list li').mouseleave(function(pa){
      		
      		 $(this).removeClass('selected');
	  			
	  		  });
      	
      	},
        error:function(){
      	  alert("No data found");   
        }
    });
	
	}
});
$('#freeSearch').on('keypress', function (temp) {
	
	if(temp.keyCode === 13){
		var freeSearch=document.getElementById('freeSearch').value;
	if (freeSearch.length>0) {
//Create Base64 Object
 /*var Base64={_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",encode:function(e){var t="";var n,r,i,s,o,u,a;var f=0;e=Base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},decode:function(e){var t="";var n,r,i;var s,o,u,a;var f=0;e=e.replace(/[^A-Za-z0-9+/=]/g,"");while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=Base64._utf8_decode(t);return t},_utf8_encode:function(e){e=e.replace(/rn/g,"n");var t="";for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},_utf8_decode:function(e){var t="";var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}}return t}}
 var encodedString = Base64.encode(freeSearch);
 */
			var path1=document.getElementById("contextPaht").value;
		
			  $.ajax({
		          type: "POST",
		          url:path1+"/searchByKeyword/"+freeSearch,
		          success: function(message)
		          { 
		        	 
		        	  $('#skillEmpbody').empty();
		        	if(message.length==0)
		        		{
		$('.errorRMG').html("No Data found");
		$(".panel_custom").hide();
		$("#showEmpLight").show();
		$("#freeSearch").val("");
		        		 /* $(".panel_custom").hide();
		        		$('#nodataError').show();*/
		        		}
		        	else
		        		{
		        		
		        		var div = document.getElementById('createExport');

		        		div.innerHTML = '<button class="btn btn-danger pull-right" type="button" id="exportKeywordSearch" onclick="exportKeywordSearch('+freeSearch+')">Export to Excel</button>';
		        		
		        		
		        		/*var rowCount = $('#skillEmpbody').size();*/
		        		/*.text(length);*/
		        		var noRecords=message.length;
		        		var rowCount = $("#skillEmp > tbody").length;
		        		/*var rowCount = $("#skillEmp td").closest("tr").length;*/
		        		
		        		/*var rowCount = $("#skillEmp td").closest("tr").length;
		        		*/
		        	
							
		        		/*$('.errorRMG').html("Searched Result for "+freeSearch+ " " +rowCount);*/
		        		$('.errorRMG').html(noRecords+" records found for '"+freeSearch+"'");
		        		
		        		$('#nodataError').hide();

		           	 $.each(message, function(i, item) {

		               	 $('#skillEmp th').removeClass("sorting_asc sorting");
		               	
		               	$("#skillEmp th").removeAttr("onclick");
		               	var methodology ="NA";
		               	var domain ="NA";
		               	var tool ="NA";
		               	var technology ="NA";
		               	var accerd ="NA";
		               	
		               	
		               	if(item.methodology)
		               	{
		               	methodology =item.methodology;
		               	}
		               	if(item.domain)
		               	{
		               		domain =item.domain;
		               	}
		               	if(item.tool)
		               	{
		               		tool =item.tool;
		               	}
		            	if(item.skillDesc)
		               	{
		            		technology =item.skillDesc;
		               	}
		            	/*if(item.techDesc)
		               	{
		            		technology =item.techDesc;
		               	}*/
		            	if(item.accreditation)
		               	{
		            		accerd =item.accreditation;
		               	}
		            	var name="NA";
		            	var aow="NA";
		            	var project="NA";
		            	var exp="NA";

		            	if(item.empMailId)
		               	{
		            		name =item.empMailId;
		               	}
		            	if(item.areaOfWork)
		               	{
		            		aow =item.areaOfWork;
		               	}
		            	if(item.projectName)
		               	{
		            		project =item.projectName;
		               	}
		            	if(item.expInyear)
		               	{
		            		exp =item.expInyear;
		            		exp += ' Yr(s) ';
		               	}
		            	if(item.expInmonth>0)
		               	{
		            		exp += '& ';
		            		exp +=item.expInmonth;
		            		exp += ' Mn(s)';

		               	}
		            	
		                /* $("#skillEmpbody").append("<tr><td>"+item.name+"</td><td >"+item.primarySkill+"</td><td >"+item.secondarySkill+"</td><td >"+item.area+"</td><td >"+item.experience+"</td><td >"+item.projectName+"</td></tr");*/
		               // $("#skillEmpbody").append("<tr><td>"+item.name+"</td><td >"+item.area+"</td><td >"+item.projectName+"</td><td >"+technology+"</td><td >"+domain+"</td><td >"+methodology+"</td><td >"+tool+"</td><td >"+item.experience+"</td><td><button class='btn btn-default btn-danger' onclick='location.href='<%=request.getContextPath()%>/employeeDetailsById/${viewAllEmp.empID}/${viewAllEmp.empName}/${viewAllEmp.emailID}';>View Details</button</td></td></tr");
		                $("#skillEmpbody").append("<tr><td>"+name+"</td><td >"+aow+"</td><td >"+project+"</td><td >"+technology+"</td><td >"+domain+"</td><td >"+methodology+"</td><td >"+tool+"</td><td >"+exp+"</td><td>"+accerd+"</td><td><button class='btn btn-default btn-danger' id='mybtnFree"+i+"' >View Details</button</td></td></tr>");
		              
		                
		                document.getElementById("exportKeywordSearch").addEventListener("click", function(){
		            		//"+item.empId+","+item.name+","+item.name+"
		            		var freeSearch=document.getElementById('freeSearch').value;

							
		            	 //location.href=path1+"/ExportsearchByKeyword/"+freeSearch;
		            		
		            		 location.href=path1+"/exportKeywordSearch/"+freeSearch;//change by kunal

		            	   
		            	});
		                
		              
		            	
		            	document.getElementById("mybtnFree"+i).addEventListener("click", function(){
		            		//"+item.empId+","+item.name+","+item.name+"
		            	   var empId=item.empId;
		            	   var name=item.name;
		            	   var empMailId=item.empMailId;
		            		var path1=document.getElementById("contextPaht").value;
    	   
							location.href=path1+"/employeeDetailsById/0/"+empMailId+"/"+empMailId;

		            	   
		            	});
		            	
		            	
		            	$(".panel_custom").show();
		            	
		               	$(".panel-body").addClass('searchpopup');
		                 $("#skillEmp").show();
		                
		         	$("#allEmp_paginate,#allEmp_info").hide();
		             	$("#skillEmp_paginate,#skillEmp_info,.dataTables_empty").hide();
		             	$("#allEmp").hide(); 
		             	$("#showEmpLight").show();
		             	
		           	 });  
		           	 
		        		}
		        	
		        	//skillName
		        	},
		        	 error: function(xhr, status, error) {
		        		 /*   alert(xhr.responseText);*/
		        		 alert("No data found");
		        		 
		        		 $("#showEmpLight ").hide();
		        			$("#freeSearch").val("");
		        			 $("#rmgskill").val("");
		        				$("#rmgpname").val("");
		        				$("#rmgTOOL").val("");
		        				$("#rmgDomain").val("");
		        				$("#rmgMethodology").val("");
		        				
		        				$('#TechExp').hide();
		        				$('#DomainExp').hide();
		        				$('#TechProfi').hide();
		        				$('#DomainProfi').hide();	
		        				}
		      });
			  
			  
			  
     }else{
    	 
    	 
     }
	}
});

$('#showEmpLight >.modal-backdrop').click(function() {
	$("#showEmpLight ").hide();
	$("#freeSearch").val("");
	 $("#rmgskill").val("");
		$("#rmgpname").val("");
		$("#rmgTOOL").val("");
		$("#rmgDomain").val("");
		$("#rmgMethodology").val("");
		
		$('#TechExp').hide();
		$('#DomainExp').hide();
		$('#TechProfi').hide();
		$('#DomainProfi').hide();
		

	});
$('#showEmpLight1 >.modal-backdrop').click(function() {
	$("#showEmpLight1").hide();
	$("#freeSearch").val("");
	 $("#rmgskill").val("");
		$("#rmgpname").val("");
		$("#rmgTOOL").val("");
		$("#rmgDomain").val("");
		$("#rmgMethodology").val("");
		
		$('#TechExp').hide();
		$('#DomainExp').hide();
		$('#TechProfi').hide();
		$('#DomainProfi').hide();
	});
$('#rmgcls').click(function() {
	$("#freeSearch").val("");

	 $("#rmgskill").val("");
		$("#rmgpname").val("");
		$("#rmgTOOL").val("");
		$("#rmgDomain").val("");
		$("#rmgMethodology").val("");

	$("#showEmpLight ").hide();
	
	$('#TechExp').hide();
	$('#DomainExp').hide();
	$('#TechProfi').hide();
	$('#DomainProfi').hide();
	
	});
$('#rmgcls1').click(function() {
	$("#freeSearch").val("");

	 $("#rmgskill").val("");
		$("#rmgpname").val("");
		$("#rmgTOOL").val("");
		$("#rmgDomain").val("");
		$("#rmgMethodology").val("");

	$("#showEmpLight1").hide();
	
	$('#TechExp').hide();
	$('#DomainExp').hide();
	$('#TechProfi').hide();
	$('#DomainProfi').hide();
	var path1=document.getElementById("contextPaht").value;
	location.href= path1+"/home";
	});







//end of jquery
});


function download(){
	var path1=document.getElementById("contextPaht").value;
	console.log('Calling service');
	$.ajax({
		type : "GET",
		url : path1 + "/downloadResume",
		success : function(message) {
	      alert(message);
		},
		error : function(xhr) {
			alert("try again");
		}
	});

	}
