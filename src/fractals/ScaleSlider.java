package fractals;

import javax.swing.*;
import java.awt.*;

/** This class is to be instantiated as a slider for controlling the tree branches scale **/


class ScaleSlider extends MySlider {

    public ScaleSlider(TreeFractal tree) {
        super(tree);

        setValue((int)(tree.getScale() * 50));

        setBackground(Color.WHITE);
        addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            double scale = (((double)source.getValue()) / 50);
            tree.setScale(scale);
            JWindow.pane.repaint();
        });
    }
}