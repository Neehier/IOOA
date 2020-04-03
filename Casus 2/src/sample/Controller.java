package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Pane speler;
    public AnchorPane mainPane;
    public Label hoeveelEiren;
    public Pane kippenhok;
    private static double snelheid = 5;
    private int aantalEiren = 0;

    @Override //dit gebeurd wanneer het programma start
    public void initialize(URL location, ResourceBundle resources) {
        hoeveelEiren.setText(String.format("Aantal eieren: %s", aantalEiren));
    }

    private void plaatsEi(double x, double y) {
        if(inKippenHok()){
            aantalEiren += 1;
            Ei ei = new Ei(x, y);
            mainPane.getChildren().add(ei);
            hoeveelEiren.setText("Aantal eiren: " + aantalEiren); //aantaleiren teller linksboven word verhoogd
            if(aantalEiren==10){
                eiMelding();
            }
        }
    }

    private boolean inKippenHok(){
        double posX = kippenhok.getLayoutX();
        double posY = kippenhok.getLayoutY();
        double width = kippenhok.getPrefWidth();
        double height = kippenhok.getPrefHeight();

        Point2D linkerBovenHoekHok = (new Point2D(posX, posY));
        Point2D rechterBovenHoekHok = (new Point2D(posX + width, posY));
        Point2D rechterOnderHoekHok = (new Point2D(posX + width, posY + height));
        Point2D positieKip = (new Point2D(speler.getLayoutX(), speler.getLayoutY())); //met X en Y coordinaten

        if(((positieKip.getX() + 10) >= linkerBovenHoekHok.getX()) && (positieKip.getX() <= (rechterBovenHoekHok.getX() - 30)) &&
                ((positieKip.getY() + 10) >= linkerBovenHoekHok.getY()) && positieKip.getY() <= (rechterOnderHoekHok.getY() - 35)){
            return true; //kip is in kippenhok, dus return true
        } else {
            foutMelding();
            return false;
        }
    }

    private boolean isBinnenScherm(){
        Point2D linkerBovenHoekScherm = (new Point2D(0, 0));
        Point2D rechterBovenHoekScherm = (new Point2D(700, 0));
        Point2D rechterOnderHoekScherm = (new Point2D(700, 700));
        Point2D positieKip = (new Point2D(speler.getLayoutX(), speler.getLayoutY()));

        if((positieKip.getX() >= linkerBovenHoekScherm.getX()) && (positieKip.getX() <= rechterBovenHoekScherm.getX()) &&
                (positieKip.getY() >= linkerBovenHoekScherm.getY()) && (positieKip.getY() <= rechterOnderHoekScherm.getY())){
            return true; //kip is binnen het scherm, dus return true
        } else {
            return false;
        }
    }

    public void kijkVoorInput(KeyEvent event) {
        switch(event.getCode()){
            case UP: movePlayerTo(0, -snelheid); break;
            case DOWN: movePlayerTo(0, snelheid); break;
            case LEFT: movePlayerTo(-snelheid, 0); break;
            case RIGHT: movePlayerTo(snelheid, 0); break;
            case SPACE: plaatsEi(speler.getLayoutX(), speler.getLayoutY());
        }
    }

    public void alsToetsLosgelaten(KeyEvent event) {
        switch(event.getCode()){
            case W: case S: case A: case D: movePlayerTo(0, 0); break;
        }
    }

    public void movePlayerTo(double x, double y){
        double posX = speler.getLayoutX();
        double posY = speler.getLayoutY();

        if(isBinnenScherm()){
            speler.relocate(posX + x, posY + y);

        } else { //als deze else niet gerunned word, zit de kip vast, hiermee word ervoor gezorgd dat het terugbounced van de muur af
            if(posX <= 0)
                speler.relocate(posX + 1, posY);
            if(posX >= 700)
                speler.relocate(posX - 1, posY);
            if(posY <= 0)
                speler.relocate(posX, posY + 1);
            if(posY >= 700)
                speler.relocate(posX, posY - 1);
        }
    }

    public void foutMelding(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hey!");
        alert.setHeaderText("Dat mag niet!");
        alert.setContentText("Een ei kan alleen geplaatst worden in het bruine vlak! Klik op OK om verder te gaan.");
        alert.showAndWait();
    }

    public void eiMelding(){
        Alert alertei = new Alert(Alert.AlertType.INFORMATION);
        alertei.setTitle("Hey!");
        alertei.setHeaderText("Goed gedaan!");
        alertei.setContentText("Je hebt al 10 eiren geplaatst, de boer zou trots zijn!");
        alertei.showAndWait();
    }
}