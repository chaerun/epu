<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/js/select2/select2.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/js/select2/theme.css"/>" type="text/css" />
</head>
<body>
	<section class="vbox">
		<section class="scrollable padder">
			<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
				<li><a href="<c:url value="/"/>"><i class="fa fa-home"></i> <spring:message code="home" /></a></li>
				<li class="active"><spring:message code="config" /></li>
			</ul>
			<div class="m-b-md">
				<h3 class="m-b-none">
					<spring:message code="config" />
				</h3>
			</div>
			<jsp:include page="/WEB-INF/views/message.jsp" />
			<section class="panel panel-default">
				<header class="panel-heading font-bold">&nbsp;</header>
				<div class="panel-body">
					<form class="form-horizontal" action="<c:url value="/config/update/"/>" method="post" data-validate="parsley" autocomplete="off">
						<div class="form-group">
							<label class="col-sm-2 control-label"><spring:message code="year.plan" /></label>
							<div class="col-sm-10">
								<select id="select2-option" style="width: 50%;" name="year">
									<option value="">&nbsp;</option>
									<c:forEach var="i" begin="2014" step="1" end="2019">
										<option value="${i}">${i}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<!-- 
						<div class="form-group">
							<label class="col-sm-2 control-label"><spring:message code="pejabat.pembuat.komitmen" /></label>
							<div class="col-sm-10">
								<input class="form-control" value="${pejabat} " name="pejabat" type="text" maxlength="100" data-required="true" placeholder="<spring:message code="pejabat.pembuat.komitmen" />" />
							</div>
						</div>
						<div class="line line-dashed line-lg pull-in"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label"><spring:message code="bendahara" /></label>
							<div class="col-sm-10">
								<input class="form-control" value="${bendahara} " name="bendahara" maxlength="100" type="text" data-required="true" placeholder="<spring:message code="bendahara" />" />
							</div>
						</div>
						 -->
						<div class="line line-dashed line-lg pull-in"></div>
						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-2">
								<a href="<c:url value="/subdivision/"/>" class="btn btn-default"><spring:message code="cancel" /></a>
								<button class="btn btn-primary" type="submit">
									<spring:message code="submit" />
								</button>
							</div>
						</div>
					</form>
				</div>
			</section>
		</section>
	</section>
</body>
<script src="<c:url value="/resources/js/select2/select2.min.js"/>"></script>
<script type="text/javascript">
	$(document).ready(function() {

		var nav = $("#nav_config");
		nav.addClass("active");
		nav.parent().addClass("active");
		nav.parent().parent().parent().addClass("active");
		$("#select2-option").select2("val", "${year}");
	});
</script>
</html>