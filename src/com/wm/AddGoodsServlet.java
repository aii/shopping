package com.wm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddGoodsServlet
 */
public class AddGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddGoodsServlet() {
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
		HttpSession session = request.getSession(false);
		String pid = request.getParameter("goodPid");
		Good go = null;
		Map<Integer, Good> sessionGoodsMap = null;
		if (pid != null && !"".equalsIgnoreCase(pid)) {
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
				ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
				if(cart==null){
					//首次购物
					cart=new ShoppingCart();
				}
				addGoodToCart(go,cart);
				request.getSession().setAttribute("cart", cart);
				response.sendRedirect("/shopping/EnquireGoodsServlet");
			}
		}
	}

	private void addGoodToCart(Good good, ShoppingCart cart) {// 判断是否买过
		Map<Integer, ShoppingCartItem> items = cart.getItems();

		ShoppingCartItem item = items.get(good.getPid());

		if (item == null) {
			// 此书未买过，创建新条目
			item = new ShoppingCartItem();
			item.setGood(good);
			item.setQuantity(1);
			// 条目存入购物车
			items.put(good.getPid(), item);
		} else {
			// 买过数量加1
			item.setQuantity(item.getQuantity() + 1);
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
