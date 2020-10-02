package com.example.contentprovider_wordbook;

import android.provider.BaseColumns;

public class Word{

    public static int ID;


    private String word;
    private String v;
    private String notes;
    private String example;

    public Word(){

    }
    public Word(String word, String v, String notes, String example){
        this.word = word;
        this.v = v;
        this.notes = notes;
        this.example = example;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public static abstract class w implements BaseColumns{
        public static final String TABLE_NAME="words";
        public static final String COLUMN_NAME_WORD="word";
        public static final String COLUMN_NAME_V="v";
        public static final String COLUMN_NAME_NOTES="notes";
        public static final String COLUMN_NAME_EXAMPLE="example";
    }

}
