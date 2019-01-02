<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="header.jsp"%>
<div class="col-xs-12">
	<div>${title}</div>
	<div class="panel panel-danger">
		<div class="panel-heading text-center">
			<b> My Task</b>
		</div>
		<!-- /.panel-heading -->
		<div class="panel-body">
			<!-- Nav tabs -->
			<ul class="nav nav-tabs">
				<c:choose>

					<c:when test="${!empty coreSkills && !empty domain}">
						<li class="active"><a href="#home" data-toggle="tab">Technology
						</a></li>
						<li><a href="#messages" data-toggle="tab">Domain /
								Product</a></li>
					</c:when>

					<c:when test="${!empty coreSkills}">
						<li class="active"><a href="#home" data-toggle="tab">Technology
						</a></li>

					</c:when>
					<c:when test="${!empty domain}">
						<li class="active"><a href="#messages" data-toggle="tab">Domain
								/ Product</a></li>
					</c:when>

					<c:when test="${empty coreSkills && empty domain}">
						<div>Task Completed</div>
					</c:when> 
				</c:choose>

			</ul>



			<!-- Tab panes -->
			<div class="tab-content">
							<c:if test="${!empty coreSkills}">
			
				<div class="tab-pane fade in active tab_pane_cus" id="home">


					<div class="panel panel-default gab">
						<div class="panel-heading">
							Review Assessment - Technology
							<!--<span id="Merror"class="pull-right ">Please fill all the field marked with <span class="star"><b>*</b></span></span>  -->
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">

							<table class="table table_center1 table-bordered table-hover"
								id="tab_logic">
								<thead>
									<tr>
										<th>Is Primary</th>
										<th>Area of work</th>
										<th>Stream</th>
										<th>Skill</th>
										<th class="col-lg-2">Experience</th>
										<th>Emp Assessment</th>
										<th>Emp's Comment</th>
										<th>Review Assessment <span class="star"><b>*</b></span></th>
										<th>Review Comment</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>

									
										<c:forEach items="${coreSkills}" var="coreSkills"
											varStatus="">
											<tr>
											<form:form
												action="${pageContext.request.contextPath}/reviewSkill/${empID}/${coreSkills.proficiencyId}"
												commandName="ProficiencyCoreSkillObject">
												<c:if test="${coreSkills.isPrime =='Y'}">
												<td>Yes</td>
											</c:if>
											<c:if test="${coreSkills.isPrime =='N'}">
												<td>No</td>
											</c:if>
											<td>${coreSkills.aowName}</td>
											<td>${coreSkills.techname}</td>
											<td>
											  <form:input path="technology"  class="form-control"  type="hidden" value='${coreSkills.skillName}'/>${coreSkills.skillName}
											
											<%-- <form:input path="technology" readonly="true" value="${coreSkills.technology}"  class="form-control"></form:input></td> --%>
											<td><div class="col-xs-6 text-center">
											 <form:input path="expYears"  class="form-control"  type="hidden" value='${coreSkills.expYears}'/>${coreSkills.expYears} 
											 Yr(s)</div>
											<div class="col-xs-6 text-center">
											 <form:input path="expMonths"  class="form-control"  type="hidden" value='${coreSkills.expMonths}'/>${coreSkills.expMonths} Mn(s)</div>
											</div>
											
											<%-- <form:input path="expYears" readonly="true" value="${coreSkills.expYears}" class="form-control"></form:input> Years</div>
											<div class="col-xs-6"> <form:input path="expMonths" readonly="true" value="${coreSkills.expMonths}" class="form-control" ></form:input> Months </div>
											 --%>
											</td>
										
											<td>
											 <form:input path="empRating"  class="form-control"  type="hidden" value='${coreSkills.empRating}'/>${coreSkills.empRating}
											<%-- <form:input path="empRating" readonly="true" value="${coreSkills.empRating}" class="form-control"></form:input> --%>
											</td>
													<td>${coreSkills.empComment}</td>
													<td><form:select class="form-control" path="mgrRating" id="techratingSME" onchange="SMEDisabledTech($(this));">
														<form:option value="0">Select</form:option>
														<%-- <form:option value="1">1</form:option>
														<form:option value="2">2</form:option>
														<form:option value="3">3</form:option>
														<form:option value="4">4</form:option>
														<form:option value="5">5</form:option> --%>
														<c:if test="${coreSkills.empRating==1}">
															<form:option value="1">1-Novice</form:option>
														</c:if>
														<c:if test="${coreSkills.empRating ==2 }">
															<form:option value="1">1-Novice</form:option>
															<form:option value="2">2-Learner</form:option>
														</c:if>
														<c:if test="${coreSkills.empRating ==3 }">
														<form:option value="1">1-Novice</form:option>
															<form:option value="2">2-Learner</form:option>
															<form:option value="3">3-Intermediate</form:option>
														</c:if>
														<c:if test="${coreSkills.empRating ==4 }">
														<form:option value="1">1-Novice</form:option>
															<form:option value="2">2-Learner</form:option>
															<form:option value="3">3-Intermediate</form:option>
															<form:option value="4">4-Master</form:option> 
														</c:if>
														<c:if test="${coreSkills.empRating ==5}">
															<form:option value="1">1-Novice</form:option>
															<form:option value="2">2-Learner</form:option>
															<form:option value="3">3-Intermediate</form:option>
															<form:option value="4">4-Master</form:option>
															<form:option value="5">5-Expert</form:option> 
														</c:if>
													</form:select></td>
												<td><form:textarea class="form-control" rows="3"
														path="mgrComment" id="pcomment"></form:textarea></td>
												<td>

													<button class="btn btn-danger disabled" id="errorSME" type="submit">Save</button>
												</td>
											</form:form>
											</tr>
											</c:forEach>
									

								</tbody>
							</table>

						</div>
						<!-- /.panel-body -->
					</div>


				</div>
				</c:if>
			<c:if test="${!empty domain}">
			
				<c:if test="${!empty coreSkills}"> <div class="tab-pane fade tab_pane_cus" id="messages"> </c:if>
				<c:if test="${empty coreSkills}"><div class="tab-pane fade in active tab_pane_cus" id="messages"></c:if>
								
				
					<div class="panel panel-default gab">
						<div class="panel-heading">
							Review Assessment - Domain / Product
							<!--<span id="Terror"class="pull-right ">Please fill all the field marked with <span class="star"><b>*</b></span></span>  -->
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">

							<table class="table table-bordered table-hover" id="tab_logic">
								<thead>
									<tr>
										<th>Is Primary</th>
										<th>Domain</th>
										<th>Sub Domain</th>
										<th>Experience</th>
										<th>Emp Assessment</th>
										<th>Emp's Comment</th>
										<th>Review Assessment <span class="star"><b>*</b></span></th>
										<th>Review Comments</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>

									<c:forEach items="${domain}" var="domain" varStatus="countIndex">
									<tr id="${countIndex.index}">
										 <form:form
											action="${pageContext.request.contextPath}/reviewDomain/${empID}/${domain.domain}/${domain.proficiencyId}"
											commandName="ProficiencyDomainObject"> 
