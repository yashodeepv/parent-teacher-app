package com.ysol.ptapp.parentteacherservices.child;

import com.ysol.ptapp.parentteacherservices.course.ChildHomeworkStatus;
import com.ysol.ptapp.parentteacherservices.course.ChildHomeworkStatusRepository;
import com.ysol.ptapp.parentteacherservices.course.CourseBatchDetails;
import com.ysol.ptapp.parentteacherservices.course.CourseBatchDetailsRepository;
import com.ysol.ptapp.parentteacherservices.exceptions.CourseNotFoundException;
import com.ysol.ptapp.parentteacherservices.exceptions.UserNotFoundException;
import com.ysol.ptapp.parentteacherservices.parent.Parent;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChildService {

    private final ChildRepository childRepository;
    private final ChildHomeworkStatusRepository childHomeworkStatusRepository;
    private final CourseBatchDetailsRepository courseBatchDetailsRepository;

    public ChildService(ChildRepository childRepository,
                        ChildHomeworkStatusRepository childHomeworkStatusRepository,
                        CourseBatchDetailsRepository courseBatchDetailsRepository) {
        this.childRepository = childRepository;
        this.childHomeworkStatusRepository = childHomeworkStatusRepository;
        this.courseBatchDetailsRepository = courseBatchDetailsRepository;
    }


    public Child save(Child build) {
        Child child = childRepository.saveAndFlush(
                Child.builder()
                        .parent(build.getParent())
                        .firstName(build.getFirstName())
                        .lastName(build.getLastName())
                        .build()
        );
        return child;
    }

    public List<Child> getChildByParent(Parent parent) {
        return childRepository.findByParentId(parent.getId())
                .orElse(Collections.emptyList());
    }

    public boolean deleteChild(Child child) {
        List<Child> children =
                childRepository.findByParentIdAndFirstNameAndLastName(
                        child.getParent().getId(),
                        child.getFirstName(),
                        child.getLastName())
                        .orElseThrow(UserNotFoundException::new);

        if (children.isEmpty()) {
            throw new UserNotFoundException();
        }

        children.stream()
                .forEach(childRepository::delete);

        return true;
    }

    public List<ChildHomeworkStatus> getHomeworkStatus(Child child) {
        List<CourseBatchDetails> courses = courseBatchDetailsRepository.findByChildId(child.getId())
                .orElseThrow(CourseNotFoundException::new);

        return courses.stream()
                .map(a -> childHomeworkStatusRepository.findByCourseBatchDetilsId(a.getId())
                        .orElseThrow(CourseNotFoundException::new))
                .collect(Collectors.toList());

    }
}
