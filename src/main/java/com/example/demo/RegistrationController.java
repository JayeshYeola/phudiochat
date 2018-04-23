package com.example.demo;

import java.io.IOException;
import java.sql.Blob;

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
	public ModelAndView renderpage() {
		
		ModelAndView createProfilePage = new ModelAndView();
		
		createProfilePage.setViewName("createprofile");
		return createProfilePage;
	}
	
	@PostMapping(value="/user/add")
	public ModelAndView addUser(
			@RequestParam(name="name",required=true) String name,
			@RequestParam String email,
			@RequestParam String description,
			@RequestParam("profilepic") MultipartFile image
			) {
		User u = new User();
		u.setName(name);
		u.setEmail(email);
		u.setDescription(description);
		System.out.println(System.getenv("AWS_ACCESS"));
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
		
		u.setProfilepic(profileimage);
		userRepo.save(u);
		return new ModelAndView("index");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("index");
		}
	}
}
