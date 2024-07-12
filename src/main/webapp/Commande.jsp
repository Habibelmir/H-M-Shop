<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="Commander?idProd=<%=(String)request.getParameter("idProd") %>" method="post">
		Adresse de Livraison
		<input class="form-control" type="text" name="AdressLivraison">
		<br>
		Quantité
		<input class="form-control" type="number" name="Quantite">
		<input type="submit" value="Commander">
	</form>
</body>
</html>