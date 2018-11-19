package com.abc.restapi.controller;


import com.abc.restapi.exception.ResourceNotFoundException;
import com.abc.restapi.model.Module;
import com.abc.restapi.repository.ModuleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/abcModule")
public class ModuleController {

    @Autowired
    private ModuleRepo moduleRepo;


    @GetMapping("/modules")
    public List<Module> getAllModules() {
        return moduleRepo.findAll();
    }

    @GetMapping("/module/{id}")
    public Module getModuleById(@PathVariable(value = "id") int moduleId) {
        return moduleRepo.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module", "id", moduleId));
    }

    @PostMapping("/modules")
    public Module createModule(@Valid @RequestBody Module module) {
        return moduleRepo.save(module);
    }

    @PutMapping("/module/{id}")
    public Module updateModule(@PathVariable(value = "id") int moduleId,
                              @Valid @RequestBody Module moduleDetials) {

        Module module = moduleRepo.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module", "id", moduleId));

        module.setModuleName(moduleDetials.getModuleName());

        Module updatedSModule = moduleRepo.save(module);
        return updatedSModule;
    }

    @DeleteMapping("/module/{id}")
    public ResponseEntity<?> deleteModule(@PathVariable(value = "id") int moduleId) {
        Module module = moduleRepo.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module", "id", moduleId));

        moduleRepo.delete(module);

        return ResponseEntity.ok().build();
    }

}
