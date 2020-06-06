/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpygroof;

/**
 *
 * @author Dina
 */
public class Edge {
    private GraphNode verticeLink;
    private Edge edgeLink;
    private int Height;

    public Edge() {
        verticeLink = null;
        edgeLink = null;
        Height = 0;
    }

    public Edge(GraphNode verticeLink, int Height, Edge edgeLink) {
        this.verticeLink = verticeLink;
        this.Height = Height;
        this.edgeLink = edgeLink;
    }

    public void setHeight(int edge) { this.Height = edge; }
    public int getHeight() { return Height; }
    public void setVerticeLink(GraphNode verticeLink) { this.verticeLink = verticeLink; }
    public GraphNode getVerticeLink() { return verticeLink; }
    public void setEdgeLink(Edge edgeLink) { this.edgeLink = edgeLink; }
    public Edge getEdgeLink() { return edgeLink; }

    public String toString() { return " -> " + verticeLink.getVertice() + " : " + Height; }
}
