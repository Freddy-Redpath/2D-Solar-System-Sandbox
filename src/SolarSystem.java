import java.awt.*;
import java.util.ArrayList;

public class SolarSystem {
    // ArrayLists to hold the current orbital bodies
    public ArrayList<Planet> planetArray = new ArrayList<Planet>();

    public ArrayList<Star> starArray = new ArrayList<Star>();

    public ArrayList<Asteroid> asteroidArray = new ArrayList<Asteroid>();

    public ArrayList<BlackHole> blackHoleArray = new ArrayList<BlackHole>();

    // Constructor, nothing is in here as we can instantiate an empty solar system
    public SolarSystem(){
    }


    // Functions for adding pre-set planets from our solar system
    public void addSun(){
        Star theSun = new Star(0.0, 0.0, 1.989e30, 0.0, 0.0, 1.3927e6, "src/images/Sun.png", "sun");
        addStar(theSun);
    }
    public void addEarth(){
        Planet earth = new Planet(0.0, 0.0, 5.97e24, 0.0, 0.0, 12756.0, "src/images/Earth.png", "earth");
        addPlanet(earth);
    }
    public void addMercury(){
        Planet mercury = new Planet(0.0, 0.0, 0.330e24, 0.0, 0.0, 4879.0, "src/images/Mercury.png", "mercury");
        addPlanet(mercury);
    }
    public void addVenus(){
        Planet venus = new Planet(0.0, 0.0, 4.87e24, 0.0, 0.0, 12104.0, "src/images/Venus.png", "venus");
        addPlanet(venus);
    }
    public void addMars(){
        Planet mars = new Planet(0.0, 0.0, 0.642e24, 0.0, 0.0, 6792.0, "src/images/Mars.png", "mars");
        addPlanet(mars);
    }
    public void addJupiter(){
        Planet jupiter = new Planet(0.0, 0.0, 1898e24, 0.0, 0.0, 142984.0, "src/images/Jupiter.png", "jupiter");
        addPlanet(jupiter);
    }
    public void addSaturn(){
        Planet saturn = new Planet(0.0, 0.0, 586e24, 0.0, 0.0, 120536.0, "src/images/Saturn.png", "saturn");
        addPlanet(saturn);
    }
    public void addUranus(){
        Planet uranus = new Planet(0.0, 0.0, 86.8e24, 0.0, 0.0, 51118.0, "src/images/Uranus.png", "uranus");
        addPlanet(uranus);
    }
    public void addNeptune(){
        Planet neptune = new Planet(0.0, 0.0, 102e24, 0.0, 0.0, 49528.0, "src/images/Neptune.png", "neptune");
        addPlanet(neptune);
    }
    public void addPluto(){
        Planet pluto = new Planet(0.0, 0.0, 0.0130e24, 0.0, 0.0, 2376.0, "src/images/Pluto.png", "pluto");
        addPlanet(pluto);
    }

    // Function for adding all of our solar system at once
    public void addOurSolarSystem(){
        addSun();
        addEarth();
        addMercury();
        addVenus();
        addMars();
        addJupiter();
        addSaturn();
        addUranus();
        addNeptune();
        addPluto();
    }

    // Functions for adding created bodies to the relevant ArrayLists
    public void addPlanet(Planet planet) {
        this.planetArray.add(planet);
    }
    public void removePlanet(Planet planet) {
        this.planetArray.remove(planet);
    }

    public void addStar(Star star) {
        this.starArray.add(star);
    }
    public void removeStar(Star star) {
        this.starArray.remove(star);
    }

    public void addAsteroid(Asteroid asteroid) {
        this.asteroidArray.add(asteroid);
    }
    public void removeAsteroid(Asteroid asteroid) {
        this.asteroidArray.remove(asteroid);
    }

    public void addBlackHole(BlackHole blackhole){
        this.blackHoleArray.add(blackhole);
    }
    public void removeBlackHole(BlackHole blackhole){
        this.blackHoleArray.remove(blackhole);
    }

    /*
    Getter functions for returning objects in Solar System
    Can change this to return individual planets quite easily, either by giving the function the name attribute
    or the index
    */
    public ArrayList<Planet> getPlanets() {
        return planetArray;
    }
    public ArrayList<Star> getStars() {
        return starArray;
    }
    public ArrayList<Asteroid> getAsteroids() {
        return asteroidArray;
    }
    public ArrayList<BlackHole> getBlackHoles() {
        return blackHoleArray;
    }
}
