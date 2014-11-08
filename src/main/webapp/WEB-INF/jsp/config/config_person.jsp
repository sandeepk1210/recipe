<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<style type="text/css">
.error {
	color: RED;
}
</style>
</head>
<body>
	<sf:form method="POST" modelAttribute="person">
		<sf:input path="name" size="20" />
		<input type="submit" value="Add" />
		<br />
		<sf:errors path="name" cssClass="error" />
	</sf:form>

	<h3>Person</h3>
	<table border="0">
		<thead>
			<tr>
				<td><b>Person Name</b></td>
			</tr>
		</thead>
		<c:forEach items="${personList}" var="person">
			<sf:form method="DELETE">
				<tr>
					<td><c:out value="${person.name}" /></td>
					<td><input type="hidden" value="${person.id}" id="id"
						name="id"> <input type="submit" value="Delete"
						onClick="return confirm('Are you sure you want to delete?')">
					</td>
				</tr>
			</sf:form>
		</c:forEach>
	</table>
</body>
</html>