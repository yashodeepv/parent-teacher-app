package com.ysol.ptapp.parentteacherservices.coursebatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseBatchService {

    private final CourseBatchRepository courseBatchRepository;

    @Autowired
    public CourseBatchService(CourseBatchRepository courseBatchRepository) {
        this.courseBatchRepository = courseBatchRepository;
    }


}
