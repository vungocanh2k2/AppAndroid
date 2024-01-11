package com.example.app_c_truyn.Model;

public class Story {
    private int ID;
    private String NameStory;
    private String Content;
    private String Image;
    private int ID_TK;


    public Story(int ID, String nameStory, String content, String image, int ID_TK) {
        this.ID = ID;
        NameStory = nameStory;
        Content = content;
        Image = image;
        this.ID_TK = ID_TK;
    }

    public Story() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNameStory() {
        return NameStory;
    }

    public void setNameStory(String nameStory) {
        NameStory = nameStory;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getID_TK() {
        return ID_TK;
    }

    public void setID_TK(int ID_TK) {
        this.ID_TK = ID_TK;
    }
}
