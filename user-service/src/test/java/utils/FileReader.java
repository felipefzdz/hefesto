package utils;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {
    
    public static String read(String path) throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getPath())));
        
    }
}
