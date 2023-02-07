package com.c823.consorcio.auth.service.seeder;

import com.c823.consorcio.entity.RoleEntity;
import com.c823.consorcio.entity.TurnEntity;
import com.c823.consorcio.enums.RoleName;
import com.c823.consorcio.enums.Turn;
import com.c823.consorcio.repository.IRoleRepository;
import com.c823.consorcio.repository.ITurnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StartData {

    private final IRoleRepository roleRepository;
    private final ITurnRepository turnRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event){
        List<RoleEntity> roles = roleRepository.findAll();
        List<TurnEntity> turns = turnRepository.findAll();
        if(roles.isEmpty()|| turns.isEmpty())
            this.createRoles();
            this.createTurns();

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
    private void createTurns() {
        this.createTurn(1L, Turn.MORNING);
        this.createTurn(2L, Turn.AFTERNOON);
        this.createTurn(3L, Turn.NIGHT);
    }

    private void createTurn(long l, Turn name) {
        TurnEntity turn = new TurnEntity();
        turn.setTurnId(l);
        turn.setTurnName(name.getName());
        turnRepository.save(turn);
    }





}
