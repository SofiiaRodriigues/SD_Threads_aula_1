package pt.ipb.dsys.intro.hello;

public class SpanishHelloWorld extends HelloWorld {

    @Override
    public void sayHello() {
        System.out.println("Hola " + getName() + "!");
    }

    @Override
    public void sayHello(String senderName) {
        System.out.println("Hola " + getName() + ", from " + senderName + "!");
    }

}
