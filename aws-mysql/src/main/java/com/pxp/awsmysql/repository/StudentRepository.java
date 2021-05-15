package com.pxp.awsmysql.repository;

import com.pxp.awsmysql.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    public boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
