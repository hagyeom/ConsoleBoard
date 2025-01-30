package repository;

import entity.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * 메모리 상에서 게시글 데이터를 관리하는 저장소 구현체.
 * 게시글을 리스트로 저장하고 조회, 삭제 등의 기능을 제공.
 */
public class InMemoryPostRepository implements PostRepository {
    private List<Post> posts = new ArrayList<>();
    private int currentId = 0;

    @Override
    public void save(Post post) {
        post.setPostId(++currentId);
        posts.add(post);
    }

    @Override
    public List<Post> findAll() {
        return posts;
    }

    @Override
    public Post findById(int id) {
        return posts.stream().filter(post -> post.getPostId() == id).findFirst().orElse(null);
    }

    @Override
    public void delete(int postId) {
        posts.removeIf(post -> post.getPostId() == postId);
    }
}