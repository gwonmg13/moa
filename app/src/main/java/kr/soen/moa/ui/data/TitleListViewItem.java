package kr.soen.moa.ui.data;

/**
 * Created by manggi on 2017. 8. 19..
 */

public class TitleListViewItem {
    private String content;
    private int likeNum;
    private boolean isLiked;
    private boolean checked=false;

    public TitleListViewItem(String content, int likeNum, boolean isLiked, boolean checked) {
        this.content = content;
        this.likeNum = likeNum;
        this.isLiked = isLiked;
        this.checked = checked;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    public boolean getChecked(){
        return checked;
    }
    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
