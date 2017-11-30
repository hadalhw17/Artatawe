package Artatawe.GUI;

import Artatawe.Data.Artwork;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *  @author Aleksandr Slobodov
 *
 *  ArtworkScene.java
 *
 *  Scene made to display Artwork information.
 *  Inherits all of the elements from ScenePattern.java
 *
 */
public class ArtworkScene extends ScenePattern {

    //Artwork to be displayed
    private Artwork a;

    /**
     * Constructor for ArtworkScene.java
     * @param a
     */
    public ArtworkScene(Artwork a){
        this.a = a;
        setNameLabel(a.getName());
        setContentPane();
    }

    /**
     * Generates central content pane for profile.
     * @return profile page.
     */
    @Override
    public JFXMasonryPane constructContentPane(){
        JFXMasonryPane contentPane = new JFXMasonryPane();
        ImageView imgView = new ImageView();
        if(imgView == null){
            imgView = new ImageView(new Image("file:data/artworks/img2.png"));
        }
        imgView.setImage(new Image("file:data/artworks/img2.png"));
        imgView.setFitWidth(200);
        imgView.setFitHeight(200);
        AnchorPane imgPane = new AnchorPane(imgView);
        VBox aboutCard = new VBox(new Label("ABOUT"), new Label("Name: " + a.getName() + "\nYear: " + a.getYear()
        + "\nWidth: " + a.getWidth() + "\nHeight: " + a.getHeight()));
        VBox descriptionCard = new VBox(new Label("Description\n"), new Label(a.getDescription()));
        aboutCard.setStyle("-fx-effect: dropshadow(gaussian, silver, 10, 0, 0, 0); -fx-background-color: #E8EAF6;");
        descriptionCard.setStyle("-fx-effect: dropshadow(gaussian, silver, 10, 0, 0, 0); -fx-background-color: #E8EAF6; -fx-max-width: 200px;");
        VBox bidders = new VBox(new Label("BIDDERS: "), new Label("1.Freddia Mercury\n2.James Hatfield," +
                "\nJimmie Hendrix\nRonnie James Dio\nDimebag Darell"));
        bidders.setStyle("-fx-effect: dropshadow(gaussian, silver, 10, 0, 0, 0); -fx-background-color: #E8EAF6;");
        contentPane.getChildren().addAll(imgPane,aboutCard,descriptionCard,bidders);
        return contentPane;
    }

}
