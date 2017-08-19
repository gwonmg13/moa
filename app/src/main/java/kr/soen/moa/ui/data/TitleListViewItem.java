package kr.soen.moa.ui.data;

/**
 * Created by manggi on 2017. 8. 19..
 */

public class TitleListViewItem {
    private String content;
    private int likeNum;
    private boolean isLiked;

    public TitleListViewItem() {
    }

    public TitleListViewItem(String content, int likeNum, boolean isLiked) {
        this.content = content;
        this.likeNum = likeNum;
        this.isLiked = isLiked;
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

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
