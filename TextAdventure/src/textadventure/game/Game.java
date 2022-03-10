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
        Location corridorOfCastle =new Location("Corridor of Castle", "You have stepped in a romm that surrounds you with knight's armor with a sword and shield and the room seems to split off into two other rooms.", "long description of room");
        Location hauntedLibrary = new Location("Haunted Library", "Short description of room", "long description of room");
        Location draculasBedroom = new Location("Draculas Bedroom", "Short description of room", "long description of room");
        Location frankensteinsLab = new Location("Frankensteins Lab", "Short description of room", "long description of room");
        dragonLair = new Location("Dragon Lair", "Short description of room", "long description of room");

        Item itemShield = new Item("shield", "long description", false);
        itemSword = new Item("sword", "long description", false);
        Item itemJournal = new Item("journal", "long description", false);
        itemRedPotion = new Item("Red Potion", "long description", true);
        itemGreenPotion = new Item("Green Potion", "long description", true);

        corridorOfCastle.setExit("Haunted Library", hauntedLibrary);
        corridorOfCastle.setExit("Draculas Bedroom", draculasBedroom);
        hauntedLibrary.setExit("Frankensteins Lab", frankensteinsLab);
        hauntedLibrary.setExit("Corridor of Castle", corridorOfCastle);
        draculasBedroom.setExit("Corridor of Castle", corridorOfCastle);
        draculasBedroom.setExit("Dragon Lair", dragonLair);
        draculasBedroom.setExit("Frankensteins Lab", frankensteinsLab);
        frankensteinsLab.setExit("Haunted Library", hauntedLibrary);
        dragonLair.setExit("Draculas Bedroom", draculasBedroom);

        corridorOfCastle.setItem("Shield", itemShield);
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
        play();
        printInformation();
    }

    public void play() {
        while(true) {  
        	
        	
        Command command = parser.getCommand();
            try {
                cls_var.main(); 
            }catch(Exception e) {
                System.out.println(e); 
            }
            processCommand(command);
            printInformation();   
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
    }
    	System.out.println("You hit the dragon for "+ player.getDamage() + " hp"); 
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
}