
<%@include file="header.jsp"%>

<div class="col-xs-12">
	<div class="panel panel-danger">
		<div class="panel-heading  text-center">
			<b>${title}</b>
		</div>
	<%--  <c:if test="${empty show}"> --%>
		<c:if test="${!empty coreSkills || !empty domain || !empty methodologyList || !empty tool || !empty accreditationList}"> 
		<div class="row">
		
			 <div class="col-lg-12">
				<button class="btn btn-danger pull-right" type="button"
					id="exportToXl1"
					onclick="location.href='${pageContext.servletContext.contextPath}/exportEmployeesProficiency/${emailId}'">Export
					to Excel</button>
			</div> 
		</div>
		</c:if>
		 <c:if test="${!empty show }">
			<div class="text-center gab_more">
				<b>No data found</b>
			</div>
			
		</c:if> 
	   <c:if test="${empty show }">   
	 
	 
	
		

			<!-- /.panel-heading -->
			<div class="panel-body">
			<c:if test="${!empty coreSkills || !empty domain || !empty methodologyList || !empty tool || !empty accreditationList}"> 
				<ul class="nav nav-tabs">
					<li class="active"><a href="#home" data-toggle="tab">Skill</a>
					</li>

					<!-- <li><a href="#messages" data-toggle="tab">Accreditation</a></li> -->

				</ul>
				</c:if>

				<!-- Tab panes -->
				<div class="tab-content">
					<div class="tab-pane fade in active tab_pane_cus" id="home">
					 <c:if test="${empty coreSkills && empty domain && empty methodologyList && empty tool && empty accreditationList}">
					<br><div class="text-center" style="font-weight:bold; color:#ee3523;"> Proficiency is not yet updated !!! </br>
					</c:if>
						<c:if test="${!empty coreSkills}">
							<h5>
								<b>Technology</b>
							</h5>
							<div class="table-responsive">
								<table class="table table_center  table-bordered table-hover">
									<thead>
										<tr>
											<th>Type</th>
											<th>Area of work</th>
											<th>Stream</th>
											<th>Skill</th>

											<th class="col-lg-2">Experience</th>
											<th>Employee's Proficiency</th>
											<th>Employee's comments</th>
											<th>Reviewer's comments</th>
											<th>Final Proficiency</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${coreSkills}" var="coreSkills" varStatus="">
											<c:if test="${coreSkills.isPrimary =='Y'}">
												<tr class="table-border-primary">
											</c:if>

											<%-- 									<c:if test="${coreSkills.isPrimary =='Y'}"><tr style="    background-color: #5cb85c;"></c:if>
 --%>
											<c:if test="${coreSkills.isPrimary =='N'}">
												<tr>
											</c:if>

											<td><c:if test="${coreSkills.isPrimary =='Y'}">Primary</c:if>
												<c:if test="${coreSkills.isPrimary =='N'}">Secondary</c:if></td>
											<td>${coreSkills.areaOfWork}</td>
											<td>${coreSkills.technology}</td>
											<td>${coreSkills.skill}</td>
											<td>${coreSkills.expYears} Yr(s) ${coreSkills.expMonths}
												Mn(s)
											</td>
											<td>${coreSkills.empRating}</td>
											<td><c:if test="${coreSkills.empComments==null}"> </c:if>
											<c:if test="${coreSkills.empComments!=null}">${coreSkills.empComments}</c:if>
											</td>
											<td><c:if test="${coreSkills.finalRating ==0}">
													<span class="star"><b>Waiting for Review</b></span>
												</c:if>
												<c:if test="${coreSkills.finalRating >0}">${coreSkills.mgrComment}</c:if></td>
											<td><c:if test="${coreSkills.finalRating ==0}">
													<span class="star"><b>Waiting for Review</b></span>
												</c:if> <c:if test="${coreSkills.finalRating >0}">${coreSkills.finalRating}</c:if>
											</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</c:if>
						
						<%-- <c:if test="${empty coreSkills}">
						No Updated Data Available
						</c:if> --%>
						
						<c:if test="${!empty  domain}">

							<h5>
								<b>Domain/Product</b>
							</h5>
							<div class="table-responsive">
								<table class="table table_center table-bordered table-hover">
									<thead>
										<tr>
											<th>Type</th>
<!-- 											<th>Area of Work</th>
 -->											<th>Domain</th>
											<th>Sub Domain</th>
											<th>Year's of Experience</th>
											<th>Employee's Proficiency</th>
											<th>Employee's comments</th>
											<th>Reviewer's comments</th>
											<th>Final Proficiency</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${domain}" var="domain" varStatus="">
											<c:if test="${domain.isPrimary =='Y'}">
												<tr style="border: 3px solid #5cb85c;">
											</c:if>
											<c:if test="${domain.isPrimary =='N'}">
												<tr>
											</c:if>

											<td><c:if test="${domain.isPrimary =='Y'}">Primary</c:if>
												<c:if test="${domain.isPrimary =='N'}">Secondary</c:if></td>
