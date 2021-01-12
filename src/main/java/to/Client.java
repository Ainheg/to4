package to;

import java.util.Random;

public class Client {
    private int age;
    private int number = new Random(System.currentTimeMillis()).nextInt();

    public Client(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
    public int getNumber() {return number;};
}
