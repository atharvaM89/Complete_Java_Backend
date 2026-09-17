package com.avm.JPAP02.repo;

import com.avm.JPAP02.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher, Long> {
}
