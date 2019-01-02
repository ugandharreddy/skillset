<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header2.jsp"%>
<script>
<%String employessList = (String) request.getAttribute("jsonObject");
System.out.println(employessList);
%>
</script>
<style>
.modal-backdrop {
  z-index: -1;
}
</style>
<div class="col-xs-12">
<%@include file="userdetail.jsp"%>
</div>

<c:if test="${userRole.contains('MANAGEMENT')}">
<div class="col-xs-12">
	<div class="panel panel-danger">
		<div class="panel-heading  text-center col-xs-12">
			<div class="col-xs-4 pull-left" style="text-align:right;"></div>  
			 <div class="col-xs-4 text-center"style="padding-left: 68px;">
			 		<input type="text" class="form-control" style="width: 250px;" id="freeSearch" placeholder="Search by skill or name of resource">
			 </div> 
			<div class="col-xs-4" style="text-align:center;">
				<div class="text-right" style="margin-left: 0px;">
					<button class="btn btn-danger" type="button" id="viewAllEmp"   data-toggle="modal" data-target="#myModal2">View All Attra Employee</button>
				</div>
			</div>  
		</div> 
	</div>
</div>
<div class="col-xs-12 text-center">
		<span class="star" id="error">* Please provide atleast one input</span> <br />
</div>
<div class="col-xs-12 text-center gab next">
	<div class="col-lg-4 col-xs-4  col-lg-offset-1">
		<span class="star pull-right"><b>Search By Primary *</b></span>
	</div>
	<div class="col-lg-2 col-xs-2">
		<select class="form-control" id="isprimaryRMG">
			<option value="N">Select</option>
			<option value="T">Skill</option>
			<option value="D">Sub Domain</option>
		</select>
	</div>
</div>
<div class="next gab">&nbsp;</div>
<div id="rmg_hide" class="hidden">
	<div class="col-xs-2 col-xs-offset-4">
		<select class="form-control" id="rmgskill" placeholder="Skill"></select>
	</div>
	<div class="col-xs-2 ">
		<select class="form-control" id="rmgDomain" placeholder="Sub Domain"></select>
	</div>
</div>


<div class="col-xs-2 col-xs-offset-4 gab next">
				<select id="TechExp" class="form-control">
					<option value="0-99">Experience</option>
					<option value="0-3">0-3</option>
					<option value="3-7">3-7</option>
					<option value="7-9">7-9</option>
					<option value="9-99">9-99</option>
				</select>

</div>
<div class="col-xs-2  gab ">
				<select id="DomainExp" class="form-control">
					<option value="0-99">Experience</option>
					<option value="0-3">0-3</option>
					<option value="3-7">3-7</option>
					<option value="7-9">7-9</option>
					<option value="9-99">9-99</option>
				</select>

</div>
<div class="col-xs-2 col-xs-offset-4 gab next">
				<select id="TechProfi" name="selfAssesment" class="form-control">
					<option value="0">Self Assesement</option>
					<option value="1">1 - Novice</option>
					<option value="2">2 - Learner</option>
					<option value="3">3 - Intermediate</option>
					<option value="4">4 - Master</option>
					<option value="5">5 - Expert</option>
</select>


			</div>
			<div class="col-xs-2  gab ">
				<select id="DomainProfi" name="selfAssesment" class="form-control">
					<option value="0">Self Assesement</option>
					<option value="1">1 - Novice</option>
					<option value="2">2 - Learner</option>
					<option value="3">3 - Intermediate</option>
					<option value="4">4 - Master</option>
					<option value="5">5 - Expert</option>
				</select>


			</div>
			<div class="col-xs-12 text-center  next">
					<b class="or"> AND / OR</b>
				</div>
				
				<div class="col-xs-2 next gab col-xs-offset-2">
				<select class="form-control" id="rmgMethodology"
					placeholder="Methodology Name" ></select>
			</div>

			<div class="col-xs-2 gab">
				<select class="form-control" id="rmgTOOL" placeholder="Tool Name" ></select>
			</div>
			<div class="col-xs-2  gab">
				<select class="form-control" id="rmgpname"
					placeholder="Project Name">
				</select>
			</div>
			<div class="col-xs-2  gab">
				<select class="form-control" id="rmgAcc" placeholder="Accreditation Name">
													<option value="">Accreditation</option>
				
																<c:forEach items="${allAccreditationList}" var="allAccreditationList">
													<option value="${allAccreditationList.accreditationName}">${allAccreditationList.accreditationName}</option>
				
									</c:forEach>
				
				</select>
			</div> 
			<div class="col-xs-12 text-center  gab">
				<button class="btn btn-danger disabled" id="stbn">Search</button>
			</div>
			





	<div class="col-xs-12 text-center ">
		<span class="star" id="nodataError"><b>No Data found</b> </span> <br />
	</div>