<%-- 											<td>${domain.areaName}</td>
 --%>											<td>${domain.dName}</td>
											<td>${domain.sGroup}</td>
											<td>${domain.dExpYears} Yr(s) ${domain.dExpMonths}
												Mn(s)
											</td>
											<td>${domain.empRating}</td>
											<%-- <td>${domain.empComments}</td> --%>
											<td><c:if test="${domain.empComments==null}"> </c:if>
											<c:if test="${domain.empComments!=null}">${domain.empComments}</c:if>
											</td>
											<td><c:if test="${domain.finalRating ==0}">
													<span class="star"><b>Waiting for Review</b></span>
												</c:if>
												<c:if test="${domain.finalRating >0}">${domain.mgrComment}</c:if></td>
											<td><c:if test="${domain.finalRating ==0}">
													<span class="star"><b>Waiting for Review</b></span>
												</c:if>
												<c:if test="${domain.finalRating >0}">${domain.finalRating}</c:if></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</c:if>
						<%-- <c:if test="${empty domain}">
						No Updated Data Available
						</c:if> --%>
						
						<c:if test="${!empty methodologyList}">

							<h5>
								<b>Methodology</b>
							</h5>
							<div class="table-responsive">
								<table class="table table_center  table-bordered table-hover">
									<thead>
										<tr>

											<th>Methodology Name</th>
											<th>Year's of Experience</th>
											<th>Employee's Proficiency</th>
											<th>Employee's comments</th>
											<!-- <th>Manager's proficiency</th> -->
											<!-- <th>Manager's comments</th>
										<th>Final Proficiency</th> -->
										</tr>
									</thead>
									<tbody>

										<c:forEach items="${methodologyList}" var="methodologyLists"
											varStatus="">

											<tr>

												<td>${methodologyLists.mName}</td>
												<td>${methodologyLists.mExpYears} Yr(s)
													${methodologyLists.mExpMonths} Mn(s)
												</td>
												<td>${methodologyLists.empRating}</td>
												<td>${methodologyLists.comments}</td>
												<%-- <td>${methodologyLists.mgrRating}</td> --%>
												<%-- <td>${methodologyLists.mgrComment}</td>
											<td>${methodologyLists.finalRating}</td> --%>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</c:if>
						
						<%-- <c:if test="${empty methodologyLists}">
						No Updated Data Available
						</c:if> --%>
					
						<c:if test="${!empty tool}">

							<h5>
								<b>Tools</b>
							</h5>
							<div class="table-responsive">
								<table class="table table_center  table-bordered table-hover">
									<thead>
										<tr>
											<th>Tool Type</th>
											<th>Tool Name</th>
											<th>Year's of Experience</th>
											<th>Employee's Proficiency</th>
											<th>Employee's comments</th>
											<!-- 	<th>Manager's proficiency</th> -->
											<!-- 	<th>Manager's comments</th>
										<th>Final Proficiency</th> -->
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${tool}" var="tool">
											<tr>
												<td>${tool.toolType}</td>
												<td>${tool.toolName}</td>
												<td>${tool.tExpYears} Yr(s) ${tool.tExpMonths}
													Mn(s)
												</td>
												<td>${tool.empRating}</td>
												<td>${tool.empComments}</td>
												<%-- <td>${tool.mgrRating}</td> --%>
												<%-- <td>${tool.mgrComment}</td>
											<td>${tool.finalRating}</td> --%>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</c:if>
						
						<%-- <c:if test="${empty tool}">
						No Updated Data Available
						</c:if> --%>
						
						<c:if test="${!empty accreditationList}">
							<h5>
								<b>Accreditation</b>
							</h5>
							<div class="table-responsive">
								<table class="table table_center table-bordered table-hover">
									<thead>
										<tr>
											<th>Name of Certificate</th>
											<th>Certificate Number</th>
											<th>Issue Date</th>


										</tr>
									</thead>
									<tbody>
										<c:forEach items="${accreditationList}"
											var="accreditationList">
											<tr>
												<td>${accreditationList.accreditationName}</td>
												<td>${accreditationList.cNo}</td>
												<td>${accreditationList.issueDate}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</c:if>
						<%-- <c:if test="${empty accreditationList}">
						No Updated Data Available
						</c:if> --%>
						</c:if>
					</div>

					<%-- 	<div class="tab-pane fade tab_pane_cus" id="messages">
									<div class="gab">&nbsp;</div>
					
						<div class="table-responsive">
							<table class="table table_center table-bordered table-hover">
								<thead>
									<tr>
										<th>Name of Certificate</th>
										<th>Certificate Number</th>
										<th>Issue Date</th>


									</tr>
								</thead>
								<tbody>
									<c:forEach items="${accreditationList}" var="accreditationList">
										<tr>
											<td>${accreditationList.accreditationName}</td>
											<td>${accreditationList.certificateNo}</td>
											<td>${accreditationList.issueDt}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div> --%>

				</div>
			</div>
			
			
			
			
			
			
		 <%--  </c:if>  
		  --%>
		 <%--  </c:if> --%>
		<!-- /.panel-body -->
	</div>
</div>



<%@include file="footer.jsp"%>