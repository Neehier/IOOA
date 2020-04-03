package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Ei extends AnchorPane {
    public Ei(double x, double y){
        Image eiAfbeelding = new Image("sample/egg.png");
        ImageView laatEiZien = new ImageView(eiAfbeelding);
        laatEiZien.setFitWidth(30);
        laatEiZien.setFitHeight(30);
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.getChildren().add(laatEiZien);
    }
}