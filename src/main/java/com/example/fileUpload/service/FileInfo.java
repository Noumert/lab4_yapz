package com.example.fileUpload.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileInfo {

    public String name;
    public String type;
    public List<String> tags = new ArrayList<>();
    public String tagsS;

    public FileInfo(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public boolean addTag(String tagsString) {
        String[] tags = tagsString.split(" ");
        List<String> tagsL = Arrays.stream(tags).filter(x-> (x.length()<=200 && x.length()>0)).collect(Collectors.toList());
        if (this.tags.size() + tags.length <= 100) {
            this.tags.addAll(tagsL);
            return true;
        }
        return false;
    }

    public String getTadsString() {
        String result="";
        for (String tag:
             this.tags) {
            result+= tag + " ";
        }
        return result;
    }
}
