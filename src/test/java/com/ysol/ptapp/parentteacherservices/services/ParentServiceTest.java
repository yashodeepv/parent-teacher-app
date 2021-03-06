package com.ysol.ptapp.parentteacherservices.services;

import com.ysol.ptapp.parentteacherservices.course.*;
import com.ysol.ptapp.parentteacherservices.entity.UserType;
import com.ysol.ptapp.parentteacherservices.exceptions.UserNotFoundException;
import com.ysol.ptapp.parentteacherservices.parent.ParentRepository;
import com.ysol.ptapp.parentteacherservices.child.Child;
import com.ysol.ptapp.parentteacherservices.parent.Parent;
import com.ysol.ptapp.parentteacherservices.teacher.Teacher;
import com.ysol.ptapp.parentteacherservices.user.User;
import com.ysol.ptapp.parentteacherservices.child.ChildService;
import com.ysol.ptapp.parentteacherservices.parent.ParentService;
import com.ysol.ptapp.parentteacherservices.user.UserService;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class ParentServiceTest {

    @Mock
    ParentRepository parentRepository;

    @Mock
    ChildService childService;

    @Mock
    UserService userService;

    @InjectMocks
    ParentService parentService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    User user = User.builder().userType(UserType.PARENT).username("parent1").id(2l).build();
    User userTeacher = User.builder().userType(UserType.TEACHER).username("teacher").id(13l).build();
    Parent parent = Parent.builder()
            .id(1l)
            .user(user).build();
    Child child = Child.builder().firstName("Y1").parent(parent).build();
    Teacher teacher = Teacher.builder()
            .id(12l)
            .user(userTeacher)
            .build();
    Course course = Course.builder()
            .teacher(teacher)
            .build();
    CourseBatchDetails courseBatchDetails = CourseBatchDetails.builder()
            .course(course)
            .child(child)
            .build();
    Homework homework = Homework.builder()
            .course(course)
            .homeworkDetails("Homework details 1")
            .id(14l)
            .build();
    ChildHomeworkStatus childHomeworkStatus = ChildHomeworkStatus.builder()
            .courseBatchDetails(courseBatchDetails)
            .homework(homework)
            .homeworkStatus(HomeworkStatus.ASSIGNED)
            .build();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        courseBatchDetails.setHomeworkStatuses(Arrays.asList(childHomeworkStatus));
        //parent.setChildren((Arrays.asList(child)));
    }

    @Test
    public void should_add_children() {
        // given
        when(parentRepository.findByUserId(2l))
                .thenReturn(Optional.of(parent));
        when(childService.save(Mockito.any(Child.class)))
                .thenReturn(child);
        when(childService.getChildByParent(parent))
                .thenReturn(asList(child));

        // when
        List<Child> actual = parentService.addChild(
                child
        );

        // then
        assertThat(actual, not(nullValue()));
        assertThat(actual.size(), is(1));
        actual.stream().forEach(a -> {
            assertThat(a.getParent(), not(nullValue()));
            assertThat(a.getParent().getUser(), not(nullValue()));
            assertThat(a.getParent().getUser().getUsername(), is("parent1"));
        });

    }

    @Test
    public void should_delete_existing_valid_children() {
        // given

        when(parentRepository.findByUserId(Mockito.anyLong())).thenReturn(Optional.of(parent));
        when(childService.deleteChild(child)).thenReturn(true);

        // when
        boolean b = parentService.deleteChild(child);

        // then
        assertThat(b, is(true));

    }

    @Test
    public void should_not_delete_invalid_children() {
        when(parentRepository.findByUserId(Mockito.anyLong())).thenReturn(Optional.of(parent));
        when(childService.deleteChild(child)).thenThrow(new UserNotFoundException());

        expectedException.expect(UserNotFoundException.class);

        boolean b = parentService.deleteChild(child);

    }

    @Test
    public void should_get_homework_details_for_given_student() {
        // given
        when(childService.getHomeworkStatus(child))
                .thenReturn(Arrays.asList(childHomeworkStatus));

        // when
        Map<Child, List<Homework>> childHomeWork = parentService.getChildHomeWork(Parent.builder()
                .children((Arrays.asList(child)))
                .build());

        // then
        assertThat(childHomeWork, CoreMatchers.not(CoreMatchers.nullValue()));
        childHomeWork.forEach((k, v) -> {
            assertThat(k.getFirstName(), CoreMatchers.is("Y1"));
            assertThat(v, CoreMatchers.not(CoreMatchers.nullValue()));
            v.stream()
                    .forEach(a -> {
                        assertThat(a.getHomeworkDetails(), CoreMatchers.is("Homework details 1"));
                    });
        });
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
