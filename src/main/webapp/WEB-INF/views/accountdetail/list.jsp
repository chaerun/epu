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
			<li class="active"><spring:message code="accountDetail.list" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none"> <spring:message code="accountDetail.list" /> = ${accountNumber}</h3>
		</div>
		<jsp:include page="/WEB-INF/views/message.jsp" />
		<section class="panel panel-default">
			<header class="panel-heading">
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/accountdetail/precreate/${accountNumber}/"><i class="fa fa-plus-square"></i> <spring:message code="accountDetail.create"/></a>
			</header>
		</section>
		<section class="panel panel-default">
			<header class="panel-heading">
				<spring:message code="date.filter"/>
			</header>
			<div class="panel-body">
				<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/accountdetail/${accountNumber}/" modelAttribute="dateFilter" autocomplete="off">
					<div class="form-group">
						<input type="hidden" name="pagesize" value="<%=Integer.MAX_VALUE%>" />
						<div class="form-group">
							<label class="col-lg-2 control-label"><spring:message code="date.from"/></label>
							<div class="col-sm-10">
								<form:input cssClass="input-sm input-s datepicker-input form-control" cssErrorClass="input-sm input-s datepicker-input form-control parsley-error" path="fromDate" maxlength="10" data-date-format="<%=Constant.DATE_PICKER_FORMAT%>" />
								<form:errors cssClass="help-block m-b-none" path="fromDate" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 control-label"><spring:message code="date.to"/></label>
							<div class="col-sm-10">
								<form:input cssClass="input-sm input-s datepicker-input form-control" cssErrorClass="input-sm input-s datepicker-input form-control parsley-error" path="toDate" maxlength="10" data-date-format="<%=Constant.DATE_PICKER_FORMAT%>" />
								<form:errors cssClass="help-block m-b-none" path="toDate" />
							</div>
						</div>
						<div class="line line-dashed line-lg pull-in"></div>
						<div class="col-sm-4 col-sm-offset-2">
							<form:button class="btn btn-primary">
								<spring:message code="filter" />
							</form:button>
						</div>
					</div>
				</form:form>
			</div>
		</section>
		<section class="panel panel-default">
			<header class="panel-heading">
				<spring:message code="accountDetail.list"/>
			</header>
			<div class="panel-body">
				<display:table id="accountdetail" class="table table-striped" name="page" sort="external" partialList="true" size="${page.fullListSize}" pagesize="${page.objectsPerPage}" requestURI="" export="true">
					<display:column property="createdDate" titleKey="createdDate" sortable="true" format="{0,date,MM/dd/yyyy HH:mm:ss}" />
					<display:column property="description" titleKey="accountDetail.description" />
					<display:column titleKey="debit" class="text-right" headerClass="text-right">
						<c:if test="${accountdetail.type eq 'DB'}">
							<fmt:formatNumber value="${accountdetail.amount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
						</c:if>
						<c:if test="${accountdetail.type eq 'CR'}">
						-
					</c:if>
					</display:column>
					<display:column titleKey="credit" class="text-right" headerClass="text-right">
						<c:if test="${accountdetail.type eq 'CR'}">
							<fmt:formatNumber value="${accountdetail.amount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
						</c:if>
						<c:if test="${accountdetail.type eq 'DB'}">
						-
					</c:if>
					</display:column>
					<display:column sortProperty="account.accountNumber" titleKey="account" sortable="true">
						${accountdetail.account.accountNumber} - ${accountdetail.account.name}   
					</display:column>
					<display:column property="createdBy" titleKey="createdBy" sortable="true" />
					<display:footer>
						<tr>
							<td colspan="2" class="font-bold"><spring:message code="total" /></td>
							<td class="text-right"><fmt:formatNumber value="${debit}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" /></td>
							<td class="text-right"><fmt:formatNumber value="${credit}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" /></td>
							<td></td>
						</tr>
					</display:footer>
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