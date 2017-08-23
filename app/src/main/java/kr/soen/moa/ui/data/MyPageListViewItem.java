package kr.soen.moa.ui.data;

/**
 * Created by manggi on 2017. 8. 22..
 */

public class MyPageListViewItem {

    private String reg_date;
    private String title;
    private String comment;

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "MyPageListViewItem{" +
                "reg_date='" + reg_date + '\'' +
                ", title='" + title + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
