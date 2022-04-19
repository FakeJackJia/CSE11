class Line {
    
    // Task 1: computeY method
    // Your code here
    int computeY(int m, int x, int c){
         /*
        This method called computeY would take three integer variables m,x,c as parameter and put into the expression
        to calculate the y then return the value

        For the parameter, as long as they are within the range of int value which is -2,147,483,648 to 2,147,483,647 would be fine
        */ 
        int y = m * x + c;

        return y;
    }
    // Task 1: calculateSlope method
    // Your code here
    double calculateSlope(double x1, double y1, double x2, double y2){
         /*
        This method called calculateSlope would take four double variables x1,y1,x2,y2 as parameter and put into the expression
        to calculate the slope then return the value

        For the parameter, as long as they are within the range of double type would be fine
        */ 
        double slope = (y2 - y1)/(x2 - x1);

        return slope;

    }

    // Task 1: Add tests here
    // Your code here

    int computeYtest = computeY(1, 2, 3);
    int computeYtest2 = computeY(-1,-2, 0);
    double calculateSlopeTest = calculateSlope(1.123456, 3.231, 2.3232, 3.4242); 
    double calculateSlopeTest2 = calculateSlope(-1.1,-3.2,1.3,-4.0);

    double calculateSlopeTestError = calculateSlope(1.1,2.32,1.1,2.32); 

    
}
