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
                    Planet earth = solarSystem.getPlanets().get(0);
                    Planet planet2 = solarSystem.getPlanets().get(1);
                    Planet planet3 = solarSystem.getPlanets().get(2);
                    Star sun = solarSystem.getStars().getFirst();
                    int[] newXY1 = Physics.getNextPosition((int)sun.getXPosition(), (int)sun.getYPosition(), 200,(int) earth.getXPosition(),(int)earth.getYPosition());
                    earth.setXPosition(newXY1[0]);
                    earth.setYPosition(newXY1[1]);
                    int[] newXY2 = Physics.getNextPosition((int)sun.getXPosition(), (int)sun.getYPosition(), 275,(int) planet2.getXPosition(),(int)planet2.getYPosition());
                    planet2.setXPosition(newXY2[0]);
                    planet2.setYPosition(newXY2[1]);
                    int[] newXY3 = Physics.getNextPosition((int)sun.getXPosition(), (int)sun.getYPosition(), 350,(int) planet2.getXPosition(),(int)planet2.getYPosition());
                    planet3.setXPosition(newXY3[0]);
                    planet3.setYPosition(newXY3[1]);
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