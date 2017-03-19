package com.mad.wordcounter;


import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Chinmay Rawool on 2/4/2017.
 */

public class MyLayout {
    Button btn;
    EditText et;
    Boolean flag;
    int id = 0x00;

    public void setId(int id) {
        this.id = id;
        btn.setId(id);
        et.setId(2000+id);

    }

    public int getId() {
        return id;
    }

    public MyLayout(Button btn, EditText et) {
        this.btn = btn;
        this.et = et;
        this.flag =true;//true equals add, false equals remove
        btn.setText("+");
        btn.setTextSize(24);
        btn.setWidth(20);
        et.setWidth(780);

    }
    public MyLayout(){

    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public EditText getEt() {
        return et;
    }

    public void setEt(EditText et) {
        this.et = et;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }


}
