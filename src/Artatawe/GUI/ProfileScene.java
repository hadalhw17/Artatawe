package Artatawe.GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;

public class ProfileScene extends ScenePattern{

    private Image image;
    private File file;
    private ImageView vvvv;


    public ProfileScene(String pathToProfImage){

        //file = new File(pathToProfImage);
        //image = new Image(file.toURI().toString());
       // vvvv.setImage(image);

        System.out.print("Hello");

    }



    @Override
    public Pane constructContentPane(){
        return new Pane();
    }
}
