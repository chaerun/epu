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
			<li class="active"><spring:message code="informant.journey.add" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="informant.journey.add" />
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
			<header class="panel-heading font-bold">
				<spring:message code="informant" />
			</header>
			<div class="panel-body">
				<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/informant/preview" modelAttribute="informant" autocomplete="off">
					<form:hidden path="journey.id" />
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
						<form:label cssClass="col-sm-2 control-label" path="nip">
							<spring:message code="informant.journey.nip" />
						</form:label>
						<div class="col-sm-6">
							<c:set var="text_informant_journey_nip">
								<spring:message code="informant.journey.nip" />
							</c:set>
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="nip" maxlength="40" placeholder="${text_informant_journey_nip }" />
							<form:errors cssClass="help-block m-b-none" path="nip" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="name">
							<spring:message code="informant.journey.name" />
						</form:label>
						<div class="col-sm-6">
							<c:set var="text_informant_journey_name">
								<spring:message code="informant.journey.name" />
							</c:set>
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="name" maxlength="100" placeholder="${text_informant_journey_name }" />
							<form:errors cssClass="help-block m-b-none" path="name" />
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="position">
							<spring:message code="informant.journey.position" />
						</form:label>
						<div class="col-sm-6">
							<c:set var="text_informant_journey_position">
								<spring:message code="informant.journey.position" />
							</c:set>
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="position" maxlength="100" placeholder="${text_informant_journey_position }" />
							<form:errors cssClass="help-block m-b-none" path="position" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="grade">
							<spring:message code="informant.journey.grade" />
						</form:label>
						<div class="col-sm-6">
							<c:set var="text_informant_journey_grade">
								<spring:message code="informant.journey.grade" />
							</c:set>
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="grade" maxlength="100" placeholder="${text_informant_journey_grade }" />
							<form:errors cssClass="help-block m-b-none" path="grade" />
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="startDate">
							<spring:message code="informant.journey.startDate" />
						</form:label>
						<div class="col-sm-10">
							<c:set var="text_informant_journey_startDate">
								<spring:message code="informant.journey.startDate" />
							</c:set>
							<form:input cssClass="input-sm input-s datepicker-input form-control" cssErrorClass="input-sm input-s datepicker-input form-control parsley-error" path="startDate" maxlength="10" data-date-format="<%=Constant.DATE_PICKER_FORMAT%>" placeholder="${text_informant_journey_startDate }" />
							<form:errors cssClass="help-block m-b-none" path="startDate" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="endDate">
							<spring:message code="informant.journey.endDate" />
						</form:label>
						<div class="col-sm-10">
							<c:set var="text_informant_journey_endDate">
								<spring:message code="informant.journey.endDate" />
							</c:set>
							<form:input cssClass="input-sm input-s datepicker-input form-control" cssErrorClass="input-sm input-s datepicker-input form-control parsley-error" path="endDate" maxlength="10" data-date-format="<%=Constant.DATE_PICKER_FORMAT%>" placeholder="${text_informant_journey_endDate}" />
							<form:errors cssClass="help-block m-b-none" path="endDate" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="amount">
							<spring:message code="informant.journey.amount" />
						</form:label>
						<div class="col-sm-6">
							<c:set var="text_informant_journey_amount">
								<spring:message code="informant.journey.amount" />
							</c:set>
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="amount" maxlength="18" placeholder="${text_informant_journey_amount }" />
							<form:errors cssClass="help-block m-b-none" path="amount" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="hours">
							<spring:message code="informant.journey.hours" />
						</form:label>
						<div class="col-sm-6">
							<c:set var="text_informant_journey_hours">
								<spring:message code="informant.journey.hours" />
							</c:set>
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="hours" maxlength="1" placeholder="${text_informant_journey_hours }" />
							<form:errors cssClass="help-block m-b-none" path="hours" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="ppn">
							<spring:message code="informant.journey.ppn" />
						</form:label>
						<div class="col-sm-6">
							<c:set var="text_informant_journey_ppn">
								<spring:message code="informant.journey.ppn" />
							</c:set>
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="ppn" maxlength="2" placeholder="${text_informant_journey_ppn }" />
							<form:errors cssClass="help-block m-b-none" path="ppn" />
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<c:if test="${journey.type eq 'FULLDAY' or journey.type eq 'JOURNEY_IN'}">
						<div class="form-group">
							<form:label cssClass="col-sm-2 control-label" path="transportAmount">
								<spring:message code="informant.journey.transportAmount" />
							</form:label>
							<div class="col-sm-6">
								<c:set var="text_informant_journey_transportAmount">
									<spring:message code="informant.journey.transportAmount" />
								</c:set>
								<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="transportAmount" maxlength="18" placeholder="${text_informant_journey_transportAmount }" />
								<form:errors cssClass="help-block m-b-none" path="transportAmount" />
							</div>
						</div>
					</c:if>
					<c:if test="${journey.type eq 'FULLBOARD' or journey.type eq 'JOURNEY_OUT'}">
						<div class="line line-dashed line-lg pull-in"></div>
						<div class="form-group">
							<form:label cssClass="col-sm-2 control-label" path="fromLocation">
								<spring:message code="informant.journey.fromLocation" />
							</form:label>
							<div class="col-sm-6">
								<c:set var="text_informant_journey_fromLocation">
									<spring:message code="informant.journey.fromLocation" />
								</c:set>
								<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="fromLocation" maxlength="100" placeholder="${text_informant_journey_fromLocation }" />
								<form:errors cssClass="help-block m-b-none" path="fromLocation" />
							</div>
						</div>
						<c:set var="text_journey_go">
							<spring:message code="journey.go" />
						</c:set>
						<c:set var="text_journey_tax">
							<spring:message code="journey.tax" />
						</c:set>
						<c:set var="text_journey_back">
							<spring:message code="journey.back" />
						</c:set>
						<div class="form-group">
							<form:label cssClass="col-sm-2 control-label" path="planeGoAmount">
								<spring:message code="informant.journey.planeAmount" />
							</form:label>
							<div class="col-sm-10">
								<div class="row">
									<div class="col-sm-5">
										<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="planeGoAmount" maxlength="18" placeholder="${text_journey_go}" />
										<form:errors cssClass="help-block m-b-none" path="planeGoAmount" />
									</div>
									<div class="col-sm-1">(<spring:message code="journey.go" />)
									</div>
									<div class="col-sm-3">
										<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="planeGoTax" maxlength="18" placeholder="${text_journey_tax}" />
										<form:errors cssClass="help-block m-b-none" path="planeGoTax" />
									</div>
									<div class="col-sm-1">(<spring:message code="journey.tax" />)
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<form:label cssClass="col-sm-2 control-label" path="planeBackAmount">
							</form:label>
							<div class="col-sm-10">
								<div class="row">
									<div class="col-sm-5">
										<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="planeBackAmount" maxlength="18" placeholder="${text_journey_back}" />
										<form:errors cssClass="help-block m-b-none" path="planeBackAmount" />
									</div>
									<div class="col-sm-1">(<spring:message code="journey.back" />)									)
									</div>
									<div class="col-sm-3">
										<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="planeBackTax" maxlength="18" placeholder="${text_journey_tax}" />
										<form:errors cssClass="help-block m-b-none" path="planeBackTax" />
									</div>
									<div class="col-sm-1">(<spring:message code="journey.tax" />)
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<form:label cssClass="col-sm-2 control-label" path="taxiGoAmount">
								<spring:message code="informant.journey.taxiAmount" />
							</form:label>
							<div class="col-sm-10">
								<div class="row">
									<div class="col-sm-5">
										<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="taxiGoAmount" maxlength="18" placeholder="${text_journey_go}" />
										<form:errors cssClass="help-block m-b-none" path="taxiGoAmount" />
									</div>
									<div class="col-sm-1">(
										<spring:message code="journey.go" />)
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<form:label cssClass="col-sm-2 control-label" path="taxiBackAmount">
							</form:label>
							<div class="col-sm-10">
								<div class="row">
									<div class="col-sm-5">
										<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="taxiBackAmount" maxlength="18" placeholder="${text_journey_back}" />
										<form:errors cssClass="help-block m-b-none" path="taxiBackAmount" />
									</div>
									<div class="col-sm-1">(<spring:message code="journey.back" />)
									</div>
								</div>
							</div>
						</div>
					</c:if>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-2">
							<a href="${pageContext.request.contextPath}/journey/detail/${journey.id}/" class="btn btn-default"><spring:message code="cancel" /></a>
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
				<spring:message code="informant.journey.list" />
			</header>
			<div class="panel-body">
				<c:if test="${journey.type eq 'FULLDAY' or journey.type eq 'JOURNEY_IN'}">
					<c:set var="totals" value="0" />
					<display:table id="informant" class="table table-striped" name="${journey.informants }">
						<display:column titleKey="action">
							<a href="${pageContext.request.contextPath}/informant/view/${informant.id}/" class="btn btn-sm btn-info"><i class="fa fa-eye"></i> <spring:message code="view.detail" /> </a>
							<a href="${pageContext.request.contextPath}/informant/predelete/${informant.id}/" class="btn btn-sm btn-danger"><i class="fa fa-minus-square"></i> <spring:message code="delete" /></a>
						</display:column>
						<display:column titleKey="informant">${informant.nip }&nbsp;-&nbsp;${informant.name }</display:column>
						<display:column titleKey="informant.journey.startDate">
							<fmt:formatDate value="${informant.startDate}" pattern="<%= Constant.DATE_FORMAT %>" />&nbsp;-&nbsp;<fmt:formatDate value="${informant.endDate}" pattern="<%= Constant.DATE_FORMAT %>" />
						</display:column>
						<display:column titleKey="informant.journey.amount">
							<fmt:formatNumber value="${informant.amount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
								x ${informant.days} (
								<spring:message code="day" />
								) x ${informant.hours} (
								<spring:message code="hour" />
								) =
								<fmt:formatNumber value="${informant.amount * informant.days * informant.hours}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
						</display:column>
						<display:column titleKey="informant.journey.transportAmount">
							<fmt:formatNumber value="${informant.transportAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
						</display:column>
						<display:column titleKey="total">
							<fmt:formatNumber value="${informant.totalAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
							<c:set var="totals" value="${totals + informant.totalAmount}"></c:set>
						</display:column>
						<display:column titleKey="total.received.amount">
							<spring:message code="total" />&nbsp;-&nbsp;${informant.ppn}&nbsp;
								<spring:message code="symbol.percent" />
								=
								<fmt:formatNumber value="${informant.receivedAmount }" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
						</display:column>
						<display:footer>
							<tr>
								<td colspan="5" class="font-bold"><spring:message code="total" /></td>
								<td><fmt:formatNumber value="${totals}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" /></td>
								<td class="font-bold">&nbsp;</td>
							</tr>
						</display:footer>
					</display:table>
				</c:if>
				<c:if test="${journey.type eq 'FULLBOARD' or journey.type eq 'JOURNEY_OUT'}">
					<c:set var="totals" value="0" />
					<display:table id="informant" class="table table-striped" name="${journey.informants }">
						<display:column titleKey="action">
							<a href="${pageContext.request.contextPath}/informant/view/${informant.id}/" class="btn btn-sm btn-info"><i class="fa fa-eye"></i> <spring:message code="view.detail" /> </a>
							<a href="${pageContext.request.contextPath}/informant/predelete/${informant.id}/" class="btn btn-sm btn-danger"><i class="fa fa-minus-square"></i> <spring:message code="delete" /></a>
						</display:column>
						<display:column titleKey="informant">${informant.nip }&nbsp;-&nbsp;${informant.name }</display:column>
						<display:column titleKey="informant.journey.startDate">
							<fmt:formatDate value="${informant.startDate}" pattern="<%= Constant.DATE_FORMAT %>" />&nbsp;-&nbsp;<fmt:formatDate value="${informant.endDate}" pattern="<%= Constant.DATE_FORMAT %>" />
						</display:column>
						<display:column titleKey="informant.journey.amount">
							<fmt:formatNumber value="${informant.amount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
								x ${informant.days} (
								<spring:message code="day" />
								) x ${informant.hours} (
								<spring:message code="hour" />
								) =
								<fmt:formatNumber value="${informant.amount * informant.days * informant.hours}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
						</display:column>
						<display:column titleKey="informant.journey.planeAmount">
							<fmt:formatNumber value="${informant.planeGoAmount + informant.planeGoTax+informant.planeBackAmount + informant.planeBackTax}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
						</display:column>
						<display:column titleKey="informant.journey.taxiAmount">
							<fmt:formatNumber value="${informant.taxiGoAmount + informant.taxiBackAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
						</display:column>
						<display:column titleKey="total">
							<fmt:formatNumber value="${informant.totalAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
							<c:set var="totals" value="${totals + informant.totalAmount}"></c:set>
						</display:column>
						<display:column titleKey="total.received.amount">
							<spring:message code="total" />&nbsp;-&nbsp;${informant.ppn}&nbsp;
								<spring:message code="symbol.percent" />
								=
								<fmt:formatNumber value="${informant.receivedAmount }" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
						</display:column>
						<display:footer>
							<tr>
								<td colspan="6" class="font-bold"><spring:message code="total" /></td>
								<td><fmt:formatNumber value="${totals}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" /></td>
								<td class="font-bold">&nbsp;</td>
							</tr>
						</display:footer>
					</display:table>
				</c:if>
			</div>
		</section>
	</section>
</section>
<script type="text/javascript">
	$(document).ready(function() {
		select_nav("#nav_journey_list");
	});
</script>
