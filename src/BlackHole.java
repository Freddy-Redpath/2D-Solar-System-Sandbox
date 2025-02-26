import java.awt.*;

/**
 * The Black Hole class represents a black hole in a 2D space.
 * and is used to initialize such an object
 */
public class BlackHole {
    private String Name;
    private double xPosition;  // X-coordinate of the black hole
    private double yPosition;  // Y-coordinate of the black hole
    private double mass;       // Mass of the black hole
    private double speed;      // Speed of the black hole
    private double direction;  // Direction of movement in degrees
    private double size;       // Size of the black hole
    private Image image;       // Image representation of the black hole

    /**
     * Constructor to initialize an BlackHole object.
     *
     * @param xPosition  Initial X-coordinate
     * @param yPosition  Initial Y-coordinate
     * @param mass       Mass of the black hole
     * @param speed      Speed of the black hole
     * @param direction  Direction of movement in degrees
     * @param size       Size of the black hole
     * @param image      Image representation of the black hole
     */
    public BlackHole(double xPosition, double yPosition, double mass, double speed, double direction, double size, Image image) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.mass = mass;
        this.speed = speed;
        this.direction = direction;
        this.size = size;
        this.image = image;
    }

    // Getter methods to retrieve black hole properties
    public double getXPosition() {
        return xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }

    public double getMass() {
        return mass;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDirection() {
        return direction;
    }

    public double getSize() {
        return size;
    }

    public Image getImage() {
        return image;
    }

    // Setter methods to modify black hole properties
    public void setImage(Image image) {
        this.image = image;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public void setXPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public void setYPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }
}
