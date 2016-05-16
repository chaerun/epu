<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<section class="vbox">
	<section class="scrollable padder">
		<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
			<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-home"></i> <spring:message code="home" /></a></li>
			<li class="active"><spring:message code="official.list" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="official.list" />
			</h3>
		</div>
		<jsp:include page="/WEB-INF/views/message.jsp" />
		<section class="panel panel-default">
			<header class="panel-heading">
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/official/precreate"><i class="fa fa-plus-square"></i> <spring:message code="official.create" /> </a>
			</header>
		</section>
		<section class="panel panel-default">
			<header class="panel-heading">
				<spring:message code="official.list" />
			</header>
			<div class="panel-body">
				<display:table id="official" class="table table-striped" name="page" sort="external" partialList="true" size="${page.fullListSize}" pagesize="${page.objectsPerPage}" requestURI="" export="true">
					<display:column titleKey="action">
						<a href="${pageContext.request.contextPath}/official/predelete/${official.year}/" class="btn btn-sm btn-danger"><i class="fa fa-minus-square"></i> <spring:message code="delete" /></a>
					</display:column>
					<display:column property="year" titleKey="official.year" sortable="true" />
					<display:column property="official.name" sortProperty="official.official.name" titleKey="official.official" sortable="true" />
					<display:column property="treasurer.name" sortProperty="treasurer.name" titleKey="official.treasurer" sortable="true" />
				</display:table>
			</div>
		</section>
	</section>
</section>
<script type="text/javascript">
	$(document).ready(function() {
		select_nav("#nav_official_list");
	});
</script>
