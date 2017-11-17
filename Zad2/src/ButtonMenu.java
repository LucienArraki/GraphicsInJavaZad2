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
    private JComboBox sketchChange;
    private JComboBox colorChange;

    public ButtonMenu() {
        //Nazwy przycisków
        saveImage = new JButton("Otworz obraz");
        openImage = new JButton("Zapisz obraz");

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

        setLayout(new FlowLayout());
        add(saveImage);
        add(openImage);
        add(sketchChange);
        add(colorChange);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        Object source = arg0.getSource();

        if (source == saveImage){

        }

        else if (source == openImage)
            setBackground(Color.RED);
    }

    public JComboBox getSketchChange() {
        return sketchChange;
    }

    public JComboBox getColorChange() {
        return colorChange;
    }

    public static boolean saveFile(ArrayList<MyShape> selections){
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter("save.txt"));
            for(MyShape selection : selections){
                writer.write(selection.toSerializableString() + "\n");
            }
            writer.close();
            return true;
        }
        catch(IOException e){
            e.printStackTrace();
            return false;
        }
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
