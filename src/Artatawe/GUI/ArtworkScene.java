package Artatawe.GUI;

import Artatawe.Data.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;

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
    Profile p;
    DataController dc;
    private Profile logedInProfile;

    /**
     * Constructor for ArtworkScene.java
     * @param a
     */
    public ArtworkScene(DataController dc, Profile p, Auction a, Profile logedInProfile){
        super(dc,p, logedInProfile);
        this.logedInProfile = logedInProfile;
        this.a = a;
        this.p = p;
        this.art = a.getArtwork();
        this.dc = dc;
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
        JFXButton buyNow = new JFXButton("Buy now");
        buyNow.setOnMousePressed(e->{
            while(!a.isCompleted()){
                a.placeBid(logedInProfile,a.getBidMax(),new Date(12,9,1997));
            }
            dc.save();
            setContentPane();
        });
        JFXButton sellerButton = new JFXButton();
        JFXButton bidButton = new JFXButton("Bid this work");
        if(!a.isCompleted()){
            bidButton.setOnMousePressed(e->{
                a.placeBid(p,1,new Date(12,9,1997));
                dc.save();
                setContentPane();
            });
        } else {
            bidButton.setText("Winner is " + a.getLastBid().getBuyer().getFirstname() + " " +
                    a.getLastBid().getBuyer().getSurname());
        }

        for(Bid bid:a.getBidList()){
            biddersName.add(bid.getBuyer().getUsername() + "\n");
        }
        if(imgView == null){
            imgView = new ImageView(art.getPhoto().getPath());
        }
        sellerButton.setText("Seller is " + a.getSeller().getFirstname() + " " + a.getSeller().getSurname());
        sellerButton.setStyle(" -fx-text-fill: rgb(49, 89, 23)");
        sellerButton.setOnMousePressed(e -> {
            ((Stage) sellerButton.getScene().getWindow()).setScene(new Scene(new ProfileScene(dc,a.getSeller(),logedInProfile).getPane(),
                    Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight()));
        });
        imgView.setImage(new Image(art.getPhoto().getPath()));
        imgView.setFitWidth(200);
        imgView.setFitHeight(200);
        AnchorPane imgPane = new AnchorPane(imgView);
        VBox bidPane = new VBox();
        bidPane.getChildren().addAll(bidButton, buyNow);
        VBox aboutCard = new VBox(sellerButton, new Label("ABOUT"), new Label(a.toString()));
        VBox descriptionCard = new VBox(new Label("Description\n"), new Label(art.getDescription()));
        aboutCard.setStyle("-fx-effect: dropshadow(gaussian, silver, 10, 0, 0, 0); -fx-background-color: #E8EAF6;");
        descriptionCard.setStyle("-fx-effect: dropshadow(gaussian, silver, 10, 0, 0, 0); -fx-background-color: #E8EAF6; -fx-max-width: 200px;");
        VBox bidders = new VBox(new Label("BIDDERS: "), new Label(biddersName.toString()));
        bidders.setStyle("-fx-effect: dropshadow(gaussian, silver, 10, 0, 0, 0); -fx-background-color: #E8EAF6;");
        contentPane.getChildren().addAll(imgPane,aboutCard,bidPane,descriptionCard,bidders);
        return contentPane;
    }

}
