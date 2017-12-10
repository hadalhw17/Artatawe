package Artatawe.GUI;

import Artatawe.Data.Auction;
import Artatawe.Data.AuctionFilterKey;
import Artatawe.Data.DataController;
import Artatawe.Data.Profile;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXScrollPane;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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

    //Contains all information abot system
    private DataController dc;

    private Profile p;

    //Profile of currently logged in user
    private Profile loggedInProfile;

    /**
     * Constructor for <p>Artworkontainer.java</p>
     * @param dc Artatawe system
     * @param p profile requred for manipulation with another users
     * @param loggedInProfile current user
     */
    public ArtworkContainer(DataController dc, Profile p, Profile loggedInProfile){
        super(dc,p, loggedInProfile);
        this.loggedInProfile = loggedInProfile;
        this.dc = dc;
        this.p = p;
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

        filterPane.getChildren().addAll(new Label("Filter auctions: "), filterAll, filterPaintings, filterSculptures);

        //Fill auction pane
        fillAuctionPane(auctionPane, dc.filterAuctions(AuctionFilterKey.ALL));

        //Filter events
        filterAll.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) fillAuctionPane(auctionPane, dc.filterAuctions(AuctionFilterKey.ALL));
        });
        filterPaintings.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) fillAuctionPane(auctionPane, dc.filterAuctions(AuctionFilterKey.PAINTING));
        });
        filterSculptures.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) fillAuctionPane(auctionPane, dc.filterAuctions(AuctionFilterKey.SCULPTURE));
        });

        //Add panes to main pane
        mainPane.setTop(filterPane);

        JFXScrollPane scroll = new JFXScrollPane();
        scroll.setContent(auctionPane); //wrap auctions in scroll pane
        mainPane.setCenter(auctionPane);

        return  mainPane;
    }


    private void fillAuctionPane(Pane auctionPane, List<Auction> auctionList) {
        auctionPane.getChildren().clear();
        ImageView imgView = null;
        Random r = new Random(100);
        int Low = 50;
        int High = 250;
        for (Auction auction : auctionList) {//Gonna iterate over auctions list
            VBox imageHolder;
            int wh = r.nextInt(High - Low) + Low;
            imgView = new ImageView(new Image(auction
                    .getArtwork()
                    .getPhoto()
                    .getPath()));
            imgView.setFitHeight(wh);
            imgView.setFitWidth(wh);
            imageHolder = new VBox(imgView, new Label(auction.getArtwork().getName()));
            auctionPane
                    .getChildren()
                    .addAll(imageHolder);
            imageHolder.setOnMouseClicked(e ->
                    GUIController
                            .getPrimaryStage()
                            .setScene(new Scene(
                                    new ArtworkScene(dc, p, auction, loggedInProfile)
                                            .getPane(), GUIConstants
                                    .SCENE_WIDTH, GUIConstants
                                    .SCENE_HEIGHT)));
        }
    }
}
