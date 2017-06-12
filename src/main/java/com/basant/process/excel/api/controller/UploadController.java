package com.basant.process.excel.api.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	private static String uploadedFileName = "";
	// Save the uploaded file to this folder

	private static String uploadedFolder = "C:/Users/basanta.kumar.hota/Desktop/ApplicationDataHouse/";

	@GetMapping("/")
	public String index() {
		return "upload";
	}

	@RequestMapping("/upload")
	public String singleFileUpload(@ModelAttribute FileDTO fileDto, Model model) {
		MultipartFile file = fileDto.getFile();
		if (file.isEmpty()) {
			model.addAttribute("message", "Invalid File");
			return "upload";
		}
		try {
			System.out.println(fileDto);
			file.transferTo(new File(uploadedFolder
					+ file.getOriginalFilename()));
			model.addAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename()
							+ "'");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "upload";
	}

}