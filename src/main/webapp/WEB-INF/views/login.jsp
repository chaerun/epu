<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en" class="bg-dark">
<head>
<meta charset="utf-8" />
<title><spring:message code="app.name"/></title>
<meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/animate.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/font.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/app.css"/>" type="text/css" />
<!--[if lt IE 9]>
    <script src="<c:url value="/resources/js/ie/html5shiv.js"/>" ></script>
    <script src="<c:url value="/resources/js/ie/respond.min.js"/>"></script>
    <script src="<c:url value="/resources/js/ie/excanvas.js"/>"></script>
  <![endif]-->
</head>
<section id="content" class="m-t-lg wrapper-md animated fadeInUp">
	<div class="container aside-xxl">
		<a class="navbar-brand block" href="<c:url value="/"/>"><spring:message code="app.name" /></a>
		<section class="panel panel-default bg-white m-t-lg">
			<header class="panel-heading text-center">
				<strong>&nbsp;</strong>
			</header>
			<form name='f' action="<c:url value="/j_spring_security_check"/>" class="panel-body wrapper-lg" method="post">
				<c:if test="${not empty param.error}">
					<div class="alert alert-danger">
						<spring:message code="login.failed" />
					</div>
				</c:if>
				<div class="form-group">
					<label class="control-label"> <spring:message code="user.username" /></label> <input type="text" name="j_username" placeholder="<spring:message code="user.username" />" class="form-control input-lg">
				</div>
				<div class="form-group">
					<label class="control-label"> <spring:message code="user.password" /></label> <input type="password" name="j_password" id="inputPassword" placeholder="<spring:message code="user.password" />" class="form-control input-lg">
				</div>
				<button type="submit" class="btn btn-primary">
					<spring:message code="login" />
				</button>
				<div class="line line-dashed"></div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
		</section>
	</div>
</section>
<!-- footer -->
<footer id="footer">
	<div class="text-center padder">
		<p>
			<small><spring:message code="kemenkes" /><br>&copy; 2014 </small>
		</p>
	</div>
</footer>

</body>
</html>