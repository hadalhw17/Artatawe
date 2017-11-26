package Artatawe.GUI;

import Artatawe.Data.Auction;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class AuctionScene extends ScenePattern {

    public AuctionScene(Auction a){

        setNameLabel("Mona Lisa");
    }

    @Override
    public JFXMasonryPane constructContentPane(){
        JFXMasonryPane contentPane = new JFXMasonryPane();
        ImageView imgView = new ImageView(new Image(getClass().getResource("/img3.png").toExternalForm()));
        imgView.setFitWidth(200);
        imgView.setFitHeight(200);
        AnchorPane imgPane = new AnchorPane(imgView);
        VBox descrtiptionCard = new VBox(new Label("ABOUT:"), new Label("Name: Mona Lisa\nAge:...y.o\nWeight: 95\nHeight: 195\nAmount of bids available: 5."));
        descrtiptionCard.setStyle("-fx-effect: dropshadow(gaussian, silver, 10, 0, 0, 0); -fx-background-color: #E8EAF6;");
        VBox bidders = new VBox(new Label("BIDDERS: "), new Label("1.Freddia Mercury\n2.James Hatfield,\nJimmie Hendrix\nRonnie James Dio\nDimebag Darell"));
        bidders.setStyle("-fx-effect: dropshadow(gaussian, silver, 10, 0, 0, 0); -fx-background-color: #E8EAF6;");
        contentPane.getChildren().addAll(imgPane,descrtiptionCard,bidders);
        return contentPane;
    }
}
