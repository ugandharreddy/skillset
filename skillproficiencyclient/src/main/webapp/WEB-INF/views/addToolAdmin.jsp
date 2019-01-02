<%@include file="header.jsp"%>

<% String toolList=(String)request.getAttribute("toolList");
System.out.println(toolList);
%>

<div class="configStyle" style="width:100%;">
	<div class="panel panel-danger">
		<div class="panel-body">	
			<div class="col-xs-12">
				<div class="col-xs-12">
					<div class="panel panel-danger">
						<div class="panel-heading text-center">
							<font size="3"><b>Add Tool</b></font>
						</div>
						<div class="panel-body">	
							<div class="col-xs-12 text-center" style="padding-bottom:20px;padding-top:20px">
								<!-- <div class="col-xs-1"></div> -->
								<div class="col-xs-4">
									<font size="3"><b>Tool Type</b></font>
								</div>
								<div class="col-xs-4">
									<font size="3"><b>Tool</b></font>
								</div>
								<div class="col-xs-4">
									<font size="3"><b>Action</b></font>
								</div>
								<!-- <div class="col-xs-1"></div> -->
							</div>
							<form autocomplete="off">
							<div class="col-xs-12">
								<!-- <div class="col-xs-1"></div> -->
								<div class="col-xs-4">
									<select class="form-control" id="Ttool">
										<option>Select</option>
										<c:forEach items="${toolTypeList}" var="toolTypeList"
											varStatus="">
											<option value="${toolTypeList.toolTId}">${toolTypeList.toolTName}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-xs-4">
									 <input class="form-control" type="text" id="toolText" name="tool">
								</div>
								<div class="col-xs-4 text-center">
									<input type="button" value="Save" id="saveButton" onclick="saveTool();" class="btn btn-default btn-danger"></input>
								</div>
								<!-- <div class="col-xs-1"></div> -->
							</div>
							</form>
						</div>
					</div>
				</div> 
			<!-- </div> -->
				<div class="col-xs-12">
					<div class="panel panel-danger ">
						<div class="panel-heading text-center">
							<font size="3"><b>Tool List</b></font>
						</div>
						<div class="panel-body">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 datatableStyling "
									id="Datatable_Tool" style="margin: 30px 0px 100px 0px;">
									<table id="Tool_list"
										class="display display_table table nowrap table-bordered  table-striped  dt-responsive" style="width:100%;">
								</table>
							</div>
						</div>
						<%-- <div class="panel-body">
							<div  class="dataTables_paginate paging_simple_numbers">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover dataTable" id="example">
									<thead>
										<tr>
											<th class="text-center">Domain</th>
											<th class="text-center">Subdomain</th>
											<th class="text-center">Action</th>
										</tr>
									</thead>
									<tbody>
										  <c:forEach items="${toolList}" var="toolList" varStatus="">
										       <tr id="${toolList.toolID}">
										        <td id=""><input disabled class="form-control" type="text" id="toolType${toolList.toolTypeID}"  value="${toolList.toolTypeName}"></td>
										        <td id=""><input disabled class="form-control" type="text" id="tool${toolList.toolID}"  value="${toolList.toolName}"></td>
										        <td>
										        
										        <input type="button" value="Edit" id="editButton${toolList.toolID}" onclick="editTool('${toolList.toolID}');" class="btn btn-default btn-danger"></input>
										        <input type="button" value="Update" style="display:none;" id="updateButton${toolList.toolID}" onclick="updateTool('${toolList.toolTypeID}','${toolList.toolID}');" class="btn btn-default btn-danger"></input>
										        </td>
										      </tr>
									      </c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div> --%>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<script>

$(document).ready(function() { 
	var aa = '<%=toolList%>';
	var toolList= JSON.parse(aa);
   	var count=0;
   	//alert(accreditationList.length)
	var ResultData_Tool=[];
	for(var x1 in toolList){
		var arr_TOOL=[];
		arr_TOOL.push(toolList[x1].toolTypeName===null?"N/A":toolList[x1].toolTypeName);
		arr_TOOL.push(toolList[x1].toolName===null?"N/A":toolList[x1].toolName);
		arr_TOOL.push('<input type="button" name="firstname" class="btn btn-default btn-danger btn-primary btn-sm editTool" toolID="'+toolList[x1].toolID+'" toolTypeID="'+toolList[x1].toolTypeID+'"value="Edit" style="border-radius:2px;">');
		
		ResultData_Tool.push(arr_TOOL);
	}
	
	$('#Datatable_Tool').show();
	$('#Tool_list').DataTable( {
		
		 	 scrollY:"520px",
	        scrollX:true,
	       // scrollCollapse: true, 
	        "bDestroy": true,
		 data: ResultData_Tool,
		
		 columns: [
				{ title: "Tool Type" },
				{ title: "Tool"},
				{ title: "Action"},
			]
	});$(".dataTables_scrollBody th").removeAttr('class');
	$(".dataTables_scrollHeadInner th").removeAttr('class');
});

</script>

<%@include file="footer.jsp"%>
<%--    <script src="${pageContext.servletContext.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/jquery-3.1.1.min.js"></script>
  <script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script> --%>
  <script src="${pageContext.servletContext.contextPath}/resources/js/jquery.validate.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/js/addTool.js"></script>