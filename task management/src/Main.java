
import controller.CombinedController;

public class Main {
    public static void main(String[] args) {
        start();
    }
    public static void start() {
        CombinedController controller = new CombinedController();
        controller.startTaskManagement();
    }
}
