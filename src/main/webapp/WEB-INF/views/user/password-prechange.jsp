<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<section class="vbox">
	<section class="scrollable padder">
		<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
			<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-home"></i> <spring:message code="home" /></a></li>
			<li class="active"><spring:message code="user.password.change" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="user.password.change" />
			</h3>
		</div>
		<section class="panel panel-default">
			<header class="panel-heading font-bold">
				<spring:message code="user.password.change" />
			</header>
			<div class="panel-body">
				<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/user/password/change" modelAttribute="passwordChangeForm" autocomplete="off">

					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"> <spring:message code="user.username" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${passwordChangeForm.user.username}</p>
						</div>
					</div>

					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="oldPassword">
							<spring:message code="user.password.old" />
						</form:label>
						<div class="col-sm-10">
							<form:password cssClass="form-control" cssErrorClass="form-control parsley-error" path="oldPassword" maxlength="32" />
							<form:errors cssClass="help-block m-b-none" path="oldPassword" />
						</div>
					</div>

					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="newPassword">
							<spring:message code="user.password.new" />
						</form:label>
						<div class="col-sm-10">
							<form:password cssClass="form-control" cssErrorClass="form-control parsley-error" path="newPassword" maxlength="32" />
							<form:errors cssClass="help-block m-b-none" path="newPassword" />
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


					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-2">
							<a href="${pageContext.request.contextPath}/" class="btn btn-default"><spring:message code="cancel" /></a>
							<form:button class="btn btn-primary">
								<spring:message code="send" />
							</form:button>
						</div>
					</div>
				</form:form>
		</section>
	</section>
</section>
<script type="text/javascript">
	$(document).ready(function() {

		$("input[name='user.username']").prop('disabled', true);
	});
</script>
