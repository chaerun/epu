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
			<li class="active"><spring:message code="stock.list" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="stock.list" />
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
				<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/stock/preview" modelAttribute="stock" autocomplete="off">
					<form:hidden path="activity.code" />
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="account.accountNumber">
							<spring:message code="account.accountNumber" />
						</form:label>
						<div class="col-sm-10">
							<form:select path="account.accountNumber" style="width: 70%;">
								<form:option value="" />
								<c:forEach var="a" items="${activity.accounts}">
									<form:option value="${a.accountNumber }">${a.accountNumber } - ${a.name }</form:option>
								</c:forEach>
							</form:select>
							<form:errors cssClass="help-block m-b-none" path="account.accountNumber" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="amount">
							<spring:message code="stock.amount" />
						</form:label>
						<div class="col-sm-6">
							<c:set var="text_stock_amount">
								<spring:message code="stock.amount" />
							</c:set>
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="amount" maxlength="18" placeholder="${text_stock_amount }" />
							<form:errors cssClass="help-block m-b-none" path="amount" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="description">
							<spring:message code="stock.description" />
						</form:label>
						<div class="col-sm-6">
							<c:set var="text_stock_description">
								<spring:message code="stock.description" />
							</c:set>
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="description" maxlength="18" placeholder="${text_stock_description }" />
							<form:errors cssClass="help-block m-b-none" path="description" />
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
							<a href="${pageContext.request.contextPath}/stock/activity" class="btn btn-default"><spring:message code="back" /></a>
							<form:button class="btn btn-primary">
								<spring:message code="send" />
							</form:button>
						</div>
					</div>
				</form:form>
			</div>
		</section>
		<section class="panel panel-default">
			<header class="panel-heading font-bold">
				<spring:message code="stock.list" />
			</header>
			<div class="panel-body">
				<c:set var="total" value="0" />
				<display:table id="stock" class="table table-striped" name="page" sort="external" partialList="true" size="${page.fullListSize}" pagesize="${page.objectsPerPage}" requestURI="" export="true">
					<display:column property="createdDate" titleKey="createdDate" sortable="true" format="{0,date,MM/dd/yyyy HH:mm:ss}" />
					<display:column property="description" titleKey="stock.description" />
					<display:column titleKey="stock.amount" class="text-right" headerClass="text-right">
						<fmt:formatNumber value="${stock.amount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
						<c:set var="total" value="${stock.amount + total}" />
					</display:column>
					<display:column titleKey="account">
						 ${stock.account.accountNumber} - ${stock.account.name }
					</display:column>
					<display:column property="createdBy" titleKey="createdby" sortable="true" />
					<display:footer>
						<tr>
							<td colspan="2" class="font-bold"><spring:message code="total" /></td>
							<td class="text-right"><fmt:formatNumber value="${total}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" /></td>
							<td colspan="2"></td>
						</tr>
					</display:footer>
				</display:table>
			</div>
		</section>
	</section>
</section>
<script type="text/javascript">
	$(document).ready(function() {
		select_nav("#nav_stock_list");

		$("select[name='account.accountNumber']").select2();
	});
</script>
