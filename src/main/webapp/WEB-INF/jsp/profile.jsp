<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Profile</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	</head>
	<body>	
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="./facebook">
	   	<img src="https://upload.wikimedia.org/wikipedia/commons/b/b6/STEREO_EUV_Feb10_rotating.gif" width="30" height="30" alt="Icon Missing">
	</a>
	<a class="navbar-brand" href="./facebook">
	   	Phudio Chat!
	</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="./facebook">Homepage <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="./editprofile">Edit Profile</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="./displayfriendlist">My Friends</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#"><b>Welcome ${user.name }${u.name }</b></a>
      </li>
 <!--      <li class="nav-item">
        <a class="nav-link" href="./logout">Logout</a>
      </li> -->
      <div class="fb-login-button" data-max-rows="1" data-size="large" data-button-type="continue_with" data-show-faces="false" data-auto-logout-link="true" data-use-continue-as="false" onlogin="checkLoginState()"></div>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
	<h2 align="center">	Welcome to Phudio Chat </h2>	
	<br />
		
	<div class="card w-75 rounded mx-auto bg-light" style="padding-bottom: 10px; padding-top: 10px; margin-bottom: 10px;">
		<div class="container">
			<div class="row">
				<div class="col-sm-4">
		  			<img class="card-img-top img-responsive" src="${imgSrc }" alt= "Profile Page Image" style="width: 100%">
		  		</div>
		  		<div class="col-sm-8">
			  		<div class="card-body">
			    		<h5 class="card-title">Jayesh Yeola</h5>
			    			<p class="card-text">
			    				Course: Masters in Computer Science <br> 
			    				Lab 2 Assignment - Profile Photo Upload <br>	
			    			</p>
			    	</div>
			  	</div>
			</div>
		</div>
	</div>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	</body>
</html>