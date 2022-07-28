package fractals;

import javax.swing.*;
import java.awt.*;

/** This class creates the GUI **/

public class JWindow extends JFrame {

    public static final int WIDTH = 1300;
    public static final int HEIGHT = 700;
    public static final int TOP_HEIGHT = 60;

    public static DrawPane pane; // Drawing area

    public static void main(String[] args) {
        new JWindow();
    }

    // Setup the window
    public JWindow(){
        super("Fractals");

        TreeFractal tree = new TreeFractal(15,  120, 200, 0.5);

        pane = new DrawPane(tree);
        setContentPane(pane);
        pane.setFocusable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(JWindow.WIDTH,JWindow.HEIGHT + 28);
        setVisible(true);
    }

    class DrawPane extends JPanel{
        protected TreeFractal tree;

        // Sliders to manipulate fractal tree
        public JLabel bothDegLabel;
        public JLabel leftDegLabel;
        public JLabel rightDegLabel;
        public JLabel scaleLabel;
        public JLabel sizeLabel;

        public DrawPane(TreeFractal tree) {
            super();
            this.tree = tree;

            /** Setup GUI elements **/
            bothDegLabel = new JLabel("Lº & Rº");
            add(bothDegLabel);
            add(new DegreeSlider(tree, DegreeSlider.Orientation.BOTH));

            leftDegLabel = new JLabel("Lº");
            add(leftDegLabel);
            add(new DegreeSlider(tree, DegreeSlider.Orientation.LEFT));

            rightDegLabel = new JLabel("Rº");
            add(rightDegLabel);
            add(new DegreeSlider(tree, DegreeSlider.Orientation.RIGHT));

            scaleLabel = new JLabel("Sc");
            add(scaleLabel);
            add(new ScaleSlider(tree));

            sizeLabel = new JLabel("Sz");
            add(sizeLabel);
            add(new SizeSlider(tree));

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            fillBackground(g);
            if (tree != null)
                tree.draw(g);
            fillTop(g);
        }

        private void fillBackground(Graphics g) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, JWindow.WIDTH, JWindow.HEIGHT);
        }

        private void fillTop(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, JWindow.WIDTH, TOP_HEIGHT);
        }
    }
}
