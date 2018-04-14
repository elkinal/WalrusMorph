package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by alxye on 14-Apr-18.
 */
public class Program {

    //private static Path currentDirectory = Paths.get(System.getProperty("user.dir"));
    private static Path imageToInsert = Paths.get("C:\\Users\\alxye\\Pictures\\control_image.jpg");
    private static Path currentDirectory = Paths.get("C:\\Users\\alxye\\Pictures\\");


    public static void main(String[] args) {
        ArrayList<File> list = new ArrayList<>();
        Path path2 = Paths.get(currentDirectory + "a specific image");
        try {
            Files.walk(currentDirectory)
                    .filter(p -> p.toString().endsWith(".jpg"))
                    .forEach(z -> list.add(z.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //make sure the images are stored in an existing directory
            if (Files.notExists(Paths.get(currentDirectory + "\\convertedImages"))) {
                Files.createDirectories(Paths.get(currentDirectory + "\\convertedImages"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(File file : list) {
            System.out.println(file);
        }
    }
}
