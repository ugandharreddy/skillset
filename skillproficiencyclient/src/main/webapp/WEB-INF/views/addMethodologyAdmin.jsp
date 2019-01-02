<%@include file="header.jsp"%>

<% String methodologyList=(String)request.getAttribute("methodologyList");
System.out.println(methodologyList);
%>

<div class="configStyle" style="width:100%;">
	<div class="panel panel-danger">
		<div class="panel-body">	
			<div class="col-xs-12">
				<div class="col-xs-12">
					<div class="panel panel-danger">
						<div class="panel-heading text-center">
							<font size="3"><b>Add Methodology</b></font>
						</div>
						<div class="panel-body">	
							<div class="col-xs-12 text-center" style="padding-bottom:20px;padding-top:20px">
								<div class="col-xs-1"></div>
								<div class="col-xs-5 ">
									<font size="3"><b>Methodology Name</b></font>
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
									<input class="form-control" type="text" id="methodologyText" name="methodology">
								</div>
								<div class="col-xs-5 text-center">
									<input type="button" value="Save" id="saveButton" onclick="saveMethodology();" class="btn btn-default btn-danger"></input>
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
							<font size="3"><b>Methodology List</b></font>
						</div>
						<div class="panel-body">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 datatableStyling "
									id="Datatable_Methodology" style="margin: 30px 0px 100px 0px;">
									<table id="Methodology_list"
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
											<th class="text-center">Methodology Name</th>
											<th class="text-center">Action</th>
										</tr>
									</thead>
									<tbody>
										 <c:forEach items="${methodologyList}" var="methodologyList" varStatus="">
									       <tr id="${methodologyList.methodologyId}">
									        <td id=""><input disabled class="form-control" type="text" id="method${methodologyList.methodologyId}" name="methodology" value="${methodologyList.methodologyName}"></td>
									        <td class="text-center">
									        <input type="button" value="Edit" id="editButton${methodologyList.methodologyId}" onclick="editMethodology('${methodologyList.methodologyId}');" class="btn btn-default btn-danger"></input>
									        <input type="button" value="Update" style="display:none;" id="updateButton${methodologyList.methodologyId}" onclick="updateMethodology('${methodologyList.methodologyId}');" class="btn btn-default btn-danger"></input>
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
	var aa = '<%=methodologyList%>';
	var methodologyList= JSON.parse(aa);
   	var count=0;
   	//alert(accreditationList.length)
	var ResultData_Methodology=[];
	for(var x1 in methodologyList){
		var arr_METH=[];
		arr_METH.push(methodologyList[x1].methodologyName===null?"N/A":methodologyList[x1].methodologyName);
		//arr_AOW.push(methodologyList[x1].areaWork===null?"N/A":methodologyList[x1].methodologyList);
		arr_METH.push('<input type="button" name="firstname" class="btn btn-default btn-danger btn-primary btn-sm editmeth" methID="'+methodologyList[x1].methodologyId+'" value="Edit" style="border-radius:2px;">');
		
		ResultData_Methodology.push(arr_METH);
	}
	
	$('#Datatable_Methodology').show();
	$('#Methodology_list').DataTable( {
		
		 	 scrollY:"520px",
	        scrollX:true,
	       // scrollCollapse: true, 
	        "bDestroy": true,
		 data: ResultData_Methodology,
		
		 columns: [
				//{ title: "Area of work Id" },
				{ title: "Methodology Name" },
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
<script src="${pageContext.servletContext.contextPath}/resources/js/addMethodology.js"></script>
