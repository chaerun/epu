<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en" class="app">
<head>
<meta charset="utf-8" />
<title><spring:message code="app.name" /></title>
<meta name="description" content="<spring:message code="app.name"/>" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/animate.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/font.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/app.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/js/datepicker/datepicker.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/js/select2/select2.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/js/select2/theme.css"/>" type="text/css" />
<link type="text/css" href="<c:url value="/resources/js/fuelux/fuelux.css"/>" rel="stylesheet">

<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<!--[if lt IE 9]>
    <script src="<c:url value="/resources/js/ie/html5shiv.js"/>" ></script>
    <script src="<c:url value="/resources/js/ie/respond.min.js"/>"></script>
    <script src="<c:url value="/resources/js/ie/excanvas.js"/>"></script>
  <![endif]-->
<sitemesh:write property='head' />
</head>
<body class="">
	<section class="vbox">
		<jsp:include page="_header.jsp"></jsp:include>
		<section>
			<section class="hbox stretch">
				<aside id="nav" class="bg-dark lter aside-md hidden-print hidden-xs">
					<section class="vbox">
						<jsp:include page="_nav.jsp"></jsp:include>
						<footer class="footer lt hidden-xs b-t b-dark">
							<a class="pull-right btn btn-sm btn-dark btn-icon" data-toggle="class:nav-xs" href="#nav"> <i class="fa fa-angle-left text"></i> <i class="fa fa-angle-right text-active"></i>
							</a>
						</footer>
					</section>
				</aside>
				<section id="content">
					<sitemesh:write property='body' />
				</section>
			</section>
		</section>
	</section>
	<!-- Bootstrap -->
	<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
	<!-- App -->
	<script src="<c:url value="/resources/js/app.js"/>"></script>
	<script src="<c:url value="/resources/js/slimscroll/jquery.slimscroll.min.js"/>"></script>
	<script src="<c:url value="/resources/js/app.plugin.js"/>"></script>
	<script src="<c:url value="/resources/js/parsley/parsley.min.js"/>"></script>
	<script src="<c:url value="/resources/js/parsley/parsley.extend.js"/>"></script>
	<script src="<c:url value="/resources/js/datepicker/bootstrap-datepicker.js"/>"></script>
	<script src="<c:url value="/resources/js/select2/select2.min.js"/>"></script>
	<script type="text/javascript">
		function select_nav(nav_id) {
			var nav = $(nav_id);
			nav.addClass("active");
			nav.parent().addClass("active");
			nav.parent().parent().parent().addClass("active");
		}

		$(document).ready(function() {
			$("span > input[type='radio']").each(function(index) {
				$(this).parent().addClass("radio");
			});
		});
	</script>
</body>
</html>