package ServletChaning;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Logout")
public class Logout extends HttpServlet
{
   @Override
     	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   	//HttpSession object
	   	HttpSession session = req.getSession();
	   	//retrieve the first name and lastname
	    String first = (String) session.getAttribute("fn");
	   	String last =  (String) session.getAttribute("ln");
	   	PrintWriter writer = resp.getWriter();
	   	
	   	
	   	if(first!=null)
	   	{
	   		RequestDispatcher dispatcher = req.getRequestDispatcher("Email.html");
	   		dispatcher.include(req, resp);
		   writer.println("<h1>Thank You "+ first+" "+last+"for Visting Application.....</h1>");
		   writer.println("<h1>Logout Sucessfull!!!</h1>");
	   	}
	   	else
	   	{
		   writer.println("<h1 style='color:red;'>session time out</h1>");
		   //close the session
		   session.invalidate();
		   System.out.println("Session Closes");
	   	}
  }	
}
