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
	<%ArrayList<produit> list;ArrayList<Categorie> listCat; %>
	<%if(session.getAttribute("listProduit") != null){
		 list = (ArrayList<produit>) session.getAttribute("listProduit");
		 listCat = (ArrayList<Categorie>) session.getAttribute("listCategorie");
	}
	else
	{
		 list = new ArrayList<produit>();
		 listCat = new ArrayList<Categorie>();
	}%>
	<%for(produit p:list)
	{
		if(p.getIdProduit()==Integer.parseInt(request.getParameter("id")) )
		{%>
	<form  action="UpdateProd?id=<%=p.getIdProduit() %>" method="post">
		<div class="container w-50">
			<div class="form-group">
				<label>libelle Produit</label>
				<input class="form-control" value="<%=p.getLibelleProduit() %>" type="text" name="libelle">
			</div>
			<div class="form-group">
				<label>Prix Unitaire</label>
				<input class="form-control" value="<%=p.getPrix() %>" type="number" name="pu">
			</div>
			<div class="form-group">
				<label>Color</label>
				<input class="form-control" value="<%=p.getColor() %>" type="text" name="color">
			</div>
			<div class="form-group">
				<label>Taille</label>
				<input class="form-control" value="<%=p.getTaille() %>" type="text" name="taille">
			</div>
			<div class="form-group">
				<label>Image</label>
				<input type="file" name="image">
			</div>
			<select class="form-control" name="categorie">
			<%for(Categorie c:listCat)
			{%>
			<%if(c.getIdCategorie() ==p.getIdCategorie())
			{ %>
				<option selected value="<%=c.getIdCategorie()%>"><%=c.getLibelleCategorie() %></option>
			<%}else
			{%>
				<option value="<%=c.getIdCategorie()%>"><%=c.getLibelleCategorie() %></option>
			<%}} %>
			
			</select>
			<div class="form-group">
				<input type="submit" value="Modifier">
				<input type="reset" value="Annuler">
			</div>
			<%}}%>
		</div>
	</form>
</body>
</html>