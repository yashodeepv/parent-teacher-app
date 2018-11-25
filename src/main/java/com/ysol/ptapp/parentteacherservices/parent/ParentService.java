package com.ysol.ptapp.parentteacherservices.parent;

import com.ysol.ptapp.parentteacherservices.child.Child;
import com.ysol.ptapp.parentteacherservices.exceptions.UserNotFoundException;
import com.ysol.ptapp.parentteacherservices.coursebatch.ChildHomework;
import com.ysol.ptapp.parentteacherservices.user.User;
import com.ysol.ptapp.parentteacherservices.child.ChildService;
import com.ysol.ptapp.parentteacherservices.coursebatch.CourseBatchService;
import com.ysol.ptapp.parentteacherservices.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
        Parent parent = getParent(child);
        childService.deleteChild(child);
        return true;
    }

    private Parent getParent(Child child) {
        User user = child.getParent().getUser();
        return parentRepository.findByUserId(user.getId())
                .orElseThrow(UserNotFoundException::new);
    }

    public List<ChildHomework> getChildHomeWork(Child child) {
        Parent parent = getParent(child);

        return null;

    }
}