</c:if>




<div class="col-xs-12">
<div id="myModal2" class="modal fade " role="dialog" style="width:100%; padding-left:0px !important; margin-left:0px;margin-right-0px;">
  <div class="modal-dialog" style="width:90%;">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <div class="col-xs-12">
        <h4 class="modal-title" style="text-align:center">All Attra Employee</h4>
        </div>
        <div class="col-xs-12 pull-right star" style="text-align: right;">
        	
        	<h5>* for detailed list of data, please export the table data to excel</h5></div>
        
      </div>
      <div class="modal-body">
      <div class="row">
      <div class="col-lg-6 col-xs-6 col-sm-6 col-xs-6">
      <b><span id="Employee_list_info1"></span></b>
      </div>
      <div class="col-lg-6 col-xs-6 col-sm-6 col-xs-6">
      	<button class="btn btn-danger pull-right" type="button" id="exportToXl1" onclick="location.href='${pageContext.servletContext.contextPath}/ExportReportExcel'">Export to Excel</button>
      </div>
      <div class="col-lg-12 col-xs-12 col-sm-12 col-xs-12">
        <div class="col-lg-12 col-xs-12 col-sm-12 col-xs-12 datatableStyling" id="Datatable_Employee" style="margin: 0px 0px 0px 0px; display: block;">
			<table id="Employee_list" class="display display_table table nowrap table-bordered table-striped dt-responsive" width="900px">
				<thead> 			
         		<tr id="filterrow">
               <th>Employee Name</th>
<th>Current Project</th>
<th>Current Project</th>
<th>Primary</th>
<th>Skill</th>
<th>Sub Domain</th>
<th>Methodology</th>
<th>Tools</th>
<th>Total Experience</th>
<th>Accreditation</th>
				<th>Action</th>
            </tr>  <tr>
               <th>Employee Name</th>
<th>Current Project</th>
<th>Current Project</th>
<th>Primary</th>
<th>Skill</th>
 <th>Sub Domain</th>
 <th>Methodology</th>
<th>Tools</th>
<th>Total Experience</th>
<th>Accreditation</th>
				<th>Action</th>
            </tr>
            </thead>
			<tfoot></tfoot>
			</table>
		</div>
		</div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button"   class="btn btn-default close" data-dismiss="modal">Close</button>
      </div>
    </div>
</div>
  </div>
  <div id="myModal3" class="modal fade " role="dialog" style="width:100%; opacity:1; margin-top:0px; padding-left:0px !important; margin-left:0px;margin-right-0px;">
  <div class="modal-dialog" style="width:90%; margin-top:200px;">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <div class="col-xs-12">
        <h4 class="modal-title" id="modalHeader3" style="text-align:center"></h4>
        </div>
           <div class="col-xs-12 pull-right star" style="text-align: right;">
        	<!-- <div class="col-xs-4"></div><div class="col-xs-4"></div>
        	<div class="col-xs-4"> -->
        	<h5>* for detailed list of data, please export the table data to excel</h5></div>
        <!-- </div> -->
      </div>
      <div class="modal-body">
      <div class="row">
      <div class="col-lg-6 col-xs-6 col-sm-6 col-xs-6">
      <b><span id="Employee_list3_info1"></span></b>
      </div>
      <div class="col-lg-6 col-xs-6 col-sm-6 col-xs-6">
      	<button class="btn btn-danger pull-right" type="button" id="exportKeywordSearch">Export to Excel</button>
      </div>
      <div class="col-lg-12 col-xs-12 col-sm-12 col-xs-12">
        <div class="col-lg-12 col-xs-12 col-sm-12 col-xs-12 datatableStyling" id="Datatable_Employee3" style="margin: 0px 0px 0px 0px; display: block;">
			<table id="Employee_list3" class="display display_table table nowrap table-bordered table-striped dt-responsive" width="900px">
							<thead> 			
         		<tr id="filterrow">
               <th>Employee Name</th>
               <th>Area of Work</th>
