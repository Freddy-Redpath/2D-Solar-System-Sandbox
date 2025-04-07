import java.awt.*;
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

    public static void newxandy(Planet planet, Star sun, double deltaT) {
        double acceleration = planet.getForce() / planet.getMass();
        double forceAngle = planet.getForceDirection();

        Vector2D initialVelocity = new Vector2D(planet.getSpeed() * Math.cos(planet.getSpeedDirection()),planet.getSpeed() * Math.sin(planet.getSpeedDirection()));

        Vector2D accelerationVector = new Vector2D(acceleration * Math.cos(forceAngle), acceleration * Math.sin(forceAngle));

        Vector2D finalVelocity = initialVelocity.add(accelerationVector.scale(deltaT));
        Vector2D newPosition = new Vector2D(planet.getXPosition(), planet.getYPosition()).add(finalVelocity.scale(deltaT));

        double newRadius = Math.sqrt(Math.pow(newPosition.x - sun.getXPosition(), 2) + Math.pow(newPosition.y - sun.getYPosition(), 2));
        planet.setRadius(newRadius);

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
            newxandy(planet, sun, deltaT);

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


    //Functions for creating (need to be developed)

    public void eccentricityCalcOld(Planet planet, Star sun) {
        //if (planet.getCreated() == true)
        KeplersThirdLaw(planet, sun);
        visViva(planet, sun);
        double h = specificAngularMomentum(planet);
        double E = specificOrbitalEnergy(planet, sun);
        double mass1 = sun.getMass();
        double G = 6.67e-11;


        double e = Math.sqrt(1 + (2 * E * h * h) / (G * G * mass1 * mass1));
        planet.setEccentricity(e);
        // Used to calc eccentricity but factsheet has for existing planets
    }


    public static void visViva(Planet planet, Star sun) {
        double mass1 = sun.getMass();
        double G = 6.67e-11;
        double radius = planet.getRadius();
        double a = planet.getSemiMajorAxis();


        double speedAtR = Math.sqrt(G * mass1 * ((2 / radius) - (1 / a)));
        planet.setSpeed(speedAtR);
        // Used to calc speed at a given distance from star in secure orbit
    }

    public static void KeplersThirdLaw(Planet planet, Star sun) {
        double mass1 = sun.getMass();
        double pi = Math.PI;
        double G = 6.67e-11;

        double period = planet.getPeriod(); //check on direction? (based on angular velocity)
        double a = Math.cbrt((period * period * G * mass1) / (4 * pi * pi));

        planet.setSemiMajorAxis(a);
    }

    public static double newtonsLawGrav(Planet planet, double mass1, double radius) {
        double mass2 = planet.getMass();
        if (radius == -1) {
            radius = planet.getRadius();
        }
        double G = 6.67e-11;


        // check if correct equation for context
        return (G * mass1 * mass2) / radius; // Force in Newtons
    }

    public static void periodCalc(Planet planet, double mass1) {
        double pi = Math.PI;
        double radius = planet.getRadius();
        double G = 6.67e-11;
        double speed = planet.getSpeed();
        double a = (G * mass1)/(((2*G*mass1)/radius)-(speed*speed));
        planet.setSemiMajorAxis(a);
        planet.setPeriod((2 * pi) * Math.sqrt((Math.pow(a, 3)) / (G * mass1)));
    }
}