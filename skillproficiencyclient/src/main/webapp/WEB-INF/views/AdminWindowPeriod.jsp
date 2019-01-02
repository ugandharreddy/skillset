<%@include file="header.jsp"%>
<% String empList=(String)request.getAttribute("empList");
System.out.println(empList);
%>
	<div class="panel panel-danger">
		<div class="panel-heading text-center">
							<font size="3"><b>Admin Window Period</b></font>
						</div>
		<div class="panel-body">
								
			<div class="col-xs-12 text-center gab next">
				<div class="col-lg-4 col-xs-4  col-lg-offset-1">
					<span class="star pull-right"><b>Organisation Level :</b></span>
				</div>
				<div class="col-lg-2 col-xs-2">
					<select class="form-control" id="orglevel" onchange="show()">
						<option value="select">Select</option>

						<option value="organisation">Organisation</option>
						<option value="bu">BU</option>
						<option value="account">Account</option>
						<option value="project">Project</option>
						<option value="employee">Employee</option>
						
					</select>
				</div>
			
			</div>

 <div class="col-xs-12 text-center gab next orgShow"
			style="display: none">
			<div class="col-lg-4 col-xs-4  col-lg-offset-1">
				<span class="star pull-right"><b>Organisation :</b></span>
			</div>
			<div class="col-lg-2 col-xs-2">
				<select class="form-control" id="org">
					<option value=1>Select</option>

					

				</select>
			</div> 

		</div> 



	  <div class="col-xs-12 text-center gab next buShow"
			style="display: none">
			<div class="col-lg-4 col-xs-4  col-lg-offset-1">
				<span class="star pull-right"><b>BU* :</b></span>
			</div>
			<div class="col-lg-2 col-xs-2">
				<select class="form-control" id="bu">
					<option value=0>Select</option>

					<c:forEach items="${bulist}" var="allBuList">
				
						<option value="${allBuList.buID}">${allBuList.buName}</option>

					</c:forEach>

				</select>
			</div> 

		</div> 

		<div class="col-xs-12 text-center gab next accountShow"
			style="display: none">
			<div class="col-lg-4 col-xs-4  col-lg-offset-1">
				<span class="star pull-right"><b>Account* :</b></span>
			</div>
			<div class="col-lg-2 col-xs-2">
				<select class="form-control" id="account">
					<option value="0">Select</option>
			<%-- 		<c:forEach items="${accList}" var="allAccList">
				
						<option value="${allAccList.accountID}">${allAccList.accountName}</option>

					</c:forEach> --%>

				</select>
			</div>

		</div>

		<div class="col-xs-12 text-center gab next projectShow"
			style="display: none">
			<div class="col-lg-4 col-xs-4  col-lg-offset-1">
				<span class="star pull-right"><b>Project* :</b></span>
			</div>
			<div class="col-lg-2 col-xs-2">
				<select class="form-control" id="project">
					<option value="0">Select</option>
<%-- 
				<c:forEach items="${accList}" var="allProjectList">
				
						<option value="${allProjectList.projectID}">${allProjectList.projectName}</option>

					</c:forEach> --%>

				</select>
			</div>

		</div>
		<div class="col-xs-12 text-center gab next employeeShow" style="display:none">
				<div class="col-lg-4 col-xs-4  col-lg-offset-1">
					<span class="star pull-right"><b>Employee* :</b></span>
				</div>
				<div class="col-lg-2 col-xs-2">
                <input type="text" id="employeename" class="form-control"></input>
                <ul id="uol" style="display:none"></ul>
				</div>
			
			</div>
			
			
			<div class="col-xs-12 showDiv" style="display:none">
			<div class="col-xs-12 text-center gab next ">
				<div class="col-lg-4 col-xs-4  col-lg-offset-1">
					<span class="star pull-right"><b>Window Period* :</b></span>
				</div>
				<div class="col-lg-2 col-xs-2">
<input  type="text"class="form-control" maxlength="3" onkeypress="return isNumber(event)"  id="windowperiod" placeholder="no of days"/>
				</div>
			
			</div>
			<div class="col-xs-12 text-center gab next ">
				<div class="col-lg-4 col-xs-4  col-lg-offset-1">
					<span class="star pull-right"><b>Start Date* :</b></span>
				</div>
				<div class="col-lg-2 col-xs-2">
					 
					<input type="text" class="form-control" 
													readonly="true" placeholder="DD-MM-YYYY" path="issueDt"
													style="padding: inherit;" data-provide="datepicker"
													id="startDatePicker"></input>
				</div>
			
			</div>
			
			</div>
			<div class="col-xs-12 text-center">
			<p id="error" style="color: #ff6666"></p>
		</div>

			<div class="next gab">&nbsp;</div>
          		
							
		<%-- 	<div class="col-xs-2 gab">
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
			</div>  --%>
			<div class="col-xs-12 text-center  gab">
			 <div class="col-xs-3 text-center  gab">
			</div>
			<div class="col-xs-6 text-center  gab">
				<button class="btn btn-danger " id="clearBtn" disabled style=margin-top:28px;>Clear</button>
				<button class="btn btn-danger " id="assignBtn" disabled onclick="saveWindow()" style=margin-top:28px;>Assign</button>
			</div>
			 <div class="col-xs-3 text-center  gab">
			</div> 
			</div>
			
			

</div>
<!-- /.col-lg-12 -->
</div>
<%@include file="footer.jsp"%>
 <%-- <script
	src="${pageContext.servletContext.contextPath}/resources/js/temp.js"></script>
 --%>	
<!--  <link src="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">
 <script href="//code.jquery.com/jquery-1.12.3.js"> </script>
<script href="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script> -->




 <script
	src="${pageContext.servletContext.contextPath}/resources/js/AdminWindowPeriod.js"></script>