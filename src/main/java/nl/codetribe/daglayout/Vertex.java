package nl.codetribe.daglayout;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

/**
 * Created by ronsmits on 15/11/2016.
 */
public class Vertex extends Pane
{

    public DoubleProperty getBottomPortXProperty()
    {
        return bottomPortXProperty;
    }

    public DoubleProperty getBottomPortYProperty()
    {
        return bottomPortYProperty;
    }



    private SimpleDoubleProperty bottomPortXProperty = new SimpleDoubleProperty();
    private SimpleDoubleProperty bottomPortYProperty = new SimpleDoubleProperty();

    public void setBottomPortX(double value) { bottomPortXProperty.setValue(value);}
    public void setBottomPortY(double value) {bottomPortYProperty.setValue(value);}

    public Vertex(String label){
        StackPane stackPane = new StackPane();
        Rectangle rectangle = new Rectangle(100, 50, Color.THISTLE);
        rectangle.setStroke(Color.BLACK);
        Text text = new Text(label);

        text.setBoundsType(TextBoundsType.LOGICAL_VERTICAL_CENTER);
        //text.setWrappingWidth(95);
        stackPane.getChildren().addAll(rectangle, text);
        getChildren().add(stackPane);

        bottomPortXProperty.setValue(50);
        bottomPortYProperty.setValue(50);
        layoutXProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println(label);
                bottomPortXProperty.setValue(newValue.doubleValue()+50.0);
                printBottomPort();
            }
        });
        layoutYProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println(label);
                bottomPortYProperty.setValue(newValue.doubleValue()+getHeight());
                printBottomPort();
            }
        });

        Circle circle = new Circle(6, Color.RED);
        System.out.println(getLayoutBounds());
        circle.setCenterX(50);
        circle.setCenterY(50);

        circle.centerYProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("circle Y is now "+newValue);
                System.out.println(getLayoutBounds());
            }
        });
        getChildren().add(circle);
    }

    private void printBottomPort() {
        System.out.println(String.format("X: %f Y: %f", bottomPortXProperty.doubleValue(), bottomPortYProperty.doubleValue()));
    }

}
