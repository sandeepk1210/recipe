<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<div>
	<s:url value="/index" var="recipe_home" />
	<s:url value="/config/home" var="config_home" />
	<span class="header"> <a href="${recipe_home}">Home</a> | <a
		href="${config_home}">Configure</a> |
		Tools
	</span>
</div>