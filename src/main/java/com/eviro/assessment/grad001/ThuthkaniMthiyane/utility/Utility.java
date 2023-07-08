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
            throw new IllegalArgumentException("File not found: " + fileName);
        } else {
            try {
                File file = new File(resource.toURI());
                Path filePath = file.toPath();

                // Convert the Path object to a string, using the correct file separator for the OS
                return filePath.toString();
            } catch (URISyntaxException e) {
                throw new RuntimeException("Error getting resource file path: " + fileName, e);
            }
        }
    }

    public String getResourcesPath(){
        String currentDirectory = System.getProperty("user.dir");
        Path path = Paths.get(currentDirectory, "src", "main", "resources", "static");

        // Get the absolute path from the root directory
        Path absolutePath = path.toAbsolutePath();

        // Convert the Path object to a string
        return absolutePath.toString();
    }
}
