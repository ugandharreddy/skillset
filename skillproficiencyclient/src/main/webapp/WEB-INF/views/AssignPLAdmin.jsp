<%@include file="header.jsp"%>


	<div class="container">
		<div class="col-lg-12">
			<div class="panel panel-danger">
				<div class="panel-heading ">
					<font size="4"><b>Assign Employee as PL</b></font>
				</div>
				<div class="panel-body">
					<div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 text-center">
						<div class="col-lg-4 col-sm-4 col-md-4 col-xs-4 ">
							<font size="3"><b>Domain Name</b></font>
						</div>
						<div class="col-lg-4 col-sm-4 col-md-4 col-xs-4">
							<font size="3"><b>PL Name</b></font>
						</div>
						<div class="col-lg-4 col-sm-4 col-md-4 col-xs-4">
							<font size="3"><b>Action </b></font>
						</div>
					</div>
					<c:forEach var="refId" items="${list}">
						<div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 text-center"
							style="margin-bottom: 20px; border-bottom: true">
							<div class="col-lg-4 col-sm-4 col-md-4 col-xs-4 ">
								<p id="heroName"><font size="3">${refId.domainName}</font></p>
							</div>
							<div class="col-lg-4 col-sm-4 col-md-4 col-xs-4" >
								<c:forEach var="refId" items="${list}">
									<p><font size="3">${refId.plName}</font></p>
								</c:forEach>
							</div>
							<div class="col-lg-4 col-sm-4 col-md-4 col-xs-4">
								<input type="button" value="Add/Edit" data-toggle="modal"
									data-target="#myModal"
									class="btn btn-default btn-danger addEdit"></input>
							</div>

						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<div class="panel panel-danger panel-style">
						<div class="panel-heading ">
							<font size="4"><b>Assign Employee as PL</b></font>
						</div>
					</div>
				</div>
				<div class="modal-body model-body">
					<div class="panel panel-danger">
						<div class="panel-body">
							<div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 bottom-margin">
								<div class="col-lg-3 col-sm-3 col-md-3 col-xs-3 text-center ">
									<font size="4"><b>Domain:</b></font>
								</div>
								<div class="col-lg-3 col-sm-3 col-md-3 col-xs-3 text-center ">
									<font size="4"><b id="techName"></b></font>
								</div>
							</div>
							<div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 text-center">
								<div class="panel panel-danger">
									<div class="panel-heading panel-head">
										<div class="col-lg-7 col-sm-7 col-md-7 col-xs-7">
												<font size="3"><b>PL Name</b></font>
										</div>
										<div class="col-lg-5 col-sm-5 col-md-5 col-xs-5">
											<font size="3"><b>Action</b></font>
										</div>
									</div>
									<div class="panel-body"  id="plList">
										<div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 bottom-margin">
											<div class="col-lg-7 col-sm-7 col-md-7 col-xs-7">
												<font size="3">${refId.plName}</font>
											</div>
											<div class="col-lg-5 col-sm-5 col-md-5 col-xs-5">
												<a data-toggle="modal" data-target="#deleteModal">delete</a>
											</div>
										</div>
									</div>
								</div>
								<div id="entered_information"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer footer-style">
					<input type="button" value="Close" id="closeButton" class="btn btn-default btn-danger" data-dismiss="modal"></input>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="deleteModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<div class="panel panel-danger panel-style">
						<div class="panel-heading ">
							<font size="4"><b>Delete PL</b></font>
						</div>
					</div>
				</div>
				<div class="modal-body model-body">
					<div class="panel panel-danger">
						<div class="panel-body">
							<div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 bottom-margin">
								<font size="2"><b>Are you sure you want to delete the existing PL?</b></font>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer footer-style">
					<input type="button" value="Yes" id="yesButton" class="btn btn-default btn-danger" data-dismiss="modal"></input>
					<input type="button" value="No" id="noButton" class="btn btn-default btn-danger" data-dismiss="modal"></input>
				</div>
			</div>
		</div>
	</div>
	
	<script>
		$(function() {
			$("#dialog").dialog({
				autoOpen : false,
				title : "Assign Employee",
				width : 600,
				height : 300,
			});
			$(".addEdit").click(function() {
				var technologyName=$(this).parent().parent().find("div:first-child").find("p").text();
				$("#techName").text(technologyName);
				
			});
			$("#deleteButton").on("click",function(){
				$('#dialog').dialog('open');
			});
			$("#yesButton").on("click",function(){
				
				addedInformation="<div class='col-lg-12 col-sm-12 col-md-12 col-xs-12' align='left'><font size='3'><b>Assign PL</b></font></div><div class='col-lg-12 col-sm-12 col-md-12 col-xs-12 text-center'><div class='col-lg-7 col-sm-7 col-md-7 col-xs-7'><input class='form-control' type='text' id='extField' name='empName'></div><div class='col-lg-3 col-sm-3 col-md-3 col-xs-3'><input type='button' value='Add'  class='btn btn-default btn-danger btn-block add_button'></input></div></div>";
				$("#entered_information").append(addedInformation);
				$("#plList").remove();
			});
		});
</script>

<%@include file="footer.jsp"%>
<link
	href="${pageContext.servletContext.contextPath}/resources/css/assignPL.css"
	rel="stylesheet">

  <script src="${pageContext.servletContext.contextPath}/resources/js/jquery.min.js"></script>
  <script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script>
  <script src="${pageContext.servletContext.contextPath}/resources/js/jquery-ui.js"></script>