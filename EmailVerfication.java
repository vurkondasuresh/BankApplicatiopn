package ServletChaning;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

@WebServlet("/EmailVerfication")
public class EmailVerfication  extends HttpServlet
{
    	@Override
    	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//fetch email from html file.
    	String email=req.getParameter("email");
    	String url="jdbc:mysql://localhost:3306?user=root&password=12345";
    	String query ="select * from mysqlsuresh.userinformation where email=?";
    	try 
    	{
    		Class.forName("com.mysql.jdbc.Driver");
    		Connection connection =  (Connection) DriverManager.getConnection(url);
    		PreparedStatement ps=(PreparedStatement) connection.prepareStatement(query);
    		ps.setString(1, email);
    		ResultSet rs = ps.executeQuery();
    		PrintWriter writer = resp.getWriter();
    		if (rs.next())
    		{
				 /* Email is valid
				 *  Retrive data from database.
				 */
				 String emailid =rs.getString("email");
				 String mobile = rs.getString("mobile");
				 String password = rs.getString("password");
				 String firstname = rs.getString("firstName");
				 String lastname = rs.getString("lastName");
				 System.out.println("data retrived from database , mobile = "+mobile); 
				 //access session object
				 HttpSession session =req.getSession();
				 System.out.println("Session Object Accessed...."); 
				 ///store data in session object
				 session.setAttribute("userMail", emailid);
				 session.setAttribute("userMob", mobile);
				 session.setAttribute("userPwd", password);
				 session.setAttribute("fn", firstname);
				 session.setAttribute("ln", lastname);
				 System.out.println("data stored in session object"); 
				 //dispaly password.html
				 RequestDispatcher dispatcher = req.getRequestDispatcher("Password.html");
				 dispatcher.include(req, resp);
				 writer.println("<center><h1 style='color:green;'>Email Verified </h1></center>");
			 }
			 else 
			 {
				 /*email is not valid
				  *display email.html
				  */
				 RequestDispatcher dispatcher = req.getRequestDispatcher("Email.html");
				 dispatcher.include(req, resp);
				 writer.println("<center><h1 style='color:red;'>Enter Valid Datails</h1></center>");
			 }
			     connection.close();
		} 
    	
    	catch (Exception e) 
    	{
			e.printStackTrace();
		}
    	
    }   
}
