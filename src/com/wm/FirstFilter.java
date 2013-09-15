package com.wm;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

/**
 * Servlet Filter implementation class FirstFilter
 */
public class FirstFilter implements Filter {
	String excluded_servlet_login = null;
	String excluded_servlet_logout = null;
	/**
	 * Default constructor.
	 */
	public FirstFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		Cookie[] cs = req.getCookies();

		Customer customer = new Customer();
		//String userid = null;
		if (cs != null) {
			for (int i = 0; i < cs.length; i++) {
				if ("id".equalsIgnoreCase(cs[i].getName())) {
					//user = new User();
					customer.setCustomerId(cs[i].getValue());
				}
				if ("pwd".equalsIgnoreCase(cs[i].getName())) {
					customer.setCustomerPwd(cs[i].getValue());
				}
			}
		}
		if (customer.getCustomerId() != null && customer.getCustomerPwd() != null) {
			session.setAttribute("customer",customer);
		}
		if (session.getAttribute("customer") != null) {
			chain.doFilter(request, response);
		} else if (req.getServletPath().contains("/login/")) {
			chain.doFilter(request, response);
		} else if (req.getServletPath().endsWith(excluded_servlet_login)) {
			chain.doFilter(request, response);
		} else if(req.getServletPath().endsWith(excluded_servlet_logout)){
			chain.doFilter(request, response);
		} else {
			res.setHeader("refresh", "0;url=/shopping/login/login.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.excluded_servlet_login = fConfig.getInitParameter("excluded_servlet_login");
		this.excluded_servlet_logout = fConfig.getInitParameter("excluded_servlet_logout");
	}

}
