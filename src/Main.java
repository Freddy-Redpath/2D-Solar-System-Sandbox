
public class Main {
    public UI ui;
    private SolarSystem solarSystem;
    public Main(){

       Initialise();

    }
    public void Initialise() {
        ui = new UI();
        ui.initialiseUI();
        solarSystem = new SolarSystem(ui);
        solarSystem.addEarth();
        solarSystem.addSun();



    }
    public static void main(String[] args) {
        Main main = new Main();

    }
}