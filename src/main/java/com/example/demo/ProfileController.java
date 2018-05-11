package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping(value="/")
	public ModelAndView renderpage() {
		
		ModelAndView indexPage = new ModelAndView();
		
		indexPage.setViewName("index");
		return indexPage;
	}
	
	@PostMapping(value="/upload")
	public ModelAndView uploadToS3(
			@RequestParam("file") MultipartFile image 
			) {
		ModelAndView profilePage = new ModelAndView();

		BasicAWSCredentials cred = new BasicAWSCredentials(
				System.getenv("AWS_ACCESS"), System.getenv("AWS_PRIVATE")
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
		try {
			System.out.println("inside try"+MyEmail);
		User u = userRepo.findByEmail(MyEmail);
		mv.addObject("user",u);
		mv.setViewName("homepage");
		req.getSession().setAttribute("userId", MyId);
		req.getSession().setAttribute("userName", MyName);
		req.getSession().setAttribute("myEmail", MyEmail);
		
		if(u == null)
		{
			ModelAndView myview = new ModelAndView();
			myview.setViewName("createprofile");
			return myview;
			// throw new Exception("User is null");
		}
		}
		catch(Exception e){
			mv.addObject("Error","Usre Does not Exist");
			mv.setViewName("usermissingerror");
			e.printStackTrace();
		}
		return mv;
	}
	
	
}
