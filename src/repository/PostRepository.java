package repository;
/**
 * 게시글 데이터를 관리하는 저장소 인터페이스.
 * 게시글의 저장, 조회, 삭제 등의 기능을 정의.
 */

import entity.Post;

import java.util.List;

public interface PostRepository {
    void save(Post post);

    List<Post> findAll();

    Post findById(int id);

    void delete(int id);
}
