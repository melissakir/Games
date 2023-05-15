import java.util.ArrayList;

class Player {
    private String name;
    private int money;
    private ArrayList<Item> inventory;
    
    public Player(String name, int money) {
        this.name = name;
        this.money = money;
        this.inventory = new ArrayList<Item>();
    }
    
    public int getMoney() {
        return money;
    }
    
    public void buyItem(Item item) {
        this.money -= item.getSalePrice();
        this.inventory.add(item);
    }

    public String getName() {
        return null;
    }

    public class Inventory {
        public static void main(String[] args) {
            ArrayList<String> inventory = new ArrayList<String>();

            System.out.println("\nYour inventory contains:");

            for (String item: inventory)
                System.out.println(" " + item);
        }
    }
}