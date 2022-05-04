import tester.*;

interface Number {
    int numerator();
    int denominator();
    Number add(Number other);
    Number multiply(Number other);
    String toText();
    double toDouble();
}

class WholeNumber implements Number{
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
}

class Fraction implements Number{
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
}

class Decimal implements Number{
    int mantissa;
    int exponent;

    Decimal(String number){
        this.mantissa = Integer.parseInt(number.replace(".", ""));
        this.exponent = number.length() - 1 - number.indexOf('.');
    }

    public int numerator(){
        return mantissa;
    }

    public int denominator(){
        return (int)Math.pow(10,Math.abs(exponent));
    }

    public Number add(Number other){
        Number answer = new Fraction(this.mantissa*other.denominator() + other.numerator()*this.denominator(), this.denominator()*other.denominator());
        return answer;
    }

    public Number multiply(Number other){
        Number answer = new Fraction(this.mantissa*other.numerator(), this.denominator()*other.denominator());
        return answer;
    }

    public String toText(){
        return Integer.toString(this.mantissa).substring(0,Integer.toString(this.mantissa).length()-Math.abs(exponent)) + "." + Integer.toString(this.mantissa).substring(Integer.toString(this.mantissa).length()-Math.abs(exponent));
    }

    public double toDouble(){
        return (double)mantissa/this.denominator();
    }
}


class Numbers{
    Number n1 = new WholeNumber(5);
    Number n2 = new WholeNumber(7);
    Number n3 = new Fraction(7, 2);
    Number n4 = new Fraction(1, 2);
    Number n5 = new Decimal("3.25");
    Number n6 = new Decimal("5.5");

    void testAdd(Tester t) {
        t.checkExpect(this.n1.add(this.n2).toDouble(), 12.0);
        t.checkExpect(this.n1.add(this.n3).toDouble(), 5 + 7.0/2.0);
        t.checkExpect(this.n3.add(this.n3).toDouble(), 7.0);
        t.checkExpect(this.n5.add(this.n4).toDouble(), 3.75);
    }

    void testMultiply(Tester t) {
        t.checkExpect(this.n1.multiply(this.n4).toDouble(), 2.5);
        t.checkExpect(this.n3.multiply(this.n4).toDouble(), 7.0/4.0);
        t.checkExpect(this.n6.multiply(this.n1).toDouble(), 27.5);
    }

    void testNumDem(Tester t) {
        t.checkExpect(this.n3.numerator(), 7);
        t.checkExpect(this.n1.numerator(), 5);
        t.checkExpect(this.n5.numerator(), 325);
        t.checkExpect(this.n4.denominator(), 2);
        t.checkExpect(this.n2.denominator(), 1);
        t.checkExpect(this.n6.denominator(), 10);
    }

    void testToString(Tester t) {
        t.checkExpect(this.n4.toText(), "1/2");
        t.checkExpect(this.n3.toText(), "7/2");
        t.checkExpect(this.n2.toText(), "7");
        t.checkExpect(this.n5.toText(), "3.25");
    }

    //Exploration
    double result1 = 0.1 + 0.2 + 0.3;
    double result2 = 0.1 + (0.2 + 0.3);

    Number num1 = new Fraction(1, 10);
    Number num2 = new Fraction(2, 10);
    Number num3 = new Fraction(3, 10);

    String result3 = num1.add(num2).add(num3).toText();
    String result4 = num1.add(num2.add(num3)).toText();
}