<th>Current Project</th>
<th>Technology</th>
<th>Sub Domain</th>
<th>Methodology</th>
<th>Tools</th>
<th>Total Experience</th>
<th>Accreditation</th>
				<th>Action</th>
            </tr>  <tr>
             <th>Employee Name</th>
               <th>Area of Work</th>
<th>Current Project</th>
<th>Technology</th>
<th>Sub Domain</th>
<th>Methodology</th>
<th>Tools</th>
<th>Total Experience</th>
<th>Accreditation</th>
				<th>Action</th>
            </tr>
            </thead>
			<tfoot></tfoot>
			</table>
		</div>
		</div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button"  class="btn btn-default close" data-dismiss="modal">Close</button>
      </div>
    </div>
</div>
  </div>
  
    <div id="myModal4" class="modal fade " role="dialog" style="width:100%; opacity:1; margin-top:0px; padding-left:0px !important; margin-left:0px;margin-right-0px;">
  <div class="modal-dialog" style="width:90%; margin-top:200px;">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header" style="padding:0px;">
        <button type="button" class="close" data-dismiss="modal" style="padding-right:10px; padding-top:10px;">&times;</button>
        <div class="col-xs-12">
        <h4 class="modal-title" id="modalHeader4" style="text-align:center">Combinational Search</h4>
        </div>
           <div class="col-xs-12 pull-right star" style="text-align: right;">
        	<!-- <div class="col-xs-4"></div><div class="col-xs-4"></div>
        	<div class="col-xs-4"> -->
        	<h5>* for detailed list of data, please export the table data to excel</h5></div>
       <!--  </div> -->
      </div>
      <div class="modal-body">
      <div class="row">
      <div class="col-lg-6 col-xs-6 col-sm-6 col-xs-6">
      <b><span id="Employee_list4_info1"></span></b>
      </div>
      <div class="col-lg-6 col-xs-6 col-sm-6 col-xs-6">
      	<button class="btn btn-danger pull-right" type="button" id="createConmiExport">Export to Excel</button>
      </div>
      <div class="col-lg-12 col-xs-12 col-sm-12 col-xs-12">
        <div class="col-lg-12 col-xs-12 col-sm-12 col-xs-12 datatableStyling" id="Datatable_Employee4" style="margin: 0px 0px 0px 0px; display: block;">
			<table id="Employee_list4" class="display display_table table nowrap table-bordered table-striped dt-responsive" width="900px">

			<thead> 			
         		<tr id="filterrow">
               <th>Employee Name</th>
               <th>Area of Work</th>
<th>Current Project</th>
<th>Technology</th>
<th>Sub Domain</th>
<th>Methodology</th>
<th>Tools</th>
<th>Total Experience</th>
<th>Accreditation</th>
				<th>Action</th>
            </tr>  <tr>
             <th>Employee Name</th>
               <th>Area of Work</th>
<th>Current Project</th>
<th>Technology</th>
<th>Sub Domain</th>
<th>Methodology</th>
<th>Tools</th>
<th>Total Experience</th>
<th>Accreditation</th>
				<th>Action</th>
            </tr>
            </thead>
			<tfoot></tfoot>
			
			</table>
		</div>
		</div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button"  class="btn btn-default close" data-dismiss="modal">Close</button>
      </div>
    </div>
</div>
  </div>
</div>




<script>

function stopPropagation(evt) {
	 if (evt.stopPropagation !== undefined) {
	 evt.stopPropagation();
	 } else {
	 evt.cancelBubble = true;
	 }
	 }


