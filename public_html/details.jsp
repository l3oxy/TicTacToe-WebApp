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
            The user inputs the url "localhost:8080/java112/tic-tac-toe" as an
            HTTP Request to the Tomcat server, accessing the TeamMVCServlet
            controller.
        </li>
        <li>
            On first access, the init() method in the controller creates a
            JavaBean object and sets initial values for board height, width,
            player 1 icon, player 2 icon, and empty icon. The doGet() also
            executes, which gets the empty board and sets the turn string to
            player 1.
        </li>
        <li>
            The controller sends the JavaBean model to the teamMVC.jsp view,
            which includes the sub-views: head, scoreBoard, and gameBoard.
            scoreBoard.jsp uses expression language (EL) to display data about
            the icons and scores. gameBoard.jsp uses EL to display the table of
            the board and the icons in their appropriate positions
        </li>
        <li>
            The sub-views are congregated onto teamMVC.jsp through includes.
            The string displaying turn info is also called with EL.
        </li>
        <li>
            HTTP Response displays the formatted HTML from teamMVC.jsp to the
            browser.
        </li>
    </ol>
    <p><strong>If a cell on the board has been clicked:</strong></p>
    <ol>
        <li>
            The player clicks a spot and sends another HTTP request along with
            a parameter called "cell", with a value indicating the chosen spot.
        </li>
        <li>
            The "cell" parameter is used by TeamMVCServlet to change the board.
            The controller sets and gets the variables in the JavaBean model.
            The controller goes through the process of checking if a win
            condition has been met as well as changing the player turn and all
            variables pertaining to it.
        </li>
        <li>
            The controller sends the JavaBean data to the view in the same way
            as before.
        </li>
        <li>
            The view is congregated at teamMVC.jsp.
        </li>
        <li>
            HTTP Response displays the code from teamMVC.jsp to the browser.
        </li>
    </ol>
    <p><strong>
        The rest of the model pertains to details.jsp (this page), which
        details the data and architecture of this application.
    </strong></p>
</body>
</html>
