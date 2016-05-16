<%@page import="java.util.Enumeration"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<section class="vbox">
	<section class="scrollable padder">
		<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
			<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-home"></i> <spring:message code="home" /></a></li>
			<li><a href="${pageContext.request.contextPath}/user"><spring:message code="user.list" /></a></li>
			<li class="active"><spring:message code="user.delete" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="user.delete" />
			</h3>
		</div>
		<section class="panel panel-default">
			<header class="panel-heading font-bold">&nbsp;</header>
			<div class="panel-body">
				<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/user/delete" modelAttribute="user" autocomplete="off">
					<form:hidden path="username" />
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="user.username" /> </label>
						<div class="col-sm-10">
							<p class="form-control-static">${user.username}</p>
						</div>
					</div>
					<form:hidden path="subdivision.code" />
					<form:hidden path="role" />
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="subdivision" /> </label>
						<div class="col-sm-10">
							<p class="form-control-static">${user.subdivision.code}-${user.subdivision.name}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="user.role" /> </label>
						<div class="col-sm-10">
							<p class="form-control-static">${user.role.description}</p>
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-2">
							<a href="${pageContext.request.contextPath}/user" class="btn btn-default"><spring:message code="cancel" /></a>
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
		select_nav("#nav_user_list");
	});
</script>
