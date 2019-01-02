<%@include file="header.jsp"%>

<% String domainList=(String)request.getAttribute("domainList");
System.out.println(domainList);
%>
<style>
#uolId{
	border: 1px solid #999;
	width: 100%;
	padding-left: 10px;
	z-index: 9999 !important;
	position: static;
	background-color: #ccc;
	max-height: 200px;
	color: #111;
	overflow: auto;
	padding-right:10px;
	border-radius:1px;
}

#uolId li:hover {
	background-color:#ccc;
	width:320px;
	text-color:white;
}
</style>
<div class="configStyle" style="width:100%;">
	<div class="panel panel-danger">
		<div class="panel-body">	
			<div class="col-xs-12">
				<div class="col-xs-12">
					<div class="panel panel-danger">
						<div class="panel-heading text-center">
							<font size="3"><b>Add Domain</b></font>
						</div>
						<div class="panel-body">	
							<div class="col-xs-12 text-center" style="padding-bottom:20px;padding-top:20px">
								<div class="col-xs-1"></div>
								<div class="col-xs-3">
									<font size="3"><b>Domain Name</b></font>
								</div>
								<div class="col-xs-4">
									<font size="3"><b>Practice Lead</b></font>
								</div>
								<div class="col-xs-3">
									<font size="3"><b>Action</b></font>
								</div>
								<div class="col-xs-1"></div>
							</div>
							<form autocomplete="off">
							<div class="col-xs-12">
								<div class="col-xs-1"></div>
								<div class="col-xs-3">
									<input class="form-control" type="text" id="domainText" name="domain">
								</div>
								<div class="col-xs-4">
									 <input type="text" id="plEmailText" name="EmpName" class="form-control" placeholder="Search Name">
										<ul id="uol" style="display:none" class="dropdown-menu"></ul>
								</div>
								<div class="col-xs-3 text-center">
									<input type="button" value="Save" id="saveButton" onclick="saveDomain();" class="btn btn-default btn-danger"></input>
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
							<font size="3"><b>Domain List</b></font>
						</div>
						<div class="panel-body">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 datatableStyling "
									id="Datatable_Domain" style="margin: 30px 0px 100px 0px;">
									<table id="Domain_list"
										class="display display_table table nowrap table-bordered  table-striped  dt-responsive" style="width:100%;">
								</table>
							</div>
						</div>
	<%-- 					<div class="panel-body">
							<div  class="dataTables_paginate paging_simple_numbers">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover dataTable" id="example">
									<thead>
										<tr>
											<th class="text-center">Domain Name</th>
											<th class="text-center">PL EmailId</th>
											<th class="text-center">Action</th>
										</tr>
									</thead>
									<tbody>
										 <c:forEach items="${domainList}" var="domainList" varStatus="">
										      <tr id="${domainList.categoryId}">
											        <td id=""><input disabled class="form-control" type="text" id="domain${domainList.categoryId}" name="methodology" value="${domainList.categoryName}"></td>
											        <td id=""><input disabled class="form-control" type="text" id="plEmail${domainList.categoryId}" name="methodology" value="${domainList.plEmail}"></td>
											        <td>
											         <td id=""><input disabled class="form-control" type="text" id="plemail${domainList.categoryId}" name="methodology" oninput="search('${domainList.categoryId}');" 
											         value="${domainList.plEmail}"><ul id="uol" class="Sdrop${domainList.categoryId}" style="display:none"></ul></td>
											        <td class="text-center">
											        <input type="button" value="Edit" id="editButton${domainList.categoryId}" onclick="editDomain('${domainList.categoryId}');" class="btn btn-default btn-danger"></input>
											        <input type="button" value="Update" style="display:none;" id="updateButton${domainList.categoryId}" onclick="updateDomain('${domainList.categoryId}');" class="btn btn-default btn-danger"></input>
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
	var aa = '<%=domainList%>';
	var domainList= JSON.parse(aa);
   	var count=0;
   	//alert(accreditationList.length)
	var ResultData_Domain=[];
	for(var x1 in domainList){
		var arr_DOMAIN=[];
		arr_DOMAIN.push(domainList[x1].categoryName===null?"N/A":domainList[x1].categoryName);
		arr_DOMAIN.push(domainList[x1].plEmail===null?"N/A":domainList[x1].plEmail);
		arr_DOMAIN.push('<input type="button" name="firstname" class="btn btn-default btn-danger btn-primary btn-sm editDomain" domainId="'+domainList[x1].categoryId+'" value="Edit" style="border-radius:2px;">');
		
		ResultData_Domain.push(arr_DOMAIN);
	}
	
	$('#Datatable_Domain').show();
	$('#Domain_list').DataTable( {
		
		 	 scrollY:"520px",
	        scrollX:true,
	        //scrollCollapse: true, 
	        "bDestroy": true,
		 data: ResultData_Domain,
		
		 columns: [
				{ title: "Domain" },
				{ title: "Practice Lead"},
				{ title: "Action"},
			]
	});$(".dataTables_scrollBody th").removeAttr('class');
	$(".dataTables_scrollHeadInner th").removeAttr('class');
});

</script>
<%@include file="footer.jsp"%>
<%-- 
   <script src="${pageContext.servletContext.contextPath}/resources/js/jquery.min.js"></script>
 <script src="${pageContext.servletContext.contextPath}/resources/js/jquery-3.1.1.min.js"></script> 
  <script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script> --%>
   <script src="${pageContext.servletContext.contextPath}/resources/js/jquery.validate.min.js"></script> 
<script src="${pageContext.servletContext.contextPath}/resources/js/addDomain.js"></script> 