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
			<li class="active"><spring:message code="journey.create" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="journey.create" />
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
		<div class="panel panel-default wizard">
			<div id="form-wizard" class="wizard-steps clearfix">
				<ul class="steps">
					<li class="complete" data-target="#step1"><span class="badge badge-success">1</span> <spring:message code="journey.choose.type" /></li>
					<li class="active" data-target="#step2" class=""><span class="badge">2</span> <spring:message code="journey.fill.form" /></li>
					<li data-target="#step3"><span class="badge">3</span> <spring:message code="journey.form.preview" /></li>
					<li data-target="#step3"><span class="badge">4</span> <spring:message code="done" /></li>
				</ul>
			</div>
			<div class="step-content clearfix">
				<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/journey/preview/form" modelAttribute="journey" autocomplete="off" method="post">
					<form:hidden path="activity.code" />
					<form:hidden path="type" />
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="journey.type" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${journey.type.description}</p>
							<form:errors cssClass="help-block m-b-none" path="type" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="name">
							<spring:message code="journey.name" />
						</form:label>
						<div class="col-sm-10">
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="name" maxlength="70" />
							<form:errors cssClass="help-block m-b-none" path="name" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="location">
							<spring:message code="journey.location" />
						</form:label>
						<div class="col-sm-10">
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="location" maxlength="70" />
							<form:errors cssClass="help-block m-b-none" path="location" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="startDate">
							<spring:message code="journey.startDate" />
						</form:label>
						<div class="col-sm-10">
							<form:input cssClass="input-sm input-s datepicker-input form-control" cssErrorClass="input-sm input-s datepicker-input form-control parsley-error" path="startDate" maxlength="10" data-date-format="<%=Constant.DATE_PICKER_FORMAT%>" />
							<form:errors cssClass="help-block m-b-none" path="startDate" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="endDate">
							<spring:message code="journey.endDate" />
						</form:label>
						<div class="col-sm-10">
							<form:input cssClass="input-sm input-s datepicker-input form-control" cssErrorClass="input-sm input-s datepicker-input form-control parsley-error" path="endDate" maxlength="10" data-date-format="<%=Constant.DATE_PICKER_FORMAT%>" />
							<form:errors cssClass="help-block m-b-none" path="endDate" />
						</div>
					</div>
					<c:if test="${journey.type eq 'FULLDAY' or journey.type eq 'FULLBOARD'}">
						<div class="line line-dashed line-lg pull-in"></div>
						<div class="form-group">
							<form:label cssClass="col-sm-2 control-label" path="packetMeetingAmount">
								<spring:message code="journey.packet.meeting.amount" />
							</form:label>
							<div class="col-sm-10">
								<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="packetMeetingAmount" maxlength="70" />
								<form:errors cssClass="help-block m-b-none" path="packetMeetingAmount" />
							</div>
						</div>
						<div class="form-group">
							<form:label cssClass="col-sm-2 control-label" path="packetMeetingAccount.accountNumber">
								<spring:message code="journey.account.packet.meeting" />
							</form:label>
							<div class="col-sm-10">
								<form:select path="packetMeetingAccount.accountNumber" style="width: 70%;">
									<form:option value="" />
									<c:forEach var="account" items="${activity.accounts}">
										<form:option value="${account.accountNumber }">${account.accountNumber } - ${account.name }</form:option>
									</c:forEach>
								</form:select>
								<form:errors cssClass="help-block m-b-none" path="packetMeetingAccount.accountNumber" />
							</div>
						</div>
					</c:if>
					<c:if test="${journey.type eq 'JOURNEY_IN' or journey.type eq 'JOURNEY_OUT'}">
						<div class="line line-dashed line-lg pull-in"></div>
						<div class="form-group">
							<form:label cssClass="col-sm-2 control-label" path="innAmount">
								<spring:message code="journey.inn.amount" />
							</form:label>
							<div class="col-sm-10">
								<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="innAmount" maxlength="70" />
								<form:errors cssClass="help-block m-b-none" path="innAmount" />
							</div>
						</div>
						<div class="form-group">
							<form:label cssClass="col-sm-2 control-label" path="innAccount.accountNumber">
								<spring:message code="journey.account.inn" />
							</form:label>
							<div class="col-sm-10">
								<form:select path="innAccount.accountNumber" style="width: 70%;">
									<form:option value="" />
									<c:forEach var="account" items="${activity.accounts}">
										<form:option value="${account.accountNumber }">${account.accountNumber } - ${account.name }</form:option>
									</c:forEach>
								</form:select>
								<form:errors cssClass="help-block m-b-none" path="innAccount.accountNumber" />
							</div>
						</div>
					</c:if>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="participantAccount.accountNumber">
							<spring:message code="journey.account.participant" />
						</form:label>
						<div class="col-sm-10">
							<form:select path="participantAccount.accountNumber" style="width: 70%;">
								<form:option value="" />
								<c:forEach var="account" items="${activity.accounts}">
									<form:option value="${account.accountNumber }">${account.accountNumber } - ${account.name }</form:option>
								</c:forEach>
							</form:select>
							<form:errors cssClass="help-block m-b-none" path="participantAccount.accountNumber" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="moderatorAccount.accountNumber">
							<spring:message code="journey.account.moderator" />
						</form:label>
						<div class="col-sm-10">
							<form:select path="moderatorAccount.accountNumber" style="width: 70%;">
								<form:option value="" />
								<c:forEach var="account" items="${activity.accounts}">
									<form:option value="${account.accountNumber }">${account.accountNumber } - ${account.name }</form:option>
								</c:forEach>
							</form:select>
							<form:errors cssClass="help-block m-b-none" path="moderatorAccount.accountNumber" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="informantAccount.accountNumber">
							<spring:message code="journey.account.informant" />
						</form:label>
						<div class="col-sm-10">
							<form:select path="informantAccount.accountNumber" style="width: 70%;">
								<form:option value="" />
								<c:forEach var="account" items="${activity.accounts}">
									<form:option value="${account.accountNumber }">${account.accountNumber } - ${account.name }</form:option>
								</c:forEach>
							</form:select>
							<form:errors cssClass="help-block m-b-none" path="informantAccount.accountNumber" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="informantTransportAccount.accountNumber">
							<spring:message code="journey.account.informant.transport" />
						</form:label>
						<div class="col-sm-10">
							<form:select path="informantTransportAccount.accountNumber" style="width: 70%;">
								<form:option value="" />
								<c:forEach var="account" items="${activity.accounts}">
									<form:option value="${account.accountNumber }">${account.accountNumber } - ${account.name }</form:option>
								</c:forEach>
							</form:select>
							<form:errors cssClass="help-block m-b-none" path="informantTransportAccount.accountNumber" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="requirementAccount.accountNumber">
							<spring:message code="journey.account.requirement" />
						</form:label>
						<div class="col-sm-10">
							<form:select path="requirementAccount.accountNumber" style="width: 70%;">
								<form:option value="" />
								<c:forEach var="account" items="${activity.accounts}">
									<form:option value="${account.accountNumber }">${account.accountNumber } - ${account.name }</form:option>
								</c:forEach>
							</form:select>
							<form:errors cssClass="help-block m-b-none" path="requirementAccount.accountNumber" />
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="returnAmountAccount.accountNumber">
							<spring:message code="journey.account.return" />
						</form:label>
						<div class="col-sm-10">
							<form:select path="returnAmountAccount.accountNumber" style="width: 70%;">
								<form:option value="" />
								<c:forEach var="account" items="${activity.accounts}">
									<form:option value="${account.accountNumber }">${account.accountNumber } - ${account.name }</form:option>
								</c:forEach>
							</form:select>
							<form:errors cssClass="help-block m-b-none" path="returnAmountAccount.accountNumber" />
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-2">
							<a href="${pageContext.request.contextPath}/journey/precreate/type/${activity.code}/?type=${journey.type}" class="btn btn-default"><spring:message code="cancel" /></a>
							<form:button class="btn btn-primary">
								<spring:message code="send" />
							</form:button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
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
