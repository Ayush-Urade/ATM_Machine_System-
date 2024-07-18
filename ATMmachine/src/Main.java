//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

class Transaction {
     LocalDateTime timestamp;
    private float amount;
    private String type; // "deposit" or "withdraw"

    public Transaction(LocalDateTime timestamp, float amount, String type) {
        this.timestamp = timestamp;
        this.amount = amount;
        this.type = type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public float getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }
}

class ATM{

     int pin=1414;
     float balance;
     float deposit_cnt=0;
     float withdraw_cnt=0;
    Transaction[] transactions = new Transaction[100];
    int transactionCount = 0;

    public void chk_pin()
     {
         System.out.println("Enter Your PIN");
         Scanner sc = new Scanner(System.in);
         int p=sc.nextInt();

         if(pin==p)
         {
             menu();
         }
         else
         {
             System.out.println("Please enter valid print");
             chk_pin();
         }
     }

     public void menu()
     {
         System.out.println("1.Check Balance");
         System.out.println("2.withdraw");
         System.out.println("3.Deposit money");
         System.out.println("4.Statement");
         System.out.println("5.Exit");

         System.out.println("Enter your choice");
         Scanner sc= new Scanner(System.in);
         int op = sc.nextInt();

         if(op==1)
         {
             chk_bal();
         }
         else if(op==2)
         {
             withdraw();
         }
         else if(op==3)
         {
             deposit();
         }
         else if(op==4)
         {
             statement();
         }
         else if(op==5)
         {
             return;
         }
         else
         {
             System.out.println("Enter a valid choice");
         }


     }



     public void chk_bal()
     {
         System.out.println("Balance is :" +balance);
         menu();
     }

     public void withdraw()
     {
         System.out.println("Enter Amount");
         Scanner sc= new Scanner(System.in);
         float amt=sc.nextFloat();

         if(amt>balance)
         {
             System.out.println("Insufficient Balance");
             menu();
         }
         else {

             balance = balance - amt;
            // withdraw_cnt=withdraw_cnt+amt;

             System.out.println("Enter Your PIN");
             Scanner sc2 = new Scanner(System.in);
             float p2=sc2.nextInt();

             if(p2==pin) {
                 addTransaction(amt, "withdraw");
                 System.out.println("Amount withdraw successfully");
                 menu();
             }

         }

     }

     public void deposit()
     {
         System.out.println("Enter Amount");
         Scanner sc=new Scanner(System.in);
         float amt=sc.nextFloat();
         balance=balance+amt;
         //deposit_cnt=deposit_cnt+amt;
         addTransaction(amt, "deposit");
         System.out.println("Amount deposit successfully");
         menu();



     }

     public void statement(){

         System.out.println("Bank Statement");

         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
         LocalDateTime now = LocalDateTime.now();
         System.out.println(dtf.format(now));

        // System.out.println("Withdraw : "+ withdraw_cnt);
       //  System.out.println("Deposit : "+deposit_cnt);
        // System.out.println("Balance :"+balance);
         printTransactions(0, dtf);
         System.out.println("Current Balance: " + balance);

         menu();
     }

    public void addTransaction(float amount, String type) {
        if (transactionCount < transactions.length) {
            transactions[transactionCount++] = new Transaction(LocalDateTime.now(), amount, type);
        } else {
            System.out.println("Cannot add more transactions.");
        }
    }

    private void printTransactions(int index, DateTimeFormatter dtf) {
        if (index < transactionCount) {
            Transaction transaction = transactions[index];
            System.out.print("Type: " + transaction.getType());
            System.out.print(", Amount: " + transaction.getAmount());
            System.out.println(", Timestamp: " + dtf.format(transaction.getTimestamp()));
            printTransactions(index + 1, dtf);
        }
    }

}


public class Main {
    public static void main(String[] args) {

        ATM obj=new ATM();
        obj.chk_pin();
        }
    }
