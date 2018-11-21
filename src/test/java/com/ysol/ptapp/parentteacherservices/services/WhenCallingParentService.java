package com.ysol.ptapp.parentteacherservices.services;

import com.ysol.ptapp.parentteacherservices.entity.ChildDTO;
import com.ysol.ptapp.parentteacherservices.entity.ParentDTO;
import com.ysol.ptapp.parentteacherservices.entity.UserDTO;
import com.ysol.ptapp.parentteacherservices.entity.UserType;
import com.ysol.ptapp.parentteacherservices.jpa.ChildRepository;
import com.ysol.ptapp.parentteacherservices.jpa.ParentRepository;
import com.ysol.ptapp.parentteacherservices.jpa.UserRepository;
import com.ysol.ptapp.parentteacherservices.jpa.domain.Child;
import com.ysol.ptapp.parentteacherservices.jpa.domain.Parent;
import com.ysol.ptapp.parentteacherservices.jpa.domain.User;
import com.ysol.ptapp.parentteacherservices.services.parent.ChildMapper;
import com.ysol.ptapp.parentteacherservices.services.parent.ParentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.when;

public class WhenCallingParentService {

    @Mock
    ParentRepository parentRepository;

    @Mock
    ChildRepository childRepository;

    @Mock
    ChildMapper childMapper;

    @Mock
    UserRepository userRepository;

    ParentService parentService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        parentService = new ParentService(parentRepository, childRepository, childMapper, userRepository);
    }

    @Test
    public void should_add_children() {
        // given

        User user = User.builder().userType(UserType.PARENT).username("parent1").id(2l).build();
        Parent parent = Parent.builder().id(1l).build();
        Child child = Child.builder().parent(parent).firstName("Y1").build();

        UserDTO userDTO = UserDTO.builder().userName("parent1").build();
        ParentDTO parentDTO = ParentDTO.builder().user(userDTO).build();
        ChildDTO childDTO = ChildDTO.builder().parent(parentDTO).build();

        when(userRepository.findByUsername("parent1")).thenReturn(Optional.of(asList(user)));
        when(parentRepository.findByUserId(2l)).thenReturn(Optional.of(parent));
        when(childRepository.findByParentId(1l)).thenReturn(Optional.of(asList(child)));
        when(childRepository.saveAndFlush(Mockito.any(Child.class))).thenReturn(child);
        when(childMapper.map(anyList())).thenReturn(asList(childDTO));

        // when


        List<ChildDTO> actual = parentService.addChild(
                childDTO
        );

        // then
        assertThat(actual, not(nullValue()));
        assertThat(actual.size(), is(1));
        actual.stream().forEach(a -> {
            assertThat(a.getParent(), not(nullValue()));
            assertThat(a.getParent().getUser(), not(nullValue()));
            assertThat(a.getParent().getUser().getUserName(), is("parent1"));
        });

    }

    @Test
    public void should_delete_children() {

    }

    @Test
    public void should_get_homework_details_for_given_student() {

    }

    @Test
    public void should_get_homework_completion_status_for_given_student() {

    }

    @Test
    public void should_get_homework_details_for_all_children() {

    }

    @Test
    public void should_get_all_children_details() {

    }

    @Test
    public void should_get_details_of_specific_child() {

    }

}
