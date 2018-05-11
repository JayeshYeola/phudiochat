<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Display Post</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="#">
	   	<img src="https://upload.wikimedia.org/wikipedia/commons/b/b6/STEREO_EUV_Feb10_rotating.gif" width="30" height="30" alt="Icon Missing">
	</a>
	<a class="navbar-brand" href="#">
	   	Phudio Chat!
	</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#">Homepage <span class="sr-only">(current)</span></a>
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
      <div class="fb-login-button" data-max-rows="1" data-size="large" data-button-type="continue_with" data-show-faces="false" data-auto-logout-link="true" data-use-continue-as="false" onlogin="checkLoginState()"></div>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
<!-- Show Post -->
<div class="container">
	<div class="row">
		<div class="col-sm-4 my-auto">
			<img class="card-img-top img-responsive" src="${p.photo }" alt="Post Image" style="width: 100%">
			<audio autoplay>
				<source src="${p.audiopost }" type="audio/webm">
				</source> 
			</audio>
		</div>
		<div class="col-sm-8">
			<div class="card-body">
 				<h5 class="card-title"> <c:out value="${p.title }" /></h5>
 				<p class="card-text">
  					Annotation: <c:out value="${p.annotation }" /><br>
  				</p>
  				<form id="addComment" name="addComment" method="POST" action="/addcomment" style="margin: auto;">
	  				<input type="hidden" name="postId" value="${post.id }" >
	  				<input type="text" name="commentText">
					<button type="submit" class="btn btn-primary" name="commentbtn">
						Add/Reply Comment
					</button>
				</form>
			</div>
		</div>
	</div>
</div>

</body>
</html>