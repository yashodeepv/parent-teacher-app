package com.ysol.ptapp.parentteacherservices.child;

import com.ysol.ptapp.parentteacherservices.exceptions.UserNotFoundException;
import com.ysol.ptapp.parentteacherservices.parent.Parent;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ChildService {

    private final ChildRepository childRepository;

    public ChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
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

        if(children.isEmpty()) {
            throw new UserNotFoundException();
        }

        children.stream()
                .forEach(childRepository::delete);

        return true;
    }
}
