<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Error Page</h1>
<p>
	<c:forEach items="${messages}" var="msg">
		<c:out value="${msg}" />
		<br />
	</c:forEach>
</p>

<!--
    Failed URL: ${url}
    Exception:  ${ex.message}
        <c:forEach items="${ex.stackTrace}" var="ste">    
        ${ste} 
    </c:forEach>
    -->