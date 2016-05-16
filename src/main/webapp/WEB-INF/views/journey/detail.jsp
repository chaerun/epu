<%@page import="com.kemenkes.epu.common.util.Constant"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<section class="vbox">
	<section class="scrollable padder">
		<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
			<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-home"></i> <spring:message code="home" /></a></li>
			<li><a href="${pageContext.request.contextPath}/journey/activity"><spring:message code="activity.list" />&nbsp;<spring:message code="year" />&nbsp;${activity.year}</a></li>
			<li><a href="${pageContext.request.contextPath}/journey/activity/${activity.code}/"><spring:message code="journey.list" /></a></li>
			<li class="active"><spring:message code="journey.detail" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="journey.detail" />
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
							<p class="form-control-static">${journey.activity.code}</p>
							<form:errors cssClass="help-block m-b-none" path="activity.code" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"> <spring:message code="activity.name" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${journey.activity.name}</p>
						</div>
					</div>
				</form>
			</div>
		</section>
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
					<c:if test="${journey.type eq 'FULLDAY' or journey.type eq 'FULLBOARD'}">
						<div class="line line-dashed line-lg pull-in"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label font-bold"><spring:message code="journey.packet.meeting.amount" /></label>
							<div class="col-sm-10">
								<p class="form-control-static">
									<fmt:formatNumber value="${journey.packetMeetingAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
								</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label font-bold"><spring:message code="journey.account.packet.meeting" /></label>
							<div class="col-sm-10">
								<p class="form-control-static">${journey.packetMeetingAccount.accountNumber}&nbsp;-&nbsp;${journey.packetMeetingAccount.name}</p>
							</div>
						</div>
					</c:if>
					<c:if test="${journey.type eq 'JOURNEY_IN' or journey.type eq 'JOURNEY_OUT'}">
						<div class="line line-dashed line-lg pull-in"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label font-bold"><spring:message code="journey.inn.amount" /></label>
							<div class="col-sm-10">
								<p class="form-control-static">
									<fmt:formatNumber value="${journey.innAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
								</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label font-bold"><spring:message code="journey.account.inn" /></label>
							<div class="col-sm-10">
								<p class="form-control-static">${journey.innAccount.accountNumber}&nbsp;-&nbsp;${journey.innAccount.name}</p>
							</div>
						</div>
					</c:if>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="journey.account.participant" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${journey.participantAccount.accountNumber}&nbsp;-&nbsp;${journey.participantAccount.name}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="journey.account.moderator" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${journey.moderatorAccount.accountNumber}&nbsp;-&nbsp;${journey.moderatorAccount.name}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="journey.account.informant" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${journey.informantAccount.accountNumber}&nbsp;-&nbsp;${journey.informantAccount.name}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="journey.account.informant.transport" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${journey.informantTransportAccount.accountNumber}&nbsp;-&nbsp;${journey.informantTransportAccount.name}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="journey.account.requirement" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${journey.requirementAccount.accountNumber}&nbsp;-&nbsp;${journey.requirementAccount.name}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="journey.account.return" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${journey.returnAmountAccount.accountNumber}&nbsp;-&nbsp;${journey.returnAmountAccount.name}</p>
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="total.participant" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<fmt:formatNumber value="${journey.totalAmountParticipant + journey.totalAmountTransportInformant}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="total.moderator" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<fmt:formatNumber value="${journey.totalAmountModerator}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="total.informant" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<fmt:formatNumber value="${journey.totalAmountHonorInformant}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="total.requirement" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<fmt:formatNumber value="${journey.totalAmountRequirement}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
							</p>
						</div>
					</div>
					<c:if test="${journey.type eq 'JOURNEY_IN' or journey.type eq 'JOURNEY_OUT'}">
						<div class="form-group">
							<label class="col-sm-2 control-label font-bold"><spring:message code="total.inn" /></label>
							<div class="col-sm-10">
								<p class="form-control-static">
									<fmt:formatNumber value="${journey.innAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
									x ${journey.days}
									<spring:message code="day" />
									x ( ${fn:length(journey.participants)}
									<spring:message code="participant" />
									+ ${fn:length(journey.informants)}
									<spring:message code="informant" />
									) =
									<fmt:formatNumber value="${journey.totalAmountInn}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
								</p>
							</div>
						</div>
					</c:if>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="total" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<fmt:formatNumber value="${journey.totalAmountAll}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
							</p>
						</div>
					</div>
					<c:if test="${journey.status eq 'IN_PROGRESS' or journey.status eq 'PENDING_COMPLETE' }">
						<div class="form-group">
							<label class="col-sm-2 control-label font-bold"><spring:message code="total.received.amount" /></label>
							<div class="col-sm-10">
								<p class="form-control-static">
									<fmt:formatNumber value="${received}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
								</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label font-bold"><spring:message code="total.return.amount" /></label>
							<div class="col-sm-10">
								<p class="form-control-static">
									<fmt:formatNumber value="${received-journey.totalAmountAll}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
								</p>
							</div>
						</div>
					</c:if>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-2">
							<c:if test="${journey.status eq 'CREATE_NEW' }">
								<a href="${pageContext.request.contextPath}/journey/release/${journey.id}/" class="btn btn-danger" onclick="return confirm('Lanjutkan ?');"><spring:message code="release.new" /></a>
							</c:if>
							<c:if test="${journey.status eq 'IN_PROGRESS' }">
								<a href="${pageContext.request.contextPath}/journey/releasecomplete/${journey.id}/" class="btn btn-danger" onclick="return confirm('Lanjutkan ?');"><spring:message code="release.complete" /></a>
							</c:if>
							<c:if test="${journey.status eq 'PENDING_COMPLETE' }">
								<a href="${pageContext.request.contextPath}/report/return/${journey.id}/" class="btn btn-danger" onclick="return confirm('Lanjutkan ?');"><spring:message code="print.return.amount" /></a>
							</c:if>
						</div>
					</div>
				</form>
			</div>
		</section>
		<c:if test="${journey.status eq 'CREATE_NEW' or journey.status eq 'IN_PROGRESS'}">
			<section class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal">
						<div class="form-group">
							<div class="col-sm-12">
								<a class="btn btn-primary" href="${pageContext.request.contextPath}/participantjourney/precreate/${journey.id}/"><i class="fa fa-plus-square"></i> <spring:message code="participant.journey.add" /> </a>
							</div>
						</div>
						<div class="line line-dashed line-lg pull-in"></div>
						<div class="form-group">
							<div class="col-sm-12">
								<a class="btn btn-primary" href="${pageContext.request.contextPath}/moderator/precreate/${journey.id}/"><i class="fa fa-plus-square"></i> <spring:message code="moderator.journey.add" /> </a>
							</div>
						</div>
						<div class="line line-dashed line-lg pull-in"></div>
						<div class="form-group">
							<div class="col-sm-12">
								<a class="btn btn-primary" href="${pageContext.request.contextPath}/informant/precreate/${journey.id}/"><i class="fa fa-plus-square"></i> <spring:message code="informant.journey.add" /> </a>
							</div>
						</div>
						<div class="line line-dashed line-lg pull-in"></div>
						<div class="form-group">
							<div class="col-sm-12">
								<a class="btn btn-primary" href="${pageContext.request.contextPath}/requirement/precreate/${journey.id}/"><i class="fa fa-plus-square"></i> <spring:message code="requirement.journey.add" /> </a>
							</div>
						</div>
						<div class="line line-dashed line-lg pull-in"></div>
						<div class="form-group">
							<div class="col-sm-12">
								<a class="btn btn-primary" href="${pageContext.request.contextPath}/journeyaccount/preedit/${journey.id}/"><i class="fa fa-plus-square"></i> <spring:message code="journey.account.edit" /> </a>
							</div>
						</div>
					</form>
				</div>
			</section>
		</c:if>
		<section class="panel panel-default">
			<div class="panel-body">
				<form class="form-horizontal">
					<div class="form-group">
						<div class="col-sm-12">
							<a class="btn btn-warning" href="${pageContext.request.contextPath}/report/normatif/${journey.id}/"><i class="fa fa-print"></i> <spring:message code="print.normatif" /> </a>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<a class="btn btn-warning" href="${pageContext.request.contextPath}/report/attendance/${journey.id}/"><i class="fa fa-print"></i> <spring:message code="print.attendance" /> </a>
						</div>
					</div>
					<c:if test="${journey.type eq 'FULLDAY' or journey.type eq 'JOURNEY_IN'}">
						<div class="line line-dashed line-lg pull-in"></div>
						<div class="form-group">
							<div class="col-sm-12">
								<a class="btn btn-warning" href="${pageContext.request.contextPath}/report/moderator/${journey.id}/"><i class="fa fa-print"></i> <spring:message code="print.honor.moderator" /> </a>
							</div>
						</div>
						<div class="line line-dashed line-lg pull-in"></div>
						<div class="form-group">
							<div class="col-sm-12">
								<a class="btn btn-warning" href="${pageContext.request.contextPath}/report/narasumber/${journey.id}/"><i class="fa fa-print"></i> <spring:message code="print.honor.narasumber" /> </a>
							</div>
						</div>
						<div class="line line-dashed line-lg pull-in"></div>
						<div class="form-group">
							<div class="col-sm-12">
								<a class="btn btn-warning" href="${pageContext.request.contextPath}/report/actlist/${journey.id}/"><i class="fa fa-print"></i> <spring:message code="print.actlist" /> </a>
							</div>
						</div>
					</c:if>
				</form>
			</div>
		</section>
		<section class="panel panel-default">
			<header class="panel-heading font-bold">
				<spring:message code="participant.journey.list" />
			</header>
			<div class="panel-body">
				<c:if test="${journey.type eq 'FULLDAY' or journey.type eq 'JOURNEY_IN'}">
					<c:set var="totals" value="0" />
					<display:table id="participantJourney" class="table table-striped" name="${journey.participants }">
						<display:column titleKey="action">
							<a href="${pageContext.request.contextPath}/participantjourney/view/${participantJourney.id}/" class="btn btn-sm btn-info"><i class="fa fa-eye"></i> <spring:message code="view.detail" /> </a>
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
						<display:column titleKey="participant.journey.dailyAmount">
							<fmt:formatNumber value="${participantJourney.dailyAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
								x ${participantJourney.days} (<spring:message code="day" />) =
								<fmt:formatNumber value="${participantJourney.dailyAmount * participantJourney.days}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
						</display:column>
						<display:column titleKey="participant.journey.transportAmount">
							<fmt:formatNumber value="${participantJourney.transportAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
						</display:column>
						<display:column titleKey="total">
							<fmt:formatNumber value="${participantJourney.totalAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
							<c:set var="totals" value="${totals + participantJourney.totalAmount}"></c:set>
						</display:column>
						<display:footer>
							<tr>
								<td colspan="5" class="font-bold"><spring:message code="total" /></td>
								<td><fmt:formatNumber value="${totals}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" /></td>
							</tr>
						</display:footer>
					</display:table>
				</c:if>
				<c:if test="${journey.type eq 'FULLBOARD' or journey.type eq 'JOURNEY_OUT'}">
					<c:set var="totals" value="0" />
					<display:table id="participantJourney" class="table table-striped" name="${journey.participants }">
						<display:column titleKey="action">
							<a href="${pageContext.request.contextPath}/participantjourney/view/${participantJourney.id}/" class="btn btn-sm btn-info"><i class="fa fa-eye"></i> <spring:message code="view.detail" /> </a>
							<a href="${pageContext.request.contextPath}/report/detailjourney/participant/${participantJourney.id}/" class="btn btn-sm btn-info"><i class="fa fa-print"></i> <spring:message code="print.detail" /></a>
							<a href="${pageContext.request.contextPath}/report/participant/riil/${participantJourney.id}/" class="btn btn-sm btn-info"><i class="fa fa-print"></i> <spring:message code="print.riil" /></a>
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
						<display:column titleKey="participant.journey.dailyAmount">
							<fmt:formatNumber value="${participantJourney.dailyAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
								x ${participantJourney.days} (<spring:message code="day" />) =
								<fmt:formatNumber value="${participantJourney.dailyAmount * participantJourney.days}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
						</display:column>
						<display:column titleKey="participant.journey.planeAmount">
							<fmt:formatNumber value="${participantJourney.planeGoAmount + participantJourney.planeGoTax+participantJourney.planeBackAmount + participantJourney.planeBackTax}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
						</display:column>
						<display:column titleKey="participant.journey.taxiAmount">
							<fmt:formatNumber value="${participantJourney.taxiGoAmount + participantJourney.taxiBackAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
						</display:column>
						<display:column titleKey="total">
							<fmt:formatNumber value="${participantJourney.totalAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
							<c:set var="totals" value="${totals + participantJourney.totalAmount}"></c:set>
						</display:column>
						<display:footer>
							<tr>
								<td colspan="6" class="font-bold"><spring:message code="total" /></td>
								<td><fmt:formatNumber value="${totals}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" /></td>
							</tr>
						</display:footer>
					</display:table>
				</c:if>
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
							<spring:message code="symbol.percent" />
							=
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
							<a href="${pageContext.request.contextPath}/report/detailjourney/informant/${informant.id}/" class="btn btn-sm btn-info"><i class="fa fa-print"></i> <spring:message code="print.detail" /></a>
							<a href="${pageContext.request.contextPath}/report/informant/riil/${informant.id}/" class="btn btn-sm btn-info"><i class="fa fa-print"></i> <spring:message code="print.riil" /></a>
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
		<section class="panel panel-default">
			<header class="panel-heading font-bold">
				<spring:message code="requirement.journey.list" />
			</header>
			<div class="panel-body">
				<c:set var="totals" value="0" />
				<display:table id="requirement" class="table table-striped" name="${journey.requirements }">
					<display:column titleKey="requirement.journey.description">${requirement.description }</display:column>
					<display:column titleKey="requirement.journey.amount">
						<fmt:formatNumber value="${requirement.amount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
						<c:set var="totals" value="${totals + requirement.amount}" />
					</display:column>
					<display:footer>
						<tr>
							<td colspan="1" class="font-bold"><spring:message code="total" /></td>
							<td><fmt:formatNumber value="${totals}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" /></td>
						</tr>
					</display:footer>
				</display:table>
			</div>
		</section>
	</section>
</section>
<script type="text/javascript">
	$(document).ready(function() {
		select_nav("#nav_sa_journey_list");
	});
</script>
