<%@page import="com.kemenkes.epu.common.util.Constant"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<section class="vbox">
	<section class="scrollable padder">
		<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
			<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-home"></i> <spring:message code="home" /></a></li>
			<li class="active"><spring:message code="account.list" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="account.list" />
			</h3>
		</div>
		<jsp:include page="/WEB-INF/views/message.jsp" />
		<section class="panel panel-default">
			<header class="panel-heading">
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/account/precreate"><i class="fa fa-plus-square"></i> <spring:message code="account.create" /> </a>
			</header>
		</section>
		<section class="panel panel-default">
			<header class="panel-heading">
				<spring:message code="account.list" />
			</header>
			<div class="panel-body">
				<display:table id="account" class="table table-striped" name="page" sort="external" partialList="true" size="${page.fullListSize}" pagesize="${page.objectsPerPage}" requestURI="" export="true">
					<display:column titleKey="action">
						<a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/account/preedit/${account.accountNumber}/"><i class="fa fa-pencil"></i> <spring:message code="edit" /></a>
						<a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/accountdetail/${account.accountNumber}/"><i class="fa fa-eye"></i> <spring:message code="account.view.detail" /> </a>
					</display:column>
					<display:column property="accountNumber" titleKey="account.accountNumber" sortable="true" />
					<display:column property="name" titleKey="account.name" sortable="true" />
					<display:column property="description" titleKey="account.description" />
					<display:column sortProperty="amount" titleKey="account.amount">
						<fmt:formatNumber value="${account.amount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
					</display:column>
				</display:table>
			</div>
		</section>
	</section>
</section>
<script type="text/javascript">
	$(document).ready(function() {
		select_nav("#nav_account_list");
	});
</script>
