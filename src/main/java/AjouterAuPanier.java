

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Classes.produit;

/**
 * Servlet implementation class AjouterAuPanier
 */
@WebServlet("/AjouterAuPanier")
public class AjouterAuPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterAuPanier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//	PrintWriter out = response.getWriter();
		Boolean found = false;
		int id;

		HttpSession session = request.getSession();
		if (request.getParameter("id") != null) {
		    id = Integer.parseInt(request.getParameter("id"));
		} else {
		    id = 999;
		}

		ArrayList<produit> listPanier;
		ArrayList<produit> list = (ArrayList<produit>) session.getAttribute("listProduit");

		if (session.getAttribute("PanierSession") == null) {
		    listPanier = new ArrayList<produit>();
		} else {
		    listPanier = (ArrayList<produit>) session.getAttribute("PanierSession");
		}

		for (produit p : list) {
		    //out.print("<p>" + id + "</p>");
		    if (p.getIdProduit() == id) {
		        found = true;
		        listPanier.add(p);
		    }
		}

		session.setAttribute("PanierSession", listPanier);

		if (!found) {
		    response.sendRedirect(request.getContextPath() + "/ListPanier.jsp");
		} else {
		    response.sendRedirect(request.getContextPath() + "/ListCat");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
