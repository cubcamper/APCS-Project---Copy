/*
player.java - edited by george
edited to getWepName, getWepCode, setWep (lines 320 to 340)
note the different parameters being passed
*/

import java.util.Scanner;
import java.io.IOException;
public class Player extends Character{
   private Scanner in = new Scanner(System.in);
   private double maxhp = 0;
   private double hp = 0;
   private double xp = 0;
   private double xpmax = 10;
   private double xpmult = 0;
   private double dmgred = 0;
   private int str = 0;
   private int dtx = 0;
   private int intel = 0;
   private double dmgtkn = 0;
   private double armour = 0;
   private int strmod = 0;
   private int dtxmod = 0;
   private int intelmod = 0;
   private String atkname = "";
   private int money = 0;
   private double atkdmg = 0;
   private double dodge = 0;

   
   public Player(){
   super();
   }
      
   public void Player(String name, String race){
      this.name = name;
      race = race.toUpperCase();
      if(race.equals("HUMAN")){
         this.race = 1;
         this.raceS = "Human";
         this.maxhp = (this.race+1)*10;
         this.xpmult = 1.5;
         this.dmgred = 0;
         this.str = 2;
         this.dtx = 2;
         this.intel = 2;
         this.dodge = 10;
      }
      else if(race.equals("ORC")){
         this.race = 2;
         this.raceS = "Orc";
         this.maxhp = (this.race+1)*10;
         this.xpmult = .8;
         this.dmgred = .75;
         this.str = 4;
         this.dtx = 0;
         this.intel = 0;
         this.dodge = 0;
      }
      else if(race.equals("ELF")){
         this.race = 3;
         this.raceS = "Elf";
         this.maxhp = (this.race-2)*10;
         this.xpmult = 1.75;
         this.dmgred = -.5;
         this.dtx = 3;
         this.str = 1;
         this.intel = 2;
         this.dodge = 33;
      }
      else if(race.equals("DWARF")){
         this.race = 4;
         this.raceS = "Dwarf";
         this.maxhp = 25;
         this.xpmult = 1;
         this.dmgred = .9;
         this.dtx = 1;
         this.str = 2;
         this.intel = 2;
         this.dodge = 20;
      }
      lvl = 0;
      hp = maxhp;
      xp = 0;
      
   }
   public Player(String name, String raceS, int race, int lvl, int str,int dtx, int intel, double maxhp, double xpmult, double dmgred, double dodge, int money, double xp, String wepcode1, String wepcode2)throws IOException{
      Weapon w;
      this.name = name;
      this.lvl = lvl;
      this.raceS = raceS;
      this.race = race;
      this.str = str;
      this.dtx = dtx;
      this.maxhp = maxhp;
      this.xpmult = xpmult;
      this.dmgred = dmgred;
      this.dodge = dodge;
      this. money = money;
      this.xp = xp;
      String[] anArr = ["wepcode1", "wepcode2"];
         for (int i=0; i<2; i++) {
         w = new Weapon(anArr[i], true);
         this.itemcode[i] = w.getCode();
         this.wepname[i] = w.getName();
         this.wepdmg[i] = w.getDmg();
         this.weparmour[i] = w.getArmour();
         this.spclPwr[i] = w.getSpclPwr();
      }
      for(int i = 1; i <= this.lvl; i++){
         this.xpmax = this.xpmax+i*5;
      }
   }
   
   private static boolean miss;
   public void setDamage(double dmg, double armour){
      double hit = Math.random()*101;
      if(hit > dodge){
         double pen;
         if(race != 3){
            pen = dmgred - armour;
            if(pen <= 0){
               pen = 0;
            }
         }
         else{
            pen = dmgred;
         }
      
         hp = hp - dmg*(1.0-pen);
         this.dmgtkn = dmg*(1.0-pen);
         miss = false;
      }
      else{
         this.dmgtkn = 0;
         this.armour = 0;
         miss = true;
      }
   }
   public void setXp(double gain){
      xp = xp + gain*xpmult;
      Scanner in = new Scanner(System.in);
      if(xp >= xpmax){
         lvl++;
         xpmax = xpmax + lvl*5;
         xp = 0;
         maxhp = maxhp + 5;
         hp = maxhp;
         System.out.println("Put point into HP, DR, STR, or DTX?");
         String choice = in.nextLine();
         boolean finish = false;
         while(finish = false){
         
            if(choice.toUpperCase().equals("HP")){
               maxhp++;
               finish = true;
            }
            else if(choice.toUpperCase().equals("DR")){
               dmgred = dmgred - .01;
               finish = true;
            }
            else if(choice.toUpperCase().equals("STR")){
               str++;
               finish = true;
            }
            else if(choice.toUpperCase().equals("DTX")){
               dtx++;
               finish = true;
            }
            choice = in.nextLine();
            
         }
      }
            
   }


