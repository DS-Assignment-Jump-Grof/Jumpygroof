/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpygroof;

import java.util.ArrayList;


/**
 *
 * @author Dina
 */

public class Graph<V, E> {
    private GraphNode head;

    public Graph() {
        head = null;
    }

    public boolean isEmpty () {
        return head == null;
    }

    public int getSize() {
        GraphNode currentNode = head;
        int count = 0;
        while (currentNode != null) {
            currentNode = currentNode.getVerticeLink();
            count++;
        }
        return count;
    }

    public void clear() {
        head = null;
    }

    public void showGraph() {
        GraphNode currentNode = head;
        while (currentNode != null) {
            System.out.println(currentNode.toString());
            Edge edge = (Edge) currentNode.getEdgeLink();
            while (edge != null) {
                System.out.print(edge.toString());
                edge = edge.getEdgeLink();
            }
            System.out.println();
            currentNode = currentNode.getVerticeLink();
        }
    }

    public GraphNode hasVertice(V vertice) {
        GraphNode currentNode = head;
        if (isEmpty()) {
            return null;
        } else {
            while (currentNode != null) {
                if (vertice==((V)currentNode.getVertice())) {
                    return currentNode;
                }
                currentNode = currentNode.getVerticeLink();
            }
        }
        return null;
    }

    public void addVertice(V a) {
        GraphNode newNode = new GraphNode(a, null);
        if (isEmpty()) {
            head = newNode;
        } else {
            GraphNode currentNode = head;
            while (currentNode.getVerticeLink() != null) currentNode = currentNode.getVerticeLink();
            currentNode.setVerticeLink(newNode);
        }
    }

    public void markVertice(V a) {
        if (hasVertice(a) != null) {
            GraphNode currentNode = head;
            while (currentNode != null) {
                if (a==((V)currentNode.getVertice())) {
                    currentNode.setMarked(true);
                    break;
                } else {
                    currentNode = currentNode.getVerticeLink();
                }
            }
        }
    }

    public boolean isMarked(V a) {
        if (hasVertice(a) != null) {
            GraphNode currentNode = head;
            while (currentNode != null) {
                if (a==((V)currentNode.getVertice())) {
                    return currentNode.isMarked();
                } else {
                    currentNode = currentNode.getVerticeLink();
                }
            }
        }
        return false;
    }

    public boolean addEdge(V from, V to, E Height) {
        if (hasVertice(from) == null || hasVertice(to) == null) {
            return false;
        } else {
            GraphNode currentNode = head;
            while (currentNode != null) {
                if (from==((V)currentNode.getVertice())) {
                    GraphNode toNode = hasVertice(to);
                    Edge newEdge = new Edge(toNode, (Integer)Height, null);
                    Edge currentEdge = (Edge) currentNode.getEdgeLink();
                    if (currentEdge != null) { // if there's edges already exist
                        while (currentEdge.getEdgeLink() != null) currentEdge = currentEdge.getEdgeLink();
                        currentEdge.setEdgeLink(newEdge);
                    } else { // if there are no existing edges
                        currentNode.setEdgeLink(newEdge);
                    }
                    return true;
                } 
                else {
                    currentNode = currentNode.getVerticeLink();
                }
            }
        }
        return false;
    }

    public boolean removeEdge(V from, V to) {
        if (hasVertice(from) == null || hasVertice(to) == null) {
            return false;
        } else {
            GraphNode currentNode = head;
            while (currentNode != null) {
                if (from==((V)currentNode.getVertice())) {
                    GraphNode toNode = hasVertice(to);
                    Edge previousEdge = null;
                    Edge currentEdge = (Edge) currentNode.getEdgeLink();
                    while (currentEdge != null) {
                        if (to==((V)currentEdge.getVerticeLink().getVertice())) {
                            if (previousEdge != null) { // If its second edge and after
                                previousEdge.setEdgeLink(currentEdge.getEdgeLink());
                            } else { // If its the first edge
                                currentNode.setEdgeLink(currentEdge.getEdgeLink());
                            }
                            return true;
                        } else {
                            previousEdge = currentEdge;
                            currentEdge = currentEdge.getEdgeLink();
                        }
                    }
                } else {
                    currentNode = currentNode.getVerticeLink();
                }
            }
        }
        return false;
    }

    public boolean isEdge(V from, V to) {
        if (hasVertice(from) == null || hasVertice(to) == null) {
            return false;
        } else {
            GraphNode currentNode = head;
            while (currentNode != null) {
                if (from==((V)currentNode.getVertice())) {
                    GraphNode toNode = hasVertice(to);
                    Edge currentEdge = (Edge) currentNode.getEdgeLink();
                    while (currentEdge != null) {
                        if (currentNode == toNode) return true;
                        currentEdge = currentEdge.getEdgeLink();
                    }
                } else {
                    currentNode = currentNode.getVerticeLink();
                }
            }
        }
        return false;
    }

    public int getHeight(V from, V to) {
        if (hasVertice(from) == null || hasVertice(to) == null) {
            return 0;
        } else {
            GraphNode currentNode = head;
            while (currentNode != null) {
                if (from ==((V)currentNode.getVertice())) {
                    GraphNode toNode = hasVertice(to);
                    Edge currentEdge = (Edge) currentNode.getEdgeLink();
                    while (currentEdge != null) {
                        if (currentEdge.getVerticeLink() == toNode) 
                            return currentEdge.getHeight();
                        currentEdge = currentEdge.getEdgeLink();
                    }
                } else {
                    currentNode = currentNode.getVerticeLink();
                }
            }
        }
        return 0;
    }

    public ArrayList getAdjacent(V a) {
        ArrayList<V> array = new ArrayList<> ();
        if(hasVertice(a) != null) {
            GraphNode currentNode = head;
            while (currentNode != null) {
                if(a==((V)currentNode.getVertice())) {
                    Edge edge = (Edge) currentNode.getEdgeLink();
                    while (edge != null) {
                        array.add((V)edge.getVerticeLink().getVertice());
                        edge = edge.getEdgeLink();
                    }
                    break;
                } else {
                    currentNode = currentNode.getVerticeLink();
                }
            }
        }
        return array;
    }
}