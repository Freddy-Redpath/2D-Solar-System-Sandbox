import java.awt.*;

/**
 * The Asteroid class represents an asteroid in a 2D space.
 * and is used to initialize such an object
 */
public class Asteroid {
    private double xPosition;  // X-coordinate of the asteroid
    private double yPosition;  // Y-coordinate of the asteroid
    private double mass;       // Mass of the asteroid
    private double speed;      // Speed of the asteroid
    private double direction;  // Direction of movement in degrees
    private double size;       // Size of the asteroid
    private Image image;       // Image representation of the asteroid

    /**
     * Constructor to initialize an Asteroid object. hello
     *
     * @param xPosition  Initial X-coordinate
     * @param yPosition  Initial Y-coordinate
     * @param mass       Mass of the asteroid
     * @param speed      Speed of the asteroid
     * @param direction  Direction of movement in degrees
     * @param size       Size of the asteroid
     * @param image      Image representation of the asteroid
     */
    public Asteroid(double xPosition, double yPosition, double mass, double speed, double direction, double size, Image image) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.mass = mass;
        this.speed = speed;
        this.direction = direction;
        this.size = size;
        this.image = image;
    }

    // Getter methods to retrieve asteroid properties
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

    // Setter methods to modify asteroid properties
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