   public double playerAttack(){
      boolean finish = false;
      int answer = 0;
      double temp = 0;
      double mod = 0;
      do{
         System.out.println("||=======================================||");
         System.out.println("|| 1: "+wepname[0]+"                                                 ||");
         System.out.println("|| 2: "+wepname[1]+"                                                 ||");
         System.out.println("||=======================================||");
         answer = in.nextInt();
         switch(answer){
            case 1:
            
               if(itemcode[0].charAt(0) == '1'){
                  for(int i = 0; i < str; i++){
                     mod = mod + damageMod(str);
                  }
                  this.atkdmg = wepdmg[0] + mod;
               }
               else if(itemcode[0].charAt(0) == '2'){
                  for(int i = 0; i < dtx; i++){
                     mod = mod + damageMod(dtx);
                  }
                  this.atkdmg = wepdmg[0] + mod;
               }
               else if(itemcode[0].charAt(0) == '3'){
                  for(int i = 0; i < intel; i++){
                     mod = mod + damageMod(intel);
                  }
                  this.atkdmg = wepdmg[0] + mod;
               
               }
               atkname = wepname[0];
               armour = weparmour[0];
               
               finish = true;
               temp = this.atkdmg;
               break;
            case 2:
                
            
               if(itemcode[1].charAt(0) == '1'){
                  this.atkdmg = wepdmg[1] + strmod;
               }
               else if(itemcode[1].charAt(0) == '2'){
                  this.atkdmg = wepdmg[1] + dtxmod;
               }
               else if(itemcode[1].charAt(0) == '3'){
                  this.atkdmg = wepdmg[1] + intelmod;
               }
               atkname = wepname[1];
               armour = weparmour[1];
               finish = true;
               temp = this.atkdmg;
               break;
         
         }
      
         
      }while(finish == false);
      return temp;
   }
   public boolean getMiss(){
      return miss;
   }
   public String getName(){
      return name;
   }
   public String getAttack(){
      return atkname;
   }
      
   public double getHp(){
      return hp;
   }
   public void setHp(){
      hp = maxhp;
   }
   public void setHp(double gain){
      hp = hp + gain;
   }
      
   public double getXp(){
      return xp;
   }
      
   public int getLvl(){
      return lvl;
   }
   public double getDmg(){
      return dmgtkn;
   }
      
   public void getCharacter(){
      System.out.println("||--------------------------------------------------------||");
      System.out.println("||----------------------"+raceS+"--------------------------||");
      System.out.println("|| Hp : "+hp+"                                             ||");
      System.out.println("|| Xp Gain: "+xpmult*100+"%                                 ||");
      System.out.println("|| Str:  "+str+"                                              ||");
      System.out.println("|| Dtx: "+dtx+"                                              ||");
      System.out.println("|| Int:   "+intel+"                                              ||");
      System.out.println("|| Dodge: "+dodge+"                                        ||");
      System.out.println("|| Damage Reduction: "+dmgred*100+"%                   ||");
      System.out.println("||------------------------------------------------------- ||");      }
   public double getArmour(){
      return armour;
   }
   public String getRaceS(){
      return raceS;
   }
   public int getMoney(){
      return money;
   }
   public void setMoney(int increase){
      this.money = this.money + increase;
   }
   public int getRace(){
      return race;
   }
   public int getStr(){
      return str;
   }
   public int getDtx(){
      return dtx;
   }
   public int getIntel(){
      return intel;
   }
   public double getMaxHp(){
      return maxhp;
   }
   public double getXpMult(){
      return xpmult;
   }
   public double getDmgRed(){
      return dmgred;
   }
   public double getDodge(){
      return dodge;
   }
   public String getWepcode(int z){    //z will be 0 or 1, depending which wepcode u want, consolidating gwc0() and gwc1()
      return itemcode[z];
   }
   
   public String[] itemcode = {"1051", "1051"};
   public String[] wepname = {"Iron Knife of Immenent Dying", "Iron Knife of Immenent Dying"};
   public double[] wepdmg = {2.0, 2.0};
   public double[] weparmour = {0.3, 0.3};
   public char[] spclPwr = {'n', 'n'};
   
   
   
