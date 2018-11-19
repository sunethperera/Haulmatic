package com.abc.restapi.repository;

import com.abc.restapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository <Student, Integer>{

}
