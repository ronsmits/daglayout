package nl.codetribe.daglayout;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * Created by ronsmits on 15/11/2016.
 */
public class Vertex extends Pane {
    private Text textNode;
    private Circle bottomPort;
    private Circle topPort;
    private List<Vertex> kids = new ArrayList<>();
    private Vertex dad;


    private final static double textHorizontalPadding = 25;
    private final static double textVerticalPadding = 25;
    private final static double circleRadius = 2;

    private final DoubleProperty bottomXProperty = new SimpleDoubleProperty();
    private final DoubleProperty bottomYProperty = new SimpleDoubleProperty();

    private final DoubleProperty topXProperty = new SimpleDoubleProperty();


    private final DoubleProperty topYProperty = new SimpleDoubleProperty();
    private boolean layedOut;

    public Vertex(String label){
        this(label, null);
    }
    public Vertex(String label, Vertex dad) {
        textNode = new Text(label);
        createPorts();
        setStyle("-fx-background-color: cadetblue; -fx-border-color: black;-fx-arc-height: 15px; -fx-arc-width: 15px");
        getChildren().addAll(textNode, bottomPort, topPort);
        setPortProperties();

        if (dad == null){
            getProperties().put("level", 0);
        } else
            getProperties().put("level", dad.getLevel()+1);
    }

    private void setPortProperties() {
        bottomYProperty.bind(layoutYProperty().add(heightProperty()));
        bottomXProperty.bind(layoutXProperty().add(widthProperty().divide(2)));
        topXProperty.bind(bottomXProperty);
        topYProperty.bind(layoutYProperty());
    }

    private void createPorts() {
        bottomPort = new Circle(circleRadius, Color.RED);
        bottomPort.setStyle("-fx-fill: red");
        topPort = new Circle(circleRadius, Color.RED);
        topPort.setStyle("-fx-fill: red");
    }

    public int getLevel(){
        return (Integer) getProperties().get("level");
    }

    @Override
    protected void layoutChildren() {
        Bounds layoutBounds = textNode.getLayoutBounds();
        double textX = (getWidth() / 2) - (layoutBounds.getWidth() / 2);
        double textY = (getHeight() / 2) - (layoutBounds.getHeight() / 2);
        textNode.resizeRelocate(textX, textY, layoutBounds.getWidth(), layoutBounds.getHeight());

        Bounds bottomPortLayoutBounds = bottomPort.getLayoutBounds();
        double circleX = (getWidth() / 2) - (bottomPortLayoutBounds.getWidth() / 2);
        double circleY = getHeight() - (bottomPortLayoutBounds.getHeight() / 2);
        bottomPort.resizeRelocate(circleX, circleY, bottomPortLayoutBounds.getWidth(), bottomPortLayoutBounds.getHeight());

        Bounds topPortLayoutBounds = topPort.getLayoutBounds();
        circleY = 0 - (topPortLayoutBounds.getHeight() / 2);
        topPort.resizeRelocate(circleX, circleY, topPortLayoutBounds.getWidth(), topPortLayoutBounds.getHeight());
    }

    public DoubleProperty getTopXProperty() {
        return topXProperty;
    }


    public DoubleProperty getTopYProperty() {
        return topYProperty;
    }

    public DoubleProperty getBottomXProperty() {
        return bottomXProperty;
    }

    public DoubleProperty getBottomYProperty() {
        return bottomYProperty;
    }

    @Override
    protected double computePrefWidth(double height) {
        return textNode.prefWidth(height) + (textHorizontalPadding * 2);
    }

    @Override
    protected double computePrefHeight(double width) {
        return textNode.prefHeight(width) + (textVerticalPadding + 2);
    }

    @Override
    protected double computeMaxWidth(double height) {
        return computePrefWidth(height);
    }

    @Override
    protected double computeMaxHeight(double width) {
        return computePrefHeight(width);
    }

    @Override
    protected double computeMinWidth(double height) {
        return computePrefWidth(height);
    }

    @Override
    protected double computeMinHeight(double width) {
        return computePrefHeight(width);
    }

    @Override
    public String toString()
    {
        return String.format("Vertex[text: %s, X: %f, Y: %f, width: %f, height: %f]", textNode.getText(), layoutXProperty().doubleValue(),
            layoutYProperty().doubleValue(), widthProperty().doubleValue(), heightProperty().doubleValue());
    }

    public List<Vertex> getKids()
    {
        return kids;
    }

    public void setKids(List<Vertex> kids)
    {
        this.kids = kids;
    }

    public Vertex getDad()
    {
        return dad;
    }

    public void setDad(Vertex dad)
    {
        this.dad = dad;
    }

    public void addKid(Vertex vertex)
    {
        vertex.setDad(this);
        vertex.setLevel(getLevel()+1);
        kids.add(vertex);
    }

    public void setLevel(int level)
    {
        getProperties().put("level", level);
    }

    public boolean isLayedOut()
    {
        return layedOut;
    }

    public void setLayedOut(boolean layedOut)
    {
        this.layedOut = layedOut;
    }

    public double getKidsSize() {
        double size =0;
        for (Vertex kid : kids)
        {
            size+=kid.getWidth();
        }
        return size;

    }
}
