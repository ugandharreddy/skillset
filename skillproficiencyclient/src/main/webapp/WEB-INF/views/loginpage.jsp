
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@ taglib uri="http://www.springframework.org/security/tags"
    prefix="sec"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>	
	<%@page import="org.apache.catalina.Session" %>
	 <%@page language="java" session="true" %>
	 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE = edge">
<meta name="viewport" content="width = device-width,  initial-scale = 1">

<title>PMT - Proficiency Management Tool</title>

<!-- Bootstrap -->

<link
	href="${pageContext.servletContext.contextPath}/resources/css/mystyle.css"
	rel="stylesheet">
<link
	href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.servletContext.contextPath}/resources/css/font-awesome.min.css"
	rel="stylesheet">
</head>
<body class="bdy_img">
	<div class="col-lg-12 gab_small">
		<img
			src="${pageContext.servletContext.contextPath}/resources/images/attra_logo_transparent.png"
			class="img-responsive center-block">
	</div>
	<div class="col-md-4  col-md-offset-4 next gab_more">
<div style="color:red; "class="panel-title text-center">${msg}</div>

		<div class="login-panel panel panel1  panel-default">
			<div class="panel-heading panel1">
				<h3 class="panel-title text-center">
					<b class="attra_color">Skill & Proficiency Management Tool</b>
				</h3>
			</div>
			<div class="panel-body">
				<form:form action="${pageContext.request.contextPath}/login"
					commandName="loginUser">
					
						<div class="form-group">

							<form:input class="form-control panel1" placeholder="EmailId"
								path="username" maxlength="50" type="email" autofocus=""></form:input>
						</div>
						<div class="form-group">

							<form:input class="form-control panel1" placeholder="........."
								path="password" maxlength="50" type="password" value=""/>
						</div>
						<button class="btn btn-lg btn-danger btn-block"
							data-loading-text="Loading..." type="submit">Login</button>
					
				</form:form>
			</div>
		</div>
	</div>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<%--  	   <script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script> 
 --%>
	<%-- <script src="<c:url value="/resources/js/bootstrap.min.js" />" type="text/javascript"></script> --%>
	<!-- <script type="text/javascript" src="resources/js/bootstrap.min.js"> -->
		
<!-- 	</script> -->
	<script type='text/javascript'>
		window.history.forward();
		function noBack() {
			window.history.forward();
		}
		window.onload = 'noBack()';
		window.onpageshow = function(evt) {
			if (evt.persisted)
				noBack()
		}
		window.onunload = function() {
			void (0)
		}
	</script>

</body>
</html>