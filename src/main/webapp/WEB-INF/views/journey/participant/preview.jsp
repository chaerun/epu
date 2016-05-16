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
			<li class="active"><spring:message code="participant.journey.add" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="participant.journey.add" />
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
				<spring:message code="participant" />
			</header>
			<div class="panel-body">
				<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/participantjourney/create" modelAttribute="participantJourney" autocomplete="off">
					<form:hidden path="journey.id" />
					<form:hidden path="flag" />
					<form:hidden path="startDate" />
					<form:hidden path="endDate" />
					<form:hidden path="dailyAmount" />
					<form:hidden path="transportAmount" />
					<form:hidden path="fromLocation" />
					<form:hidden path="planeGoAmount" />
					<form:hidden path="planeGoTax" />
					<form:hidden path="planeBackAmount" />
					<form:hidden path="planeBackTax" />
					<form:hidden path="taxiGoAmount" />
					<form:hidden path="taxiBackAmount" />
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="journey.account.participant" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${journey.participantAccount.accountNumber }&nbsp;-&nbsp;${journey.participantAccount.name}</p>
						</div>
					</div>
					
					<c:if test="${not participantJourney.flag}">
						<form:hidden path="participant.code" />
						<div class="form-group">
							<label class="col-sm-2 control-label font-bold"><spring:message code="participant" /></label>
							<div class="col-sm-10">
								<p class="form-control-static">${participantJourney.participant.code}&nbsp;-&nbsp;${participantJourney.participant.name}</p>
							</div>
						</div>
					</c:if>
					
					<c:if test="${participantJourney.flag}">
						<form:hidden path="name" />
						<div class="form-group">
							<label class="col-sm-2 control-label font-bold"><spring:message code="participant.name" /></label>
							<div class="col-sm-10">
								<p class="form-control-static">${participantJourney.name}</p>
							</div>
						</div>
					</c:if>
					
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="participant.journey.startDate" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<fmt:formatDate value="${participantJourney.startDate}" pattern="<%= Constant.DATE_FORMAT %>" />
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="participant.journey.endDate" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<fmt:formatDate value="${participantJourney.endDate}" pattern="<%= Constant.DATE_FORMAT %>" />
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="participant.journey.dailyAmount" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<fmt:formatNumber value="${participantJourney.dailyAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
								x ${participantJourney.days} (<spring:message code="day" />) =
								<fmt:formatNumber value="${participantJourney.dailyAmount * participantJourney.days}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
							</p>
						</div>
					</div>
					<c:if test="${journey.type eq 'FULLDAY' or journey.type eq 'JOURNEY_IN'}">
						<div class="form-group">
							<label class="col-sm-2 control-label font-bold"><spring:message code="participant.journey.transportAmount" /></label>
							<div class="col-sm-10">
								<p class="form-control-static">
									<fmt:formatNumber value="${participantJourney.transportAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
								</p>
							</div>
						</div>
					</c:if>
					<c:if test="${journey.type eq 'FULLBOARD' or journey.type eq 'JOURNEY_OUT'}">
						<div class="line line-dashed line-lg pull-in"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label font-bold"><spring:message code="participant.journey.fromLocation" /></label>
							<div class="col-sm-10">
								<p class="form-control-static">${participantJourney.fromLocation}</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label font-bold"> <spring:message code="participant.journey.planeAmount" /></label>
							<div class="col-sm-10">
								<p class="form-control-static">
									<fmt:formatNumber value="${participantJourney.planeGoAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
									(<spring:message code="journey.go" />) +
									<fmt:formatNumber value="${participantJourney.planeGoTax}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
									(<spring:message code="journey.tax" />) =
									<fmt:formatNumber value="${participantJourney.planeGoAmount + participantJourney.planeGoTax}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
								</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label font-bold"></label>
							<div class="col-sm-10">
								<p class="form-control-static">
									<fmt:formatNumber value="${participantJourney.planeBackAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
									(<spring:message code="journey.back" />) +
									<fmt:formatNumber value="${participantJourney.planeBackTax}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
									(<spring:message code="journey.tax" />) =
									<fmt:formatNumber value="${participantJourney.planeBackAmount + participantJourney.planeBackTax}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
								</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label font-bold"> <spring:message code="participant.journey.taxiAmount" /></label>
							<div class="col-sm-10">
								<p class="form-control-static">
									<fmt:formatNumber value="${participantJourney.taxiGoAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
									(<spring:message code="journey.go" />) +
									<fmt:formatNumber value="${participantJourney.taxiBackAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
									(<spring:message code="journey.back" />) =
									<fmt:formatNumber value="${participantJourney.taxiGoAmount + participantJourney.taxiBackAmount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
								</p>
							</div>
						</div>
					</c:if>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"> <spring:message code="total" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<fmt:formatNumber value="${participantJourney.totalAmount }" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
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
		select_nav("#nav_journey_list");
		$("select[name='participant.code']").select2();
	});
</script>
