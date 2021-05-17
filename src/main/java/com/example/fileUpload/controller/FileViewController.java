package com.example.fileUpload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class FileViewController {
    public static String viewDirectory = System.getProperty("user.dir") + "/src/main/resources/uploads";

    @RequestMapping("/view")
    public String Upload(Model model) {
        List<String> fileNames = new ArrayList<>();
        String[] files = (new File(viewDirectory)).list();
        System.out.println(viewDirectory);
        System.out.println(Arrays.toString(files));
        if(files != null){
            fileNames.addAll(Arrays.asList(files));
        }
        model.addAttribute("files", fileNames);
        return "viewFiles";
   
    }
}
