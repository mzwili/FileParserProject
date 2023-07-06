package com.eviro.assessment.grad001.ThuthkaniMthiyane.Entity;


import org.springframework.util.Base64Utils;
import java.io.*;
import java.net.URI;
import java.nio.file.*;
import java.util.*;


public class FileProcessor implements FileParser {

    private String[] imageFormat = null;
    private List<UserDetails> userDetailsList;

    @Override
    public void parseCSV(File csvFile) {
        try {
            LineNumberReader inputCsvFile = new LineNumberReader(new FileReader(csvFile));
            String eachLine;
            int iteration = 0;
            while ((eachLine = inputCsvFile.readLine()) !=  null ){
                if(iteration == 0) {
                    iteration++;
                    continue;
                }
                String [] columns = eachLine.split(",");
                if(columns.length == 4){
                    UserDetails user = new UserDetails();
                    user.setName(columns[0]);
                    user.setSurname(columns[1]);
                    this.imageFormat = columns[2].split("/");
                    File imageFile = convertCSVDataToImage(columns[3]);
                    URI imageLink = createImageLink(imageFile);
                    user.setImagePath(imageLink);
                    this.userDetailsList = new ArrayList<>();
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
            Path destination = Path.of("/home/wtc/springbootApp/ThuthkaniMthiyane/src/main/resources/static", fileImage.getName());
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
