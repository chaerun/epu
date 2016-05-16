<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<section class="vbox">
	<section class="scrollable padder">
		<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
			<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-home"></i> <spring:message code="home" /></a></li>
			<li><a href="${pageContext.request.contextPath}/activity"><spring:message code="activity.list" /></a></li>
			<li class="active"><spring:message code="activity.account.list" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="activity.account.list" />
			</h3>
		</div>
		<jsp:include page="/WEB-INF/views/message.jsp" />
		<section class="panel panel-default">
			<header class="panel-heading">
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/activity/account/preadd/${activity.code}/"><i class="fa fa-plus-square"></i> <spring:message code="account.add" /> </a>
			</header>
		</section>
		<section class="panel panel-default">
			<header class="panel-heading font-bold">
				<spring:message code="activity" />
			</header>
			<div class="panel-body">
				<form class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="activity.code" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${activity.code}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"> <spring:message code="activity.name" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${activity.name}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"> <spring:message code="activity.year" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${activity.year}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"> <spring:message code="activity.amount" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<fmt:formatNumber value="${activity.amount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="activity.source" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${activity.source.description}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="activity.type" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${activity.type.description}</p>
						</div>
					</div>
					<c:if test="${not empty activity.subdivision} ">
						<div class="form-group">
							<label class="col-sm-2 control-label font-bold"><spring:message code="subdivision" /></label>
							<div class="col-sm-10">
								<p class="form-control-static">${activity.subdivision.code}&nbsp;-&nbsp;${activity.subdivision.name}</p>
							</div>
						</div>
					</c:if>
				</form>
			</div>
		</section>
		<section class="panel panel-default">
			<header class="panel-heading font-bold">
				<spring:message code="account.list" />
			</header>
			<div class="panel-body">
				<display:table id="account" class="table table-striped" name="${activity.accounts}">
					<display:column property="accountNumber" titleKey="account.accountNumber" />
					<display:column property="name" titleKey="account.name" />
				</display:table>
			</div>
		</section>
	</section>
</section>
<script type="text/javascript">
	$(document).ready(function() {
		select_nav("#nav_activity_list");

	});
</script>
