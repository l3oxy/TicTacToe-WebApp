<!DOCTYPE html>
<html>
<%@ include file="head.jsp" %>

<body>
    <h3>Team MVC Project</h3>

    <h4>${beanData.turnString}</h4>
    <p>${beanData.boardStates}</p>
    <p>Game Over? = ${beanData.gameOver}</p>
    <p>X's Score = ${beanData.player1Score}</p>
    <p>O's Score = ${beanData.player2Score}</p>

    <%@ include file="table.jsp" %>

</body>
</html>
