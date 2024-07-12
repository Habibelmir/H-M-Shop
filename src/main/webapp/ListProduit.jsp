<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*" %> 
    <%@ page import="Classes.*" %> 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		
		td 
		{
			
			text-align: center;
		}
		th
		{
			text-align: center;
		}
	</style>
</head>
<body>
	
		<nav  class="navbar navbar-expand-lg navbar-light  ">
		 
		  <a class="navbar-brand" href="#">H-M Shop</a>
		  
		  <div class="collapse navbar-collapse"  id="navbarNav">
		    <ul class="navbar-nav d-flex justify-content-around w-100 bd-highlight">
		      <li class="nav-item active">
		        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
		      </li>
		      <%if(session.getAttribute("role").equals("client")){%>
		      <li class="nav-item">
		        <a class="nav-link" href="AjouterAuPanier">Votre Panier</a>
		      </li>
		      <%}%>
		      <li class="nav-item">
		        <a class="nav-link" href="#">vos Commandes</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="SeDeconnecter">Se Deconnecter</a>
		      </li>
		    </ul>
		  </div>
		</nav>
	



	<%ArrayList<produit> list; ArrayList<Categorie> listCat;%>
	
	<%if(session.getAttribute("listProduit") != null && session.getAttribute("listCategorie") != null)
	{
		 list = (ArrayList<produit>) session.getAttribute("listProduit");
		 listCat = (ArrayList<Categorie>) session.getAttribute("listCategorie");
		 
	}else{ %>
	<% list = new ArrayList<produit>(); listCat = new ArrayList<Categorie>();} %>
	<table style="margin-top: 100px" class="table table-bordered">
	<tr>
		<th>Image</th>
		<th>libelle</th>
		<th>prix </th>
		<th>taille</th>
		<th>Color</th>
		<th>Categorie</th>
		<%if(session.getAttribute("role").equals("client")){%>
		<th>Ajouter au panier</th>
		<%}%>
		<%if(session.getAttribute("role")!= null && session.getAttribute("role").equals("admin")){ %>
		<th>Action</th>
		<%} %>
		
	</tr>
		<%for(produit p : list){%>
			<tr class="">
				<td><center><img alt="" style="max-height: 100px; width:100px;text-align: center;" src="images/<%=p.getImage()%>"> </center> </td>
				<td><%=p.getLibelleProduit() %></td>
				<td><%=p.getPrix() %>$</td>
				<td><%=p.getTaille()%></td>
				<td><%=p.getColor()%></td>
				<%for(Categorie c:listCat){
					if(c.getIdCategorie()==p.getIdCategorie())
					{
						%>
						<td><%=c.getLibelleCategorie() %></td>
					<%}
				}%>
				<%if(session.getAttribute("role").equals("client")){%>
				<td><a href="AjouterAuPanier?id=<%=p.getIdProduit()%>" class="btn btn-success">Ajouter au panier</a></td>
				<%}%>
				<%if(session.getAttribute("role")!= null && session.getAttribute("role").equals("admin")){ %>
				<td>
					<a class="btn btn-warning" href="UpdateProduit.jsp?id=<%=p.getIdProduit()%>">Modifier</a>
					<a class="btn btn-danger" href="DeleteProd?id=<%=p.getIdProduit()%>">Supprimer</a>
				</td>
				<%} %>
			</tr>
	  <%}%>
	
	</table>
	<%if(session.getAttribute("role")!= null && session.getAttribute("role").equals("admin")){ %>
	<a style="margin-left: 600px" class="btn btn-primary" href="AjouterProduit.jsp">Ajouter Un Produit</a>
	<%} %>
</body>
</html>