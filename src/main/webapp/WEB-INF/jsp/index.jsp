<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Phudio</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">	
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
	      
	      <li class="nav-item active">
	        <a class="nav-link" href="#">Upload Image <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">Profile</a>
	      </li>
	    </ul>
	  </div>
	</nav>	
	<h2 align="center">	Welcome to Phudio Chat </h2>	
	<br />
	<h3 align="center">	Select a Profile Photo to Upload</h3>
	<br />
	<form action="/upload" method="POST" enctype = "multipart/form-data">
		
			<table align="center" style="padding: 5px;margin-left: 40%;border-width 1px; border-color: #A9A9A9;border-style: solid;">
			<tr> <td align="center" style="border-width 1px; border-color: #A9A9A9;border-style: solid;padding: 3px"><big>Upload Profile Image</big></td> </tr>
		  	<tr> <td style="padding: 3px"><input type="file" name="file"></td></tr>
		  	<tr> <td style="padding: 3px"><input type="submit" value="Upload"></td></tr>
		  	</table>
	</form>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>