abstract class Bicycle {
    private String brand;
    private int gears;
    private double weight;

    private static int count = 0;

    public Bicycle() {
        this("NoName", 1, 10.0);
    }

    public Bicycle(String brand, int gears, double weight) {
        this.brand = brand;
        this.gears = gears;
        this.weight = weight;
        count++;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getGears() {
        return gears;
    }
    public void setGears(int gears) {
        this.gears = gears;
    }

    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public static int getCount() {
        return count;
    }

    public abstract void ride();
    public abstract void brake();

    public void printInfo() {
        System.out.println("Бренд: " + brand +
                ", передач: " + gears +
                ", вес: " + weight + " кг");
    }
}

class MountainBike extends Bicycle {
    private String suspensionType;

    public MountainBike() {
        this("Default MTB", 21, 14.5, "Хардтейл");
    }

    public MountainBike(String brand, int gears, double weight, String suspensionType) {
        super(brand, gears, weight);
        this.suspensionType = suspensionType;
    }

    public String getSuspensionType() {
        return suspensionType;
    }
    public void setSuspensionType(String suspensionType) {
        this.suspensionType = suspensionType;
    }

    @Override
    public void ride() {
        System.out.println("Горный велосипед едет по пересеченной местности.");
    }

    @Override
    public void brake() {
        System.out.println("Горный велосипед тормозит дисковыми тормозами.");
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Подвеска: " + suspensionType);
    }
}

class DownhillBike extends MountainBike {
    private boolean hasChainGuide;

    public DownhillBike() {
        this("Downhill Pro", 7, 16.0, "Полная амортизация", true);
    }

    public DownhillBike(String brand, int gears, double weight, String suspensionType, boolean hasChainGuide) {
        super(brand, gears, weight, suspensionType);
        this.hasChainGuide = hasChainGuide;
    }

    public boolean isHasChainGuide() {
        return hasChainGuide;
    }
    public void setHasChainGuide(boolean hasChainGuide) {
        this.hasChainGuide = hasChainGuide;
    }

    @Override
    public void ride() {
        System.out.println("Даунхилл велосипед мчится по горному склону.");
    }

    @Override
    public void brake() {
        System.out.println("Даунхилл велосипед резко тормозит на большой скорости.");
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Цепной успокоитель: " + (hasChainGuide ? "есть" : "нет"));
    }
}

class KidsBike extends Bicycle {
    private boolean hasTrainingWheels;

    public KidsBike() {
        this("KidsBike", 1, 8.0, true);
    }

    public KidsBike(String brand, int gears, double weight, boolean hasTrainingWheels) {
        super(brand, gears, weight);
        this.hasTrainingWheels = hasTrainingWheels;
    }

    public boolean isHasTrainingWheels() {
        return hasTrainingWheels;
    }
    public void setHasTrainingWheels(boolean hasTrainingWheels) {
        this.hasTrainingWheels = hasTrainingWheels;
    }

    @Override
    public void ride() {
        System.out.println("Детский велосипед катится медленно и безопасно.");
    }

    @Override
    public void brake() {
        System.out.println("Детский велосипед тормозит ножным тормозом.");
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Доп. колёсики: " + (hasTrainingWheels ? "есть" : "нет"));
    }
}

class BMX extends Bicycle {
    private String frameMaterial;

    public BMX() {
        this("BMX Street", 1, 11.0, "Steel");
    }

    public BMX(String brand, int gears, double weight, String frameMaterial) {
        super(brand, gears, weight);
        this.frameMaterial = frameMaterial;
    }

    public String getFrameMaterial() {
        return frameMaterial;
    }
    public void setFrameMaterial(String frameMaterial) {
        this.frameMaterial = frameMaterial;
    }

    @Override
    public void ride() {
        System.out.println("BMX выполняет трюки на рампе.");
    }

    @Override
    public void brake() {
        System.out.println("BMX резко тормозит при трюках.");
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Материал рамы: " + frameMaterial);
    }
}

public class Main {
    public static void main(String[] args) {
        Bicycle bike1 = new MountainBike("Merida", 24, 13.2, "Полная амортизация");
        Bicycle bike2 = new DownhillBike();
        Bicycle bike3 = new KidsBike();
        Bicycle bike4 = new BMX("Haro", 1, 10.5, "Алюминий");

        bike1.printInfo();
        bike1.ride();
        bike1.brake();
        System.out.println();

        bike2.printInfo();
        bike2.ride();
        bike2.brake();
        System.out.println();

        bike3.printInfo();
        bike3.ride();
        bike3.brake();
        System.out.println();

        bike4.printInfo();
        bike4.ride();
        bike4.brake();
        System.out.println();

        System.out.println("Всего создано велосипедов: " + Bicycle.getCount());
    }
}
