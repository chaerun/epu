<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<section class="vbox">
	<section class="scrollable padder">
		<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
			<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-home"></i> <spring:message code="home" /></a></li>
			<li><a href="${pageContext.request.contextPath}/stock/activity"><spring:message code="activity.list" />&nbsp;<spring:message code="year" />&nbsp;${activity.year}</a></li>
			<li><a href="${pageContext.request.contextPath}/stock/activity/${activity.code }/"><spring:message code="stock.list" /></a></li>
			<li class="active"><spring:message code="stock.create" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="stock.create" />
			</h3>
		</div>
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
							<form:errors cssClass="help-block m-b-none" path="activity.code" />
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
				</form>
			</div>
		</section>
		<section class="panel panel-default">
			<header class="panel-heading font-bold">
				<spring:message code="stock.create" />
			</header>
			<div class="panel-body">
				<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/stock/create" modelAttribute="stock" autocomplete="off">
					<form:hidden path="activity.code" />
					<form:hidden path="account.accountNumber" />
					<form:hidden path="amount" />
					<form:hidden path="description" />
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="account" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${accountBalance.accountNumber}&nbsp;-&nbsp;${accountBalance.name}&nbsp;-&nbsp;<fmt:formatNumber value="${accountBalance.amount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="stock.amount" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<fmt:formatNumber value="${stock.amount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="stock.description" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${stock.description}</p>
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="warning" /></label>
						<div class="col-sm-10">
							<p class="form-control-static text-danger">
								<spring:message code="stock.create.warning" />
							</p>
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-2">
							<form:button class="btn btn-default" name="cancel" value="true">
								<spring:message code="cancel" />
							</form:button>
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
		select_nav("#nav_stock_list");
	});
</script>
