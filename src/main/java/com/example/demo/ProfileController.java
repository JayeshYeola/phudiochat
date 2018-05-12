package com.example.demo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Controller
public class ProfileController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private CommentRepository comRepo;
	
	@GetMapping(value="/")
	public ModelAndView renderpage() {
		
		ModelAndView indexPage = new ModelAndView();
		
		indexPage.setViewName("index");
		return indexPage;
	}
	
	@GetMapping(value="/error")
	public ModelAndView showErrorPage() {
		
		ModelAndView indexPage = new ModelAndView();
		
		indexPage.setViewName("error");
		return indexPage;
	}
	
	@PostMapping(value="/upload")
	public ModelAndView uploadToS3(
			@RequestParam("file") MultipartFile image 
			) {
		ModelAndView profilePage = new ModelAndView();

		BasicAWSCredentials cred = new BasicAWSCredentials(
				"AKIAJFZK7UK7MONPUPVQ","fZrEADwF9gydlLhg1rImLS2iRtEw7b51GYTzm5B0" 
				);
		AmazonS3 s3client = AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(cred))
				.withRegion(Regions.US_EAST_2)
				.build();
		
		try {
			PutObjectRequest putReq = new PutObjectRequest("sefileupload",image.getOriginalFilename(),image.getInputStream(),new ObjectMetadata())
					.withCannedAcl(CannedAccessControlList.PublicRead);
			
			s3client.putObject(putReq);
			
			String profileimage = "http://"+ "sefileupload" + ".s3.amazonaws.com/" + image.getOriginalFilename();
			
			profilePage.addObject("imgSrc", profileimage);
			
			profilePage.setViewName("profile");
			
			return profilePage;
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			profilePage.setViewName("error");
			return profilePage;
			
		}		
	}
	
	@GetMapping(value="/displayfriendlist")
	public ModelAndView showFriendList(HttpServletRequest req) {
		String email = (String)req.getSession().getAttribute("myEmail");
		System.out.println(email);
		User u = userRepo.findByEmail(email);
		
		String[] friendsId = u.getFriend_ids();
		if( friendsId != null) {
		System.out.println("Inside Display Friends"+u.getName()+"count : "+friendsId.length);
		List<User> friends = new ArrayList<User>();
		for (String frndId : friendsId) {
			User temp = userRepo.findByFbid(frndId);
			if(temp != null)
				friends.add(temp);
		}
		ModelAndView mv = new ModelAndView("friendlist");
		System.out.println(friends.size());
		mv.addObject("friends", friends);
		return mv;
		}
		else {
			ModelAndView mv = new ModelAndView("friendlist");
			return mv;
			
		}
			
	}
	
	@RequestMapping(value="/showProfile")
	public ModelAndView showFriendList(
			@RequestParam("friendId") int friendId,
			HttpServletRequest req) {
		System.out.println("Inside Show Profile");
		ModelAndView mv = new ModelAndView("showprofile");
		User u = userRepo.findById(friendId);
		if(u != null)
		{
			System.out.println(u.getName());
		}
		mv.addObject("u",u);
		List<Post> posts = postRepo.findByuserId(friendId);
		mv.addObject("posts", posts);
		return mv;
	}
	
	@GetMapping(value="/facebook")
	public ModelAndView renderFaceBook() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("facebook_index");
		return mv;
	}
	
	@PostMapping(value="/facebookRedirect")
	public ModelAndView getRedirect(
		@RequestParam(name="myId") String MyId,
		@RequestParam(name="myName") String MyName,
		@RequestParam(name="myEmail") String MyEmail,
		@RequestParam(name="myFriends") String MyFriends,
		HttpServletRequest req
		)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("homepage");
		System.out.println("My Friends are : "+ MyFriends);
		try {
		User u = userRepo.findByEmail(MyEmail);
		List<User> ulist = userRepo.findAll();
		System.out.println("User List : ");
		for(int i=0;i<ulist.size();i++)
			System.out.println(ulist.get(i));
		req.getSession().setAttribute("userId", MyId);
		req.getSession().setAttribute("userName", MyName);
		req.getSession().setAttribute("myEmail", MyEmail);
		if(u == null)
		{
			System.out.println("User is null");
			ModelAndView myview = new ModelAndView();
			User u3 = new User();
			u3.setFbid(MyId);
			u3.setEmail(MyEmail);
			u3.setName((String) req.getSession().getAttribute("userName"));
			System.out.println("u3:"+u3.getEmail());
			if(!MyFriends.isEmpty()) {
				String[] arr = MyFriends.split("/");
				System.out.println("Spliting my Friends:"+arr.toString());
			String[] friendlist = new String[arr.length];
			for(int j = 0; j < arr.length; j++) {
				System.out.println(arr[j]);
				String[] friend = arr[j].split(",");
				for(int k = 0; k < friend.length; k++) {
					System.out.println("My Friend are: " +friend[k]);
					friendlist[j] = friend[0];
				}
			}
				u3.setFriend_ids(friendlist);
				userRepo.save(u3);
			}
//			System.out.println(u3.getName());
//			System.out.println(u3.getEmail());
			
			myview.addObject("u3",u3);
			myview.setViewName("createprofile");
			return myview;
			// throw new Exception("User is null");
		}
		else {
			System.out.println("Inside Else");
			if(!MyFriends.isEmpty()) {
				String[] arr = MyFriends.split("/");
				System.out.println("Spliting my Friends:"+arr.toString());
			String[] friendlist = new String[arr.length];
			for(int j = 0; j < arr.length; j++) {
				System.out.println(arr[j]);
				String[] friend = arr[j].split(",");
				for(int k = 0; k < friend.length; k++) {
					System.out.println("My Friend are: " +friend[k]);
					friendlist[j] = friend[0];
				}
			}
				u.setFriend_ids(friendlist);
				System.out.println(MyId);
				u.setFbid(MyId);
			}
			userRepo.save(u);
			mv.addObject("u",u);
			mv.setViewName("homepage");
			List<Post> posts = postRepo.findByuserId(u.getId());
			mv.addObject("posts",posts);
			return mv;
		}
		}
		catch(Exception e){
			mv.addObject("Error","User Does not Exist");
			mv.setViewName("usermissingerror");
			e.printStackTrace();
		}
		return mv;
	}
}
