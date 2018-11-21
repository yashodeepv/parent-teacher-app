package com.ysol.ptapp.parentteacherservices.services;


import com.ysol.ptapp.parentteacherservices.entity.UserDTO;
import com.ysol.ptapp.parentteacherservices.entity.UserType;
import com.ysol.ptapp.parentteacherservices.jpa.UserRepository;
import com.ysol.ptapp.parentteacherservices.jpa.domain.User;
import com.ysol.ptapp.parentteacherservices.services.user.UserMapper;
import com.ysol.ptapp.parentteacherservices.services.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class WhenUserLogin {

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(userMapper.mapUser(Mockito.any(User.class))).thenCallRealMethod();
    }

    @Test
    public void should_allow_valid_user_credentials() {
        // given
        UserDTO userDTO = UserDTO.builder().userName("yash").password("pass").build();
        when(userRepository.findByUsernameAndPassword(anyString(), anyString())).thenReturn(Optional.of(Arrays.asList(User.builder().build())));

        // when
        boolean actual = userService.validateCredentials(userDTO.getUserName(), userDTO.getPassword());

        // then
        assertThat(actual, is(true));
    }

    @Test
    public void should_reject_invalid_user_credentials() {
        // given
        UserDTO userDTO = UserDTO.builder().userName("yash").password("pass").emailid("yash@hm.com").userType(UserType.PARENT).build();
        when(userRepository.findByUsernameAndPassword(anyString(), anyString())).thenReturn(Optional.empty());

        // when
        boolean actual = userService.validateCredentials(userDTO.getUserName(), userDTO.getPassword());

        // then
        assertThat(actual, is(false));
    }

    @Test
    public void should_return_user_details_of_a_valid_user() {
        // given
        UserDTO userDTO = UserDTO.builder()
                .userName("yash")
                .password("pass")
                .emailid("yash@hm.com")
                .userType(UserType.PARENT)
                .build();

        when(userRepository.findByUsernameAndPassword(anyString(), anyString()))
                .thenReturn(Optional.of(Arrays.asList(User.builder()
                        .username(userDTO.getUserName())
                        .password(userDTO.getPassword())
                        .emailid(userDTO.getEmailid())
                        .userType(userDTO.getUserType())
                        .build())));

        // when
        UserDTO actual = userService.getUserDetails(userDTO.getUserName(), userDTO.getPassword());

        // then
        assertThat(actual.getEmailid(), is("yash@hm.com"));
        assertThat(actual.getUserType(), is(UserType.PARENT));
    }

    @Test
    public void should_not_return_user_details_of_a_invalid_user() {
        // given
        UserDTO userDTO = UserDTO.builder()
                .userName("yash")
                .password("pass")
                .emailid("yash@hm.com")
                .userType(UserType.PARENT)
                .build();

        when(userRepository.findByUsernameAndPassword(anyString(), anyString()))
                .thenReturn(Optional.empty());

        // when
        UserDTO actual = userService.getUserDetails(userDTO.getUserName(), userDTO.getPassword());

        // then
        assertThat(actual, is(nullValue()));
    }

    @Test
    public void should_add_user_on_signup() {
        // given
        UserDTO userDTO = UserDTO.builder()
                .userType(UserType.PARENT)
                .userName("yash")
                .password("pass")
                .emailid("yash@g.com")
                .build();

        when(userRepository.saveAndFlush(Mockito.any(User.class)))
                .thenReturn(User.builder()
                        .username(userDTO.getUserName())
                        .password(userDTO.getPassword())
                        .emailid(userDTO.getEmailid())
                        .userType(userDTO.getUserType())
                        .id(1l)
                        .build());
        // when
        User actual = userService.addUser(userDTO);


        // then
        assertThat(actual.getId(), is(1l));
    }



}
