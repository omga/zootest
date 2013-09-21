package com.test.zoo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Authentification
 */
@WebServlet("/Authentification")
public class Authentification extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	Statement stmt;
	ResultSet result;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authentification() {
        super(); 
        
    }
    @Override
    public void destroy() {
    	try {
			con.close();
			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login=request.getParameter("login");
		String pass=request.getParameter("password");
		String redirectURL="FailAuth.html";
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");			
    		con = DriverManager.getConnection("jdbc:derby://localhost:1527/ZooDB");
    		String sqlQuery = "SELECT * from Users";        	
    		stmt = con.createStatement();        	
    		result = stmt.executeQuery(sqlQuery);
			while(result.next()){
				if(login.equalsIgnoreCase(result.getString("login"))&&pass.equalsIgnoreCase(result.getString("password"))){
					System.out.println("new user logged in:"+ result.getString("login"));
					HttpSession session=request.getSession(true);
					session.setAttribute("username", login);
					redirectURL="zozo.jsp";
				}	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(redirectURL);
	}

}
