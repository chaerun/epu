<%@page import="java.util.Enumeration"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<section class="vbox">
	<section class="scrollable padder">
		<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
			<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-home"></i> <spring:message code="home" /></a></li>
			<li><a href="${pageContext.request.contextPath}/subdivision"><spring:message code="subdivision.list" /></a></li>
			<li class="active"><spring:message code="subdivision.edit" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="subdivision.edit" />
			</h3>
		</div>
		<section class="panel panel-default">
			<header class="panel-heading font-bold">&nbsp;</header>
			<div class="panel-body">
				<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/subdivision/edit" modelAttribute="subdivision" autocomplete="off">
					<form:hidden path="code" />
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="subdivision.code" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${subdivision.code}</p>
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="name">
							<spring:message code="subdivision.name" />
						</form:label>
						<div class="col-sm-10">
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="name" maxlength="100" />
							<form:errors cssClass="help-block m-b-none" path="name" />
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-2">
							<a href="${pageContext.request.contextPath}/subdivision" class="btn btn-default"><spring:message code="cancel" /></a>
							<form:button class="btn btn-primary">
								<spring:message code="send" />
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
