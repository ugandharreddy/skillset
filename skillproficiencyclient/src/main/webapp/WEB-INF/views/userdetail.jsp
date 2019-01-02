<%-- <script>

function uploadFile(){
				
				$.ajax({
					url : "<%=request.getContextPath()%>/uploadFile",
					type : "POST",
					datatype : 'json',
						success : function(result) {
						
						if (result==='success') {
							alert("Uploaded Successfully!!!");
							location.href = "<%=request.getContextPath()%>/login";
						}
						else {
							alert("Upload failed!!!");
							location.href = "<%=request.getContextPath()%>/login";
						
						}
					},
					error : function(error) {
						alert("uploadedeeeeee failed!!!");
						console.log("Error");
					}
				}); 
	
}


</script> --%>
<script>

</script>
<div class="panel panel-danger">

	<div class="panel-heading text-center"><b>Proficiency Details</b></div>
	<div class="panel-body">
	<div class="text-center star">*This dashboard only displays your proficiency with primary skills or highest rating</div><br>
		
		<form:form commandName="userDetails">
		<c:if test="${!empty userDetails}">
			<div class="col-xs-3 attra_border">
				<div class="row">
					<div class="col-lg-6">
						<label>Employee Name</label>

					</div>
					<div class="col-lg-6">
						<span>${userDetails.empName}</span>

					</div>
				</div>
				<div class="row next gab">
					<div class="col-lg-6 ">
						<label>Manager Name</label>

					</div>
					<div class="col-lg-6">
						<span>${userDetails.mgrName}</span>
					</div>
				</div>
				<%-- <div class="row next gab">
					<div class="col-lg-6 ">
						<label>Designation</label>

					</div>
					<div class="col-lg-6">
						<span>${userDetails.designation}</span>
					</div>
				</div> --%>
				<%-- <div class="row next gab border-top">
					<div class="col-lg-6 ">
						<label>Date of Joining</label>

					</div>
					<div class="col-lg-6">
						<span>${userDetails.doj}</span>
					</div>
				</div> --%>
				<div class="row next gab">
					<div class="col-lg-6 ">
						<label>Attra Experience</label>

					</div>
					<div class="col-lg-6">
					<c:if test="${userDetails.attraExpYears >0 || userDetails.attraExpMonths >0}">
						<c:if test="${userDetails.attraExpYears >0}"><span>${userDetails.attraExpYears} Yr(s)</span></c:if>&nbsp;&nbsp; 
							<c:if test="${userDetails.attraExpMonths >0}"><span>${userDetails.attraExpMonths} Mn(s)</c:if></span>
					</c:if>
					<c:if test="${userDetails.attraExpYears ==0 && userDetails.attraExpMonths ==0}">
						<span>Yet to update</span>
						</c:if>
					</div>
				</div>
				<div class="row next gab">
					<div class="col-lg-6 ">
						<label>Total Experience</label>

					</div>
					<div class="col-lg-6">
						<c:if test="${userDetails.totalExpYears >0}"><span>${userDetails.totalExpYears} Yr(s)</span></c:if>&nbsp;&nbsp; 
							<c:if test="${userDetails.totalExpMonths >0}"><span>${userDetails.totalExpMonths} Mn(s)</c:if></span>
					</div>
				</div>


			</div>
