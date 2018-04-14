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
import java.util.Random;

/**
 * Created by alxye on 14-Apr-18.
 */
public class Program {

    private static Path currentDirectory = Paths.get(System.getProperty("user.dir"));
    private static Path imageToInsert = Paths.get(currentDirectory + "\\convertedImages\\control_image.jpg");
    //private static Path currentDirectory = Paths.get("C:\\Users\\alxye\\Pictures\\");
    private static BufferedImage slaveImage;
    private static BufferedImage masterImage;

    public static void main(String[] args) {
        Random random = new Random();
        ArrayList<File> list = new ArrayList<>();
        try {
            masterImage = ImageIO.read(imageToInsert.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Files.walk(currentDirectory)
                    .filter(p -> p.toString().toLowerCase().endsWith(".png"))
                    .forEach(z -> list.add(z.toFile()));
            Files.walk(currentDirectory)
                    .filter(p -> p.toString().toLowerCase().endsWith(".jpg"))
                    .forEach(z -> list.add(z.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
/*        try {
            //make sure the images are stored in an existing directory
            if (Files.notExists(Paths.get(currentDirectory + "\\convertedImages"))) {
                Files.createDirectories(Paths.get(currentDirectory + "\\convertedImages"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        for(File file : list) {
            //System.out.println(file);
            try {
                slaveImage = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int slaveImageWidth = slaveImage.getWidth();
            int slaveImageHeight = slaveImage.getHeight();
            int masterImageHeight = masterImage.getHeight();
            int masterImageWidth = masterImage.getWidth();

            int w = Math.max(slaveImageWidth, masterImageWidth);
            int h = Math.max(slaveImageHeight, masterImageHeight);

            BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics g = newImage.getGraphics();

            if (slaveImage.getWidth() < masterImage.getWidth()) {
                g.drawImage(masterImage, 0, 0, null);
                g.drawImage(slaveImage, 0, 0, null);
            }
            else {
                g.drawImage(slaveImage, 0, 0, null);
                g.drawImage(masterImage, 0, 0, null);
            }
            String relativeFileName = file.getName();
            int pos = relativeFileName.lastIndexOf(".");
            if (pos > 0) {
                relativeFileName = relativeFileName.substring(0, pos);
            }
            file.delete();
            try {
                ImageIO.write(newImage, "JPG", new File(String.valueOf(currentDirectory), relativeFileName + ".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
