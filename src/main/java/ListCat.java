

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Classes.Categorie;
import Classes.produit;

/**
 * Servlet implementation class ListCat
 */
@WebServlet("/ListCat")
public class ListCat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListCat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		if(session.getAttribute("idUser") != null) 
		{
			List<Categorie> list = new ArrayList<>();
			String jdbcUrl = "jdbc:mysql://localhost:3306/gestioncmd";
			String username = "root";
			String password = "Root";
			
			try(Connection conn = DriverManager.getConnection(jdbcUrl,username,password))
			{
				
				String querry = "select * from categorie";
				try(PreparedStatement pr = conn.prepareStatement(querry))
				{
					
					try(ResultSet r = pr.executeQuery())
					{
						while(r.next()) 
							{
								 Categorie c = new Categorie();
								 c.setIdCategorie(r.getInt("idCategorie"));
								 c.setLibelleCategorie(r.getString("LibelleCategorie"));
								 list.add(c);
								    
							}
							
							if (!list.isEmpty()) 
							{
								session.setAttribute("listCategorie", list);
				            }else {
				            	
				                session.setAttribute("erreur", "Pas de Categorie !");
				            }
				            response.sendRedirect(request.getContextPath() + "/ListProd");
						
						
					}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (SQLException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else 
		{
			 response.sendRedirect(request.getContextPath() + "/Authentification.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
