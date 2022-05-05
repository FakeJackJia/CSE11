import tester.*;

class Pair{
  int a;
  int b;

  Pair(int a, int b){
    this.a = a;
    this.b = b;
  }
}

class ArrayExamples{
  static String joinWith(String[] arr, String s){
    String output = "";

    if (arr.length == 0){
      return output;
    }

    for (int i = 0; i < arr.length-1; i++){
      output = output + arr[i] + s;
    }

    output = output + arr[arr.length-1];
    
    return output;
  }

  static boolean somethingFalse(boolean[] arr){
    if (arr.length == 0){
      return false;
    }

    for (boolean i : arr){
      if (i == false){
        return true;
      }
    }

    return false;
  }

  static int countWithinRange(double[] arr, double low, double high){
    int count = 0;

    if (arr.length == 0){
      return count;
    }

    for (double i : arr){
      if ( i <= high && i >= low){
        count++;
      }
    }

    return count;
  }

  static double[] numsWithinRange(double[] arr, double low, double high){
    double[] output = new double[countWithinRange(arr, low, high)];
    int count = 0;

    if(arr.length == 0){
      return output;
    }

    for (int i = 0; i < arr.length; i++){
      if (arr[i] >= low && arr[i] <= high){
        output[count] = arr[i];
        count++;
      }
    }

    return output;
  }

  static Pair maxmin(int[] arr){
    int max = arr[0];
    int min = arr[0];
    Pair output = new Pair(min, max);

    for(int i = 1; i < arr.length; i++){
      if (arr[i] < min){
        min = arr[i];
      }

      if (arr[i] > max){
        max = arr[i];
      }
    }

    output.a = min;
    output.b = max;
    
    return output;
  }

  static String earliest(String[] arr){
    String output = arr[0];

    for (int i = 1; i < arr.length; i++){
      if (output.compareTo(arr[i]) <= 0){
        continue;
      }
      else if (output.compareTo(arr[i]) > 0){
        output = arr[i];
      }
    }

    return output;
  }

  //Test
  void testJoinWith(Tester t){
    String[] arr1 = {};
    String[] arr2 = {"J","a","c","k"};
    String[] arr3 = {"h","i"};

    t.checkExpect(ArrayExamples.joinWith(arr1, "."),"");
    t.checkExpect(ArrayExamples.joinWith(arr2, "~"),"J~a~c~k");
    t.checkExpect(ArrayExamples.joinWith(arr3, "."),"h.i");
  }

  void testSomethingFalse(Tester t){
    boolean[] arr1 = {};
    boolean[] arr2 = {true, true,true,true,true,true};
    boolean[] arr3 = {true, true, false};

    t.checkExpect(ArrayExamples.somethingFalse(arr1), false);
    t.checkExpect(ArrayExamples.somethingFalse(arr2), false);
    t.checkExpect(ArrayExamples.somethingFalse(arr3), true);
  }

  void testCountWithinRange(Tester t){
    double[] arr1 = {};
    double[] arr2 = {1.1, 2.2, 3.3, 4.4};
    double[] arr3 = {0.1, 0.3};
    
    t.checkExpect(ArrayExamples.countWithinRange(arr1, 1.1, 2.2), 0);
    t.checkExpect(ArrayExamples.countWithinRange(arr2, 1.1, 3.3), 3);
    t.checkExpect(ArrayExamples.countWithinRange(arr3, 0.1, 0.3), 2);
  }

  void testNumsWithinRange(Tester t){
    double[] arr1 = {};
    double[] arr2 = {1.3, 2.9};
    double[] arr3 = {1.1, 2.3, 3.4, 4.5, 6.7, 9.5, 2.1, 1.3, 5.1};

    t.checkExpect(ArrayExamples.numsWithinRange(arr1, 1.1, 2.2), new double[]{});
    t.checkExpect(ArrayExamples.numsWithinRange(arr2, 1.1, 2.2), new double[]{1.3});
    t.checkExpect(ArrayExamples.numsWithinRange(arr3, 3.4, 5.1), new double[]{3.4, 4.5, 5.1});
  }

  void testMaxmin(Tester t){
    int[] arr1 = {4};
    int[] arr2 = {3, 9, 10, 11, 1};
    int[] arr3 = {0, 7};

    t.checkExpect(ArrayExamples.maxmin(arr1), new Pair(4, 4));
    t.checkExpect(ArrayExamples.maxmin(arr2), new Pair(1, 11));
    t.checkExpect(ArrayExamples.maxmin(arr3), new Pair(0, 7));
  }

  void testEarliest(Tester t){
    String[] arr1 = {"s"};
    String[] arr2 = {"a", "aa", "aaa", "aaaa"};
    String[] arr3 = {"cedwed", "fwef", "eqeqw", "bwef"};

    t.checkExpect(ArrayExamples.earliest(arr1), "s");
    t.checkExpect(ArrayExamples.earliest(arr2), "a");
    t.checkExpect(ArrayExamples.earliest(arr3), "bwef");
  }
}

class ProvidedArrayExamples {
  void testJoinWith(Tester t){
    String[] example1 = {"a", "b","c"};
    t.checkExpect(ArrayExamples.joinWith(example1, ":"), "a:b:c");
  }

  void testSomethingFalse(Tester t){
    boolean[] example1 = {true, false};
    t.checkExpect(ArrayExamples.somethingFalse(example1), true);
  }

  void testCountWithinRange(Tester t){
    double[] example = {0.1, 1.3, 2.6};
    t.checkExpect(ArrayExamples.countWithinRange(example, 1.1, 2.2), 1);
  }

  void testNumsWithinRange(Tester t){
    double[] example = {0.0, 3.0, 1.4, 1.5, 2.7, 9.1, 2.1};
    double[] expected = {1.4, 1.5, 2.1};
    t.checkExpect(ArrayExamples.numsWithinRange(example, 1.1, 2.2), expected);
  }

  void testMaxmin(Tester t){
    int[] example = {4, 5, 2, 3, 1};
    t.checkExpect(ArrayExamples.maxmin(example), new Pair(1, 5));
  }

  void testEarliest(Tester t){
    String[] example = {"aa", "aab", "abcd", "a"};
    t.checkExpect(ArrayExamples.earliest(example), "a");
  }
}