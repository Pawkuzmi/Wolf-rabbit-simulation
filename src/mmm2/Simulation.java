package mmm2;

import java.util.ArrayList;
import org.jfree.data.xy.XYSeries;

/**
 *
 * @author PawełKuźmickiPrzemysławPrzybyt
 */
public class Simulation {
    
    private int allTurns;
    private int[][] world;
    private int worldSize;
    private int wolvesNumber;
    private int rabbitNumber;
    private ArrayList<Wolf> wolves;
    private ArrayList<Rabbit> rabbits;  
    private int wolvesMovement;
    private int rabbitMovement;
    private int wolvesProliferation;
    private int rabbitProliferation;
    private int wolvesMaxHunger;
    
    private int wolfStartingAge;
    private int rabbitStartingAge;
    
    private ArrayList<Wolf> wolvesChildren;
    private ArrayList<Rabbit> rabbitsChildren;
    
    private final byte wolvesArrayConst;
    private final byte rabbitsArrayConst;
    private final byte wolvesChildrenArrayConst;
    private final byte rabbitsChildrenArrayConst;
    
    private final byte wolvesWeight;
    private final byte rabbitsWeight;
    
    private XYSeries wolvesSeries ;
    private XYSeries rabbitsSeries ;
    
    
    public Simulation(int allTurns, int worldSize, int wolvesNumber, int rabbitNumber, int wolvesMovement, int rabbitMovement,
            int wolvesProliferation, int rabbitProliferation, int wolvesHunger ){
        
        this.wolvesArrayConst = 0;
        this.rabbitsArrayConst = 1;
        this.wolvesChildrenArrayConst = 2;
        this.rabbitsChildrenArrayConst = 3;
        
        this.wolvesWeight = 10;
        this.rabbitsWeight = 5;
        
        this.allTurns = allTurns;
        this.worldSize = worldSize;
        this.world = new int[worldSize][worldSize];
        
        this.wolvesNumber = wolvesNumber;
        this.rabbitNumber = rabbitNumber;
        
        this.wolves = new ArrayList();
        this.rabbits = new ArrayList();
        
        this.wolvesMovement = wolvesMovement;
        this.rabbitMovement = rabbitMovement;
        
        this.wolvesProliferation = wolvesProliferation;
        this.rabbitProliferation = rabbitProliferation;
        
        this.wolvesMaxHunger = wolvesHunger;
        
        
        
        this.wolvesChildren = new ArrayList();
        this.rabbitsChildren = new ArrayList();
        
        wolvesSeries = new XYSeries("Wilki");
        rabbitsSeries = new XYSeries("Zające");
        
        setupWorld();
        
        for(int i = 1; i < this.allTurns; i++)
            makeTurn(i);

    }
    
    private void setupWorld() {
        
        for(int i = 0; i < worldSize; i++){
            for(int j = 0; j < worldSize; j++){
                world[i][j] = 0;
            }
        }
        
        for(int i = 0; i < wolvesNumber; i++){
            
            addAnimalToProperArray(new Wolf(), this.wolvesArrayConst);
            
        }
        
        
        for(int i = 0; i < rabbitNumber; i++){
            
            addAnimalToProperArray(new Rabbit(), this.rabbitsArrayConst);
        } 
        
    }

    private void makeTurn(int currentTurn) {
       
        killHungryWolves();
        //System.out.println("Aktualnie wilków " + wolves.size());
        //System.out.println("Aktualnie zajęcy " + rabbits.size());
        moveEveryAnimal();
        
        wolvesEatRabbits();
        
        proliferateEveryAnimal(currentTurn);
        
        addChildrenToWorld();
        
        addDataToChart(currentTurn);
        
        increaseAgeOfEveryAnimal();
        
     //   wolves.trimToSize();
        //System.out.println("Koniec tury " + currentTurn);
        
    }

    private void moveEveryAnimal() {
       
        for(Wolf wolf : wolves){
            
            world[ wolf.getXPos() ][ wolf.getYPos() ] = 0;
            
            wolf.move(world, worldSize);
                
            
            world[ wolf.getXPos() ][ wolf.getYPos() ] = wolf.getWeight();
          
        }
        
        for(Rabbit rabbit : rabbits){
            
            world[ rabbit.getXPos() ][ rabbit.getYPos() ] = 0;
            
            rabbit.move(world, worldSize);
            
            world[ rabbit.getXPos() ][ rabbit.getYPos() ] = rabbit.getWeight();
          
        }
    }
    
    private void wolvesEatRabbits(){
        
        for(Wolf wolf : wolves){
            if( world[ wolf.getXPos() ][ wolf.getYPos() ] == rabbitsWeight ){
                wolf.eat(rabbits);
            }
        }
        
    }

