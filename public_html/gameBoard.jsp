<%@ page import="java112.project3.JavaBean" %>
<table class="game_board">
    <tbody>
		<%	int cell = 0;
			JavaBean beanData = (JavaBean) request.getAttribute("beanData");
			for (int row = 0; row < beanData.getBoardHeight(); ++row) {
			out.print("<tr>");
			for (int col = 0; col < beanData.getBoardWidth(); ++col) {
				out.print("<td>" +
						"<a href=\"tic-tac-toe?cell=" + cell + "\">" +
						"<div class=\"table-cell\">" +
						beanData.getBoardCell(cell) +
						"</div>" +
						"</a>" +
						"</td>");
				++cell;
			}
			out.print("</tr>");
		}%>
    </tbody>
</table>
