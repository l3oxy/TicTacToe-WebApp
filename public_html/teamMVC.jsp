<!DOCTYPE html>
<html>
<%@ include file="head.jsp" %>

<body class="container">
    <div class="title_and_subtitle">
        <h1 class="title"><a href="tic-tac-toe">Tic-Tac-Toe</a></h1>
        <a class="subtitle" href="tic-tac-toe?details">Details</a>
    </div>
    <%@ include file="scoreBoard.jsp" %>
    <h4>${beanData.turnString}</h4>

    <%@ include file="gameBoard.jsp" %>
</body>
</html>
