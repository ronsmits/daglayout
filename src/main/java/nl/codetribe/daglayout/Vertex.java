package nl.codetribe.daglayout;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

/**
 * Created by ronsmits on 15/11/2016.
 */
public class Vertex extends StackPane{

    private DoubleProperty bottomPortXProperty = new SimpleDoubleProperty();
    private DoubleProperty bottomPortYProperty = new SimpleDoubleProperty();

    public void setBottomPortX(double value) { bottomPortXProperty.setValue(value);}
    public void setBottomPortY(double value) {bottomPortYProperty.setValue(value);}

    public Vertex(String label){
        Rectangle rectangle = new Rectangle(100, 50, Color.THISTLE);
        rectangle.setStroke(Color.BLACK);
        Text text = new Text(label);
        text.setBoundsType(TextBoundsType.LOGICAL_VERTICAL_CENTER);
        //text.setWrappingWidth(95);
        getChildren().addAll(rectangle, text);
        layoutXProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("new value is "+newValue.doubleValue());
                bottomPortXProperty.setValue(newValue.doubleValue()+50.0);
            }
        });
        layoutYProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("Y "+newValue);
                bottomPortYProperty.setValue(newValue.doubleValue()+getHeight());
                System.out.println(bottomPortYProperty);
            }
        });

        Circle circle = new Circle(5, Color.RED);
        circle.centerXProperty().bind(bottomPortXProperty);
        circle.centerYProperty().bind(bottomPortYProperty);
        circle.centerYProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("circle Y is now "+newValue);
            }
        });
        getChildren().add(circle);
    }


}
