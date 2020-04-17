<!DOCTYPE html>
<html>
<%@ include file="head.jsp" %>

<body>
    <h3>Team MVC Project</h3>

    <h4>${beanData.turnString}</h4>
    <p>X's Score = ${beanData.player1Score}</p>
    <p>O's Score = ${beanData.player2Score}</p>
    <p>Draws = ${beanData.draws}</p>

    <%@ include file="table.jsp" %>

</body>
</html>
