package com.avm.JPAP02.repo;

import com.avm.JPAP02.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopRepo extends JpaRepository<Laptop, Long> {
}
