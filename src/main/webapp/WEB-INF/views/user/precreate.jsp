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
			<li class="active"><spring:message code="user.create" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="user.create" />
			</h3>
		</div>
		<section class="panel panel-default">
			<header class="panel-heading font-bold">&nbsp;</header>
			<div class="panel-body">
				<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/user/create" modelAttribute="userForm" autocomplete="off">
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="user.username">
							<spring:message code="user.username" />
						</form:label>
						<div class="col-sm-10">
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="user.username" maxlength="50" />
							<form:errors cssClass="help-block m-b-none" path="user.username" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="user.password">
							<spring:message code="user.password" />
						</form:label>
						<div class="col-sm-10">
							<form:password cssClass="form-control" cssErrorClass="form-control parsley-error" path="user.password" maxlength="32" />
							<form:errors cssClass="help-block m-b-none" path="user.password" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="confirmPassword">
							<spring:message code="user.password.confirm" />
						</form:label>
						<div class="col-sm-10">
							<form:password cssClass="form-control" cssErrorClass="form-control parsley-error" path="confirmPassword" maxlength="32" />
							<form:errors cssClass="help-block m-b-none" path="confirmPassword" />
						</div>
					</div>
					<sec:authorize access="hasRole('ROLE_SA')">
						<div class="form-group">
							<form:label cssClass="col-sm-2 control-label" path="user.subdivision.code">
								<spring:message code="subdivision" />
							</form:label>
							<div class="col-sm-10">
								<form:select path="user.subdivision.code" style="width: 50%;" >
									<form:option value="" />
									<form:options items="${subdivisions}" itemLabel="name" itemValue="code" />
								</form:select>
								<form:errors cssClass="help-block m-b-none" path="user.subdivision.code" />
							</div>
						</div>
						
						<div class="form-group">
							<form:label cssClass="col-sm-2 control-label" path="user.role">
								<spring:message code="user.role" />
							</form:label>
							<div class="col-sm-10">
								<form:radiobuttons path="user.role" items="${roles}" itemLabel="description" itemValue="id" />
								<form:errors cssClass="help-block m-b-none" path="user.role" />
							</div>
						</div>
					</sec:authorize>
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<form:hidden path="user.subdivision.code" />
						<form:hidden path="user.role" />
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
					</sec:authorize>
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
		$("select[name='user.subdivision.code']").select2();
		
	});
</script>
