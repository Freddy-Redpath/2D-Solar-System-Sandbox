import java.util.ArrayList;

public class SolarSystem {
    //ArrayLists to hold the current orbital bodies
    public ArrayList<Planet> planetArray = new ArrayList<Planet>();

    public ArrayList<Star> starArray = new ArrayList<Star>();

    public ArrayList<Asteroid> asteroidArray = new ArrayList<>();

    public ArrayList<BlackHole> blackHoleArray = new ArrayList<>();

    //These functions add and remove bodies from the corresponding ArrayLists
    public void addPlanet(Planet planet) {
        planetArray.add(planet);
    }
    public void removePlanet(Planet planet) {
        planetArray.remove(planet);
    }

    public void addStar(Star star) {
        starArray.add(star);
    }
    public void removeStar(Star star) {
        starArray.remove(star);
    }

    public void addAsteroid(Asteroid asteroid) {
        asteroidArray.add(asteroid);
    }
    public void removeAsteroid(Asteroid asteroid) {
        asteroidArray.remove(asteroid);
    }

    public void addBlackHole(BlackHole blackhole){
        blackHoleArray.add(blackhole);
    }
    public void removeBlackHole(BlackHole blackhole){
        blackHoleArray.remove(blackhole);
    }
}
