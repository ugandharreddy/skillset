<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page language="java" session="true"%>

<%-- <%@page import="org.apache.catalina.Session"%>
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
Object uname = request.getSession().getAttribute("role");

	Object expY = request.getSession().getAttribute("expY");
	Object expM = request.getSession().getAttribute("expM");
	Object windowValue = request.getSession().getAttribute("windowValue");

	
	/* out.print(expY);
	out.print(expM); */
%>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery.js"></script>
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> 
<link
	href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.servletContext.contextPath}/resources/css/jquery.editable-select.min.css"
	rel="stylesheet">  
<link
	href="${pageContext.servletContext.contextPath}/resources/css/datepicker.min.css"
	rel="stylesheet">

<link href="<%=request.getContextPath()%>/resources/css/mystyle.css"
	rel="stylesheet">
<!-- <link rel="stylesheet"
	href="http://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css"> -->



<script type="text/javascript">
	/* function noBack() {
		window.history.forward()
	}
	noBack();
	window.onload = noBack;
	window.onpageshow = function(evt) {
		if (evt.persisted)
			noBack()
	}
	window.onunload = function() {
		void (0)
	} */
	 var expY = "<%=expY%>";
	 var expM = "<%=expM%>";
	 var uname="<%=uname%>";
	 var windowValue="<%=windowValue%>";

	 
</script>
<style>
/* Admin role Submenu dropdown css */

.dropdown-submenu {
    position: relative;
}

.dropdown-submenu>.dropdown-menu {
    top: 0;
    left: 100%;
    margin-top: -6px;
    margin-left: -1px;
    -webkit-border-radius: 0 6px 6px 6px;
    -moz-border-radius: 0 6px 6px;
    border-radius: 0 6px 6px 6px;
}

.dropdown-submenu:hover>.dropdown-menu {
    display: block;
}
.dropdown-submenu>a:after {
    display: block;
    content: " ";
    float: right;
    width: 0;
    height: 0;
    border-color: transparent;
    border-style: solid;
    border-width: 5px 0 5px 5px;
    border-left-color: #ccc;
    margin-top: 5px;
    margin-right: -10px;
}

.dropdown-submenu:hover>a:after {
    border-left-color: #fff;
}

.dropdown-submenu.pull-left {
    float: none;
}

