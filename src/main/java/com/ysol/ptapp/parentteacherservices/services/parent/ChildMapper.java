package com.ysol.ptapp.parentteacherservices.services.parent;

import com.ysol.ptapp.parentteacherservices.entity.ChildDTO;
import com.ysol.ptapp.parentteacherservices.jpa.domain.Child;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChildMapper {

    private final ParentMapper parentMapper;

    @Autowired
    public ChildMapper(ParentMapper parentMapper) {
        this.parentMapper = parentMapper;
    }

    public ChildDTO map(Child child) {
        return ChildDTO.builder()
                .firstName(child.getFirstName())
                .lastName(child.getLastName())
                .parent(parentMapper.map(child.getParent()))
                .build();
    }

    public List<ChildDTO> map(List<Child> children) {
        if(children == null) {
            return Collections.emptyList();
        }
        return children.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

}
