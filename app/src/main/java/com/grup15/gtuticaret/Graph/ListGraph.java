package com.grup15.gtuticaret.Graph;

import com.grup15.gtuticaret.Categories;
import com.grup15.gtuticaret.User;

import java.util.*;

/** A ListGraph is an extension of the AbstractGraph abstract class
*   that uses an array of lists to represent the edges.
*
*/

public class ListGraph
    implements Graph {

  // Data Field
  /** An array of Lists to contain the edges that
      originate with each vertex. */
  private Map<User,LinkedList<Edge>>  edges;

  /** Construct a graph with the specified number of
      vertices and directionality.

   */
  public ListGraph() {
    edges = new HashMap<>();
  }

  /** Determine whether an edge exists.
      @param source The source vertex
      @param dest The destination vertex
      @return true if there is an edge from source to dest
   */
  public boolean isEdge(User source, String dest) {
    return edges.get(source).contains(new Edge(source, dest));
  }

  /** Insert a new edge into the graph.
      @param edge The new edge
   */
  public void insert(Edge edge) {


    // Kullanıcı daha önce herhangi bir ürüne tıklamışsa
     if(edges.containsKey(edge.getSource())){
       // Aynı kategoriden daha önce bir ürüne tıkladıysa
       if(edges.get(edge.getSource()).contains(new Edge(edge.getSource(),edge.getDest()))) {
         for (Edge e : edges.get(edge.getSource()))
           if (e.getDest().equals(edge.getDest()))
             e.setWeight(e.getWeight() - 1); /*Kullanıcının kategoriye olan uzaklığı azaltılır.Bu da
                                                bu kategorinin kullanıcı tarafından daha fazla gezildiğini belirtir*/
      }
      else// Yeni bir kategorideki ürünlere tıklamışsa
         edges.get(edge.getSource()).add(edge);
    }
    else{// Kullanıcı daha önce herhangi bir ürüne tıklamamışsa
      edges.put(edge.getSource(),new LinkedList<Edge>());
      edges.get(edge.getSource()).add(edge);
    }



  }

  public Iterator < Edge > edgeIterator(User source) {
    return edges.get(source).iterator();
  }

  /** Get the edge between two vertices. If an
      edge does not exist, an Edge with a weight
      of Double.POSITIVE_INFINITY is returned.
      @param source The source
      @param dest The destination
      @return the edge between these two vertices
   */
  public Edge getEdge(User source, String dest) {
    Edge target =
        new Edge(source, dest, Double.POSITIVE_INFINITY);
    for (Edge edge : edges.get(source)) {
      if (edge.equals(target))
        return edge; // Desired edge found, return it.
    }
    // Assert: All edges for source checked.
    return target; // Desired edge not found.
  }

  public String shortestPath(User source){

    Random random = new Random();

    // Kullanıcının şimdiye kadar gezindiği ürünlerin kategorisine göre bir kategori önerilir
    if(edges.containsKey(source)) {
      Collections.sort(edges.get(source), new Edge());
      return edges.get(source).peek().getDest();
    }
    else
      return Categories.categories[random.nextInt(5)];




  }




}
