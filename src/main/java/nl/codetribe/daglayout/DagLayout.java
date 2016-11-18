package nl.codetribe.daglayout;

import javafx.collections.transformation.FilteredList;
import javafx.scene.Group;
import javafx.scene.Node;
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
    private final Group group;

    public DagLayout(Vertex rootVertex)
    {
        group = new Group();
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
        System.out.println("called");
        doLayout(rootVertex);
    }

    private void doLayout(Vertex parent)
    {
        double height = parent.getBottomYProperty().doubleValue()+vertical_distance;
        double width = 0;

        FilteredList<Node> filtered = group.getChildren().filtered(node -> {
            if (node instanceof Vertex) {
                Vertex xx = (Vertex) node;
                if(xx.getLevel()==parent.getLevel()+1 /*&& xx.isLayedOut()*/){
                    return true;
                }

            }
            return false;
        });

        double highestX=0;
        for (Node node : filtered)
        {
            if(node.getLayoutX()>highestX) {
                highestX = node.getLayoutX();
                width=((Vertex)node).getLayoutBounds().getWidth();
            }

        }
        System.out.println("width "+width);
        width += highestX+horizontal_distance;
        System.out.println("width "+width);

        if(width==0) width = parent.getBottomXProperty().doubleValue()-(parent.getKids().get(0).getWidth()/2);
        if(parent.getKids().size()==1){
            width=parent.getBottomXProperty().doubleValue()-(parent.getKidsSize()/2);
        }
        for(Vertex vertex : parent.getKids()){
            vertex.relocate(width, height);
            vertex.setLayedOut(true);
            width += vertex.getLayoutBounds().getWidth()+horizontal_distance;
            if(parent.getKids().size()>0)
            {
                centerParent(parent);
                //parent.relocate(((width - horizontal_distance) / 2) - (parent.getWidth() / 2), parent.getLayoutY());
            }
            doLayout(vertex);
        }

    }
    private void centerParent(Vertex parent) {
        double distance = (parent.getKids().get(parent.getKids().size()-1).getLayoutX()) - (parent.getKids().get(0).getLayoutX());
        double X;
        X = parent.getKids().get(0).getLayoutX() +(distance/2);
        if (X > parent.getLayoutX())
            parent.relocate(X, parent.getLayoutY());
        else
            centerKids(parent);

        if(parent.getDad()!=null){
            centerParent(parent.getDad());
        }
    }

    private void centerKids(Vertex parent)
    {
        double size =parent.getKidsSize();
        size += horizontal_distance*(parent.getKids().size()-2);
        System.out.println("size"+size);
        double middle = size/2;
        double left = parent.getLayoutX()-middle;
        double d = left - parent.getKids().get(0).getLayoutX();
        System.out.println("d is "+d);
        if(d>0)
        for(Vertex v : parent.getKids())
        {
            System.out.println("v.getWidth "+v.getWidth());
            v.relocate(left, v.getLayoutY());
            System.out.println("left: "+left);
            left+=v.getWidth()+horizontal_distance;
            System.out.println("left: "+left);

        }
    }
}
