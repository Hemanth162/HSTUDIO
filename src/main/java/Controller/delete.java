package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import Model.Registration;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name="delete",urlPatterns= {"/delete"})

public class delete extends HttpServlet{
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException
	{
		response.setContentType("text/html;charset=UtF-8");
		PrintWriter out =response.getWriter();
		HttpSession session = request.getSession();
		try {
			int id = Integer.parseInt(request.getParameter("userid"));
			Registration reg =new Registration(session);
			String status = reg.delete(id);
			if(status.equals("success"))
			{
				request.setAttribute("status","Successfully Deleted");
				request.getRequestDispatcher("DeleteUser.jsp").forward(request, response);
			}
			if(status.equals("failure"));
			{
				request.setAttribute("status", "Deletion failure");
				response.sendRedirect("DeleteUser.jsp");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException
	{
		processRequest(request,response);
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException
	{
		processRequest(request,response);
	}
	public String getServletInfo()
	{
		return "Short description";
	}//</editor-fold>
}


//Next step it will invoke the method called delete in Registration.java
//Note: use delete method code in Registration.java (in this document only).
//Last step: it will be navigate from Registration.java  to servlet called delete.java
//From servlet delete.java  control will reach it to DeleteUser.jsp
