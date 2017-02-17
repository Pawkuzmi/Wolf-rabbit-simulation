package mmm2;



/**
 *
 * @author PawełKuźmickiPrzemysławPrzybyt
 */
public class Rabbit extends Animal{
    
    private static int amountOfRabbits;
    
    public Rabbit(int xPos, int yPos, int movement, int proliferation, int startingAge){
        
        super(xPos, yPos, movement, proliferation, startingAge);
        amountOfRabbits++;
        
        this.setWeight(5);
    }
    
    public Rabbit(){
        super();
    }
    
    @Override
    public String toString(){
        return "Zając: xPos " + getXPos()+"   yPos " + getYPos() ;
    }
    
  /*  public int getHunger(){
        return -1;
    }
    
    public void increaseHungerBy(int param){
        
    }*/
}
