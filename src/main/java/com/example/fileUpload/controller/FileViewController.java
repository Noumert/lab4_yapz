package com.example.fileUpload.controller;

import com.example.fileUpload.service.FileInfo;
import com.example.fileUpload.service.JsonTag;
import com.example.fileUpload.service.JsonTags;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static com.example.fileUpload.service.Service.getFileNamesAndTypes;


@Controller
public class FileViewController {
    public static String jsonDirectory = System.getProperty("user.dir") + "/src/main/resources/json/";

    @PostMapping("/view")
    public String postBody(@RequestBody String json) throws IOException {
        File file = new File(jsonDirectory + "jsonTags.json");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        StringBuilder jsonSB= new StringBuilder();
        while ((st = br.readLine()) != null){
            jsonSB.append(st);
        }
        String jsonStr = jsonSB.toString();
        Gson gson = new Gson();
        JsonTags jsonTags;
        if(!jsonStr.equals("")) {
            jsonTags = gson.fromJson(jsonStr, JsonTags.class);
        } else {
            jsonTags = new JsonTags();
        }
        JsonTag jsonTag = gson.fromJson(json, JsonTag.class);
        System.out.println(json);
        System.out.println(jsonTag.name);
        System.out.println(jsonTag.tags);
        jsonTags.jsonTags.add(new JsonTag(jsonTag.name,jsonTag.tags));
        String jsonWr = gson.toJson(jsonTags);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(jsonWr);
        writer.close();
        for (JsonTag element :
                jsonTags.jsonTags) {
            System.out.println(element.name);
        }

        return "redirect:/view";
        //return uploadView(model);
    }

    @GetMapping("/view")
    public String uploadView(Model model) throws IOException {
        File file = new File(jsonDirectory + "jsonTags.json");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        StringBuilder jsonSB= new StringBuilder();
        while ((st = br.readLine()) != null){
            jsonSB.append(st);
        }
        String jsonStr = jsonSB.toString();
        Gson gson = new Gson();
        JsonTags jsonTags;
        if(!jsonStr.equals("")) {
            jsonTags = gson.fromJson(jsonStr, JsonTags.class);
        } else {
            jsonTags = new JsonTags();
        }
        List<FileInfo> filesInfo = getFileNamesAndTypes();
        for (FileInfo fileI :
                filesInfo) {
            for (JsonTag jtag :
                    jsonTags.jsonTags) {
                if (fileI.name.equals(jtag.name)) {
                    fileI.addTag(jtag.tags);
                }
            }

        }

        model.addAttribute("files", filesInfo);
        return "viewFiles";

    }
}
