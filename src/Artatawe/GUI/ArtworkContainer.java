package Artatawe.GUI;

import Artatawe.Data.Auction;
import Artatawe.Data.DataController;
import Artatawe.Data.Profile;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
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

    //List with all of the auctions
    private ArrayList<Auction> auctions;

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
        this.auctions = dc.getAuctions();
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
    public JFXMasonryPane constructContentPane() {
        JFXMasonryPane contentPane = new JFXMasonryPane();
        ImageView imgView = new ImageView(new Image("file:data/artworks/img2.png"));
        imgView.setFitWidth(20);
        Random r = new Random(100);
        imgView.setFitHeight(20);
        int Low = 50;
        int High = 250;
        for(Auction auction: auctions){//Gonna iterate over auctions list
            VBox imageHolder;
            int wh = r.nextInt(High - Low) + Low;
            imgView = new ImageView(new Image(auction
                    .getArtwork()
                    .getPhoto()
                    .getPath()));
            imgView.setFitHeight(wh);
            imgView.setFitWidth(wh);
            imageHolder = new VBox(imgView, new Label(auction.getArtwork().getName()));
            contentPane
                    .getChildren()
                    .addAll(imageHolder);
            imageHolder.setOnMouseClicked(e->
                GUIController
                        .getPrimaryStage()
                        .setScene(new Scene(
                                new ArtworkScene(dc,p, auction, loggedInProfile)
                                        .getPane(), GUIConstants
                                .SCENE_WIDTH,GUIConstants
                                .SCENE_HEIGHT)));

        }
        return  contentPane;
    }

}
