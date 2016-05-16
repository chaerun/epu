<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<section class="vbox">
	<section class="scrollable padder">
		<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
			<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-home"></i> <spring:message code="home" /></a></li>
			<li class="active"><spring:message code="user.list" /></li>
		</ul>
		<div class="m-b-md">
			<h3 class="m-b-none">
				<spring:message code="user.list" />
			</h3>
		</div>
		<jsp:include page="/WEB-INF/views/message.jsp" />
		<section class="panel panel-default">
			<header class="panel-heading">
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/user/precreate"><i class="fa fa-plus-square"></i> <spring:message code="user.create" /> </a>
			</header>
		</section>
		<section class="panel panel-default">
			<header class="panel-heading">
				<spring:message code="user.list" />
			</header>
			<div class="panel-body">
				<display:table id="user" class="table table-striped" name="page" sort="external" partialList="true" size="${page.fullListSize}" pagesize="${page.objectsPerPage}" requestURI="" export="true">
					<display:column titleKey="action">
						<c:if test="${sessionScope.user.role eq 'ROLE_ADMIN'}"> 
							<c:if test="${user.role eq 'ROLE_USER'}">
								<a href="${pageContext.request.contextPath}/user/preedit/${user.username}/" class="btn btn-sm btn-info"><i class="fa fa-edit"></i> <spring:message code="edit" /> </a>
								<a href="${pageContext.request.contextPath}/user/predelete/${user.username}/" class="btn btn-sm btn-danger"><i class="fa fa-minus-square"></i> <spring:message code="delete" /></a>
							</c:if>
						</c:if>
						<c:if test="${sessionScope.user.role eq 'ROLE_SA'}"> 
							<a href="${pageContext.request.contextPath}/user/preedit/${user.username}/" class="btn btn-sm btn-info"><i class="fa fa-edit"></i> <spring:message code="edit" /> </a>
							<a href="${pageContext.request.contextPath}/user/predelete/${user.username}/" class="btn btn-sm btn-danger"><i class="fa fa-minus-square"></i> <spring:message code="delete" /></a>
						</c:if>
					</display:column>
					<display:column property="username" titleKey="user.username" />
					<display:column property="role.description" titleKey="user.role" />
					<display:column sortProperty="subdivision.name" titleKey="subdivision">${user.subdivision.code} - ${user.subdivision.name}</display:column>
				</display:table>
			</div>
		</section>
	</section>
</section>
<script type="text/javascript">
	$(document).ready(function() {
		select_nav("#nav_user_list");
	});
</script>
