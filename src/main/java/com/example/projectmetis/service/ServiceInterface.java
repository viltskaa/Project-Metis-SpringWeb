package com.example.projectmetis.service;

import com.example.projectmetis.models.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ServiceInterface <T, C >{
    @NotNull List<T> getAll();
    @Nullable T getByName(@NotNull String name);
    @Nullable T getById(@NotNull Long id);
    @Nullable T deleteById(@NotNull Long id);
    @Nullable T deleteByName(@NotNull String name);
    void deleteAll();
    @Nullable T edit(@NotNull C dto);
}
