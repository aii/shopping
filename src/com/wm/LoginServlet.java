package com.wm;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

import stringUtil.StringUtil;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Customer customer = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		String customerId = request.getParameter("customerId");
		String customerPwd = request.getParameter("customerPwd");
		String cookieStatus = request.getParameter("cookie");
		HttpSession session = request.getSession();
		/*
		 * [2013/08/04]潜在的bug，不会出错的原因是使用了客户端跳转--整个页面执行完毕后再会执行跳转，因为后面有一样的语句，
		 * 所以在这个地方不会出错。 if(session.getAttribute("user") != null){
		 * response.setHeader("refresh", "0;url=./all/index.jsp"); }
		 */
		if (!StringUtil.isNullOrBlank(customerId)
				&& !StringUtil.isNullOrBlank(customerPwd)
				&& !StringUtil.isNullOrBlank(cookieStatus)) {
			try {
				customer = DAOFactory.getICustomerInstance().findByIdAndPwd(customerId, customerPwd);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (customer != null) {
				this.handleCookie(request, response, customerId, customerPwd,
						cookieStatus);
				response.setHeader("refresh", "0;url=/shopping/EnquireGoodsServlet");
			} else {
				response.setHeader("refresh", "0;url=/shopping/login/login_error.jsp");
			}
		} else {
			/*
			 * [2013/08/04] 更好的用户体验：
			 * 问题：用户突然在地址栏中通过url的形式访问http://localhost:8080/
			 * hr/LoginServlet这个地址，该地址是普通用户在本地看不到的。
			 * 解决方法：加入else判断，即如果用户直接访问http:/
			 * /localhost:8080/hr/LoginServlet这个地址，重定向到login
			 * .jsp页面，该页面中有收集登陆信息的session，根据用户登陆与否决定是否显示用户名+密码。
			 */
			response.sendRedirect("/hr/login/login.jsp");
		}
	}

	public void handleCookie(HttpServletRequest request,
			HttpServletResponse response, String id, String pwd,
			String cookieStatus) {
		HttpSession session = request.getSession();
		session.setAttribute("customer", customer);
		if ("save".equals(cookieStatus)) {
			// 如果选择了保存Cookie选项，则保存Cookie
			Cookie idCookie = new Cookie("id", id);
			Cookie pwdCookie = new Cookie("pwd", pwd);
			// 设置Cookie保存时间为1分钟
			idCookie.setMaxAge(60);
			pwdCookie.setMaxAge(60);
			response.addCookie(idCookie);
			response.addCookie(pwdCookie);
		} else {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					if (cookie.getValue().equalsIgnoreCase(id) || cookie.getValue().equalsIgnoreCase(pwd)) {
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
			}
		}
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
