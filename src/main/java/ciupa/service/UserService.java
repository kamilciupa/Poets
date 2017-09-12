package ciupa.service;

import ciupa.model.User;

import java.util.List;

/**
 * Created by Kamil on 2017-09-05.
 */

public interface UserService {
    public User findUserByEmail(String email);
    public List<User> findAll();
    public void saveUser(User user);
    public void deleteById(Integer id);
    public void updateUser(Integer id);
}
