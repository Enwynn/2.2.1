package demo.FX;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Buttons {
    private HBox vb = new HBox();

    public Buttons(BorderPane bp, Draw draw){
        Button btn = new Button();
        btn.setOnAction(e -> {
            draw.setBrushSizePlus();
                });
        
        Button btn1 = new Button();
        btn1.setOnAction(e -> {
            draw.setBrushSizeMinus();
        });
        setButtons(bp, btn, btn1);
    }

    private void setButtons(BorderPane bp, Button btn, Button btn1) {
        btn.setText("-");
        btn1.setText("+");
        vb.setSpacing(5);
        vb.getChildren().addAll(btn, btn1);
        bp.setTop(vb);
    }
}



