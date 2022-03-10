package textadventure.game;
import java.util.HashMap;
import java.util.Set;

public class Player {
	private HashMap<String, Item> inventory;
    private Item item;
    private String name;
    private int hp;
    private int damage;
    
    //write a method to get the hashmap
    public Player(String name, int hp, int damage) {
        inventory = new HashMap<>();
        this.name = name; 
        this.hp = hp; 
        this.damage = damage; 
    }
    public String getName(){
        return name;
    }
    public int getHp(){
        return hp;
    }
    public int getDamage(){
        return damage;
    }
    
    public void setDamage(int newValue) {
    	damage = newValue; 
    }
    
    public void takeDamage(int damage) {
    	hp += damage; 
    }
    public HashMap <String, Item> getInventory() {
    	return inventory;
    }
    
    
    
    
    
    
    public String getInventoryString() {
        String returnString = "Player Inventory: ";
        Set<String> keys = inventory.keySet();
        for (String item: keys) {
            returnString += "\"" + item + "\" ";
        }
        return returnString;
    }
    
    public void setItem(String name, Item item) {
        inventory.put(name, item); 
        }
        
    public Item removeItem(String key) {
        return inventory.remove(key);
    }
    
    public Item getItem(String key) {
        return inventory.get(key);
    }
}
