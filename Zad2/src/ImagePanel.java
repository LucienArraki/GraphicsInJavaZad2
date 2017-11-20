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
    protected ButtonMenu buttonMenu;

    public ImagePanel() {
    }

    public ImagePanel(ButtonMenu buttonMenu, String name, ArrayList<MyShape> arrayListMyShape) {
        this.arrayListMyShape = arrayListMyShape;
        this.buttonMenu = buttonMenu;

        //Nasluchiwanie na myszy
        mousePanel = new MousePanel(buttonMenu, this);
        addMouseListener(mousePanel);
        addMouseMotionListener(mousePanel);

        //Wczytanie obrazu
        try {
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
        Dimension dimension = new Dimension(image.getWidth(), image.getHeight());
        setPreferredSize(dimension);
        this.repaint();
    }
}
