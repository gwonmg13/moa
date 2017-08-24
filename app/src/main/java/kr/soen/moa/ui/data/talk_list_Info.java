package kr.soen.moa.ui.data;

/**
 * Created by hong on 2017-08-19.
 */

public class talk_list_Info {
    public int id;
    public String text;
    public int file;
    public String guide = null;
    public boolean check = false;
    public talk_list_Info(int id, String text, int file, String guide, boolean check){
        this.id = id;
        this.text = text;
        this.file = file;
        this.guide = guide;
        this.check = check;
    }
}
