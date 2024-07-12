<%@page import="java.util.ArrayList"%>
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
</head>
<body>
	<%ArrayList<produit> list ;%>
	<%if(session.getAttribute("PanierSession") != null)
	{
		 list = (ArrayList<produit>) session.getAttribute("PanierSession");
		 
	}else{ %>
	<% list = new ArrayList<produit>(); } %>
	<table class="table table-bordred">
		<tr>
			<th>Image</th>
			<th>Libelle</th>
			<th>prix</th>
			<th>taille</th>
			<th>Color</th>
			<th>Categorie</th>
			<th>Commander</th>
		</tr>
		<%for(produit p:list) {%>
			<tr>
				<td><img style="max-height: 100px; width:100px;text-align: center;" src="images/<%=p.getImage() %>"></td>
				<td><%=p.getLibelleProduit() %></td>
				<td><%=p.getPrix()%></td>
				<td><%=p.getTaille()%></td>
				<td><%=p.getColor()%></td>
				<td><%=p.getIdCategorie()%></td>
				<td>
					<a href="Commande.jsp?idProd=<%=p.getIdProduit()%>">Commander</a>
				</td>
			</tr>
			<%} %>
	</table>
</body>
</html>