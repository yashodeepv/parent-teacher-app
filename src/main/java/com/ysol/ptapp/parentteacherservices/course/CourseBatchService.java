package com.ysol.ptapp.parentteacherservices.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseBatchService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseBatchService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


}
