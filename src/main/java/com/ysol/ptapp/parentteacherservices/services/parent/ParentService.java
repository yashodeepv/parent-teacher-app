package com.ysol.ptapp.parentteacherservices.services.parent;

import com.ysol.ptapp.parentteacherservices.entity.ChildDTO;
import com.ysol.ptapp.parentteacherservices.entity.ParentDTO;
import com.ysol.ptapp.parentteacherservices.exceptions.UserNotFoundException;
import com.ysol.ptapp.parentteacherservices.jpa.ChildRepository;
import com.ysol.ptapp.parentteacherservices.jpa.ParentRepository;
import com.ysol.ptapp.parentteacherservices.jpa.UserRepository;
import com.ysol.ptapp.parentteacherservices.jpa.domain.Child;
import com.ysol.ptapp.parentteacherservices.jpa.domain.Parent;
import com.ysol.ptapp.parentteacherservices.jpa.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class ParentService {

    private final ParentRepository parentRepository;
    private final ChildRepository childRepository;
    private final ChildMapper childMapper;
    private final UserRepository userRepository;

    @Autowired
    public ParentService(ParentRepository parentRepository,
                         ChildRepository childRepository,
                         ChildMapper childMapper,
                         UserRepository userRepository) {
        this.parentRepository = parentRepository;
        this.childRepository = childRepository;
        this.childMapper = childMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<ChildDTO> addChild(ChildDTO build) {

        String userName = build.getParent().getUser().getUserName();

        System.out.println(userName);

        List<User> parentUser = userRepository.findByUsername(userName)
                .orElse(Collections.emptyList());

        if (parentUser.size() <= 0) {
            throw new UserNotFoundException();
        }

        Long id = parentUser.get(0).getId();

        System.out.println(parentUser);
        Parent byUserId = parentRepository.findByUserId(id).orElseThrow(UserNotFoundException::new);

        childRepository.saveAndFlush(
                Child.builder()
                        .firstName(build.getFirstName())
                        .lastName(build.getLastName())
                        .parent(byUserId)
                        .build()
        );

        return childMapper.map(
                childRepository.findByParentId(byUserId.getId())
                        .orElseGet(null));
    }
}
