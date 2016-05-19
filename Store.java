import java.util.*;
import java.io.*;

public class Store{
   
   private String itemcode = "";
   private String name = "";
   private double dmg = 0;
   private double armour = 0;
   private String spclPwr = "none";
   private int price = 0;
   private Random r = new Random();
   private int cost = 0;
   private boolean purchase = false;

   
   public void Store(int money) throws IOException{
      purchase = false;
      Scanner s = new Scanner(System.in);
      Weapon[] w = new Weapon[3];
      int rand = (((int)(Math.random()*21)*10)+11);
      w[0] = new Weapon(rand);
      rand = (((int)(Math.random()*21)*10)+11);
      w[1] = new Weapon(rand);
      rand = (((int)(Math.random()*21)*10)+11);
      w[2] = new Weapon(rand);
      String storename = "";
      rand = r. nextInt(4);
      if(rand == 0){
         storename = "Jeff's";
      }
      if(rand == 1){
         storename = "Shawn's";
      }
      if(rand == 2){
         storename = "Ted's";
      }
      if(rand == 3){
         storename = "Matt's";
      }
      if(rand == 4){
         storename = "Carson's";
      }
      rand = r.nextInt(4);
      if(rand == 0){
         storename = storename+" Emporium";
      }
      if(rand == 1){
         storename = storename+" Caravan";
      }
      if(rand == 2){
         storename = storename+" Bazaar";
      }
      if(rand == 3){
         storename = storename+" Trade Depot";
      }
      if(rand == 4){
         storename = storename+" House of Wonders";
      }
      System.out.println("Hello, and welcome to "+storename+"!\nMy wares are better than any other's!\nThese are my items!");
      System.out.println("\n"+w[0].getName());
      System.out.println("\n"+w[1].getName());
      System.out.println("\n"+w[2].getName());
      System.out.println("Do any of these interest you?");
      if(s.nextLine().toUpperCase().equals("YES")){
         boolean finish = false;
         int i = 0;
         while(finish == false && i < 3){
            System.out.println("This one? "+w[i].getName());
            if(s.nextLine().toUpperCase().equals("YES")){
               if(money < w[i].getPrice()){
                  System.out.println("I think its out of your price range.");
                  finish = true;
               }
               else{
                  System.out.println("It costs this much: "+w[i].getPrice()+"\nIs that acceptable?");
                  if(s.nextLine().toUpperCase().equals("YES")){
                     cost = w[i].getPrice();
                     System.out.println("Thank you for your purchase");
                     w[0] = w[i];
                     w[1] = w[i];
                     w[2] = w[i];
                     purchase = true;
                     finish = true;
                  }
                  else{
                     System.out.println("Then let's move on.");
                  }
               }
            }
            i++;
         }
      }
      if(purchase == true){
         itemcode = w[1].getCode();
         name = w[1].getName();
         dmg = w[1].getDmg();
         armour = w[1].getArmour();
         spclPwr = w[1].getSpclPwr();
      }
   }
   public int getCost(){
      return cost;
   }
   public String getCode(){
      return itemcode;
   }
   public String getName(){
      return name;
   }
   public double getDmg(){
      return dmg;
   }
   public double getArmour(){
      return armour;
   }
   public char getSpclPwr(){
      return spclPwr;
   {
   public boolean getPurchase(){
      return purchase;
   }
}