$(document).ready(function() {
	var path = '${pageContext.servletContext.contextPath}';
	
	$("#showEmpLight").hide();
	$('#TechExp').hide();
	$('#TechProfi').hide();
	$('#DomainExp').hide();
	$('#DomainProfi').hide();
	
	var employessList = <%=employessList%>;	
	
	
	var arr_candiadte = [];
	for ( var x1 in employessList) {
		var arr_candidateList = [];
		arr_candidateList.push(employessList[x1].empName===null?"NA":employessList[x1].empName);
		arr_candidateList.push(employessList[x1].areaOfWork===null?"NA":employessList[x1].areaOfWork);
		arr_candidateList.push(employessList[x1].projectName===null?"NA":employessList[x1].projectName);
		arr_candidateList.push(employessList[x1].primaryTech===null?"NA":employessList[x1].primaryTech);
		arr_candidateList.push(employessList[x1].secondarySkill===null?"NA":"NA");
		arr_candidateList.push(employessList[x1].domainName===null?"NA":employessList[x1].domainName);
		arr_candidateList.push(employessList[x1].methodlogy===null?"NA":employessList[x1].methodlogy);
		arr_candidateList.push(employessList[x1].toolName===null?"NA":employessList[x1].toolName);
		arr_candidateList.push(employessList[x1].expInyear+' & '+employessList[x1].expInmonth);
		arr_candidateList.push(employessList[x1].accreditation===null?"NA":employessList[x1].accreditation);
		
		
		arr_candidateList.push('<a class="btn btn-default btn-danger" href="<%=request.getContextPath()%>/employeeDetailsById/0/'+employessList[x1].empName+'/'+employessList[x1].emailId+'">View Details</a>');
		
		
		arr_candiadte.push(arr_candidateList);
	}

	
	  $('#Employee_list thead tr#filterrow th').each( function () {
	        var title = $('#Employee_list thead th').eq( $(this).index() ).text();
	        var num = $(this).index();
	        $(this).html( '<input type="text" id="'+num+'" onclick="stopPropagation(event);" placeholder="Search '+title+'" />' );
	    } ); 	
	

	  
	$("#Datatable_Employee").show();
	var table = $('#Employee_list').DataTable({
		responsive : true,
		
/* 		"initComplete" : function () {
			 $(".dataTables_scrollBody th").removeAttr('class');
		}, */
		"scrollX" : true, 
		"scrollY":'45vh',
		 scrollCollapse: true, 
		 "order":[[0,'asc']],
		  paging:         true,
		  fixedColumns:   true,
		
		 "columnDefs": [ 
	                    {
	                        "targets": [ 1 ],
	                        "visible": false,
	                        "searchable": true
	                    }
	                ], 
	 data : arr_candiadte,

	});
	
	
	
    $("thead input").keyup(function(event) {
    	 table.column( this.id ).search( this.value ).draw();
     
   });
	
	
	
	var totalEntries = $('#Employee_list_info').text();
	$('#Employee_list_info1').text(totalEntries);
	$('#Employee_list_length').on('change',function(){
		totalEntries = $('#Employee_list_info').text();
		$('#Employee_list_info1').text(totalEntries);
	});
	
	
	
	
	

	
	
	
	
/* 	$('#viewAllEmp').on('click',function(){
		
	
	

	});
 */
		
	 
	 /* Searching  */

		$('#freeSearch').on('keypress', function (temp) {
			if(temp.keyCode === 13){
				var freeSearch=document.getElementById('freeSearch').value;
				$("#myModal2").hide();
			if (freeSearch.length>0) {
					var path1=path;
					  $.ajax({
				          type: "POST",
				          url:path+"/searchByKeyword/"+freeSearch,
				          success: function(message)
				          { 

				        	var candidateList=message.substring(message.indexOf('['));
				  			var employessList = JSON.parse(candidateList); 
				  			
				  			var arr_candiadte = [];
							for ( var x1 in employessList) {
								var arr_candidateList = [];
								arr_candidateList.push(employessList[x1].empName===null?"NA":employessList[x1].empName);
								arr_candidateList.push(employessList[x1].areaOfWork===null?"NA":employessList[x1].areaOfWork);
								arr_candidateList.push(employessList[x1].projectName===null?"NA":employessList[x1].projectName);
								arr_candidateList.push(employessList[x1].primaryTech===null?"NA":employessList[x1].skillDesc);
								arr_candidateList.push(employessList[x1].domain===null?"NA":employessList[x1].domain);
								arr_candidateList.push(employessList[x1].methodology===null?"NA":employessList[x1].methodology);
								arr_candidateList.push(employessList[x1].toolName===null?"NA":employessList[x1].tool);
								arr_candidateList.push(employessList[x1].expInyear+' & '+employessList[x1].expInmonth);
								arr_candidateList.push(employessList[x1].accreditation===null?"NA":employessList[x1].accreditation);
								
			 					
								arr_candidateList.push('<a class="btn btn-default btn-danger" href="<%=request.getContextPath()%>/employeeDetailsById/0/'+employessList[x1].name+'/'+employessList[x1].empMailId+'">View Details</a>');
								
								 
								arr_candiadte.push(arr_candidateList);
								
								/* $('#mybtnFree'+x1).on('click',function(){
				            		
					            	   var empId=employessList[x1].empId;
					            	   var name=employessList[x1].name;
					            	   var empMailId=employessList[x1].empMailId;
					            		var path1=path;
			    	   
										location.href=path1+"/employeeDetailsById/0/"+empMailId+"/"+empMailId;

					            	   
					            	}); */
		
							}
				        	
							$('#Employee_list3 thead tr#filterrow th').each( function () {
						        var title = $('#Employee_list3 thead th').eq( $(this).index() ).text();
						        var num = $(this).index();
						        $(this).html( '<input type="text" id="'+num+'" onclick="stopPropagation(event);" placeholder="Search '+title+'" />' );
						    } );
							
							
							$('#Datatable_Employee3').show();
							var table = $('#Employee_list3').DataTable({
								
								responsive : true,
								"initComplete" : function () {
									 $(".dataTables_scrollBody th").removeAttr('class');
								},
								"bDestroy": true,
								"scrollX" : true, 
								"scrollY":'45vh',
								 scrollCollapse: true,
								 paging:         true,
								  fixedColumns:   true,
								 "order":[[0,'asc']],
								 data : arr_candiadte,
					 		 "columnDefs": [ 
							                    {
							                        "targets": [ 1 ],
							                        "visible": false,
							                        "searchable": true
							                    }
							                ],
							
				
						
							});
							
							  $("thead input").keyup(function(event) {
							    	 table.column( this.id ).search( this.value ).draw();
							     
							   });
							
							
							var totalEntries3 = $('#Employee_list3_info').text();
							$('#Employee_list3_info1').text(totalEntries3);
							$('select').on('change',function(){
								totalEntries3 = $('#Employee_list3_info').text();
								$('#Employee_list3_info1').text(totalEntries3);
							});
							
							$('#modalHeader3').text("Search result for : "+freeSearch);
							
							
							$("#myModal3").show();
				        	},
				        	 error: function(xhr, status, error) {
				        		 /*   alert(xhr.responseText);*/
				        		 alert("No data found");
				        	 } 
				      });
					  
						$('#exportKeywordSearch').on('click',function(){
							var path1=path;
			       		 	location.href=path1+"/exportKeywordSearch/"+freeSearch;
			       		 	return;
						});
					  
		     }else{
		    	 
		    	 
		     }
			}
			
			
		/* 	$('#exportKeywordSearch').on('click',function(){
				var path1=path;
       		 	location.href=path1+"/exportKeywordSearch/"+freeSearch;
       		 	return;
			}); */
			
			
		
		});
		$('.close').on("click",function(){
			$("#myModal2").hide();
			$("#myModal3").hide();
			$("#myModal4").hide();
			$('#TechExp').hide();
			$('#TechProfi').hide();
			$('#DomainExp').hide();
			$('#DomainProfi').hide();
		});
		
		
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
		});	
		
		$('#rmgskill').editableSelect({ effects: 'default' });
		$('#rmgpname').editableSelect({ effects: 'fade' });
		$('#rmgTOOL').editableSelect({ effects: 'fade' });
		$('#rmgMethodology').editableSelect({ effects: 'fade' });
		$('#rmgDomain').editableSelect({ effects: 'fade' });
		$("#showEmpLight").hide();
		
		
		$('#rmgskill,#rmgpname,#rmgTOOL,#rmgMethodology,#rmgDomain,#rmgAcc').on('mouseleave', function () {

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
		
		
		/* RMG Skill key word search */
		$('#rmgskill').on('keydown', function (temp) {
			
			var val=$('#rmgskill').val();

			var path1=path;
			
			 $.ajax({
		          type: "POST",
		          url:path+"/getSkillName/"+val,
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
		          }});
		});
		
		$('#rmgpname').on('keyup', function (temp) {
			var val=$('#rmgpname').val();

			var path1=path;
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
			var val=$('#rmgTOOL').val();

			var path1=path;
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
			var val=$('#rmgMethodology').val();

			var path1=path;
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
			var val=$('#rmgDomain').val();

			var path1=path;

				

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
			
			
		});
		
		$('#stbn').on('click', function () {
			
			var isprimary=$('#isprimaryRMG').val();
			var skillName=$('#rmgskill').val();
			var projectName=$('#rmgpname').val();
			var toolName=$('#rmgTOOL').val();
			var methodologyName=$('#rmgMethodology').val();
			var domainName=$('#rmgDomain').val();
			
			var TechExp=$('#TechExp').val();
			var DomainExp=$('#DomainExp').val();
			var TechProfi=$('#TechProfi').val();
			var DomainProfi=$('#DomainProfi').val();
			var acc=$('#rmgAcc').val();
			
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
		
		var path1=path;
		$.ajax({
	          type: "POST",
	          url:path1+"/searchEmployeeWithSkill/"+skillName+"/"+projectName+"/"+toolName+"/"+methodologyName+"/"+domainName+"/"+techPrimary+"/"+domainPrimary+"/"+TechExp+"/"+DomainExp+"/"+TechProfi+"/"+DomainProfi+"/"+acc,
	          success: function(message)
	          {
	        	  if(message.length==0)
	        		{
	$('.errorRMG').html("No Data found");
	$(".panel_custom").hide();
	$("#showEmpLight1").show();
	$('#TechExp').hide();
	$('#DomainExp').hide();
	$('#TechProfi').hide();
	$('#DomainProfi').hide();

	var path1=path;
	location.href= path1+"/home";
	        		}
	        	else
	        		{
	              	var candidateList=message.substring(message.indexOf('['));
		  			var employessList = JSON.parse(candidateList); 
		  			
		  			var arr_candiadte = [];
					for ( var x1 in employessList) {
						
						var technology ="NA";
						var domain ="NA";
						
						if(employessList[x1].techDesc)
		               	{
		            		technology =employessList[x1].techDesc;
//		            		technology.concat(", exp:",item.tExpInyear," Years",item.tExpInmonth," Months");
//		            		technology.concat(", Proficiency:",item.tProf);
		               		if(employessList[x1].tExpInyear>0){

		            		technology += ' exp:';
		            		technology += ' ' + employessList[x1].tExpInyear;
		            		technology += ' Yr(s) ';
		            		
		            		if(item.tExpInmonth>0){
		                		technology += '&';

		            		technology += ' ' + employessList[x1].tExpInmonth;
		            		technology += ' Mn(s)';
		            		}
		               		}
		               		else if(item.tExpInmonth>0){
		               			technology += ' exp:';
		            		technology += ' ' + employessList[x1].tExpInmonth;
		            		technology += ' Mn(s)';
		            		}
		               		if(item.techProf>0){

		            		technology += ' Proficiency:';
		            		technology += ' ' + employessList[x1].techProf;
		               		}
		               	}
		               	if(employessList[x1].domain)
		               	{
		               		domain =employessList[x1].domain;
		               		if(employessList[x1].dExpInyear>0){
		               		domain += ' exp:';
		               		domain += ' ' + employessList[x1].dExpInyear;
		               		domain += ' Yr(s) ';
		            		if(employessList[x1].dExpInmonth>0){
		            			domain += '&';

		               		domain += ' ' + employessList[x1].dExpInmonth;
		               		domain += ' Mn(s)';
		            		}
		               		}
		               		else if(employessList[x1].dExpInmonth>0){
		               			domain += ' exp:';
		               		domain += ' ' + employessList[x1].dExpInmonth;
		               		domain += ' Mn(s)';
		            		}
		               		if(employessList[x1].domainProf>0)
		               			{
		               		domain += ' Proficiency:';
		               		domain += ' ' + employessList[x1].domainProf;
		               			}
		            		
		               	}
						
						var arr_candidateList = [];
						arr_candidateList.push(employessList[x1].empName===null?"NA":employessList[x1].empName);
						arr_candidateList.push(employessList[x1].areaOfWork===null?"NA":employessList[x1].areaOfWork);
						arr_candidateList.push(employessList[x1].projectName===null?"NA":employessList[x1].projectName);
						arr_candidateList.push(technology);
						arr_candidateList.push(domain);
						arr_candidateList.push(employessList[x1].methodology===null?"NA":employessList[x1].methodology);
						arr_candidateList.push(employessList[x1].tool===null?"NA":employessList[x1].tool);
						arr_candidateList.push(employessList[x1].dExpInyear+' & '+employessList[x1].dExpInmonth);
						arr_candidateList.push(employessList[x1].accreditation===null?"NA":employessList[x1].accreditation);
						
	 					
						arr_candidateList.push('<a class="btn btn-default btn-danger" href="<%=request.getContextPath()%>/employeeDetailsById/0/'+employessList[x1].empMailId+'/'+employessList[x1].empMailId+'">View Details</button>');
						
						 
						arr_candiadte.push(arr_candidateList);
					}
					
					$('#Employee_list4 thead tr#filterrow th').each( function () {
				        var title = $('#Employee_list4 thead th').eq( $(this).index() ).text();
				        var num = $(this).index();
				        $(this).html( '<input type="text" id="'+num+'" onclick="stopPropagation(event);" placeholder="Search '+title+'" />' );
				    } );
					
					
					
					$('#Datatable_Employee4').show();
					var table = $('#Employee_list4').DataTable({
						
						responsive : true,
						"initComplete" : function () {
							 $(".dataTables_scrollBody th").removeAttr('class');
						},
						"scrollX" : true,
						"bDestroy": true,
						"scrollY":'45vh',
						 scrollCollapse: true, 
						 paging:         true,
						  fixedColumns:   true,
						 "order":[[0,'asc']],
						 data : arr_candiadte,
			 		 "columnDefs": [ 
					                    {
					                        "targets": [ 1 ],
					                        "visible": false,
					                        "searchable": true
					                    }
					                ],
					
			
				
					});
					
					 $("thead input").keyup(function(event) {
				    	 table.column( this.id ).search( this.value ).draw();
				     
				   });
					
					
					var totalEntries4 = $('#Employee_list4_info').text();
					$('#Employee_list4_info1').text(totalEntries4);
					$('select').on('change',function(){
						totalEntries4 = $('#Employee_list4_info').text();
						$('#Employee_list4_info1').text(totalEntries4);
					});
					
					
					
					
					
					$("#myModal4").show();
					$('#createConmiExport').on('click',function(){
						var path1=path;
						//location.href= path1+"/ExportSearchEmployeeWithSkill/"+skillName+"/"+projectName+"/"+toolName+"/"+methodologyName+"/"+domainName+"/"+techPrimary+"/"+domainPrimary+"/"+TechExp+"/"+DomainExp+"/"+TechProfi+"/"+DomainProfi+"/"+acc;
						location.href= path1+"/exportCombinationalSearchEmployee/"+skillName+"/"+projectName+"/"+toolName+"/"+methodologyName+"/"+domainName+"/"+techPrimary+"/"+domainPrimary+"/"+TechExp+"/"+DomainExp+"/"+TechProfi+"/"+DomainProfi+"/"+acc;
					});
	        		}
	          }
	          });
		

			
		});
		
		
		
});
</script>
<%-- <jsp:include page="footer.jsp" /> --%>
