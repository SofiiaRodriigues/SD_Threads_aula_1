package pt.ipb.dsys.intro.exception;

import javax.swing.*;

public class ExceptionMain {

    public ExceptionMain(int number) throws Exception {
        doStuff(number);
    }

    private void doStuff(int number) throws Exception {
        doMoreStuff(number);
    }

    private void doMoreStuff(int number) throws Exception {
        if (number <= 10)
            throw new IllegalArgumentException("Number must be > 10");
        System.out.println("Number is valid: " + number);
    }

    public static void main(String[] args) {
        try {
            new ExceptionMain(1);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "You must type a valid number!");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
        System.out.println("teste");
    }

}
