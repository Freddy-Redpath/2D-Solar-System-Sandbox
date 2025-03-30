import java.util.ArrayList;


public class Physics {
    public static void updatePhysicsTest(){

        //for (Planet planet : Main.solarSystem.getPlanets()) {
  //Main.solarSystem.getPlanets().get(1).setXPosition(getNextPosition);
        //}
    }
    public static int[] getNextPosition(int cx, int cy, int radius, int currentX, int currentY) {
        // Calculate the current angle
        double angle = Math.toDegrees(Math.atan2(currentY - cy, currentX - cx));

        // Increment angle (e.g., move 10 degrees forward)
        angle += 1;

        // Convert angle to radians
        double radians = Math.toRadians(angle);

        // Calculate next x and y positions
        int nextX = (int)(cx + radius * Math.cos(radians));
        int nextY = (int)(cy + radius * Math.sin(radians));

        return new int[]{nextX, nextY};
    }


    public void totalForceCalc(ArrayList<Planet> planetArray, Star sun) {
        for (Planet planet : planetArray) {
            double x = planet.getXPosition();
            double y = planet.getYPosition();

            ArrayList<Double> forceList = new ArrayList<>();

            double mass1 = sun.getMass();

            double forcePlanetToSun = newtonsLawGrav(planet, mass1, -1);

            forceList.add(forcePlanetToSun);

            ArrayList<Double> forceDirectionList = new ArrayList<>();
            double x2 = sun.getXPosition();
            double y2 = sun.getYPosition();
            double radiusDirectionPlanetToSun = radiusDirectionCalc(x, y, x2, y2);
            forceDirectionList.add(radiusDirectionPlanetToSun);

            for (Planet planet2 : planetArray) {
                if (planet2 != planet) {
                    x2 = planet2.getXPosition();
                    y2 = planet2.getYPosition();
                    double radiusPlanetToPlanet2 = radiusCalc(x, y, x2, y2);
                    mass1 = planet2.getMass();

                    double forcePlanetToPlanet2 = newtonsLawGrav(planet, mass1, radiusPlanetToPlanet2);
                    forceList.add(forcePlanetToPlanet2);

                    double radiusDirectionPlanetToPlanet2 = radiusDirectionCalc(x, y, x2, y2);
                    forceDirectionList.add(radiusDirectionPlanetToPlanet2);
                }
            }

            double forceTotalX = 0, forceTotalY = 0;
            for (int i = 0; i < forceDirectionList.size(); i++) {
                forceTotalX += forceList.get(i) * Math.cos(forceDirectionList.get(i));
                forceTotalY += forceList.get(i) * Math.sin(forceDirectionList.get(i));
            }
            double forceTotal = Math.sqrt(forceTotalX * forceTotalX + forceTotalY * forceTotalY);
            planet.setForceDirection(Math.asin(forceTotalY / forceTotal));
            planet.setForce(forceTotal);
        }
    }

    public double radiusDirectionCalc(double x, double y, double x2, double y2) {
        return Math.atan((Math.abs(x - x2) / Math.abs(y - y2)));
    }

    // Calculates distance between two objects based on coordinates
    public double radiusCalc(double x, double y, double x2, double y2) {
        return Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2));
    }

    public void eccentricityCalc(Planet planet, Star sun) {
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

    public static void periodCalc(Planet planet, double mass1) {
        double pi = Math.PI;
        double radius = planet.getRadius();
        double G = 6.67e-11;
        double speed = planet.getSpeed();
        double a = (G * mass1)/(((2*G*mass1)/radius)-(speed*speed));
        planet.setSemiMajorAxis(a);
        planet.setPeriod((2 * pi) * Math.sqrt((Math.pow(a, 3)) / (G * mass1)));
    }

    public double newtonsLawGrav(Planet planet, double mass1, double radius) {
        double mass2 = planet.getMass();
        if (radius == -1) {
            radius = planet.getRadius();
        }
        double G = 6.67e-11;


        // check if correct equation for context
        return (G * mass1 * mass2) / radius; // Force in Newtons
    }

    public void KeplersThirdLaw(Planet planet, Star sun) {
        double mass1 = sun.getMass();
        double pi = Math.PI;
        double G = 6.67e-11;

        double period = planet.getPeriod(); //check on direction? (based on angular velocity)
        double a = Math.cbrt((period * period * G * mass1) / (4 * pi * pi));

        planet.setSemiMajorAxis(a);
    }

    public void visViva(Planet planet, Star sun) {
        double mass1 = sun.getMass();
        double G = 6.67e-11;
        double radius = planet.getRadius();
        double a = planet.getSemiMajorAxis();


        double speedAtR = Math.sqrt(G * mass1 * ((2 / radius) - (1 / a)));
        planet.setSpeed(speedAtR);
        // Used to calc speed at a given distance from star in secure orbit
    }

    public double specificAngularMomentum(Planet planet) {
        double radius1 = planet.getRadius();
        double speed = planet.getSpeed();

        return radius1 * speed;
    }

    public double specificOrbitalEnergy(Planet planet, Star sun) {
        double speed = planet.getSpeed();
        double radius = planet.getRadius();
        double mass1 = sun.getMass();
        double G = 6.67e-11;

        return (speed * speed) / 2 - (G * mass1) / radius;
    }


    public void newxandy(Planet planet, Star sun, double deltaT) {
        double mass1 = sun.getMass();
        double mass2 = planet.getMass();
        double x = planet.getXPosition();
        double y = planet.getYPosition();
        double radius = planet.getRadius();
        double force = newtonsLawGrav(planet, mass1, radius);

        double ax = -force * (x / radius) / mass2;
        double ay = -force * (y / radius) / mass2;

        double acceleration = Math.sqrt(ax * ax + ay * ay);
        double theta2 = planet.getForceDirection();
        double V0 = planet.getSpeed();
        double theta1 = planet.getSpeedDirection();

        double Va = acceleration * deltaT;

        double Vtx = V0 * Math.sin(theta1) + Va * Math.sin(theta2);
        double Vty = V0 * Math.cos(theta1) + Va * Math.cos(theta2);

        double Vt = Math.sqrt(Vtx * Vtx + Vty * Vty);
        double velocityDirection = Math.atan2(Vty, Vtx);

        // Position update using velocity
        double x_new = x + Vtx * deltaT;
        double y_new = y + Vty * deltaT;

        //Set values
        planet.setXPosition(x_new);
        planet.setYPosition(y_new);
        planet.setSpeed(Vt);
        planet.setSpeedDirection(velocityDirection);
    }

    public void runSimulation(SolarSystem solarSystem) {


        Star sun = solarSystem.getStars().get(0);
        ArrayList<Planet> planets = solarSystem.getPlanets();

        totalForceCalc(planets, sun);
        for (Planet planet : planets) {
            eccentricityCalc(planet, sun);
            newxandy(planet, sun, 1.0);
        }

    }

    public Physics(SolarSystem solarSystem) {
        runSimulation(solarSystem);
    }

}


