<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<header class="bg-dark dk header navbar navbar-fixed-top-xs">
	<div class="navbar-header aside-md">
		<a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen,open" data-target="#nav,html"> <i class="fa fa-bars"> </i>
		</a> <a href="#" class="navbar-brand" data-toggle="fullscreen"> <img src="<c:url value="/resources/images/logo.png"/>" class="m-r-sm"> 
		</a> <a class="btn btn-link visible-xs" data-toggle="dropdown" data-target=".nav-user"> <i class="fa fa-cog"> </i>
		</a>
	</div>
	<ul class="nav navbar-nav hidden-xs">
		<li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle dker" href="#"> <i class="fa fa-building-o"></i> <span class="font-bold">

<c:choose>
	<c:when test="${sessionScope.user.role == 'ROLE_ADMIN'}">
		Admin
	</c:when>
	<c:when test="${sessionScope.user.role == 'ROLE_SA'}">
		Super Admin
	</c:when>
	<c:when test="${sessionScope.user.role == 'ROLE_USER'}">
		Anggota
	</c:when>
	<c:otherwise></c:otherwise>
</c:choose>		

<c:if test="${sessionScope.user.subdivision.name != null}">
&nbsp;-&nbsp; ${sessionScope.user.subdivision.name}
</c:if>
		</span></a></li>
	</ul>
	<ul class="nav navbar-nav navbar-right m-n hidden-xs nav-user">
		<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> <span class="thumb-sm avatar pull-left"> <img src="<c:url value="/resources/images/avatar_default.jpg"/>">
			</span> ${sessionScope.user.username}<b class="caret"> </b>
		</a>
			<ul class="dropdown-menu animated fadeInRight">
				<span class="arrow top"> </span>
				<li><a href="<c:url value="/user/password/prechange"/>"> <spring:message code="password.change" />
				</a></li>
				<li class="divider"></li>
				<li><a href="<c:url value="/j_spring_security_logout"/>"> <spring:message code="logout" />
				</a></li>
			</ul></li>
	</ul>
</header>