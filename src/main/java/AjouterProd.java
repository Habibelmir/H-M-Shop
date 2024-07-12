

import java.io.IOException;
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
 * Servlet implementation class AjouterProd
 */
@WebServlet("/AjouterProd")
public class AjouterProd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterProd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
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
        	
        	String requete = "insert into produit values(NULL,?,?,?,?,?,?)";
        	
        	// Pour eviter l'attack Sql injection
        	
        	try (PreparedStatement preparedStatement = conn.prepareStatement(requete)) 
        	{
        		preparedStatement.setString(1,request.getParameter("libelle"));
       		 	preparedStatement.setString(2,request.getParameter("pu"));
       		 	preparedStatement.setString(3,request.getParameter("categorie"));
       		 	preparedStatement.setString(4,request.getParameter("image"));
    		 	preparedStatement.setString(5,request.getParameter("color"));
    		 	preparedStatement.setString(6,request.getParameter("taille"));
    		 	// Execution de la Requete 
       		    int rowsAffected = preparedStatement.executeUpdate();
       		    if (rowsAffected > 0) {
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

}
