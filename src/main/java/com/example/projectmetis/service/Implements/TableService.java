package com.example.projectmetis.service.Implements;

import com.example.projectmetis.dto.TableDto;
import com.example.projectmetis.dto.TableTopDto;
import com.example.projectmetis.models.Table;
import com.example.projectmetis.models.TableTop;
import com.example.projectmetis.models.User;
import com.example.projectmetis.repos.TableRepository;
import com.example.projectmetis.repos.TableTopRepository;
import com.example.projectmetis.service.ServiceInterface;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableService implements ServiceInterface<Table, TableDto> {
    private final TableRepository tableRepository;

    public Table create(String article,
                        Long timeAssembly,
                        List<Byte> qrCode,
                        TableTop tableTop,
                        Long marketPlaceId,
                        User user) {
        Table table = new Table();
        table.setTimeAssembly(timeAssembly);
        table.setArticle(article);
        table.setUser(user);
        table.setMarketPlaceId(marketPlaceId);
        table.setQrCode(qrCode);
        table.setTableTop(tableTop);
        return tableRepository.save(table);
    }

    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public @NotNull List<Table> getAll() {
        return tableRepository.findAll();
    }

    @Override
    public @Nullable Table getByName(@NotNull String name) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public @Nullable Table getById(@NotNull Long id) {
        return tableRepository.findById(id).orElse(new Table());
    }

    @Override
    public @Nullable Table deleteById(@NotNull Long id) {
        Optional<Table> table = tableRepository.findById(id);
        tableRepository.deleteById(id);
        return table.orElse(null);
    }

    @Override
    public @Nullable Table deleteByName(@NotNull String name) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public void deleteAll() {
        tableRepository.deleteAll();
    }

    @Override
    public @Nullable Table edit(@NotNull TableDto dto) {
        Table object = tableRepository.findById(dto.getId()).orElse(null);
        if (object == null) return null;
        object.setId(dto.getId());
        return tableRepository.save(object);
    }
}
