import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class ButtonMenu extends JButton implements ActionListener {

    private JButton saveImage;
    private JButton openImage;
    private JButton openEditedImaage;
    private JComboBox sketchChange;
    private JComboBox colorChange;
    protected String nameOfImage;
    private Panel panel;
    private ImagePanel imagePanel;

    protected ArrayList<MyShape> arrayListMyShape;

    public ButtonMenu(ArrayList<MyShape> arrayListMyShape, Panel panel) {
        this.arrayListMyShape = arrayListMyShape;
        this.nameOfImage = panel.nameOfImage;
        this.panel = panel;
        //Panele
        imagePanel = new ImagePanel(this, nameOfImage, arrayListMyShape);

        panel.ramka.add(imagePanel);

        //Nazwy przycisków
        saveImage = new JButton("Zapisz obraz");
        openImage = new JButton("Otworz obraz");
        openEditedImaage = new JButton("Otworz edytowany obraz");
        //Listy figur
        Color color[] = {Color.BLACK, Color.BLUE, Color.RED, Color.PINK, Color.GREEN, Color.CYAN, Color.YELLOW, Color.WHITE};
        String figures[] = {"Kwadrat", "Kolo", "Wielokat"};

        //Utworzenie ComboBox
        sketchChange = new JComboBox<String>(figures);
        colorChange = new JComboBox<Color>(color);
        colorChange.setRenderer(new ColorComboRenderer());

        saveImage.addActionListener(this);
        openImage.addActionListener(this);
        sketchChange.addActionListener(this);
        colorChange.addActionListener(this);
        openEditedImaage.addActionListener(this);

        setLayout(new FlowLayout());
        add(saveImage);
        add(openImage);
        add(sketchChange);
        add(colorChange);
        add(openEditedImaage);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        Object source = arg0.getSource();

        if (source == saveImage) {
            SaveOpenImage.saveFile(arrayListMyShape, nameOfImage);
        }
        else if (source == openImage) {
            System.out.println(nameOfImage);
            imagePanel.updateImage(SaveOpenImage.loadImage());
            imagePanel.arrayListMyShape.clear();
            nameOfImage = SaveOpenImage.getFilename();
        }
        else if (source == openEditedImaage){
            ArrayList<MyShape> array = new ArrayList<MyShape>();
            array = SaveOpenImage.loadFile(imagePanel);
            imagePanel.arrayListMyShape = array;
            imagePanel.repaint();

        }
    }

    public JComboBox getSketchChange() {
        return sketchChange;
    }

    public JComboBox getColorChange() {
        return colorChange;
    }
}

class ColorComboRenderer extends JPanel implements ListCellRenderer {
    private Color m_c = Color.black;

    public ColorComboRenderer() {
        super();
        setBorder(new CompoundBorder(
                new MatteBorder(2, 10, 2, 10, Color.white),
                new LineBorder(Color.black)));
    }

    public Component getListCellRendererComponent(JList list, Object obj,
                                                  int row, boolean sel, boolean hasFocus) {
        if (obj instanceof Color)
            m_c = (Color) obj;
        return this;
    }

    public void paint(Graphics g) {
        setBackground(m_c);
        super.paint(g);
    }
}
