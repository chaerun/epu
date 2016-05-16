<%@page import="java.util.Enumeration"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<section class="vbox">
	<section class="scrollable padder">
		<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
			<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-home"></i> <spring:message code="home" /></a></li>
			<li><a href="${pageContext.request.contextPath}/official"><spring:message code="official.list" /></a></li>
			<li class="active"><spring:message code="official.delete" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="official.delete" />
			</h3>
		</div>
		<section class="panel panel-default">
			<header class="panel-heading font-bold">&nbsp;</header>
			<div class="panel-body">
				<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/official/delete" modelAttribute="official" autocomplete="off">
					<form:hidden path="year" />
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label font-bold" path="official.year">
							<spring:message code="official.year" />
						</form:label>
						<div class="col-sm-10">
							<p class="form-control-static">${official.official.year}</p>
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label font-bold" path="official.code">
							<spring:message code="official.official" />
						</form:label>
						<div class="col-sm-10">
							<p class="form-control-static">${official.official.name}</p>
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label font-bold" path="treasurer.code">
							<spring:message code="official.treasurer" />
						</form:label>
						<div class="col-sm-10">
							<p class="form-control-static">${official.treasurer.name}</p>
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
							<a href="${pageContext.request.contextPath}/official" class="btn btn-default"><spring:message code="cancel" /></a>
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

		select_nav("#nav_official_list");
	});
</script>
