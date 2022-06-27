import tester.*;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

class Point {
  int x, y;
  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  double distance(Point other) {
    return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
  }
}

class PointCompare implements Comparator<Point>{
  public int compare(Point p1, Point p2){
    if (p1.y < p2.y){
      return -1;
    }
    else if (p1.y > p2.y){
      return 1;
    }
    else{
      if(p1.x == p2.x){
        return 0;
      }
      else if (p1.x < p2.x){
        return -1;
      }
      else{
        return 1;
      }
    }
  }


}

class PointDistanceCompare implements Comparator<Point>{
  public int compare(Point p1, Point p2){
    Point origin = new Point(0, 0);
    if (p1.distance(origin) == p2.distance(origin)){
      return 0;
    }
    else if (p1.distance(origin) > p2.distance(origin)){
      return 1;
    }
    else{
      return -1;
    }
  }


}

class StringCompare implements Comparator<String>{
  public int compare(String s1, String s2){
    return s1.compareTo(s2);
  }
}

class StringLengthCompare implements Comparator<String>{
  public int compare(String s1, String s2){
    if(s1.length() > s2.length()){
      return 1;
    }
    else if (s1.length() < s2.length()){
      return -1;
    }
    else{
      return 0;
    }
  }
  
}

class BooleanCompare implements Comparator<Boolean>{
  public int compare(Boolean b1, Boolean b2){
    if (b1 == true && b2 == false){
      return 1;
    }
    else if(b1 == false && b2 == true){
      return -1;
    }
    else{
      return 0;
    }
  }
}



class CompareLists{

  public static void main(String[] args){
    List<Boolean> test = new ArrayList<Boolean>();
    test.add(null);

    System.out.println(inOrder(test, new BooleanCompare()));
  }
  
  static <E> E minimum(List<E> l, Comparator<E> c){
    if(l.isEmpty() == true){
      return null;
    }

    Collections.sort(l, c);

    return l.get(0);
  }

  <E> E minimum(E[] array, Comparator<E> c){
    if(array.length == 0){
      return null;
    }

    Arrays.sort(array, c);

    return array[0];
  }

  static <E> List<E> greaterThan(List<E> l, Comparator<E> c, E element){
    l.add(element);
    Collections.sort(l, c);

    int position = 0;

    if (c instanceof StringLengthCompare){
      String e = (String)element;

      for (int i = 0; i < l.size(); i++){
        String a = (String)l.get(i);
        if(i == l.size() - 1 && e.length() == a.length()){
          break;
        }
  
        if(e.length() < a.length()){
          position = i;
          break;
        }
  
      }
      
    }
    else{

      for (int i = 0; i < l.size(); i++){
        if(i == l.size() - 1 && element == l.get(i)){
          break;
        }

        if(element == l.get(i) && element != l.get(i+1)){
          position = i+1;
          break;
        }

      }
    }

    if (position == 0){
      return new ArrayList<E>();
    }

    int length = l.size();
    List<E> temp = new ArrayList<>(l);

    //List<E> temp = l.subList(position, length);
    return temp.subList(position, length);

  }

  static <E> Boolean inOrder(List<E> l, Comparator<E> c){
    if (l.contains(null)){
      throw new IllegalArgumentException( "null value in list");
    }

    List<E> temp = new ArrayList<>(l);
    Collections.sort(temp,c);
    
    if (temp.equals(l)){
      return true;
    }
    else {
      return false;
    }

  }

  static <E> Boolean inOrder(E[] array, Comparator<E> c){
    if (Arrays.asList(array).contains(null)){
      throw new IllegalArgumentException( "null value in array");
    }
    
    E[] temp = array.clone();
    Arrays.sort(temp, c);

    if (Arrays.equals(temp,array)){
      return true;
    }
    else {
      return false;
    }

  }

  static <E> List<E> merge(Comparator<E> c, List<E> l1, List<E> l2){
    if(l1.contains(null) == true){
      throw new IllegalArgumentException( "null value in first list");
    }
    else if (l2.contains(null) == true){
      throw new IllegalArgumentException( "null value in second list");
    }

    l1.addAll(l2);

    Collections.sort(l1, c);

    return l1;  

  }


  //Test for comparators
  void testPointCompare(Tester t){
    Point p1 = new Point(0, 1);
    Point p2 = new Point(0,1);
    Point p3 = new Point(1,0);
    Point p4 = new Point(3,1);

    PointCompare pointCompare = new PointCompare();

    t.checkExpect(pointCompare.compare(p1, p2), 0);
    t.checkExpect(pointCompare.compare(p4, p3), 1);
    t.checkExpect(pointCompare.compare(p1, p4), -1);
    t.checkExpect(pointCompare.compare(p1, p3), 1);


  }

  void testPointDistanceCompare(Tester t){
    Point p1 = new Point(0, 1);
    Point p2 = new Point(0,1);
    Point p3 = new Point(1,0);
    Point p4 = new Point(3,1);

    PointDistanceCompare pointDistanceCompare = new PointDistanceCompare();

    t.checkExpect(pointDistanceCompare.compare(p1, p2), 0);
    t.checkExpect(pointDistanceCompare.compare(p4, p3), 1);
    t.checkExpect(pointDistanceCompare.compare(p1, p4), -1);
    t.checkExpect(pointDistanceCompare.compare(p1, p3), 0);


  }

