<%@page import="com.kemenkes.epu.entity.ActivityType"%>
<%@page import="java.util.Enumeration"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<section class="vbox">
	<section class="scrollable padder">
		<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
			<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-home"></i> <spring:message code="home" /></a></li>
			<li><a href="${pageContext.request.contextPath}/activity"><spring:message code="activity.list" /></a></li>
			<li class="active"><spring:message code="activity.create" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="activity.create" />
			</h3>
		</div>
		<section class="panel panel-default">
			<header class="panel-heading font-bold">&nbsp;</header>
			<div class="panel-body">
				<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/activity/preview" modelAttribute="activity" autocomplete="off">

					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="code">
							<spring:message code="activity.code" />
						</form:label>
						<div class="col-sm-10">
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="code" maxlength="25" />
							<form:errors cssClass="help-block m-b-none" path="code" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="name">
							<spring:message code="activity.name" />
						</form:label>
						<div class="col-sm-10">
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="name" maxlength="255" />
							<form:errors cssClass="help-block m-b-none" path="name" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="year">
							<spring:message code="official.year" />
						</form:label>
						<div class="col-sm-4">
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="year" maxlength="4" />
							<form:errors cssClass="help-block m-b-none" path="year" />
						</div>
						<div class="col-sm-6"></div>
					</div>

					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="amount">
							<spring:message code="activity.amount" />
						</form:label>
						<div class="col-sm-10">
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="amount" maxlength="18" />
							<form:errors cssClass="help-block m-b-none" path="amount" />
						</div>
					</div>

					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="source">
							<spring:message code="activity.source" />
						</form:label>
						<div class="col-sm-10">
							<form:radiobuttons path="source" items="${sources}" itemLabel="description" itemValue="id" />
							<form:errors cssClass="help-block m-b-none" path="source" />
						</div>
					</div>

					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="type">
							<spring:message code="activity.type" />
						</form:label>
						<div class="col-sm-10">
							<form:radiobuttons path="type" items="${types}" itemLabel="description" itemValue="id" />
							<form:errors cssClass="help-block m-b-none" path="type" />
						</div>
					</div>

					<div class="form-group" id="subdivision">
						<form:label cssClass="col-sm-2 control-label" path="subdivision.code">
							<spring:message code="subdivision" />
						</form:label>
						<div class="col-sm-10">
							<form:select path="subdivision.code" style="width: 50%;">
								<form:option value="" />
								<form:options items="${subdivisions}" itemLabel="name" itemValue="code" />
							</form:select>
							<form:errors cssClass="help-block m-b-none" path="subdivision.code" />
						</div>
					</div>

					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="warning" /></label>
						<div class="col-sm-10">
							<p class="form-control-static text-danger">
								<spring:message code="activity.create.warning" />
							</p>
						</div>
					</div>

					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-2">
							<a href="${pageContext.request.contextPath}/activity" class="btn btn-default"><spring:message code="cancel" /></a>
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

<c:set var="journey"><%=ActivityType.JOURNEY.getId()%></c:set>

<script type="text/javascript">
	$(document).ready(function() {
		select_nav("#nav_activity_list");

		$("select[name='subdivision.code']").select2();

		$("input[name='type']").click(function() {

			var _val = $("input[name='type'][value='${journey}']").prop('checked');

			if (_val) {
				$('#subdivision').show();
			} else {
				$('#subdivision').hide();
				$("select[name='subdivision.code']").val('');
			}

		});

		var _val = $("input[name='type'][value='${journey}']").prop('checked');

		if (_val) {
			$('#subdivision').show();
		} else {
			$('#subdivision').hide();
			$("select[name='subdivision.code']").val('');
		}

	});
</script>
