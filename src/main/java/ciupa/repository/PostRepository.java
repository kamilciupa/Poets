package ciupa.repository;

import ciupa.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Kamil on 2017-09-07.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
}
