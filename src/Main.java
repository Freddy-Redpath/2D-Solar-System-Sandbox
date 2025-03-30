import java.util.*;

public class Main {
    public static SolarSystem solarSystem;
    public UI ui;
    public static boolean simPaused;
    public Main(){
        solarSystem = new SolarSystem();
        simPaused = false;
        solarSystem.addOurSolarSystem();
        Physics physics = new Physics();
        Initialise();
        Timer timer = new Timer();
        int interval = 30;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!simPaused) {
                    for (int i = 0; i < solarSystem.getCelestialBodies().size(); i++) {
                        for (int j = i + 1; j < solarSystem.getCelestialBodies().size(); j++) {
                            physics.applyGravity(solarSystem.getCelestialBodies().get(i), solarSystem.getCelestialBodies().get(j));
                        }
                    }
                    for (CelestialBody planet : solarSystem.getCelestialBodies()) {
                        physics.updatePosition(planet, 1.0);
                    }
                    ui.solarPanel.repaint();
                }else {
                System.out.print("paused");
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, interval);
    }
    public void Initialise() {

        solarSystem = new SolarSystem();
        solarSystem.addOurSolarSystem();
        ui = new UI();
        ui.initialiseUI();


    }
    public static void main(String[] args) {
        Main main = new Main();

    }
}