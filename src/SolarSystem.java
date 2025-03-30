import java.awt.*;
import java.util.ArrayList;

public class SolarSystem {
    public static ArrayList<CelestialBody> CelestialBodies = new ArrayList<>();

    public SolarSystem() {}

    public void addSun() {
        Star theSun = new Star(640, 400,1.989e30 , 0, 0, 50, "src/images/Sun.png", "sun");
        addBody(theSun);
    }

    public void addMercury() {
        Planet mercury = new Planet(640, 400, 5.97, 1.5, Math.PI / 2, 5, "src/images/Mercury.png", "mercury");
        addBody(mercury);
    }

    public void addVenus() {
        Planet venus = new Planet(400, 500, 1, 1.2, Math.PI / 2, 12, "src/images/Venus.png", "venus");
        addBody(venus);
    }

    public void addEarth() {
        Planet earth = new Planet(640+149e6, 400, 1, 1.0, Math.PI / 2, 15, "src/images/Earth.png", "earth");
        addBody(earth);
    }

    public void addMars() {
        Planet mars = new Planet(500, 800, 5.97, 0.8, Math.PI / 2, 10, "src/images/Mars.png", "mars");
        addBody(mars);
    }

    public void addJupiter() {
        Planet jupiter = new Planet(600, 500, 1, 0.5, Math.PI / 2, 30, "src/images/Jupiter.png", "jupiter");
        addBody(jupiter);
    }

    public void addSaturn() {
        Planet saturn = new Planet(700, 500, 1, 0.4, Math.PI / 2, 25, "src/images/Saturn.png", "saturn");
        addBody(saturn);
    }

    public void addUranus() {
        Planet uranus = new Planet(800, 500, 1, 0.3, Math.PI / 2, 20, "src/images/Uranus.png", "uranus");
        addBody(uranus);
    }

    public void addNeptune() {
        Planet neptune = new Planet(900, 500, 1, 0.2, Math.PI / 2, 18, "src/images/Neptune.png", "neptune");
        addBody(neptune);
    }

    public void addPluto() {
        Planet pluto = new Planet(1000, 500, 1, 0.1, Math.PI / 2, 6, "src/images/Pluto.png", "pluto");
        addBody(pluto);
    }

    public void addOurSolarSystem() {
        addSun();
        //addMercury();
        //addVenus();
        addEarth();
        //addMars();
        //addJupiter();
        //addSaturn();
        //addUranus();
       // addNeptune();
        //addPluto();
    }

    public void addBody(CelestialBody bodyToAdd) {
        this.CelestialBodies.add(bodyToAdd);
    }
    public void removeBody(CelestialBody toRemove) {
        this.CelestialBodies.remove(toRemove);
    }




    public ArrayList<CelestialBody> getCelestialBodies() {
        return this.CelestialBodies;
    }
}
