<%@include file="header.jsp"%>

<% String accreditationList=(String)request.getAttribute("accreditationList");
System.out.println(accreditationList);
%>

<div class="configStyle" style="width:100%;">
	<div class="panel panel-danger">
		<div class="panel-body">	
			<div class="col-xs-12">
				<div class="col-xs-12">
					<div class="panel panel-danger">
						<div class="panel-heading text-center">
							<font size="3"><b>Add Accreditation</b></font>
						</div>
						<div class="panel-body">	
							<div class="col-xs-12 text-center" style="padding-bottom:20px;padding-top:20px">
								<div class="col-xs-1"></div>
								<div class="col-xs-5 ">
									<font size="3"><b>Accreditation Name</b></font>
								</div>
								<div class="col-xs-5">
									<font size="3"><b>Action</b></font>
								</div>
								<div class="col-xs-1"></div>
							</div>
							<form autocomplete="off">
							<div class="col-xs-12">
								<div class="col-xs-1"></div>
								<div class="col-xs-5 ">
									<input class="form-control" type="text" id="accredationText" name="accredation">
								</div>
								<div class="col-xs-5 text-center">
									<input type="button" value="Save" id="saveButton" onclick="saveAccredation();" class="btn btn-default btn-danger"></input>
								</div>
								<div class="col-xs-1"></div>
							</div>
							</form>
						</div>
					</div>
				</div> 
			<!-- </div> -->
				<div class="col-xs-12">
					<div class="panel panel-danger ">
						<div class="panel-heading text-center">
							<font size="3"><b>Accreditation List</b></font>
						</div>
						<div class="panel-body">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 datatableStyling "
									id="Datatable_Accre" style="margin: 30px 0px 100px 0px;">
									<table id="Accre_list"
										class="display display_table table nowrap table-bordered  table-striped  dt-responsive" style="width:100%;">
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
$(document).ready(function() { 
	var aa = '<%=accreditationList%>';
	var accreditationList= JSON.parse(aa);
   	var count=0;
   	//alert(accreditationList.length)
	var ResultData_Acc=[];
	for(var x1 in accreditationList){
		var arr_ACC=[];
		//arr_ACC.push(accreditationList[x1].accID===null?"N/A":accreditationList[x1].accID);
		arr_ACC.push(accreditationList[x1].accreditationName===null?"N/A":accreditationList[x1].accreditationName);
		arr_ACC.push('<input type="button" name="firstname" class="btn btn-default btn-primary btn-sm btn-danger editAccre" accID="'+accreditationList[x1].accID+'" value="Edit" style="border-radius:2px;">');
		
		ResultData_Acc.push(arr_ACC);
	}
	
	$('#Datatable_Accre').show();
	$('#Accre_list').DataTable( {
		
		 	 scrollY:"520px",
	        scrollX:true,
	        "bDestroy": true,
		 data: ResultData_Acc,
		
		 columns: [
				//{ title: "Accrediation Id" },
				{ title: "Accreditation Name" },
				{ title: "Action"},
			]
	});$(".dataTables_scrollBody th").removeAttr('class');
	$(".dataTables_scrollHeadInner th").removeAttr('class');
});

</script>
<%@include file="footer.jsp"%>

<%--   <script src="${pageContext.servletContext.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/jquery-3.1.1.min.js"></script>
  <script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script> --%>
  <script src="${pageContext.servletContext.contextPath}/resources/js/jquery.validate.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/js/addAccredation.js"></script>