   public void setWeapon(Weapon w, int i){      //i will be 0 or 1, depending which weapon u want to set
      this.itemcode[i] = w.getCode();
      this.wepname[i] = w.getName();
      this.wepdmg[i] = w.getDmg();
      this.weparmour[i] = w.getArmour();
      this.spclPwr[i] = w.getSpclPwr();
   }
   
   public String getWepName(int q){    //q will be zero or 1, depending which WepName u want, consolidating gwc0() and gwc1()
      return wepname[q];
   }
   
   public double damageMod(int lvl){
      double mod = 0;
      for(int i = 0; i < this.str; i++){
         mod = mod + 1/(Math.sqrt(lvl));
      }
      return mod;
   }
   
   public void characterCreate(){
         //Character Create
      boolean finish = false;
      String answer = "";
      while(finish == false){
         System.out.println("||------------Character Creation----------||");
         System.out.println("||-------------------------------------------------||");
         System.out.println("||-----------What is your name?---------||");
         name = in.nextLine();
         System.out.println("||---------------Your name is---------------||");
         System.out.print("||");
         for(double i = name.length()*2.5; i > 0; i--){
            System.out.print("-");
         }
         System.out.print(name);
         for(double i = name.length()*2.5; i > 0; i--){
            System.out.print("-");
         }
         System.out.println("||");
         System.out.println("||-------------------------------------------------||");
         System.out.println("|| Yes: 1 -------------------------------------||");
         System.out.println("|| No:   2 -------------------------------------||");
         System.out.println("||-------------------------------------------------||");
         if(in.nextInt() == 1){
            this.name = name;
            finish = true;
         }
      }
      finish = false;
      System.out.println("||--------------------------------------------------------||");
      System.out.println("||Are you an Human, Elf, Orc, or Dwarf?||");
      System.out.println("||--------------------------------------------------------||");
          
      while(finish == false){
         answer = in.nextLine();
         if(answer.toUpperCase().equals("HUMAN")){
            System.out.println("||--------------------------------------------------------||");
            System.out.println("||-----------------------Human-----------------------||");
            System.out.println("|| Hp : 20                                             ||");
            System.out.println("|| Xp Gain: 150%                                 ||");
            System.out.println("|| Str:  2                                              ||");
            System.out.println("|| Dtx: 2                                              ||");
            System.out.println("|| Int:   2                                              ||");
            System.out.println("|| Dodge: 10                                        ||");
            System.out.println("|| Damage Reduction: 0%                    ||");
            System.out.println("||------------------------------------------------------- ||");
            Player(name, answer);
            finish = true;
         }
         else if(answer.toUpperCase().equals("ORC")){
            System.out.println("||--------------------------------------------------------||");
            System.out.println("||-----------------------Orc----------------------------||");
            System.out.println("|| Hp : 30                                             ||");
            System.out.println("|| Xp Gain: 8  0%                                 ||");
            System.out.println("|| Str:  4                                              ||");
            System.out.println("|| Dtx: 0                                              ||");
            System.out.println("|| Int:   0                                              ||");
            System.out.println("|| Dodge: 0                                          ||");
            System.out.println("|| Damage Reduction: 25%                   ||");
            System.out.println("||------------------------------------------------------- ||");
            Player(name, answer);
            finish = true;
         }
         else if(answer.toUpperCase().equals("ELF")){
            System.out.println("||--------------------------------------------------------||");
            System.out.println("||-----------------------Elf-----------------------------||");
            System.out.println("|| Hp : 10                                             ||");
            System.out.println("|| Xp Gain: 175%                                 ||");
            System.out.println("|| Str:  0                                              ||");
            System.out.println("|| Dtx: 4                                              ||");
            System.out.println("|| Int:   2                                              ||");
            System.out.println("|| Dodge: 33                                        ||");
            System.out.println("|| Damage Reduction: -5%                   ||");
            System.out.println("||------------------------------------------------------- ||");
            Player(name, answer);
            finish = true;
         }
         else if(answer.toUpperCase().equals("DWARF")){
            System.out.println("||--------------------------------------------------------||");
            System.out.println("||-----------------------Dwarf-------------------------||");
            System.out.println("|| Hp : 25                                             ||");
            System.out.println("|| Xp Gain: 100%                                 ||");
            System.out.println("|| Str:  2                                              ||");
            System.out.println("|| Dtx: 1                                              ||");
            System.out.println("|| Int:   2                                              ||");
            System.out.println("|| Dodge: 10                                        ||");
            System.out.println("|| Damage Reduction: 10%                   ||");
            System.out.println("||------------------------------------------------------- ||");
            Player(name, answer);
            finish = true;
         }
      }
      
   }
}


