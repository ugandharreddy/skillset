<%@include file="header.jsp"%>
<c:if test="${role.contains('MANAGER') }">
<div class="col-xs-12">

	<div class="panel panel-danger">
		<div class="panel-heading  text-center">
			<b>Proficiency Review Requests</b>
		</div>
		<div class="panel-body">
		<div class="col-xs-6">
	<div class="panel panel-default">
		<div class="panel-heading  text-center">
			<b>Pending Review For Proficiency Rated<= 3</b>
		</div>
						<c:if test="${!empty managerTaskList}">
		
		<!-- /.panel-heading -->
		<div class="panel-body">
		
			<!-- Nav tabs -->
			<!--  <h5><b>Testing Type</b></h5> -->
			<div class="table-responsive table-responsive-custom">
				<table class="table  table-bordered table-hover" id="smeReview">
					<thead>
						<tr>
							<th>Employee Name</th>
							<th>Email ID</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${managerTaskList}" var="managerTaskList" varStatus="">
							<tr>
								<td><a id="hand"
									onclick="location.href='<%=request.getContextPath()%>/smeReview/${managerTaskList.empEmailId}'">${managerTaskList.empName}</a></td>
								<td>${managerTaskList.empEmailId}</td>
							</tr> 
						</c:forEach>
					</tbody>
				</table>
			</div>
				</div>
			
			</c:if>
	
		<c:if test="${empty managerTaskList}">
<div class="panel-heading  text-center">
			<div style="font-weight:bold;color:#ee3523;">No Task available</div>
		</div>
</c:if>
		<!-- /.panel-body -->
	</div>

</div>

<div class="col-xs-6">
	<div class="panel panel-default">
		<div class="panel-heading  text-center">
					<b>Pending Review For Proficiency Rated 3+</b>
		</div>
		<c:if test="${!empty managerDecisionTaskList}">
		
		<!-- /.panel-heading -->
		<div class="panel-body">
			<!-- Nav tabs -->
			<!--  <h5><b>Testing Type</b></h5> -->
			<div class="table-responsive table-responsive-custom">
				<table class="table  table-bordered table-hover" id="smeReview1">
					<thead>
						<tr>
							<th>Employee Name</th>
							<th>Email ID</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${managerDecisionTaskList}" var="managerDecisionTaskList" varStatus="">
							<tr>
								<td><a id="hand"
									onclick="location.href='<%=request.getContextPath()%>/smeDecisionReview/${managerDecisionTaskList.empEmailId}'">${managerDecisionTaskList.empName}</a></td>
								<td>${managerDecisionTaskList.empEmailId}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		</c:if>
		<c:if test="${empty managerDecisionTaskList}">
<div class="panel-heading  text-center">
			<div style="font-weight:bold;color:#ee3523;">No Task available</div>
		</div>
		<!-- /.panel-body -->
	</div>

</c:if>
</div>
</div>
</div>
</div>
</c:if>
<c:if test="${userRole.contains('SME') }">


<div class="col-xs-12">
	<div class="panel panel-danger">
		<div class="panel-heading  text-center">
					<b>Pending Review For Proficiency assigned by Practice Lead</b>
		</div>
		<!-- /.panel-heading -->
		<div class="panel-body">
		<c:if test="${!empty smeTaskList}">
		
			<!-- Nav tabs -->
			<!--  <h5><b>Testing Type</b></h5> -->
			<div class="table-responsive table-responsive-custom">
				<table class="table  table-bordered table-hover" id="smeReview2">
					<thead>
						<tr>
							<th>Employee Name</th>
							<th>Email ID</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${smeTaskList}" var="smeTaskList" varStatus="">
							<tr>
								<td><a id="hand"
									onclick="location.href='<%=request.getContextPath()%>/smeandLeadDecisionReview/${smeTaskList.empEmailId}'">${smeTaskList.empName}</a></td>
								<td>${smeTaskList.empEmailId}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</c:if>
			<c:if test="${empty smeTaskList}">
<div class="panel-heading  text-center">
			<div style="font-weight:bold;color:#ee3523;">No Task available</div>
		</div>
</c:if>
		</div>
		<!-- /.panel-body -->
	</div>
	</c:if>

</div>

<c:if test="${userRole.contains('PRACTICE LEAD')}">


<div class="col-xs-12">
	<div class="panel panel-danger">
		<div class="panel-heading  text-center">
					<b>Pending Review For Proficiency Requested by Managers</b>
		</div>
		<!-- /.panel-heading -->
		<div class="panel-body">
		<c:if test="${!empty praticeLeadTaskList}">
		
			<!-- Nav tabs -->
			<!--  <h5><b>Testing Type</b></h5> -->
			<div class="table-responsive table-responsive-custom">
				<table class="table  table-bordered table-hover" id="smeReview3">
					<thead>
						<tr>
							<th>Employee Name</th>
							<th>Email ID</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${praticeLeadTaskList}" var="praticeLeadTaskList" varStatus="">
							<tr>
								<td><a id="hand"
									onclick="PLTask('${praticeLeadTaskList.empEmailId}');">${praticeLeadTaskList.empName}</a></td>
								<td>${praticeLeadTaskList.empEmailId}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</c:if>
			<c:if test="${empty praticeLeadTaskList}">
<div class="panel-heading  text-center">
			<div style="font-weight:bold;color:#ee3523;">No Task available</div>
		</div>
</c:if>
		</div>
		<!-- /.panel-body -->
	</div>

</div>



</c:if>
<%@include file="footer.jsp"%>
<script type="text/javascript">
    
   
      function PLTask(emailId)
    {
    var  path=document.getElementById("contextPaht").value;
    
    	window.location.href=path+'/getPLTask/'+emailId;
    } 
    $(document).ready(function() {
    	
  
       
        $('#smeReview').DataTable({
            responsive: true,
			 "iDisplayLength": 5,
			"aLengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
        });
        $('#smeReview1').DataTable({
            responsive: true,
			 "iDisplayLength": 5,
"aLengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
    });
        $('#smeReview2').DataTable({
            responsive: true,
			 "iDisplayLength": 5,
"aLengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
    });
        $('#smeReview3').DataTable({
            responsive: true,
			 "iDisplayLength": 5,
"aLengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
    });
        $('#smeReview_filter').addClass("pull-right");  
        $('#smeReview1_filter').addClass("pull-right");
        $('#smeReview2_filter').addClass("pull-right");
        $('#smeReview3_filter').addClass("pull-right");
    });
    </script>