<%@include file="header.jsp"%>
<c:if test="${role == 'RMG HEAD'|| role == 'BU HEAD'|| role == 'PRE SALES'}">
	<div class="col-xs-12">

		<%@include file="userdetail.jsp"%>
	</div>

</c:if>
<div class="col-xs-12">
	<div class="panel panel-danger">
		<div class="panel-heading panel-heading-rmg text-center">
		<!-- 	<span class="">RMG Role Specific Search</span> <span class=pull-right><input
				type="text" class="form-control" id="freeSearch"
				placeholder="Search..."></span> -->
					<span class=""><input
				type="text" class="form-control" id="freeSearch"
				placeholder="Search..."></span>
				
		</div>
		<div class="panel-body searchpopup">
			<div class="col-xs-12 text-center">
				<button class="btn btn-danger" type="button" id="viewAllEmp"
					data-toggle="modal" data-target="#myModal2">View All Attra
					Employee</button>
			</div>
			<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true"
				style="display: none;">
				<div class="modal-dialog modal-dialog1">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">X</button>
							<h4 class="modal-title text-center" id="myModalLabel ">All
								Employees</h4>
						</div>
						<div class="modal-body">
							<div class="panel panel-default gab panel_custom">
								<div class="panel-heading text-center">
									<div class="row">
										<div class="col-lg-12">
											<button class="btn btn-danger pull-right" type="button"
												id="exportToXl1" onclick="location.href='${pageContext.servletContext.contextPath}/exportAllEmployees'">Export to Excel</button>
										</div>
									</div>
								</div>
								<!-- /.panel-heading -->
								<div class="panel-body searchpopup" id="searchPanelId">
									<div id="allEmp_paginate_duplicate" class="dataTables_paginate paging_simple_numbers"></div>
									<div class="dataTable_wrapper">
										<table class="table table-striped table-bordered table-hover"
											id="allEmp">
											<thead>
												<tr>

													<th>Employee Name</th>
													<th>Area of Work</th>
													<th>Current Project</th>
													<th>Primary</th>
													<th>Technology</th>
													<!-- <th>Secondary Skill</th> -->
													<th>Domain</th>
													<th>Methodology</th>
													<th>Tools</th>
													<th>Total Experience</th>
													<th>Accreditation</th>
													<th>Action</th>


												</tr>
											</thead>
											<tbody id="">
												<c:forEach items="${employessList}" var="viewAllEmp">
													<tr>
														<td><c:if test="${empty viewAllEmp.empName }">NA</c:if>${viewAllEmp.empName}</td>
														<td><c:if test="${empty viewAllEmp.areaOfWork }">NA</c:if>${viewAllEmp.areaOfWork}</td>
														<td><c:if test="${empty viewAllEmp.projectName }">NA</c:if>${viewAllEmp.projectName}</td>
														<td><c:if test="${! empty viewAllEmp.primaryTech }">
																<span >Technology:${viewAllEmp.primaryTech}</span>
															</c:if> <c:if test="${! empty viewAllEmp.primaryDomain }">
																<span >Domain:${viewAllEmp.primaryDomain}</span>
															</c:if> <c:if
																test="${empty viewAllEmp.primaryDomain &&  empty viewAllEmp.primaryTech}">NA</c:if>
														</td>
														<td><c:if test="${empty viewAllEmp.technology}">NA</c:if>
															${viewAllEmp.technology}</td>
														<%-- <td>${viewAllEmp.secondarySkill}</td> --%>
														<td><c:if test="${empty viewAllEmp.domainName }">NA</c:if>
															${viewAllEmp.domainName}</td>
														<td><c:if test="${empty viewAllEmp.methodology }">NA</c:if>
															${viewAllEmp.methodology}</td>
														<td><c:if test="${empty viewAllEmp.toolName }">NA</c:if>
															${viewAllEmp.toolName}</td>
														<td>${viewAllEmp.expInyear} Yr(s) &
															${viewAllEmp.expInmonth} Mn(s)</td>
															<td><c:if test="${empty viewAllEmp.accreditation}">NA</c:if>${viewAllEmp.accreditation}</td>
														<td><button class="btn btn-default btn-danger"
																onclick="location.href='<%=request.getContextPath()%>/employeeDetailsById/${viewAllEmp.empId}/${viewAllEmp.empName}/${viewAllEmp.emailId}';">View
																Details</button></td>

													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>

					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<div class="col-xs-12 text-center gab next">
				<b class="or"> OR</b>
			</div>
			<div class="col-xs-12 text-center gab next">
				<div class="col-lg-4 col-xs-4  col-lg-offset-1">
					<span class="star pull-right"><b>Search By Primary *</b></span>
				</div>
				<div class="col-lg-2 col-xs-2">
					<select class="form-control" id="isprimaryRMG">
						<option value="N">Select</option>

						<option value="T">Skill</option>
						<option value="D">Sub Domain</option>

						<option value="T">Technology</option>
						<option value="D">Domain</option>

						<option value="S">Skills</option>
						<option value="D">Sub Domain</option>

					</select>
				</div>
			<!-- 	<div class="col-xs-12 text-center gab next">
					<b class="or"> / OR By Others</b>
				</div> -->
			</div>

			<div class="next gab">&nbsp;</div>
