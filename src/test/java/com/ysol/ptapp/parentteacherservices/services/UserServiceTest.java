package com.ysol.ptapp.parentteacherservices.services;


import com.ysol.ptapp.parentteacherservices.entity.UserType;
import com.ysol.ptapp.parentteacherservices.exceptions.UserNotFoundException;
import com.ysol.ptapp.parentteacherservices.user.UserRepository;
import com.ysol.ptapp.parentteacherservices.user.User;
import com.ysol.ptapp.parentteacherservices.user.UserService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    User user = User.builder()
            .username("yash")
            .password("pass")
            .emailid("yash@hm.com")
            .userType(UserType.PARENT)
            .build();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_allow_valid_user_credentials() {
        // given
        when(userRepository.findByUsernameAndPassword(anyString(), anyString()))
                .thenReturn(Optional.of(Arrays.asList(User.builder().build())));

        // when
        boolean actual = userService.validateCredentials(user.getUsername(), user.getPassword());

        // then
        assertThat(actual, is(true));
    }

    @Test
    public void should_reject_invalid_user_credentials() {
        // given
        when(userRepository.findByUsernameAndPassword(anyString(), anyString())).thenReturn(Optional.empty());

        // when
        boolean actual = userService.validateCredentials(user.getUsername(), user.getPassword());

        // then
        assertThat(actual, is(false));
    }

    @Test
    public void should_return_user_details_of_a_valid_user() {
        // given

        when(userRepository.findByUsernameAndPassword(anyString(), anyString()))
                .thenReturn(Optional.of(Arrays.asList(User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .emailid(user.getEmailid())
                        .userType(user.getUserType())
                        .build())));

        // when
        User actual = userService.getUserDetails(user.getUsername(), user.getPassword());

        // then
        assertThat(actual.getEmailid(), is("yash@hm.com"));
        assertThat(actual.getUserType(), is(UserType.PARENT));
    }

    @Test
    public void should_not_return_user_details_of_a_invalid_user() {
        // given

        when(userRepository.findByUsernameAndPassword(anyString(), anyString()))
                .thenReturn(Optional.empty());

        // when

        expectedException.expect(UserNotFoundException.class);
        User actual = userService.getUserDetails(user.getUsername(), user.getPassword());

    }

    @Test
    public void should_add_user_on_signup() {
        // given
        when(userRepository.saveAndFlush(Mockito.any(User.class)))
                .thenReturn(User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .emailid(user.getEmailid())
                        .userType(user.getUserType())
                        .id(1l)
                        .build());
        // when
        User actual = userService.addUser(user);


        // then
        assertThat(actual.getId(), is(1l));
    }

}