    private void proliferateEveryAnimal(int currentTurn) {
        
        if( tooManyAnimalsLiving() )
            return;
        
        for(int i = 0; i < wolves.size()/1; i++){
            
            if( wolves.get(i).getAge() % wolves.get(i).getProliferation() == 0){
                
                addAnimalToProperArray(new Wolf(), this.wolvesChildrenArrayConst);
            }
        }
        
        for(int i = 0; i < rabbits.size()/1; i++){
            
            
            
            if( rabbits.get(i).getAge() % rabbits.get(i).getProliferation() == 0){
                
                addAnimalToProperArray(new Rabbit(),this.rabbitsChildrenArrayConst);
            }
        }
    }
    
  
    /**Adds animal to array depending in constant
     * 
     * @param animal
     * @param finalConst 
     */
    private void addAnimalToProperArray(Animal animal, byte finalConst){
        
        int xPos, yPos;
            
        xPos = (int) (Math.random()*worldSize);
        yPos = (int) (Math.random()*worldSize);

        wolfStartingAge = (int)   (Math.random()*wolvesProliferation);
        rabbitStartingAge = (int) (Math.random()*rabbitProliferation);
        
        if(animal instanceof Wolf && finalConst == this.wolvesArrayConst){
            
            Wolf newWolf = new Wolf(xPos, yPos, wolvesMovement, wolvesProliferation, wolfStartingAge, wolvesMaxHunger);
            world[newWolf.getXPos()][newWolf.getYPos()] = newWolf.getWeight();
            
            wolves.add( newWolf);
            wolves.trimToSize();
        }
        else if(animal instanceof Rabbit && finalConst == this.rabbitsArrayConst){
            
            Rabbit newRabbit = new Rabbit(xPos, yPos, rabbitMovement, rabbitProliferation, rabbitStartingAge);
            world[newRabbit.getXPos()][newRabbit.getYPos()] = newRabbit.getWeight();
            
            rabbits.add(newRabbit);
            rabbits.trimToSize();
        }   
        else if(animal instanceof Wolf && finalConst == this.wolvesChildrenArrayConst){
            
            Wolf newWolf = new Wolf(xPos, yPos, wolvesMovement, wolvesProliferation, wolfStartingAge, wolvesMaxHunger);
            world[newWolf.getXPos()][newWolf.getYPos()] = newWolf.getWeight();
            
            wolvesChildren.add( newWolf);
            wolvesChildren.trimToSize();
        }
        
        else if(animal instanceof Rabbit && finalConst == this.rabbitsChildrenArrayConst){
            
            Rabbit newRabbit = new Rabbit(xPos, yPos, rabbitMovement, rabbitProliferation, rabbitStartingAge);
            world[newRabbit.getXPos()][newRabbit.getYPos()] = newRabbit.getWeight();
            
            rabbitsChildren.add( newRabbit);
            rabbitsChildren.trimToSize();
        }
    }
    
    private void addChildrenToWorld(){
        wolves.addAll(wolvesChildren);
        rabbits.addAll(rabbitsChildren);
        
        wolves.trimToSize();
        rabbits.trimToSize();
        
        wolvesChildren.clear();
        rabbitsChildren.clear();
    }

    private boolean isPositionFree(int x, int y) {
        return world[x][y] == 0;
    }
    
    

    private void killHungryWolves() {
        
        ArrayList<Wolf> wolvesToKill = new ArrayList();     
        
        for(Wolf wolf : wolves){
            
            if(wolf.getHunger() >= wolvesMaxHunger){
                wolvesToKill.add(wolf);
                
            }
            else
                wolf.increaseHungerBy(1);
            
        }
        wolves.removeAll(wolvesToKill);
        wolves.trimToSize();
    }
    
    private void increaseAgeOfEveryAnimal() {
        for(int i = 0; i < wolves.size(); i++)
            wolves.get(i).increaseAge();
        
        for(Rabbit rabbit : rabbits)
            rabbit.increaseAge();
        
    }
    
    private boolean tooManyAnimalsLiving(){
        
        if( wolves.size() + rabbits.size() >= worldSize*worldSize*10)
            return true;
        
        return false;
    }

    private void addDataToChart(int currentTurn) {
        
        wolvesSeries.add(currentTurn, wolves.size());
        rabbitsSeries.add(currentTurn, rabbits.size());
    }
    
    public XYSeries getWolvesSeries(){
        return wolvesSeries;
    }
    
    public XYSeries getRabbitsSeries(){
        return rabbitsSeries;
    }
}
