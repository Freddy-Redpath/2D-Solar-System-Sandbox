
public class Main {
    private UI ui;
    public Main(){
       Initialise();

    }
    public void Initialise() {
        ui = new UI();
        ui.initialiseUI();

    }
    public static void main(String[] args) {
        Main main = new Main();

    }
}