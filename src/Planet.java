import java.awt.*;

/**
 * The Planet class represents a planet in a 2D space.
 * and is used to initialize such an object
 */
public class Planet  {
    private String name;
    private double xPosition;  // X-coordinate of the planet
    private double yPosition;  // Y-coordinate of the planet
    private double mass;       // Mass of the planet
    private double speed;      // Speed of the planet
    private double direction;  // Direction of movement in degrees
    private double size;       // Size of the planet
    private String image;       // Image representation of the planet

    /**
     * Constructor to initialize a Planet object.
     *
     * @param name       Label for the planet
     * @param xPosition  Initial X-coordinate
     * @param yPosition  Initial Y-coordinate
     * @param mass       Mass of the planet
     * @param speed      Speed of the planet
     * @param direction  Direction of movement in degrees
     * @param size       Size of the planet
     * @param image      Image representation of the planet
     */
    public Planet(double xPosition, double yPosition, double mass, double speed, double direction, double size, String image, String name) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.mass = mass;
        this.speed = speed;
        this.direction = direction;
        this.size = size;
        this.image = image;
        this.name = name;
    }

    // Getter methods to retrieve planet properties
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

    public String getImage() {
        return image;
    }

    public String getName() {return name;}

    // Setter methods to modify planet properties
    public void setImage(String image) {
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

    public void setName(String name) {this.name = name;}
}