<c:if test="${domain.isPrime =='Y'}">
														<td>Yes</td>
													</c:if>
													<c:if test="${domain.isPrime =='N'}">
														<td>No</td>
													</c:if>	
											
												<td>
												<%-- <form:input path="dName" readonly="true" value="${domain.dName}" class="form-control"></form:input> --%>
													 <form:input path="sGroup"  class="form-control"  type="hidden" value='${domain.domainCategory}'/>${domain.domainCategory}
												
												</td>
												<td>
												<%-- <form:input path="sGroup" readonly="true" value="${domain.sGroup}" class="form-control"></form:input> --%>
												 <form:input path="dName"  class="form-control"  type="hidden" value='${domain.domain}'/>${domain.domain}
												</td>
												<td>
												<div class="col-xs-6 text-center">
											 <form:input path="dExpYears"  class="form-control"  type="hidden" value='${domain.dExpYears}'/>${domain.dExpYears} Yr(s)</div>
											<div class="col-xs-6 text-center">
											 <form:input path="dExpMonths"  class="form-control"  type="hidden" value='${domain.dExpMonths}'/>${domain.dExpMonths} Mn(s)</div>
											</div>
												
												<%-- <div class="col-xs-6"><form:input path="dExpYears" readonly="true" value="${domain.dExpYears}" class="form-control"></form:input> Years</div>
												<div class="col-xs-6"><form:input path="dExpMonths" readonly="true" value="${domain.dExpMonths}" class="form-control"></form:input> Months</div>
												 --%>
												 </td>
												<td>
												 <form:input path="empRating"  class="form-control"  type="hidden" value='${domain.empRating}'/>${domain.empRating}
												<%-- <form:input path="empRating" readonly="true" value="${domain.empRating}" class="form-control"></form:input> --%>
												</td>
												<td>${domain.empComment}</td>
												<td><form:select class="form-control" path="mgrRating"
														id="domainRatingSME"
														onchange="SMEDisabledDomain($(this));">
														<form:option value="0">Select</form:option>
														<%-- 	<form:option value="2">2</form:option>
														<form:option value="3">3</form:option>
														<form:option value="4">4</form:option>
														<form:option value="5">5</form:option> --%>
														<c:if test="${domain.empRating == 1}">
															<form:option value="1">1-Novice</form:option>
														</c:if>
														<c:if
															test="${domain.empRating == 2}">
															<form:option value="1">1-Novice</form:option>
															<form:option value="2">2-Learner</form:option>
														</c:if>
														<c:if
															test="${domain.empRating == 3}">
															<form:option value="1">1-Novice</form:option>
															<form:option value="2">2-Learner</form:option>
															<form:option value="3">3-Intermediate</form:option>
														</c:if>
														<c:if
															test="${domain.empRating == 4}">
															<form:option value="1">1-Novice</form:option>
															<form:option value="2">2-Learner</form:option>
															<form:option value="3">3-Intermediate</form:option>
															<form:option value="4">4-Master</form:option>
															</c:if>
															<c:if test="${domain.empRating == 5}">
																<form:option value="1">1-Novice</form:option>
																<form:option value="2">2-Learner</form:option>
																<form:option value="3">3-Intermediate</form:option>
																<form:option value="4">4-Master</form:option>
																<form:option value="5">5-Expert</form:option>
															</c:if>
													</form:select></td>

												<td><form:textarea class="form-control" rows="3"
														id="pcomment" path="mgrComment"></form:textarea></td>
												<td>

													<button class="btn btn-danger disabled" id="errorSME1" type="submit">Save</button>
												</td>
											
										</form:form>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.panel-body -->
					</div>
				</div>
				</c:if>


			</div>
		</div>
		<!-- /.panel-body -->
	</div>
</div>
<%@include file="footer.jsp"%>
<script type="text/javascript">

function SMEDisabledTech(row){
	
	var Experience = row.closest('tr').find('select[id=techratingSME]').val();
	 
	 if((Experience=="0")){


		 row.closest('tr').find('button[id=errorSME]').addClass("disabled");

	}
	else{
		 row.closest('tr').find('button[id=errorSME]').removeClass("disabled");
	}  
}	

function SMEDisabledDomain(row){
	
	var Experience = row.closest('tr').find('select[id=domainRatingSME]').val();
	 
	 if((Experience=="0")){


		 row.closest('tr').find('button[id=errorSME1]').addClass("disabled");

	}
	else{
		 row.closest('tr').find('button[id=errorSME1]').removeClass("disabled");
	}  
}

</script>