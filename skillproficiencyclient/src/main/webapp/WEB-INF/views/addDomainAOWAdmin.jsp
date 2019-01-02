<%@include file="header.jsp"%>

<div class="container">
	<div class="col-lg-12"  >
		<div class="panel panel-danger"  id="hi">
			<div class="panel-heading ">
				<font size="4"><b>Add Domain AOW</b></font>
			</div>
			<div class="panel-body" >
				<div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 text-center">
					<div class="col-lg-6 col-sm-6 col-md-6 col-xs-6">
						<font size="3"><p><b>Domain AOW</b> </p></font>
					</div>
					<div class="col-lg-6 col-sm-6 col-md-6 col-xs-6">
						<font size="3"><b><p>Action</p></b></font>
					</div>
				</div>
				<form name="aowForm" id="aowForm_id">
					<div id="entered_information">
					
					</div>
					<div id="aowDiv" class="col-lg-12 col-sm-12 col-md-12 col-xs-12 text-center" >
						<div class="col-lg-6 col-sm-6 col-md-6 col-xs-6">
							<input class="form-control" type="text" id="textField" name="domainAow">
						</div>
						<div class="col-lg-6 col-sm-6 col-md-6 col-xs-6">
							<input type="button" value="Save" id="saveButton" class="btn btn-default btn-danger"></input>
						</div>
					</div>	
				</form>
			</div>
		</div>       
	</div>
</div>

<%@include file="footer.jsp"%>

   <script src="${pageContext.servletContext.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/jquery-3.1.1.min.js"></script>
  <script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script>
  <script src="${pageContext.servletContext.contextPath}/resources/js/jquery.validate.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/js/addDomainAOW.js"></script>