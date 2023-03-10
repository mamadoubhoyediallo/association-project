package org.mbd.aeesgs.utils;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        System.out.println("upload path >> "+uploadPath.toString());
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
    public static byte[] decompressImage(String data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length());
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }
    public static String uploadImage(String path, MultipartFile[] multipartFile) throws IOException {
        //File name
        List<String> fileNames = new ArrayList<>();
        //Full path
        String filePath = path+File.separator+fileNames;
        Arrays.asList(multipartFile).stream().forEach(file -> {
            fileNames.add(file.getOriginalFilename());
            try {
                Files.copy(file.getInputStream(), Paths.get(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //Create folder if not created
        File f = new File(path);
        if (!f.exists()){
            f.mkdir();
        }

        return fileNames.toString();
    }
    public static final String folderPath =  "formation_photos/";
    public static final Path filePath = Paths.get(folderPath);
}
