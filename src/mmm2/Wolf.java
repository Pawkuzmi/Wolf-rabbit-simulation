package mmm2;

import java.util.ArrayList;



/**
 *
 * @author PawełKuźmicki
 */
public class Wolf extends Animal implements AbleToBeHungry{
    
    private int turnsWithoutFood, hunger;


    public Wolf(int xPos, int yPos, int movement, int proliferation, int startingAge, int turns) {
        super(xPos, yPos, movement, proliferation, startingAge);
        
        this.turnsWithoutFood = turns;
        
        this.setWeight(10);
       
    }
    
    public Wolf(){
        super();
    }
    
    public void eat(ArrayList<Rabbit> rabbits){
        for(Animal rabbit : rabbits){
            
            if(rabbit.getXPos() == this.getXPos() && rabbit.getYPos() == this.getYPos()){
                rabbits.remove(rabbit);
                break;
            }
        }
        this.hunger--;
    }
    
    /**
     * 
     * @return returns actual value of hunger
     */
    public int getHunger(){
        return this.hunger;
    }
    
    public void increaseHungerBy(int param){
        this.hunger += param;
    }
    
    @Override
    public String toString(){
        return "Wilk: xPos " + getXPos()+"   yPos " + getYPos()+"     głod "+hunger+"    wiek "+getAge(); 
    }
    
    
}
