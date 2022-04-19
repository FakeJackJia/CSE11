import java.sql.Time;

// Task 2: TimeStamp class
// Your code here
class TimeStamp{
  String date;
  String month;
  String year;

  TimeStamp(String date, String month, String year){
    this.date = date;
    this.month = month;
    this.year = year;
  }

}

// Task 2: Account class
// Your code here
class Account{
  double availableBalance;
  TimeStamp timestamp;
  String accountID;

  Account(double availableBalance, TimeStamp timestamp, String accountID){
    this.availableBalance = availableBalance;
    this.timestamp = timestamp;
    this.accountID = accountID;
  }

  double deposit(double transac){
    this.availableBalance = this.availableBalance + transac;
    
    return this.availableBalance;
  }

  String withdraw(double money){
    if (this.availableBalance < money){
      return "Invalid Transaction";
    }
    else{
      this.availableBalance = this.availableBalance - money;
      
      return "Valid Transaction";
    }
  }
}

class BankTransfer {

  // Task 2: At least 1 TimeStamp object
  // Your code here
  TimeStamp timeStamp = new TimeStamp("Mon", "Jan", "2003");
  // Task 2: At least 1 Account object
  // Your code here
  Account accountObject = new Account(3000.000, timeStamp, "12312312");
  // Task 2: At least 1 call of deposit method
  // Your code here
  double currentMoney = accountObject.deposit(100.00);
  // // Task 2: At least 2 calls of withdraw method
  // // Your code here
  String test1 = accountObject.withdraw(5000);
  String test2 = accountObject.withdraw(3100);

  double currentMoney1 = accountObject.deposit(0);
}