<div id="rmg_hide" class="hidden">
			

			<div class="col-xs-2 col-xs-offset-4">
				<select class="form-control" id="rmgskill" placeholder="Skills">
				</select>
			</div>

			<div class="col-xs-2 ">
				<select class="form-control" id="rmgDomain"
					placeholder="Sub Domain Name" ></select>
			</div>

			



			</div>
			
			
			<!-- <div class="col-xs-2">
<select id="rmgexp" class="form-control">
<option value="0">Total Experience</option>
<option value="0-3">0-3</option>
<option value="3-7">3-7</option>
<option value="7-9">7-9</option>
<option value="9-99">9-99</option>
</select>
<input type="text" class="form-control" placeholder="Experience" id="rmgexp"/>
</div> -->
			<div class="col-xs-2 col-xs-offset-4 gab next">
				<select id="TechExp" class="form-control">
					<option value="0-0">Experience</option>
					<option value="0-3">0-3</option>
					<option value="3-7">3-7</option>
					<option value="7-9">7-9</option>
					<option value="9-99">9-99</option>
				</select>

			</div>
			<div class="col-xs-2  gab ">
				<select id="DomainExp" class="form-control">
					<option value="0-0">Experience</option>
					<option value="0-3">0-3</option>
					<option value="3-7">3-7</option>
					<option value="7-9">7-9</option>
					<option value="9-99">9-99</option>
				</select>

			</div>
			<div class="col-xs-2 col-xs-offset-4 gab next">
				<select id="TechProfi" name="selfAssesment" class="form-control">
					<option value="0">Self Assesement</option>
					<option value="1">1 - Novice</option>
					<option value="2">2 - Learner</option>
					<option value="3">3 - Intermediate</option>
					<option value="4">4 - Master</option>
					<option value="5">5 - Expert</option>
				</select>


			</div>
			<div class="col-xs-2  gab ">
				<select id="DomainProfi" name="selfAssesment" class="form-control">
					<option value="0">Self Assesement</option>
					<option value="1">1 - Novice</option>
					<option value="2">2 - Learner</option>
					<option value="3">3 - Intermediate</option>
					<option value="4">4 - Master</option>
					<option value="5">5 - Expert</option>
				</select>


			</div>
			<div class="col-xs-12 text-center  next">
					<b class="or"> AND / OR</b>
				</div>
				
				<div class="col-xs-2 next gab col-xs-offset-2">
				<select class="form-control" id="rmgMethodology"
					placeholder="Methodology Name" ></select>
			</div>

			<div class="col-xs-2 gab">
				<select class="form-control" id="rmgTOOL" placeholder="Tool Name" ></select>
			</div>
			<div class="col-xs-2  gab">
				<select class="form-control" id="rmgpname"
					placeholder="Project Name">
				</select>
			</div>
			<div class="col-xs-2  gab">
				<select class="form-control" id="rmgAcc" placeholder="Accreditation Name">
													<option value="">Accreditation</option>
				
																<c:forEach items="${allAccreditationList}" var="allAccreditationList">
													<option value="${allAccreditationList.accreditationName}">${allAccreditationList.accreditationName}</option>
				
									</c:forEach>
				
				</select>
			</div> 
			<div class="col-xs-12 text-center  gab">
				<button class="btn btn-danger disabled" id="stbn">Search</button>
			</div>
			
			<!-- <div class="modal fade in" id="showEmpLight" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
				<div class="modal-backdrop fade in" style="height: 813px;"></div>
				<div class="modal-dialog modal-dialog1">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true" id="rmgcls">X</button>
							<h4 class="modal-title text-center errorRMG" id="myModalLabel">Searched
								Result</h4>
						</div>
						<div class="modal-body">
							<div class="panel panel-default gab panel_custom">
								<div class="panel-heading text-center">
									<div class="row">
										<div class="col-lg-12">
											<button class="btn btn-danger pull-right" type="button"
												id="exportToXl">Export to Excel</button>
										</div>
									</div>
								</div>
								/.panel-heading
								<div class="panel-body dd searchpopup">
								</div>
								<div class="modal-body model-body "
									style="padding-bottom: 0px; padding-top: 0px;">
									<div class="panel panel-danger ">
										<div class="panel-body">
											<div
												class="col-lg-12 col-md-12 col-sm-12 col-xs-12 datatableStyling"
												id="Employee_FreeSearch" style="margin: 30px 0px 100px 0px;">
												<table id="FreeSearchList"
													class="display display_table table nowrap table-bordered  table-striped  dt-responsive nowrap text-center"
													width="100%">
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					/.modal-content
				</div>
				/.modal-dialog
			</div> -->
			  <div class="modal fade in" id="showEmpLight" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
				<div class="modal-backdrop fade in" style="height: 813px;"></div>
				<div class="modal-dialog modal-dialog1">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true" id="rmgcls">X</button>
							<h4 class="modal-title text-center errorRMG" id="myModalLabel">Searched
								Result</h4>
						</div>
						<div class="modal-body">
							<div class="panel panel-default gab panel_custom">
								<div class="panel-heading text-center">
									<div class="row">
										<div class="col-lg-12">
											<button class="btn btn-danger pull-right" type="button"
												id="exportToXl">Export to Excel</button>
										</div>
									</div>
								</div>
								/.panel-heading
								<div class="panel-body dd searchpopup">
 								<table class="table table-striped table-bordered table-hover"
										id="skillEmp">
										<thead>
											<tr>
												<th>Employee Name</th>
												<th>Area of Work</th>
												<th>Current Project</th>
												<th>Technology</th>
												<th>Domain</th>
												<th>Methodology</th>
												<th>Tools</th>
												<th>Total Experience</th>
												<th>Accreditation</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody id="skillEmpbody">
										</tbody>
									</table>
								</div>
							</div>
						</div>

					</div>
					/.modal-content
				</div>
				/.modal-dialog
			</div>  
			
			
			
				<div class="modal fade in" id="showEmpLight1" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
				<div class="modal-backdrop fade in" style="height: 813px;"></div>
				<div class="modal-dialog modal-dialog1">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true" id="rmgcls1">X</button>
							<h4 class="modal-title text-center errorRMG" id="myModalLabel">Searched
								Result</h4>
						</div>
						<div class="modal-body">
							<div class="panel panel-default gab panel_custom">
								<div class="panel-heading text-center">
									<div class="row">
										<div class="col-lg-12">
											<button class="btn btn-danger pull-right" type="button"
												id="exportToXl">Export to Excel</button>
										</div>
									</div>
								</div>
								<!-- /.panel-heading -->
								<div class="panel-body dd searchpopup">
									<table class="table table-striped table-bordered table-hover dataTables_paginate paging_simple_numbers"
										id="skillEmp">
										<thead>
											<tr>
												<th>Employee Name</th>
												<th>Area of Work</th>
												<th>Current Project</th>

												<th>Skill</th>
												<th>Sub Domain</th>

												<th>Technology</th>
												<th>Domain</th>

												<th>Skills</th>
												<th>Sub Domain</th>

												<th>Methodology</th>
												<th>Tools</th>
												<th>Accreditation</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody id="skillEmpbodySearch">
										</tbody>
									</table>
								</div>
							</div>
						</div>

					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>

			<div class="col-xs-12">
				<span class="star" id="error">Please provide atleast one
					input</span> <br />
			</div>
		</div>
	</div>




	<div class="col-xs-12 text-center ">
		<span class="star" id="nodataError"><b>No Data found</b> </span> <br />
	</div>











	<!-- /.panel -->
</div>
<!-- /.col-lg-12 -->
</div>
<%@include file="footer.jsp"%>
<%-- <script
	src="${pageContext.servletContext.contextPath}/resources/js/temp.js"></script>
 --%>	
 <link src="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">
 <script href="//code.jquery.com/jquery-1.12.3.js"> </script>
<script href="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/resources/js/myscriptRmgHomecreen.js"></script>