</c:if>
			<div class="col-xs-7 attra_border">
				<%-- <div class="col-xs-12 next ">
					<div class="col-xs-3">
					<div class="row">
						<label>Current Area of Work</label>
					</div>
					</div>
					<div class="col-xs-3">
						<span>${userDetails.areaOfWork}</span>
					</div>
				</div> --%>
				<div class="col-xs-6 ">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<%-- <th>Technology<c:if test="${userDetails.techPrimaryCheck =='Y'}">
							<label style="color:#5cb85c;">(Primary)</label>
						</c:if></th> --%>
						<th><div>Skill<c:if test="${userDetails.techPrimaryCheck =='Y'}">
							<span style="color:#5cb85c;">(Primary)</span>
						</c:if></div></th>
							<th>Overall Rating</th>

						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty userDetails.technology}">
						<c:if test="${userDetails.techPrimaryCheck=='Y'}"><tr ></c:if>
						<c:if test="${userDetails.techPrimaryCheck=='N'}"><tr></c:if>
						
							
								<td>${userDetails.technology}</td>
								<%-- <td><c:if test="${userDetails.technologyRating ==0}">
										<label class="star">Waiting for review</label>
									</c:if> <c:if test="${userDetails.technologyRating >0}">${userDetails.technologyRating}</c:if></td> --%>
									<td><div><c:if test="${userDetails.technologyRating ==0}">
										<span class="star">Waiting for review</span>
									</c:if> <c:if test="${userDetails.technologyRating >0}">${userDetails.technologyRating}</c:if></div></td>

							</tr>
						</c:if>
							<c:if test="${empty userDetails.technology}">
							<tr>
								<td>NA</td>
								<td>NA</td>

							</tr>
						</c:if>
						
						
					</tbody>
				</table>
				</div>
				<div class="col-xs-6 ">
	<table class="table  table-bordered table-hover">
					<thead>
						<tr>
							<%-- <th>Domain <c:if test="${userDetails.domainPrimaryCheck =='Y'}">
							<label  style="color:#5cb85c;">(Primary)</label>
						</c:if></th> --%>
						<th><div>Sub Domain <c:if test="${userDetails.domainPrimaryCheck =='Y'}">
							<span  style="color:#5cb85c;">(Primary)</span>
						</c:if></div></th>
							<th>Overall Rating</th>

						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty userDetails.domain}">
					<c:if test="${userDetails.domainPrimaryCheck =='Y'}"><tr ></c:if>
						<c:if test="${userDetails.domainPrimaryCheck =='N'}"><tr></c:if>
						
							
								<td>${userDetails.domain}</td>
								<%-- <td><c:if test="${userDetails.domainRating ==0}">
										<label class="star">Waiting for review</label>
									</c:if> <c:if test="${userDetails.domainRating >0}">${userDetails.domainRating}</c:if></td> --%>
									<td><div><c:if test="${userDetails.domainRating ==0}">
										<span class="star">Waiting for review</span>
									</c:if> <c:if test="${userDetails.domainRating >0}">${userDetails.domainRating}</c:if></div></td>
							</tr>
						</c:if>
							<c:if test="${ empty userDetails.domain}">
							<tr>
								<td>NA</td>
								<td>NA</td>

							</tr>
						</c:if>
						
						
					</tbody>
				</table>
				</div>
				<div class="col-xs-6 next">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>Methodology </th>
							<th>Self Assesement</th>

						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty userDetails.methodology}">
								<tr>
							<td>${userDetails.methodology}</td>
								<td>${userDetails.methodRating}</td>

							</tr>
						</c:if>
							 <c:if test="${empty userDetails.methodology}">
							<tr>
								<td>NA</td>
								<td>NA</td>

							</tr>
						</c:if> 
						
						
					</tbody>
				</table>
				</div>
				<div class="col-xs-6">
					<table class="table  table-bordered table-hover">
					<thead>
						<tr>
							<th>Tools </th>
							<th>Self Assesement</th>

						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty userDetails.toolName}">
								<tr>
							<td>${userDetails.toolName}</td>
								<td>${userDetails.toolRating}</td>

							</tr>
						</c:if>
							 <c:if test="${empty userDetails.toolName}">
							<tr>
								<td>NA</td>
								<td>NA</td>

							</tr>
						</c:if> 
						
						
					</tbody>
				</table>
				</div>


		<%-- 		<div class="row next gab">
					<div class="col-lg-6 ">
						<label>Technology : Rating</label>
						<c:if test="${userDetails.techPrimaryCheck=='Y'}">
							<label class="star">(Primary)</label>
						</c:if>

					</div>
					<div class="col-lg-6">
						<c:if test="${not empty userDetails.technology}">
							${userDetails.technology}
							
							
							<c:if test="${userDetails.technologyRating ==0}">
								<label class="star">Waiting for review</label>
							</c:if>
							<c:if test="${userDetails.technologyRating >0}">
							</c:if>


						</c:if>
						<c:if test="${empty userDetails.technology}">
							<span>Please update Technology</span>
						</c:if>

					</div>
				</div>
				<div class="row next gab">
					<div class="col-lg-6 ">
						<label>Domain : Rating</label>
						<c:if test="${userDetails.domainPrimaryCheck =='Y'}">
							<label class="star">(Primary)</label>
						</c:if>

					</div>
					<div class="col-lg-6">
						<c:if test="${not empty userDetails.domain}">
							<c:if test="${userDetails.domainRating ==0}">${userDetails.domain} :<label
									class="star">Waiting for review</label>
							</c:if>
							<c:if test="${userDetails.domainRating >0}">
								<span>${userDetails.domain} : ${userDetails.domainRating}</span>
							</c:if>
						</c:if>
						<c:if test="${empty userDetails.domain}">
							<span>Please update Domain</span>
						</c:if>

					</div>
				</div>

				<div class="row next gab">
					<div class="col-lg-6 ">
						<label>Methodology : Rating</label>

					</div>
					<div class="col-lg-6">
						<c:if test="${not empty userDetails.methodology}">
							<span>${userDetails.methodology} :
								${userDetails.methodRating}</span>
						</c:if>
						<c:if test="${empty userDetails.methodology}">
							<span>Please update Methodology</span>
						</c:if>
					</div>
				</div>
				<div class="row next gab">
					<div class="col-lg-6 ">
						<label>Tool : Rating</label>

					</div>
					<div class="col-lg-6">
						<c:if test="${not empty userDetails.toolName}">
							<span>${userDetails.toolName} : ${userDetails.toolRating}</span>
						</c:if>
						<c:if test="${empty userDetails.toolName}">
							<span>Please update Tool</span>
						</c:if>
					</div>
				</div> --%>
				<!-- 	<div class="row next gab">
						<div class="col-lg-6 ">
							<label>Final Proficiency<br/><span class="star">(Primary Skill)</span></label>

						</div>
						<div class="col-lg-6">
							<span>d</span>
						</div>
					</div> -->



			</div>
			<div class="col-xs-2">

				<form role="form">

					<a data-toggle="modal" data-target="#myModal" href="#"> <img
						src="${pageContext.servletContext.contextPath}/resources/images/profile.jpg"
						class="img-thumbnail img-responsive center-block"
						width="100" height="93">
					</a>
					<%-- <div class="modal fade in modal_custom" id="myModal" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
						style="display: none;">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header modal_custom">
									<button type="button" class="close " data-dismiss="modal">
										<span class="attra_color">X—</span>
									</button>
									<!--      <h4 class="modal-title" id="myModalLabel">Please </h4> -->
								</div>
								<div class="modal-body">
									<img
										src="${pageContext.servletContext.contextPath}/resources/images/profile_lg.jpg"
										class="img-responsive center-block">
								</div>
								<div class="modal-footer">
									<form action="#" method="post">
										<div class="col-lg-6">
											<input class="form-control" Placeholder="file" type="file">
										</div>
										<div class="col-lg-6">
											<button type="button" class="btn btn-danger">Update</button>
										</div>
									</form>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div> --%>
				</form>

				<div class="gab next">&nbsp;</div>
				<%-- <div class="row next gab">
					<div class="col-lg-6 ">
						<label>Contact Number</label>

					</div>
					<div class="col-lg-6">

						<span>${userDetails.contactNumber}</span>
					</div>
				</div> --%>
				<!-- <div class="row next gab"> -->
					<div class="col-lg-6 text-center">
						<!-- <label>Resume</label> -->

					</div>
					 <div class="col-lg-6">
						<span class="center-block" style="margin-left: -34px;">Resume</span>
					</div> 
				<!-- </div> -->
				<div class="col-lg-12 next gab text-center">
				<div class="col-lg-6">
					<a data-toggle="modal" data-target="#myModal1" href="#">
					 	<button class="btn btn-danger btn-sm">Upload</button> <!-- need to uncomment if upload functionality is required -->
					</a>
					</div>
					<div class="col-lg-6">
					
				<!-- 	 <button type="button" class="btn btn-danger btn-sm" onclick="download();">Download</button>  -->
					<%-- 	<a href="<c:url value='/downloadResume' />" class="btn btn-danger btn-sm">Download</a> --%><!--  need to uncomment if nee download functioality -->
					
					
					<!-- 						<!-- <button type="button" class="btn btn-danger btn-sm" onclick="href='<c:url value="/downloadResume"/>'">Download</button> -->
						
