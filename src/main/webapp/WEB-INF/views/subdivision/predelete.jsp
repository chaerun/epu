<%@page import="java.util.Enumeration"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<section class="vbox">
	<section class="scrollable padder">
		<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
			<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-home"></i> <spring:message code="home" /></a></li>
			<li><a href="${pageContext.request.contextPath}/subdivision"><spring:message code="subdivision.list" /></a></li>
			<li class="active"><spring:message code="subdivision.delete" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="subdivision.delete" />
			</h3>
		</div>
		<section class="panel panel-default">
			<header class="panel-heading font-bold">&nbsp;</header>
			<div class="panel-body">
				<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/subdivision/delete" modelAttribute="subdivision" autocomplete="off">
					<form:hidden path="code" />
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="subdivision.code" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${subdivision.code}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="subdivision.name" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${subdivision.name}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="warning" /></label>
						<div class="col-sm-10">
							<p class="form-control-static font-bold text-danger">
								<spring:message code="confirm.delete" />
							</p>
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-2">
							<a href="${pageContext.request.contextPath}/subdivision" class="btn btn-default"><spring:message code="cancel" /></a>
							<form:button class="btn btn-primary">
								<spring:message code="yes" />
							</form:button>
						</div>
					</div>
				</form:form>
			</div>
		</section>
	</section>
</section>
<script type="text/javascript">
	$(document).ready(function() {

		select_nav("#nav_subdivision_list");
	});
</script>
