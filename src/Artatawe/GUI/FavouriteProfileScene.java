package Artatawe.GUI;

import Artatawe.Data.DataController;
import Artatawe.Data.Profile;

import com.jfoenix.controls.JFXMasonryPane;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.awt.event.MouseEvent;

/**
 * @author Tom Street
 *
 * Scene listing the favourite users of the current user
 */
public class FavouriteProfileScene extends ScenePattern {

    /**
     * Construct a FavouriteProfileScene
     * @param dc Data Controller
     * @param curProfile Profile of logged-in user
     */
    public FavouriteProfileScene(DataController dc, Profile curProfile, Profile logedInProfile) {
        super(dc,curProfile, logedInProfile);
        this.setNameLabel("Favourites");
        setContentPane();
    }

    @Override
    public JFXMasonryPane constructContentPane()
    {
        JFXMasonryPane pane = new JFXMasonryPane();

        for (Profile fav : curProfile.getAllFavourites())
        {
            BorderPane tile = new BorderPane();
            tile.setStyle("-fx-background-color: #d3ecff;");

            ImageView profilePicView = new ImageView(fav.getProfileImg());
            profilePicView.setFitHeight(GUIConstants.PROFILE_HEIGHT / 1.5);
            profilePicView.setFitWidth(GUIConstants.PROFILE_WIDTH / 1.5);

            Label label  = new Label(fav.getUsername());
            label.setStyle("-fx-font-weight: bold; -fx-font-size: 16pt;");

            tile.setCenter(profilePicView);
            tile.setBottom(new StackPane(label));

            tile.setOnMouseClicked( e -> {
                GUIController.getPrimaryStage().setScene(new Scene(new ProfileScene(dc,fav,curProfile).getPane(),
                        GUIConstants.SCENE_WIDTH, GUIConstants.SCENE_HEIGHT));
            });

            pane.getChildren().add(tile);
        }

        return pane;
    }
}
