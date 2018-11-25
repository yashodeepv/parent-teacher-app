package com.ysol.ptapp.parentteacherservices.services;

import com.ysol.ptapp.parentteacherservices.child.Child;
import com.ysol.ptapp.parentteacherservices.child.ChildRepository;
import com.ysol.ptapp.parentteacherservices.child.ChildService;
import com.ysol.ptapp.parentteacherservices.exceptions.UserNotFoundException;
import com.ysol.ptapp.parentteacherservices.parent.Parent;
import com.ysol.ptapp.parentteacherservices.user.User;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class ChildServiceTest {

    @Mock
    ChildRepository childRepository;

    @InjectMocks
    ChildService childService;

    User user = User.builder()
            .username("yy")
            .build();

    Parent parent = Parent.builder()
            .id(3l)
            .user(user)
            .build();

    Child child = Child.builder()
            .firstName("y")
            .lastName("v")
            .parent(parent)
            .build();


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_add_child() {
        // given
        when(childRepository.saveAndFlush(child)).thenReturn(Child.builder()
                .id(2l)
                .firstName("y")
                .lastName("v")
                .parent(parent)
                .build());

        // when
        Child actual = childService.save(child);

        // then
        assertThat(actual, not(nullValue()));
        assertThat(actual.getId(), is(2l));
    }

    @Test
    public void should_return_list_of_childs_for_valid_parent() {
        // given
        when(childRepository.findByParentId(3l))
                .thenReturn(Optional.of(Arrays.asList(child)));

        // when
        List<Child> actual = childService.getChildByParent(parent);

        // then
        assertThat(actual, not(nullValue()));
        assertThat(actual.size(), is(1));
        actual.stream()
                .forEach(a -> {
                    assertThat(a.getParent().getUser(), not(nullValue()));
                    assertThat(a.getParent().getUser().getUsername(), is("yy"));
                });

    }

    @Test
    public void should_delete_valid_child_of_a_given_parent() {
        // given
        when(childRepository.findByParentIdAndFirstNameAndLastName(Mockito.anyLong(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Optional.of(Arrays.asList(child)));

        // when
        boolean actual = childService.deleteChild(child);

        // then
        assertThat(actual, CoreMatchers.is(true));

    }

    @Test
    public void should_not_delete_non_existing_child_of_a_given_parent() {
        // given
        when(childRepository.findByParentIdAndFirstNameAndLastName(Mockito.anyLong(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Optional.of(Collections.emptyList()));

        expectedException.expect(UserNotFoundException.class);

        boolean actual = childService.deleteChild(child);

    }

}
