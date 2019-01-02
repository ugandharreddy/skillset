<%@include file="header.jsp"%>

<% String areaofworkList=(String)request.getAttribute("areaofworkList");
System.out.println(areaofworkList);
%>
<div class="configStyle" style="width:100%;">
	<div class="panel panel-danger">
		<div class="panel-body">	
			<div class="col-xs-12">
				<div class="col-xs-12">
					<div class="panel panel-danger">
						<div class="panel-heading text-center">
							<font size="3"><b>Add Area of work</b></font>
						</div>
						<div class="panel-body">	
							<div class="col-xs-12 text-center" style="padding-bottom:20px;padding-top:20px">
								<div class="col-xs-1"></div>
								<div class="col-xs-5 ">
									<font size="3"><b>Area of work Name</b></font>
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
									<input class="form-control" type="text" id="aowText" name="areaOfWork">
								</div>
								<div class="col-xs-5 text-center">
									<input type="button" value="Save" id="saveButton" onclick="saveAOW();" class="btn btn-default btn-danger"></input>
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
							<font size="3"><b>Area of work List</b></font>
						</div>
						<div class="panel-body">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 datatableStyling "
									id="Datatable_Aow" style="margin: 30px 0px 100px 0px;">
									<table id="Aow_list"
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
											<th class="text-center">Area of work Name</th>
											<th class="text-center">Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${areaofworkList}" var="areaofworkList" varStatus="">
										   <tr id="${areaofworkList.areaId}">
											<td id=""><input disabled class="form-control" type="text" id="aow${areaofworkList.areaId}" name="methodology" value="${areaofworkList.areaWork}"></td>
											<td class="text-center">
											<input type="button" value="Edit" id="editButton${areaofworkList.areaId}" onclick="editAow('${areaofworkList.areaId}');" class="btn btn-default btn-danger"></input>
											<input type="button" value="Update" style="display:none;" id="updateButton${areaofworkList.areaId}" onclick="updateAow('${areaofworkList.areaId}');" class="btn btn-default btn-danger"></input>
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
	var aa = '<%=areaofworkList%>';
	var areaofworkList= JSON.parse(aa);
   	var count=0;
   	//alert(accreditationList.length)
	var ResultData_Aow=[];
	for(var x1 in areaofworkList){
		var arr_AOW=[];
		//arr_AOW.push(areaofworkList[x1].areaId===null?"N/A":areaofworkList[x1].areaId);
		arr_AOW.push(areaofworkList[x1].areaWork===null?"N/A":areaofworkList[x1].areaWork);
		arr_AOW.push('<input type="button" name="firstname" class="btn btn-default btn-primary btn-sm btn-danger editAow" areaId="'+areaofworkList[x1].areaId+'" value="Edit" style="border-radius:2px;">');
		
		ResultData_Aow.push(arr_AOW);
	}
	
	$('#Datatable_Aow').show();
	$('#Aow_list').DataTable( {
		
		 	 scrollY:"520px",
	        scrollX:true,
	        "bDestroy": true,
		 data: ResultData_Aow,
		
		 columns: [
				//{ title: "Area of work Id" },
				{ title: "Area of work Name" },
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
    <script src="${pageContext.servletContext.contextPath}/resources/js/addAOW.js"></script>