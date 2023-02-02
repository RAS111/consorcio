package com.c823.consorcio.auth.service.seeder;

import com.c823.consorcio.entity.RoleEntity;
import com.c823.consorcio.enums.RoleName;
import com.c823.consorcio.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StartData {

    private final IRoleRepository roleRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event){
        List<RoleEntity> roles = roleRepository.findAll();
        if(roles.isEmpty())
            this.createRoles();
    }

    private void createRoles() {
        this.createRol(1L, RoleName.ADMIN);
        this.createRol(2L, RoleName.USER);
    }

    private void createRol(long l, RoleName name) {
        RoleEntity role = new RoleEntity();
        role.setId(l);
        role.setRoleName(name.getName());
        roleRepository.save(role);
    }


}
