package ciupa.service;

import ciupa.model.Post;

import java.util.List;

/**
 * Created by Kamil on 2017-09-07.
 */
public interface PostService {
    public void savePost(Post post);
    public List<Post> findAll();
    public Post findById(Integer id);
    /*/**/
    public void addVote(Integer id, int vote);
    public List<Post> findLatestThree();
    public List<Post> makeToplistVotes();
    public List<Post> top3();
}
