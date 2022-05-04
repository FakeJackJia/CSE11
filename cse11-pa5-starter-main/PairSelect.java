import tester.*;

class Pair{
    int a;
    int b;

    Pair(int a, int b){
        this.a = a;
        this.b = b;
    }


}


class PairSelect{
    int[] getAs(Pair[] array){
        int[] output = new int[array.length];

        for (int i = 0; i < array.length; i++){
            output[i] = array[i].a;
        }
        
        return output;
    }   

    Pair pair1 = new Pair(1, 2);
    Pair pair2 = new Pair(1, 2);
    Pair pair3 = new Pair(3, 1);
    Pair pair4 = new Pair(0, -2);
    Pair pair5 = new Pair(-4, 0);

    Pair[] pairArr1 = new Pair[]{pair1,pair2,pair3,pair4,pair5};
    Pair[] pairArr2 = new Pair[]{pair1,pair1,pair2,pair2,pair3};
    Pair[] pairArr3 = new Pair[]{pair5,pair4,pair3,pair2,pair1};
    Pair[] pairArr4 = new Pair[]{pair1,pair2,pair3,pair2,pair1};


    void testgetAs(Tester t){
        t.checkExpect(getAs(pairArr1), new int[]{1,1,3,0,-4});
        t.checkExpect(getAs(pairArr2), new int[]{1,1,1,1,3});
        t.checkExpect(getAs(pairArr3), new int[]{-4,0,3,1,1});
        t.checkExpect(getAs(pairArr4), new int[]{1,1,3,1,1});
    }

}