import tester.*;

class ExampleArrays {
    

    // Task 1.1: averageWithThreshold
    // Your code here
    Number averageWithThreshold(Number[] arr, Number threshold){
        Number total = new WholeNumber(0);

        if (arr.length == 0){
            return total;
        }

        int count = 0;
        boolean flag = false;
        

        for (int i = 0; i < arr.length; i++){
            if(arr[i].toDouble() > threshold.toDouble()){
                total = total.add(arr[i]);
                flag = true;
                count++;
            }
        }

        /*
        arr = [3, 2/3, 10/9, 1], threshold = 4/3

        i start    i end     total start    total end    flag start   flag end   count start    count end
         0          1           0               3            false        true        0            1
         1          2           3               3            true         true        1            1
         2          3           3               3            true          true       1            1
         3          4           3               3            true          true        1           1



        
        */

        if (flag == false){
            return total;
        }
        else{
            Number temp = new Fraction(1, count);
            return total.multiply(temp);
        }

    }

    // Task 1.2: findGoodPairs
    // Your code here
    Pair[] findGoodPairs(Pair[] arr){
        Pair[] temp = new Pair[arr.length]; 
        int count = 0;
        boolean flag = false;

        for(Pair i : arr){
            if(i.a < i.b){
                temp[count] = i;
                count++;
                flag = true;
            }
        }

        if (flag == false){
            return new Pair[0];
        }
        else{
            Pair[] good = new Pair[count];
            System.arraycopy(temp, 0, good, 0, count);
            
            return good;
        }
    }

    // Task 1.3: mergePairs
    // Your code here
    Pair[] mergePairs(Pair[] p1, Pair[] p2){
        int len;

        if (p1.length < p2.length){
            len = p1.length;
        }
        else{
            len = p2.length;
        }

        Pair[] output = new Pair[len];
        int a;
        int b;

        for (int i = 0; i < len; i++){
            if (p1[i].a < p2[i].a){
                a = p1[i].a;
            }
            else{
                a = p2[i].a;
            }

            if (p1[i].b < p2[i].b){
                b = p2[i].b;
            }
            else{
                b = p1[i].b;
            }

            output[i] = new Pair(a,b);
        }

        return output;

    }

}

class Pair {
    // Task 1.2: Pair class
    // Your code here
    int a;
    int b;

    Pair(int a, int b){
        this.a = a;
        this.b = b;
    }
}

