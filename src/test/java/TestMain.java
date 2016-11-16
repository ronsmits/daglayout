import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import nl.codetribe.daglayout.Vertex;

/**
 * Created by ronsmits on 15/11/2016.
 */
public class TestMain extends Application {
    public void start(Stage primaryStage) throws Exception {
        Group group = new Group();
        final Scene scene = new Scene(group, 600, 400, Color.CORNSILK);

        Vertex e = new Vertex("test");
        Vertex b = new Vertex("nog een test");
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP: e.relocate(e.getLayoutX(),      e.getLayoutY()-5);break;
                    case DOWN: e.relocate(e.getLayoutX(),    e.getLayoutY()+5);break;
                    case LEFT: e.relocate(e.getLayoutX()-5,  e.getLayoutY());break;
                    case RIGHT: e.relocate(e.getLayoutX()+5, e.getLayoutY());break;
                }
            }
        });

        group.getChildren().addAll(e, b);

//        e.relocate(100, 50);
        e.relocate(100,100);
        Line line = new Line();
        line.startXProperty().bind(e.getBottomPortXProperty());
        line.startYProperty().bind(e.getBottomPortYProperty());
        line.endXProperty().bind(b.getBottomPortXProperty());
        line.endYProperty().bind(b.getBottomPortYProperty());
        group.getChildren().add(line);
        b.relocate(10,10);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
