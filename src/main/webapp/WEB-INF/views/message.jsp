<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:if test="${not empty message}">
	<div class="alert alert-${message.type.description}">
		<button class="close" data-dismiss="alert" type="button">×</button>
		<span class="font-bold"><spring:message code="${message.text}" text="${message.text}" arguments="" /></span>
	</div>
</c:if>

<c:if test="${not empty messages}">
	<c:forEach var="message" items="${messages}">
		<div class="alert alert-${message.type.description}">
			<button class="close" data-dismiss="alert" type="button">×</button>
			<span class="font-bold"><spring:message code="${message.text}" text="${message.text}" arguments="" /></span>
		</div>
	</c:forEach>
</c:if>
