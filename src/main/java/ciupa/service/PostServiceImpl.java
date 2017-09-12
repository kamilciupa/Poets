package ciupa.service;

import ciupa.model.Post;
import ciupa.repository.PostRepository;
import ciupa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Kamil on 2017-09-07.
 */

@Service("postService")
public class PostServiceImpl implements PostService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public void savePost(Post post) {

        //post.setAuthor(userRepository.findByEmail("test@test.com"));
        postRepository.save(post);

    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(Integer id) {
        return postRepository.findOne(id);
    }


    @Override
    public void addVote(Integer id, int vote) {
        Post post = postRepository.findOne(id);
        post.setControlSum(post.getControlSum()+vote);
        post.setVotesAmount(post.getVotesAmount()+1);
        post.setSrednia(post.getControlSum()/post.getVotesAmount());
        postRepository.save(post);

    }

    @Override
    public List<Post> findLatestThree() {
        List<Post> posts = postRepository.findAll();
        List<Post> latestPost = new ArrayList<Post>();
        for(int i = 0 ; i < 3 ; i++) {
            latestPost.add(posts.get(posts.size()-1-i));
        }
        return latestPost;
    }

    @Override
    public List<Post> makeToplistVotes() {
        List<Post> posts = postRepository.findAll();
        Collections.sort(posts,
                new Comparator<Post>()
                {
                    public int compare(Post o1,
                                       Post o2)
                    {
                        if (o1.getSrednia() ==
                                o2.getSrednia())
                        {
                            return 0;
                        }
                        else if (o1.getSrednia() <
                                o2.getSrednia())
                        {
                            return 1;
                        }
                        return -1;
                    }
                });

        return posts;
    }

    @Override
    public List<Post> top3() {
        List<Post> post = makeToplistVotes();
        List<Post> top3 = new ArrayList<Post>();
        for(int i = 0 ; i < 3 ; i++ ) {
            top3.add(post.get(i));
        }

        return top3;
    }
}
