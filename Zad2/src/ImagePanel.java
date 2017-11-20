import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;

public class ImagePanel extends JPanel {

    private BufferedImage image;    //Obraz
    private MousePanel mousePanel;  //Panel myszy
    protected ArrayList<MyShape> arrayListMyShape;
    private String nameOfImage;

    public ImagePanel() {
    }

    public ImagePanel(ButtonMenu buttonMenu, String name, ArrayList<MyShape> arrayListMyShape) {
        this.arrayListMyShape = arrayListMyShape;
        System.out.println(name);

        //Nasluchiwanie na myszy
        mousePanel = new MousePanel(buttonMenu, this);
        addMouseListener(mousePanel);
        addMouseMotionListener(mousePanel);

        //Wczytanie obrazu
        File imageFile = new File(name + ".jpg");
        try {
            URL myURL = new URL("http://www.komputerswiat.pl/media/2015/320/4096559/emocjep.jpg");
            image = ImageIO.read(new File(name));

        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }

        //Ustawienie rozmiaru panelu
        Dimension dimension = new Dimension(image.getWidth(), image.getHeight());
        setPreferredSize(dimension);
    }

    @Override
    public void paintComponent(Graphics g) {    //Wstawienie obrazu i figur
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
        for (MyShape shape : arrayListMyShape) {
            g2d.setColor(shape.color);
            g2d.draw(shape.figureSketch());
        }
    }

    public void updateImage(BufferedImage img){
        image = img;
        this.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
        this.repaint();
    }

    public void update(ArrayList<MyShape> selections){
        arrayListMyShape.clear();
        this.repaint();
        arrayListMyShape = selections;
        repaint();
    }
}
