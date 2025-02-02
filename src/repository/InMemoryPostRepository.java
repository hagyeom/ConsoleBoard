package repository;

import entity.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * 메모리 상에서 게시글 데이터를 관리하는 저장소 구현체.
 * 게시글을 리스트로 저장하고 조회, 삭제 등의 기능을 제공.
 */
public class InMemoryPostRepository implements PostRepository {
    private List<Post> posts = new ArrayList<>();  // 게시글을 저장할 리스트
    private int currentId = 0;                    // 자동 증가하는 게시글 ID

    /**
     * 게시글을 저장하는 메서드.
     *
     * @param post 저장할 게시글 객체
     */
    @Override
    public void save(Post post) {
        post.setPostId(++currentId);  // 게시글 ID를 자동으로 증가시킴
        posts.add(post);              // 리스트에 게시글 추가
    }

    /**
     * 모든 게시글을 반환하는 메서드.
     *
     * @return 게시글 리스트
     */
    @Override
    public List<Post> findAll() {
        return posts;
    }

    /**
     * ID로 게시글을 찾는 메서드.
     *
     * @param id 찾을 게시글의 ID
     * @return 해당 ID의 게시글 (없으면 null 반환)
     */
    @Override
    public Post findById(int id) {
        return posts.stream().filter(post -> post.getPostId() == id).findFirst().orElse(null);
    }

    /**
     * 게시글을 삭제하는 메서드.
     *
     * @param id 삭제할 게시글의 ID
     */
    @Override
    public void delete(int id) {
        posts.removeIf(post -> post.getPostId() == id);  // ID에 해당하는 게시글 삭제
    }
}