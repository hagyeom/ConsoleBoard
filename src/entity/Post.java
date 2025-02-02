package entity;

import java.util.Date;

/**
 * 게시글 정보를 나타내는 데이터 모델 클래스.
 * 게시글의 ID, 제목, 내용, 작성자, 좋아요 수, 조회수, 작성 일자 등을 관리.
 */
public class Post {
    private int postId;          // 게시글의 고유 ID
    private String title;        // 게시글의 제목
    private String content;      // 게시글의 내용
    private User author;         // 게시글 작성자 (User 객체)
    private int likes;           // 좋아요 수
    private int views;           // 조회수
    private Date createdDate;    // 게시글 작성 일자
    private Date updatedDate;    // 게시글 수정 일자

    public Post(int postId, String title, String content, User author) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.likes = 0;
        this.views = 0;
        this.createdDate = new Date();
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

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * 좋아요 수를 1 증가시키는 메서드.
     */
    public void incrementLikes() {
        this.likes++;
    }

    /**
     * 조회수를 1 증가시키는 메서드.
     */
    public void incrementViews() {
        this.views++;
    }

    /**
     * 게시글 내용을 수정하는 메서드.
     *
     * @param newContent 새로운 내용
     */
    public void updateContent(String newContent) {
        this.content = newContent;
    }

    /**
     * 게시글 제목을 수정하는 메서드.
     *
     * @param newTitle 새로운 제목
     */
    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }
}