import java.util.Scanner;

public class Stock
{
  String stockName;
  int shares;
  double sharePrice, stockCost, total;
  Scanner input = new Scanner(System.in); 
  //to see if input is a valid input
  boolean online = false;
  boolean commission = false;
  
  public Stock()
  {
   
  }
  
  public Stock(String stockName)
  {
    
    setStockName(stockName);
//    setShares();
//    setSharePrice();
//    calcStockCost();
  }
  
  public void setStockName()
  {
    
  }
  
  public void setStockName(String stock)
  {
   stockName = stock;
  }
  public void setShares()
  {
    
  }
  
  public void setShares(int noShares)
  {
    shares = noShares;
  }

  public void setSharePrice()
  {
    
  }
  
  public void setSharePrice(double pricePerShare)
  {
    sharePrice = pricePerShare;
  }
  
  public void calcStockCost()
  {
    //IDK if this right
    stockCost = (double)shares * sharePrice;
  }
  
  public String getStockName()
  {
    return this.stockName;
  }
  
  public int getShares()
  {
    return this.shares;
  }
  
  public double getSharePrice()
  {
    return this.sharePrice;
  }
  
  public double getStockCost()
  {
    return this.stockCost;
  }
}