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
							<font size="3"><b>Assign SME for Technology</b></font>
						</div>
						<div class="panel-body">	
							<div class="col-xs-12 text-center" style="padding-bottom:20px;padding-top:20px">
								<div class="col-xs-3">
									<font size="3"><b>Area of work</b></font>
								</div>
								<div class="col-xs-3 ">
									<font size="3"><b>Stream</b></font>
								</div>
								<div class="col-xs-3">
									<font size="3"><b>SME EmailId</b></font>
								</div>
								<div class="col-xs-3">
									<font size="3"><b>Action</b></font>
								</div>
							</div>
							<div class="col-xs-12">
								<div class="col-xs-3 ">
									<select class="form-control" id="Aaow">
										<option>Select</option>
										<c:forEach items="${areaofworkList}" var="areaofworkList"
											varStatus="">
											<option value="${areaofworkList.areaId}">${areaofworkList.areaWork}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-xs-3 ">
									<select class="form-control" id="Sstream">
										<c:forEach items="${streamsList}" var="streamsList"
												varStatus="">
												<option value="${streamsList.streamId}">${streamsList.streamName}</option>
											</c:forEach>
										</select>
								</div>
								<div class="col-xs-3 ">
									  <input type="text" id="smeText" name="EmpName" class="form-control" placeholder="Search Name">
										<ul id="uol" style="display:none"></ul>
								</div>
								<div class="col-xs-3 text-center">
									<input type="button" value="Add" id="addButton" onclick="addSmeForTech();" class="btn btn-default btn-danger"></input>
								</div>
							</div>
						</div>
					</div>
				</div> 
			<!-- </div> -->
				<div class="col-xs-12">
					<div class="panel panel-danger ">
						<div class="panel-heading text-center">
							<font size="3"><b>Technology SME List</b></font>
						</div>
							<div class="panel-body">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 datatableStyling "
									id="Datatable_SMETech" style="margin: 30px 0px 100px 0px;">
									<table id="SMETech_list"
										class="display display_table table nowrap table-bordered  table-striped  dt-responsive" style="width:100%;">
								</table>
							</div>
						</div>
					<%-- 	<div class="panel-body">
							<div>
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover dataTable">
									<thead>
										<tr>
											<th class="text-center">Area of work</th>
											<th class="text-center">Stream</th>
											<th class="text-center">Skill</th>
											<th class="text-center">Action</th>
										</tr>
									</thead>
									<tbody>
									 <c:forEach items="${smeList}" var="smeList"  varStatus="">
									       <tr>
										      	<td id="${smeList.techID}">${smeList.techName}</td>
												<td id="${smeList.techSmeEmail}">${smeList.techSmeEmail}</td>
										        <td><input type="button" value="Delete" id="deleteButton" class="btn btn-default btn-danger"
										         	onclick="deleteSme('${smeList.techSmeEmail}','${smeList.techID}');" 	data-toggle="modal" data-target="#deleteModal"></input></td>
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
		arr_TSME.push(smeList[x1].techName===null?"N/A":smeList[x1].techName);
		arr_TSME.push(smeList[x1].techSme===null?"N/A":smeList[x1].techSme);
		arr_TSME.push('<input type="button" name="firstname" class="btn btn-default btn-danger btn-primary btn-sm deleteSmeTech" techId="'+smeList[x1].techID+'" smeId="'+smeList[x1].techSmeEmail+'" value="Delete" style="border-radius:2px;">');
		
		ResultData_SMETech.push(arr_TSME);
	}
	
	$('#Datatable_SMETech').show();
	$('#SMETech_list').DataTable( {
		
		 	 scrollY:"520px",
	        scrollX:true,
	        //scrollCollapse: true, 
	        "bDestroy": true,
		 data: ResultData_SMETech,
		
		 columns: [
				{ title: "Stream"},
				{ title: "SME EmailId"},
				{ title: "Action"}
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
<script src="${pageContext.servletContext.contextPath}/resources/js/AssignSMEforTechnology.js"></script>