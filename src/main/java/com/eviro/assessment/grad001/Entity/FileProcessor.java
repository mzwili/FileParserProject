package com.eviro.assessment.grad001.Entity;

import com.eviro.assessment.grad001.Repository.AccountProfileRepository;
import org.springframework.util.Base64Utils;
import java.io.*;
import java.net.URI;
import java.nio.file.*;
import java.util.*;


public class FileProcessor implements FileParser{

    private String[] imageFormat = null;
    private String byteString;
    private List<UserDetails> userDetailsList;

    @Override
    public void parseCSV(File csvFile) {
        try {
            LineNumberReader inputCsvFile = new LineNumberReader(new FileReader(csvFile));
            String eachLine;
            while ((eachLine = inputCsvFile.readLine()) !=  null){
                String [] columns = eachLine.split(",");
                if(columns.length == 4){
                    UserDetails user = new UserDetails();
                    user.setName(columns[0]);
                    user.setSurname(columns[1]);
                    this.imageFormat = columns[2].split("/");
                    this.byteString = columns[3];
                    File imageFile = convertCSVDataToImage(byteString);
                    URI imageLink = createImageLink(imageFile);
                    user.setImagePath(imageLink);
                    this.userDetailsList =new ArrayList<>();
                    userDetailsList.add(user);
                }

            }



        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File convertCSVDataToImage(String base64ImageData) {
        try {
            byte[] imageData = Base64Utils.decodeFromString(base64ImageData);
            File imageFile = File.createTempFile("image", this.imageFormat[1]);
            try (FileOutputStream fos = new FileOutputStream(imageFile)) {
                fos.write(imageData);
            }
            return imageFile;
        } catch (IOException e) {
            throw new RuntimeException("Error converting CSV data to image.", e);
        }
    }

    @Override
    public URI createImageLink(File fileImage) {
        try {
            Path destination = Path.of("/path/to/image/directory/", fileImage.getName());
            Files.copy(fileImage.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            return destination.toUri();
        } catch (IOException e) {
            throw new RuntimeException("Error creating image link.", e);
        }
    }

    public List<UserDetails> getUserDetailsList() {
        return userDetailsList;
    }


}
