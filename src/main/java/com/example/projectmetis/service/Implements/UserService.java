package com.example.projectmetis.service.Implements;

import com.example.projectmetis.dto.UserDto;
import com.example.projectmetis.models.User;
import com.example.projectmetis.repos.UserRepository;
import com.example.projectmetis.service.ServiceInterface;
import com.example.projectmetis.service.TimeService;
import com.nimbusds.jose.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements ServiceInterface<User, UserDto> {
    private final UserRepository userRepository;
    private final TimeService timeService;

    public UserService(UserRepository userRepository,
                       TimeService timeService) {
        this.userRepository = userRepository;
        this.timeService = timeService;
    }

    public User create(@NotNull String name) {
        User user = new User();
        user.setName(name);
        return userRepository.save(user);
    }

    public void addStartWork(Long id){
        Long workStart = timeService.getTime();
        User user = userRepository.findById(id).orElse(null);
        if(user == null) return;

        user.getWork().add(Pair.of(workStart, null));
    }

    public void addEndWork(Long id){
        Long workEnd = timeService.getTime();
        User user = userRepository.findById(id).orElse(null);
        if(user == null) return;

        Long workStart = user.getWork().getLast().getLeft();
        user.getWork().removeLast();
        user.getWork().add(Pair.of(workStart, workEnd));
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
        return userRepository.findUserById(id);
    }

    @Override
    public @Nullable User deleteById(@NotNull Long id) {
        Optional<User> user = userRepository.findById(id);
        userRepository.deleteById(id);
        return user.orElse(null);
    }

    @Override
    public @Nullable User deleteByName(@NotNull String name) {
        User user = userRepository.findByName(name).orElse(null);
        if (user == null) return null;

        userRepository.deleteById(user.getId());
        return user;
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
