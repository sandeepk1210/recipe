<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
"http://www.w3.org/TR/html4/loose.dtd">

<head>
<style>
th {
	width: 30%;
	text-align: left;
}
</style>
</head>
<body>
	<s:url value="/recipe/delete/{recipeId}" var="recipe_delete">
		<s:param name="recipeId" value="${recipesList[index].id}" />
	</s:url>

	<s:url value="/recipe/modify/{recipeId}" var="recipe_modify">
		<s:param name="recipeId" value="${recipesList[index].id}" />
	</s:url>

	<s:url value="/recipe/modify/{recipeId}" var="recipe_modify">
		<s:param name="recipeId" value="${recipesList[index].id}" />
	</s:url>

	<table width="100%">
		<tr>
			<td align="right"><a href="${recipe_delete}">Delete</a> | <a
				href="${recipe_modify}">Modify</a>
			<td>
		</tr>
		<tr>
			<td>
				<h3>
					Recipe -
					<c:out value="${recipesList[index].name}" />
				</h3>

				<table style="width: 100%" border="1">
					<tr>
						<th>Ingredients</th>
						<td><span style="white-space: pre"> <c:out
									value="${recipesList[index].ingredients}" />
						</span></td>
					</tr>
					<tr>
						<th>Preparation Steps</th>
						<td><c:out value="${recipesList[index].method}" /></td>
					</tr>
					<tr>
						<th>Tips used while making recipe?</th>
						<td><c:out value="${recipesList[index].tips}" /></td>
					</tr>
					<tr>
						<th>Personal Notes</th>
						<td><c:out value="${recipesList[index].writeup}" /></td>
					</tr>
					<tr>
						<th>Favourite of</th>
						<td><c:out value="${recipesList[index].person.name}" /></td>
					</tr>

					<tr>
						<th>Cuisine</th>
						<td><c:out value="${recipesList[index].cuisine.name}" /></td>
					</tr>

					<tr>
						<th>Source</th>
						<td><c:out value="${recipesList[index].source}" /></td>
					</tr>


				</table>
			</td>
		</tr>
	</table>
</body>