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

    private ArrayList<Point> points;
    private boolean isPolygon = false;

    private String sketch;
    protected Color color;
    int polygonNumer = 0;

    public MyShape() {
    }

    public MyShape(Point xy1, Point xy2, String sketch, Object color) {
        this.xy1 = xy1;
        this.xy2 = xy2;
        height = (int) Math.abs(xy1.getY() - xy2.getY());
        width = (int) Math.abs(xy1.getX() - xy2.getX());
        this.sketch = sketch;
        this.color = (Color) color;
    }

    public MyShape(MousePanel mouse, String sketch, Object color) {
        this.points = mouse.points;
        this.sketch = sketch;
        this.color = (Color) color;
        isPolygon = true;
    }

    protected Shape figureSketch() {
        if (sketch == "Kolo") {
            id++;
            return new Ellipse2D.Double(xy1.getX(), xy1.getY(), width, height);
        } else if(sketch == "Wielokat" && isPolygon){
            int[] xpoint = new int[points.size()];
            int[] ypoint = new int[points.size()];
            int pom = 0;
            for (int i = 0; i < points.size()-pom; i++) {
                xpoint[i] = (int)points.get(i+pom).getX();
                ypoint[i] = (int)points.get(i+pom).getY();
                polygonNumer++;
            }
            isPolygon = false;
            pom = polygonNumer;
            id++;
            return new Polygon(xpoint,ypoint,points.size());
        }else if(sketch == "Kwadrat"){
            id++;
            return new Rectangle2D.Double(xy1.getX(), xy1.getY(), width, height);
        }
        return new Rectangle2D.Double(xy1.getX(), xy1.getY(), 1, 1);
    }

    public String toSerializableString() {
        String out = "b," + id;
        for(Point p : points)
            out += "," + p.x + "," + p.y;
        return out;
    }
}
