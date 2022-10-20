package com.bjit.salon.auth.service.serviceImpl

import com.bjit.salon.auth.service.entity.User
import com.bjit.salon.auth.service.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.userdetails.UsernameNotFoundException
import spock.lang.Specification

@SpringBootTest
class UserDetailsServiceImplTest extends Specification {


    private UserRepository userRepository;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    def setup() {
        userRepository = Mock()
    }


    def "should load user details by username"() {
        given:
        def user = User.builder()
                .firstName("jewel")
                .lastName("chowdhury")
                .username("jewel")
                .email("jewel@gmail.com")
                .nonLocked(true)
                .enabled(true)
                .build()

        userRepository.findByUsername("jewel") >> Optional.of(user)

        when:
        def response = userDetailsService.loadUserByUsername("jewel")

        then:
        response.getUsername() == "jewel"
    }

    def "should throw user not found exception by username"() {
        given:
        userRepository.findByUsername("jewel") >> { throw new UsernameNotFoundException("User not found for username: jewel")}

        when:
        userDetailsService.loadUserByUsername("jewe2l")

        then:
        def response = thrown(UsernameNotFoundException)
        response.message == "User not found for username: jewe2l"
    }

}
