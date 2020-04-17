<!DOCTYPE html>
<html>
<%@ include file="head.jsp" %>

<body>
    <h3>Team MVC Project</h3>

    <h4>${beanData.turnString}</h4>
    <p>${beanData.boardStates}</p>
    <p>Game Over? = ${beanData.gameOver}</p>

    <%@ include file="table.jsp" %>

</body>
</html>
