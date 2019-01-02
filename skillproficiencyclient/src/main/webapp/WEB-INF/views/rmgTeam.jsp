<%@include file="header.jsp"%>

<div class="container">
	<div>${msg}</div>
	<div class="col-lg-12">
		<div class="panel panel-danger">
			<div class="panel-heading text-center">
				<b>Employee Detail</b>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="dataTable_wrapper">

					<table class="table table_center1 table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>

								<th>Profile Picture</th>
								<th>Person Details</th>
								<th>Action</th>


							</tr>
						</thead>
						<tbody>
							<c:forEach items="${employessList}" var="employessList">


								<tr class="gradeA odd" role="row">
									<td class="sorting_1"><img
										src="${pageContext.servletContext.contextPath}/resources/images/profile.jpg"
										class="img-responsive center-block" width="150" height="150"></td>
									<td>
										<p class="list-group-item-text">
										<div class="row next gab">
											<div class="col-lg-3">
												<label>Name</label>

											</div>
											<div class="col-lg-3">
												<span>${employessList.empName}</span>
											</div>
										</div>

										<div class="row next gab">
											<div class="col-lg-3">
												<label>Technology</label>

											</div>
											<div class="col-lg-3">
												<span>${employessList.technologyName}<c:if test="${techPrimaryCheck =='Y'}">(Primary)</c:if></span>
												
											</div>
											<div class="col-lg-3 ">
												<label>Exp</label>

											</div>
											<div class="col-lg-3">
												<span>${employessList.tExpYears}Yr(s) ${employessList.tExpMonths}Mn(s)</span>
											</div>
										</div>

										<div class="row next gab">
											<div class="col-lg-3 ">
												<label>Domain</label>

											</div>
											<div class="col-lg-3">
												<span>${employessList.domain}<c:if test="${domainPrimaryCheck =='Y'}">(Primary)</c:if></span>
											</div>
											<div class="col-lg-3 ">
												<label>Exp</label>

											</div>
											<div class="col-lg-3">
												<span>${employessList.dExpYears}Yr(s) ${employessList.dExpMonths}Mn(s)</span>
											</div>
										</div>

										</p>
									</td>
									<td>
										<div class="text-center gab_more">
											<button class="btn btn-default btn-danger"
												onclick="location.href='<%=request.getContextPath()%>/employeeDetailsById/${employessList.empID}/${employessList.empName}/${employessList.emailID}';">View
												Details</button>

										</div>
												<%-- 	<c:set var="role" scope="session" value="<%=uname%>" /> --%>
										
							<%-- 	<c:if test="${role == 'BENCH_HEAD'}">
										
											<button class="btn btn-default btn-danger gab_right"
												data-toggle="modal" data-target="#myModal" href="#"
												id="${employessList.empID}">Deploy</button>
											<!-- 	<a data-toggle="modal" data-target="#myModal"  href="#">
															<img src="images/profile_lg.jpg" class="img-thumbnail img-responsive center-block pull-right" width="93" height="93">
															</a> -->
											<div class="modal fade in" id="myModal" tabindex="-1"
												role="dialog" aria-labelledby="myModalLabel"
												aria-hidden="true" style="display: none;">
												<div class="modal-dialog">
													<div class="modal-content">
														<div class="modal-header modal_custom">
															<button type="button" class="close " data-dismiss="modal">
																<span class="attra_color">×</span>
															</button>
															<div class="text-center">
																<b>Deploy To Project </b>
															</div>
														</div>
														<div class="modal-body">
															<div class="row">
																<div class="panel panel-default">
																	<!--    <div class="panel-heading">
       Table - Business Unit <div class="pull-right"></div>
         </div> -->
																	<!-- /.panel-heading -->
																	<div class="panel-body">
																		<div class="dataTable_wrapper">

																			<table
																				class="table table_center1 table-striped table-bordered table-hover"
																				id="dataTables-example1">
																				<thead>
																					<tr>

																						<th>Project Name</th>
																						<th>Project Manager</th>
																						<th>Action</th>

																					</tr>
																				</thead>
																				<tbody>
																					<c:forEach items="${projectList}" var="projectList">

																						<tr class="gradeA odd" role="row">
																							<td class="sorting_1">${projectList.projectName}</td>
																							<td class="sorting_1">${projectList.managerMailID}</td>
																																												<td class="sorting_1"><button class="btn btn-default btn-primary gab_right" onclick="location.href='<%=request.getContextPath()%>/deployEmployee/${projectList.projectID}/${employessList.empID};">deploy</button></td>

																							<td class="sorting_1"><button
																									class="btn btn-default btn-danger gab_right"
																									id="${projectList.projectID}/${employessList.empID}"
																									onclick="deploy(this.id);">Deploy</button></td>
																						</tr>
																					</c:forEach>

																				</tbody>
																			</table>
																		</div>
																		<!-- /.table-responsive -->

																	</div>
																	<!-- /.panel-body -->
																</div>

															</div>

														</div>
														<div class="modal-footer"></div>
													</div>
													<!-- /.modal-content -->
												</div>
												<!-- /.modal-dialog -->
											</div>


</c:if>

 --%>



										
									</td>

								</tr>
							</c:forEach>


						</tbody>
					</table>
				</div>
				<!-- /.table-responsive -->

			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>


<script type="text/javascript">

function deploy(deployId)
{
	
	location.href="${pageContext.servletContext.contextPath}/deployEmployee/"+deployId;

}
</script>

<%@include file="footer.jsp"%>
