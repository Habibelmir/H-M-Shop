

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Inscrip
 */
@WebServlet("/Inscrip")
public class Inscrip extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscrip() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
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
        	
        	String requete = "insert into user values(NULL,?,?,?,?,?,'client')";
        	
        	// Pour eviter l'attack Sql injection
        	
        	try (PreparedStatement preparedStatement = conn.prepareStatement(requete)) 
        	{
        		preparedStatement.setString(1,request.getParameter("nom"));
       		 	preparedStatement.setString(2,request.getParameter("prenom"));
       		 	preparedStatement.setString(3,request.getParameter("adress"));
       		 	preparedStatement.setString(4,request.getParameter("username"));
    		 	preparedStatement.setString(5,request.getParameter("password"));
    		 	// Execution de la Requete 
       		    int rowsAffected = preparedStatement.executeUpdate();
       		    if (rowsAffected > 0) {
       		       response.sendRedirect(request.getContextPath()+"/ListCat");
       		    } else 
       		    {
       		    	session.setAttribute("ErreurInscription", "");
       		    	response.sendRedirect(request.getContextPath()+"/Authentification.jsp");
       		    }
            }
        }
        catch (SQLException e) 
        {
            e.printStackTrace(); 
        }
	}

}
