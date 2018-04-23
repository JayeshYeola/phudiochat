<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Profile</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	 	<a class="navbar-brand" href="#">
	    	<img src="https://upload.wikimedia.org/wikipedia/commons/b/b6/STEREO_EUV_Feb10_rotating.gif" width="30" height="30" alt="Icon Missing">
	  	</a>
		<a class="navbar-brand" href="#">
	    	Phudio Chat!
	  	</a>
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item">
	        <a class="nav-link" href="#">Upload Image </a>
	      </li>
	      <li class="nav-item active">
	        <a class="nav-link" href="#">Profile<span class="sr-only">(current)</span></a>
	      </li>
	    </ul>
	  </div>
</nav>	
<h2 align="center">	Welcome to Phudio Chat </h2>	
<br />
<div class="container">
	<div class="row">
		<form action="user/add" method="post" enctype = "multipart/form-data">
			<div class="form-group">
				<label for="exampleInputEmail1">Email address</label>
			   	<input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
			   	<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
			</div>
			<div class="form-group">
			   	<label for="text1">Name</label>
			   	<input type="text" name="name" class="form-control" id="text1" placeholder="Enter Name">
			</div>
			<div class="form-group">
			   	<label for="text2">Description</label>
			   	<input type="text" name="description" class="form-control" id="text2" placeholder="Enter Description">
			</div>
			<div class="form-group">
				<label for="exampleFormControlFile1">Profile Photo</label>
				<input type="file" name="profilepic" class="form-control-file" id="exampleFormControlFile1">
		 	</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>		
	</div>
	
</div>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
</body>
</html>