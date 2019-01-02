<%@include file="header.jsp"%>

<% String smeList=(String)request.getAttribute("smeList");
System.out.println(smeList);
%>

<div class="configStyle" style="width:100%;">
	<div class="panel panel-danger">
		<div class="panel-body">	
			<div class="col-xs-12">
				<div class="col-xs-12">
					<div class="panel panel-danger">
						<div class="panel-heading text-center">
							<font size="3"><b>Assign SME for Domain</b></font>
						</div>
						<div class="panel-body">	
							<div class="col-xs-12 text-center" style="padding-bottom:20px;padding-top:20px">
								<!-- <div class="col-xs-1"></div> -->
								<div class="col-xs-4">
									<font size="3"><b>Domain</b></font>
								</div>
								<div class="col-xs-4">
									<font size="3"><b>SME EmailId</b></font>
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
									<select class="form-control" id="Ddomain">
										<option>Select</option>
										<c:forEach items="${domainList}" var="domainList"
											varStatus="">
											<option value="${domainList.categoryId}">${domainList.categoryName}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-xs-4">
									  <input type="text" id="smeText" name="EmpName" class="form-control" placeholder="Search Name">
											<ul id="uol" style="display:none"></ul>
								</div>
								<div class="col-xs-4 text-center">
									<input type="button" value="Add" id="addButton" onclick="addSmeForDomain();" class="btn btn-default btn-danger"></input>
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
							<font size="3"><b>Domain SME List</b></font>
						</div>
						<div class="panel-body">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 datatableStyling "
									id="Datatable_SMEDomain" style="margin: 30px 0px 100px 0px;">
									<table id="SMEDomain_list"
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
										   <c:forEach items="${smeList}" var="smeList"  varStatus="">
										       <tr>
											      	<td id="${smeList.domainID}">${smeList.domainName}</td>
													<td id="${smeList.domainSmeEmail}">${smeList.domainSmeEmail}</td>
											        <td><input type="button" value="Delete" id="deleteButton" class="btn btn-default btn-danger"
											         	onclick="deleteSme('${smeList.domainSmeEmail}','${smeList.domainID}');" 	data-toggle="modal" data-target="#deleteModal"></input></td>
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
	var aa = '<%=smeList%>';
	var smeList= JSON.parse(aa);
   	var count=0;
   	//alert(accreditationList.length)
	var ResultData_SMETech=[];
	for(var x1 in smeList){
		var arr_TSME=[];
		arr_TSME.push(smeList[x1].domainName===null?"N/A":smeList[x1].domainName);
		arr_TSME.push(smeList[x1].domainSmeEmail===null?"N/A":smeList[x1].domainSmeEmail);
		arr_TSME.push('<input type="button" name="firstname" class="btn btn-default btn-danger btn-primary btn-sm deleteSmeDomain" domainId="'+smeList[x1].domainID+'" smeId="'+smeList[x1].domainSmeEmail+'" value="Delete" style="border-radius:2px;">');
		
		ResultData_SMETech.push(arr_TSME);
	}
	
	$('#Datatable_SMEDomain').show();
	$('#SMEDomain_list').DataTable( {
		
		 	 scrollY:"520px",
	        scrollX:true,
	       // scrollCollapse: true, 
	        "bDestroy": true,
		 data: ResultData_SMETech,
		
		 columns: [
				{ title: "Domain"},
				{ title: "SME EmailId"},
				{ title: "Action"}
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
<script src="${pageContext.servletContext.contextPath}/resources/js/AssignSMEforDomain.js"></script>