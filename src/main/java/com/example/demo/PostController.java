package com.example.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostController {

	@Autowired
	UploadToS3 s3;
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CommentRepository comRepo;
	
	@GetMapping(value="/audiorecord")
	public ModelAndView audiorecord() {
		
		ModelAndView indexPage = new ModelAndView();
		
		indexPage.setViewName("audio_fetch");
		return indexPage;
	}
	
	@PostMapping(value="/showPost")
	public ModelAndView showPost(
			@RequestParam("postId") int postId,
			HttpServletRequest req
			) {
		System.out.println("Inside Post Controller");
		Post p = postRepo.findById(postId);
		System.out.println(p.getPhoto());
		ModelAndView mv = new ModelAndView("displayPost");
		mv.addObject("p",p);
		
		Comment com = new Comment();
		List<Comment> comments = comRepo.findByPostid(postId);
		mv.addObject("comments", comments);
		return mv;
	}
	
	@PostMapping(value="/addcomment")
	public ModelAndView saveComment(
			@RequestParam("postId") int postId,
			@RequestParam("commentorId") int commentorId,
			@RequestParam("commentText") String commentText,
			HttpServletRequest req
			) {
		System.out.println("Inside Add Comment");
		Comment c= new Comment();
		c.setCommentor(commentorId);
		c.setPostid(postId);
		c.setText(commentText);
		comRepo.save(c);
		ModelAndView mv = new ModelAndView("displayPost");
		List<Comment> comments = comRepo.findByPostid(postId);
		Post p = postRepo.findById(postId);
		System.out.println(p.getPhoto());
		mv.addObject("p",p);
		mv.addObject("comments",comments);
		return mv;
	}
	
	@PostMapping(value="/audiostore")
	public ModelAndView getAudioComment(	
		@RequestParam("commentRecording") String commentRecording,	
		@RequestParam("imageRecording") String imageRecording,
		@RequestParam("titlePost") String titlePost,
		@RequestParam("annotePost") String annotePost,
		HttpServletRequest req
		) throws Exception{
		System.out.println("Getting Recording . . ."+imageRecording.length());
		ModelAndView successPage = new ModelAndView("success");
		Post p = new Post();
		if(commentRecording.isEmpty() == false) {
			Decoder decoder = Base64.getDecoder();
			byte[] decodeByte = decoder.decode(commentRecording.split(",")[1]);
			FileOutputStream fos;
			try
			{
				fos = new FileOutputStream("MyAudio.webm");
				fos.write(decodeByte);
				fos.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			String userId = (String) req.getSession().getAttribute("userId");
			String uploadtime = DateTime.now().toString();
			System.out.println("Inside Audio" +uploadtime);
			String audiourl = s3.uploadFile(userId+uploadtime+"a.webm", new FileInputStream("MyAudio.webm"));
			successPage.addObject("audiourl",audiourl);
			p.setAudiopost(audiourl);
		}
		if (imageRecording.isEmpty() == false){
			Decoder decoder = Base64.getDecoder();
			byte[] decodeByte = decoder.decode(imageRecording.split(",")[1]);
			FileOutputStream fos;
			try
			{
				fos = new FileOutputStream("MyImage.webm");
				fos.write(decodeByte);
				fos.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			String userId = (String) req.getSession().getAttribute("userId");
			String uploadtime = DateTime.now().toString();
			System.out.println("Inside Image"+ uploadtime);
			String imageurl = s3.uploadFile(userId+uploadtime+"i.webm", new FileInputStream("MyImage.webm"));
			System.out.println(imageurl);
			successPage.addObject("imageurl",imageurl);
			p.setPhoto(imageurl);
		}
		User u = userRepo.findByEmail((String)req.getSession().getAttribute("myEmail"));
		p.setUser(u.getId());
		p.setAnnotation(annotePost);
		p.setTitle(titlePost);
		System.out.println("Save Post");
		postRepo.save(p);
		System.out.println("Post Added Successfully");
		ModelAndView mv = new ModelAndView("facebook_index");
		return mv;
	}
}
