package com.eviro.assessment.grad001.ThuthkaniMthiyane.Controller;
import com.eviro.assessment.grad001.ThuthkaniMthiyane.Entity.FileProcessor;
import com.eviro.assessment.grad001.ThuthkaniMthiyane.Entity.UserDetails;
import com.eviro.assessment.grad001.ThuthkaniMthiyane.Repository.AccountProfileRepository;
import com.eviro.assessment.grad001.ThuthkaniMthiyane.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/v1/api/image")
public class ImageController {

    @Autowired
    private AccountProfileRepository accountProfileRepository;

    @PostConstruct
    public void init() {
        String csvFilePath = new Utility().getResourceFilePath("1672815113084-GraduateDev_AssessmentCsv_Ref003.csv");

        FileProcessor fileProcessor = new FileProcessor(accountProfileRepository);
        fileProcessor.parseCSV(new File(csvFilePath));
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
}
