package com.example.demo;

import java.io.IOException;
import java.sql.Blob;

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
public class RegistrationController {
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping(value="/createprofile")
	public ModelAndView renderpage(HttpServletRequest req) {
		System.out.println("inside Create Profile");
		ModelAndView createProfilePage = new ModelAndView();		
		createProfilePage.setViewName("createprofile");
		User u = new User();
		u.setEmail((String) req.getSession().getAttribute("myEmail"));
		u.setName((String) req.getSession().getAttribute("userName"));
		createProfilePage.addObject("u",u);
		return createProfilePage;
	}
	
	@PostMapping(value="/adduser")
	public ModelAndView addUser(
			@RequestParam(name="name",required=true) String name,
			@RequestParam(name="email") String email,
			@RequestParam(name="description") String description,
			@RequestParam("profilepic") MultipartFile image,
			HttpServletRequest req
			) {
		System.out.println("Email in add user:"+email);
		User user = userRepo.findByEmail(email);
//		u.setName(name);
//		u.setEmail(email);
		System.out.println(email);
		user.setDescription(description);
		System.out.println(System.getenv("AWS_ACCESS"));
		BasicAWSCredentials cred = new BasicAWSCredentials(
				"AKIAJFZK7UK7MONPUPVQ", "fZrEADwF9gydlLhg1rImLS2iRtEw7b51GYTzm5B0"
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
		
		user.setProfilepic(profileimage);
		System.out.println(user.getProfilepic());
		System.out.println(user.getName());
		userRepo.save(user);
		ModelAndView mv = new ModelAndView("homepage");
		mv.addObject("user",user);
		return mv;
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("homepage");
		}
	}
}
