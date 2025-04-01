import java.util.ArrayList;

public class Physics {

    private static final double G = 6.67e-11; // Gravitational constant

    public static void totalForceCalc(ArrayList<Planet> planetArray, Star sun) {
        for (Planet planet : planetArray) {
            Vector2D totalForce = new Vector2D(0, 0);

            // Force due to Sun
            totalForce = totalForce.add(gravitationalForce(planet, sun.getMass(), sun.getXPosition(), sun.getYPosition()));
            // Forces due to other planets
            for (Planet otherPlanet : planetArray) {
                if (otherPlanet != planet) {
                    totalForce = totalForce.add(gravitationalForce(planet, otherPlanet.getMass(), otherPlanet.getXPosition(), otherPlanet.getYPosition()));
                }
            }

            // Set force values on the planet
            if (planet.getName().equals("mercury")) {
                System.out.println(totalForce.x + " " + totalForce.y);
            }
            planet.setForce(totalForce.magnitude());
            planet.setForceDirection(totalForce.angle());
        }
    }

    private static Vector2D gravitationalForce(Planet planet, double mass, double x2, double y2) {
        double x1 = planet.getXPosition();
        double y1 = planet.getYPosition();
        double distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));

        if (distance == 0) return new Vector2D(0, 0); // Avoid divide by zero

        double force = (G * planet.getMass() * mass) / (distance * distance);
        double angle = Math.atan2(y2 - y1, x2 - x1);

        return new Vector2D(force * Math.cos(angle), force * Math.sin(angle));
    }

    public static void newxandy(Planet planet, double deltaT) {
        double acceleration = planet.getForce() / planet.getMass();
        double forceAngle = planet.getForceDirection();

        Vector2D initialVelocity = new Vector2D(planet.getSpeed() * Math.cos(planet.getSpeedDirection()),planet.getSpeed() * Math.sin(planet.getSpeedDirection()));

        Vector2D accelerationVector = new Vector2D(acceleration * Math.cos(forceAngle), acceleration * Math.sin(forceAngle));

        Vector2D finalVelocity = initialVelocity.add(accelerationVector.scale(deltaT));
        Vector2D newPosition = new Vector2D(planet.getXPosition(), planet.getYPosition()).add(finalVelocity.scale(deltaT));

        planet.setXPosition(newPosition.x);
        planet.setYPosition(newPosition.y);
        planet.setSpeed(finalVelocity.magnitude());
        planet.setSpeedDirection(finalVelocity.angle());
    }

    public static void runSimulation(SolarSystem solarSystem, double deltaT) {
        Star sun = solarSystem.getStars().get(0);
        ArrayList<Planet> planets = solarSystem.getPlanets();

        totalForceCalc(planets, sun);

        for (Planet planet : planets) {
            eccentricityCalc(planet, sun);
            newxandy(planet, deltaT);
        }
    }

    public static void eccentricityCalc(Planet planet, Star sun) {
        double h = specificAngularMomentum(planet);
        double E = specificOrbitalEnergy(planet, sun);
        double e = Math.sqrt(1 + (2 * E * h * h) / (G * G * sun.getMass() * sun.getMass()));

        planet.setEccentricity(e);
    }

    public static double specificAngularMomentum(Planet planet) {
        return planet.getRadius() * planet.getSpeed();
    }

    public static double specificOrbitalEnergy(Planet planet, Star sun) {
        return (planet.getSpeed() * planet.getSpeed()) / 2 - (G * sun.getMass()) / planet.getRadius();
    }
}