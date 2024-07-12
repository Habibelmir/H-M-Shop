

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Classes.produit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class ListProd
 */
@WebServlet("/ListProd")
public class ListProd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListProd() {
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
			List<produit> list = new ArrayList<>();
			String jdbcUrl = "jdbc:mysql://localhost:3306/gestioncmd";
			String username = "root";
			String password = "Root";
			
			try(Connection conn = DriverManager.getConnection(jdbcUrl,username,password))
			{
				
				String querry = "select * from produit";
				try(PreparedStatement pr = conn.prepareStatement(querry))
				{
					
					try(ResultSet r = pr.executeQuery())
					{
						while(r.next()) 
							{
								 produit p = new produit();
								 p.setIdProduit(r.getInt("idProduit"));  // Access columns by name for clarity
								 p.setLibelleProduit(r.getString("LibelleProduit"));
								 p.setPrix(r.getInt("Pu"));
								 p.setTaille(r.getString("taille"));
								 p.setColor(r.getString("color"));
								 p.setIdCategorie(r.getInt("idCategorie"));
								 p.setImage(r.getString("image"));
								 
								 list.add(p);
								    
							}
							
							if (!list.isEmpty()) 
							{
								session.setAttribute("listProduit", list);
				            }else {
				            	
				                session.setAttribute("erreur", "Pas de produit !");
				            }
				            response.sendRedirect(request.getContextPath() + "/ListProduit.jsp");
						
						
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
