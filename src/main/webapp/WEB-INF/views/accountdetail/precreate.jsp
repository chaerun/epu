<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<section class="vbox">
	<section class="scrollable padder">
		<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
			<li><a href="${pageContext.request.contextPath}"><i class="fa fa-home"></i> <spring:message code="home" /></a></li>
			<li><a href="${pageContext.request.contextPath}/accountdetail/${accountNumber}/"><spring:message code="accountDetail.list" /> </a></li>
			<li class="active"><spring:message code="accountDetail.create" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="accountDetail.create" />
			</h3>
		</div>
		<section class="panel panel-default">
			<header class="panel-heading ">
				<spring:message code="accountDetail.create" />
			</header>
			<div class="panel-body">
				<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/accountdetail/preview/${accountNumber}/" modelAttribute="accountDetail" autocomplete="off">
					<form:hidden path="account.accountNumber" />
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="account" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${accountDetail.account.accountNumber}-${accountDetail.account.name}</p>
							<form:errors cssClass="help-block m-b-none" path="account.accountNumber" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="description">
							<spring:message code="accountDetail.description" />
						</form:label>
						<div class="col-sm-10">
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="description" maxlength="255" />
							<form:errors cssClass="help-block m-b-none" path="description" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="amount">
							<spring:message code="accountDetail.type" />
						</form:label>
						<div class="col-sm-10">
							<div class="radio">
								<label> <spring:message code="debit" /> <form:radiobutton path="type" value="DB" />
								</label>
							</div>
							<div class="radio">
								<label> <spring:message code="credit" /> <form:radiobutton path="type" value="CR" />
								</label>
							</div>
							<form:errors cssClass="help-block m-b-none" path="type" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="amount"><spring:message code="accountDetail.amount" /></form:label>
						<div class="col-sm-10">
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="amount" maxlength="18" />
							<form:errors cssClass="help-block m-b-none" path="amount" />
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="warning" /></label>
						<div class="col-sm-10">
							<p class="form-control-static text-danger"><spring:message code="accountDetail.create.warning" /></p>
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-2">
							<a href="${pageContext.request.contextPath}/accountdetail/${accountNumber}/" class="btn btn-default"><spring:message code="cancel" /></a>
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