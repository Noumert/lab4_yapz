package com.example.fileUpload.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Service {

    public static final String[] correctFormats = {"avi", "mov", "mkv", "jpg", "png"};

    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/uploads";

    public static double getMFileSizeMegaBytes(MultipartFile file) {
        return (double) file.getSize() / (1024 * 1024);
    }

    public static double getMFileSizeKiloBytes(MultipartFile file) {
        return (double) file.getSize() / 1024;
    }

    public static String getFileMFormat(MultipartFile file) {
        if (file.getOriginalFilename() == null) {
            return "";
        }
        if (file.getOriginalFilename().split("\\.").length > 0) {
            return file.getOriginalFilename().split("\\.")[1].toLowerCase();
        }
        return "";
    }

    public static boolean isCorrectFormat(MultipartFile[] files) {
        boolean result = true;
        for (MultipartFile file :
                files) {
            if (Arrays.stream(correctFormats).noneMatch(x -> (x.equals(getFileMFormat(file))))) {
                result = false;
            }
        }
        return result;
    }

    public static boolean isCorrectSize(MultipartFile[] files) {
        boolean result = true;
        for (MultipartFile file :
                files) {
            if(getMFileType(file).equals("video")){
                if(getMFileSizeMegaBytes(file)>50){
                    result=false;
                }
            }else if(getMFileType(file).equals("image")){
                if(getMFileSizeMegaBytes(file)>2){
                    result=false;
                }
            }else {
                result = false;
            }
        }
        return result;
    }

    public static String getMFileType(MultipartFile file) {
        if (file.getContentType() == null) {
            return "";
        }
        if (file.getOriginalFilename().split("/").length > 0) {
            return file.getContentType().split("/")[0];
        }
        return "";
    }

    public static String getFileTypeByPath(Path path) throws IOException {
        if (Files.probeContentType(path) == null) {
            return "";
        }
        if (Files.probeContentType(path).split("/").length > 0) {
            return Files.probeContentType(path).split("/")[0];
        }
        return "";
    }

    public static StringBuilder uploadFiles(MultipartFile[] files) {
        StringBuilder fileNames = new StringBuilder();
        for (MultipartFile file :
                files) {
            Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename()).append(" ");
            System.out.println(getMFileType(file));
            try {
                Files.write(fileNameAndPath, file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileNames;
    }

    public static List<FileInfo> getFileNamesAndTypes() throws IOException {
        List<FileInfo> fileNamesAndTypes = new ArrayList<>();
        String[] files = (new File(uploadDirectory)).list();
        if (files != null) {
            for (String file :
                    files) {
                Path path = Paths.get(uploadDirectory, file);
                try {
                    fileNamesAndTypes.add(new FileInfo(file, getFileTypeByPath(path)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileNamesAndTypes;
    }

}
