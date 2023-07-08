package com.eviro.assessment.grad001.ThuthkaniMthiyane.Controller;
import com.eviro.assessment.grad001.ThuthkaniMthiyane.Entity.UserDetails;
import com.eviro.assessment.grad001.ThuthkaniMthiyane.Repository.AccountProfileRepository;
import com.eviro.assessment.grad001.ThuthkaniMthiyane.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/v1/api/image")
public class ImageController implements FileParser {

    @Autowired
    private AccountProfileRepository accountProfileRepository;
    private String[] imageFormat;

    @PostConstruct
    public void init() {
        String csvFilePath = new Utility().getResourceFilePath("1672815113084-GraduateDev_AssessmentCsv_Ref003.csv");

//        FileProcessor fileProcessor = new FileProcessor(accountProfileRepository);
            parseCSV(new File(csvFilePath));
    }

    @GetMapping(value = "/{name}/{surname}/{\\w\\.\\w}")
    public ResponseEntity<FileSystemResource> gethttpImageLink(@PathVariable String name, @PathVariable String surname){
        try {
            List<UserDetails> userDetailsList = accountProfileRepository.findAll();

            for(UserDetails user: userDetailsList){
                if(user.getName().equalsIgnoreCase(name) && user.getSurname().equalsIgnoreCase(surname)){

                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Content-Type", "image/png");
                    FileSystemResource resource = new FileSystemResource(user.getImagePath());

                    return ResponseEntity.ok()
                            .headers(headers)
                            .body(resource);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void parseCSV(File csvFile) {

        try {

            LineNumberReader inputCsvFile = new LineNumberReader(new FileReader(csvFile));
            String eachLine;
            int iteration = 0;
            while ((eachLine = inputCsvFile.readLine()) !=  null ){
                if(iteration == 0) {
                    iteration++;                //skips first line (column headings)
                    continue;
                }

                UserDetails user = new UserDetails();
                String [] columns = eachLine.split(",");
                if(columns.length == 4){
                    user.setName(columns[0]);
                    user.setSurname(columns[1]);
                    this.imageFormat = columns[2].split("/");
                    File imageFile = convertCSVDataToImage(columns[3]);
                    URI imageLink = createImageLink(imageFile);
                    user.setImagePath(imageLink.getPath());
                    accountProfileRepository.save(user);
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
            File imageFile = File.createTempFile("image", "."+this.imageFormat[1]);
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
            String resourcesPath = new Utility().getResourcesPath();
            Path destinationDir = Paths.get(resourcesPath);

            // Create the destination directory if it does not exist
            if (!Files.exists(destinationDir)) {
                Files.createDirectories(destinationDir);
            }

            Path destination = destinationDir.resolve(fileImage.getName());
            Files.copy(fileImage.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            return destination.toUri();
        } catch (IOException e) {
            throw new RuntimeException("Error creating image link.", e);
        }
    }
}
