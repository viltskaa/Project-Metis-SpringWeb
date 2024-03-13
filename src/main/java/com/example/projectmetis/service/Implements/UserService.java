package com.example.projectmetis.service.Implements;

import com.example.projectmetis.dto.UserDto;
import com.example.projectmetis.models.User;
import com.example.projectmetis.repos.UserRepository;
import com.example.projectmetis.service.ServiceInterface;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class UserService implements ServiceInterface<User, UserDto> {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(@NotNull String name) {
        User user = new User();
        user.setName(name);
        return userRepository.save(user);
    }


    @Override
    public @NotNull List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public @Nullable User getByName(@NotNull String name) {
        return userRepository.findByName(name).orElse(new User());
    }

    @Override
    public @Nullable User getById(@NotNull Long id) {
        return userRepository.findUsersById(id);
    }

    @Override
    public @Nullable User deleteById(@NotNull Long id) {
        Optional<User> user = userRepository.findById(id);
        userRepository.deleteById(id);
        return user.orElse(null);
    }

    @Override
    public @Nullable User deleteByName(@NotNull String name) {
        Optional<User> user = userRepository.findByName(name);
        if (user.isEmpty()) return new User();

        userRepository.deleteById(user.get().getId());
        return user.get();
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public @Nullable User edit(@NotNull UserDto dto) {
        User user = userRepository.findById(dto.getId()).orElse(null);
        if (user == null) return null;
        user.setName(dto.getName());
        return userRepository.save(user);
    }
}
