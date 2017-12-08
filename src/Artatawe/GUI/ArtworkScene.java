package Artatawe.GUI;

import Artatawe.Data.*;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

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
    private Auction a;
    private Artwork art;

    /**
     * Constructor for ArtworkScene.java
     * @param a
     */
    public ArtworkScene(DataController dc, Profile p, Auction a){
        super(dc,p);
        this.a = a;
        this.art = a.getArtwork();
        setNameLabel(art.getName());
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
        ArrayList<String> biddersName = new ArrayList<>();
        for(Bid bid:a.getBidList()){
            biddersName.add(bid.getBuyer().getUsername() + "\n");
        }
        if(imgView == null){
            imgView = new ImageView(art.getPhoto());
        }
        imgView.setImage(art.getPhoto());
        imgView.setFitWidth(200);
        imgView.setFitHeight(200);
        AnchorPane imgPane = new AnchorPane(imgView);
        VBox aboutCard = new VBox(new Label("ABOUT"), new Label(a.toString()));
        VBox descriptionCard = new VBox(new Label("Description\n"), new Label(art.getDescription()));
        aboutCard.setStyle("-fx-effect: dropshadow(gaussian, silver, 10, 0, 0, 0); -fx-background-color: #E8EAF6;");
        descriptionCard.setStyle("-fx-effect: dropshadow(gaussian, silver, 10, 0, 0, 0); -fx-background-color: #E8EAF6; -fx-max-width: 200px;");
        VBox bidders = new VBox(new Label("BIDDERS: "), new Label(biddersName.toString()));
        bidders.setStyle("-fx-effect: dropshadow(gaussian, silver, 10, 0, 0, 0); -fx-background-color: #E8EAF6;");
        contentPane.getChildren().addAll(imgPane,aboutCard,descriptionCard,bidders);
        return contentPane;
    }

}
