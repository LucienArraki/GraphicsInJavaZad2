import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class MyShape {
    private Point xy1;
    private Point xy2;
    private int height;
    private int width;
    private int id = 0;
    private ImagePanel imagePanel;
    private ArrayList<Point> points;
    private boolean isPolygon = false;

    private String sketch;
    protected Color color;
    int polygonNumer = 0;

    public MyShape() {
    }

    public MyShape(Point xy1, Point xy2, String sketch, Object color, ImagePanel imagePanel) {
        this.xy1 = xy1;
        this.xy2 = xy2;
        height = (int) Math.abs(xy1.getY() - xy2.getY());
        width = (int) Math.abs(xy1.getX() - xy2.getX());
        this.sketch = sketch;
        this.color = (Color) color;
        this.imagePanel = imagePanel;
    }

    public MyShape(Point xy1, Point xy2, String sketch, Object color, ImagePanel imagePanel, int id) {
        this.xy1 = xy1;
        this.xy2 = xy2;
        height = (int) Math.abs(xy1.getY() - xy2.getY());
        width = (int) Math.abs(xy1.getX() - xy2.getX());
        this.sketch = sketch;
        this.color = (Color) color;
        this.imagePanel = imagePanel;
        this.id = id;
    }

    public MyShape(MousePanel mouse, String sketch, Object color, ImagePanel imagePanel) {
        this.points = mouse.points;
        this.sketch = sketch;
        this.color = (Color) color;
        isPolygon = true;
        this.imagePanel = imagePanel;
    }

    protected Shape figureSketch() {
        if (sketch == "Kolo") {
            id++;
            return new Ellipse2D.Double(xy1.getX(), xy1.getY(), width, height);
        } else if (sketch == "Wielokat" && isPolygon) {
            int[] xpoint = new int[points.size()];
            int[] ypoint = new int[points.size()];
            int pom = 0;
            for (int i = 0; i < points.size() - pom; i++) {
                xpoint[i] = (int) points.get(i + pom).getX();
                ypoint[i] = (int) points.get(i + pom).getY();
                polygonNumer++;
            }
            isPolygon = false;
            pom = polygonNumer;
            id++;
            return new Polygon(xpoint, ypoint, points.size());
        } else if (sketch == "Kwadrat") {
            id++;
            return new Rectangle2D.Double(xy1.getX(), xy1.getY(), width, height);
        }
        return new Rectangle2D.Double(xy1.getX(), xy1.getY(), 1, 1);
    }

    public String toSerializableString() {
        String out = sketch + ";" + color.getRed() + "/" + color.getGreen() + "/" + color.getBlue() + ";" + id + "; ";
        out += "x: " + xy1.getX() + "," + xy1.getY() + " ;y: " + xy2.getX() + "," + xy2.getY();
        return out;
    }
}