  void testStringCompare(Tester t){
    String s1 = "hi";
    String s2 = "abc";
    String s3 = "abc";
    String s4 = "jack";

    StringCompare stringCompare = new StringCompare();

    t.checkExpect(stringCompare.compare(s1, s2), 7);
    t.checkExpect(stringCompare.compare(s4, s3), 9);
    t.checkExpect(stringCompare.compare(s1, s4), -2);
    t.checkExpect(stringCompare.compare(s2, s3), 0);


  }

  void testStringLengthCompare(Tester t){
    String s1 = "hi";
    String s2 = "abc";
    String s3 = "abc";
    String s4 = "jack";

    StringLengthCompare stringLengthCompare = new StringLengthCompare();

    t.checkExpect(stringLengthCompare.compare(s1, s2), -1);
    t.checkExpect(stringLengthCompare.compare(s4, s3), 1);
    t.checkExpect(stringLengthCompare.compare(s1, s4), -1);
    t.checkExpect(stringLengthCompare.compare(s2, s3), 0);



  }

  void testBooleanCompare(Tester t){
    Boolean b1 = true;
    Boolean b2 = true;
    Boolean b3 = false;
    Boolean b4 = false;

    BooleanCompare booleanCompare = new BooleanCompare();

    t.checkExpect(booleanCompare.compare(b1, b2), 0);
    t.checkExpect(booleanCompare.compare(b3, b4), 0);
    t.checkExpect(booleanCompare.compare(b1, b3), 1);
    t.checkExpect(booleanCompare.compare(b4, b2), -1);


  }

  //Test for List Methods
  void testMinimum(Tester t){
    List<Point> l1 = new ArrayList<Point>();
    l1.add(new Point(1, 0));
    l1.add(new Point(1, 1));
    l1.add(new Point(1, 2));

    List<Point> l2 = new ArrayList<Point>();

    t.checkExpect(minimum(l1, new PointCompare()), new Point(1,0));
    t.checkExpect(minimum(l2, new PointDistanceCompare()), null);
    t.checkExpect(minimum(l1, new PointDistanceCompare()), new Point(1,0));

    Point[] array1 = {new Point(0,0), new Point(1,0), new Point(1,2),};
    Point[] array2 = {};
  
    t.checkExpect(minimum(array1, new PointCompare()), new Point(0,0));
    t.checkExpect(minimum(array2, new PointDistanceCompare()), null);
    t.checkExpect(minimum(array1, new PointDistanceCompare()), new Point(0,0));
    
  }

  void testGreaterThan(Tester t){
    List<String> l1 = new ArrayList<String>();
    l1.add("hi");
    l1.add("hello");
    l1.add("jack");

    List<String> l3 = new ArrayList<String>();
    l3.add("hello");
    l3.add("hi");
    l3.add("jack");

    List<String> l4 = new ArrayList<String>();
    l4.add("jack");
    l4.add("hello");


    t.checkExpect(greaterThan(l1, new StringCompare(), "h"), l3);
    t.checkExpect(greaterThan(l3, new StringLengthCompare(), "hello"), new ArrayList<String>());
    t.checkExpect(greaterThan(l1, new StringLengthCompare(), "for"), l4);
    
  }

  void testInOrder(Tester t){
    List<String> l1 = new ArrayList<String>();
    l1.add("hi");
    l1.add("jack");
    l1.add("hello");
    

    List<Boolean> l2 = new ArrayList<Boolean>();
    l2.add(true);
    l2.add(false);
    l2.add(true);

    List<Boolean> l3 = new ArrayList<Boolean>();
    l3.add(true);
    l3.add(null);
    l3.add(true);

    t.checkExpect(inOrder(l1, new StringLengthCompare()), true);
    t.checkExpect(inOrder(l2, new BooleanCompare()), false);
    t.checkException(new IllegalArgumentException("null value in list"), this, "inOrder", l3, new BooleanCompare());

    Point[] array1 = {new Point(0,0), new Point(1,1), new Point(1,2),};
    Boolean[] array2 = {true};
    Boolean[] array3 = {true, null};

    t.checkExpect(inOrder(array1, new PointCompare()), true);
    t.checkExpect(inOrder(array2, new BooleanCompare()), true);
    t.checkException(new IllegalArgumentException("null value in array"), this, "inOrder", array3, new BooleanCompare());

  }

  void testMerge(Tester t){
    List<Boolean> l1 = new ArrayList<Boolean>();
    l1.add(null);
    l1.add(false);
    l1.add(true);

    List<Boolean> l2 = new ArrayList<Boolean>();
    l2.add(true);
    l2.add(false);
    l2.add(true);

    List<Boolean> l3 = new ArrayList<Boolean>();
    l3.add(true);
    l3.add(null);
    l3.add(true);

    List<Boolean> l4 = new ArrayList<Boolean>();
    l4.add(true);

    List<Boolean> l5 = new ArrayList<Boolean>();
    l5.add(false);
    l5.add(true);
    l5.add(true);
    l5.add(true);

    t.checkException(new IllegalArgumentException("null value in second list"), this, "merge", new BooleanCompare(), l2, l3);
    t.checkException(new IllegalArgumentException("null value in first list"), this, "merge", new BooleanCompare(), l1, l2);
    t.checkExpect(merge(new BooleanCompare(), l2, l4), l5);
    

  }

  
  
  


}
