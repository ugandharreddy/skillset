<%@include file="header.jsp"%>
<%-- <%@include file="Legend.jsp"%> --%>

<div class="col-xs-12">

	<!--<c:if test="${msg !='update'}">
		<div class="text-center">
			<span class="errormsg">${msg}</span>
		</div>
	</c:if>-->

	<marquee>
		<span class="star">Only Technology and Domain will be assessed
			by manager. One skill from either Technology or Domain can be primary
			skill, others will be secondary skill. Please do not update duplicate
			proficiency. <span>
	</marquee>
	<div class="panel panel-danger">
		<div class="panel-heading text-center">
			<b>Update Proficiency</b>
		</div>
		<!-- /.panel-heading -->
		<div class="panel-body panel-body-custom1">

			<!-- Nav tabs -->
			<c:if test="${msg=='1'}">
						<ul class="nav nav-tabs nav-custom">
				<li class="active"><a href="#home" data-toggle="tab">Technology
				</a></li>
				<li><a href="#messages" data-toggle="tab">Domain / Product</a></li>
				<li><a href="#profile" data-toggle="tab">Methodology</a></li>


				<li><a href="#tool" data-toggle="tab">Tools</a></li>
				<li><a href="#acc" data-toggle="tab">Accreditation</a></li>
			</ul>
			</c:if>
			<c:if test="${msg=='2'}">
						<ul class="nav nav-tabs nav-custom">
				<li ><a href="#home" data-toggle="tab">Technology
				</a></li>
				<li class="active"><a href="#messages" data-toggle="tab">Domain / Product</a></li>
				<li><a href="#profile" data-toggle="tab">Methodology</a></li>


				<li><a href="#tool" data-toggle="tab">Tools</a></li>
				<li><a href="#acc" data-toggle="tab">Accreditation</a></li>
			</ul>
			</c:if>
			<c:if test="${msg=='3'}">
						<ul class="nav nav-tabs nav-custom">
				<li ><a href="#home" data-toggle="tab">Technology
				</a></li>
				<li><a href="#messages" data-toggle="tab">Domain / Product</a></li>
				<li class="active"><a href="#profile" data-toggle="tab">Methodology</a></li>


				<li><a href="#tool" data-toggle="tab">Tools</a></li>
				<li><a href="#acc" data-toggle="tab">Accreditation</a></li>
			</ul>
			</c:if>
			<c:if test="${msg=='4'}">
						<ul class="nav nav-tabs nav-custom">
				<li ><a href="#home" data-toggle="tab">Technology
				</a></li>
				<li><a href="#messages" data-toggle="tab">Domain / Product</a></li>
				<li><a href="#profile" data-toggle="tab">Methodology</a></li>


				<li class="active"><a href="#tool" data-toggle="tab">Tools</a></li>
				<li><a href="#acc" data-toggle="tab">Accreditation</a></li>
			</ul>
			</c:if>
			<c:if test="${msg=='5'}">
						<ul class="nav nav-tabs nav-custom">
				<li ><a href="#home" data-toggle="tab">Technology
				</a></li>
				<li><a href="#messages" data-toggle="tab">Domain / Product</a></li>
				<li><a href="#profile" data-toggle="tab">Methodology</a></li>


				<li><a href="#tool" data-toggle="tab">Tools</a></li>
				<li class="active"><a href="#acc" data-toggle="tab">Accreditation</a></li>
			</ul>
			</c:if>
			<!-- Tab panes -->
			<div class="tab-content">
				<c:choose>
					<c:when test="${msg=='1'}">
						<div class="tab-pane fade in active tab_pane_cus" id="home">
					</c:when>
					<c:when test="${msg!='1'}">
						<div class="tab-pane fade in  tab_pane_cus" id="home">
					</c:when>
				</c:choose>
				
				<div class="panel panel-default gab">
						<div class="panel-heading">
							Self Assessment - Technology <span id="Derror" class="pull-right">
								Please fill all the fields marked with <span class="star"><b>*</b></span>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body   panel-body-custom">

							<table class="table table_center table-bordered table-hover"
								id="tab_logic">
								<thead>
									<tr>
										<th>Is Primary ?</th>
										<th>Area of work<span class="star"><b>*</b></span></th>
										<th>Stream<span class="star"><b>*</b></span></th>
										<th>Skill<span class="star"><b>*</b></span></th>
										<th class="col-lg-4">Experience<span class="star"><b>*</b></span></th>
										<th>Self Assessment<span class="star"><b>*</b></span></th>
										<th>Comments</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<form:form
											action="${pageContext.request.contextPath}/updateCoreSkill"
											commandName="ProficiencySkillObject" data-toggle="validator">

											<td><span><form:checkbox value="Y"
														path="isPrimary" id="isPrime" /></span></td>
											<td><form:select class="form-control" id="Pareaofwork"
													path="areaId">
													<form:option value="0">Select</form:option>
													<c:forEach items="${areaofworkList}" var="areaofworkList"
														varStatus="">
														<form:option value="${areaofworkList.areaId}">${areaofworkList.areaWork}</form:option>
													</c:forEach>
												</form:select></td>
											<td><form:select class="form-control" id="pstream"
													path="streamId">
													<form:option value="0">Select</form:option>
												</form:select></td>
											<td><form:select class="form-control" id="pskill"
													path="skillId">
													<form:option value="0">Select</form:option>

												</form:select></td>


											<td>
												<div class="col-lg-6">
													<form:select name="tExpInYear1"
														class="form-control required" id="pexpY"
														path="experienceYear">
														<option>Year</option>
														<script type="text/javaScript">
															for (i = 0; i <= expY; i++) {
																document
																		.write('<option val='+i+'>'
																				+ i
																				+ '</option>');
															}
														</script>
													</form:select>
												</div>
												<div class="col-lg-6">
													<form:select name="tExpInMonth1" class="form-control"
														id="pexpM" path="experienceMonth">
														<option>Month</option>
														<script type="text/javaScript">
															for (i = 0; i <= 11; i++) {
																document
																		.write('<option value='+i+'>'
																				+ i
																				+ '</option>');
															}
														</script>
														<script type="text/javaScript">
															var option = new Array();
															function TechMonth(
																	month) {

																$('#pexpM')
																		.html(
																				"<option value='0'>Month</option>");
																var month = month;
																for (i = 0; i <= month; i++) {

																	option[i] = '<option value='+i+'>'
																			+ i
																			+ '</option>';

																	$('#pexpM')
																			.append(
																					option[i]);
																}

															}
														</script>
														<!-- 	
														<script type="text/javaScript">
														
															for (i = 0; i <= expM; i++) {
																document
																		.write('<option val='+i+'>'
																				+ i
																				+ '</option>');
															}
														</script> -->
													</form:select>
												</div>
											</td>

											<td><form:select class="form-control" id="pself"
													path="selfAssesment">
													<option>Select</option>
													

													<script type="text/javaScript">
														var option = new Array();
														function SelfRating(
																Rating) {

															$('#pself')
																	.html(
																			"<option>Select</option>");
															var Rating = Rating;

															for (i = 1; i <= Rating; i++) {
																
																if(i==1)
																{
																option[i] = '<option val='+i+'>'
																+i +"- Novice"
																+ '</option>';
																}
															else if(i==2)
																{
																option[i] = '<option val='+i+'>'
																+i +" - Learner"
																+ '</option>';
																}
															else if(i==3)
															{
															option[i] = '<option val='+i+'>'
															+i +" - Intermediate"
															+ '</option>';
															}
															else if(i==4)
															{
															option[i] = '<option val='+i+'>'
															+i +" - Master"
															+ '</option>';
															}
															else if(i==5)
															{
															option[i] = '<option val='+i+'>'
															+i +" - Expert"
															+ '</option>';
															} 
																
																/* option[i] = '<option val='+i+'>'
																		+ i
																		+ '</option>'; */

																$('#pself')
																		.append(
																				option[i]);
															}

														}
													</script>
													
												</form:select></td>

											<td><form:input type="text" name='mail0' maxlength="100"
													placeholder='max 100character' id="comm"
													class="form-control" path="comments" /></td>

											<td>


												<button class="btn btn-danger disabled " id="Technologysave"
													type="button" ">Save</button>
											</td>
										</form:form>
									</tr>
									<!-- 	<tr> -->
									<c:forEach items="${coreSkills}" var="coreSkills" varStatus="">
										<c:if test="${coreSkills.isPrimary =='Y'}">
											<tr class="table-border-primary">
										</c:if>
										<c:if test="${coreSkills.isPrimary =='N'}">
											<tr>
										</c:if>
										<c:if test="${coreSkills.isPrimary =='Y'}">
											<td><span><input type="checkbox" checked disabled
													value="" path="isPrimary"></span></td>
										</c:if>
										<c:if test="${coreSkills.isPrimary =='N'}">
											<td><span><input type="checkbox" disabled
													value="" path="isPrimary"></span></td>
										</c:if>
										<td>${coreSkills.areaOfWork}</td>
										<td>${coreSkills.technology}</td>
										<td><span id="TechName">${coreSkills.skill}</span></td>
										<%-- <td>${coreSkills.expYears}Years<br />${coreSkills.expMonths}
												Months
											</td> --%>
										<td>
											<div class="col-lg-6">
												<%-- <span> ${domain.dExpYears}Years</span> --%>
												<div class="row">
													<div class="col-lg-8">

														<select class="form-control" id="techenableY"
															onchange="monthYearTech($(this));" disabled>
															<option>${coreSkills.expYears}</option>
															<script type="text/javaScript">
																for (i = 0; i <= expY; i++) {
																	document
																			.write('<option val='+i+'>'
																					+ i
																					+ '</option>');
																}
															</script>
														</select>
													</div>
													<div class="col-lg-4">Yr(s)</div>

												</div>
											</div>
											<div class="col-lg-6">
												<%-- <span>${domain.dExpMonths}Months</span> --%>
												<div class="row">
													<div class="col-lg-8">

														<select class="form-control " id="techenableM" disabled>
															<option>${coreSkills.expMonths}</option>
															<script type="text/javaScript">
																for (i = 0; i <= 11; i++) {
																	document
																			.write('<option val='+i+'>'
																					+ i
																					+ '</option>');
																}
															</script>
														</select>
													</div>
													<div class="col-lg-4">Mn(s)</div>

												</div>
											</div>
										</td>
										<td>${coreSkills.empRating}</td>
										<td>${coreSkills.empComments}</td>
										<!-- 			<td id="editUpdate"><button class="btn btn-danger"
												onclick="editTech($(this));" type="button" id="Dedit">Edit</button></td>
										 -->
										 <td>NA</td>
									</c:forEach>
									<!-- </tr> -->
								</tbody>
							</table>

						</div>
					</div>

				</div>

	<c:choose>
					<c:when test="${msg=='2'}">
						<div class="tab-pane fade in active tab_pane_cus" id="messages">
					</c:when>
					<c:when test="${msg!='2'}">
						<div class="tab-pane fade in  tab_pane_cus" id="messages">
					</c:when>
				</c:choose>
					<div class="panel panel-default gab">
						<div class="panel-heading">
							Self Assessment - Domain/Product <span id="Derror"
								class="pull-right"> Please fill all the fields marked
								with <span class="star"><b>*</b></span>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">

							<table class="table table_center table-bordered table-hover"
								id="domainTable">
								<thead>
									<tr>
										<th>Is Primary ?</th>
										<!-- 										<th>Area of Work<span class="star"><b>*</b></span></th>
 -->
										<!-- <th>Domain Category<span class="star"><b>*</b></span></th> -->
										<th>Domain<span class="star"><b>*</b></span></th>
										<!-- <th>Domain<span class="star"><b>*</b></span></th> -->
										<th>Sub Domain<span class="star"><b>*</b></span></th>
										<th class="col-lg-4">Experience<span class="star"><b>*</b></span></th>
										<th>Self Assessment<span class="star"><b>*</b></span>
										</th>
										<th>Comments</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<form:form
											action="${pageContext.request.contextPath}/updateDomain"
											commandName="ProficiencyDomainObject">
											<td><span><form:checkbox value="Y" id="isDPrime"
														path="isPrimary" /></span></td>

											<%-- <td><form:select class="form-control" id="dareaofwork"
													path="areaId">
													<form:option value="0">Select</form:option>
													<c:forEach items="${domainAreaofworkResponse}" var="areaofworkList"
														varStatus="">
														<form:option value="${areaofworkList.areaId}">${areaofworkList.areaWork}</form:option>
													</c:forEach>
												</form:select></td> --%>
											<td><form:select class="form-control" id="Ddomain"
													path="sGroup">
													<option>Select</option>
													<c:forEach items="${domainList}" var="domainList"
														varStatus="">
														<form:option value="${domainList.categoryName}">${domainList.categoryName}</form:option>
													</c:forEach>
												</form:select></td>
											<td><form:select class="form-control" id="Dsub"
													path="dName">
													<form:option value="Select">Select</form:option>


												</form:select></td>
											<td>
												<div class="col-lg-6">
													<form:select name="tExpInYear1"
														class="form-control required" id="DexpY" path="dExpYears">
														<option>Year</option>
														<script type="text/javaScript">
															for (i = 0; i <= expY; i++) {
																document
																		.write('<option val='+i+'>'
																				+ i
																				+ '</option>');
															}
														</script>
													</form:select>
												</div>
												<div class="col-lg-6">
													<form:select name="tExpInMonth1" class="form-control"
														id="DexpM" path="dExpMonths">
														<option val="0">Month</option>
														<script type="text/javaScript">
															for (i = 0; i <= 11; i++) {
																document
																		.write('<option value='+i+'>'
																				+ i
																				+ '</option>');
															}
														</script>
														<script type="text/javaScript">
															var option = new Array();
															function DomainMonth(
																	month) {

																$('#DexpM')
																		.html(
																				"<option value='0'>Month</option>");
																var month = month;
																for (i = 0; i <= month; i++) {

																	option[i] = '<option value='+i+'>'
																			+ i
																			+ '</option>';

																	$('#DexpM')
																			.append(
																					option[i]);
																}

															}
														</script>
													</form:select>
												</div>
											</td>
											<td><form:select class="form-control" id="Drate"
													path="empRating">
													<option>Select</option>
													<script type="text/javaScript">
														var option = new Array();
														function SelfRatingDomain(
																Rating) {
															$('#Drate')
																	.html(
																			"<option>Select</option>");
															var Rating = Rating;
															for (i = 1; i <= Rating; i++) {
																/*option[i] = '<option val='+i+'>'
																+i 
																+ '</option>';*/
																 if(i==1)
																	{
																	option[i] = '<option val='+i+'>'
																	+i +"- Novice"
																	+ '</option>';
																	}
																else if(i==2)
																	{
																	option[i] = '<option val='+i+'>'
																	+i +" - Learner"
																	+ '</option>';
																	}
																else if(i==3)
																{
																option[i] = '<option val='+i+'>'
																+i +" - Intermediate"
																+ '</option>';
																}
																else if(i==4)
																{
																option[i] = '<option val='+i+'>'
																+i +" - Master"
																+ '</option>';
																}
																else if(i==5)
																{
																option[i] = '<option val='+i+'>'
																+i +" - Expert"
																+ '</option>';
																} 
																
																$('#Drate')
																		.append(
																				option[i]);
															}

														}
													</script>
												</form:select></td>
											<td><form:input type="text" name='mail0' id="commt"
													maxlength="100" placeholder='max 100character'
													class="form-control" path="empComments" /></td>

											<td>
												<button class="btn btn-danger disabled" id="Dsave"
													type="button">Save</button>
											</td>
										</form:form>
									</tr>
									<c:forEach items="${domain}" var="domain" varStatus="pa">
										<c:if test="${domain.isPrimary =='Y'}">
											<tr id='${pa.index}' style="border: 3px solid #5cb85c;">
										</c:if>
										<c:if test="${domain.isPrimary =='Y'}">
											<tr id='${pa.index}'>
										</c:if>
										<c:if test="${domain.isPrimary =='Y'}">
											<td><span><input type="checkbox" checked disabled
													value="" path="isPrimary"></span></td>
										</c:if>
										<c:if test="${domain.isPrimary =='N'}">
											<td><span><input type="checkbox" disabled
													value="" path="isPrimary"></span></td>
										</c:if>
										<%-- 										<td> <span> ${domain.areaName}</span></td>
 --%>
										<td><span> ${domain.dName}</span></td>
										<td><span id="dName">${domain.sGroup}</span></td>
										<td>
											<div class="col-lg-6">
												<%-- <span> ${domain.dExpYears}Years</span> --%>
												<div class="row">
													<div class="col-lg-8">

														<select class="form-control" id="denableY"
															onchange="monthYearDomain($(this));" disabled>
															<option>${domain.dExpYears}</option>
															<script type="text/javaScript">
																for (i = 0; i <= expY; i++) {
																	document
																			.write('<option val='+i+'>'
																					+ i
																					+ '</option>');
																}
															</script>
														</select>
													</div>
													<div class="col-lg-4">Yr(s)</div>

												</div>
											</div>
											<div class="col-lg-6">
												<%-- <span>${domain.dExpMonths}Months</span> --%>
												<div class="row">
													<div class="col-lg-8">

														<select class="form-control " id="denableM" disabled>
															<option>${domain.dExpMonths}</option>
															<script type="text/javaScript">
																for (i = 0; i <= 11; i++) {
																	document
																			.write('<option val='+i+'>'
																					+ i
																					+ '</option>');
																}
															</script>
														</select>
													</div>
													<div class="col-lg-4">Mn(s)</div>

												</div>
											</div>
										</td>
										<td><span>${domain.empRating}</span></td>
										<td><span>${domain.empComments}</span></td>
										 <td>NA</td>
										<!-- <td id="editUpdate"><button class="btn btn-danger"
												onclick="editDomain($(this));" type="submit" id="Dedit">Edit</button></td>
										 -->
										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>
						<!-- /.panel-body -->
					</div>
				</div>
				<c:choose>
					<c:when test="${msg=='3'}">
						<div class="tab-pane fade in active tab_pane_cus" id="profile">
					</c:when>
					<c:when test="${msg!='3'}">
						<div class="tab-pane fade in  tab_pane_cus" id="profile">
					</c:when>
				</c:choose>
				

					<div class="panel panel-default gab">
						<div class="panel-heading">
							Self Assessment - Methodology <span id="Merror"
								class="pull-right ">Please fill all the fields marked
								with <span class="star"><b>*</b></span>
							</span>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">

							<table class="table table_center table-bordered table-hover"
								id="tab_logic">
								<thead>
									<tr>

										<th>Methodology <span class="star"><b>*</b></span></th>
										<th>Experience<span class="star"><b>*</b></span></th>
										<th>Self Assessment<span class="star"><b>*</b></span></th>
										<th>Comments</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<form:form
											action="${pageContext.request.contextPath}/updateMethodology"
											commandName="ProficiencyMethodologyObject">

											<td><form:select class="form-control" id="Mmethodology1"
													path="mName">
													<form:option value="0">Select</form:option>
													<c:forEach items="${methodologyList}" var="methodologyList"
														varStatus="">
														<form:option value="${methodologyList.methodologyId}">${methodologyList.methodologyName}</form:option>
													</c:forEach>
												</form:select></td>
											<td>
												<div class="col-lg-6">
													<form:select name="tExpInYear1"
														class="form-control required" id="MexpY1" path="mExpYears">
														<option>Year</option>
														<script type="text/javaScript">
															for (i = 0; i <= expY; i++) {
																document
																		.write('<option val='+i+'>'
																				+ i
																				+ '</option>');
															}
														</script>
													</form:select>
												</div>
												<div class="col-lg-6">
													<form:select name="tExpInMonth1" class="form-control"
														id="Mexp1" path="mExpMonths">
														<option val="0">Month</option>
														<script type="text/javaScript">
															for (i = 0; i <= 11; i++) {
																document
																		.write('<option value='+i+'>'
																				+ i
																				+ '</option>');
															}
														</script>
														<script type="text/javaScript">
															var option = new Array();
															function MethodMonth(
																	month) {

																$('#Mexp1')
																		.html(
																				"<option value='0'>Month</option>");
																var month = month;

																for (i = 0; i <= month; i++) {

																	option[i] = '<option value='+i+'>'
																			+ i
																			+ '</option>';

																	$('#Mexp1')
																			.append(
																					option[i]);
																}

															}
														</script>
													</form:select>
												</div>
											</td>
											<td><form:select class="form-control" id="Mrate1"
													path="empRating">
													<option>Select</option>
													<script type="text/javaScript">
														var option = new Array();
														function SelfRatingMethod(
																Rating) {
															$('#Mrate1')
																	.html(
																			"<option>Select</option>");
															var Rating = Rating;
															for (i = 1; i <= Rating; i++) {
																
																if(i==1)
																{
																option[i] = '<option val='+i+'>'
																+i +"- Novice"
																+ '</option>';
																}
															else if(i==2)
																{
																option[i] = '<option val='+i+'>'
																+i +" - Learner"
																+ '</option>';
																}
															else if(i==3)
															{
															option[i] = '<option val='+i+'>'
															+i +" - Intermediate"
															+ '</option>';
															}
															else if(i==4)
															{
															option[i] = '<option val='+i+'>'
															+i +" - Master"
															+ '</option>';
															}
															else if(i==5)
															{
															option[i] = '<option val='+i+'>'
															+i +" - Expert"
															+ '</option>';
															} 
																
																/* option[i] = '<option val='+i+'>'
																		+ i
																		+ '</option>'; */

																$('#Mrate1')
																		.append(
																				option[i]);
															}
														}
													</script>
												</form:select></td>

											<td><form:input type="text" name='mail0' maxlength="100"
													placeholder='max 100character' class="form-control"
													path="comments" id="Mcomments" /></td>
											<td>
												<button class="btn btn-danger disabled " id="Msave1"
													type="button" onclick="saveMethodology()">Save</button>
											</td>
										</form:form>
									</tr>
									<c:forEach items="${methodology}" var="methodologyLists">
										<tr id='addr0'>

											<td><span id="Mname">${methodologyLists.mName}</span></td>
											<td>

												<div class="col-lg-6">

													<div class="row">
														<div class="col-lg-8">

															<select class="form-control" id="MenableY"
																onclick="monthYearMethod($(this));" disabled>
																<option>${methodologyLists.mExpYears}</option>
																<script type="text/javaScript">
																	for (i = 0; i <= expY; i++) {
																		document
																				.write('<option val='+i+'>'
																						+ i
																						+ '</option>');
																	}
																</script>
															</select>
														</div>
														<div class="col-lg-4">Yr(s)</div>

													</div>
												</div>
												<div class="col-lg-6">
													<%-- <span>${domain.dExpMonths}Months</span> --%>
													<div class="row">
														<div class="col-lg-8">

															<select class="form-control " id="MenableM" disabled>
																<option>${methodologyLists.mExpMonths}</option>
																<script type="text/javaScript">
																	for (i = 0; i <= 11; i++) {
																		document
																				.write('<option val='+i+'>'
																						+ i
																						+ '</option>');
																	}
																</script>
															</select>
														</div>
														<div class="col-lg-4">Mn(s)</div>

													</div>
												</div>

											</td>
											<td><span>${methodologyLists.empRating}</span></td>
											<td><span>${methodologyLists.comments}</span></td>
											 <td>NA</td>
											<!-- <td id="editUpdate"><button class="btn btn-danger"
													onclick="editMethodology($(this));" type="submit"
													id="Dedit">Edit</button></td> -->

										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>
						<!-- /.panel-body -->
					</div>
				</div>
					<c:choose>
					<c:when test="${msg=='4'}">
						<div class="tab-pane fade in active tab_pane_cus" id="tool">
					</c:when>
					<c:when test="${msg!='4'}">
						<div class="tab-pane fade in  tab_pane_cus" id="tool">
					</c:when>
				</c:choose>
					<div class="panel panel-default gab">
						<div class="panel-heading">
							Self Assessment - Tools<span id="Terror" class="pull-right ">Please
								fill all the fields marked with <span class="star"><b>*</b></span>
							</span>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<table class="table table_center table-bordered table-hover"
								id="tab_logic">
								<thead>
									<tr>
										<th>Tool Type<span class="star"><b>*</b></span></th>
										<th>Tool<span class="star"><b>*</b></span></th>
										<th class="col-lg-4">Experience<span class="star"><b>*</b></span></th>
										<th>Self Assessment<span class="star"><b>*</b></span>
										</th>
										<th>Comments</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<form:form
											action="${pageContext.request.contextPath}/updateTool"
											commandName="ProficiencyToolObject">
											<td><form:select class="form-control" id="TTtool"
													path="toolType">
													<form:option value="Select">Select</form:option>
													<c:forEach items="${toolsTypeList}" var="toolstypeList"
														varStatus="">
														<form:option value="${toolstypeList.toolTName}">${toolstypeList.toolTName}</form:option>
													</c:forEach>
												</form:select></td>
											<td><form:select class="form-control" id="Ttool"
													path="toolName">
													<form:option value="Select">Select</form:option>

												</form:select></td>
											<td>
												<div class="col-lg-6">
													<form:select name="tExpInYear1"
														class="form-control required" id="TexpY" path="tExpYears">
														<option>Year</option>
														<script type="text/javaScript">
															for (i = 0; i <= expY; i++) {
																document
																		.write('<option val='+i+'>'
																				+ i
																				+ '</option>');
															}
														</script>
													</form:select>
												</div>
												<div class="col-lg-6">
													<form:select name="tExpInMonth1" class="form-control"
														id="Texp" path="tExpMonths">
														<option val="0">Month</option>
														<script type="text/javaScript">
															for (i = 0; i <= 11; i++) {
																document
																		.write('<option value='+i+'>'
																				+ i
																				+ '</option>');
															}
														</script>
														<script type="text/javaScript">
															var option = new Array();
															function ToolMonth(
																	month) {

																$('#Texp')
																		.html(
																				"<option value='0'>Month</option>");
																var month = month;

																for (i = 0; i <= month; i++) {

																	option[i] = '<option value='+i+'>'
																			+ i
																			+ '</option>';

																	$('#Texp')
																			.append(
																					option[i]);
																}

															}
														</script>
													</form:select>
												</div>
											</td>
											<td><form:select class="form-control" id="Trate"
													path="empRating">
													<option>Select</option>
													<script type="text/javaScript">
														var option = new Array();
														function SelfRatingTool(
																Rating) {
															$('#Trate')
																	.html(
																			"<option>Select</option>");
															var Rating = Rating;
															for (i = 1; i <= Rating; i++) {

																
																if(i==1)
																{
																option[i] = '<option val='+i+'>'
																+i +"- Novice"
																+ '</option>';
																}
															else if(i==2)
																{
																option[i] = '<option val='+i+'>'
																+i +" - Learner"
																+ '</option>';
																}
															else if(i==3)
															{
															option[i] = '<option val='+i+'>'
															+i +" - Intermediate"
															+ '</option>';
															}
															else if(i==4)
															{
															option[i] = '<option val='+i+'>'
															+i +" - Master"
															+ '</option>';
															}
															else if(i==5)
															{
															option[i] = '<option val='+i+'>'
															+i +" - Expert"
															+ '</option>';
															} 
																/* option[i] = '<option val='+i+'>'
																		+ i
																		+ '</option>';
 */
																$('#Trate')
																		.append(
																				option[i]);
															}
														}
													</script>
												</form:select></td>
											<td><form:input type="text" name='mail0' maxlength="100"
													placeholder='max 100character' class="form-control"
													path="empComments" id="Tcomments" /></td>
											<td>
												<button class="btn btn-danger disabled" id="Tsave"
													type="button" onclick="saveTools()">Save</button>
											</td>
										</form:form>
									</tr>
									<c:forEach items="${tool}" var="tool">
										<tr id='addr0'>
											<td><span>${tool.toolType}</span></td>
											<td><span id="Tname">${tool.toolName} </span></td>
											<td>

												<div class="col-lg-6">
													<%-- <span> ${domain.dExpYears}Years</span> --%>
													<div class="row">
														<div class="col-lg-8">

															<select class="form-control" id="TenableY"
																onclick="monthYearTool($(this));" disabled>
																<option>${tool.tExpYears}</option>
																<script type="text/javaScript">
																	for (i = 0; i <= expY; i++) {
																		document
																				.write('<option val='+i+'>'
																						+ i
																						+ '</option>');
																	}
																</script>
															</select>
														</div>
														<div class="col-lg-4">Yr(s)</div>

													</div>
												</div>
												<div class="col-lg-6">
													<%-- <span>${domain.dExpMonths}Months</span> --%>
													<div class="row">
														<div class="col-lg-8">

															<select class="form-control " id="TenableM" disabled>
																<option>${tool.tExpMonths}</option>
																<script type="text/javaScript">
																	for (i = 0; i <= 11; i++) {
																		document
																				.write('<option val='+i+'>'
																						+ i
																						+ '</option>');
																	}
																</script>
															</select>
														</div>
														<div class="col-lg-4">Mn(s)</div>

													</div>
												</div>
											</td>
											<td><span>${tool.empRating}</span></td>
											<td><span>${tool.empComments}</span></td>
											 <td>NA</td>
											<!-- <td id="editUpdate"><button class="btn btn-danger"
													onclick="editTool($(this));" type="submit" id="Dedit">Edit</button></td>
										 -->
										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>
						<!-- /.panel-body -->
					</div>
				</div>
	<c:choose>
					<c:when test="${msg=='5'}">
						<div class="tab-pane fade in active tab_pane_cus" id="acc">
					</c:when>
					<c:when test="${msg!='5'}">
						<div class="tab-pane fade in  tab_pane_cus" id="acc">
					</c:when>
				</c:choose>
				
					<%-- <c:if test="${msg !='update'}">
						<div class="text-center" style="margin-top: 5px;">
							<span class="errormsg">${msg}</span>
						</div>
					</c:if> --%>
					<div class="panel panel-default gab">
						<div class="panel-heading">
							Self Assessment - Accreditation<span id="Aerror"
								class="pull-right ">Please fill all the fields marked
								with <span class="star"><b>*</b></span>
							</span>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<table class="table table_center table-bordered table-hover"
								id="tab_logic">
								<thead>
									<tr>
										<th>Name of Certificate<span class="star"><b>*</b></span></th>
										<th>Certificate Number<span class="star"><b>*</b></span></th>
										<th>Valid From <span class="star"><b>*</b></span></th>
										<!-- 										<th>Expiry Date</th>
 -->
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<form:form
										action="${pageContext.request.contextPath}/updateAcc"
										commandName="ProficiencyAccObject">
										<tr id='addr1'>
											<td><form:select class="form-control" id="Acertified"
													path="accreditationName">
													<form:option value="Select">Select</form:option>
													<c:forEach items="${allAccreditationList}"
														var="accreditationList">
														<form:option value="${accreditationList.accID}">${accreditationList.accreditationName}</form:option>
													</c:forEach>
												</form:select></td>


											<td><form:input type="text" class="form-control"
													placeholder="Certificate No" id="Aname"
													path="certificateNo" /></td>
											<td><form:input type="text" class="form-control"
													readonly="true" placeholder="DD-MM-YYYY" path="issueDt"
													style="padding: inherit;" data-provide="datepicker"
													id="startDatePicker"></form:input></td>
											<%-- <td>
												<form:input type="text" class="form-control" readonly="readonly"
												 placeholder="Expiry Date" path="expiryDate"
												id="endDatePicker"></form:input>

											</td> --%>

											<td>

												<button class="btn btn-danger disabled" id="Asave"
													type="button" onclick="saveAccer()">Save</button>
											</td>
										</tr>
									</form:form>
									<c:forEach items="${accreditationList}" var="accreditationList">
										<tr id='addr0'>
											<td><span>${accreditationList.accreditationName}
											</span></td>

											<td><span>${accreditationList.cNo}</span></td>
											<td><span>${accreditationList.issueDate}</span></td>
											 <td>NA</td>
											<%-- <td><span>${accreditationList.expiryDate}</span></td> --%>

											<!-- <td><span></span></td> -->
										</tr>
									</c:forEach>
								</tbody>
							</table>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--  pop up message -->



