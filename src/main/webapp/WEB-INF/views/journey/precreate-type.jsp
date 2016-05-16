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
					<li class="active" data-target="#step1"><span class="badge badge-info">1</span> <spring:message code="journey.choose.type" /></li>
					<li data-target="#step2" class=""><span class="badge">2</span> <spring:message code="journey.fill.form" /></li>
					<li data-target="#step3"><span class="badge">3</span> <spring:message code="journey.form.preview" /></li>
					<li data-target="#step3"><span class="badge">4</span> <spring:message code="done" /></li>
				</ul>
			</div>
			<div class="step-content clearfix">
				<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/journey/precreate/form" modelAttribute="journeyTypeForm" autocomplete="off">
					<form:hidden path="activity.code" />
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="type">
							<spring:message code="journey.type" />
						</form:label>
						<div class="col-sm-10">
							<form:select path="type" style="width: 50%;">
								<form:option value="" />
								<form:options items="${types }" itemLabel="description" itemValue="id" />
							</form:select>
							<form:errors cssClass="help-block m-b-none" path="type" />
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-2">
							<a href="${pageContext.request.contextPath}/journey/activity/${activity.code}/" class="btn btn-default"><spring:message code="cancel" /></a>
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
		$("select[name='type']").select2();
	});
</script>
