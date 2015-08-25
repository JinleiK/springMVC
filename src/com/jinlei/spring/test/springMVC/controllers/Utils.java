package com.jinlei.spring.test.springMVC.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Utils {

	public static void saveFile(int id, MultipartFile attachment) throws IOException {
		try {
			String fileType = attachment.getContentType();
			File file = new File("/offers/static/uploadFiles/offerAttaches/" + id + "." + fileType);
			byte[] bytes = attachment.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(file));
            stream.write(bytes);
            stream.close();
		} catch (Exception e) {
			throw new IOException("unable to upload file", e);
		}
	}
	
	public static void saveFile(String username, MultipartFile attachment) throws IOException {
		try {
			String fileType = attachment.getContentType();
			System.out.println(attachment.getOriginalFilename());
			System.out.println(fileType);
			File file = new File("/offers/static/uploadFiles/userAttaches/" + attachment.getOriginalFilename());
			byte[] bytes = attachment.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(file));
            stream.write(bytes);
            stream.close();
		} catch (Exception e) {
			throw new IOException("unable to upload file", e);
		}
	}


}
