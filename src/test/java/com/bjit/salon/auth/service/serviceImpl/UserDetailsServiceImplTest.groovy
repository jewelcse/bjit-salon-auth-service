package com.bjit.salon.auth.service.serviceImpl

import com.bjit.salon.auth.service.entity.User
import com.bjit.salon.auth.service.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.userdetails.UsernameNotFoundException
import spock.lang.Ignore
import spock.lang.Specification

@SpringBootTest
class UserDetailsServiceImplTest extends Specification {


    private UserRepository userRepository;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    def setup() {
        userRepository = Mock()
    }


    @Ignore
    def "should load user details by username"() {
        given:
        def user = User.builder()
                .id(1L)
                .firstName("jewel")
                .lastName("chowdhury")
                .username("jewel123")
                .email("jewel@gmail.com")
                .nonLocked(true)
                .enabled(true)
                .build()

        userRepository.findByUsername("jewel") >>Optional.of(user)

        when:
        def response = userDetailsService.loadUserByUsername("jewel123")

        then:
        response.getUsername() == "jewel123"
    }

    def "should throw user not found exception by username"() {
        given:
        userRepository.findByUsername("jewel") >> { throw new UsernameNotFoundException("User not found for username: jewel") }

        when:
        userDetailsService.loadUserByUsername("jewe2l")

        then:
        def response = thrown(UsernameNotFoundException)
        response.message == "User not found for username: jewe2l"
    }

}
