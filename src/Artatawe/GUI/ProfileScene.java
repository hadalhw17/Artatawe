package Artatawe.GUI;

import com.jfoenix.controls.JFXMasonryPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.util.Random;

public class ProfileScene extends ScenePattern{

    private Image image;
    private File file;
    private ImageView imgView;



    public ProfileScene(String pathToProfImage){

        String appMain = System.getProperty("user.dir");
        file = new File("src/res/img1.png");
        image = new Image(file.toURI().toString());
        System.out.print(image.isError());


    }



    @Override
    public JFXMasonryPane constructContentPane(Image img){
        JFXMasonryPane contentPane = new JFXMasonryPane();
        if(imgView == null){
            imgView = new ImageView(img);
        }
        Random r = new Random();
        for (int i = 1; i<=100; i++){
            Label label = new Label();
            label.setPrefSize(r.nextInt(200), r.nextInt(200));
            label.setStyle( "-fx-background-color: #E91E63;");
            contentPane.getChildren().add(label);
        }
        imgView.setFitWidth(150);
        imgView.setFitHeight(150);
        //imgView.relocate(Screen.getPrimary().getVisualBounds().getWidth()/2, -60);

        contentPane.getChildren().addAll(imgView);



        return contentPane;
    }

    @Override
    public void repaint(Number n){
        imgView.relocate(GUIController.screenWidth.doubleValue()/2, -60);
    }
}
