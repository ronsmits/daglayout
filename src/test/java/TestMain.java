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

        Vertex rootVertex = new Vertex("1");
        Vertex k11 = new Vertex("1.1");
        Vertex k111 = new Vertex("1.1.1");
        Vertex k112 = new Vertex("1.1.2");
        Vertex k1121 = new Vertex("1.1.2.1");
        Vertex k1111 = new Vertex("1.1.1.1");
        rootVertex.addKid(k11);
        k11.addKid(k111);
        k11.addKid(k112);
        k112.addKid(k1121);
        k111.addKid(k1111);
        Vertex k12 = new Vertex("1.2");
        Vertex k121 = new Vertex("1.2.1");
        Vertex k1211 = new Vertex("1.2.1.1");
        Vertex l1212 = new Vertex("1.2.1.2");
        rootVertex.addKid(k12);
        k12.addKid(k121);
        k121.addKid(k1211);
        k121.addKid(l1212);
        group.getChildren().add(new DagLayout(rootVertex));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
