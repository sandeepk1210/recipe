<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<html>
<head>
</head>
<body>
	<h3>Menu List</h3>

	<ol>
		<c:forEach var="menu" items="${menuList}">
			<s:url value="/config/${fn:toLowerCase(menu)}" var="config_menu" />

			<li><a href="${config_menu}"><c:out value="${menu}" /></a></li>
		</c:forEach>
	</ol>
</body>
</html>