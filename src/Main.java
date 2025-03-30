import java.util.*;

public class Main {
    public static SolarSystem solarSystem;
    public UI ui;
    public static boolean simPaused;
    public Main(){
        solarSystem = new SolarSystem();
        simPaused = false;
        solarSystem.addOurSolarSystem();
        Initialise();
        Timer timer = new Timer();
        int interval = 30;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!simPaused) {
                    for (int i = 0; i <= solarSystem.CelestialBodies.size(); i++) {
                        CelestialBody cb = solarSystem.CelestialBodies.get(i);
                        int[] newXY = Physics.
                        cb.setXPosition();
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