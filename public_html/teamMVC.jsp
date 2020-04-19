<!DOCTYPE html>
<html>
<%@ include file="head.jsp" %>

<body>
    <a class="title" href="tic-tac-toe"><h3>Team MVC Project</h3></a>
    <%@ include file="scoreBoard.jsp" %>
    <h4>${beanData.turnString}</h4>

    <%@ include file="gameBoard.jsp" %>

</body>
</html>
