<%@include file="header.jsp"%>

<% String roleEmpList=(String)request.getAttribute("roleEmpList");
System.out.println(roleEmpList);
%>

<div class="configStyle" style="width:100%;">
	<div class="panel panel-danger">
		<div class="panel-body">	
			<div class="col-xs-12">
				<div class="col-xs-12">
					<div class="panel panel-danger">
						<div class="panel-heading text-center">
							<font size="3"><b>Assign Employee Role</b></font>
						</div>
						<div class="panel-body">	
							<div class="col-xs-12 text-center" style="padding-bottom:20px;padding-top:20px">
								<!-- <div class="col-xs-1"></div> -->
								<div class="col-xs-4">
									<font size="3"><b>Role </b></font>
								</div>
								<div class="col-xs-4">
									<font size="3"><b>Employee EmailId</b></font>
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
									<select class="form-control" id="Rrole">
										<option>Select</option>
										<c:forEach items="${roleList}" var="roleList"
											varStatus="">
											<option value="${roleList.roleID}">${roleList.roleDescription}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-xs-4">
									<input type="text" id="empText" name="EmpName" class="form-control" placeholder="Search Name">
											<ul id="uol" style="display:none"></ul>
								</div>
								<div class="col-xs-4 text-center">
									<input type="button" value="Assign" id="saveButton" onclick="assignEmp();" class="btn btn-default btn-danger">
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
							<font size="3"><b>Employee Role List</b></font>
						</div>
						<div class="panel-body">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 datatableStyling "
									id="Datatable_EmpRole" style="margin: 30px 0px 100px 0px;">
									<table id="EmpRole_list"
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
										  <c:forEach items="${roleEmpList}" var="roleEmpList" varStatus="">
										       <tr id="${roleEmpList.roleID}">
										        <td id=""><input disabled class="form-control" type="text" name="methodology" value="${roleEmpList.roleName}"></td>
										     	<td id=""><input disabled class="form-control" type="text" name="methodology" value="${roleEmpList.empEmailID}"></td>
										        <td>
										        <input type="button" value="Delete" id="deleteButton${roleEmpList.roleID}" onclick="deleteRole('${roleEmpList.roleID}','${roleEmpList.empEmailID}');" class="btn btn-default btn-danger"></input>
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
	var aa = '<%=roleEmpList%>';
	var roleEmpList= JSON.parse(aa);
   	var count=0;
   	//alert(accreditationList.length)
	var ResultData_EmpRole=[];
	for(var x1 in roleEmpList){
		var arr_EROLE=[];
		arr_EROLE.push(roleEmpList[x1].roleName===null?"N/A":roleEmpList[x1].roleName);
		arr_EROLE.push(roleEmpList[x1].empEmailID===null?"N/A":roleEmpList[x1].empEmailID);
		arr_EROLE.push('<input type="button" name="firstname" class="btn btn-default btn-danger btn-primary btn-sm deleteRole" roleId="'+roleEmpList[x1].roleID+'" empId="'+roleEmpList[x1].empEmailID+'"value="Delete" style="border-radius:2px;">');
		
		ResultData_EmpRole.push(arr_EROLE);
	}
	
	$('#Datatable_EmpRole').show();
	$('#EmpRole_list').DataTable( {
		
		 	 scrollY:"520px",
	        scrollX:true,
	        //scrollCollapse: true, 
	        "bDestroy": true,
		 data: ResultData_EmpRole,
		
		 columns: [
				{ title: "Role" },
				{ title: "Employee EmailId"},
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
    <script src="${pageContext.servletContext.contextPath}/resources/js/AdminAssignEmpRole.js"></script>