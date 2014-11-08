<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<style type="text/css">
.error {
	color: red
}

th {
	text-align: left
}
</style>
</head>
<body>
	<s:url value="/recipe/modify" var="recipe_modify">
	</s:url>
	<h2>
		Modify
		<c:out value="${fn:toUpperCase(recipe.name)}" />
	</h2>
	<sf:form modelAttribute="recipe" method="POST"
		action="${recipe_modify}">
		<sf:hidden path="id" />
		<fieldset>
			<table>
				<tr>
					<th><sf:label path="name">Name of Recipe</sf:label></th>
				</tr>
				<tr>
					<td><sf:input path="name" size="30" /> <br /> <sf:errors
							path="name" cssClass="error"></sf:errors><br /></td>
				</tr>
				<tr>
					<th><sf:label path="ingredients">Ingredients</sf:label></th>
				</tr>
				<tr>
					<td><sf:textarea path="ingredients" cols="30" rows="6" /> <small>Tip
							- Add ingredient as <b>Type - Quantity</b>
					</small> <sf:errors path="ingredients" cssClass="error"></sf:errors><br /></td>
				</tr>
				<tr>
					<th><sf:label path="method">Preparation Steps</sf:label></th>
				</tr>
				<tr>
					<td><sf:textarea path="method" cols="30" rows="6" /> <br />
						<sf:errors path="method" cssClass="error"></sf:errors><br /></td>
				</tr>
				<tr>
					<th><sf:label path="tips">Tips and tricks</sf:label></th>
				</tr>
				<tr>
					<td><sf:textarea path="tips" cols="30" rows="4" /> <br /> <sf:errors
							path="tips" cssClass="error"></sf:errors><br /></td>
				</tr>
				<tr>
					<th><sf:label path="writeup">Notes</sf:label></th>
				</tr>
				<tr>
					<td><sf:textarea path="writeup" cols="30" rows="4" /> <br />
						<sf:errors path="writeup" cssClass="error"></sf:errors><br /></td>
				</tr>
				<tr>
					<th><sf:label path="person">Liked by Person</sf:label></th>
				</tr>
				<tr>
					<td><sf:select path="person.id">
							<sf:options items="${personList}" itemValue="id" itemLabel="name" />
						</sf:select> <br /> <sf:errors path="person" cssClass="error"></sf:errors><br /></td>
				</tr>

				<tr>
					<th><sf:label path="cuisine">Cuisine</sf:label></th>
				</tr>
				<tr>
					<td><sf:select path="cuisine.id">
							<sf:options items="${cuisineList}" itemValue="id"
								itemLabel="name" />
						</sf:select> <br /> <sf:errors path="cuisine.id" cssClass="error"></sf:errors><br /></td>
				</tr>
				<tr>
					<th><sf:label path="source">Source</sf:label></th>
				</tr>
				<tr>
					<td><sf:input path="source" /> <br /> <sf:errors
							path="source" cssClass="error"></sf:errors><br /></td>
				</tr>
				<tr>
					<th></th>
					<td align="left"><input type="submit" value="Update" /></td>
				</tr>
			</table>
		</fieldset>
	</sf:form>

</body>
</html>