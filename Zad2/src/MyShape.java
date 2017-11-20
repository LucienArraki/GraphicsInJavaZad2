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

    public String sketch;
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

    public MyShape(ArrayList<Point> points, String sketch, Object color, ImagePanel imagePanel) {
        this.points = new ArrayList<Point>();
        this.points = points;
        this.sketch = sketch;
        this.color = (Color) color;
        isPolygon = true;
        this.imagePanel = imagePanel;
    }

    protected Shape figureSketch() {
        if (sketch.toString().equals("Kolo")) {
            id++;
            return new Ellipse2D.Double(xy1.getX(), xy1.getY(), width, height);
        } else if (sketch.toString().equals("Wielokat") && isPolygon) {
            int size = points.size();
            int[] xpoint = new int[size];
            int[] ypoint = new int[size];
            int pom = 0;
            for (int i = 0; i < size - pom; i++) {
                xpoint[i] = (int) points.get(i + pom).getX();
                ypoint[i] = (int) points.get(i + pom).getY();
                polygonNumer++;
            }
            isPolygon = false;
            pom = polygonNumer;
            id++;
            return new Polygon(xpoint, ypoint, points.size());
        } else if (sketch.toString().equals("Kwadrat")) {
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

    @Override
    public String toString() {
        String out = sketch + " " + xy1.getX() + "x" + xy1.getY();
        return out;
    }
}
