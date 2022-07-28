package fractals;

import javax.swing.*;

/** This class is to be instantiated as a slider for controlling the left and right branch degrees **/

class DegreeSlider extends MySlider {

    // remember which degree slider this is
    public enum Orientation {
        LEFT,
        RIGHT,
        BOTH
    }
    Orientation orientation;

    // store left and right slider to update when using the left-&-right slider
    private static DegreeSlider leftSlider;
    private static DegreeSlider rightSlider;



    public DegreeSlider(TreeFractal tree, Orientation orientation) {
        super(tree);

        // handle exception
        if (orientation != Orientation.LEFT &&
                orientation != Orientation.RIGHT &&
                orientation != Orientation.BOTH)
            throw new IllegalArgumentException("Orientation must be 0, 1, or 2");

        // note this orientation to set degrees of left/right/both later when updated
        this.orientation = orientation;

        // Set default slider values
        // assign left and right slider class fields for updating later
        switch (orientation) {
            case LEFT:
                leftSlider = this;
                setValue(degreesToValue(tree.getLeftDegrees()));
                break;
            case RIGHT:
                setValue(degreesToValue(tree.getRightDegrees()));
                rightSlider = this;
                break;
            case BOTH:
                setValue(degreesToValue(tree.getLeftDegrees()));
                break;
            default:
                throw new IllegalStateException("Invalid Orientation State");
        }

        // when slider value is changed, update tree degrees
        addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            int value = source.getValue();
            double degrees = valueToDegrees(value);
            switch (orientation) {
                case LEFT:
                    tree.setLeftDegrees(degrees);
                    JWindow.pane.leftDegLabel.setText(degreesToString('L', degrees));
                    break;
                case RIGHT:
                    tree.setRightDegrees(degrees);
                    JWindow.pane.rightDegLabel.setText(degreesToString('R', degrees));
                    break;
                case BOTH:
                    tree.setDegrees(degrees);
                    updateLeftAndRightSliders();
                    break;
                default:
                    throw new IllegalStateException("Orientation is not 0, 1, or 2");
            }

            JWindow.pane.repaint();
        });
    }

    // call update left and right sliders when "both" is changed
    private void updateLeftAndRightSliders() {
        leftSlider.setValue(degreesToValue(tree.getLeftDegrees()));
        rightSlider.setValue(degreesToValue(tree.getRightDegrees()));
    }

    /** Conversions between degrees, slider value, String **/

    private static String degreesToString(Character prefix, double degrees) {
        String s = "";
        s += prefix;
        s += " ";
        s += (int)degrees;
        s += 'º';
        int targSize = 7;
        while (s.length() < targSize) {
            s = s.substring(0,1) + "  " + s.substring(1);
            targSize++;
        }
        return s;
    }

    private static int degreesToValue(double degrees) {
        return (int)((degrees) / 3.60);
    }

    private static double valueToDegrees(int value) {
        return (((double)value) * 3.60);
    }
}