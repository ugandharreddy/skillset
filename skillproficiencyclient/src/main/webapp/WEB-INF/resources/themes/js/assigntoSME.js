


var Base64={_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",encode:function(e){var t="";var n,r,i,s,o,u,a;var f=0;e=Base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},decode:function(e){var t="";var n,r,i;var s,o,u,a;var f=0;e=e.replace(/[^A-Za-z0-9+/=]/g,"");while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=Base64._utf8_decode(t);return t},_utf8_encode:function(e){e=e.replace(/rn/g,"n");var t="";for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},_utf8_decode:function(e){var t="";var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}}return t}}

	 var path1="";
	var empid="";
	var domain= "";
	var Stream="";
	 var skill="";
	 var plComment="";
	//$('#AssgnToSme').click(function(){
function openSME(e,deleteRow,pId){
	

		 path1=document.getElementById("contextPaht").value;
		 empid=e;
		
			   /* if(document.getElementById("Stream") != null){*/
  	     Stream=document.getElementById("Stream"+empid).innerHTML;
			    
  	   skill=document.getElementById("Skill"+empid).innerHTML;
  	 plComment=document.getElementById("techCommentForPL"+deleteRow).value;
  	 
	//	 var encodedString = Base64.encode(Stream);
//////
		  $.ajax({
		        type: "GET",
		        //headers: { Accept : "text/plain; charset=utf-8","Content-Type": "text/plain; charset=utf-8"},
		         url:path1+"/SMEList/Technology/"+pId,
		        // url:path1+"/SMEList/Technology/"+pId+"/"+empid,
		           contentType: "application/json",

		        success: function(message)
		        {   
		      	if(message.length>0)
         		 {
		      		temp(message,empid,pId,"Technology",deleteRow,plComment);

         		 }
         	 else{
 			  
 			$("#messageModelLabel" ).text( "No SME found, please review yourself");

      		 $('#messageModel').show();

         	 }

		      	
		      	},
		        error:function(xhr){
		      	  alert("No data found");   
		        }
		    });
		 
		 
		 
		 
		 //////
		/* $.ajax({
	          type: "POST",
	          headers: { Accept : "text/plain; charset=utf-8","Content-Type": "text/plain; charset=utf-8"},
	         url:path1+"/SMEList/Technology/"+pId+"/"+empid,
	          success: function(message)
	          {  
	        	 // temp(message,empid,skill,"Technology");
	        	  if(message.length>0)
	          		 {
	        		  temp(message,empid,skill,"Technology",deleteRow,plComment);

	          		 }
	          	 else{
	  			  
	  			$("#messageModelLabel" ).text( "No SME found, please review yourself");

	       		 $('#messageModel').show();

	          	 }
	        	  
	        	},
	          error:function(xhr, status, error){
	        	  alert("Data not found");   
	          }
	      });*/
	
}
function openSMEDomain(e,deleteRow,pId){
	
	 var path1="";
		var empid="";
		var domain= "";
		var Stream="";
		 var skill="";
		 var plComment="";
	 path1=document.getElementById("contextPaht").value;
	 empid=e;
  	 plComment=document.getElementById("domainCommentForPL"+deleteRow).value;

/*	 domain=document.getElementById("domain"+empid).innerHTML;
	   skill=document.getElementById("subDomain"+empid).innerHTML;
	   skill= skill.replace(/&amp;/g,'&');
    var Base64Validator={_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",encode:function(e){var t="";var n,r,i,s,o,u,a;var f=0;e=Base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},decode:function(e){var t="";var n,r,i;var s,o,u,a;var f=0;e=e.replace(/[^A-Za-z0-9+/=]/g,"");while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=Base64._utf8_decode(t);return t},_utf8_encode:function(e){e=e.replace(/rn/g,"n");var t="";for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},_utf8_decode:function(e){var t="";var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}}return t}}
	var encodedDomainString = Base64.encode(skill);*/
	 $.ajax({
         type: "GET",
        url:path1+"/SMEList/Domain/"+pId,
        contentType: "application/json",

         success: function(message)
         {  
          	 if(message.length>0)
          		 {
            	 temp1(message,empid,pId,"Domain",deleteRow,plComment);
          		 }
          	 else{
          		//alert("No SME found, please review yourself"); 
  			  
  			$("#messageModelLabel" ).text( "No SME found, please review yourself");

       		 $('#messageModel').show();

          	 }
       	},
   	 error: function(xhr, status, error) {
       	  alert("Data not found");   
         }
     });
	



}

function temp(messageList,empid,Stream,type,deleteRow,plComment)
{
	$("#smeAppend").empty();
		$('#successMsg').hide();  
		$('#failureMsg').hide();      
	  $.each(messageList, function(i, item) {

		  if(empid !=item){
		  
			  $("#smeAppend").append("<tr id="+item+"><td>"+item+"</td><td><button class='btn btn-danger' id='AddButtonSME"+item+"'>Assign</button></td></tr>");
		 
		  
		   document.getElementById("AddButtonSME"+item).addEventListener("click", function(){
			   
  	   var smeId=item;
 	 $.ajax({
         type: "POST",
        url:path1+"/AssignTaskToSME/"+empid+"/"+smeId+"/"+Stream+"/"+plComment,
      /*  dataType:"json",*/
         success: function(message)
         {  
        	 if(message){
        		 
     			$('#successMsg').show();      

     			//$('#successMsg').text("Request sent successfully to SME");
     			alert("Request sent successfully to SME");
             	

     			/*$('#successMsg').text("Request sent successfully to SME");*/
             	
              	$('#tech'+deleteRow).remove();

        		// code to be written in success method from here 
        		var techCount = $('#tech tr').length;
        		var domainCount = $('#domainBody tr').length;

        		if(techCount ==0 && domainCount==0)
        			{
        			$('#techTab').hide();
        			$('#domainTab').hide();
        			$('#home').hide();
        			$('#messages').hide();

        			/*$('#completed').show();      */
        			}
        		
        		if(techCount==0)
        		{
        			$('#techTab').hide();
        			$('#home').hide();
        		}
        		if(domainCount==0)
        		{
        			$('#domainTab').hide();  
        			$('#messages').hide();
        		}
        		location.href=path1+"/home";
        	 }
        	 else
        		 {
     			$('#failureMsg').show();      
     			$('#failureMsg').text("Failed to sent request to SME");
        		 }
        	 
       	},

        /* error:function(){
       	  alert("please try again");   

         }
      */
       		error: function(xhr, status, error) {
       		       	  alert("Data not found");   
       		         } 

     });
 	 
  	});
		   
		  }   
		   
		   
	  $('#myModal').show();
	  });
	
}


function temp1(messageList,empid,Stream,type,deleteRow,plComment)
{
	$("#smeAppend1").empty();
		$('#successMsg').hide();  
		$('#failureMsg').hide();      
	  $.each(messageList, function(i, item) {
		  
		  if(empid !=item){
			  $("#smeAppend1").append("<tr id="+item+"><td>"+item+"</td><td><button class='btn btn-danger' id='AddButtonSME1"+item+"'>Assign</button></td></tr>");
		  
			   document.getElementById("AddButtonSME1"+item).addEventListener("click", function(){
  	   var smeId=item;
		 path1=document.getElementById("contextPaht").value;

 	 $.ajax({
         type: "POST",
        url:path1+"/AssignTaskToSME/"+empid+"/"+smeId+"/"+Stream+"/"+plComment,
       /* dataType:"json",*/
         success: function(message)
         {  
        	 if(message){
     			$('#successMsg').show();      
     			$('#successMsg').text("Request sent successfully to SME");
             	
        	 	$('#domain'+deleteRow).remove();

        		// code to be written in success method from here 
        		var techCount = $('#tech tr').length;
        		var domainCount = $('#domainBody tr').length;

        		if(techCount ==0 && domainCount==0)
        			{
        			$('#techTab').hide();
        			$('#domainTab').hide();
        			$('#home').hide();
        			$('#messages').hide();
/*
        			$('#completed').show(); */     
        			}
        		
        		if(techCount==0)
        		{
        			$('#techTab').hide();
        			$('#home').hide();
        		}
        		if(domainCount==0)
        		{
        			$('#domainTab').hide();  
        			$('#messages').hide();
        		}
        		location.href=path1+"/home";
        	 }
        	 else
        		 {
     			$('#failureMsg').show();      
     			$('#failureMsg').text("Failed to sent request to SME");
        		 }
        	 
       	},
         error:function(){
       	  alert("please try again");   
         }
     });
 	
 	
 	
 	

  	});
		  }
	  $('#myModal1').show();
	  
	  });
	
}

	
$('.closed').click(function(){
		
		$('#myModal').hide();
		$('#myModal1').hide();
		$('#messageModel').hide();
		
		});
$("#mgrRating").change(function() {
	var mgrRating=document.getElementById("mgrRating").value;
	 if((mgrRating=="0")){

	
		$("#error2").addClass("disabled");
		
	}
	else{$("#error2").removeClass("disabled");
	} 
});
		/* $("#techRate").change(function() {
			var techRate=document.getElementById("techRate").value;
			 if((techRate=="0")){

			
				$("#error3").addClass("disabled");
				
			}
			else{$("#error3").removeClass("disabled");
			} 
		}); */
		
function plDisabledTech(row){
			
	var Experience = row.closest('tr').find('select[id=techRate]').val();
	 
	 if((Experience=="0")){


		 row.closest('tr').find('button[id=error3]').addClass("disabled");
		 
		 row.closest('tr').find('button[value=dd]').removeClass("disabled");
	}
	else{
		 row.closest('tr').find('button[id=error3]').removeClass("disabled");
		
		 row.closest('tr').find('button[value=dd]').addClass("disabled");
	}  
}	
		
function plDisabledDomain(row){
	
	var Experience = row.closest('tr').find('select[id=domainerror]').val();
 
 if((Experience=="0")){


	 row.closest('tr').find('button[id=error2]').addClass("disabled");
	 row.closest('tr').find('button[value=dd]').removeClass("disabled");
}
else{
	 row.closest('tr').find('button[id=error2]').removeClass("disabled");
	 row.closest('tr').find('button[value=dd]').addClass("disabled");
}  
}	

		
			

	