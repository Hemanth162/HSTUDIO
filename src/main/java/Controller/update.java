package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import Model.Registration;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name="update",urlPatterns={"/update"})

public class update extends HttpServlet{
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException
	{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out =response.getWriter();
		
		HttpSession session = request.getSession();
		
		try 
		{
			if(session.getAttribute("uname")!=null && request.getParameter("submit")!=null)
			{
				String name = request.getParameter("name");
				String pno = request.getParameter("pno");
				String email = request.getParameter("email");
				Registration u = new Registration(session);
				String status =u.update(name, pno, email);
				if(status.equals("sucess"))
				{
					request.setAttribute("status", "Profile successfully Updated");
					RequestDispatcher rd1 = request.getRequestDispatcher("index.jsp");
					rd1.forward(request, response);
				}
				else
				{
					request.setAttribute("status", "Updation failure");
					RequestDispatcher rd1 = request.getRequestDispatcher("index.jsp");
					rd1.forward(request, response);
				}
				
			}
		}
		catch(Exception e)
		{
			
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

//Note:
//Update method code present in Registraion.java.
//
//It will navigate from Registraion.java to Servlet called update.
//
//From update servlet to  it will be navigate to index.jsp.

