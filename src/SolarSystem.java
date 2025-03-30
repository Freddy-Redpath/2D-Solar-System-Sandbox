import java.awt.*;
import java.util.ArrayList;

public class SolarSystem {
    // ArrayLists to hold the current orbital bodies
    public ArrayList<CelestialBody> CelestialBodies = new ArrayList<CelestialBody>();

    //public ArrayList<Planet> planetArray = new ArrayList<Planet>();

    //public ArrayList<Star> starArray = new ArrayList<Star>();

   // public ArrayList<Asteroid> asteroidArray = new ArrayList<Asteroid>();

   // public ArrayList<BlackHole> blackHoleArray = new ArrayList<BlackHole>();

    // Constructor, nothing is in here as we can instantiate an empty solar system
    public SolarSystem(){
    }

    // radius(done), eccentricity, period, force, force_direction, semi_major_axis,size
    // Functions for adding pre-set planets from our solar system
    public void addSun(){
        Star theSun = new Star(0.0, 0.0, 1.989e30, 0, 0, 1.3927e6, "src/images/Sun.png", "sun");
        addStar(theSun);
    }

    public void addMercury() {
        Planet mercury = new Planet(69, 0.0, 1, 1, 0.0, 69.818E9,  "src/images/Mercury.png", "mercury");
        addPlanet(mercury);
    }

    public void addVenus() {
        Planet venus = new Planet(25, 0.0, 1, 1, 10, 108.9E9,  "src/images/Venus.png", "venus");
        addPlanet(venus);
    }

    public void addEarth(){
        Planet earth = new Planet(20, 0.0, 1, 1, 0.0,152.1E9,"src/images/Earth.png", "earth");
        addPlanet(earth);
    }

    public void addMars() {
        Planet mars = new Planet(250, 0.0, 1, 1, 0.0, 249.3E9, "src/images/Mars.png", "mars");
        addPlanet(mars);
    }

    public void addJupiter() {
        Planet jupiter = new Planet(10, 0.0, 1, 2.3, 0.0, 816.4E9,  "src/images/Jupiter.png", "jupiter");
        addPlanet(jupiter);
    }

    public void addSaturn() {
        Planet saturn = new Planet(150, 0.0, 1, 0.5, 0.0, 1506.5E9, "src/images/Saturn.png", "saturn");
        addPlanet(saturn);
    }

    public void addUranus() {
        Planet uranus = new Planet(3001.4E9, 0.0, 1, 3, 0.0, 3001.4E9, "src/images/Uranus.png", "uranus");
        addPlanet(uranus);
    }

    public void addNeptune() {
        Planet neptune = new Planet(100, 0.0, 1, 1, 0.0, 4558.9E9, "src/images/Neptune.png", "neptune");
        addPlanet(neptune);
    }

    public void addPluto() {
        Planet pluto = new Planet(50, 0.0, 1, 2, 0.0, 7375.9E9, "src/images/Pluto.png", "pluto");
        addPlanet(pluto);
    }

    // Function for adding all of our solar system at once
    public void addOurSolarSystem(){
        addSun();
        addMercury();
        addVenus();
        addEarth();
        addMars();
        addJupiter();
        addSaturn();
        addUranus();
        addNeptune();
        addPluto();
    }

    // Functions for adding created bodies to the relevant ArrayLists
    public void addPlanet(Planet planet) {
        this.CelestialBodies.add(planet);
    }
    public void removePlanet(Planet planet) {
        this.CelestialBodies.remove(planet);
    }

    public void addStar(Star star) {
        this.CelestialBodies.add(star);
    }
    public void removeStar(Star star) {
        this.CelestialBodies.remove(star);
    }

   /* public void addAsteroid(Asteroid asteroid) {
        this.CelestialBodies.add(asteroid);
    }
    public void removeAsteroid(Asteroid asteroid) {
        this.CelestialBodies.remove(asteroid);
    }

    public void addBlackHole(BlackHole blackhole){
        this.CelestialBodies.add(blackhole);
    }
    public void removeBlackHole(BlackHole blackhole){
        this.CelestialBodies.remove(blackhole);
    }
*/
    /*
    Getter functions for returning objects in Solar System
    Can change this to return individual planets quite easily, either by giving the function the name attribute
    or the index
    */
    public ArrayList<CelestialBody> getCelestialBodies(){return this.CelestialBodies;}
}