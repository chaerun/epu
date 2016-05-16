<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<section class="vbox">
	<section class="scrollable padder">
		<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
			<li><a href="<c:url value="/"/>"><i class="fa fa-home"></i> <spring:message code="home" /></a></li>
			<li><a href="${pageContext.request.contextPath}/balance"><spring:message code="balance.list" /></a></li>
			<li class="active"><spring:message code="balance.create" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="balance.create" />
			</h3>
		</div>
		<section class="panel panel-default">
			<header class="panel-heading ">
				<spring:message code="balance.create" />
			</header>
			<div class="panel-body">
				<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/balance/create" modelAttribute="balance" autocomplete="off">
					<form:hidden path="description" />
					<form:hidden path="type" />
					<form:hidden path="amount" />
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="balance.description" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${balance.description}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="balance.type" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<c:if test="${balance.type eq 'DB'}">
									<spring:message code="debit" />
								</c:if>
								<c:if test="${balance.type eq 'CR'}">
									<spring:message code="credit" />
								</c:if>
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="balance.amount" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<fmt:formatNumber value="${balance.amount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
							</p>
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="warning" /></label>
						<div class="col-sm-10">
							<p class="form-control-static text-danger">
								<spring:message code="balance.create.warning" />
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
		select_nav("#nav_balance_list");
	});
</script>