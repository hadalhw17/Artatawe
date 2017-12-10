package Artatawe.GUI;

import Artatawe.Data.DataController;
import Artatawe.Data.Profile;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


/**
 * @author Aleksandr Slobodov
 *<p>
 *     ToggleSwitch.java
 *</p>
 * This is a custom controll element for a toggle button
 */
public class ToggleSwitch extends HBox {

    //Left button
    private final Button button1 = new Button();

    //Right button
    private final Button button = new Button();

    //Mark/Unmark
    private SimpleBooleanProperty switchedOn;
    public SimpleBooleanProperty switchOnProperty() { return switchedOn; }

    //Profile to mark as fav
    private Profile p;

    //Current user
    private Profile logedInProfile;

    //Text on toggle
    private String text;

    /**
     * Constructor of ToggleButton.java
     * @param dc
     * @param logedInProfile
     * @param toMark
     */
    public ToggleSwitch(DataController dc, Profile logedInProfile, Profile toMark) {
        this.logedInProfile = logedInProfile;
        this.p = toMark;
        init();
        switchedOn.addListener((a,b,c) -> {
            System.out.print(b);
            if (switchOnProperty().getValue()) {
                button1.setText("Unmark");
                setStyle("-fx-background-color: green;");
                button1.toFront();
                logedInProfile.addFavourite(toMark);
                dc.save();
            }
            else {
                button1.setText("Mark as favourite");
                setStyle("-fx-background-color: grey;");
                button.toFront();
                logedInProfile.removeFavourite(toMark);
                dc.save();
            }
        });
    }

    /**
     * Initializes toggle button
     * Sets text on the correct button
     * Make the correct button active
     */
    private void init() {

        if (logedInProfile.getAllFavourites().contains(p)){
            text = "Unmark";
            switchedOn = new SimpleBooleanProperty(true);
            button.toFront();
        } else{
            switchedOn = new SimpleBooleanProperty(false);
            text = "Mark as favourite";
            button1.toFront();
        }
        button1.setText(text);

        getChildren().addAll(button1, button);
        button.setOnAction((e) -> {
            switchedOn.set(!switchedOn.get());
        });
        button1.setOnAction((e) -> {
            switchedOn.set(!switchedOn.get());
        });
        setStyle();
        bindProperties();
    }

    /**
     * Style settings
     */
    private void setStyle() {
        //Default Width
        setWidth(100);
        setHeight(50);
        setStyle("-fx-background-color: grey; -fx-text-fill:black; -fx-background-radius: 4;");
        setAlignment(Pos.CENTER_LEFT);
    }


    /**
     * Resizing
     */
    private void bindProperties() {
        button1.prefWidthProperty().bind(widthProperty().divide(2));
        button1.prefHeightProperty().bind(heightProperty());
        button.prefWidthProperty().bind(widthProperty().divide(2));
        button.prefHeightProperty().bind(heightProperty());
    }
}