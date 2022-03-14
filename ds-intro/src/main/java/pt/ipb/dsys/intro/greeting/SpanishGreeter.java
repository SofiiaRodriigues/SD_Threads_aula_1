package pt.ipb.dsys.intro.greeting;

public class SpanishGreeter implements GoodMorning, GoodEvening {
    @Override
    public void sayGoodMorning() {
        System.out.println("Buenos Dias!");
    }

    @Override
    public void sayGoodEvening() {
        System.out.println("Buenas Noches!");
    }
}
