package textadventure.game;

public class Item {
    private String name;
    private String description;
    
    int damage;
    boolean drinkable;
    //boolean drinkable
    
    public Item(String name, String description, boolean drinkable) {
        this.name = name;
        this.description= description;
        damage = 0;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean getDrinkable() {
    	return drinkable;
    }
    //drinkable getter
    
    
    public String getDiscription() {
        return description;
    }
    
    public void setDamage(int value) {
        damage = value;
    }
    
    public int getDamage() {
        return damage;
    }
    
}