package textadventure.game;

public class Game {
	private Parser parser;
    private Location currentLocation;
    private Player player; 
    private Player dragon; 
    private Item item;
    private CLS cls_var;

    public Game() {
        parser = new Parser();
        player= new Player("tyler", 100, 10);
        dragon= new Player("dragon", 1000, 30);
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
    }

    public void setupGame(){
        Location corridorOfCastle =new Location("Corridor of Castle", "You have stepped in a romm that surrounds you with knight's armor with a sword and shield and the room seems to split off into two other rooms.", "long description of room");
        Location hauntedLibrary = new Location("Haunted Library", "Short description of room", "long description of room");
        Location draculasBedroom = new Location("Draculas Bedroom", "Short description of room", "long description of room");
        Location frankensteinsLab = new Location("Frankensteins Lab", "Short description of room", "long description of room");
        Location dragonLair = new Location("Dragon Lair", "Short description of room", "long description of room");

        Item itemShield = new Item("shield", "long description");
        Item itemPotion = new Item("Potion", "long description");
        

        corridorOfCastle.setExit("Haunted Library", hauntedLibrary);
        corridorOfCastle.setExit("Draculas Bedroom", draculasBedroom);
        hauntedLibrary.setExit("Frankensteins Lab", frankensteinsLab);
        hauntedLibrary.setExit("Corridor of Castle", corridorOfCastle);
        draculasBedroom.setExit("Corridor of Castle", corridorOfCastle);
        draculasBedroom.setExit("Dragon Lair", dragonLair);
        draculasBedroom.setExit("Frankensteins Lab", frankensteinsLab);
        frankensteinsLab.setExit("Haunted Library", hauntedLibrary);
        dragonLair.setExit("Draculas Bedroom", draculasBedroom);

        corridorOfCastle.setItem("shield", itemShield);
        frankensteinsLab.setItem("Potion", itemPotion);
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
        printInformation();
        play();
    }

    public void play() {
        while(true) {  
        	System.out.println(player.getDamage() + " is the amount of damage that " + player.getName() + " inflicts on enemies");
        	System.out.println(dragon.getDamage() + " is how much damage the dragon does"); 
        	player.setDamage(8888);
        	System.out.println(player.getDamage() + " is the amount of damage that " + player.getName() + " inflicts on enemies");
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
        }
    }

    public void drink(Command command) {
        String thingToDrink = ""; 
        if(!command.hasSecondWord()) {
            System.out.println("drink what?");
            return;
        }
        if (!command.hasLine()) {
            thingToDrink = command.getSecondWord();
        }
        else if (command.hasLine()) {
            thingToDrink = command.getSecondWord()+ command.getLine();
        }
        
        
        Item itemToDrink = player.removeItem(thingToDrink);
        if(item != null) {
            System.out.println("You can't drink that");
            return;
        }
        else {
            player.setDamage(150);
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

    public void grab(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("grab what?");
            return;
        }
        String item = command.getSecondWord();
        Item itemToGrab = currentLocation.removeItem(item);
        if(itemToGrab == null) {
            System.out.println("you can't grab that");
            return;
        }
        else {
            player.setItem(item, itemToGrab);
        }
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