<%@include file="header.jsp"%>

<% String streamList=(String)request.getAttribute("streamList");
System.out.println(streamList);
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
							<font size="3"><b>Add Stream</b></font>
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
									<font size="3"><b>Practice Lead</b></font>
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
									<input class="form-control" type="text" id="streamText" name="stream">
								</div>
								<div class="col-xs-3 ">
									 <input type="text" id="plEmailText" name="EmpName" class="form-control" placeholder="Search Name">
										<ul id="uol" style="display:none"></ul></td>
								</div>
								<div class="col-xs-3 text-center">
									<input type="button" value="Save" id="saveButton" onclick="saveStream();" class="btn btn-default btn-danger">
								</div>
							</div>
						</div>
					</div>
				</div> 
			<!-- </div> -->
				<div class="col-xs-12">
					<div class="panel panel-danger ">
						<div class="panel-heading text-center">
							<font size="3"><b>Stream List</b></font>
						</div>
						<div class="panel-body">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 datatableStyling "
									id="Datatable_Technology" style="margin: 30px 0px 100px 0px;">
									<table id="Technology_list"
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
											<th class="text-center">PL EmailId</th>
											<th class="text-center">Action</th>
										</tr>
									</thead>
									<tbody>
										  <c:forEach items="${streamList}" var="streamList" varStatus="">
										       <tr id="${streamList.aowID}">
										        <td id=""><input disabled class="form-control" type="text" id="aow${streamList.aowID}" name="methodology" value="${streamList.aowName}"></td>
										        <td id=""><input disabled class="form-control" type="text" id="stream${streamList.techID}" name="methodology" value="${streamList.techName}"></td>
										        <td id=""><input disabled class="form-control" type="text" id="plemail${streamList.techID}" name="methodology" oninput="search('${streamList.techID}');" 
										         value="${streamList.plEmailId}"><ul id="uol" class="Sdrop${streamList.techID}" style="display:none"></ul></td>
										        <td class="text-center">
										        <input type="button" value="Edit" id="editButton${streamList.techID}" onclick="editStream('${streamList.techID}');" class="btn btn-default btn-danger"></input>
										        <input type="button" value="Update" style="display:none;" id="updateButton${streamList.techID}" onclick="updateStream('${streamList.aowID}','${streamList.techID}');" class="btn btn-default btn-danger"></input>
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
	var aa = '<%=streamList%>';
	var streamList= JSON.parse(aa);
   	var count=0;
   	//alert(accreditationList.length)
	var ResultData_Technology=[];
	for(var x1 in streamList){
		var arr_TECH=[];
		arr_TECH.push(streamList[x1].aowName===null?"N/A":streamList[x1].aowName)
		arr_TECH.push(streamList[x1].techName===null?"N/A":streamList[x1].techName);
		arr_TECH.push(streamList[x1].plEmailId===null?"N/A":streamList[x1].plEmailId);
		arr_TECH.push('<input type="button" name="firstname" class="btn btn-default btn-danger btn-primary btn-sm editTech" aowId="'+streamList[x1].aowID+'" techId="'+streamList[x1].techID+'" value="Edit" style="border-radius:2px;">');
		
		ResultData_Technology.push(arr_TECH);
	}
	
	$('#Datatable_Technology').show();
	$('#Technology_list').DataTable( {
		
		 	 scrollY:"520px",
	        scrollX:true,
	        //scrollCollapse: true, 
	        "bDestroy": true,
		 data: ResultData_Technology,
		
		 columns: [
				{ title: "Area of work" },
				{ title: "Stream" },
				{ title: "Practice Lead"},
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
<script src="${pageContext.servletContext.contextPath}/resources/js/addTechnology.js"></script>