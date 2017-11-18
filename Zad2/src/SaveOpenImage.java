import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveOpenImage {
    private static String filename = "test";

    public static boolean saveFile(ArrayList<MyShape> selections, String nameOfImage){
        filename = nameOfImage;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(nameOfImage + ".txt"));
            writer.write(nameOfImage + "\n");
            for(MyShape selection : selections){
                writer.write(selection.toSerializableString() + "\n");
            }
            System.out.println(selections.size());
            System.out.println(nameOfImage);
            writer.close();
            return true;
        }
        catch(IOException e){
            System.out.println("problem");
            e.printStackTrace();
            return false;
        }
    }

    public static BufferedImage loadImage(){
        String name = JOptionPane.showInputDialog("wpisz nazwe obrazka");
        filename = name;
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filename));
        }catch (IOException e) {e.printStackTrace();}
        return image;
    }

    private static BufferedImage loadImage(String name){
        filename = name;
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filename));
        }catch (IOException e) {e.printStackTrace();}
        return image;
    }

    public static String getFilename() {
        return filename;
    }

    public static ArrayList<MyShape> loadFile(/*ImagePanel imagePanel*/){
        ArrayList<MyShape> loadedList = new ArrayList<MyShape>();
        try {
            //Read text file
            String name = JOptionPane.showInputDialog("wpisz nazwe pliku txt");
            String textFileName = name;

            //Change image to edited
            Scanner scanner = new Scanner(new File(textFileName + ".txt"));
            filename = scanner.nextLine();
            loadImage(textFileName);

            System.out.println(scanner.nextLine());
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                String[] tab = s.split(";");

                switch (tab[0]) {
                    case "Kwadrat":
                        System.out.println( "Kwadrat");
                        break;
                    case "Kolo":
                        System.out.println("Kolo");
                        break;
                    case "Wielokat":
                        System.out.println("Wielokat");
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return loadedList;
    }
}
