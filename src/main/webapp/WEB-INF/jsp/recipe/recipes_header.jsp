<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	function searchRecipe() {
		var txtSearch = document.getElementById("txtSearch");
		var str = txtSearch.value; //.trim();

		if (str != "") {
			window.location = "" + str;
		} else { /* To check if non-String value is inputted */
			txtSearch.value = "";
			txtSearch.focus();
			alert("Null or invalid value inputted");
		}
	}
	
	function addRecipe(){
		window.location = "add";
	}
</script>
</head>
<body>
	<div class="search_class" align="right">
		<table>
			<tr>
				<td ><button id="btnAdd" onclick="addRecipe();">Add a recipe</button></td>
				<td ><input type="text" id="txtSearch" size="15" />
					<button id="btnSearch" onclick="searchRecipe();">Search</button></td>
			</tr>
		</table>
	</div>
</body>
</html>