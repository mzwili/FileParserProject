package com.eviro.assessment.grad001.ThuthkaniMthiyane.utility;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utility {

    public String getResourceFilePath(String fileName){
        URL resource = getClass().getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        } else {
            try {
                return new File(resource.toURI()).getAbsolutePath();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getResourcesPath(){
        Path path = Paths.get("src\\main\\resources\\");

        // Get the absolute path from the root directory
        Path absolutePath = path.toAbsolutePath();

        // Convert the Path object to a string
        return absolutePath.toString()+"\\";
    }
}
