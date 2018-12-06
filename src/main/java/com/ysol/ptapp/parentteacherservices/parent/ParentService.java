package com.ysol.ptapp.parentteacherservices.parent;

import com.ysol.ptapp.parentteacherservices.child.Child;
import com.ysol.ptapp.parentteacherservices.course.ChildHomeworkStatus;
import com.ysol.ptapp.parentteacherservices.course.HomeworkStatus;
import com.ysol.ptapp.parentteacherservices.exceptions.UserNotFoundException;
import com.ysol.ptapp.parentteacherservices.course.Homework;
import com.ysol.ptapp.parentteacherservices.user.User;
import com.ysol.ptapp.parentteacherservices.child.ChildService;
import com.ysol.ptapp.parentteacherservices.course.CourseBatchService;
import com.ysol.ptapp.parentteacherservices.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ParentService {

    private final ParentRepository parentRepository;
    private final UserService userService;
    private final ChildService childService;
    private final CourseBatchService courseBatchService;

    @Autowired
    public ParentService(ParentRepository parentRepository,
                         UserService userService,
                         ChildService childService,
                         CourseBatchService courseBatchService) {
        this.parentRepository = parentRepository;
        this.userService = userService;
        this.childService = childService;
        this.courseBatchService = courseBatchService;
    }

    @Transactional
    public List<Child> addChild(Child build) {
        Parent parent = getParent(build);
        childService.save(build);
        return childService.getChildByParent(parent);
    }

    @Transactional
    public boolean deleteChild(Child child) {
        childService.deleteChild(child);
        return true;
    }

    private Parent getParent(Child child) {
        User user = child.getParent().getUser();
        return parentRepository.findByUserId(user.getId())
                .orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public Map<Child, List<Homework>> getChildHomeWork(Parent parent) {
        return parent.getChildren().stream()
                .collect(Collectors.toMap(
                        a -> a,
                        a -> childService.getHomeworkStatus(a).stream()
                                .map(b -> b.getHomework())
                                .collect(Collectors.toList())));
    }
}
