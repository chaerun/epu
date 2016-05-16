<%@page import="com.kemenkes.epu.entity.ActivityType"%>
<%@page import="java.util.Enumeration"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<section class="vbox">
	<section class="scrollable padder">
		<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
			<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-home"></i> <spring:message code="home" /></a></li>
			<li><a href="${pageContext.request.contextPath}/activity"><spring:message code="activity.list" /></a></li>
			<li><a href="${pageContext.request.contextPath}/activity/account/${form.activity.code}/"><spring:message code="activity.account.list" /></a></li>
			<li class="active"><spring:message code="account.add" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="account.add" />
			</h3>
		</div>
		<section class="panel panel-default">
			<header class="panel-heading font-bold">
				<spring:message code="account.add" />
			</header>
			<div class="panel-body">
				<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/activity/account/preview" modelAttribute="form" autocomplete="off">
					<form:hidden path="activity.code" />
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="activity.code" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${form.activity.code}</p>
							<form:errors cssClass="help-block m-b-none" path="activity.code" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"> <spring:message code="activity.name" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${form.activity.name}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"> <spring:message code="activity.year" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${form.activity.year}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"> <spring:message code="activity.amount" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<fmt:formatNumber value="${form.activity.amount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="activity.source" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${form.activity.source.description}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="activity.type" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${form.activity.type.description}</p>
						</div>
					</div>
					<c:if test="${not empty form.activity.subdivision} ">
						<div class="form-group">
							<label class="col-sm-2 control-label font-bold"><spring:message code="subdivision" /></label>
							<div class="col-sm-10">
								<p class="form-control-static">${form.activity.subdivision.code}&nbsp;-&nbsp;${form.activity.subdivision.name}</p>
							</div>
						</div>
					</c:if>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="account.accountNumber">
							<spring:message code="account" />
						</form:label>
						<div class="col-sm-10">
							<form:select path="account.accountNumber" style="width: 100%;">
								<form:option value="" />
								<c:forEach var="account" items="${accounts}">
									<form:option value="${account.accountNumber}">${account.accountNumber} - ${account.name}  </form:option>
								</c:forEach>
							</form:select>
							<form:errors cssClass="help-block m-b-none" path="account.accountNumber" />
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="warning" /></label>
						<div class="col-sm-10">
							<p class="form-control-static text-danger">
								<spring:message code="account.add.warning" />
							</p>
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-2">
							<a href="${pageContext.request.contextPath}/activity/account/${form.activity.code}/" class="btn btn-default"><spring:message code="cancel" /></a>
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
		select_nav("#nav_activity_list");

		$("select[name='account.accountNumber']").select2();
	});
</script>
