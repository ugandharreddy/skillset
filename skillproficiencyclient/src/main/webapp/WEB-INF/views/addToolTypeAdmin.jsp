<%@include file="header.jsp"%>

<% String toolTypeList=(String)request.getAttribute("toolTypeList");
System.out.println(toolTypeList);
%>

<div class="configStyle" style="width:100%;">
	<div class="panel panel-danger">
		<div class="panel-body">	
			<div class="col-xs-12">
				<div class="col-xs-12">
					<div class="panel panel-danger">
						<div class="panel-heading text-center">
							<font size="3"><b>Add Tooltype</b></font>
						</div>
						<div class="panel-body">	
							<div class="col-xs-12 text-center" style="padding-bottom:20px;padding-top:20px">
								<div class="col-xs-1"></div>
								<div class="col-xs-5 ">
									<font size="3"><b>Tooltype Name</b></font>
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
									<input class="form-control" type="text" id="tooltypeText" name="tooltype">
								</div>
								<div class="col-xs-5 text-center">
									<input type="button" value="Save" id="saveButton" onclick="saveToolType();" class="btn btn-default btn-danger">
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
							<font size="3"><b>Tooltype List</b></font>
						</div>
						<div class="panel-body">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 datatableStyling "
									id="Datatable_Tooltype" style="margin: 30px 0px 100px 0px;">
									<table id="ToolType_list"
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
	var aa = '<%=toolTypeList%>';
	var toolTypeList= JSON.parse(aa);
   	var count=0;
   	//alert(accreditationList.length)
	var ResultData_Tooltype=[];
	for(var x1 in toolTypeList){
		var arr_TTOOL=[];
		//arr_ACC.push(accreditationList[x1].accID===null?"N/A":accreditationList[x1].accID);
		arr_TTOOL.push(toolTypeList[x1].toolTName===null?"N/A":toolTypeList[x1].toolTName);
		arr_TTOOL.push('<input type="button" name="firstname" class="btn btn-default btn-danger btn-primary btn-sm editTooltype" toolTypeID="'+toolTypeList[x1].toolTId+'" value="Edit" style="border-radius:2px;">');
		
		ResultData_Tooltype.push(arr_TTOOL);
	}
	
	$('#Datatable_Tooltype').show();
	$('#ToolType_list').DataTable( {
		
		 	 scrollY:"520px",
	        scrollX:true,
	     //   scrollCollapse: true, 
	        "bDestroy": true,
		 data: ResultData_Tooltype,
		
		 columns: [
				//{ title: "Accrediation Id" },
				{ title: "Tooltype Name" },
				{ title: "Action"},
			]
	});$(".dataTables_scrollBody th").removeAttr('class');
	$(".dataTables_scrollHeadInner th").removeAttr('class');
});

</script>

<%@include file="footer.jsp"%>

  <%--  <script src="${pageContext.servletContext.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/jquery-3.1.1.min.js"></script>
  <script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script> --%>
  <script src="${pageContext.servletContext.contextPath}/resources/js/jquery.validate.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/js/addToolType.js"></script>