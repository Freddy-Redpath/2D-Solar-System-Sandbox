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

    // radius(done), eccentricity, period, force, force_direction, semi_major_axis,size
    // Functions for adding pre-set planets from our solar system
    public void addSun(){
        Star theSun = new Star(0.0, 0.0, 1.989e30, 0.0, 0.0, 1.3927e6, "src/images/Sun.png", "sun");
        addStar(theSun);
    }

    public void addMercury() {
        Planet mercury = new Planet(69.8E9, 0.0, 0.330e24, 38864.7570, 0.0, 69.8E9, 0.206, 7603200.0, 0.0, 0.0, 5.7909E10, 4879.0, "src/images/Mercury.png", "mercury");
        addPlanet(mercury);
    }

    public void addVenus() {
        Planet venus = new Planet(108.9E9, 0.0, 4.87e24, 34790.1818, 0.0, 108.9E9, 0.007, 19408080.0, 0.0, 0.0, 1.0821E11, 12104.0, "src/images/Venus.png", "venus");
        addPlanet(venus);
    }

    public void addEarth(){
        Planet earth = new Planet(152.1E9, 0.0, 5.97e24, 29295.1810, 0.0,152.1E9,0.017,31556926.0,0.0,0.0,1.495978707E11, 12756.0 , "src/images/Earth.png", "earth");
        addPlanet(earth);
    }

    public void addMars() {
        Planet mars = new Planet(249.3E9, 0.0, 0.642e24, 21974.9314, 0.0, 249.3E9, 0.093, 2359200.0, 0.0, 0.0, 2.2794E11, 6792.0, "src/images/Mars.png", "mars");
        addPlanet(mars);
    }

    public void addJupiter() {
        Planet jupiter = new Planet(816.4E9, 0.0, 1898e24, 12434.5425, 0.0, 816.4E9, 0.049, 59356800.0, 0.0, 0.0, 7.7830E11, 142984.0, "src/images/Jupiter.png", "jupiter");
        addPlanet(jupiter);
    }

    public void addSaturn() {
        Planet saturn = new Planet(1506.5E9, 0.0, 568e24, 9100.3757, 0.0, 1506.5E9, 0.056, 374942400.0, 0.0, 0.0, 1.4325E12, 120536.0, "src/images/Saturn.png", "saturn");
        addPlanet(saturn);
    }

    public void addUranus() {
        Planet uranus = new Planet(3001.4E9, 0.0, 86.8e24, 6484.6825, 0.0, 3001.4E9, 0.046, 928540800.0, 0.0, 0.0, 2.8710E12, 51118.0, "src/images/Uranus.png", "uranus");
        addPlanet(uranus);
    }

    public void addNeptune() {
        Planet neptune = new Planet(4558.9E9, 0.0, 102e24, 5383.9657, 0.0, 4558.9E9, 0.010, 2642889600.0, 0.0, 0.0, 4.4984E12, 49528.0, "src/images/Neptune.png", "neptune");
        addPlanet(neptune);
    }

    public void addPluto() {
        Planet pluto = new Planet(7375.9E9, 0.0, 0.0130e24, 3676.9789, 0.0, 7375.9E9, 0.248, 5167920000.0, 0.0, 0.0, 5.9064E12, 2376.0, "src/images/Pluto.png", "pluto");
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