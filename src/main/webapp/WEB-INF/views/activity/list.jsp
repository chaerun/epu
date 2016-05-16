<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<section class="vbox">
	<section class="scrollable padder">
		<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
			<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-home"></i> <spring:message code="home" /></a></li>
			<li class="active"><spring:message code="activity.list" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="activity.list" />
			</h3>
		</div>
		<jsp:include page="/WEB-INF/views/message.jsp" />
		<section class="panel panel-default">
			<header class="panel-heading">
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/activity/precreate"><i class="fa fa-plus-square"></i> <spring:message code="activity.create" /> </a>
			</header>
		</section>
		<section class="panel panel-default">
			<header class="panel-heading">
				<spring:message code="activity.list" />
			</header>
			<div class="panel-body">
				<display:table id="activity" class="table table-striped" name="page" sort="external" partialList="true" size="${page.fullListSize}" pagesize="${page.objectsPerPage}" requestURI="" export="true">
					<display:column titleKey="action">
						<a href="${pageContext.request.contextPath}/activity/account/${activity.code}/" class="btn btn-sm btn-info"><i class="fa fa-edit"></i> <spring:message code="activity.account.list.view" /> </a>
					</display:column>

					<display:column property="code" titleKey="activity.code" sortable="true" />
					<display:column property="name" titleKey="activity.name" sortable="true" />
					<display:column sortProperty="amount" titleKey="activity.amount" sortable="true">
						<fmt:formatNumber value="${activity.amount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
					</display:column>
					<display:column property="source" titleKey="activity.source" sortable="true" />
					<display:column property="type" titleKey="activity.type" sortable="true" />
					<display:column property="year" titleKey="activity.year" sortable="true" />
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
