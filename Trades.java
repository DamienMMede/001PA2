import java.util.ArrayList;
import java.util.Scanner;
import java.util.Calendar;

public class Trades
{
  Stock[] stockTrades;
  Scanner input = new Scanner(System.in);
  Calendar dateTime = Calendar.getInstance();
  ArrayList<String> tradingStmts = new ArrayList<String>();
  boolean invalidTrade = false;
  int size;
  //boolean done = false;
  
  public Trades()
  {
    //start();
  }
  
  public void start()
  {
    char decision;
    System.out.printf("Enter 'Y' to begin the trading system or 'N' to exit:");
    decision = input.next().charAt(0);
    if(decision == 'N')
    {
      System.out.printf("%nThank you! Exiting Program");
      System.exit(0);
    }
    processTrades();
    displayTrades();
  }
  
  public void processTrades()
  {
    double commission = 0.0, totalCost = 0.0, onlineFee = 5.95;
    int counter = 0;
    char onlineTrade, brokerAssisted, anotherPerson = 'Y';
    String stockPurchases, personName;
    do
    {
      counter++;
      System.out.printf("%nEnter %sthe trader\'s first and last name? ", counter > 1 ? "next " : "");
      //assign final output header to stockPurchases
      input.nextLine();
      stockPurchases = input.nextLine();
      System.out.printf("%nOnce you have your list of stocks, enter the number of stocks you want to purchase. You will be limited to the number you enter: ");
      while(!input.hasNextInt())
      {
        System.out.printf("%nInvalid. Enter the number of stocks you want to purchase: ");
        input.next();
      }
      size = input.nextInt();
      stockTrades = new Stock[size];
      for(int i = 0; i < size; i ++)
      {
        stockTrades[i] = new Stock();
        System.out.printf("%nWhat is the name of your stock? ");
        
        input.nextLine();
        //inputted name for stock
        String stockName = input.nextLine();
        stockTrades[i].setStockName(stockName);

        System.out.printf("%nHow many shares did you purchase? ");
        //inputted number of shares
        int shares;
        while(!input.hasNextInt())
        {
          System.out.println("Invalid. Please re-enter how many shares did you purchase: ");
          input.next();
        }
        shares = input.nextInt();
        stockTrades[i].setShares(shares);
        
        System.out.printf("%nWhat is the price per share? ");
        //inputted price of share
         while(!input.hasNextDouble())
        {
          System.out.println("Invalid. Please re-enter price per share: ");
          input.next();
        }
        double price = input.nextDouble();
        stockTrades[i].setSharePrice(price);
        
        stockTrades[i].calcStockCost();
        //calc the stock cost
        totalCost += stockTrades[i].getStockCost();
        
        System.out.printf("%nIs this an online trade? Enter 'Y' or 'N': ");
        onlineTrade = input.next().charAt(0);
        
        if(onlineTrade == 'Y'||onlineTrade =='y')
        {
         totalCost += onlineFee;
         commission = 0.0;
         stockTrades[i].online = true;
        }
        else
        {
          System.out.printf("%nIs this a broker assisted trade? Enter 'Y' or 'N': ");
          brokerAssisted = input.next().charAt(0);
          if(brokerAssisted == 'Y')
          {
            onlineFee = 0.0;
            commission = stockTrades[i].getStockCost() * .2;
            totalCost += commission;
            stockTrades[i].commission = true;
          }
          else if(brokerAssisted == 'n')
          {
            System.out.printf("%nINVALID TRADE TYPE! Exiting program.");
            invalidTrade = true;
            stockPurchases = null;
          }
        
        if(invalidTrade == true)
        {
          totalCost = 0.0;
          anotherPerson = 'n';
        }
       }
        tradingStmts.add(stockPurchases);
        stockTrades[i].total = totalCost;
        totalCost=0.0;
      }
      System.out.printf("%nWill trades continue for another person? Enter 'Y' or 'N': ");
      anotherPerson = input.next().charAt(0);
    }while(anotherPerson != 'n' );
    for(int i = 0; i < counter; i++)
    {
      System.out.printf(tradingStmts.get(i));
    }
  }
  //display information 
  public void displayTrades()
  {
    for(int i = 0; i <= size ; i++)
    {
      String date = String.format("%1$TB %1$Td, %1$TY", dateTime);
      System.out.printf("%n%nSTOCK PURCHASE FOR %S%nAS OF %S", tradingStmts.get(i), date);
      System.out.printf("%nStock: %S", stockTrades[i].getStockName());
      System.out.printf("%nNo. of Shares: %d", stockTrades[i].getShares());
      System.out.printf("%nPrice Per Share: $%,.2f", stockTrades[i].getSharePrice());
      System.out.printf("%nCost of Stock: $%,.2f", stockTrades[i].getStockCost());
      if(stockTrades[i].online == true)
      {
        System.out.printf("%nOnline Fee: 5.95");
         System.out.printf("%nCommission: 0.0");
      }
      else
      {
       System.out.printf("%nOnline Fee: 0.0");
       if(stockTrades[i].commission == true)
       {
         System.out.printf("%nCommission: %.2f", stockTrades[i].stockCost * .2);
       }
       else
       {
         System.out.printf("%nCommission: 0.0");
       }
      }
      System.out.printf("%nTotal Cost: $%.2f", stockTrades[i].total);
    }
    System.out.printf("%n%nAll trading statements habe been printed.");
  }
}
