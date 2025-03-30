

import java.util.*;

public class Main {
    public static SolarSystem solarSystem;
    public static  UI ui;
    public static int interval = 30; // Default simulation interval (ms)
    public static boolean simPaused;
    public static Timer timer;
    private static Physics physics = new Physics(); // Ensure physics object is available

    public Main(){
        solarSystem = new SolarSystem();
        simPaused = false;
        solarSystem.addOurSolarSystem();
        ui = new UI();
        ui.initialiseUI();
        startTimer();


    }
    public static void startTimer(){
        System.out.println(interval);
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
        timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!simPaused) {
                    for (int i = 0; i < solarSystem.getCelestialBodies().size(); i++) {
                        for (int j = i + 1; j < solarSystem.getCelestialBodies().size(); j++) {
                            physics.applyGravity(i,j);

                        }
                    }
                    for (CelestialBody planet : solarSystem.getCelestialBodies()) {

                        double[] newXY = physics.updatePosition(planet, 1.0);
                        planet.setXPosition(newXY[0]);
                        planet.setYPosition(newXY[1]);

                    }
                    ui.solarPanel.repaint();
                }else {
                    System.out.print("paused");
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, interval);

    }

    public static void main(String[] args) {
        Main main = new Main();

    }
}

