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
			<li class="active"><spring:message code="journey.account.edit" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="journey.account.edit" />
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
				</form>
			</div>
		</section>
		<section class="panel panel-default">
			<header class="panel-heading font-bold">
				<spring:message code="journey.account.edit" />
			</header>

			<div class="panel-body">
				<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/journeyaccount/create" modelAttribute="journey" autocomplete="off" method="post">
					<form:hidden path="id" />
					<form:hidden path="packetMeetingAccount.accountNumber" />
					<form:hidden path="participantAccount.accountNumber" />
					<form:hidden path="moderatorAccount.accountNumber" />
					<form:hidden path="informantAccount.accountNumber" />
					<form:hidden path="informantTransportAccount.accountNumber" />
					<form:hidden path="requirementAccount.accountNumber" />
					<form:hidden path="innAccount.accountNumber" />
					<form:hidden path="returnAmountAccount.accountNumber" />
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="journey.name" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${journey.name}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="journey.location" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${journey.location}</p>
						</div>
					</div>
					<c:if test="${journey.type eq 'FULLDAY' or journey.type eq 'FULLBOARD'}">
						<div class="line line-dashed line-lg pull-in"></div>
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
		$("select[name='participantAccount.accountNumber']").select2();
		$("select[name='moderatorAccount.accountNumber']").select2();
		$("select[name='participantAccount.accountNumber']").select2();
		$("select[name='informantAccount.accountNumber']").select2();
		$("select[name='requirementAccount.accountNumber']").select2();
		$("select[name='packetMeetingAccount.accountNumber']").select2();
		$("select[name='innAccount.accountNumber']").select2();
		$("select[name='returnAmountAccount.accountNumber']").select2();
		$("select[name='informantTransportAccount.accountNumber']").select2();
	});
</script>
