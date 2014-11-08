<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
	<table border="1" cellpadding="5" align="center">
		<tr align="left">
			<td width="20%"><b>Recipe Collection</b></td>
			<td align="right" width="80%"><tiles:insertAttribute
					name="header" /></td>
		</tr>
		<tr align="left">
			<td width="20%"><tiles:insertAttribute name="list" /></td>
			<td width="80%"><tiles:insertAttribute
					name="content" /></td>
		</tr>
	</table>
</body>
</html>
