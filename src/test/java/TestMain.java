import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nl.codetribe.daglayout.Vertex;

import static javafx.scene.input.KeyCode.UP;

/**
 * Created by ronsmits on 15/11/2016.
 */
public class TestMain extends Application {
    public void start(Stage primaryStage) throws Exception {
        Group group = new Group();
        final Scene scene = new Scene(group, 600, 400, Color.CORNSILK);

        Vertex e = new Vertex("test");
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP: e.relocate(e.getLayoutX(), e.getLayoutY()+5);break;
                }
            }
        });

        group.getChildren().add(e);
        e.relocate(100, 50);
        e.relocate(100,100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
