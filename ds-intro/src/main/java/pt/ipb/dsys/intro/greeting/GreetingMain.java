package pt.ipb.dsys.intro.greeting;

public class GreetingMain {

    public static void main(String[] args) {
        GoodMorning enGm = new EnglishGoodMorning();
        enGm.sayGoodMorning();
        GoodEvening enGe = new EnglishGoodEvening();
        enGe.sayGoodEvening();

        SpanishGreeter spg = new SpanishGreeter();
        spg.sayGoodMorning();
        spg.sayGoodEvening();

        GoodMorning spGm = new SpanishGreeter();
        spGm.sayGoodMorning();
        GoodEvening spGe = new SpanishGreeter();
        spGe.sayGoodEvening();
    }

}
