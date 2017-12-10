package Artatawe.GUI;

import Artatawe.Data.Auction;
import Artatawe.Data.AuctionFilterKey;
import Artatawe.Data.DataController;
import Artatawe.Data.Profile;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXScrollPane;
import javafx.beans.property.BooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author Aleksandr Slobodv
 * <p>
 *     ArtworkContainer.java
 * </p>
 *
 * This class is made to construct a page where all of ouctions are displayed
 * Inherits all of the elements from <p>ScenePattern.java</p>
 */
public class ArtworkContainer extends ScenePattern {

    //Name of the page
    private final String PAGE_NAME = "Artworks";

    //Filter out completed auctions
    private boolean hideCompleted = false;

    //Filter key
    private AuctionFilterKey auctionKey = AuctionFilterKey.ALL;

    /**
     * Constructor for <p>Artworkontainer.java</p>
     * @param dc Artatawe system
     * @param p profile requred for manipulation with another users
     * @param loggedInProfile current user
     */
    public ArtworkContainer(DataController dc, Profile p, Profile loggedInProfile){
        super(dc,p, loggedInProfile);
        setNameLabel(PAGE_NAME);
        setContentPane();
    }

    /**
     * Constructs central part of stage.
     * Contains dynamical content.
     * @return main pane of the page containing all of the auctions
     */
    @Override
    public Pane constructContentPane() {

        //Panes
        BorderPane mainPane = new BorderPane();
        HBox filterPane = new HBox();
        JFXMasonryPane auctionPane = new JFXMasonryPane();
        //filterPane.setStyle();
        filterPane.setSpacing(10);

        //Fill filter option pane
        JFXRadioButton filterAll = new JFXRadioButton("All");
        JFXRadioButton filterPaintings = new JFXRadioButton("Paintings");
        JFXRadioButton filterSculptures = new JFXRadioButton("Sculptures");
        filterAll.setSelected(true);

        ToggleGroup filterGroup = new ToggleGroup();
        filterAll.setToggleGroup(filterGroup);
        filterPaintings.setToggleGroup(filterGroup);
        filterSculptures.setToggleGroup(filterGroup);

        //Is complete filter
        JFXCheckBox filterIsComplete = new JFXCheckBox("Hide completed");
        filterIsComplete.setSelected(hideCompleted);
        filterIsComplete.selectedProperty().addListener((observable, oldValue, newValue) -> {
            hideCompleted = newValue;
            fillAuctionPane(auctionPane, dc.filterAuctions(auctionKey, hideCompleted));
        });

        filterPane.getChildren().addAll(
                new Label("Filter auctions: "), filterAll, filterPaintings, filterSculptures, filterIsComplete
        );

        //Fill auction pane
        fillAuctionPane(auctionPane, dc.filterAuctions(AuctionFilterKey.ALL, hideCompleted));

        //Filter events
        filterAll.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                auctionKey = AuctionFilterKey.ALL;
                fillAuctionPane(auctionPane, dc.filterAuctions(auctionKey, hideCompleted));
            }
        });
        filterPaintings.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                auctionKey = AuctionFilterKey.PAINTING;
                fillAuctionPane(auctionPane, dc.filterAuctions(auctionKey, hideCompleted));
            }
        });
        filterSculptures.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                auctionKey = AuctionFilterKey.SCULPTURE;
                fillAuctionPane(auctionPane, dc.filterAuctions(auctionKey, hideCompleted));
            }
        });

        //Add panes to main pane
        mainPane.setTop(filterPane);
        mainPane.setCenter(auctionPane);

        BorderPane.setMargin(filterPane, new Insets(20,20,20,20));

        return  mainPane;
    }

    /*
        Populate auction pane with auctions
     */
    private void fillAuctionPane(Pane auctionPane, List<Auction> auctionList) {
        auctionPane.getChildren().clear();
        Random r = new Random(100);
        int Low = 70;
        int High = 250;
        //For each auction
        for (Auction auction : auctionList) {

            int wh = r.nextInt(High - Low) + Low;
            wh = 250; //no randomness for now

            BorderPane tile = new BorderPane();

            ImageView artPhoto = new ImageView(auction.getArtwork().getPhoto());
            artPhoto.setFitHeight(wh);
            artPhoto.setFitWidth(wh);
            artPhoto.setPreserveRatio(true);


            Label label = new Label(auction.getArtwork().getName());
            label.setStyle("-fx-font-weight: bold; -fx-font-size: 11pt;");
            label.setMaxWidth(Double.MAX_VALUE);

            tile.setCenter(artPhoto);
            tile.setBottom(new StackPane(label));

            tile.setOnMouseClicked(e ->
                    GUIController.getPrimaryStage().setScene(new Scene(
                                    new ArtworkScene(dc, curProfile, auction, curProfile).getPane(),
                                    GUIConstants.SCENE_WIDTH,
                                    GUIConstants.SCENE_HEIGHT
                            )
                    )
            );

            auctionPane.getChildren().add(tile);
        }
    }
}
