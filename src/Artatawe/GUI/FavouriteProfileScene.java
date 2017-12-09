package Artatawe.GUI;

import Artatawe.Data.DataController;
import Artatawe.Data.Profile;

import com.jfoenix.controls.JFXMasonryPane;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;

/**
 * @author Tom Street
 *
 * Scene listing the favourite users of the current user
 */
public class FavouriteProfileScene extends ScenePattern {

    private Profile logedInProfile;
    /**
     * Construct a FavouriteProfileScene
     * @param dc Data Controller
     * @param curProfile Profile of logged-in user
     */
    public FavouriteProfileScene(DataController dc, Profile curProfile, Profile logedInProfile) {
        super(dc,curProfile, logedInProfile);
        this.logedInProfile = logedInProfile;
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
            profilePicView.setFitHeight(300);
            profilePicView.setFitWidth(300);

            Label label  = new Label(fav.getUsername());
            label.setStyle("-fx-font-weight: bold; -fx-font-size: 16pt;");

            box.getChildren().addAll(profilePicView, label);
            pane.getChildren().add(box);
        }

        return pane;
    }
}
