<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Homepage</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<script>
  function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response); 
    if (response.status === 'connected') {
      testAPI();
    } else {
    window.location.href = "./facebook";
     conlose.log("Please log into this app.");
    }
  }
  function checkLoginState() {
    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
    });
  }

  window.fbAsyncInit = function() {
    FB.init({
      appId      : '179260946059218',
      cookie     : true,  // enable cookies to allow the server to access 
                          // the session
      xfbml      : true,  // parse social plugins on this page
      version    : 'v2.8' // use graph api version 2.8
    });
    
    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
    });
  };

  // Load the SDK asynchronously
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));
  function testAPI() {
    FB.api('/me?fields=id,name,email', function(response) {
      //console.log('Successful login for: ' + response.name);
      console.log(response);
      });
  }
</script>

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
  </div>
</nav>
		<div class="container">
		<div class="row">
			<div class="col-sm-4 my-auto">
				<img class="card-img-top img-responsive" src="${user.profilepic }${u.profilepic}" alt="Card image cap" style="width: 100%">
			</div>
			<div class="col-sm-8">
				<div class="card-body">
  				<h5 class="card-title">
  					<c:out value="${user.name }" />
					<c:out value="${u.name }" />
				</h5>
  				<p class="card-text">
  					Email: <c:out value="${user.email }${u.email }" /><br>
  					Description: <c:out value="${user.description }${u.description }" /> <br>
  				</p>
				<button type="button" class="btn btn-primary" onclick="document.location.href='./audiorecord';">
				  Add Post
				</button>
				</div>
			</div>
		</div>
		</div>
<!--  Show Post List -->
	<br />	
	
	<div class="card w-50 rounded bg-light py-2 mb-2 ml-3" style="margin: auto;">
	<div class="container">
		<h2> My Posts</h2>
		<c:forEach var="post" items="${posts }">
		<div class="row">
			<div class="col-sm-4 my-auto">
				<img class="card-img-top img-responsive" src="${post.photo}" alt="Post Image" style="width: 100%">
			</div>
			<div class="col-sm-8">
				<div class="card-body">
  				<h5 class="card-title">
  					<c:out value="${post.title }" />
				</h5>
  				<p class="card-text">
  					Annotation: <c:out value="${post.annotation }" /><br>
  				</p>
  				<form id="showp" name="showp" method="POST" action="/showPost" style="width: 50%;margin: auto;">
	  				<input type="hidden" name="postId" value="${post.id }" >
					<button type="submit" class="btn btn-primary" name="postbtn">
					  View Post
					</button>
				</form>
				</div>
			</div>
		</div>
		</c:forEach>	
	</div>
	</div>	
	
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
</body>

</html>