class DesignRecipeExamples {

    //Problem 1
    int perimeter(int width, int height){
        return 2 * (width + height);
    }

    int perimeterTest1 = perimeter(1,2); //should return 6
    int perimeterTest2 = perimeter(4,5); //should return 18

    //Problem 2
    int borderArea(int width1, int height1, int width2, int height2){
        int area1 = width1 * height1;
        int area2 = width2 * height2;

        return Math.abs(area1 - area2);
    }

    int borderAreaTest1 = borderArea(1,2,3,4); //should return 10
    int borderAreaTest2 = borderArea(5,6,7,8); //should return 26

    //Problem 3
    int FahrenheitToCelsius(int Fahrenheit){
        /*
        This method called FahrenheitToCelsius would take integer variable Fahrenheit as parameter and put into the expression
        to calculate the integer value of celsius degree and then return the value

        For the parameter, as long as it is within the range of int value which is -2,147,483,648 to 2,147,483,647 would be fine
        */ 
        return (5 * (Fahrenheit - 32)) / 9;
    }

    //the difference would be the accuracy, mine returns an integer while the reference converter returns the value with several
    //decimal places
    int FahrenheitToCelsiusTest1 = FahrenheitToCelsius(30); //should return -1
    //same applies to this one
    int FahrenheitToCelsiusTest2 = FahrenheitToCelsius(90); //should return 32
    
    //Problem 4
    int inchesCombiner(int feet, int inches){
         /*
        This method called inchesCombiner would take two integer variables feet and inches as parameter and put into the expression
        to calculate the total integer value of inches and then return the value

        For the parameter, both of them should be positive and less than the upper bound of int value which is 2,147,483,647 
        */ 
        return feet * 12 + inches;
    }

    int inchesCombinerTest1 = inchesCombiner(10, 32); //should return 152
    int inchesCombinerTest2 = inchesCombiner(41, 50); //should return 542

    //the reason this test would output an incorrect output is that there is negative value passed in the parameter
    //though it is integer, but it could not be negative
    int inchesCombinerTest3 = inchesCombiner(-3, 4); 

}
