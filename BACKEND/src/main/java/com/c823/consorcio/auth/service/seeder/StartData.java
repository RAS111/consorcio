package com.c823.consorcio.auth.service.seeder;

import com.c823.consorcio.entity.AmenitieEntity;
import com.c823.consorcio.entity.RoleEntity;
import com.c823.consorcio.entity.TurnEntity;
import com.c823.consorcio.enums.Amenities;
import com.c823.consorcio.enums.RoleName;
import com.c823.consorcio.enums.Turn;
import com.c823.consorcio.repository.IRoleRepository;
import com.c823.consorcio.repository.ITurnRepository;
import com.c823.consorcio.repository.IamentieRepository;
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
    private final IamentieRepository amentieRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        List<RoleEntity> roles = roleRepository.findAll();
        List<TurnEntity> turns = turnRepository.findAll();
        List<AmenitieEntity> amenities = amentieRepository.findAll();
        if (roles.isEmpty() || turns.isEmpty() || amenities.isEmpty()) {
            this.createRoles();
            this.createTurns();
            this.createAmenities();
        }

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
    private void createAmenities() {
        this.createAmenitie(1L, Amenities.GYM);
        this.createAmenitie(2L, Amenities.GRILL);
        this.createAmenitie(3L, Amenities.LOUNGE);
        this.createAmenitie(4L, Amenities.SWPOOL);
        this.createAmenitie(5L, Amenities.TENNIS);
    }

    private void createAmenitie(Long l, Amenities name) {
        AmenitieEntity amenitie = new AmenitieEntity();
        amenitie.setId(l);
        amenitie.setAmenitieName(name.getName());
        amentieRepository.save(amenitie);
    }




}
