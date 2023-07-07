package com.eviro.assessment.grad001.ThuthkaniMthiyane.Controller;
import com.eviro.assessment.grad001.ThuthkaniMthiyane.Entity.FileProcessor;
import com.eviro.assessment.grad001.ThuthkaniMthiyane.Repository.AccountProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

@RestController
@RequestMapping("/v1/api/image")
public class ImageController {

    @Autowired
    private AccountProfileRepository accountProfileRepository;
    private String DirectorPath = "/home/wtc/springbootApp/ThuthkaniMthiyane/src/main/resources/static/";

    @GetMapping(value = "/{name}/{surname}/{\\w\\.\\w}")
    public FileSystemResource gethttpImageLink(@PathVariable String name, @PathVariable String surname){
        return null;
    }

    @PostMapping("/upload")
    public String uploadUserData(@RequestParam("file") MultipartFile file) throws Exception{
        String csvFilePath = DirectorPath+file.getOriginalFilename();
        file.transferTo(new File(csvFilePath));
        FileProcessor fileProcessor = new FileProcessor(accountProfileRepository);
        fileProcessor.parseCSV(new File(DirectorPath+file.getOriginalFilename()));
        return "Successfully updated";
    }

}
