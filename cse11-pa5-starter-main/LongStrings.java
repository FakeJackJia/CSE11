import tester.*;

class LongStrings{
    String[] longStrings(String[] arr, int n){
        String[] temp = new String[arr.length];
        int count = 0;

        for (int i = 0; i < arr.length; i++){
            if (arr[i].length() >= n){
                temp[count] = arr[i];
                count++;
            }
        }

        String[] output = new String[count];

        for (int x = 0; x < count; x++){
            output[x] = temp[x];
        }

        return output;
    }

    String[] arr1 = new String[]{"Hi","hello","word","pineapple"};
    String[] arr2 = new String[]{"eye","pie","pay"};
    String[] arr3 = new String[]{"1","2",""};

    void testlongStrings(Tester t){
        t.checkExpect(longStrings(arr1, 4), new String[]{"hello","word","pineapple"});
        t.checkExpect(longStrings(arr2,3), new String[]{"eye","pie","pay"});
        t.checkExpect(longStrings(arr2,4), new String[]{});
        t.checkExpect(longStrings(arr3, 1), new String[]{"1","2"});
    }


}

