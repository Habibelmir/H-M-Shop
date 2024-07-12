

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PasserCommande
 */
@WebServlet("/PasserCommande")
public class PasserCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasserCommande() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		int idcmd = Integer.parseInt(request.getParameter("idCmd"));
		int idprod = Integer.parseInt(request.getParameter("idProd"));
		int quantité = (int)session.getAttribute("quantite");
		String jdbcUrl = "jdbc:mysql://localhost:3306/gestioncmd";
        String username = "root";
        String password = "Root";
        // pas obligé mais il faut faire pour eviter l'embiguité
        try 
        {
        	// Pour savoir le Pilote 
			Class.forName("com.Mysql.cj.jdbc.Driver");
			
		} 
        catch (ClassNotFoundException e) 
        {
			
			e.printStackTrace();
		}
        
        // initialiser la connexion
        
        try( Connection conn = DriverManager.getConnection(jdbcUrl, username, password) )
        {
        	//Requete
        	
        	String requete = "insert into passercmd values(?,?,?)";
        	
        	// Pour eviter l'attack Sql injection
        	
        	try (PreparedStatement preparedStatement = conn.prepareStatement(requete)) 
        	{
        		       
        		preparedStatement.setInt(1, idcmd);
        		preparedStatement.setInt(2, idprod);
        		preparedStatement.setInt(3,quantité);
    		 	// Execution de la Requete 
       		    int rowsAffected = preparedStatement.executeUpdate();
       		    if (rowsAffected > 0) 
       		    {
       		    	
       		       response.sendRedirect(request.getContextPath()+"/ListCat");
       		       
       		    } else 
       		    {
       		    	//session.setAttribute("ErreurInscription", "");
       		    	response.sendRedirect(request.getContextPath()+"/AjouterProduit.jsp");
       		    }
            }
        }
        catch (SQLException e) 
        {
            e.printStackTrace(); 
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
