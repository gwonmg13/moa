package kr.soen.moa.ui.activity;

/**
 * Created by hong on 2017-08-19.
 */

public class talk_list_Info {
    int id;
    public String text;
    public String file;
    public String guide = null;
    public boolean check = false;
    talk_list_Info(int id, String text, String file, String guide, boolean check){
        this.id = id;
        this.text = text;
        this.file = file;
        this.guide = guide;
        this.check = check;
    }
}
