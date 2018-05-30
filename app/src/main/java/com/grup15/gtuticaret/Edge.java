package com.grup15.gtuticaret;

import java.util.Comparator;

/** An Edge represents a relationship between two
 *  vertices.
 *  @author Koffman and Wolfgang
*/

public class Edge implements Comparator<Edge>{
  /**** BEGIN EXERCISE ****/
  // Data Fields
  /** The source vertix */
  private User source;

  /** The destination vertix */
  private String dest;

  /** The weight */
  private double weight;

  public Edge(){
    this.source = null;
    this.dest = null;
    weight = 0.0;
  }
  // Constructor
  /** Construct an Edge with a source of from
      and a destination of to. Set the weight
      to 1.0.
      @param - The source vertix
      @param  - The destination vertix
   */
  public Edge(User source, String dest) {
    this.source = new User(source);
    this.dest = dest;
    weight = 1000;
  }

  /** Construct a weighted edge with a source
      of from and a destination of to. Set the
      weight to w.
      @param - The source vertix
      @param - The destination vertix
      @param w - The weight
   */
  public Edge(User source, String dest, double w) {
    this.source = new User(source);
    this.dest = dest;
    weight = w;
  }

  // Methods
  /** Get the source
      @return The value of source
   */
  public User getSource() {
    return source;
  }

  /** Get the destination
      @return The value of dest
   */
  public String getDest() {
    return dest;
  }

  /** Get the weight
      @return the value of weight
   */
  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  /** Return a String representation of the edge
      @return A String representation of the edge
   */


  public String toString() {
    StringBuffer sb = new StringBuffer("[(");
    sb.append(source);
    sb.append(", ");
    sb.append(dest);
    sb.append("): ");
    sb.append(Double.toString(weight));
    sb.append("]");
    return sb.toString();
  }

  @Override
  public int compare(Edge first, Edge second) {
    return (int) (first.getWeight() - second.getWeight());
  }

  /** Return true if two edges are equal. Edges
      are equal if the source and destination
      are equal. Weight is not conidered.
      @param obj The object to compare to
      @return true if the edges have the same source
      and destination
   */
  public boolean equals(Object obj) {
    if (obj instanceof Edge) {
      Edge edge = (Edge) obj;
      return (source.equals(edge.source)
              && dest.equals(edge.dest));
    }
    else {
      return false;
    }
  }

  /** Return a hash code for an edge.  The hash
      code is the source shifted left 16 bits
      exclusive or with the dest
      @return a hash code for an edge
   */
  @Override
  public int hashCode() {
    int result;
    long temp;
    result = source.hashCode();
    result = 31 * result + dest.hashCode();
    temp = Double.doubleToLongBits(weight);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }
  /**** END EXERCISE ****/
}
