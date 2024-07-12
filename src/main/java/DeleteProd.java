

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
 * Servlet implementation class DeleteProd
 */
@WebServlet("/DeleteProd")
public class DeleteProd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
				PrintWriter out  = response.getWriter();
				String id = request.getParameter("id") ;
				String jdbcUrl = "jdbc:mysql://localhost:3306/gestioncmd";
				String username = "root";
				String password = "Root";
		
		try(Connection conn = DriverManager.getConnection(jdbcUrl,username,password))
		{
			
			String querry = "delete from produit where idProduit=?";
			try(PreparedStatement pr = conn.prepareStatement(querry))
			{
				
				pr.setString(1, id);
				
				int row =pr.executeUpdate();
				if(row > 0) 
				{
					out.print("<p>"+id+"</p>");
					response.sendRedirect(request.getContextPath()+"/ListCat");
				}
				else
				{
					response.sendRedirect(request.getContextPath()+"/DeleteProd?id="+id);
				}
				
			
			}catch(SQLException e) 
			{
				e.printStackTrace();
			}
			
		}catch(SQLException e) 
		{
			e.printStackTrace()	;
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
