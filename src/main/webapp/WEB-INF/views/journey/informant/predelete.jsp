<%@page import="com.kemenkes.epu.common.util.Constant"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<section class="vbox">
	<section class="scrollable padder">
		<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
			<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-home"></i> <spring:message code="home" /></a></li>
			<li><a href="${pageContext.request.contextPath}/journey/activity"><spring:message code="activity.list" />&nbsp;<spring:message code="year" />&nbsp;${activity.year}</a></li>
			<li><a href="${pageContext.request.contextPath}/journey/activity/${activity.code}/"><spring:message code="journey.list" /></a></li>
			<li><a href="${pageContext.request.contextPath}/journey/detail/${journey.id}/"><spring:message code="journey.detail" /></a></li>
			<li><a href="${pageContext.request.contextPath}/informant/precreate/${journey.id}/"><spring:message code="informant.journey.add" /></a></li>
			<li class="active"><spring:message code="informant.journey.delete" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="informant.journey.delete" />
			</h3>
		</div>
		<jsp:include page="/WEB-INF/views/message.jsp" />
		<section class="panel panel-default">
			<header class="panel-heading font-bold">
				<spring:message code="journey" />
			</header>
			<div class="panel-body">
				<form class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="journey.name" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${journey.name}</p>
							<form:errors cssClass="help-block m-b-none" path="activity.code" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"> <spring:message code="journey.location" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${journey.location}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"> <spring:message code="journey.type" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${journey.type.description}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="journey.startDate" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<fmt:formatDate value="${journey.startDate}" pattern="<%= Constant.DATE_FORMAT %>" />
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="journey.endDate" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<fmt:formatDate value="${journey.endDate}" pattern="<%= Constant.DATE_FORMAT %>" />
							</p>
						</div>
					</div>
				</form>
			</div>
		</section>
		<section class="panel panel-default">
			<header class="panel-heading ">
				<spring:message code="informant" />
			</header>
			<div class="panel-body">
				<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/informant/delete" modelAttribute="informant" autocomplete="off">
					<form:hidden path="id" />
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="journey.account.informant" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${journey.informantAccount.accountNumber}-${journey.informantAccount.name}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="journey.account.informant.transport" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${journey.informantTransportAccount.accountNumber}&nbsp;-&nbsp;${journey.informantTransportAccount.name}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="informant.journey.nip" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${informant.nip}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="informant.journey.name" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${informant.name}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="informant.journey.position" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${informant.position}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="informant.journey.grade" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${informant.grade}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="informant.journey.startDate" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<fmt:formatDate value="${informant.startDate}" pattern="<%= Constant.DATE_FORMAT %>" />
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="informant.journey.endDate" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<fmt:formatDate value="${informant.endDate}" pattern="<%= Constant.DATE_FORMAT %>" />
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="informant.journey.amount" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<fmt:formatNumber value="${informant.amount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
								x ${informant.days} (
								<spring:message code="day" />
								) x ${informant.hours} (
								<spring:message code="hour" />
								) =
								<fmt:formatNumber value="${informant.amount * informant.days * informant.hours}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
							</p>
						</div>
					</div>
					<c:if test="${journey.type eq 'FULLDAY' or journey.type eq 'JOURNEY_IN'}">
						<div class="form-group">
							<label class="col-sm-2 control-label font-bold"><spring:message code="informant.journey.transportAmount" /></label>
							<div class="col-sm-10">
								<p class="form-control-static">
									<fmt:formatNumber value="${informant.transportAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
								</p>
							</div>
						</div>
					</c:if>
					<c:if test="${journey.type eq 'FULLBOARD' or journey.type eq 'JOURNEY_OUT'}">
						<div class="line line-dashed line-lg pull-in"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label font-bold"><spring:message code="informant.journey.fromLocation" /></label>
							<div class="col-sm-10">
								<p class="form-control-static">${informant.fromLocation}</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label font-bold"> <spring:message code="informant.journey.planeAmount" /></label>
							<div class="col-sm-10">
								<p class="form-control-static">
									<fmt:formatNumber value="${informant.planeGoAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
									(
									<spring:message code="journey.go" />
									) +
									<fmt:formatNumber value="${informant.planeGoTax}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
									(
									<spring:message code="journey.tax" />
									) =
									<fmt:formatNumber value="${informant.planeGoAmount + informant.planeGoTax}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
								</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label font-bold"></label>
							<div class="col-sm-10">
								<p class="form-control-static">
									<fmt:formatNumber value="${informant.planeBackAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
									(
									<spring:message code="journey.back" />
									) +
									<fmt:formatNumber value="${informant.planeBackTax}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
									(
									<spring:message code="journey.tax" />
									) =
									<fmt:formatNumber value="${informant.planeBackAmount + informant.planeBackTax}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
								</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label font-bold"> <spring:message code="informant.journey.taxiAmount" /></label>
							<div class="col-sm-10">
								<p class="form-control-static">
									<fmt:formatNumber value="${informant.taxiGoAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
									(
									<spring:message code="journey.go" />
									) +
									<fmt:formatNumber value="${informant.taxiBackAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
									(
									<spring:message code="journey.back" />
									) =
									<fmt:formatNumber value="${informant.taxiGoAmount + informant.taxiBackAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
								</p>
							</div>
						</div>
					</c:if>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"> <spring:message code="total" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<fmt:formatNumber value="${informant.totalAmount }" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"> <spring:message code="total.received.amount" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<spring:message code="total" />
								&nbsp;-&nbsp;${informant.ppn}&nbsp;
								<spring:message code="symbol.percent" />
								=
								<fmt:formatNumber value="${informant.receivedAmount }" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="warning" /></label>
						<div class="col-sm-10">
							<p class="form-control-static font-bold text-danger">
								<spring:message code="confirm.delete" />
							</p>
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-2">
							<a href="${pageContext.request.contextPath}/informant/precreate/${journey.id}/" class="btn btn-default"><spring:message code="cancel" /></a>
							<form:button class="btn btn-primary">
								<spring:message code="send" />
							</form:button>
						</div>
					</div>
				</</form:form>
			</div>
		</section>
	</section>
</section>
<script type="text/javascript">
	$(document).ready(function() {
		select_nav("#nav_journey_list");
	});
</script>
