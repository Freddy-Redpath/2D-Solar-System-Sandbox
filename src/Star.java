/**
 * The Star class represents a star in a 2D space.
 */
public class Star extends CelestialBody {
    /**
     * Constructor to initialize a Star object.
     */
    public Star( double xPosition, double yPosition, double mass,
                double speed, double direction, double size, String image, String name) {
        super(xPosition, yPosition, mass, speed, direction, size, image, name);
    }
}
