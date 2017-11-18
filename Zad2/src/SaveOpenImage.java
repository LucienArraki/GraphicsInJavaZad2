import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveOpenImage {
    private static String filename = "test";

    public static boolean saveFile(ArrayList<MyShape> selections, String nameOfImage) {
        filename = nameOfImage;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(nameOfImage + ".txt"));
            writer.write(nameOfImage + "\n");
            for (MyShape selection : selections) {
                writer.write(selection.toSerializableString() + "\n");
            }
            System.out.println(selections.size());
            System.out.println(nameOfImage);
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("problem");
            e.printStackTrace();
            return false;
        }
    }

    public static BufferedImage loadImage() {
        String name = JOptionPane.showInputDialog("wpisz nazwe obrazka");
        filename = name;
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    private static BufferedImage loadImage(String name) {
        filename = name;
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static String getFilename() {
        return filename;
    }

    public static ArrayList<MyShape> loadFile(ImagePanel imagePanel) {
        ArrayList<MyShape> loadedList = new ArrayList<MyShape>();
        try {
            //Read text file
            String name = JOptionPane.showInputDialog("wpisz nazwe pliku txt");
            String textFileName = name;

            //Change image to edited
            Scanner scanner = new Scanner(new File(textFileName + ".txt"));
            filename = scanner.nextLine();
            imagePanel.updateImage(loadImage(textFileName));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                String[] tab = s.split(";");

                loadedList.add(stringToShape(imagePanel, tab));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedList;
    }

    private static MyShape stringToShape(ImagePanel imagePanel, String[] tab){
        String sketch = tab[0]; //Name

        String[] split = tab[1].split("/");     //RGB for String to Color
        int r = Integer.parseInt(split[0]);
        int g= Integer.parseInt(split[1]);
        int b = Integer.parseInt(split[2]);
        Color color = new Color(r,g,b); //Color

        int id = Integer.parseInt(tab[2]);  //ID

        //Point
        split = tab[3].substring(4).split(",");
        Point x = new Point((int) Double.parseDouble(split[0]), (int) Double.parseDouble(split[1]));
        split = tab[4].substring(4).split(",");
        Point y = new Point((int) Double.parseDouble(split[0]), (int) Double.parseDouble(split[1]));

        MyShape myShape = new MyShape(x,y,sketch,color,imagePanel,id);
        return myShape;
    }
}
