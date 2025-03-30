/**
 * The Planet class represents a planet in a 2D space.
 */
public class Planet extends CelestialBody {
    private double radius, eccentricity, period, force, forceDirection, semiMajorAxis;

    /**
     * Constructor to initialize a Planet object.
     */
    public Planet( double xPosition, double yPosition, double mass, double speed,
                  double speedDirection, double radius, double eccentricity, double period,
                  double force, double forceDirection, double semiMajorAxis, double size, String image,String name) {
        super(xPosition, yPosition, mass, speed, speedDirection, size, image, name );
        this.radius = radius;
        this.eccentricity = eccentricity;
        this.period = period;
        this.force = force;
        this.forceDirection = forceDirection;
        this.semiMajorAxis = semiMajorAxis;
    }

    // Getters
    public double getRadius() { return radius; }
    public double getEccentricity() { return eccentricity; }
    public double getPeriod() { return period; }
    public double getForce() { return force; }
    public double getForceDirection() { return forceDirection; }
    public double getSemiMajorAxis() { return semiMajorAxis; }

    // Setters
    public void setRadius(double radius) { this.radius = radius; }
    public void setEccentricity(double eccentricity) { this.eccentricity = eccentricity; }
    public void setPeriod(double period) { this.period = period; }
    public void setForce(double force) { this.force = force; }
    public void setForceDirection(double forceDirection) { this.forceDirection = forceDirection; }
    public void setSemiMajorAxis(double semiMajorAxis) { this.semiMajorAxis = semiMajorAxis; }
}
