package fractals;

import java.awt.*;

/** This class handles the behaviour and drawing of the recursive tree **/

public class TreeFractal {
    private int iterations;
    private double rightRadians;
    private double leftRadians;
    private double size;
    private double branchScale;

    // Customized tree fractal
    public TreeFractal(int iterations, double degrees, double size, double branchScale) {
        this(iterations, degrees, degrees, size, branchScale);
    }

    public TreeFractal(int iterations, double leftDegrees, double rightDegrees, double size, double branchScale) {
        /** Argument Exceptions **/
        if (iterations < 1)
            throw new IllegalArgumentException("Iterations must be greater than 1.");
        if (size < 0)
            throw new IllegalArgumentException("Size must be positive.");
        if (branchScale < 0)
            throw new IllegalArgumentException("Branch scale must be positive.");

        /** Set Class Fields**/
        this.iterations = iterations;
        this.leftRadians = Math.toRadians(leftDegrees);
        this.rightRadians = Math.toRadians(rightDegrees);
        this.size = size;
        this.branchScale = branchScale;
    }

    /** Recursive Draw Algorithm **/
    private Graphics g; // store global graphics var
    // entrance draw method
    public void draw(Graphics g) {
        this.g = g;
        draw(iterations, 0.0, size, JWindow.WIDTH / 2, JWindow.HEIGHT - 50);
    }

    // recursive draw method
    private void draw(int iteration, double currRadians, double currLength, double lastX, double lastY) {
        if (iteration == 0) return;
        double newX = lastX - (Math.sin(currRadians) * currLength);
        double newY = lastY - (Math.cos(currRadians) * currLength);
        drawBranch(iteration, lastX, lastY, newX, newY);
        draw(iteration - 1, currRadians + leftRadians, currLength * branchScale, newX, newY);
        draw(iteration - 1, currRadians - rightRadians, currLength * branchScale, newX, newY);
    }

    // draw single branch helper method
    private void drawBranch(int iteration, double x1, double y1, double x2, double y2) {
        double percent = (double)iteration / iterations;
        int colorValue = (int)(percent * 255);
        g.setColor(new Color(255 - (colorValue / 2),colorValue / 2 + 127,150,colorValue));
        //g.setColor(Color.WHITE);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1 + (int)(percent * 6)));
        g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
    }

    /** Getters and Setters **/
    public void setDegrees(double degrees) {
        setLeftDegrees(degrees);
        setRightDegrees(degrees);
    }

    public void setLeftDegrees(double degrees) {
        this.leftRadians = Math.toRadians(degrees);
    }

    public void setRightDegrees(double degrees) {
        this.rightRadians = Math.toRadians(degrees);
    }

    public double getLeftDegrees() {
        return Math.toDegrees(leftRadians);
    }

    public double getRightDegrees() {
        return Math.toDegrees(rightRadians);
    }

    public void setScale(double scale) {
        if (scale < 0) throw  new IllegalArgumentException("Scale must be greater than 0.");
        this.branchScale = scale;
    }

    public double getScale() {
        return branchScale;
    }

    public void setSize(double size) {
        if (size < 0) throw  new IllegalArgumentException("Length must be greater than 0.");
        this.size = size;
    }

    public double getSize() {
        return size;
    }

}
