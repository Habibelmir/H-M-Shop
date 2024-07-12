

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

/**
 * Servlet implementation class Commander
 */
@WebServlet("/Commander")
public class Commander extends HttpServlet {
	private static final long serialVersionUID = 1L;
      public static int id=1;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Commander() 
    {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		String idProd = request.getParameter("idProd");
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
        	
        	String requete = "insert into commande values(NULL,?,?,?)";
        	
        	// Pour eviter l'attack Sql injection
        	
        	try (PreparedStatement preparedStatement = conn.prepareStatement(requete)) 
        	{
        		LocalDate now = LocalDate.now();        
        		preparedStatement.setString(1,now.toString());
        		preparedStatement.setString(2,request.getParameter("AdressLivraison"));
        		int idUser = (int)session.getAttribute("idUser");
				preparedStatement.setInt(3,idUser);
				String quantite = request.getParameter("Quantite");
				session.setAttribute("quantite",quantite );
				
    		 	// Execution de la Requete 
       		    int rowsAffected = preparedStatement.executeUpdate();
       		    if (rowsAffected > 0) 
       		    {
       		    	ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
       		    	int lastInsertedId = (int) generatedKeys.getInt(1);
       		    	out.print("<p>"+lastInsertedId+"</p>");
       		    	if (generatedKeys.next()) 
       		    	{
       		    		
       		    		response.sendRedirect(request.getContextPath()+"/PasserCommande?idCmd="+lastInsertedId+"&idProd="+idProd);
                        
                    } 
       		    	else 
                    {
                        response.sendRedirect(request.getContextPath()+"/ListPanier.jsp");
                    }
       		       
       		       
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
