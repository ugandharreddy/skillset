
<%@include file="header.jsp"%>

<div class="col-xs-12">

	<div class="modal fade in background-popup " id="messageModel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close closed" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">

					<div class="panel panel-default">
						<div class="panel-heading text-center">
							<b>Assign to SME</b>
						</div>
						<div class="panel-body" id="messageModelLabel"></div>
						<!-- /.panel-heading -->
					</div>
					<!-- /.panel -->

				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

	<div>${title}</div>
	<div class="panel panel-danger">
		<div class="panel-heading text-center">
			<b> Reviews Requested By Managers</b>
		</div>
		<!-- /.panel-heading -->
		<div class="panel-body">
			<!-- Nav tabs -->
			<div id="completed" style="display: none;">Task Completed</div>
			<div id="successMsg" style="display: none; color:green;"></div>
			<div id="failureMsg" style="display: none; color:green;"></div>

			<ul class="nav nav-tabs">
				<c:choose>

					<c:when test="${!empty coreSkills && !empty domain}">
						<li id="techTab" class="active"><a href="#home"
							data-toggle="tab">Technology </a></li>
						<li id="domainTab"><a href="#messages" data-toggle="tab">Domain
								/ Product</a></li>
					</c:when>

					<c:when test="${!empty coreSkills}">
						<li id="techTab" class="active"><a href="#home"
							data-toggle="tab">Technology </a></li>

					</c:when>
					<c:when test="${!empty domain}">
						<li id="domainTab" class="active"><a href="#messages"
							data-toggle="tab">Domain / Product</a></li>
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
											<th class="col-xs-2">Experience</th>
											<th>Self Assessment</th>
											<th>Emp Comments</th>
											<th>Manager Comments</th>
											<th>Review Assessment <span class="star"><b>*</b></span></th>
											<th>Review Comment</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody id="tech">


										<c:forEach items="${coreSkills}" var="coreSkills" varStatus="indexValue">
											<tr id="tech${indexValue.index}">
												<form:form
													action="${pageContext.request.contextPath}/reviewSkillByPL/${empID}/${coreSkills.proficiencyId}"
													commandName="ProficiencyCoreSkillObject">
													<c:if test="${coreSkills.isPrime =='Y'}">
														<td>Yes</td>
													</c:if>
													<c:if test="${coreSkills.isPrime =='N'}">
														<td>No</td>
													</c:if>
													<td id="AOW${empID}">${coreSkills.aowName}</td>
													<td id="Stream${empID}">${coreSkills.techname}</td>
													<td id="Skill${empID}" style="display: none;">${coreSkills.skillName}</td>

													<td><form:input path="technology" type="hidden"
															value="${coreSkills.skillName}" class="form-control"></form:input>${coreSkills.skillName}</td>
													<td><div class="col-xs-6 text-center style="text-align: right;">
															<form:input path="expYears" type="hidden"
																value="${coreSkills.expYears}" class="form-control"></form:input>${coreSkills.expYears}
															<div>Yr(s)</div>
														</div>
														<div class="col-xs-6 text-center style="text-align: right;">
															<form:input path="expMonths" type="hidden"
																value="${coreSkills.expMonths}" class="form-control"></form:input>${coreSkills.expMonths}
															<div>Mn(s)</div>
														</div></td>
													<td><form:input path="empRating" type="hidden"
															value="${coreSkills.empRating}" class="form-control"></form:input>${coreSkills.empRating}</td>
													<td>${coreSkills.empComment}</td>
													<td>${coreSkills.mgrComment}</td>

													<td><form:select class="form-control select_tech"
															path="mgrRating" id="techRate${indexValue.index}">
															<form:option value="0">Select</form:option>
															<%-- <form:option value="1">1</form:option>
															<form:option value="2">2</form:option>
															<form:option value="3">3</form:option>
															<form:option value="4">4</form:option>
															<form:option value="5">5</form:option> --%>
															<c:if test="${coreSkills.expYears <1}">
																<form:option value="1">1-Novice</form:option>
															</c:if>
															<c:if
																test="${coreSkills.expYears <2 && coreSkills.expYears >=1}">
																<form:option value="1">1-Novice</form:option>
																<form:option value="2">2-Learner</form:option>
															</c:if>
															<c:if
																test="${coreSkills.expYears <5 && coreSkills.expYears >=2}">
																<form:option value="1">1-Novice</form:option>
																<form:option value="2">2-Learner</form:option>
																<form:option value="3">3-Intermediate</form:option>
															</c:if>
															<c:if
																test="${coreSkills.expYears <8 && coreSkills.expYears >=5}">
																<form:option value="1">1-Novice</form:option>
																<form:option value="2">2-Learner</form:option>
																<form:option value="3">3-Intermediate</form:option>
																<form:option value="4">4-Master</form:option>
															</c:if>
															<c:if test="${coreSkills.expYears >=8}">
																<form:option value="1">1-Novice</form:option>
																<form:option value="2">2-Learner</form:option>
																<form:option value="3">3-Intermediate</form:option>
																<form:option value="4">4-Master</form:option>
																<form:option value="5">5-Expert</form:option>
															</c:if>
														</form:select></td>
													<td><form:textarea class="form-control textarea_select_other" rows="3"
															path="mgrComment" id="pcomment${indexValue.index}"></form:textarea>
															<br />
														<button class="btn btn-danger disabled"
															id="saveT${indexValue.index}" type="submit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Save&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
													</td>
													<td>

														<textarea class="form-control textarea_select" rows="3"
																id="techCommentForPL${indexValue.index}"></textarea>
															<br>
															<button class="btn btn-danger btn_action disabled" 
															onclick="openSME('${empID}','${indexValue.index}','${coreSkills.proficiencyId}');" type="button" value="dd">Assign to SME</button>
														<div class="modal fade in background-popup " id="myModal">
															<div class="modal-dialog">
																<div class="modal-content">
																	<div class="modal-header">
																		<button type="button" class="close closed"
																			data-dismiss="modal" aria-hidden="true">×</button>
																		<h4 class="modal-title" id="myModalLabel"></h4>
																	</div>
																	<div class="modal-body">

																		<div class="panel panel-default">
																			<div class="panel-heading text-center">
																				<b>Assign to SME</b>
																			</div>
																			<!-- /.panel-heading -->
																			<div class="panel-body">
																				<div class="table-responsive">
																					<table class="table table-bordered">
																						<thead>
																							<tr>

																								<th>SME Name</th>
																								<th>Action</th>

																							</tr>
																						</thead>
																						<tbody id="smeAppend">


																						</tbody>
																					</table>
																				</div>
																				<!-- /.table-responsive -->
																			</div>
																			<!-- /.panel-body -->
																		</div>
																		<!-- /.panel -->

																	</div>
																	<div class="modal-footer">
																		<button type="button" class="btn btn-default closed"
																			data-dismiss="modal">Close</button>

																	</div>
																</div>
																<!-- /.modal-content -->
															</div>
															<!-- /.modal-dialog -->
														</div>
														
														
													
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

					<c:if test="${!empty coreSkills}">
						<div class="tab-pane fade tab_pane_cus" id="messages">
					</c:if>
					<c:if test="${empty coreSkills}">
						<div class="tab-pane fade in active tab_pane_cus" id="messages">
					</c:if>


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
										<th>Sub group</th>
										<th class="col-lg-2 text-center">Experience</th>
										<th>Self Assessment</th>
										<th>Emp Comments</th>
										<th>Manager Comments</th>
										<th>Review Assessment <span class="star"><b>*</b></span></th>
										<th>Review Comments</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody id="domainBody">

									<c:forEach items="${domain}" var="domain" varStatus="indexValue">
										<form:form
											action="${pageContext.request.contextPath}/reviewDomainbyPL/${empID}/${domain.domainName}/${domain.proficiencyId}"
											commandName="ProficiencyDomainObject">

											<tr id="domain${indexValue.index}">
												<c:if test="${domain.isPrime =='Y'}">
														<td>Yes</td>
													</c:if> <c:if test="${domain.isPrime =='N'}">
														<td>No</td>
													</c:if>
												<form:input path="dName" type="hidden"
													value="${domain.domainCategory}" class="form-control"></form:input>
												<td id="domain${empID}">${domain.domainCategory}</td>
												<form:input path="sGroup" type="hidden"
													value="${domain.domain}" class="form-control"></form:input>
												<td id="subDomain${empID}">${domain.domainName}</td>
												<td><div class="col-xs-6 text-center style="text-align:center;">
														<form:input path="dExpYears" type="hidden"
															value="${domain.dExpYears}" class="form-control"></form:input>${domain.dExpYears}
														<div>Yr(s)</div>
													</div>
													<div class="col-xs-6 text-center style="text-align:center;">
														<form:input path="dExpMonths" type="hidden"
															value="${domain.dExpMonths}" class="form-control"></form:input>${domain.dExpMonths}
														<div>Mn(s)</div>
													</div></td>
												<td><form:input path="empRating" type="hidden"
														value="${domain.empRating}" class="form-control"></form:input>${domain.empRating}</td>
												<td>${domain.empComment}</td>
												<td>${domain.empComment}</td>

												<td><form:select class="form-control select_tech"
														path="mgrRating" id="domain${indexValue.index}">
														<form:option value="0">Select</form:option>
														<%-- <form:option value="1">1</form:option>
														<form:option value="2">2</form:option>
														<form:option value="3">3</form:option>
														<form:option value="4">4</form:option>
														<form:option value="5">5</form:option> --%>

														<c:if test="${domain.dExpYears <1}">
															<form:option value="1">1-Novice</form:option>
														</c:if>
														<c:if
															test="${domain.dExpYears <2 && domain.dExpYears >=1}">
															<form:option value="1">1-Novice</form:option>
															<form:option value="2">2-Learner</form:option>
														</c:if>
														<c:if
															test="${domain.dExpYears <5 && domain.dExpYears >=2}">
															<form:option value="1">1-Novice</form:option>
															<form:option value="2">2-Learner</form:option>
															<form:option value="3">3-Intermediate</form:option>
														</c:if>
														<c:if
															test="${domain.dExpYears <8 && domain.dExpYears >=5}">
															<form:option value="1">1-Novice</form:option>
															<form:option value="2">2-Learner</form:option>
															<form:option value="3">3-Intermediate</form:option>
															<form:option value="4">4-Master</form:option>
														</c:if>
														<c:if test="${domain.dExpYears >=8}">
															<form:option value="1">1-Novice</form:option>
															<form:option value="2">2-Learner</form:option>
															<form:option value="3">3-Intermediate</form:option>
															<form:option value="4">4-Master</form:option>
															<form:option value="5">5-Expert</form:option>
														</c:if>

													</form:select></td>

												<td><form:textarea class="form-control textarea_select_other" rows="3"
														id="pcomment${indexValue.index}" path="mgrComment"></form:textarea>
													<br />
													<button class="btn btn-danger disabled" id="saveD${indexValue.index}"
														type="submit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Save&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
													</td>
												<td>
												<textarea class="form-control textarea_select" rows="3"
																id="domainCommentForPL${indexValue.index}"></textarea>
															<br>
													<button class="btn btn-danger btn_action disabled" 
														onclick="openSMEDomain('${empID}','${indexValue.index}','${domain.proficiencyId}');" type="button"
														value="dd">Assign to SME</button>

													<div class="modal fade in background-popup " id="myModal1">
														<div class="modal-dialog">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close closed"
																		data-dismiss="modal" aria-hidden="true">×</button>
																	<h4 class="modal-title" id="myModalLabel"></h4>
																</div>
																<div class="modal-body">

																	<div class="panel panel-default">
																		<div class="panel-heading text-center">
																			<b>Assign to SME</b>
																		</div>
																		<!-- /.panel-heading -->
																		<div class="panel-body">
																			<div class="table-responsive">
																				<table class="table table-bordered">
																					<thead>
																						<tr>

																							<th>SME Name</th>
																							<th>Action</th>

																						</tr>
																					</thead>
																					<tbody id="smeAppend1">


																					</tbody>
																				</table>
																			</div>
																			<!-- /.table-responsive -->
																		</div>
																		<!-- /.panel-body -->
																	</div>
																	<!-- /.panel -->

																</div>
																<div class="modal-footer">
																	<button type="button" class="btn btn-default closed"
																		data-dismiss="modal">Close</button>

																</div>
															</div>
															<!-- /.modal-content -->
														</div>
														<!-- /.modal-dialog -->
													</div>

												</td>
											</tr>
										</form:form>
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



<script src="${pageContext.servletContext.contextPath}/resources/js/assigntoSME.js"></script>
<script
src="${pageContext.servletContext.contextPath}/resources/js/assigntoPL.js"></script>