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
	<sf:form method="POST" modelAttribute="ingredient">
		<table>
			<tr>
				<td><sf:label path="type">Type</sf:label></td>
				<td><sf:input path="type" size="20" /> <sf:errors path="type"
						cssClass="error" /></td>
			</tr>
			<tr>
				<td><sf:label path="type">Quantity</sf:label></td>
				<td><sf:input path="quantity" size="20" /> <sf:errors
						path="quantity" cssClass="error" /></td>
			</tr>
			<tr align="right">
				<td colspan="2"><input type="submit" value="Add" /></td>
			</tr>
		</table>
	</sf:form>
	<h3>Ingredients</h3>
	<table>
		<c:forEach items="${ingredientList}" var="ingredient">
			<tr>
				<td><input type="text" value=<c:out value="${ingredient.text}"/> ></td>
			</tr>
			<tr>
				<%-- <td><sf:input path="ingredient.quantity" /></td> --%>
			</tr>
			<tr>
			<td><%-- <sf:form method="DELETE">
					<input type="hidden" value="${id}" id="id" name="id">
					<input type="submit" value="Delete"
						onClick="return confirm('Are you sure you want to delete?')">
				</sf:form> --%></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>