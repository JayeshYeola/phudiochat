package com.example.demo;

import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class UploadToS3 {
	
	public String uploadFile(String filename, InputStream fs) {
		String FileURI;
		
		BasicAWSCredentials cred = new BasicAWSCredentials(
				System.getenv("AWS_ACCESS"), System.getenv("AWS_PRIVATE")
				);
		AmazonS3 s3client = AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(cred))
				.withRegion(Regions.US_EAST_2)
				.build();
			PutObjectRequest putReq = new PutObjectRequest("sefileupload",filename,fs,new ObjectMetadata())
					.withCannedAcl(CannedAccessControlList.PublicRead);
			
			s3client.putObject(putReq);
			
			FileURI = "http://"+ "sefileupload" + ".s3.amazonaws.com/" + filename;
			
		return FileURI;
	}

}
