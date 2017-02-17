package mmm2;

/**
 *
 * @author PawełKuźmickiPrzemysławPrzybyt
 */
public class Animal {
    
    private int movement;
    private int proliferation;
    
    private int age;
    
    private int xPos, yPos;
    
    private int weight;

    
    public Animal(int xPos, int yPos, int movement, int proliferation, int startingAge){
        this.xPos = xPos;
        this.yPos = yPos;
        this.movement = movement;
        this.proliferation = proliferation;
        this.age = startingAge;
     
    }
    
    public Animal(){
        
    }
    

    /**
     * 
     * @param world
     * @param worldSize 
     */
    public void move(int[][] world, int worldSize){
        
        while(true){
            byte direction = (byte) (Math.random()*4);
            
            int futurePos = 0;
            switch(direction){
                case 0: futurePos = this.xPos + movement; break;
            
                case 1: futurePos = this.yPos - movement; break;
            
                case 2: futurePos = this.xPos - movement; break;
            
                case 3: futurePos = this.yPos + movement; break;
            }
            
            
            
            if(futurePos >=0 && futurePos < worldSize){
                switch(direction){
                    case 0: this.xPos += movement; break;
            
                    case 1: this.yPos -= movement; break;
            
                    case 2: this.xPos -= movement; break;
            
                    case 3: this.yPos += movement; break;
                }
             
            break; //while
            }
        }
    }

    public int getMovement(){
        return this.movement;
    }
    
    public void setMovement(int param){
        this.movement = param;
    }
    
    /** 
     * 
     * @return returns gow often an animal proliferates
     */
    public int getProliferation(){
        
        return this.proliferation;
    }
    
    public void setProliferation(int param){
        this.proliferation = param;
    }
    
    public int getXPos(){
        return this.xPos;
    }
    
    public void setXPos(int param){
        this.xPos = param;
    }
    
    public int getYPos(){
        return this.yPos;
    }
    
    public void setYPos(int param){
        this.yPos = param;
    }
    
    public int getAge(){
        return this.age;
    }
    
    public void increaseAge(){
        this.age += 1;
    }
    
    /**
     * 
     * @return returns weigth of the animal - for wolf = 10   for rabbit = 5
     */
    public int getWeight(){
        return this.weight;
    }
    
    public void setWeight(int param){
        this.weight = param;
    }
    
}
