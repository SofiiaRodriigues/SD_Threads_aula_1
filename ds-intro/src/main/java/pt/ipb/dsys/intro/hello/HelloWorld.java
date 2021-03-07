package pt.ipb.dsys.intro.hello;

public abstract class HelloWorld {

    private String name;

    public HelloWorld() {
    }

    public HelloWorld(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void sayHello();

    public abstract void sayHello(String senderName);

}
