<!DOCTYPE html>
<html>
<%@ include file="head.jsp" %>

<body class="container">
    <div class="title_and_subtitle">
        <h1 class="title"><a href="tic-tac-toe?details">Tic-Tac-Toe Details</a></h1>
        <a class="subtitle" href="tic-tac-toe">Back to Game</a>
    </div>

    <h4>Properties</h4>
    <%@ include file="beanDetailsTable.jsp" %>

    <h4>Diagram</h4>
    <img src="images/mvcDiagram.jpg" alt="MVC Diagram">

    <p><strong>When the user first accesses the application:</strong></p>
    <ol>
        <li>
            The user inputs the url "<code>localhost:8080/java112/tic-tac-toe</code>"
            as an HTTP Request to the Tomcat server, accessing the
            TeamMVCServlet controller.
        </li>
        <li>
            On first access, the controller's <code>init()</code> method
            creates a JavaBean object and sets initial values for the board's
            height, width, and the icons for player 1, player 2, and
            unused/empty.
            The <code>doGet()</code> method executes also, which resets the board,
            and sets the turn string to player 1.
        </li>
        <li>
            The controller sends the JavaBean model to the
            <code>teamMVC.jsp</code> view, which includes the sub-views: head,
            scoreBoard, and gameBoard.
            <code>scoreBoard.jsp</code> uses expression language (EL) to
            display data about the icons and scores.
            <code>gameBoard.jsp</code> uses EL to display the board table, and
            the icons in their appropriate positions.
        </li>
        <li>
            The sub-views are congregated onto <code>teamMVC.jsp</code> through
            includes.
            The string displaying turn information is also called with EL.
        </li>
        <li>
            HTTP Response displays the formatted HTML from
            <code>teamMVC.jsp</code> to the browser.
        </li>
    </ol>
    <p><strong>If a cell on the board has been clicked:</strong></p>
    <ol>
        <li>
            The player clicks a spot and sends another HTTP request along with
            a parameter called "<code>cell</code>", with a value indicating
            the chosen spot.
        </li>
        <li>
            Controller "<code>TeamMVCServlet</code>" uses the "<code>cell</code>"
            parameter to update the board.
            The controller sets and gets the variables in the JavaBean model.
            The controller determines if conditions have been met for a victory,
            a draw, or neither.
            The controller manages which player's turn it is, and all variables
            derived from it.
        </li>
        <li>
            The controller sends the JavaBean data to the view in the same way
            as before.
        </li>
        <li>
            The view is congregated at <code>teamMVC.jsp</code>.
        </li>
        <li>
            HTTP Response displays the code from <code>teamMVC.jsp</code> to
            the browser.
        </li>
    </ol>
    <p><strong>
        The rest of the model pertains to <code>details.jsp</code> (this page),
        which illustrates the data and architecture of this application.
    </strong></p>
</body>
</html>
