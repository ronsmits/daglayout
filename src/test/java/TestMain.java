import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nl.codetribe.daglayout.DagLayout;
import nl.codetribe.daglayout.Vertex;

/**
 * Created by ronsmits on 15/11/2016.
 */
public class TestMain extends Application {
    public void start(Stage primaryStage) throws Exception {


        Vertex rootVertex = new Vertex("1");
        Vertex k11 = new Vertex("1.1");
        Vertex k111 = new Vertex("1.1.1");
        Vertex k112 = new Vertex("1.1.2");
        Vertex k1121 = new Vertex("1.1.2.1");
        Vertex k1122 = new Vertex("1.1.2.2");
        Vertex k1111 = new Vertex("1.1.1.1");
        rootVertex.addKid(k11);
        k11.addKid(k111);
        k11.addKid(k112);
        k112.addKid(k1121);
        k112.addKid(k1122);
        k111.addKid(k1111);
        Vertex k12 = new Vertex("1.2");
        Vertex k121 = new Vertex("1.2.1");
        Vertex k1211 = new Vertex("1.2.1.1");
        Vertex l1212 = new Vertex("1.2.1.2");
//        l1212.addKid(new Vertex("1.2.1.2.1"));
        rootVertex.addKid(k12);
        k12.addKid(k121);
        k121.addKid(k1211);
        k121.addKid(l1212);
        Group group = new Group();
        ScrollPane pane = new ScrollPane(group);
        pane.setPrefSize(600,400);
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        final Scene scene = new Scene(pane, 600, 400, Color.TRANSPARENT);

        pane.setContent(new DagLayout(rootVertex));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
