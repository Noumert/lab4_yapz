package com.example.fileUpload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import static com.example.fileUpload.service.Service.*;

@Controller
public class FileUploadController {
    public static final String viewDirectory = System.getProperty("user.dir") + "/src/main/resources/uploads";
    @RequestMapping("/")
    public String UploadPage(Model model) {
        return "uploadView";
    }

    @RequestMapping("/upload")
    public String Upload(Model model, @RequestParam("files") MultipartFile[] files) {
        if(isCorrectFormat(files) && isCorrectSize(files)) {
            String fileNames = uploadFiles(files).toString();
            model.addAttribute("msg", "Successfully uploaded files: " + fileNames);
            return "uploadStatusView";
        }else {
            return "Error";
        }
    }
}
