package com.backend.v1.service.serviceImpl;

import com.backend.v1.model.Role;
import com.backend.v1.repository.RoleRepository;
import com.backend.v1.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

//    private final RoleRepository roleRepository;

    @Override
    public Role CreateRole(Role role) {
        return null;
    }

}
