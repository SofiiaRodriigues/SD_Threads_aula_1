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

        SpanishGreeter greeter = new SpanishGreeter();
        greeter.sayGoodMorning();
        greeter.sayGoodEvening();

        // SpanishGreeter is also of type GoodMorning and GoodEvening
        GoodMorning sgm = greeter;
        sgm.sayGoodMorning();
        GoodEvening sge = greeter;
        sge.sayGoodEvening();
    }

}
