<%@page import="com.kemenkes.epu.entity.ActivityType"%>
<%@page import="java.util.Enumeration"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
				<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/activity/create" modelAttribute="activity" autocomplete="off">

					<form:hidden path="code" />
					<form:hidden path="name" />
					<form:hidden path="year" />
					<form:hidden path="amount" />
					<form:hidden path="source" />
					<form:hidden path="type" />
					<form:hidden path="subdivision.code" />

					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="activity.code" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${activity.code}</p>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"> <spring:message code="activity.name" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${activity.name}</p>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"> <spring:message code="activity.year" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${activity.year}</p>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"> <spring:message code="activity.amount" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">
								<fmt:formatNumber value="${activity.amount}" currencySymbol="Rp." pattern="¤ #,##0.00;¤ -#,##0.00" />
							</p>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="activity.source" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${activity.source.description}</p>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label font-bold"><spring:message code="activity.type" /></label>
						<div class="col-sm-10">
							<p class="form-control-static">${activity.type.description}</p>
						</div>
					</div>
					<c:if test="${not empty activity.subdivision} ">
						<div class="form-group">
							<label class="col-sm-2 control-label font-bold"><spring:message code="subdivision" /></label>
							<div class="col-sm-10">
								<p class="form-control-static">${activity.subdivision.code}&nbsp;-&nbsp;${activity.subdivision.name}</p>
							</div>
						</div>
					</c:if>

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

