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

        if(imgView == null){
            imgView = new ImageView(art.getPhoto().getPath());
        }

        sellerButton.setText("Seller is " + a.getSeller().getFirstname() + " " + a.getSeller().getSurname());
        sellerButton.setStyle(" -fx-text-fill: rgb(49, 89, 23)");
        sellerButton.setOnMousePressed(e -> {
            GUIController.getPrimaryStage().setScene(new Scene(new ProfileScene(dc,a.getSeller(), logedInProfile).getPane(),
                    GUIConstants.SCENE_WIDTH, GUIConstants.SCENE_HEIGHT));
        });

        imgView.setImage(new Image(art.getPhoto().getPath()));
        imgView.setFitWidth(200);
        imgView.setFitHeight(200);

        AnchorPane imgPane = new AnchorPane(imgView);

        VBox bidPane = new VBox();
        bidPane.getChildren().addAll(bidButton, buyNow);

        VBox aboutCard = new VBox(sellerButton, new Label("ABOUT"), new Label(a.toString()));
        aboutCard.setStyle("-fx-effect: dropshadow(gaussian, silver, 10, 0, 0, 0); -fx-background-color: #E8EAF6;");

        VBox descriptionCard = new VBox(new Label("Description\n"), new Label(art.getDescription()));
        descriptionCard.setStyle("-fx-effect: dropshadow(gaussian, silver, 10, 0, 0, 0); -fx-background-color: #E8EAF6; -fx-max-width: 200px;");

        //Bids
        JFXListView<String> bidListView = new JFXListView<>();
        bidListView.setPrefSize(200, 50 * a.getBidMax());

        for(Bid bid : a.getBidList()) {
            bidListView.getItems().add(String.format("%s: £%d", bid.getBuyer().getUsername(), bid.getAmount()));
        }

        //VBox bidders = new VBox(new Label("BIDDERS: "), new Label(biddersName.toString()));
        //bidders.setStyle("-fx-effect: dropshadow(gaussian, silver, 10, 0, 0, 0); -fx-background-color: #E8EAF6;");

        //Comments
        Pane commentSection = createCommentSection();

        contentPane.getChildren().addAll(imgPane, aboutCard, bidPane, descriptionCard, bidListView);
        contentPane.getChildren().add(commentSection);

        return contentPane;
    }

    private Pane createCommentSection() {

        //Header
        Label commentHeader = new Label("Comments");
        commentHeader.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

        //Actual comments
        VBox commentList = new VBox();

        //Construct comment list
        for (AuctionComment comment : a.getCommentList()) {
            fillCommentLabel(commentList, comment);
        }

        //Comment field
        TextArea commentArea = new TextArea();
        commentArea.setMaxWidth(Double.MAX_VALUE);
        commentArea.setMinHeight(100);
        commentArea.setMaxHeight(100);

        //Comment submit button
        JFXButton commentSubmit = new JFXButton("submit");

        commentSubmit.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            //Get comment text
            String c = commentArea.getText().trim();
            commentArea.clear();
            //Update auction
            a.makeComment(curProfile, c);
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
