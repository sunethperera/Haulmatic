package com.abc.restapi.repository;

import com.abc.restapi.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepo extends JpaRepository<Module, Integer> {

}
