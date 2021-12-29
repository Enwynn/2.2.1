package demo.FX;

import demo.ServerClient.Initiate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Map;


public class Main extends Application {
    private final BorderPane bp = new BorderPane();
    private final Draw draw = new Draw(bp);
    private final Coordinates coordinates = new Coordinates();
    private boolean isClient = false;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {

        Initiate init = new Initiate(coordinates);
        Thread t1 = new Thread(init);
        t1.start();
        Thread.sleep(1000);
        isClient = init.isClient();

        if(isClient){
            new Alert(Alert.AlertType.INFORMATION, "You are the client. " +
                    "\n Left mouseclick to get an updated view of servers stream").showAndWait();
        }

        if(!isClient){
            new Alert(Alert.AlertType.INFORMATION, "You are the server. ").showAndWait();
        }

        primaryStage.setTitle("Paint");
        StackPane root = new StackPane();
        root.getChildren().add(bp);
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();

        bp.setOnMouseDragged(mouseEvent -> {
            if (mouseEvent.getY() > 25 && !isClient) {
                draw.draw(mouseEvent.getX(), mouseEvent.getY());
                coordinates.getCurrent().put(mouseEvent.getX(), mouseEvent.getY());
            }
        });

        bp.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getY() > 25 && isClient) {
                for (Map.Entry<Double, Double> entry : coordinates.getCurrent().entrySet()) {
                    draw.draw(entry.getKey(), entry.getValue());
                }
            }
        });
    }
}


