package Artatawe.GUI;

import Artatawe.Data.*;
import com.jfoenix.controls.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.*;
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
    private Auction auction;
    private Artwork art;

    /**
     * Constructor for ArtworkScene.java
     * @param a
     */
    public ArtworkScene(DataController dc, Profile p, Auction a, Profile logedInProfile){
        super(dc,p, logedInProfile);
        this.auction = a;
        this.art = a.getArtwork();
        setNameLabel(art.getName());
        setContentPane();
    }

    /**
     * Generates central content pane for profile.
     * @return profile page.
     */
    @Override
    public Pane constructContentPane(){

        JFXMasonryPane contentPane = new JFXMasonryPane();
        ImageView imgView = new ImageView();

        HBox bidButtonPane = new HBox();
        JFXButton bidPlaceButton = new JFXButton("Place bid: ");
        TextField bidAmountInput = new TextField("");
        Label bidStatus = new Label(String.format("%d bids remaining",
                auction.getBidMax() - auction.getBidList().size()));

        bidPlaceButton.getStyleClass().add("button-raised");
        bidPlaceButton.setStyle(GUIConstants.BUTTON_STYLE);
        bidAmountInput.setMaxWidth(Double.MAX_VALUE);

        bidButtonPane.getChildren().addAll(bidPlaceButton, bidAmountInput);

        if(!auction.isCompleted()){
            bidPlaceButton.setOnMousePressed(e->{
                try {
                    if (auction.placeBid(curProfile, Integer.valueOf(bidAmountInput.getText()), new Date())) {
                        dc.save();
                    }
                    else {
                        //cannot place bid
                        GUIController.alert("Invalid bid", Alert.AlertType.ERROR);
                    }
                    setContentPane();
                }
                catch (NumberFormatException ex) {
                    GUIController.alert("Bid amount must be a number", Alert.AlertType.ERROR);
                }
            });
        } else {
            bidStatus.setText("Winner is " + auction.getLastBid().getBuyer().getFirstname() + " " +
                    auction.getLastBid().getBuyer().getSurname());
        }

        if(imgView == null){
            imgView = new ImageView(art.getPhoto().getPath());
        }

        JFXButton sellerButton = new JFXButton();

        sellerButton.setText("Seller is " + auction.getSeller().getFirstname()
                + " " + auction.getSeller().getSurname());
        sellerButton.setStyle(" -fx-text-fill: rgb(49, 89, 23)");
        sellerButton.getStyleClass().add("button-raised");
        sellerButton.setStyle(GUIConstants.BUTTON_STYLE);

        sellerButton.setOnMousePressed(e -> {
            GUIController.getPrimaryStage().setScene(new Scene(new ProfileScene(dc, auction.getSeller(),
                    curProfile).getPane(),
                    GUIConstants.SCENE_WIDTH, GUIConstants.SCENE_HEIGHT));
        });

        imgView.setImage(new Image(art.getPhoto().getPath()));
        imgView.setFitWidth(200);
        imgView.setFitHeight(200);

        AnchorPane imgPane = new AnchorPane(imgView);

        VBox aboutCard = new VBox(sellerButton, new Label("ABOUT"),
                new Label(auction.toString()));
        aboutCard.setStyle("-fx-effect: dropshadow(gaussian, silver, 10, 0, 0, 0); " +
                "-fx-background-color: #E8EAF6;");

        VBox descriptionCard = new VBox(new Label("Description\n"), new Label(art.getDescription()));
        descriptionCard.setStyle("-fx-effect: dropshadow(gaussian, silver, 10, 0, 0, 0);" +
                " -fx-background-color: #E8EAF6; -fx-max-width: 200px;");

        //Bids
        JFXListView<String> bidListView = new JFXListView<>();
        bidListView.setPrefSize(200, 40 * (auction.getBidMax()));

        for(Bid bid : auction.getBidList()) {
            bidListView.getItems().add(String.format("%s: £%d", bid.getBuyer().getUsername(), bid.getAmount()));
        }

        //Bid pane
        VBox bidPane = new VBox();
        bidPane.setSpacing(10);
        bidPane.getChildren().addAll(bidStatus, bidButtonPane, bidListView);

        //Comments
        Pane commentSection = createCommentSection();

        contentPane.getChildren().addAll(imgPane, aboutCard, descriptionCard, bidPane, commentSection);

        return contentPane;
    }

    private Pane createCommentSection() {

        //Header
        Label commentHeader = new Label("Comments");
        commentHeader.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

        //Actual comments
        VBox commentList = new VBox();

        //Construct comment list
        for (AuctionComment comment : auction.getCommentList()) {
            fillCommentLabel(commentList, comment);
        }

        //Comment field
        TextArea commentArea = new TextArea();
        commentArea.setMaxWidth(Double.MAX_VALUE);
        commentArea.setMinHeight(100);
        commentArea.setMaxHeight(100);

        //Comment submit button
        JFXButton commentSubmit = new JFXButton("submit comment");
        commentSubmit.getStyleClass().add("button-raised");
        commentSubmit.setStyle(GUIConstants.BUTTON_STYLE);

        commentSubmit.setOnMouseClicked(e -> {
            //Get comment text
            String c = commentArea.getText().trim();
            commentArea.clear();
            //Update auction
            auction.makeComment(curProfile, c);
            //Update comment pane
            fillCommentLabel(commentList, new AuctionComment(curProfile, c));
            //Persist
            dc.save();
        });

        //Put comment list in scroll pane
        ScrollPane scroll = new ScrollPane(commentList);
        scroll.setMinHeight(100);
        scroll.setMaxHeight(250);

        return new VBox(commentHeader, scroll, commentArea, commentSubmit);
    }

    private void fillCommentLabel(VBox container, AuctionComment comment) {

        Label commentLabel = new Label();
        commentLabel.setText(String.format("%s: %s", comment.getCommenter().getUsername(), comment.getText()));
        commentLabel.setWrapText(true);
        commentLabel.setMaxWidth(1000);

        container.getChildren().add(commentLabel);
        container.setMargin(commentLabel, new Insets(0,20,10,10));
    }

}
