package pt.ipb.dsys.intro.hello;

public class EnglishHelloWorld extends HelloWorld {

    @Override
    public void sayHello() {
        System.out.println("Hello " + getName() + "!");
    }

    @Override
    public void sayHello(String senderName) {
        System.out.println("Hello " + getName() + ", from " + senderName + "!");
    }

}
