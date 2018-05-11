package com.example.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AudioController {

	@Autowired
	UploadToS3 s3;
	
	@GetMapping(value="/audiorecord")
	public ModelAndView audiorecord() {
		
		ModelAndView indexPage = new ModelAndView();
		
		indexPage.setViewName("audio_fetch");
		return indexPage;
	}
	
	@PostMapping(value="/audiostore")
	public ModelAndView getAudioComment(	
		@RequestParam("commentRecording") String commentRecording,	
		@RequestParam("imageRecording") String imageRecording,
		HttpServletRequest req
		) throws Exception{
		System.out.println("Getting Recording . . ."+imageRecording.length());
		ModelAndView successPage = new ModelAndView("success");
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
		}
		return successPage;
	}
}
