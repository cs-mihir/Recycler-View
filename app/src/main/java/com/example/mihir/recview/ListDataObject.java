package com.example.mihir.recview;

/**
 * Created by Mihir on 31-05-2016.
 */
public class ListDataObject {
    private String questionLabel;
    private int questionID;
    private String description;

    public ListDataObject(String label,int ID,String description) {
        this.questionLabel=label;
        this.questionID=ID;
        this.description=description;
    }

    public String getQuestionLabel() {
        return this.questionLabel;
    }

    public int getQuestionID() {
        return this.questionID;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description=description;
    }
}
