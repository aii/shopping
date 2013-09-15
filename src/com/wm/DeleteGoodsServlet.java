package com.wm;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteGoodsServlet
 */
public class DeleteGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pid = request.getParameter("pid");
		Good go = null;
		boolean result = false;
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
				ShoppingCart cart = (ShoppingCart) request.getSession()
						.getAttribute("cart");
				if (cart != null) {
					if(cart.getItems().get(go.getPid()) != null){
						result = deleteGoods(cart,Integer.parseInt(pid));
						if(result){
							request.getSession().setAttribute("cart", cart);
							response.sendRedirect("/shopping/goods/cart.jsp");
						}
					}
				}
			}
		}
	}
	
	public boolean deleteGoods(ShoppingCart cart,int pid){
		boolean result = false;
		if(cart.getItems().remove(pid) != null){
			result = true;
		}else{
			result = false;
		}
		return result;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
