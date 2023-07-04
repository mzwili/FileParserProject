package com.eviro.assessment.grad001.Controller;
import com.eviro.assessment.grad001.Entity.UserDetails;
import com.eviro.assessment.grad001.Repository.AccountProfileRepository;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;


@RestController
@RequestMapping("/v1/api/image")
public class ImageController {

    @Autowired
    AccountProfileRepository accountProfileRepository;
    private List<UserDetails> UserDataList;

    @GetMapping(value = "/{name}/{surname}/{\\w\\.\\w}")
    public FileSystemResource gethttpImageLink(@PathVariable String name, @PathVariable String surname){
        return null;
    }

    @PostMapping("/upload")
    public String uploadUserData(@RequestParam("file")MultipartFile file) throws Exception{
        for(UserDetails user : UserDataList){

        }

        return "";
    }

}
