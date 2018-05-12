<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <title>Add Post</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<link href="https://vjs.zencdn.net/6.6.3/video-js.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/videojs-record/2.1.0/css/videojs.record.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/video.js/6.7.2/video.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/RecordRTC/5.4.6/RecordRTC.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/adapterjs/0.15.0/adapter.min.js"></script>
<script src="https://collab-project.github.io/videojs-record/dist/wavesurfer.min.js"></script>
<script src="https://collab-project.github.io/videojs-record/dist/wavesurfer.microphone.min.js"></script>
<script src="https://collab-project.github.io/videojs-record/dist/videojs.wavesurfer.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/videojs-record/2.1.2/videojs.record.min.js"></script>

  <style>
  /* change player background color */
  #myAudio {
      background-color: #9FD6BA;
  }
  </style>

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
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>

<div class="container">
  <div class="page-header">
    <h1>Add Post</h1>      
  </div>
</div>
<br />
<div class="container">
<div class="row">
<label for="myAudio">Record Audio</label>
<audio id="myAudio" class="video-js vjs-default-skin" style="widht: 25%;height: 200px;margin:auto;"></audio>
<!-- </div>
<br />
<div class="row"> -->
<label for="myAudio">Capture Image</label>
<video id="myImage" class="video-js vjs-default-skin" style="widht: 25%;height: 200px;margin:auto;"></video>
</div>
</div>
<br />
<script>
var player = videojs("myAudio", {
    controls: true,
    width: 600,
    height: 300,
    fluid: false,
    plugins: {
        wavesurfer: {
            src: "live",
            waveColor: "#36393b",
            progressColor: "#black",
            debug: true,
            cursorWidth: 1,
            msDisplayMax: 20,
            hideScrollbar: true
        },
        record: {
            audio: true,
            video: false,
            maxLength: 20,
            debug: true
        }
    }
}, function() {
    // print version information at startup
    videojs.log('Using video.js', videojs.VERSION,
        'with videojs-record', videojs.getPluginVersion('record'),
        '+ videojs-wavesurfer', videojs.getPluginVersion('wavesurfer'),
        'and recordrtc', RecordRTC.version);
});

var imager = videojs("myImage", {
    controls: true,
    width: 320,
    height: 240,
    fluid: false,
    controlBar: {
        volumePanel: false,
        fullscreenToggle: false
    },
    plugins: {
        record: {
            image: true,
            debug: true
        }
    }
}, function(){
    // print version information at startup
    videojs.log('Using video.js', videojs.VERSION,
        'with videojs-record', videojs.getPluginVersion('record'));
});

// error handling
player.on('deviceError', function() {
    console.log('device error:', player.deviceErrorCode);
});
player.on('error', function(error) {
    console.log('error:', error);
});
// user clicked the record button and started recording
player.on('startRecord', function() {
    console.log('started recording!');
});
// user completed recording and stream is available
player.on('finishRecord', function() {
    // the blob object contains the recorded data that
    // can be downloaded by the user, stored on server etc.
    console.log('finished recording: ', player.recordedData);
    var reader = new FileReader();
    var base64data;
    reader.readAsDataURL(player.recordedData);
    reader.onloadend = function() {
    	base64data = reader.result;
    	console.log(base64data);
    	$("#commentRecording").val(base64data);
    }
});
//error handling
imager.on('deviceError', function() {
    console.warn('device error:', imager.deviceErrorCode);
});
imager.on('error', function(error) {
    console.log('error:', error);
});
// snapshot is available
imager.on('finishRecord', function() {
    // the blob object contains the image data that
    // can be downloaded by the user, stored on server etc.
    console.log('snapshot ready: ', imager.recordedData);
    var imgbase64data = imager.recordedData;
    $("#imageRecording").val(imgbase64data);
});

$(document).ready(function(){
	$("#saveButton").on("click",function(){
		$("#audiofetchForm").submit();
	});
});
</script>
<br>
<form id="audiofetchForm" method="POST" action="/audiostore" style="width: 50%;margin: auto;">
	<input type="hidden" name="commentRecording" id="commentRecording" />
	<input type="hidden" name="imageRecording" id="imageRecording" required="required"/>
	<div class="form-group has-success">
	  <label class="form-control-label" for="titlePost">Enter the Title</label>
	  <input type="text" class="form-control form-control-success" name="titlePost" id="titlePost">
	</div>
	<div class="form-group has-success">
	  <label class="form-control-label" for="annotePost">Enter the Annotation</label>
	  <input type="text" class="form-control form-control-success" name="annotePost" id="annotePost">
	</div>
	<button id="saveButton" class="btn btn-primary">Save</button> 
</form>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
</body>
</html>