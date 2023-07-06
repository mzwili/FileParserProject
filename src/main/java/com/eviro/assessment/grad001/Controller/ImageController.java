package com.eviro.assessment.grad001.Controller;
import com.eviro.assessment.grad001.Entity.FileProcessor;
import com.eviro.assessment.grad001.Entity.UserDetails;
import com.eviro.assessment.grad001.Repository.AccountProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.*;


@RestController
@RequestMapping("/v1/api/image")
public class ImageController {

    AccountProfileRepository accountProfileRepository;
    private String DirectorPath = "/home/wtc/springbootApp/ThuthkaniMthiyane/src/main/resources/static/";

    @GetMapping(value = "/{name}/{surname}/{\\w\\.\\w}")
    public FileSystemResource gethttpImageLink(@PathVariable String name, @PathVariable String surname){
        return null;
    }

    @PostMapping("/upload")
    public String uploadUserData(@RequestParam("file") MultipartFile file) throws Exception{
        String csvFilePath = DirectorPath+file.getOriginalFilename();
        FileProcessor fileProcessor = new FileProcessor();
        fileProcessor.parseCSV(new File(DirectorPath+"1672815113084-GraduateDev_AssessmentCsv_Ref003.csv"));
        if(fileProcessor.getUserDetailsList().size() <= 0){
            return " Empty list";
        }
        for(UserDetails user : fileProcessor.getUserDetailsList()){
            accountProfileRepository.save(user);
        }

        return "Successfully updated";
    }

}
