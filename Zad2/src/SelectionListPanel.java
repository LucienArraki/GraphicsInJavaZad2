import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class SelectionListPanel extends JPanel {

    private ArrayList<MyShape> selections;
    private ImagePanel imagePanel;
    private JLabel instructionLabel;

    public SelectionListPanel(ImagePanel imagePanel){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        instructionLabel = new JLabel("       Ksztalt   wspolrzedne    ");
        instructionLabel.setForeground (Color.white);
        this.imagePanel = imagePanel;
    }

    public void setList(ArrayList<MyShape> list){
        selections = list;
        updateList();
    }

    private void updateList(){
        repaint();
        revalidate();
        removeAll();
        add(instructionLabel);
        for(MyShape selection : selections){
            DeleteSelectionButton button = new DeleteSelectionButton(selection.toString(), selection, imagePanel, this);
            add(button);
            button.addActionListener(button);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}

class DeleteSelectionButton extends JButton implements ActionListener {
    public MyShape selection;
    private ImagePanel imagePanel;
    private SelectionListPanel selectionListPanel;

    public DeleteSelectionButton(String s, MyShape selection, ImagePanel imagePanel, SelectionListPanel selectionListPanel){
        super(s);
        this.selection = selection;
        this.imagePanel = imagePanel;
        this.selectionListPanel = selectionListPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        imagePanel.arrayListMyShape.remove(selection);
        selectionListPanel.setList(imagePanel.arrayListMyShape);
        imagePanel.repaint();
    }
}