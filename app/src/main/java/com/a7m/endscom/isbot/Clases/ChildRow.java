package com.a7m.endscom.isbot.Clases;

/**
 * Created by A7M on 20/08/2016.
 */
public class ChildRow {


    private String text,Direc;

    public ChildRow(String text, String direc) {
        this.text = text;
        Direc = direc;
    }

    public String getDirec() {
        return Direc;
    }

    public void setDirec(String direc) {
        Direc = direc;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
