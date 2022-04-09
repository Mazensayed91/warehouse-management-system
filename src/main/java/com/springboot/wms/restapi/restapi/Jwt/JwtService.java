//package com.springboot.wms.restapi.restapi.Jwt;
//import com.springboot.wms.restapi.restapi.User.UserRepository;
//import com.springboot.wms.restapi.restapi.utils.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class JwtService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception{
//        String userEmail = jwtRequest.getUserEmail();
//        String userPassword = jwtRequest.getUserPassword();
//
//        authenticate(userEmail, userPassword);
//
//        final UserDetails userDetails = loadUserByUsername(userEmail);
//
//        String newGeneratedToken = jwtUtil.generateToken(userDetails);
//
//        com.springboot.wms.restapi.restapi.User.User user = userRepository.findByContactPerson_Email(userEmail);
//
//        return new JwtResponse(user, newGeneratedToken);
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//            com.springboot.wms.restapi.restapi.User.User user =  userRepository.findByContactPerson_Email(email);
//
//            if(user != null){
//                return new User(user.getContactPerson().getEmail(),
//                        user.getContactPerson().getPassword(),
//                        getAuthorities(user)
//                );
//            }else{
//                throw new UsernameNotFoundException("User email isn't valid");
//            }
//        }
//
//
//    private Set getAuthorities(com.springboot.wms.restapi.restapi.User.User user){
//        Set authorities = new HashSet();
//
//        user.getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getTitle()));
//        });
//
//        return authorities;
//    }
//
//    private void authenticate(String userEmail, String userPassword) throws Exception{
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEmail, userPassword));
//        } catch (DisabledException e){
//            throw new Exception("User is disabled");
//        } catch (BadCredentialsException e){
//            throw new Exception(("Bad Cerd"));
//        }
//    }
//
//}
