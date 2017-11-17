import javax.swing.*;
import java.awt.*;

public class Panel {

    JFrame ramka;   //Ekran główny

    public void GUI() {

        ramka = new JFrame();
        ramka.setTitle("Zad2 Grafika kompiterowa");     //Tytuł aplikacji

        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       //Wyłącz

        //Panel Menu
        JMenuBar menuBar = new JMenuBar();
        ButtonMenu buttonMenu = new ButtonMenu();
        menuBar.add(buttonMenu);
        ramka.setJMenuBar(menuBar);


        //Panele
        ImagePanel imagePanel = new ImagePanel(buttonMenu, "image");
        ramka.add(imagePanel);

        ramka.setVisible(true);     //Ustawienia
        ramka.setResizable(false);
        ramka.pack();
    }

    public static void main(String[] args) {
        Panel grad = new Panel();
        grad.GUI();
    }
}
