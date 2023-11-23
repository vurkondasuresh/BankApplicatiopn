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

@WebServlet("/PasswordVerfication")
public class PasswordVerfication extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//fetch password entered by user
		String userPassword = req.getParameter("password");
		System.out.println("Html file Password = "+userPassword);
		//Access session object
		HttpSession session=req.getSession();
		//retrive password from session object
		String dbPassword= (String) session.getAttribute("userPwd");//downcasting
		System.out.println("Session Object Password = "+dbPassword);
		//retrive user first name from session object
		String firstName=(String) session.getAttribute("fn");
		PrintWriter writer=resp.getWriter();
		if(userPassword.equals(dbPassword))
		{
			//start time interval
			session.setMaxInactiveInterval(10);
			//servlet chaining
			RequestDispatcher dispatcher=req.getRequestDispatcher("Application.html");
			dispatcher.include(req,resp);
			writer.print("<center><h1 style='color:green'>LOGIN SUCCESSFUL!!</h1></center>");
			writer.print("<center><h1 style='color:blue'>WELCOME "+firstName +"</h1></center>");
		}
		else 
		{
			RequestDispatcher dispatcher=req.getRequestDispatcher("Email.html");
			dispatcher.include(req, resp);
			writer.print("<center><h1 style='color:red'>Please enter valid Password!!</h1></center>");
		}
	}
	

}