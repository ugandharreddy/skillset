<%@include file="header.jsp"%>

<% String skillList=(String)request.getAttribute("skillList");
System.out.println(skillList);
%>

<div class="configStyle" style="width:100%;">
	<div class="panel panel-danger">
		<div class="panel-body">	
			<div class="col-xs-12">
				<div class="col-xs-12">
					<div class="panel panel-danger">
						<div class="panel-heading text-center">
							<font size="3"><b>Add Skill</b></font>
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
									<font size="3"><b>Skill</b></font>
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
									</select>
								</div>
								<div class="col-xs-3 ">
									<input class="form-control" type="text" id="skillText" name="skill">
								</div>
								<div class="col-xs-3 text-center">
									<input type="button" value="Save" id="saveButton" onclick="saveSkill();" class="btn btn-default btn-danger"></input>
								</div>
							</div>
						</div>
					</div>
				</div> 
			<!-- </div> -->
				<div class="col-xs-12">
					<div class="panel panel-danger ">
						<div class="panel-heading text-center">
							<font size="3"><b>Skill List</b></font>
						</div>
							<div class="panel-body">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 datatableStyling "
									id="Datatable_Skill" style="margin: 30px 0px 100px 0px;">
									<table id="Skill_list"
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
										  <c:forEach items="${skillList}" var="skillList" varStatus="">
										       <tr id="${skillList.skillID}">
										        <td id=""><input disabled class="form-control" type="text" id="aow${skillList.aowID}" name="aow" value="${skillList.aowName}"></td>
										        <td id=""><input disabled class="form-control" type="text" id="stream${skillList.techID}" name="stream" value="${skillList.techName}"></td>
										        <td id=""><input disabled class="form-control" type="text" id="skill${skillList.skillID}" name=skill" value="${skillList.skillName}"></td>
										        <td class="text-center">
										        <input type="button" value="Edit" id="editButton${skillList.skillID}" onclick="editSkill('${skillList.skillID}');" class="btn btn-default btn-danger"></input>
										        <input type="button" value="Update" style="display:none;" id="updateButton${skillList.skillID}" onclick="updateSkill('${skillList.techID}','${skillList.skillID}');" class="btn btn-default btn-danger"></input>
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
	var aa = '<%=skillList%>';
	var skillList= JSON.parse(aa);
   	var count=0;
   	//alert(accreditationList.length)
	var ResultData_Skill=[];
	for(var x1 in skillList){
		var arr_SKILL=[];
		arr_SKILL.push(skillList[x1].aowName===null?"N/A":skillList[x1].aowName);
		arr_SKILL.push(skillList[x1].techName===null?"N/A":skillList[x1].techName);
		arr_SKILL.push(skillList[x1].skillName===null?"N/A":skillList[x1].skillName);
		arr_SKILL.push('<input type="button" name="firstname" class="btn btn-default btn-danger btn-primary btn-sm editSkill" techId="'+skillList[x1].techID+'" skillId="'+skillList[x1].skillID+'" value="Edit" style="border-radius:2px;">');
		
		ResultData_Skill.push(arr_SKILL);
	}
	
	$('#Datatable_Skill').show();
	$('#Skill_list').DataTable( {
		
		 	 scrollY:"520px",
	        scrollX:true,
	      //  scrollCollapse: true, 
	        "bDestroy": true,
		 data: ResultData_Skill,
		
		 columns: [
				{ title: "Area of Work" },
				{ title: "Stream"},
				{ title: "Skill"},
				{ title: "Action"}
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
<script src="${pageContext.servletContext.contextPath}/resources/js/addSkill.js"></script>