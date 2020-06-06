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

public class GraphNode<V, E> {
    private V vertice;
    private E edgeLink;
    private GraphNode verticeLink;
    private boolean marked;

    public GraphNode() {
        vertice = null;
        edgeLink = null;
        verticeLink = null;
        marked = false;
    }

    public GraphNode(V a, GraphNode b) {
        this.vertice = a;
        this.verticeLink = b;
        edgeLink = null;
        marked = false;
    }

    public void setVertice(V vertice) { this.vertice = vertice; }
    public V getVertice() { return vertice; }
    public void setEdgeLink(E edgeLink) { this.edgeLink = edgeLink; }
    public E getEdgeLink() { return edgeLink; }
    public void setVerticeLink(GraphNode graphNode) { this.verticeLink = graphNode; }
    public GraphNode getVerticeLink() { return verticeLink; }
    public void setMarked(boolean marked) { this.marked = marked; }
    public boolean isMarked() { return marked; }

    public String toString() {
        return vertice + " --> ";
    }
}