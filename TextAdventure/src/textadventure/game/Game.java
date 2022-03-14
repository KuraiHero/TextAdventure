package textadventure.game;

public class Game {
	private Parser parser;
    private Location currentLocation;
    private Player player; 
    private Player dragon; 
    private Item item;
    private CLS cls_var;
    
    Item itemRedPotion;
    Item itemGreenPotion;
    Item itemSword;
    Location dragonLair;

    public Game() {
        parser = new Parser();
        player= new Player("tyler", 100, 10);
        dragon= new Player("dragon", 1000, 30);
    }
    public void printTitle() {
    	System.out.println(" |   |                       |              |      |   |                           ");
    	System.out.println("  |   |   _` |  |   |  __ \\   __|   _ \\   _` |      |   |   _ \\   |   |   __|   _ \\");
    	System.out.println("  ___ |  (   |  |   |  |   |  |     __/  (   |      ___ |  (   |  |   | \\__ \\   __/");
    	System.out.println(" _|  _| \\__,_| \\__,_| _|  _| \\__| \\___| \\__,_|     _|  _| \\___/  \\__,_| ____/ \\___| ");
    	System.out.println("                                                                                    ");
    	System.out.println("Type: Play");
    }
    public void printIntro() {
    	System.out.println("You are an adventurer trying to help your poor villiage that has stumbled upon a haunted house.");
    	System.out.println("You don't have anything on you but are excited to go in to the house in search for riches.");
    	System.out.println("As you walk into the front doors you feel a breath of hot air on the back of your neck that sends chills down your spine.");
    	System.out.println("To add to the suspense the doors slam shut behind you.");
    	System.out.println("You realize that the only way out is to defeat some type of boss at the end of the house.");
    	System.out.println("You clench your fist and decide that you won't leave the house until you have beaten the boss and taken a prize home.")''
    }

    public static void main(String[] args) {
        Game game= new Game();
        game.setupGame();
        game.play();
    }

    public void printInformation() {
        System.out.println(currentLocation.getName()); 
        System.out.println(currentLocation.getShortDescription());
        System.out.println(currentLocation.getExitString());
        System.out.println(currentLocation.getInventoryString());
        System.out.println(player.getInventoryString());
        System.out.println(player.getDamage() + " is " + player.getName() + " damage");
    	System.out.println(player.getHp() + " is " + player.getName() + " HP");
    	if(currentLocation.equals(dragonLair)) {
    	System.out.println(dragon.getDamage() + " is the dragon's damage"); 
    	System.out.println(dragon.getHp() + " is the dragon's HP");
    	}
    }

    public void setupGame(){
        Location corridorOfCastle =new Location("Corridor of Castle", "You have stepped in a romm that surrounds you with knight's armor with a sword and shield and the room seems to split off into two other rooms.", "You walk into a haunted castle in the search for riches. The corridor of the castle is lined with knight’s armor on each side with swords and shields in the hands. There are two doors, one to the eas and the other to the west.");
        Location hauntedLibrary = new Location("Haunted Library", "The door that you have walked through lead you into a library with cobwebs and shelves of books everywhere.", "You have been lead to a library that looks as if i hasn’t been touched in years. It has cobwebs in every corner and books stacked on many shelves. In the back of the library contains a huge journal that says, The Life of Frankenstein.");
        Location draculasBedroom = new Location("Draculas Bedroom", "You walk into a room with old paintings illuminated by candles which all surround a coffin.", "Dracula’s room is filled with ancient paintings from Transylvania. The room is dimly lit with many candles scattered throughout the room. The coffin is sitting in the middle of the room with a skinned black bear in front of it.");
        Location frankensteinsLab = new Location("Frankensteins Lab", "It seems to be a secret laboratory that Frankenstein was birthed in.", "You walk into a laboratory that reminds you of the birthplace of Frankenstein. You see smashed glass and spilled liquid all over the ground. There is also two vials of green and red liquid in syringes.");
        dragonLair = new Location("Dragon Lair", "You have walked into a dungeon where you see a dragon sleeping on a pile of gold.", "You have walked into the dungeon of the sleeping dragon. The dragon is sleeping on a huge mount of gold but then awakens as it hears your footsteps. You are faced with your final task of defeating the dragon and leaving alive.");

        itemSword = new Item("sword", "A sowrd in the knight's hand that looks sharp enough to even pierce through metal", false);
        Item itemJournal = new Item("journal", "In my discoveries about Frankenstein I was able to complete two concoction to make someone stronger and another to make someone weaker. I remember correctly the red one makes your strength tenfold while the green one makes you as weak as a baby ", false);
        itemRedPotion = new Item("Red Potion", "Red bubbling potion that has ggives you a feeling of power", true);
        itemGreenPotion = new Item("Green Potion", "A green bubbling potion that makes you feel drained", true);

        corridorOfCastle.setExit("Haunted Library", hauntedLibrary);
        corridorOfCastle.setExit("Draculas Bedroom", draculasBedroom);
        hauntedLibrary.setExit("Frankensteins Lab", frankensteinsLab);
        hauntedLibrary.setExit("Corridor of Castle", corridorOfCastle);
        draculasBedroom.setExit("Corridor of Castle", corridorOfCastle);
        draculasBedroom.setExit("Dragon Lair", dragonLair);
        draculasBedroom.setExit("Frankensteins Lab", frankensteinsLab);
        frankensteinsLab.setExit("Haunted Library", hauntedLibrary);
        dragonLair.setExit("Draculas Bedroom", draculasBedroom);

        corridorOfCastle.setItem("Sword", itemSword);
        hauntedLibrary.setItem("Journal", itemJournal);
        frankensteinsLab.setItem("Red Potion", itemRedPotion);
        frankensteinsLab.setItem("Green Potion", itemGreenPotion);
        /*
         * // delete this line 
        Command command = parser.getCommand(); 
         */
        try {
            cls_var.main(); 
        }catch(Exception e) {
            System.out.println(e); 
        }
        currentLocation = corridorOfCastle;
        printTitle();
        printIntro()
        play();
        printInformation();
    }

