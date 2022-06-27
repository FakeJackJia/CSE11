import tester.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

// Task 1.0 Make Queue generic
// Edit the following class declaration

class Stack<E> {
    // Task 1.0: Set up Fields
    // Your code here
    List<E> contents;
    Comparator<E> comp;

    Stack(List<E> contents, Comparator<E> comp){
        this.contents = contents;
        this.comp = comp;
    }

    // Task 1.1: push() method
    // Your code here
    void push(E element){
        this.contents.add(0,element);
    }

    // Task 1.2: pop() method
    // Your code here
    E pop(){
        E output = this.contents.get(0);
        this.contents.remove(0);
        return output;
    }

    // Task 1.3: findDuplicates() method
    // Your code here
    int findElements(E element){
        int count = 0;

        if (this.contents.size() == 0){
            return 0;
        }

        if(this.contents.indexOf(element) == -1){
            return 0;
        }

        else{
            Collections.sort(this.contents, comp);
            int position = this.contents.indexOf(element);
            count++;

            for(int i = position+1; i < this.contents.size(); i++){
                if(comp.compare(this.contents.get(i), element) != 0){
                    return count;
                }
                else{
                    count++;
                }
            }

            return count;
        }


    }

    // Task 1.4: removeDuplicates() method
    // Your code here
    boolean removeElements(E element, int num){
        int count = num;

        if(this.contents.indexOf(element) == -1){
            throw new NoSuchElementException(); // this line is on the stack
        }

        int occur = Collections.frequency(this.contents, element);

        if(occur <= num){
            for(int i = 0; i < this.contents.size(); i++){
                if(comp.compare(this.contents.get(i), element) == 0){
                    this.contents.remove(i);
                    i--;
                }
            }

            return true;
        }

        for(int i = 0; i < this.contents.size(); i++){
            if(comp.compare(this.contents.get(i), element) == 0){
                count--;
                this.contents.remove(i);
                i--;
                if(count == 0){
                    break;
                }
            }
        }

        // if(this.contents.indexOf(element) == -1){
        //     return true;
        // }
        
        return false;
    }

}

class ComparatorStack{
    void testFunction(Tester t){
        ArrayList<Integer> array = new ArrayList<Integer>();
        array.add(3);
        array.add(4);

        Stack<Integer> s = new Stack(array, new IntCompare());

        t.checkException(new NoSuchElementException(), s, "removeElements", 9,1); // this line is on the stack
    }
    



    /*
    class                 method                 this reference               other variables
    ComparatorStack       testFunction               ignore                     array :1 , s :2
    Stack<E>              removeElements              :2                         count = 1
    
    */
}

class IntCompare implements Comparator<Integer> {
    public int compare(Integer a, Integer b) {
        return a - b;
    }
}