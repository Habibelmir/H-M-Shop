<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div class="main">  	
			<input type="checkbox" id="chk" aria-hidden="true">

			<div class="signup">
				<form action="Inscrip" method="post">
				
					<label for="chk" aria-hidden="true">Sign up</label>
					<input type="text" name="nom" placeholder="Nom" required="">
					<input type="text" name="prenom" placeholder="Prenom" required="">
					<input type="text" name="adress" placeholder="Adresse de livraison" required="">
					<input type="text" name="username" placeholder="username" required="">
					<input type="password" name="password" placeholder="Password" required="">
					<span class="">
						<input type="submit" value="Sign up">
					</span>
				</form>
				
			</div>

			<div class="login">
				<form action="Authentif" method="post">
					<label for="chk" aria-hidden="true">Login</label>
					<input type="text" name="username" placeholder="UserName" required="">
					<input type="password" name="password" placeholder="Password" required="">
					<span>
						<input type="submit" value="Sign in">
					</span>
				</form>
			</div>
	</div>
</body>
</html>