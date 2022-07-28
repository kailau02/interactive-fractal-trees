package fractals;

import javax.swing.*;

/** This is a generic abstract slider class that stores the tree being drawn **/

public abstract class MySlider extends JSlider{

    TreeFractal tree;

    MySlider(TreeFractal tree) {
        this.tree = tree;
    }
}
