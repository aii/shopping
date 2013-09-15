package com.wm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cookie[] cs = request.getCookies();
		if (cs != null) {
			for (int i = 0; i < cs.length; i++) {
				if ("userid".equalsIgnoreCase(cs[i].getName())
						|| "password".equalsIgnoreCase(cs[i].getName())
						|| "isAdmin".equalsIgnoreCase(cs[i].getName())) {
					{
						cs[i].setMaxAge(0);
						response.addCookie(cs[i]);
					}
					cs[i].setMaxAge(0);
				}
				if ("password".equalsIgnoreCase(cs[i].getName())) {
					cs[i].setMaxAge(0);
				}
				if ("isAdmin".equalsIgnoreCase(cs[i].getName())) {
					cs[i].setMaxAge(0);
				}
			}
		}
		request.getSession().invalidate();
		response.sendRedirect("/hr/login/login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
