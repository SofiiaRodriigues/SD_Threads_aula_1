package pt.ipb.dsys.com.rmi.cpu.client;

import pt.ipb.dsys.com.rmi.cpu.common.CpuTask;

import java.io.Serializable;
import java.util.Arrays;

public class WordSort implements CpuTask<String>, Serializable {
    String phrase;

    public WordSort(String phrase) {
        this.phrase = phrase;
    }

    @Override
    public String process() {
        String[] words = phrase.split(" ");
        Arrays.sort(words);
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            str.append(words[i]);
            if (i < words.length - 1) {
                str.append(" ");
            }
        }
        return str.toString();
    }

}
