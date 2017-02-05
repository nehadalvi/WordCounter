package com.mad.wordcounter;

import java.io.Serializable;

/**
 * Created by Chinmay Rawool on 2/3/2017.
 */

public class WordCount implements Serializable {
    String wrd;
    int freq;

    public WordCount() {
        this.freq =0;
    }

    public WordCount(String wrd) {
        this.wrd = wrd;
        this.freq = 0;
    }

    public WordCount(String wrd, int freq) {
        this.wrd = wrd;
        this.freq = freq;
    }

    public String getWrd() {
        return wrd;
    }

    public void setWrd(String wrd) {
        this.wrd = wrd;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    @Override
    public String toString() {
        return "WordCount{" +
                "wrd='" + wrd + '\'' +
                ", freq=" + freq +
                '}';
    }
}
