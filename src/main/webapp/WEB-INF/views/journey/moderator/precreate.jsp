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
			<li class="active"><spring:message code="moderator.journey.add" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="moderator.journey.add" />
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
				<spring:message code="participant" />
			</header>
			<div class="panel-body">
				<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/moderator/preview" modelAttribute="participantJourney" autocomplete="off">
					<form:hidden path="journey.id" />
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="journey.account.participant" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${journey.moderatorAccount.accountNumber}-${journey.moderatorAccount.name}</p>
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="id">
							<spring:message code="participant" />
						</form:label>
						<div class="col-sm-10">
							<form:select path="id" style="width: 70%;">
								<form:option value="" />
								<c:forEach var="participantJourney" items="${participantJourneys}">
									<c:if test="${not participantJourney.flag}">
										<form:option value="${participantJourney.id}">
											 ${participantJourney.participant.code } - ${participantJourney.participant.name }
									 	</form:option>
									 </c:if>
								</c:forEach>
								
								<optgroup label="<spring:message code="participant.journey.flag" />">
                                    <c:forEach var="participantJourney" items="${participantJourneys}">
									<c:if test="${participantJourney.flag}">
										<form:option value="${participantJourney.id}">
											 ${participantJourney.name}
									 	</form:option>
									 </c:if>
									</c:forEach>
                                </optgroup>
							</form:select>
							<form:errors cssClass="help-block m-b-none" path="id" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="moderatorAmount">
							<spring:message code="moderator.journey.amount" />
						</form:label>
						<div class="col-sm-6">
							<c:set var="text_moderator_amount">
								<spring:message code="moderator.journey.amount" />
							</c:set>
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="moderatorAmount" maxlength="18" placeholder="${text_moderator_amount }" />
							<form:errors cssClass="help-block m-b-none" path="moderatorAmount" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="moderatorHours">
							<spring:message code="moderator.journey.hours" />
						</form:label>
						<div class="col-sm-6">
							<c:set var="text_moderator_journey_hours">
								<spring:message code="moderator.journey.hours" />
							</c:set>
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="moderatorHours" maxlength="1" placeholder="${text_moderator_journey_hours }" />
							<form:errors cssClass="help-block m-b-none" path="moderatorHours" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="moderatorPpn">
							<spring:message code="moderator.journey.ppn" />
						</form:label>
						<div class="col-sm-10">
							<div class="row">
								<div class="col-sm-6">
									<c:set var="text_moderator_ppn">
										<spring:message code="moderator.journey.ppn" />
									</c:set>
									<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="moderatorPpn" maxlength="2" placeholder="${text_moderator_ppn }" />
									<form:errors cssClass="help-block m-b-none" path="moderatorPpn" />
								</div>
								<div class="col-sm-4">
									<spring:message code="symbol.percent" />
								</div>
							</div>
						</div>
					</div>
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
				<spring:message code="moderator.journey.list" />
			</header>
			<div class="panel-body">
				<c:set var="moderatorTotals" value="0" />
				<c:set var="moderatorReceivedAmounts" value="0" />
				<display:table id="participantJourney" class="table table-striped" name="${journey.moderators }">
					<display:column titleKey="action">
						<a href="${pageContext.request.contextPath}/moderator/view/${participantJourney.id}/" class="btn btn-sm btn-info"><i class="fa fa-eye"></i> <spring:message code="view.detail" /> </a>
						<a href="${pageContext.request.contextPath}/moderator/predelete/${participantJourney.id}/" class="btn btn-sm btn-danger"><i class="fa fa-minus-square"></i> <spring:message code="delete" /></a>
					</display:column>
					<display:column titleKey="participant">
						<c:if test="${not participantJourney.flag}">
							${participantJourney.participant.code }&nbsp;-&nbsp;${participantJourney.participant.name }
						</c:if>
						<c:if test="${participantJourney.flag}">
							${participantJourney.name }
						</c:if>
					</display:column>
					<display:column titleKey="participant.journey.startDate">
						<fmt:formatDate value="${participantJourney.startDate}" pattern="<%= Constant.DATE_FORMAT %>" />&nbsp;-&nbsp;<fmt:formatDate value="${participantJourney.endDate}" pattern="<%= Constant.DATE_FORMAT %>" />
					</display:column>
					<display:column titleKey="total">
						<fmt:formatNumber value="${participantJourney.moderatorAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
								x ${participantJourney.days} (<spring:message code="day" />) x ${participantJourney.moderatorHours} (<spring:message code="hour" />) =
								<fmt:formatNumber value="${participantJourney.moderatorAmount * participantJourney.moderatorHours * participantJourney.days}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
						<c:set var="moderatorTotals" value="${moderatorTotals +participantJourney.moderatorTotalAmount}" />
					</display:column>
					<display:column titleKey="total.received.amount">
						<p class="form-control-static">
							<fmt:formatNumber value="${participantJourney.moderatorTotalAmount }" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
							&nbsp;-&nbsp;${participantJourney.moderatorPpn}&nbsp;
							<spring:message code="symbol.percent" /> =
							<fmt:formatNumber value="${participantJourney.moderatorReceivedAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
							<c:set var="moderatorReceivedAmounts" value="${ moderatorReceivedAmounts+participantJourney.moderatorReceivedAmount}" />
						</p>
					</display:column>
					<display:footer>
						<tr>
							<td colspan="3" class="font-bold"><spring:message code="total" /></td>
							<td><fmt:formatNumber value="${moderatorTotals}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" /></td>
							<td><fmt:formatNumber value="${moderatorReceivedAmounts}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" /></td>
						</tr>
					</display:footer>
				</display:table>
			</div>
		</section>
	</section>
</section>
<script type="text/javascript">
	$(document).ready(function() {
		select_nav("#nav_journey_list");
		$("select[name='id']").select2();
	});
</script>