    public void play() {
        while(loseGame() || winGame()) {  
        	
        	
        Command command = parser.getCommand();
            try {
                cls_var.main(); 
            }catch(Exception e) {
                System.out.println(e); 
            }
            processCommand(command);
            printInformation();   
        }
        if(player.getHp()<=0) {
        System.out.println("You lost");
        }
        else if(dragon.getHp()<=0) {
            System.out.println("You won");
        }
    }

    public void processCommand(Command command) {
        String commandWord = command.getCommandWord().toLowerCase();

        switch(commandWord) {
            case "speak":
                System.out.println("you wanted me to speak this word, " + command.getSecondWord());
                break;
            case "go":
                goLocation(command);
                break;
            case "grab":
                grab(command);
                break;
            case "drop":
                drop(command);
                break;
            case "inspect":
                inspect(command);
                break;
            case "drink":
                drink(command);
                break;
            case "toss":
            	toss(command);
            	break;
            case "help":
            	help(command);
            	break;
            case "hit":
            	hit(command);
            	break;
        }
    }
    
    public void help(Command command) {
    	System.out.println("The offered moves are go, grab, drop, inspect, drink, toss, hit");
    	System.out.println("Go is used to move from one location to another");
    	System.out.println("Grab is used to pick up objects in the room");
    	System.out.println("Drop is used to drop items from your inventory to the room");
    	System.out.println("Inspect is used to get a description of the object");
    	System.out.println("Drink is used to drink arinkable items in the game to boost your stats");
    	System.out.println("Toss is used to throw items at ememies to debuff their stats");
    	System.out.println("Hit is used to deal damage to ememies in the game");
    }
    public void hit(Command command) {
    	String toHit = " ";
    	if(!currentLocation.equals(dragonLair) || !command.hasSecondWord()) {
    		System.out.println("you can't fight anything");
    		return;
    }
    if (!command.hasLine()) {
    	toHit= command.getSecondWord();
    }
    else if(command.hasLine()) {
    	toHit = command.getSecondWord() + command.getLine();
    }
    if(!player.getInventory().containsKey("Sword")) {
    	System.out.println("You can't hit that");
        return;
    }
    else {
    	dragon.takeDamage(player.getDamage());
    	player.takeDamage(dragon.getDamage());
    }
    	System.out.println("You hit the dragon for "+ player.getDamage() + " hp"); 
    	System.out.println("The dragon hit you for "+ dragon.getDamage() + " hp"); 
    }

