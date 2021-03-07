package pt.ipb.dsys.intro.hello;

public class GermanHelloWorld extends HelloWorld {

    @Override
    public void sayHello() {
        System.out.println("Hallo " + getName() + "!");
    }

    @Override
    public void sayHello(String senderName) {
        System.out.println("Hallo " + getName() + ", from " + senderName + "!");
    }

}
