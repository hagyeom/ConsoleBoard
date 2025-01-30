package service;

import entity.Post;
import entity.User;
import repository.InMemoryPostRepository;
import repository.PostRepository;

import java.util.List;

/**
 * 게시글 관련 비즈니스 로직을 처리하는 서비스 클래스.
 * 게시글 추가, 삭제, 수정, 조회 등의 기능을 제공.
 */
public class PostService {
    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void addPost(String title, String content, User author) {
        Post post = new Post(0, title, content, author);
        postRepository.save(post);
    }

    public boolean deletePost(int postId) {
        Post post = postRepository.findById(postId);
        if (post != null) {
            postRepository.delete(postId);
            return true;
        }
        return false;
    }

    public boolean updatePost(int postId, String newTitle, String newContent) {
        Post post = postRepository.findById(postId);
        if (post != null) {
            post.updateTitle(newTitle);
            post.updateConent(newContent);
            return true;
        }
        return false;
    }

    public Post viewPost(int postId) {
        Post post = postRepository.findById(postId);
        if (post != null) {
            post.incrementViews();
        }
        return post;
    }

    public List<Post> listPosts() {
        return postRepository.findAll();
    }

    public void likePost(int postId) {
        Post post = postRepository.findById(postId);
        if (post != null) {
            post.incrementLikes();
        }
    }
}
