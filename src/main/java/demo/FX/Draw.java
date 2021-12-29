package demo.FX;

import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;

public class Draw {
    private BorderPane borderPane;
    private int brushSize = 3;

    public Draw(BorderPane bp) {
        borderPane = bp;
    }

    public void draw(double x, double y) {
        borderPane.getChildren().add(new Circle(x, y,  brushSize));
    }

    public void setBrushSizePlus() {
        if (brushSize <= 9 ) {
            this.brushSize = brushSize + 1;
        }
    }

    public void setBrushSizeMinus() {
        if (brushSize >= 2) {
            this.brushSize = brushSize - 1;
        }
    }
}





