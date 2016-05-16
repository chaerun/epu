
<%@page import="com.kemenkes.epu.common.util.Constant"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/js/datatables/datatables.css"/>" type="text/css" />
</head>
<body>
	<section class="vbox">
		<section class="scrollable padder">
			<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
				<li><a href="<c:url value="/"/>"><i class="fa fa-home"></i> <spring:message code="home" /></a></li>
				<li class="active">Daftar Sub Kegiatan - Cetak Kuitansi Pembayaran Up - Tahun Anggaran ${year}</li>
			</ul>
			<div class="m-b-md">
				<h3 class="m-b-none">Daftar Sub Kegiatan - Cetak Kuitansi Pembayaran Up</h3>
			</div>
			<div class="col-sm-12">
				<jsp:include page="/WEB-INF/views/message.jsp" />
				<section class="panel panel-default">
					<div class="table-responsive">
						<table class="table table-striped b-t b-light">
							<thead>
								<tr>
									<th></th>
									<th>Nama Sub Kegiatan</th>
									<th>Lokasi</th>
									<th>Tanggal Kegiatan</th>
									<th>Total Pengajuan</th>
									<th>Subdivisi</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${meetings}" var="meeting" varStatus="status">
									<tr>
										<td><a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/report/sa/up/${meeting.id}"><i class='fa fa-print'></i>&nbsp;Cetak</a> </td>
										<td>${meeting.name}</td>
										<td>${meeting.location}</td>
										<td><fmt:formatDate value="${meeting.startDate}" pattern="<%= Constant.DATE_FORMAT %>" />&nbsp;-&nbsp;<fmt:formatDate value="${meeting.endDate}" pattern="<%= Constant.DATE_FORMAT %>" /></td>
										<td>Rp.&nbsp;<fmt:formatNumber value="${meeting.releasedAmount}" minFractionDigits="0" /></td>
										<td>${meeting.activity.subdivision.name}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</section>
			</div>
		</section>
	</section>
</body>
<div id="custom-script">
	<script src="<c:url value="/resources/js/datatables/jquery.dataTables.min.js"/>"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			var nav = $("#nav_up");
			nav.addClass("active");
			nav.parent().addClass("active");
			nav.parent().parent().parent().addClass("active");
		});
	</script>
</div>
</html>