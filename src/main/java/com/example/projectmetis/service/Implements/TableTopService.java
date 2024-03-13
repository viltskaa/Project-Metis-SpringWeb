package com.example.projectmetis.service.Implements;

import com.example.projectmetis.dto.TableTopDto;
import com.example.projectmetis.models.TableTop;
import com.example.projectmetis.models.User;
import com.example.projectmetis.repos.TableTopRepository;
import com.example.projectmetis.service.ServiceInterface;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableTopService implements ServiceInterface<TableTop, TableTopDto> {
    private final TableTopRepository tableTopRepository;

    public TableTop create(double width,
                           double height,
                           double perimeter,
                           double depth,
                           String colorMain,
                           String colorEdge,
                           String material,
                           String article,
                           Long timeAssembly,
                           User user) {
        TableTop tableTop = new TableTop();
        tableTop.setTimeAssembly(timeAssembly);
        tableTop.setArticle(article);
        tableTop.setHeight(height);
        tableTop.setDepth(depth);
        tableTop.setMaterial(material);
        tableTop.setUser(user);
        tableTop.setColorMain(colorMain);
        tableTop.setColorEdge(colorEdge);
        tableTop.setWidth(width);
        tableTop.setPerimeter(perimeter);
        return tableTopRepository.save(tableTop);
    }

    public TableTopService(TableTopRepository tableTopRepository) {
        this.tableTopRepository = tableTopRepository;
    }

    @Override
    public @NotNull List<TableTop> getAll() {
        return tableTopRepository.findAll();
    }

    @Override
    public @Nullable TableTop getByName(@NotNull String name) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public @Nullable TableTop getById(@NotNull Long id) {
        return tableTopRepository.findById(id).orElse(new TableTop());
    }

    @Override
    public @Nullable TableTop deleteById(@NotNull Long id) {
        Optional<TableTop> tableTop = tableTopRepository.findById(id);
        tableTopRepository.deleteById(id);
        return tableTop.orElse(null);
    }

    @Override
    public @Nullable TableTop deleteByName(@NotNull String name) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public void deleteAll() {
        tableTopRepository.deleteAll();
    }

    @Override
    public @Nullable TableTop edit(@NotNull TableTopDto dto) {
        TableTop object = tableTopRepository.findById(dto.getId()).orElse(null);
        if (object == null) return null;
        object.setId(dto.getId());
        return tableTopRepository.save(object);
    }
}
