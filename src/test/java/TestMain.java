import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nl.codetribe.daglayout.DagLayout;
import nl.codetribe.daglayout.Vertex;

/**
 * Created by ronsmits on 15/11/2016.
 */
public class TestMain extends Application {
    public void start(Stage primaryStage) throws Exception {
        Group group = new Group();
        final Scene scene = new Scene(group, 600, 400, Color.TRANSPARENT);

//        Vertex a = new Vertex("test");
//        Vertex b = new Vertex("nog een test");
//        Vertex c = new Vertex("derde test");
//        scene.setOnKeyPressed(event -> {
//            switch (event.getCode()) {
//                case UP:
//                    a.relocate(a.getLayoutX(), a.getLayoutY() - 5);
//                    break;
//                case DOWN:
//                    a.relocate(a.getLayoutX(), a.getLayoutY() + 5);
//                    break;
//                case LEFT:
//                    a.relocate(a.getLayoutX() - 5, a.getLayoutY());
//                    break;
//                case RIGHT:
//                    a.relocate(a.getLayoutX() + 5, a.getLayoutY());
//                    break;
//            }
//        });
//
//        group.getChildren().addAll(a, b, c);
//
////        a.relocate(100, 50);
//        a.relocate(100, 100);
//        Line line = new Line();
//        line.startXProperty().bind(a.getTopXProperty());
//        line.startYProperty().bind(a.getTopYProperty());
//        line.endXProperty().bind(b.getBottomXProperty());
//        line.endYProperty().bind(b.getBottomYProperty());
//
//        Line l2 = new Line();
//        l2.startXProperty().bind(a.getBottomXProperty());
//        l2.startYProperty().bind(a.getBottomYProperty());
//        l2.endXProperty().bind(c.getTopXProperty());
//        l2.endYProperty().bind(c.getTopYProperty());
//        group.getChildren().addAll(line, l2);
//        b.relocate(300, 10);
//        c.relocate(300, 300);
//        group.getChildren().forEach(it-> System.out.println(it));

        Vertex rootVertex = new Vertex("inside dag");
        Vertex kid1 = new Vertex("kid 1");
        Vertex kid2 = new Vertex("kid 2");
        rootVertex.addKid(kid1);
        rootVertex.addKid(kid2);
        kid2.addKid(new Vertex("grandkid1"));
        kid2.addKid(new Vertex("grandkid2"));
        group.getChildren().add(new DagLayout(rootVertex));
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("I am here");
    }

    public static void main(String[] args) {
        launch(args);
    }

}
