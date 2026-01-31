package com.backend.v1.service.serviceImpl;

import com.backend.v1.common.response.ApiResponse;
import com.backend.v1.common.response.PaginatedApiResponse;
import com.backend.v1.dto.request.GetAllUserRequest;
import com.backend.v1.dto.request.UserCreatedRequestDto;
import com.backend.v1.dto.response.UserResponseDto;
import com.backend.v1.exceptions.DuplicateEntityException;
import com.backend.v1.model.Role;
import com.backend.v1.model.User;
import com.backend.v1.repository.RoleRepository;
import com.backend.v1.repository.UserRepository;
import com.backend.v1.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public ApiResponse createUser(UserCreatedRequestDto request) {

        Role role = roleRepository.findByName(request.getRole())
                .orElseThrow(() -> new EntityNotFoundException("Default role USER not found."));

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEntityException("Email is already in use.");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // encode later if needed
        user.setRole(role);

        userRepository.save(user);

        UserResponseDto dto = modelMapper.map(user, UserResponseDto.class);

        return ApiResponse.builder()
                .success(1)
                .code(HttpStatus.OK.value())
                .data(Map.of("currentUser", dto))
                .message("User account created Successfully.")
                .build();
    }


    @Override
    public PaginatedApiResponse<UserResponseDto> getAllUsers(GetAllUserRequest getAllUserRequest) {
        return null;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
            return userRepository.findByEmail(email);
    }


}