<jsp:include page="footer.jsp" />
<script>
$(document).ready( function() {
	
 	var flag = true;	

	
		
		

		<c:forEach items="${coreSkills}" var="coreSkills" varStatus="">
		<c:if test="${coreSkills.isPrimary =='Y'}">
			flag=false;
		</c:if>
		</c:forEach>
		
		<c:forEach items="${domain}" var="domain" varStatus="">
		<c:if test="${domain.isPrimary =='Y'}">
			flag=false;
		</c:if>
		</c:forEach>
		 
		
		$('#Dsave').on('click',function(){
			var isPrimary="0";
			if($("#isDPrime").is(':checked')) 
			{
				isPrimary="1";
			}	
			
			var dc = $('#Ddomain').val();
			var d = $('#Dsub').val();
			var experienceYear = $('#DexpY').val();
			var experienceMonth = $('#DexpM').val();;
			var comments=$('#commt').val();
			//var selfAssesment=document.getElementById("Drate").value;//$('#Drate').val();
			var selfAssesment=parseInt(document.getElementById("Drate").value);

			var proficiencyDomain={
					"isPrimary":isPrimary,
					"sGroup":dc,
					"dName":d,
					"dExpYears":experienceYear,
					"dExpMonths":experienceMonth,
					"empComments":comments,
					"empRating":selfAssesment,

			};
			
			var popupString = "You will not be allowed to change once saved. Are you sure to save?";
			if(flag){
				popupString ="You will not be allowed to change once saved. Are you sure to save?";
			}else if(isPrimary==="1"){
				popupString ="Primary is selected would you like to change it";
			}
			var result = confirm(popupString);
			if(result)
				{
			var path1 = document.getElementById("contextPaht").value;
			$.ajax({
				type : "POST",
				url : path1 + "/updateDomain",
				data:proficiencyDomain,
				success : function(message) {

		alert(message);
		location.href=path1+"/updateProficiency/2";

				},
				error : function(xhr) {
					alert("try again");
				}
			});
				}
			else
				{
				return false;
				}
		});
		
		$('#Technologysave').on('click',function(){
		
		
		var isPrimary="0";
		if($("#isPrime").is(':checked')) 
		{
			isPrimary="1";
		}	
		
		var skillId = $('#pskill').val();
		var experienceYear = $('#pexpY').val();
		var experienceMonth = $('#pexpM').val();;
		var comments=$('#comm').val();
		var selfAssesment=parseInt(document.getElementById("pself").value);//$('#pself').val();
		

		var proficiencyCoreSkill={
				"isPrimary":isPrimary,
				"skillId":skillId,
				"experienceYear":experienceYear,
				"experienceMonth":experienceMonth,
				"comments":comments,
				"selfAssesment":selfAssesment,

		};
		var popupString = "You will not be allowed to change once saved. Are you sure to save?";
		if(flag){
			popupString ="You will not be allowed to change once saved. Are you sure to save?";
		}else if(isPrimary==="1"){
			popupString ="Primary is selected would you like to change it";
		}
		var result = confirm(popupString);
		if(result)
			{
		
		var path1 = document.getElementById("contextPaht").value;
		$.ajax({
			type : "POST",
			url : path1 + "/updateCoreSkill",
			data:proficiencyCoreSkill,
			success : function(message) {

	alert(message);
	location.href=path1+"/updateProficiency/1";

			},
			error : function(xhr) {
				alert("try again");
			}
		});
			}
		else
			{
			return false;
			}
		
		
		
	});
	
	
});
</script>
<script
	src="${pageContext.servletContext.contextPath}/resources/js/myscriptUpdateProficiency.js"></script>
