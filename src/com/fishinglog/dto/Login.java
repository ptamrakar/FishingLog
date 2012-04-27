package com.fishinglog.dto;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.fishinglog.dao.DbHelper;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(Login.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String prefix = getServletContext().getRealPath("/");
		PropertyConfigurator.configure(prefix
				+ "WEB-INF/classes/log4j.properties");
		String username = request.getParameter("username");
		PrintWriter out = response.getWriter();
		String password = request.getParameter("password");
		DbHelper db = new DbHelper();

		if (db.login(username, password)) {
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 "
					+ "Transitional//EN\">\n" + "<HTML>\n"
					+ "<HEAD><TITLE>Success</TITLE></HEAD>\n" + "<BODY>\n"
					+ "<H1>User Found</H1>\n" + "</BODY></HTML>");
		} else {
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 "
					+ "Transitional//EN\">\n" + "<HTML>\n"
					+ "<HEAD><TITLE>Fail</TITLE></HEAD>\n" + "<BODY>\n"
					+ "<H1>Check your username/password</H1>\n" + "</BODY></HTML>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
