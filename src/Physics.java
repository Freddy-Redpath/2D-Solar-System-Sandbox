import java.util.ArrayList;


public class Physics {

    public double totalForceCalc(ArrayList<Planet>  planetArray, Star sun){
        for(Planet planet : planetArray){
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

            for(Planet planet2 : planetArray){
                if (planet2 != planet){
                    double x2 = planet.getXPosition();
                    double y2 = planet.getYPosition();
                    double radiusPlanetToPlanet2 = radiusCalc(x, y, x2, y2);
                    mass1 = planet2.getMass();

                    double forcePlanetToPlanet2 = newtonsLawGrav(planet, mass1, radiusPlanetToPlanet2);
                    forceList.add(forcePlanetToPlanet2);

                    double radiusDirectionPlanetToPlanet2 = radiusDirectionCalc(x, y, x2, y2);
                    forceDirectionList.add(radiusDirectionPlanetToPlanet2);
                }
            }

            for (int i = 0; i < 10; i++) {
                
            }

    }

    public double radiusDirectionCalc(double x, double y, double x2, double y2){
        double radiusDirection = Math.atan((Math.abs(x - x2) / Math.abs(y - y2)));
        return radiusDirection;
    }


    // Calculates distance between two objects based on coordinates
    public double radiusCalc(double x, double y, double x2, double y2){
        double radius = Math.sqrt(Math.pow(x - x2, 2)+Math.pow(y - y2, 2));
        return radius;
    }

    public double eccentricityCalc(Planet planet, Star sun){
        double a = KeplersThirdLaw(planet, sun);
        double radius = planet.getRadius();
        double speedAtR = visViva(planet, sun);
        double h = specificAngularMomentum(planet, sun);
        double E = specificOrbitalEnergy(planet, sun);
        double mass1 = sun.getMass();
        double G = 6.67e-11;


        double e = Math.sqrt(1+(2*E*h*h)/(G*G*mass1*mass1));

        return e; // Used to calc eccentricity but factsheet has for existing planets


    }

    public long periodCalc(Planet planet, Star sun){
        double pi = Math.PI;
        double radius = planet.getRadius();
        double speed = planet.getSpeed();
        double speedDirection = planet.getDirection();

        double T = (2*pi*radius)/speed;
        long time = (long)T;

        return time; // Orbit length in sec

    }

    public double newtonsLawGrav(Planet planet, double mass1, double radius){
        double mass2 = planet.getMass();
        double speed = planet.getSpeed();
        double speedDirection = planet.getDirection();
        if (radius == -1){
            radius = planet.getRadius();
        }
        double G = 6.67e-11;


        double force = (G*mass1*mass2)/radius; // check if correct equation for context

        return force; // Force in Newtons
    }

    public double KeplersThirdLaw(Planet planet, Star sun){
        double mass1 = sun.getMass();
        double pi = Math.PI;
        double G = 6.67e-11;
        double radius = planet.getRadius();
        double speed = planet.getSpeed();
        double speedDirection = planet.getDirection();

        double period = periodCalc(planet, sun); //check on direction? (based on angualr velocity)
        double a = Math.cbrt((period*period*G*mass1)/4*pi*pi);

        return a; // b isn't needed
    }

    public double visViva(Planet planet, Star sun){
        double mass1 = sun.getMass();
        double pi = Math.PI;
        double G = 6.67e-11;
        double radius = planet.getRadius();
        double speed = planet.getSpeed();
        double speedDirection = planet.getDirection();
        double a = KeplersThirdLaw(planet, sun);

        double speedAtR = Math.sqrt(G*mass1*((2/radius)-(1/a)));

        return speedAtR; // Used to calc speed at a given distance from star in secure orbit
    }

    public double specificAngularMomentum (Planet planet, Star sun){
        double radius1 = planet.getRadius();
        double speed = planet.getSpeed();
        double speedDirection = planet.getDirection();

        double h = radius1 * speed;

        return h;
    }

    public double specificOrbitalEnergy (Planet planet, Star sun){
        double speed = planet.getSpeed();
        double speedDirection = planet.getDirection();
        double radius = planet.getRadius();
        double mass1 = sun.getMass();
        double G = 6.67e-11;

        double E = (speed*speed)/2 - (G*mass1)/radius;
        return E;
    }


    public double[] newxandy(Planet planet, Star sun, double deltaT) {
        double G = 6.67e-11;
        double mass1 = sun.getMass();
        double mass2 = planet.getMass();
        double x = planet.getXPosition();
        double y = planet.getYPosition();
        double radius= planet.getRadius();
        double force = (G * mass1 * mass2) / (radius * radius);

        double ax = -force * (x / radius) / mass2;
        double ay = -force * (y / radius) / mass2;

        double acceleration = Math.sqrt(ax * ax + ay * ay);
        double theta2 = Math.atan2(y, x);
        double V0 = planet.getSpeed();
        double theta1 = planet.getDirection();

        double Va = acceleration * deltaT;

        double Vtx = V0 * Math.sin(theta1) + Va * Math.sin(theta2);
        double Vty = V0 * Math.cos(theta1) + Va * Math.cos(theta2);

        double Vt = Math.sqrt(Vtx * Vtx + Vty * Vty);
        double velocityDirection = Math.atan2(Vty, Vtx);

        // Position update using velocity
        double x_new = x + Vtx * deltaT;
        double y_new = y + Vty * deltaT;

        return new double[]{x_new, y_new, Vt, velocityDirection};
    }
}
