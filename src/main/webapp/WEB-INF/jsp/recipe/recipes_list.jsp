<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<!-- This JSP page serves 2 goals:
a) Link to add new recipes
b) Display the list of recipes received from database -->
<html>
<head>
<s:url value="/recipe/add" var="recipe_add" />
</head>
<body>
	<table border="0" cellspacing="5" width="100%">
		<tr>
			<!-- Link to add the recipes -->
			<td align="right"><a href="${recipe_add}">Add a recipe</a></td>
		</tr>
		<tr></tr>
		<tr></tr>
		<tr>
			<!-- Display the list of recipes -->
			<td><b>List of Recipes</b></td>
		</tr>

		<tr>
			<td><c:choose>
					<c:when test="${fn:length(recipesList) eq 0 }">
						<!-- If no recipe exists in database, display message to user that "No recipes exists" -->
						No recipes exists in database.
						Please create a new recipe.
					</c:when>

					<c:otherwise>
						<!-- Display the list of recipes retrieved from database -->
						<ol>
							<c:forEach var="recipe" items="${recipesList}">
								<s:url value="/recipe/{recipeId}" var="recipe_url">
									<s:param name="recipeId" value="${recipe.id}" />
								</s:url>

								<li><a href="${recipe_url}"> <c:out
											value="${recipe.name}" />
								</a> <br /></li>
							</c:forEach>
						</ol>
					</c:otherwise>
				</c:choose></td>
		</tr>
	</table>
</body>
</html>