<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<section class="vbox">
	<section class="scrollable padder">
		<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
			<li><a href="${pageContext.request.contextPath}"><i class="fa fa-home"></i> <spring:message code="home" /></a></li>
			<li><a href="${pageContext.request.contextPath}/akun"><spring:message code="account.list" /></a></li>
			<li class="active"><spring:message code="account.edit" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="account.edit" />
			</h3>
		</div>
		<section class="panel panel-default">
			<header class="panel-heading ">
				<spring:message code="account.edit" />
			</header>
			<div class="panel-body">
				<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/account/preview/edit" modelAttribute="account" autocomplete="off">
					<form:hidden path="accountNumber" />
					<form:hidden path="createdBy" />
					<form:hidden path="createdDate" />
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="account.accountNumber" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${account.accountNumber}</p>
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="name">
							<spring:message code="account.name" />
						</form:label>
						<div class="col-sm-10">
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="name" maxlength="100" />
							<form:errors cssClass="help-block m-b-none" path="name" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="description">
							<spring:message code="account.description" />
						</form:label>
						<div class="col-sm-10">
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="description" maxlength="255" />
							<form:errors cssClass="help-block m-b-none" path="description" />
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-2">
							<a href="${pageContext.request.contextPath}/account" class="btn btn-default"><spring:message code="cancel" /></a>
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
		select_nav("#nav_account_list");
	});
</script>