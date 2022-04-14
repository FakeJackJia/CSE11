class R{
    String field1;
    R field2;

    R(String field1, R field2){
        this.field1 = field1;
        this.field2 = field2;
    }

}

class ExamplesR{
    //R example = new R("I am field1", new R());
    // it is impossible
    /*
    To create a R object, it has to pass a reference to another R object to the parameter, which means that R object should have 
    a reference to another R object and it will go on and go on
     */
   
}