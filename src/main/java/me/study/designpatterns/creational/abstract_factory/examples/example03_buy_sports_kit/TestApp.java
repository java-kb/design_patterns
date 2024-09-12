package me.study.designpatterns.creational.abstract_factory.examples.example03_buy_sports_kit;

public class TestApp {

    public static void main(String[] args) {
        ISportsFactory adidasFactory = getSportsFactory("adidas");
        ISportsFactory nikeFactory = getSportsFactory("nike");

        IShoe nikeShoe = nikeFactory.makeShoe();
        IShirt nikeShirt = nikeFactory.makeShirt();

        IShoe adidasShoe = adidasFactory.makeShoe();
        IShirt adidasShirt = adidasFactory.makeShirt();

        printShoeDetails(nikeShoe);
        printShirtDetails(nikeShirt);

        printShoeDetails(adidasShoe);
        printShirtDetails(adidasShirt);
    }

    public static ISportsFactory getSportsFactory(String brand) {
        if (brand.equals("adidas")) {
            return new Adidas();
        }

        if (brand.equals("nike")) {
            return new Nike();
        }

        return null;
    }

    public static void printShoeDetails(IShoe s) {
        System.out.printf("Logo: %s%n", s.getLogo());
        System.out.printf("Size: %d%n", s.getSize());
    }

    public static void printShirtDetails(IShirt s) {
        System.out.printf("Logo: %s%n", s.getLogo());
        System.out.printf("Size: %d%n", s.getSize());
    }

    public interface IShoe {
        void setLogo(String logo);

        void setSize(int size);

        String getLogo();

        int getSize();
    }

    public static class Shoe implements IShoe {
        private String logo;
        private int size;

        public Shoe(String logo, int size) {
            this.logo = logo;
            this.size = size;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getLogo() {
            return this.logo;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getSize() {
            return this.size;
        }
    }

    public interface ISportsFactory {
        IShoe makeShoe();

        IShirt makeShirt();
    }

    public static class Adidas implements ISportsFactory {
        public IShoe makeShoe() {
            return new AdidasShoe(new Shoe("adidas", 14));
        }

        public IShirt makeShirt() {
            return new AdidasShirt(new Shirt("adidas", 14));
        }
    }

    public static class Nike implements ISportsFactory {
        public IShoe makeShoe() {
            return new NikeShoe(new Shoe("nike", 14));
        }

        public IShirt makeShirt() {
            return new NikeShirt(new Shirt("nike", 14));
        }
    }

    public interface IShirt {
        void setLogo(String logo);

        void setSize(int size);

        String getLogo();

        int getSize();
    }

    public static class Shirt implements IShirt {
        private String logo;
        private int size;

        public Shirt(String logo, int size) {
            this.logo = logo;
            this.size = size;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getLogo() {
            return this.logo;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getSize() {
            return this.size;
        }
    }

    public static class AdidasShoe implements IShoe {
        private Shoe shoe;

        public AdidasShoe(Shoe shoe) {
            this.shoe = shoe;
        }

        public void setLogo(String logo) {
            this.shoe.setLogo(logo);
        }

        public String getLogo() {
            return this.shoe.getLogo();
        }

        public void setSize(int size) {
            this.shoe.setSize(size);
        }

        public int getSize() {
            return this.shoe.getSize();
        }
    }

    public static class NikeShoe implements IShoe {
        private Shoe shoe;

        public NikeShoe(Shoe shoe) {
            this.shoe = shoe;
        }

        public void setLogo(String logo) {
            this.shoe.setLogo(logo);
        }

        public String getLogo() {
            return this.shoe.getLogo();
        }

        public void setSize(int size) {
            this.shoe.setSize(size);
        }

        public int getSize() {
            return this.shoe.getSize();
        }
    }

    public static class AdidasShirt implements IShirt {
        private Shirt shirt;

        public AdidasShirt(Shirt shirt) {
            this.shirt = shirt;
        }

        public void setLogo(String logo) {
            this.shirt.setLogo(logo);
        }

        public String getLogo() {
            return this.shirt.getLogo();
        }

        public void setSize(int size) {
            this.shirt.setSize(size);
        }

        public int getSize() {
            return this.shirt.getSize();
        }
    }

    public static class NikeShirt implements IShirt {
        private Shirt shirt;

        public NikeShirt(Shirt shirt) {
            this.shirt = shirt;
        }

        public void setLogo(String logo) {
            this.shirt.setLogo(logo);
        }

        public String getLogo() {
            return this.shirt.getLogo();
        }

        public void setSize(int size) {
            this.shirt.setSize(size);
        }

        public int getSize() {
            return this.shirt.getSize();
        }
    }
}