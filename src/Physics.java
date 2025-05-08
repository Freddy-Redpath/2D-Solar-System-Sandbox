import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

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

    private static void handleCollision(SolarSystem solarSystem) {
        ArrayList<Planet> toDelete = new ArrayList<>();
        for (Planet planet1 : solarSystem.getPlanets()) {
            for (Planet planet2 : solarSystem.getPlanets()) {
                double dx = (planet1.getXPosition() - planet2.getXPosition())/5e8;
                double dy = (planet1.getYPosition() - planet2.getYPosition())/5e8;
                double distance = Math.sqrt(dx * dx + dy * dy);
                double dir1 = planet1.getSpeedDirection();
                double dir2 = planet2.getSpeedDirection();
                if (planet2 == planet1) {
                    break;
                }

                if (distance < (64)) {

                        double newMass = planet1.getMass() + planet2.getMass();
                        double vx = (planet1.getSpeed() * Math.cos(dir1) * planet1.getMass() +
                                planet2.getSpeed() * Math.cos(dir2) * planet2.getMass()) / newMass;
                        double vy = (planet1.getSpeed() * Math.sin(dir1) * planet1.getMass() +
                                planet2.getSpeed() * Math.sin(dir2) * planet2.getMass()) / newMass;
                        if (planet1.getMass()*planet1.getSpeed() > planet2.getMass() * planet2.getSpeed()) {
                            planet1.setMass(newMass);
                            planet1.setSpeed(Math.sqrt(vx * vx + vy * vy));
                            planet1.setSpeedDirection(Math.atan2(vy, vx));

                            planet1.setSize(planet1.getSize() * 1.2);
                            generateDebris(planet1.getXPosition(), planet1.getYPosition());
                            generateDebris(planet2.getXPosition(), planet2.getYPosition());
                            toDelete.add(planet2);
                        }else{
                            planet2.setMass(newMass);
                            planet2.setSpeed(Math.sqrt(vx * vx + vy * vy));
                            planet2.setSpeedDirection(Math.atan2(vy, vx));

                            planet2.setSize(planet1.getSize() * 1.2);
                            generateDebris(planet1.getXPosition(), planet1.getYPosition());
                            generateDebris(planet2.getXPosition(), planet2.getYPosition());
                            toDelete.add(planet1);
                        }

                    }

            }
            for (Star star : solarSystem.getStars()) {
                double dx = (planet1.getXPosition() - star.getXPosition())/5e8;
                double dy = (planet1.getYPosition() - star.getYPosition())/5e8;
                double distance = Math.sqrt(dx * dx + dy * dy);
                double dir1 = planet1.getSpeedDirection();
                double dir2 = 0;


                if (distance < (64)) {
                    double angleBetween = Math.atan2(dy, dx); // Angle from planet1 to planet2 (collision axis)

                    double relativeAngle1 = Math.abs(angleBetween - planet1.getSpeedDirection());
                    double relativeAngle2 = Math.abs(angleBetween);



                    boolean isHeadOn = (relativeAngle1 > Math.PI * 0.75 && relativeAngle2 < Math.PI * 1.25);




                    if (isHeadOn) {
                        double newMass = planet1.getMass() + star.getMass();
                        double vx = (planet1.getSpeed() * Math.cos(dir1) * planet1.getMass() +
                                star.getSpeed() * Math.cos(dir2) * star.getMass()) / newMass;
                        double vy = (planet1.getSpeed() * Math.sin(dir1) * planet1.getMass() +
                                star.getSpeed() * Math.sin(dir2) * star.getMass()) / newMass;

                        planet1.setMass(newMass);
                        planet1.setSpeed(Math.sqrt(vx * vx + vy * vy));
                        planet1.setSpeedDirection(Math.atan2(vy, vx));

                        planet1.setSize(planet1.getSize() * 1.2);
                        generateDebris(planet1.getXPosition(), planet1.getYPosition());
                        generateDebris(star.getXPosition(), star.getYPosition());
                        toDelete.add(planet1);

                    } else {

                        double lostMass = planet1.getMass() * 0.1;
                        planet1.setMass(planet1.getMass() - lostMass);
                        star.setMass(star.getMass() - lostMass);


                        planet1.setSpeedDirection(-planet1.getSpeedDirection());

                    }
                }


            }
        }
        for (Planet planet : toDelete) {
            solarSystem.removePlanet(planet);
            Main.ui.refreshUI();
        }
    }

    private static void generateDebris(double x, double y) {
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            double angle = rand.nextDouble() * 2 * Math.PI;
            double speed = rand.nextDouble() * 3e9; // adjust for visual effect
            double dx = speed * Math.cos(angle);
            double dy = speed * Math.sin(angle);
            int lifespan = 500 + rand.nextInt(300);
            int size = 10 + rand.nextInt(10);
            Color color = Color.GRAY;

            Main.debrisList.add(new Debris(x, y, dx, dy, size, size, color));
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

    public static void newxandy(Planet planet,Star sun,  double deltaT) {
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
        handleCollision(solarSystem);

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