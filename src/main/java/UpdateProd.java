

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateProd
 */
@WebServlet("/UpdateProd")
public class UpdateProd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String productIdParameter = request.getParameter("id");
		if(productIdParameter != null) 
		{
			PrintWriter out  = response.getWriter();
			
			int id = Integer.parseInt(productIdParameter);
			//out.print("<p>"+id+"</p>");
			String jdbcUrl = "jdbc:mysql://localhost:3306/gestioncmd";
			String username = "root";
			String password = "Root";
			
			try(Connection conn = DriverManager.getConnection(jdbcUrl,username,password))
			{
				
				String querry = "update produit set LibelleProduit=?,pu=?,taille=?,idCategorie=?,image=?,color=? where idProduit=?";
				try(PreparedStatement pr = conn.prepareStatement(querry))
				{
					
					pr.setString(1, request.getParameter("libelle"));
					pr.setString(2, request.getParameter("pu"));
					pr.setString(3, request.getParameter("taille"));
					pr.setString(4, request.getParameter("categorie"));
					pr.setString(5, request.getParameter("image"));
					pr.setString(6, request.getParameter("color"));
					pr.setString(7, productIdParameter);
					int rowsaffected = pr.executeUpdate();
					//out.print("<p>"+rowsaffected+"</p>");
					if(rowsaffected > 0) 
					{
						
						response.sendRedirect(request.getContextPath()+"/ListCat");
					}
					else 
					{
						response.sendRedirect(request.getContextPath()+"/UpdateProduit.jsp?id="+id);
					}
					
				}catch (SQLException e) 
				{
					e.printStackTrace();
				}
				
				
			} catch (SQLException e) 
			{
				
				e.printStackTrace();
			}
		}
		
	}

}