<%-- 						<a href="<c:url value='/downloadResume' />" class="btn btn-default">Download</a>
 --%>
				</div>
				</div>
				
				<div class="modal fade in" id="myModal1" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header modal_custom">
								<button type="button" class="close " data-dismiss="modal">
									<span class="attra_color">×</span>
								</button>
								<!--      <h4 class="modal-title" id="myModalLabel">Please </h4> -->
							</div>
							<div class="modal-body">
								<!-- 								<form role="form" name="vcfForm" id="vcfForm" METHOD="POST"  enctype="multipart/form-data">
								<form role="form" name="vcfForm" id="vcfForm" method="post" encType="multipart/form-data" >
								
									<div class="col-lg-3">
											<label>Contact Number</label>
										</div>
										<div class="col-lg-2">
											<input class="form-control" Placeholder="code" type="text"
												maxlength="5">
										</div>
										<div class="col-lg-4">
											<input class="form-control" Placeholder="Contact Number"
												type="text" maxlength="10">
										</div>
										<div class="next gab">&nbsp;</div>
									<div class="col-lg-3">
										<label>Resume</label>
									</div>
									<div class="col-lg-6">
										<input class="form-control" Placeholder="Resume" type="file" name="vcfFile" id="vcfFile" form="vcfForm">
									</div>
									<div class="next gab">&nbsp;</div>
									<div class="col-lg-12 text-center">
										<button type="button"  name="vcfSubmit" id="vcfSubmit" form="vcfForm" class="btn btn-danger">Update</button>
									</div>
									<div class="next gab">&nbsp;</div>
									<div id="valid_msg"></div>
								</form> -->
								<form method="POST"  action="uploadFile"  enctype="multipart/form-data"> 
								<!-- <form method="POST" action="uploadFile"
									enctype="multipart/form-data"> -->
									Resume: <input type="file" id="fileName" name="file">
									<!--  Name: <input type="text" name="name"> -->
									  <input type="button"  value="Upload" onClick="uploadFile()">
									  <div class="star">**File size should not exceed 3MB.!</div>
									  <!--onclick="return uploadFile();"  Press here to upload the file! -->
								</form>
								<!-- <a href="http://www.w3schools.com">Visit W3Schools.com!</a> -->
							</div>
							<!-- /.modal-content -->
						</div>

					</div>
				</div>
				
				
				
				
				
		<%-- 		<c:if test="${uploadedCV !='NO'}">
		${uploadedCV}
		</c:if> --%>

				<!-- /.modal-dialog -->
			</div>
		</form:form>
	</div>

</div>
<script
	src="${pageContext.servletContext.contextPath}/resources/js/myscript.js"></script>
<!-- <script type="text/javascript">
function uploadFile(){
 if(/.*\.(docx)|(doc)$/.test(f['file'].value.toLowerCase()))
  return true;
 alert('Please Upload Gif or Jpg Images, or Doc Files Only.');
 f['file'].focus();
 $("form").submit();
 return false;
};
</script> -->
