package entity;

import java.util.Date;

/**
 * 게시글 정보를 나타내는 데이터 모델 클래스.
 * 게시글의 ID, 제목, 내용, 작성자, 좋아요 수, 조회수, 작성 일자 등을 관리.
 */
public class Post {
    private int postId;
    private String title;
    private String content;
    private User author;
    private int likes;
    private int views;
    private Date createDate;
    private Date updatedDate;

    // 생성자
    public Post(int postId, String title, String content, User author) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.likes = 0;
        this.views = 0;
        this.createDate = new Date();
        this.updatedDate = new Date();
    }

    // Getter와 Setter 메서드
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent() {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor() {
        this.author = author;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes() {
        this.likes = likes;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    // 메서드
    public void incrementLikes() {
        this.likes++;
    }

    public void incrementViews() {
        this.views++;
    }

    public void updateConent(String newContent) {
        this.content = newContent;
    }

    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }

}
