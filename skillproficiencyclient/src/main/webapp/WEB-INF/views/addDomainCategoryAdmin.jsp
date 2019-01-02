<%@include file="header.jsp"%>

<% String subdomainList=(String)request.getAttribute("subdomainList");
System.out.println(subdomainList);
%>

<div class="configStyle" style="width:100%;">
	<div class="panel panel-danger">
		<div class="panel-body">	
			<div class="col-xs-12">
				<div class="col-xs-12">
					<div class="panel panel-danger">
						<div class="panel-heading text-center">
							<font size="3"><b>Add Subdomain</b></font>
						</div>
						<div class="panel-body">	
							<div class="col-xs-12 text-center" style="padding-bottom:20px;padding-top:20px">
								<!-- <div class="col-xs-1"></div> -->
								<div class="col-xs-4">
									<font size="3"><b>Domain</b></font>
								</div>
								<div class="col-xs-4">
									<font size="3"><b>SubDomain</b></font>
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
									 <input class="form-control" type="text" id="subDomainText" name="subDomain">
								</div>
								<div class="col-xs-4 text-center">
									<input type="button" value="Save" id="saveButton" onclick="saveSubDomain();" class="btn btn-default btn-danger">
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
							<font size="3"><b>Subdomain List</b></font>
						</div>
						<div class="panel-body">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 datatableStyling "
									id="Datatable_SubDomain" style="margin: 30px 0px 100px 0px;">
									<table id="SubDomain_list"
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
										 <c:forEach items="${subdomainList}" var="subdomainList" varStatus="">
										       <tr id="${subdomainList.subDomainID}">
										        <td id=""><input disabled class="form-control" type="text" id="domain${subdomainList.subDomainID}" name="methodology" value="${subdomainList.domainName}"></td>
										         <td id=""><input disabled class="form-control" type="text" id="subDomain${subdomainList.subDomainID}" name="methodology" value="${subdomainList.subDomainName}"></td>
										        <td class="text-center">
										        <input type="button" value="Edit" id="editButton${subdomainList.subDomainID}" onclick="editSubdomain('${subdomainList.subDomainID}');" class="btn btn-default btn-danger"></input>
										        <input type="button" value="Update" style="display:none;" id="updateButton${subdomainList.subDomainID}" onclick="updateSubdomain('${subdomainList.subDomainID}','${subdomainList.domainID}');" class="btn btn-default btn-danger"></input>
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
	var aa = '<%=subdomainList%>';
	var subdomainList= JSON.parse(aa);
   	var count=0;
   	//alert(accreditationList.length)
	var ResultData_SubDomain=[];
	for(var x1 in subdomainList){
		var arr_SUBDOMAIN=[];
		arr_SUBDOMAIN.push(subdomainList[x1].domainName===null?"N/A":subdomainList[x1].domainName);
		arr_SUBDOMAIN.push(subdomainList[x1].subDomainName===null?"N/A":subdomainList[x1].subDomainName);
		arr_SUBDOMAIN.push('<input type="button" name="firstname" class="btn btn-default btn-danger btn-primary btn-sm editSubDomain" domainId="'+subdomainList[x1].domainID+'" subDomainId="'+subdomainList[x1].subDomainID+'"value="Edit" style="border-radius:2px;">');
		
		ResultData_SubDomain.push(arr_SUBDOMAIN);
	}
	
	$('#Datatable_SubDomain').show();
	$('#SubDomain_list').DataTable( {
		
		 	 scrollY:"520px",
	        scrollX:true,
	        //scrollCollapse: true, 
	        "bDestroy": true,
		 data: ResultData_SubDomain,
		
		 columns: [
				{ title: "Domain" },
				{ title: "Subdomain"},
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
<script src="${pageContext.servletContext.contextPath}/resources/js/addDomainCategory.js"></script>