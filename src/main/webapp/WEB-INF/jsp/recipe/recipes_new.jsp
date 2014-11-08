<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<s:url value="/recipe/add" var="recipe_new" />
	<h2>Add New Recipe</h2>
	<sf:form modelAttribute="recipe" method="POST" action="${recipe_new}">
		<fieldset>
			<table>
				<tr>
					<th><sf:label path="name">Name this recipe..</sf:label></th>
				</tr>
				<tr>
					<td><sf:input path="name" size="30" /> <br /> <sf:errors
							path="name" cssClass="error"></sf:errors><br /></td>
				</tr>
				<tr>
					<th><sf:label path="ingredients">Ingredients required?</sf:label></th>
				</tr>
				<tr>
					<td><sf:textarea path="ingredients" cols="30" rows="6" /> <small>Tip
							- Add ingredient as <b>Type - Quantity</b>
					</small> <sf:errors path="ingredients" cssClass="error"></sf:errors><br /></td>
				</tr>
				<tr>
					<th><sf:label path="method">Preparation Steps?</sf:label></th>
				</tr>
				<tr>
					<td><sf:textarea path="method" cols="30" rows="6" /> <br />
						<sf:errors path="method" cssClass="error"></sf:errors><br /></td>
				</tr>
				<tr>
					<th><sf:label path="tips">Any tips or tricks you tried?</sf:label></th>
				</tr>
				<tr>
					<td><sf:textarea path="tips" cols="30" rows="4" /> <br /> <sf:errors
							path="tips" cssClass="error"></sf:errors><br /></td>
				</tr>
				<tr>
					<th><sf:label path="writeup">Any specific notes?</sf:label></th>
				</tr>
				<tr>
					<td><sf:textarea path="writeup" cols="30" rows="4" /> <br />
						<sf:errors path="writeup" cssClass="error"></sf:errors><br /></td>
				</tr>
				<tr>
					<th><sf:label path="person">Who like this dish most?</sf:label></th>
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
							<sf:option value="" label="--Please Select--" />
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
					<td colspan="2" align="left"><input type="submit"
						value="Submit" /></td>
				</tr>
			</table>
		</fieldset>
	</sf:form>

</body>
</html>