<!DOCTYPE html>
<html>
<head>
    <meta name="generator" content="HTML Tidy, see www.w3.org">
    <link href="style.css" rel="stylesheet" type="text/css" />
    <title>Team MVC Project</title>
</head>

<body>
<h3>Team MVC Project</h3>

<h4>${beanData.turnString}</h4>
<p>${beanData.boardStates}</p>
<p>${beanData.gameOver}</p>
<table>
    <tbody>
        <tr>
            <td><a href="tic-tac-toe?cell=0"><div class="table-cell">${beanData.boardStates.get(0)}</div></a></td>
            <td><a href="tic-tac-toe?cell=1"><div class="table-cell">${beanData.boardStates.get(1)}</div></a></td>
            <td><a href="tic-tac-toe?cell=2"><div class="table-cell">${beanData.boardStates.get(2)}</div></a></td>
        </tr>
        <tr>
            <td><a href="tic-tac-toe?cell=3"><div class="table-cell">${beanData.boardStates.get(3)}</div></a></td>
            <td><a href="tic-tac-toe?cell=4"><div class="table-cell">${beanData.boardStates.get(4)}</div></a></td>
            <td><a href="tic-tac-toe?cell=5"><div class="table-cell">${beanData.boardStates.get(5)}</div></a></td>
        </tr>
        <tr>
            <td><a href="tic-tac-toe?cell=6"><div class="table-cell">${beanData.boardStates.get(6)}</div></a></td>
            <td><a href="tic-tac-toe?cell=7"><div class="table-cell">${beanData.boardStates.get(7)}</div></a></td>
            <td><a href="tic-tac-toe?cell=8"><div class="table-cell">${beanData.boardStates.get(8)}</div></a></td>
        </tr>
    </tbody>
</table>

</body>
</html>
