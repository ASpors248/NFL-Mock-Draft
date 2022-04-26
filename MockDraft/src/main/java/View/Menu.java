package View;

public class Menu {

    public void startMenu() {
        System.out.println("(S) Start New Mock Draft\n(E) Exit\n");
    }

    public void pickMenu() {
        System.out.println("(D) Display Available Prospects\n(P) Pick a Prospect\n(T) Trade Pick\n(F) Finish Draft\n");
    }
    public void displayProspectsMenu() {
        System.out.println("Display Prospects: \n(1) By Position\n(2) By School\n(3) All Remaining Prospects\n(4) Back");
    }
}
