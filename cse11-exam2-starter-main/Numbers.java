interface Number {
    // Methods from PA4 - DO NOT MODIFY
    int numerator();
    int denominator();
    Number add(Number n);
    Number multiply(Number n);
    double toDouble();
    String toText();

    // Task 1.1: compare
    // Your code here
    int compare(Number other);    
}

class WholeNumber implements Number {
    int n;

    WholeNumber(int n){
        this.n = n;
    }

    public int numerator(){
        return n;
    }

    public int denominator(){
        return 1;
    }

    public Number add(Number other){
        Number answer = new Fraction(this.n*other.denominator() + other.numerator(), other.denominator());
        return answer;
    }

    public Number multiply(Number other){
        Number answer = new Fraction(this.n*other.numerator(), other.denominator());
        return answer;
    }

    public String toText(){
        return Integer.toString(n);
    }

    public double toDouble(){
        return (double)this.n;
    }

    // Task 1.1: compare 
    // Your code here
    public int compare(Number other){
        Number difference = new Fraction(this.n*other.denominator() - other.numerator(), other.denominator());
        if(difference.toDouble() > 0){
            return 1;
        }
        else if(difference.toDouble() < 0){
            return -1;
        }
        else{
            return 0;
        }
    }
}

class Fraction implements Number {
    int n;
    int d;

    Fraction(int n, int d){
        this.n = n;
        this.d = d;
    }

    public int numerator(){
        return n;
    }

    public int denominator(){
        return d;
    }

    public Number add(Number other){
        Number answer = new Fraction(this.n*other.denominator() + other.numerator()*this.d, this.d*other.denominator());
        return answer;
    }

    public Number multiply(Number other){
        Number answer = new Fraction(this.n*other.numerator(), this.d*other.denominator());
        return answer;
    }

    public String toText(){
        return Integer.toString(n) + "/" + Integer.toString(d);
    }

    public double toDouble(){
        return (double)n/d;
    }


    // Task 1.1: compare 
    // Your code here
    public int compare(Number other){
        Number difference = new Fraction(this.n*other.denominator() - other.numerator()*this.d, this.d*other.denominator());
        if(difference.toDouble() > 0){
            return 1;
        }
        else if(difference.toDouble() < 0){
            return -1;
        }
        else{
            return 0;
        }
    }
}