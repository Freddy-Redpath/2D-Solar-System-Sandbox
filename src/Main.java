import java.util.ArrayList;

public class Main {
    private UI ui;
    private ArrayList<Planet> planets;

    // Constructor to initialize UI
    public Main() {
        Initialise();
        planets = new ArrayList<>();
    }

    // Initialise method
    public void Initialise() {
        ui = new UI();
        ui.Initialisewindow();

        //planets.add(new Planet(0,0,3, 0, 12, 12, null));

    }


    public static void main(String[] args) {
        Main main = new Main(); // Instantiates Main and calls Initialise in the constructor
    }
}
