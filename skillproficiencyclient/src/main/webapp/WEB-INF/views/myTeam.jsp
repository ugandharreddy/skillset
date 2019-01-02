<%@include file="header.jsp"%>

<div class="container">
	<div class="col-lg-12">
		<div class="panel panel-danger" >
			<div class="panel-heading text-center">
				<b>Employee Details</b>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body" >
				<div class="dataTable_wrapper" >

					<table class="table table-striped table-bordered table-hover" id="dataTables-example-myteam">
						<thead>
							<tr>
								<th>Profile Picture</th>
								<th>Employee Details</th>
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
											<div class="col-lg-4">
												<span>${employessList.empName}</span>
											</div>
										</div>

										<div class="row next gab">
											<div class="col-lg-3">
												<label>Technology</label>
											</div>
											<div class="col-lg-4">
												<c:if test="${not empty employessList.technologyName}">
													<span>${employessList.technologyName}<c:if
															test="${techPrimaryCheck =='Y'}">(Primary)</c:if></span>
												</c:if>
												<c:if test="${empty employessList.technologyName}">
													<span>NA</span>
												</c:if>
											</div>
											<div class="col-lg-2 ">
												<label>Exp</label>
											</div>
											<div class="col-lg-3">
												<c:if test="${not empty employessList.technologyName}">
													<span>${employessList.tExpYears}Yr(s)
														${employessList.tExpMonths}Mn(s)</span>
												</c:if>
												<c:if test="${empty employessList.technologyName}">
													<span>NA</span>
												</c:if>
											</div>
										</div>

										<div class="row next gab">
											<div class="col-lg-3 ">
												<label>Domain </label>
											</div>
											<div class="col-lg-4">
												<c:if test="${not empty employessList.domain}">
													<span>${employessList.domain}<c:if
															test="${domainPrimaryCheck =='Y'}">(Primary)</c:if></span>
												</c:if>
												<c:if test="${empty employessList.domain}">
													<span>NA</span>
												</c:if>
											</div>
											<div class="col-lg-2 ">
												<label>Exp</label>
											</div>
											<div class="col-lg-3">
												<c:if test="${not empty employessList.domain}">
													<span>${employessList.dExpYears}Yr(s)
														${employessList.dExpMonths}Mn(s)</span>
												</c:if>
												<c:if test="${empty employessList.domain}">NA</c:if>
											</div>
										</div>
										</p>
									</td>
									<td>
										<div class="text-center gab_more">
											<%-- <button class="btn btn-default btn-danger gab_right"
												data-toggle="modal" data-target="#myModal" onclick="location.href='<%=request.getContextPath()%>/relieveEmployee/${employessList.empID}';"
												id="${employessList.empID}">Relieve to Bench</button> --%>

											<button class="btn btn-default btn-danger"
												onclick="location.href='<%=request.getContextPath()%>/employeeDetailsById/${employessList.empID}/${employessList.empName}/${employessList.emailID}';">View
												Details</button>

										</div>
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
	function deploy(deployId) {

		location.href = "${pageContext.servletContext.contextPath}/deployEmployee/"
				+ deployId;

	}
	

	  $(document).ready(function() {
	        $('#dataTables-example-myteam').DataTable({
	                responsive: true
	        });
	    });
		
		


</script>

<%@include file="footer.jsp"%>
