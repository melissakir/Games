import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class StoreGame implements Game {
    private ArrayList<Item> items;
    Random rand = new Random();
    Player player = new Player("", 50);
    public String getGameName(){
        return "StoreGame";
    }

    public String getScore(){
        return Integer.toString(player.getMoney());
    }
    
    public StoreGame() {
        this.items = new ArrayList<Item>();
        this.items.add(new Fruit ("Apple", 3, 3));
        this.items.add(new Fruit ("Banana", 2, 2));
        this.items.add(new Fruit ("Orange", 2, 2));
        this.items.add(new Fruit ("Raspberries", 6, 6));
        this.items.add(new Fruit ("Blueberries", 6, 6));
        this.items.add(new Vegetable ("Broccoli", 8, 7.36));
        this.items.add(new Vegetable("Lettuce", 7, 6.44));
        this.items.add(new Vegetable ("Carrots", 7, 6.44));
        this.items.add(new Vegetable ("Onion", 8, 7.36));
        this.items.add(new Vegetable ("Mushrooms", 10, 9.20));
        this.items.add(new Snack ("Granola bar", 5, 4.75));
        this.items.add(new Snack ("Honey grahams", 4, 3.80));
        this.items.add(new Snack ("Popcorn", 4, 3.80));
        this.items.add(new Snack ("Chedder bunnies", 6, 5.70));
        this.items.add(new Snack ("Pretzels", 5, 4.75));
        this.items.add(new Bakery ("Bread", 12, 12));
        this.items.add(new Bakery ("Pie", 18, 18));
        this.items.add(new Bakery ("Cupcakes", 9, 9));
        this.items.add(new Bakery ("Cookies", 8, 8));
        this.items.add(new Bakery ("Brownies", 7, 7));

    }

    public void play(){
        while (player.getMoney() >= 4) {
            System.out.println("Welcome to Whole Foods.");
            System.out.println("You have $" + player.getMoney() + " to spend. You have to have at least $4 left to purchase a reusable bag when you are finished shopping");
            displayItems();
            System.out.println("Enter the number of the item you wish to purchase, or 0 to exit: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            
            if (choice == 0) {
                break;
            }
            
            Item selectedItem = getItem(choice);
            
            if (selectedItem == null) {
                System.out.println("Invalid selection. Please try again.");
            } else if (selectedItem.getSalePrice() < player.getMoney()) {
                System.out.println("You don't have enough money to purchase that item. Please try again.");
            } else {
                player.buyItem(selectedItem);
                System.out.println("You purchased " + selectedItem.getName() + " for $" + selectedItem.getSalePrice() + ".");
            }
            updatePrices();
        }
        
        System.out.println("Thanks for shopping, " + "! You have $" + player.getMoney() + " left.");
    }



    public void displayItems() {
        System.out.println("Here are the items available for purchase:");
        for (int i = 0; i < this.items.size(); i++) {
            Item item = this.items.get(i);
            System.out.println((i+1) + ". " + item.getName() + " ($" + item.getSalePrice() + ")");
        }
    }

    public void updatePrices() {
        // go through every item and maybe put it on sale
        int i = rand.nextInt(2);
        for (Item item: items) {
            if (i == 0){
                item.getSalePrice();
            }
            else if (i == 1) {
                item.getSalePrice();
            }
        }
    }

    public Item getItem(int index) {
        if (index < 1 || index > this.items.size()) {
          return null;
     }
     return this.items.get(index-1); 
    }


    public void writeHighScore(File myFile){
        try (BufferedReader br = new BufferedReader(new FileReader(myFile.getPath()))) {
            String firstLine = br.readLine();
            if (Integer.parseInt(firstLine) < player.getMoney()){
                FileWriter fw = new FileWriter(myFile);
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }

    }
}