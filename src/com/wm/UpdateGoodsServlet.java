package com.wm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateGoodsServlet
 */
public class UpdateGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateGoodsServlet() {
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
		String pid = request.getParameter("pid");
		String operate = request.getParameter("operate");
		Good go = null;
		if (pid != null && !"".equalsIgnoreCase(pid) && operate != null
				&& !"".equalsIgnoreCase(operate)) {
			try {
				go = DAOFactory.getIGoodsDAOInstance().findGoodByPid(pid);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (go != null) {
				ShoppingCart cart = (ShoppingCart) request.getSession()
						.getAttribute("cart");
				if (cart != null) {
					if ("add".equals(operate)) {
						addGoodQuantity(cart, Integer.parseInt(pid));
					}
					if ("sub".equals(operate)) {
						subGoodQuantity(cart, Integer.parseInt(pid));
					}
					request.getSession().setAttribute("cart", cart);
					response.sendRedirect("/shopping/goods/cart.jsp");
				}
			}
		}
	}

	public void addGoodQuantity(ShoppingCart cart, int pid) {
		int quantity = cart.getItems().get(pid).getQuantity();
		cart.getItems().get(pid).setQuantity(++quantity);
	}

	public void subGoodQuantity(ShoppingCart cart, int pid) {
		int quantity = cart.getItems().get(pid).getQuantity();
		if(quantity != 1){
			cart.getItems().get(pid).setQuantity(--quantity);
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
