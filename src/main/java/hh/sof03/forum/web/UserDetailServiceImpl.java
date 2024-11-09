package hh.sof03.forum.web;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.sof03.forum.domain.AppUser;
import hh.sof03.forum.domain.AppUserRepository;



@Service
public class UserDetailServiceImpl implements UserDetailsService{

    private final AppUserRepository repo;

    public UserDetailServiceImpl(AppUserRepository userRepo) {
        this.repo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser currentUser = repo.findByUsername(username);
        UserDetails user = new User(username, currentUser.getPasswordHash(), AuthorityUtils.createAuthorityList(currentUser.getRole()));
        return user;
    }

}
