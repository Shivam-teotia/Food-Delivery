package org.shivam.fooddelivery.service;


import org.shivam.fooddelivery.Model.User;
import org.shivam.fooddelivery.Repository.UserRepository;
import org.shivam.fooddelivery.config.JwtProvider;
import org.shivam.fooddelivery.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email=jwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepository.findByEmail(email);
        if(user==null) {
            throw new UserException("user not exist with email "+email);
        }
        return user;
    }
    @Override
    public User findUserByEmail(String email) throws UserException {
        User user=userRepository.findByEmail(email);
        if(user!=null) {

            return user;
        }
        throw new UserException("user not exist with username "+email);
    }
}