    public void drink(Command command) {
        String toDrink = ""; 
        if(!command.hasSecondWord()) {
            System.out.println("drink what?");
            return;
        }
        if (!command.hasLine()) {
            toDrink = command.getSecondWord();
        }
        else if (command.hasLine()) {
            toDrink = command.getSecondWord()+ command.getLine();
        }
        
        Item item = player.removeItem(toDrink);
        if(item == null) {
            System.out.println("You can't drink that");
            return;
        }
        else {
        	//check drinkable boolean
        	if(item.equals(itemRedPotion)) {
            player.setDamage(150);
        	}
        	else if(item.equals(itemGreenPotion)) {
        		player.setDamage(5);
        	}
        		
            //what happens when you drink this thing
            //if (getItems counter, and has it been used less than a certain amount of times
                // if less than desired amount, use GET 
                // if more than desired amount, use REMOVE
        }
       
    }
    
    public void grab(Command command) {
    	String toGrab = "";
        if(!command.hasSecondWord()) {
            System.out.println("grab what?");
            return;
        }
        if (!command.hasLine()) {
        	toGrab = command.getSecondWord();
        }
        
        else if (command.hasLine()) {
        	toGrab = command.getSecondWord() + command.getLine();
        }
        Item item = currentLocation.removeItem(toGrab);
        if(item == null) {
            System.out.println("you can't grab that");
            return;
        }
        else { 
            player.setItem(toGrab, item);
        }
    }
    
    
    public void toss(Command command) {
        String thingToToss = ""; 
        if(!command.hasSecondWord()) {
            System.out.println("toss what?");
            return;
        }
        if (!command.hasLine()) {
            thingToToss = command.getSecondWord();
        }
        else if (command.hasLine()) {
            thingToToss = command.getSecondWord()+ command.getLine();
        }
        
        
        Item itemToToss = player.removeItem(thingToToss);
        if(item != null) {
            System.out.println("You can't toss that");
            return;
        }
        else {
        	if(item.equals(itemRedPotion)) {
                dragon.setDamage(150);
            	}
            	else if(item.equals(itemGreenPotion)) {
            		dragon.setDamage(5);
            	}
            //what happens when you drink this thing
            //if (getItems counter, and has it been used less than a certain amount of times
                // if less than desired amount, use GET 
                // if more than desired amount, use REMOVE
        }
       
    }
        public void inspect(Command command) {
        String printString = "inspecting ";
        String thingToInspect = null;
        if(!command.hasSecondWord()) {
            System.out.println("inspect what?");
            return;
        }
        if (!command.hasLine()) {
            thingToInspect = command.getSecondWord();
        }
        else if (command.hasLine()) {
            thingToInspect = command.getSecondWord()+ command.getLine();
        }
        if(thingToInspect.equals(currentLocation.getName())) {
            printString += "the room: " + currentLocation.getName() + "\n" + currentLocation.getLongDescription();
        }
        else if (currentLocation.getItem(thingToInspect) != null) {
            printString += "the item: " + currentLocation.getItem(thingToInspect).getName() + "\n" + currentLocation.getItem(thingToInspect).getDiscription();
        }
        else if (player.getItem(thingToInspect) != null) {
            printString += "the item: " + player.getItem(thingToInspect).getName() + "\n" + player.getItem(thingToInspect).getDiscription();
        }
        else {
            printString += "\nYou can't inspect it";
        }
        System.out.println(printString);
    }



    public void drop(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("drop what?");
            return;
        }
        String item = command.getSecondWord();
        Item itemDrop = player.removeItem(item);
        if(itemDrop == null) {
            System.out.println("You can't drop that");
            return;
        }
        else{
            currentLocation.setItem(item, itemDrop);
        }
    }

    public void goLocation(Command command) {
        String direction = " ";
        if(!command.hasSecondWord()) {
            System.out.println("go where?");
            return;
        }
        if (!command.hasLine()) {
            direction = command.getSecondWord();
        }
        else if (command.hasLine()) {
            direction = command.getSecondWord() + command.getLine();
        }
        Location nextLocation = currentLocation.getExit(direction);
        if(nextLocation == null) {
            System.out.println("you can't go there");
        }
        else {
            currentLocation = nextLocation;
            /*             
            //delete these calls 
            System.out.println(currentLocation.getExitString());
            System.out.println(currentLocation.getShortDescription());
             */
        }
        currentLocation = nextLocation;
    }
        
        public boolean loseGame() {
        	if (player.getHp()<= 0) {
        			return false;
        }
        	else {
        		return true;
        	}
    }
        public boolean winGame() {
        	if (dragon.getHp()<= 0) {
        			return true;
        }
        	else {
        		return false;
        	}
    }
}