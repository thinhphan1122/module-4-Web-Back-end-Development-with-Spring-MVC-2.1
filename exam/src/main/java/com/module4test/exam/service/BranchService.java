package com.module4test.exam.service;

import com.module4test.exam.entity.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchService {
    List<Branch> findAll();
    Optional<Branch> findById(long id);
}
