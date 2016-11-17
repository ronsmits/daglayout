package nl.codetribe.daglayout;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 * Created by ronsmi on 11/17/2016.
 */
public class DagLayout extends Pane
{
    private static double vertical_distance = 50;
    private static double horizontal_distance = 50;

    private Vertex rootVertex;

    public DagLayout(Vertex rootVertex)
    {
        Group group = new Group();
        this.rootVertex = rootVertex;
        this.getChildren().add(group);
        group.getChildren().add(rootVertex);
        if (rootVertex.getKids() != null)
            for (Vertex v : rootVertex.getKids())
            addKids(v, group);
        //        doLayout(rootVertex);
    }

    private void addKids(Vertex vertex, Group group)
    {
            group.getChildren().add(vertex);
            Line line = new Line();
            group.getChildren().add(line);
            line.startYProperty().bind(vertex.getDad().getBottomYProperty());
            line.startXProperty().bind(vertex.getDad().getBottomXProperty());
            line.endYProperty().bind(vertex.getTopYProperty());
            line.endXProperty().bind(vertex.getTopXProperty());
        for(Vertex v : vertex.getKids())
            addKids(v, group);
    }

    @Override
    protected void layoutChildren()
    {
        doLayout(rootVertex);
    }

    private void doLayout(Vertex parent)
    {
        double width = parent.getBottomXProperty().doubleValue();
        for(Vertex vertex : parent.getKids()){
            vertex.relocate(width-(getWidth()/2), parent.getLayoutY()+parent.getHeight()+vertical_distance);
            width += vertex.getLayoutBounds().getWidth()+horizontal_distance;
            if(parent.getKids().size()>1)
                parent.relocate(((width-horizontal_distance)/2)-(parent.getWidth()/2), parent.getLayoutY());
            doLayout(vertex);
        }
    }
}
