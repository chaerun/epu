<%@page import="com.kemenkes.epu.common.util.Constant"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<section class="vbox">
	<section class="scrollable padder">
		<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
			<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-home"></i> <spring:message code="home" /></a></li>
		</ul>
		<section class="panel panel-default">
			<header class="panel-heading">Pesan Kesalahan</header>
			<div class="panel-body">
				<jsp:include page="/WEB-INF/views/message.jsp" />
				<a href="${url}" class="btn btn-danger"><i class="fa fa-caret-left"></i> Kembali</a>
			</div>
		</section>
	</section>
</section>
