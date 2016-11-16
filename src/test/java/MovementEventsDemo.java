import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MovementEventsDemo extends Application {
    private static final int      KEYBOARD_MOVEMENT_DELTA = 5;
    private static final Duration TRANSLATE_DURATION      = Duration.seconds(0.25);

    public static void main(String[] args) { launch(args); }
    @Override public void start(Stage stage) throws Exception {
        final Circle circle = createCircle();
        final Rectangle rectangle = createRectangle();
        StackPane pane = new StackPane();
        Text text = new Text("dit is een hele lange text");
        text.setWrappingWidth(95);
        text.setBoundsType(TextBoundsType.LOGICAL_VERTICAL_CENTER);
        pane.getChildren().addAll(rectangle, text);

        System.out.println(rectangle.getX()+" "+rectangle.getY()+":"+rectangle.getWidth());
        final Group group = new Group(createInstructions(), circle, pane);
        final TranslateTransition transition = createTranslateTransition(circle);

        System.out.println(pane.getLayoutBounds());
        pane.setLayoutX(20);
        final Scene scene = new Scene(group, 600, 400, Color.CORNSILK);
        moveCircleOnKeyPress(scene, circle, pane);
        moveCircleOnMousePress(scene, circle, transition);

        stage.setScene(scene);
        stage.show();
    }

    private Label createInstructions() {
        Label instructions = new Label(
                "Use the arrow keys to move the circle in small increments\n" +
                        "Click the mouse to move the circle to a given location\n" +
                        "Ctrl + Click the mouse to slowly translate the circle to a given location"
        );
        instructions.setTextFill(Color.FORESTGREEN);

        return instructions;
    }

    private Rectangle createRectangle() {
        final Rectangle rectangle = new Rectangle(100, 50, Color.CORNFLOWERBLUE);
        //rectangle.setStroke(Color.BLACK);
        rectangle.setArcHeight(15);
        rectangle.setArcWidth(15);
        return rectangle;
    }
    private Circle createCircle() {
        final Circle circle = new Circle(200, 150, 50, Color.BLUEVIOLET);
        circle.setOpacity(0.7);
        return circle;
    }

    private TranslateTransition createTranslateTransition(final Circle circle) {
        final TranslateTransition transition = new TranslateTransition(TRANSLATE_DURATION, circle);
        transition.setOnFinished(t ->
        {
            circle.setCenterX(circle.getTranslateX() + circle.getCenterX());
            circle.setCenterY(circle.getTranslateY() + circle.getCenterY());
            circle.setTranslateX(0);
            circle.setTranslateY(0);
        });
        return transition;
    }

    private void moveCircleOnKeyPress(Scene scene, final Circle circle, final Pane pane) {
        scene.setOnKeyPressed(event ->
        {
            System.out.println(pane.getWidth());
            switch (event.getCode()) {
                case UP:    circle.setCenterY(circle.getCenterY() - KEYBOARD_MOVEMENT_DELTA); pane.setLayoutY(pane.getLayoutY()-KEYBOARD_MOVEMENT_DELTA);break;
                case RIGHT: circle.setCenterX(circle.getCenterX() + KEYBOARD_MOVEMENT_DELTA); pane.setLayoutX(pane.getLayoutX()+KEYBOARD_MOVEMENT_DELTA);break;
                case DOWN:  circle.setCenterY(circle.getCenterY() + KEYBOARD_MOVEMENT_DELTA); pane.setLayoutY(pane.getLayoutY()+KEYBOARD_MOVEMENT_DELTA);break;
                case LEFT:  circle.setCenterX(circle.getCenterX() - KEYBOARD_MOVEMENT_DELTA); pane.setLayoutX(pane.getLayoutX()-KEYBOARD_MOVEMENT_DELTA);break;
            }
            System.out.println("pane at   "+pane.getLayoutX());
            System.out.println("circle at "+circle.getLayoutBounds());
        });
    }

    private void moveCircleOnMousePress(Scene scene, final Circle circle, final TranslateTransition transition) {
        scene.setOnMousePressed(event ->
        {
            if (!event.isControlDown()) {
                circle.setCenterX(event.getSceneX());
                circle.setCenterY(event.getSceneY());
            } else {
                transition.setToX(event.getSceneX() - circle.getCenterX());
                transition.setToY(event.getSceneY() - circle.getCenterY());
                transition.playFromStart();
            }
        });
    }
}
