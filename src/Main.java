
public class Main {
    public UI ui;

    public Main(){

       Initialise();


    }


    public void Initialise() {
        ui = new UI();
        ui.initialiseUI();
        int tick = 0;
        while(true) {
            tick++;
            ui.solarSystem.getPlanets().get(0).setXPosition(tick);
            updateSim();
        }





    }
    public void updateSim() {
        //ui.solarPanel.removeAll();
        ui.solarPanel = ui.solarGraphics.updateBodies(ui.solarSystem, ui.solarPanel);
        ui.solarPanel.revalidate();
        ui.solarPanel.repaint();
    }
    public static void main(String[] args) {
        Main main = new Main();

    }
}