import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel {

    JFrame ramka;   //Ekran główny
    protected ArrayList<MyShape> arrayListMyShape = new ArrayList<MyShape>();
    protected String nameOfImage;

    public void GUI() {

        nameOfImage = "1.jpg";
        ramka = new JFrame();
        ramka.setTitle("Zad2 Grafika kompiterowa");     //Tytuł aplikacji

        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       //Wyłącz
        ButtonMenu buttonMenu;

        //Panel Menu
        JMenuBar menuBar = new JMenuBar();
        buttonMenu = new ButtonMenu(arrayListMyShape, this);
        menuBar.add(buttonMenu);
        ramka.setJMenuBar(menuBar);

        ramka.setVisible(true);     //Ustawienia
        ramka.setResizable(false);
        ramka.pack();
    }

    public static void main(String[] args) {
        Panel grad = new Panel();
        grad.GUI();
    }
}
