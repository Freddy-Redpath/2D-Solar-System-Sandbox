public class Physics {

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
        double direction = planet.getDirection();

        double T = (2*pi*radius)/speed;
        long time = (long)T;

        return time; // Orbit length in sec

    }

    public double forceCalc(Planet planet, Star sun){
        double mass1 = sun.getMass();
        double mass2 = planet.getMass();
        double speed = planet.getSpeed();
        double direction = planet.getDirection();
        double radius = planet.getRadius();
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
        double direction = planet.getDirection();

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
        double direction = planet.getDirection();
        double a = KeplersThirdLaw(planet, sun);

        double speedAtR = Math.sqrt(G*mass1*((2/radius)-(1/a)));

        return speedAtR; // Used to calc speed at a given distance from star in secure orbit
    }

    public double specificAngularMomentum (Planet planet, Star sun){
        double radius1 = planet.getRadius();
        double speed = planet.getSpeed();
        double direction = planet.getDirection();

        double h = radius1 * speed;

        return h;
    }

    public double specificOrbitalEnergy (Planet planet, Star sun){
        double speed = planet.getSpeed();
        double direction = planet.getDirection();
        double radius = planet.getRadius();
        double mass1 = sun.getMass();
        double G = 6.67e-11;

        double E = (speed*speed)/2 - (G*mass1)/radius;
        return E;
    }


}
