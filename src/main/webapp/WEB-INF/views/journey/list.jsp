<%@page import="com.kemenkes.epu.common.util.Constant"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<section class="vbox">
	<section class="scrollable padder">
		<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
			<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-home"></i> <spring:message code="home" /></a></li>
			<li><a href="${pageContext.request.contextPath}/journey/activity"><spring:message code="activity.list" />&nbsp;<spring:message code="year" />&nbsp;${activity.year}</a></li>
			<li class="active"><spring:message code="journey.list" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="journey.list" />
			</h3>
		</div>
		<jsp:include page="/WEB-INF/views/message.jsp" />
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
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="absorption" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<fmt:formatNumber value="${view.usedAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="remnant.amount" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<fmt:formatNumber value="${view.balance}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
							</p>
						</div>
					</div>
				</form>
			</div>
		</section>
		<section class="panel panel-default">
			<header class="panel-heading">
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/journey/precreate/type/${activity.code}/"><i class="fa fa-plus-square"></i> <spring:message code="journey.create" /> </a>
			</header>
		</section>
		<section class="panel panel-default">
			<header class="panel-heading font-bold">
				<spring:message code="journey.list" />
			</header>
			<div class="panel-body">
				<display:table id="journey" class="table table-striped" name="page" sort="external" partialList="true" size="${page.fullListSize}" pagesize="${page.objectsPerPage}" requestURI="" export="true">
					<display:column titleKey="action">
						<a href="${pageContext.request.contextPath}/journey/detail/${journey.id}/" class="btn btn-sm btn-success"><i class="fa fa-edit"></i> <spring:message code="view.detail" /></a>
					</display:column>
					<display:column property="name" titleKey="journey.name" sortable="true" />
					<display:column titleKey="journey.startDate">
						<fmt:formatDate value="${journey.startDate}" pattern="<%= Constant.DATE_FORMAT %>" /> s/d <fmt:formatDate value="${journey.endDate}" pattern="<%= Constant.DATE_FORMAT %>" />
					</display:column>
					<display:column property="location" titleKey="journey.location" sortable="true" />
					<display:column property="type.description" titleKey="journey.type" />
					<display:column property="status.description" titleKey="journey.status" />
				</display:table>
			</div>
		</section>
	</section>
</section>
<script type="text/javascript">
	$(document).ready(function() {
		select_nav("#nav_journey_list");
	});
</script>
