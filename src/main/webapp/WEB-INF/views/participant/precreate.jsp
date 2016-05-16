<%@page import="java.util.Enumeration"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<section class="vbox">
	<section class="scrollable padder">
		<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
			<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-home"></i> <spring:message code="home" /></a></li>
			<li><a href="${pageContext.request.contextPath}/participant"><spring:message code="participant.list" /></a></li>
			<li class="active"><spring:message code="participant.create" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="participant.create" />
			</h3>
		</div>
		<section class="panel panel-default">
			<header class="panel-heading font-bold">&nbsp;</header>
			<div class="panel-body">
				<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/participant/create" modelAttribute="participant" autocomplete="off">
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="name">
							<spring:message code="participant.code" />
						</form:label>
						<div class="col-sm-10">
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="code" maxlength="100" />
							<form:errors cssClass="help-block m-b-none" path="code" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="name">
							<spring:message code="participant.name" />
						</form:label>
						<div class="col-sm-10">
							<form:input cssClass="form-control" cssErrorClass="form-control parsley-error" path="name" maxlength="100" />
							<form:errors cssClass="help-block m-b-none" path="name" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="position.id">
							<spring:message code="position" />
						</form:label>
						<div class="col-sm-10">
							<form:select path="position.id" style="width: 50%;">
								<form:option value="" />
								<form:options items="${positions}" itemLabel="name" itemValue="id" />
							</form:select>
							<form:errors cssClass="help-block m-b-none" path="grade.id" />
						</div>
					</div>
					<div class="form-group">
						<form:label cssClass="col-sm-2 control-label" path="grade.id">
							<spring:message code="grade" />
						</form:label>
						<div class="col-sm-10">
							<form:select path="grade.id" style="width: 50%;">
								<form:option value="" />
								<form:options items="${grades}" itemLabel="name" itemValue="id" />
							</form:select>
							<form:errors cssClass="help-block m-b-none" path="grade.id" />
						</div>
					</div>
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-2">
							<a href="${pageContext.request.contextPath}/participant" class="btn btn-default"><spring:message code="cancel" /></a>
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
		select_nav("#nav_participant_list");
		$("select[name='grade.id']").select2();
		$("select[name='position.id']").select2();
	});
</script>
