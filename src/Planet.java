import java.awt.*;

/**
 * The Planet class represents a planet in a 2D space.
 * and is used to initialize such an object
 */
public class Planet {
    private String name;
    private double xPosition;  // X-coordinate of the planet
    private double yPosition;  // Y-coordinate of the planet
    private double mass;       // Mass of the planet
    private double speed;      // Speed of the planet
    private double speed_direction;  // Direction of movement in degrees
    private double radius;           // Distance of planet from the sun
    private double eccentricity;     // Eccentricity value of the orbit of the planet
    private double period;           // orbit time in seconds
    private double force;            // Gravitational Force
    private double force_direction;  // Direction of Gravitational force
    private double semi_major_axis;  // Semi Major axis of the orbit of the planet
    private double size;             // Size of the planet
    private String image;            // Image representation of the planet

    // radius:0.0, eccentricity:0.0, period:0.0, force:0.0, force_direction:0.0, semi_major_axis:0.0
    /**
     * Constructor to initialize a Planet object.
     *
     * @param name       Label for the planet
     * @param xPosition  Initial X-coordinate
     * @param yPosition  Initial Y-coordinate
     * @param mass       Mass of the planet
     * @param speed      Speed of the planet
     * @param direction  Direction of movement in degrees
     * @param radius     Distance between Sun and the planet
     * @param size       Size of the planet
     * @param image      Image representation of the planet
     */
    public Planet(double xPosition, double yPosition, double mass, double speed, double speed_direction, double radius, double eccentricity, double period, double force,double force_direction,double semi_major_axis , double size, String image, String name) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.mass = mass;
        this.speed = speed;
        this.speed_direction = speed_direction;
        this.radius = radius;
        this.eccentricity = eccentricity;
        this.period = period;
        this.force = force;
        this.force_direction = force_direction;
        this.semi_major_axis = semi_major_axis;
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

    public double getSpeedDirection() {
        return speed_direction;
    }

    public double getRadius() {return radius;}

    public double getEccentricity() {return eccentricity;}

    public double getPeriod() {return period;}

    public double getForce() {return force;}

    public double getForce_direction() {return force_direction;}

    public double getSemiMajorAxis() {return semi_major_axis;}

    public double getSize() {return size;}

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

    public void setSpeedDirection(double speed_direction) {
        this.speed_direction = speed_direction;
    }

    public void setRadius(double radius) {this.radius = radius;}

    public void setEccentricity(double eccentricit) {this.eccentricity = eccentricit;}

    public void setPeriod(double period) {this.period = period;}

    public void setForce(double force) {this.force = force;}

    public void setForce_direction(double force_direction) {this.force_direction = force_direction;}

    public  void setSemiMajorAxis(double semi_major_axis) {this.semi_major_axis = semi_major_axis;}

    public void setName(String name) {this.name = name;}
}
