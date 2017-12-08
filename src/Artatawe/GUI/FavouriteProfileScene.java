package Artatawe.GUI;

import Artatawe.Data.DataController;
import Artatawe.Data.Profile;

import com.jfoenix.controls.JFXMasonryPane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

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
    public FavouriteProfileScene(DataController dc, Profile curProfile) {
        super(dc,curProfile);

        this.setNameLabel("Favourites");
        setContentPane();
    }

    @Override
    public JFXMasonryPane constructContentPane()
    {
        JFXMasonryPane pane = new JFXMasonryPane();

        for (Profile fav : curProfile.getAllFavourites())
        {
            StackPane box = new StackPane();
            box.setStyle("-fx-background-color: #d3ecff;");

            ImageView profilePicView = new ImageView(fav.getProfileImg());
            profilePicView.setFitHeight(350);
            profilePicView.setFitWidth(350);

            Label label  = new Label(fav.getUsername());
            label.setStyle("-fx-font-weight: bold; -fx-font-size: 16pt;");

            box.getChildren().addAll(profilePicView, label);
            pane.getChildren().add(box);
        }

        return pane;
    }
}
