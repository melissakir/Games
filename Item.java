import java.util.Random;

class Item {
    String name;
    double price;
    double salePrice;
    String category;
    
    public Item(String name, double price, double salePrice, String category) {
        this.name = name;
        this.price = price; 
        this.salePrice = salePrice;
        this.category = category;
    }
        
    public String getName() {
        return name;
    }

    double getSalePrice() {
        return price;
    }
}

class Fruit extends Item {
    public Fruit(String name, double price, double salePrice) {
        super(name, price, salePrice, "Fruits");
    }
}

class Vegetable extends Item {
    public Vegetable(String name, double price, double salePrice) {
        super(name, price, salePrice, "Vegetables");
    }

    @Override
    double getSalePrice() {
        Random rand = new Random();
        int i = rand.nextInt(2);
        if (i == 0){
            return salePrice;
        }
        else {
            return price;
        }
    }
}

class Snack extends Item {
    public Snack(String name, double price, double salePrice) {
        super(name, price, salePrice, "Snacks");
    }

    @Override
    double getSalePrice() {
        Random rand = new Random();
        int i = rand.nextInt(2);
        if (i == 0){
            return salePrice;
        }
        else {
            return price;
        }
    }
}

class Bakery extends Item {
    public Bakery(String name, double price, double salePrice) {
        super(name, price, salePrice, "Bakery");
    }
}