.dropdown-submenu.pull-left>.dropdown-menu {
    left: -100%;
    margin-left: 10px;
    -webkit-border-radius: 6px 0 6px 6px;
    -moz-border-radius: 6px 0 6px 6px;
    border-radius: 6px 0 6px 6px;
}
</style>
</head>
<body>

	<header > 
	<div class="row " style="background-color: #ec361e;
    color: white;     margin-bottom: 20px;">
	<div class="col-lg-5 col-sm-5">
	<nav class="navbar navbar-custom  next">

 	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#navbar">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>

	</div> 
	<div class="collapse navbar-collapse navbar-collapse-custom"
		id="navbar">
		
		<ul class="nav navbar-nav navbar-left">
			<c:set var="role" scope="session" value="<%=uname%>" />
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false"><span
					class="">Role: <%=uname%></span><img
					src="<%=request.getContextPath()%>/resources/images/drop.png"></a>
				<ul class="dropdown-menu" role="menu">

					<c:set var="userRole" scope="session" value="${role}"></c:set>
										<c:set var="windowValue" scope="session" value="${windowValue}"></c:set>
					

					<li><a href="<%=request.getContextPath()%>/home">Home</a></li>
					<c:choose>
						<c:when
							test="${userRole.contains('ADMIN')}">
							<%-- <li><a href="<%=request.getContextPath()%>/AdminAccrediation">Add Accredation</a></li>
							<li><a href="<%=request.getContextPath()%>/AdminAreaOfWork">Add AreaOfWork</a></li>
							<li><a href="<%=request.getContextPath()%>/AdminDomain">Add Domain</a></li>
							<li><a href="<%=request.getContextPath()%>/">Add DomainAreaOfWork</a></li>
							<li><a href="<%=request.getContextPath()%>/AdminSubDomain">Add Subdomain</a></li>
							<li><a href="<%=request.getContextPath()%>/AdminMethodology">Add Methodology</a></li>
							<li><a href="<%=request.getContextPath()%>/AdminSkill">Add Skill</a></li>
							<li><a href="<%=request.getContextPath()%>/AdminStream">Add Technology</a></li>
							<li><a href="<%=request.getContextPath()%>/AdminTool">Add Tool</a></li>
							<li><a href="<%=request.getContextPath()%>/AdminToolType">Add Tool Type</a></li>
							<li><a href="<%=request.getContextPath()%>/">Assign PL</a></li>
							<li><a href="<%=request.getContextPath()%>/AdminSMEforTech">Add SME Technology</a></li>
							<li><a href="<%=request.getContextPath()%>/AdminSMEforDomain">Add SME Domain</a></li>
							<li><a href="<%=request.getContextPath()%>/AdminAssignEmpRole">Assign Employee Role</a></li>
							<li><a href="<%=request.getContextPath()%>/">Add SME</a></li> --%>
							<li class="dropdown-submenu">
				                <a tabindex="-1" href="#">Admin Functionalities</a>
				                <ul class="dropdown-menu">
				                  <li><a href="<%=request.getContextPath()%>/AdminAccrediation">Add Accreditation</a></li>
										<li><a href="<%=request.getContextPath()%>/AdminAreaOfWork">Add AreaOfWork</a></li>
										<li><a href="<%=request.getContextPath()%>/AdminDomain">Add Domain</a></li>
										<%-- <li><a href="<%=request.getContextPath()%>/">Add DomainAreaOfWork</a></li> --%>
										<li><a href="<%=request.getContextPath()%>/AdminSubDomain">Add Subdomain</a></li>
										<li><a href="<%=request.getContextPath()%>/AdminMethodology">Add Methodology</a></li>
										<li><a href="<%=request.getContextPath()%>/AdminSkill">Add Skill</a></li>
										<li><a href="<%=request.getContextPath()%>/AdminStream">Add Technology</a></li>
										<li><a href="<%=request.getContextPath()%>/AdminTool">Add Tool</a></li>
										<li><a href="<%=request.getContextPath()%>/AdminToolType">Add Tool Type</a></li>
										<%-- <li><a href="<%=request.getContextPath()%>/">Assign PL</a></li> --%>
										<li><a href="<%=request.getContextPath()%>/AdminSMEforTech">Add Technology SME </a></li>
										<li><a href="<%=request.getContextPath()%>/AdminSMEforDomain">Add Domain SME </a></li>
										<li><a href="<%=request.getContextPath()%>/AdminAssignEmpRole">Assign Employee Role</a></li>
										<li><a href="<%=request.getContextPath()%>/getBuList">WindowPeriod</a></li>
										
										
										<%-- <li><a href="<%=request.getContextPath()%>/">Add SME</a></li> --%>
				                </ul>
			               </li>
					</c:when>
					</c:choose>
					<c:choose>
						<c:when
							test="${userRole.contains('SME')  ||userRole.contains('PRACTICE LEAD') ||userRole.contains('MANAGER')}">
							<li><a href="<%=request.getContextPath()%>/smeTask">My Task</a></li>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${userRole.contains('MANAGER')}">
							<li><a href="<%=request.getContextPath()%>/teamMembers">My Team</a></li>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${userRole.contains( 'EMPLOYEE')}">
							<li><a href="<%=request.getContextPath()%>/viewProficiency">View Proficiency</a></li>
									<!-- windowValue -->
									<c:if test="${windowValue=='1'}">
							<li><a
								href="<%=request.getContextPath()%>/updateProficiency/1">Update Proficiency</a></li>
									</c:if>
						</c:when>
					</c:choose>

					<li class="divider"></li>
					<li><a href="<%=request.getContextPath()%>/logout"> Log Out</a></li>
					<!-- onclick=' return logout()' -->
				</ul></li>

		</ul>
		
	</nav>
	</div>
	<div class="col-lg-7 col-sm-7" style="margin-top: 12px;padding-left: 0px;">
			<div style="font-weight:bold; font-weight: bold;
    font-size: 20px;"><span>Skill & Proficiency Management Tool</span></div>
		</div> 
		</div>
	</header>

	<script>
		function logout() {
			var cookies = document.cookie;

			for ( var i = 0; i < cookies.split(";").length; ++i) {
				var myCookie = cookies[i];
				var pos = myCookie.indexOf("=");
				var name = pos > -1 ? myCookie.substr(0, pos) : myCookie;
				document.cookie = name
						+ "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
			}

			window.location = "/logout";

		}
	</script>