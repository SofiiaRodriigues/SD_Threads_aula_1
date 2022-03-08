package pt.ipb.dsys.intro.hello;

public class HelloMain {

    public static void main(String[] args) {
        HelloWorld spanish = new SpanishHelloWorld();
        spanish.setName("Carlos");
        spanish.sayHello();
        spanish.sayHello("Juan");

        HelloWorld english = new EnglishHelloWorld();
        english.setName("John");
        english.sayHello();
        english.sayHello("Mary");

        HelloWorld german = new GermanHelloWorld();
        german.setName("Klaus");
        german.sayHello();
        german.sayHello("Jonas");
    }

}
