package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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
    private static BufferedImage slaveImage;
    private Graphics graphics;

    public static void main(String[] args) {
        ArrayList<File> list = new ArrayList<>();
        try {
            BufferedImage masterImage = ImageIO.read(imageToInsert.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            //System.out.println(file);
            try {
                slaveImage = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int slaveImageWidth = slaveImage.getWidth();
            int slaveImageHeight = slaveImage.getHeight();
            System.out.println(file + " " + slaveImageHeight + "x" + slaveImageWidth);
        }
    }
}
