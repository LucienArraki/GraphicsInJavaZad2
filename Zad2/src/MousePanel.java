import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class MousePanel extends JPanel implements MouseListener, MouseMotionListener {

    private ButtonMenu buttonMenu;      //Panel przycisków
    private Point point;
    private ArrayList<MyShape> arrayListMyShape;
    private ImagePanel imagePanel;
    protected ArrayList<Point> points;

    public MousePanel() {
    }

    public MousePanel(ButtonMenu buttonMenu, ImagePanel imagePanel) {
        this.points = new ArrayList<Point>();
        this.imagePanel = imagePanel;
        arrayListMyShape = imagePanel.arrayListMyShape;
        this.buttonMenu = buttonMenu;
        setVisible(true);
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {

    }       //Ruch wcisnieta mysz

    @Override
    public void mouseMoved(MouseEvent e) {
    }       //Poruszanie sie

    @Override
    public void mousePressed(MouseEvent e) {    //Wcisniecie
        point = new Point(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {   //Puszczenie
        MyShape myShape = new MyShape(point, new Point(e.getX(), e.getY()),
                buttonMenu.getSketchChange().getSelectedItem().toString(),
                buttonMenu.getColorChange().getSelectedItem(), imagePanel);
        arrayListMyShape.add(myShape);      //Tworzenie tablicy figur narysowanych
        imagePanel.repaint();
        buttonMenu.listPanel.setList(arrayListMyShape);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {    //Przycisniecie
        String polygon = buttonMenu.getSketchChange().getSelectedItem().toString();

        if (polygon == "Wielokat") {
            if (points.size() > 2 && Math.sqrt(Math.pow(e.getX() - points.get(0).getX(), 2)
                    + Math.pow(e.getY() - points.get(0).getY(), 2)) < 50) {
                System.out.println(points.size());

                MyShape myShape = new MyShape(points,
                        buttonMenu.getSketchChange().getSelectedItem().toString(),
                        buttonMenu.getColorChange().getSelectedItem(), imagePanel);
                arrayListMyShape.add(myShape);
                imagePanel.repaint();
            } else
                points.add(new Point(e.getX(), e.getY()));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }    //Wejscie w panel

    @Override
    public void mouseExited(MouseEvent e) {
    }     //Wyjscie z panelu
}