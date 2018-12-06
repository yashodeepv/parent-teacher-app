package com.ysol.ptapp.parentteacherservices.services;

import com.ysol.ptapp.parentteacherservices.child.Child;
import com.ysol.ptapp.parentteacherservices.course.*;
import com.ysol.ptapp.parentteacherservices.entity.UserType;
import com.ysol.ptapp.parentteacherservices.parent.Parent;
import com.ysol.ptapp.parentteacherservices.teacher.TeacherRepository;
import com.ysol.ptapp.parentteacherservices.teacher.Teacher;
import com.ysol.ptapp.parentteacherservices.user.User;
import com.ysol.ptapp.parentteacherservices.teacher.TeacherMapper;
import com.ysol.ptapp.parentteacherservices.teacher.TeacherService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TeacherServiceTest {

    @Mock
    TeacherRepository teacherRepository;

    @Mock
    TeacherMapper teacherMapper;

    @InjectMocks
    TeacherService teacherService;

    User user = User.builder()
            .username("y1")
            .password("2")
            .emailid("uu")
            .userType(UserType.TEACHER)
            .firstName("Teacher")
            .lastName("v")
            .build();

    User parentUser = User.builder()
            .userType(UserType.PARENT)
            .username("p1")
            .build();

    Teacher teacher = Teacher.builder()
            .id(1l)
            .user(user)
            .build();

    Parent parent = Parent.builder()
            .user(parentUser)
            .build();

    Child child = Child.builder()
            .firstName("child1")
            .parent(parent)
            .build();

    Course course = Course.builder()
            .teacher(teacher)
            .build();


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_be_able_to_add_a_student_on_admission() {
        // given

        // when

        // then
    }

    @Test
    public void should_be_able_to_create_a_new_course_batch_in_the_system() {
        // given

        // when

        // then

    }

    @Test
    public void should_be_able_to_add_students_on_the_course_batch() {
        // given

        // when

        // then

    }

    @Test
    public void should_be_able_to_get_list_of_all_students_in_her_batch() {
        // given

        // when

        // then

    }

    @Test
    public void should_be_able_to_enter_homework_details_for_a_given_batch() {
        // given

        // when

        // then

    }

    @Test
    public void should_be_able_to_enter_attendance_for_a_given_course_batch() {
        // given

        // when

        // then

    }

    @Test
    public void should_be_able_to_view_attendance_history_for_a_given_students_in_a_batch() {
        // given

        // when

        // then

    }

    @Test
    public void should_be_able_to_schedule_a_parent_meeting() {
        // given

        // when

        // then

    }

}
