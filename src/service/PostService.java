package service;

import entity.Post;
import entity.User;
import repository.PostRepository;

import java.util.List;

/**
 * 게시글 관련 비즈니스 로직을 처리하는 서비스 클래스.
 * 게시글 추가, 삭제, 수정, 조회 등의 기능을 제공.
 */
public class PostService {
    private PostRepository postRepository;  // 게시글 저장소 인터페이스

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * 새로운 게시글을 추가하는 메서드.
     *
     * @param title   게시글 제목
     * @param content 게시글 내용
     * @param author  게시글 작성자
     */
    public void addPost(String title, String content, User author) {
        Post post = new Post(0, title, content, author);  // 새로운 게시글 생성
        postRepository.save(post);  // 저장소에 게시글 저장
    }

    /**
     * 게시글을 삭제하는 메서드.
     *
     * @param postId 삭제할 게시글의 ID
     * @return 삭제 성공 여부 (true: 성공, false: 실패)
     */
    public boolean deletePost(int postId) {
        Post post = postRepository.findById(postId);
        if (post != null) {
            postRepository.delete(postId);  // 게시글 삭제
            return true;
        }
        return false;
    }

    /**
     * 게시글을 수정하는 메서드.
     *
     * @param postId     수정할 게시글의 ID
     * @param newTitle   새로운 제목
     * @param newContent 새로운 내용
     * @return 수정 성공 여부 (true: 성공, false: 실패)
     */
    public boolean updatePost(int postId, String newTitle, String newContent) {
        Post post = postRepository.findById(postId);
        if (post != null) {
            post.updateTitle(newTitle);    // 제목 수정
            post.updateContent(newContent); // 내용 수정
            return true;
        }
        return false;
    }

    /**
     * 게시글을 조회하는 메서드. 조회수 증가.
     *
     * @param postId 조회할 게시글의 ID
     * @return 해당 게시글 객체 (없으면 null 반환)
     */
    public Post viewPost(int postId) {
        Post post = postRepository.findById(postId);
        if (post != null) {
            post.incrementViews();  // 조회수 증가
        }
        return post;
    }

    /**
     * 모든 게시글을 반환하는 메서드.
     *
     * @return 게시글 리스트
     */
    public List<Post> listPosts() {
        return postRepository.findAll();
    }

    /**
     * 게시글에 좋아요를 추가하는 메서드.
     *
     * @param postId 좋아요를 추가할 게시글의 ID
     */
    public void likePost(int postId) {
        Post post = postRepository.findById(postId);
        if (post != null) {
            post.incrementLikes();  // 좋아요 수 증가
        }
    }
}