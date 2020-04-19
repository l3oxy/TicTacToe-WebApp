<!DOCTYPE html>
<html>
<%@ include file="head.jsp" %>

<body>
    <h3 style="text-align:center">Team MVC Project</h3>
    <p style="text-align:center">
        || X's Score = ${beanData.player1Score} ||
        Draws = ${beanData.draws} ||
        O's Score = ${beanData.player2Score} ||
    </p>
    <h4 style="text-align:center">${beanData.turnString}</h4>

    <%@ include file="table.jsp" %>

</body>
</html>
