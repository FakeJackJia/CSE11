class WordFilter {
    // Task 3: WordFilter
    // Your code here
    public static void main(String[] args){
        if(args.length == 0){
            System.out.println("Empty Array");
        }

        String[] output = new String[args.length];
        boolean flag = false;
        int count = 0;

        for(String i : args){
            for (int x = 0; x < i.length()/2; x++){
                if(i.charAt(x) == i.charAt(i.length()-x-1)){
                    flag = true;
                }
                else{
                    flag = false;
                }
            }

            if (flag == true){
                output[count] = i;
                count++;
            }
        }

        String[] temp = new String[count];
        System.arraycopy(output, 0, temp, 0, count);

        for(String z : temp){
            System.out.print(z + " ");
        }
    }

}