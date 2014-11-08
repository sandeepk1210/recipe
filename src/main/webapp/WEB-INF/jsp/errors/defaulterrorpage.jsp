<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Error Page</h1>
<p>Something went wrong.. contact support..</p>

<!--
    Failed URL: ${url}
    Exception:  ${ex.message}
        <c:forEach items="${ex.stackTrace}" var="ste">    
        ${ste} 
    </c:forEach>
    -->