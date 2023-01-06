package com.wmk.demo.services;



import com.wmk.demo.entity.Role;
import com.wmk.demo.entity.User;
import com.wmk.demo.models.RoleModel;
import com.wmk.demo.models.UserModel;
import com.wmk.demo.repository.RoleRepository;
import com.wmk.demo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserModel register(UserModel userModel){

        User User = new User();
        BeanUtils.copyProperties(userModel, User);//it does not do a deep copy

        Set<Role> roleEntities = new HashSet<>();
        //fetch every role from DB based on role id and than set this role to user entity roles
        for(RoleModel rm :userModel.getRoles()){
            Optional<Role> optRole = roleRepository.findById(rm.getId());
            if(optRole.isPresent()){
                roleEntities.add(optRole.get());
            }
        }
        User.setRoles(roleEntities);
        User.setPassword(this.passwordEncoder.encode(userModel.getPassword()));
        User = userRepository.save(User);

        BeanUtils.copyProperties(User, userModel);

        //convert RoleEntities to RoleModels
        Set<RoleModel> roleModels = new HashSet<>();
        RoleModel rm = null;
        for(Role re :User.getRoles()){
            rm = new RoleModel();
            rm.setRoleName(re.getRoleName());
            rm.setId(re.getId());
            roleModels.add(rm);
        }
        userModel.setRoles(roleModels);
        return userModel;
    }

    //this method actually does the validation for user existence
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User User = userRepository.findByUsername(userName);

        if(User == null){//here you can make a DB call with the help of repository and do the validation
            throw new UsernameNotFoundException("User does not exist!");
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(User, userModel);

        //convert RoleEntities to RoleModels
        Set<RoleModel> roleModels = new HashSet<>();
        RoleModel rm = null;
        for(Role re :User.getRoles()){
            rm = new RoleModel();
            rm.setRoleName(re.getRoleName());
            rm.setId(re.getId());
            roleModels.add(rm);
        }

        userModel.setRoles(roleModels);
        return userModel;
    }
}