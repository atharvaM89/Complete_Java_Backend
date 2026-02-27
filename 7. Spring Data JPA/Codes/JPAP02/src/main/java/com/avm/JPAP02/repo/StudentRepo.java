package com.avm.JPAP02.repo;

import com.avm.JPAP02.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {
}
