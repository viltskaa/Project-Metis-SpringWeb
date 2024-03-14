package com.example.projectmetis.service.Implements;

import com.example.projectmetis.dto.TableDto;
import com.example.projectmetis.models.AdditionalParts;
import com.example.projectmetis.models.Table;
import com.example.projectmetis.models.TableTop;
import com.example.projectmetis.models.User;
import com.example.projectmetis.repos.TableRepository;
import com.example.projectmetis.service.ServiceInterface;
import com.mongodb.MongoException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TableService implements ServiceInterface<Table, TableDto> {

    private final TableRepository tableRepository;
    private final AdditionalPartsService additionalPartsService;
    private final UserService userService;

    public Table create(String article,
                        Long timeAssembly,
                        List<Byte> qrCode,
                        TableTop tableTop,
                        Long marketPlaceId,
                        Long userId, String ... parts_articles) {
        Table table = new Table();
        table.setTimeAssembly(timeAssembly);
        table.setArticle(article);
        table.setUser(userService.getById(userId));
        table.setMarketPlaceId(marketPlaceId);
        table.setQrCode(qrCode);
        table.setTableTop(tableTop);
        table.setAdditionalParts(findAdPartsByArticles(parts_articles));
        return tableRepository.save(table);
    }

    private List<AdditionalParts> findAdPartsByArticles(String ... parts_articles) {
        List<AdditionalParts> listOfParts = new LinkedList<>();
        for (String part_article : parts_articles) {
            AdditionalParts additionalParts = additionalPartsService.getByArticleAndTable(part_article,null);
            if (additionalParts == null)
                throw new MongoException("Not Found Additional Part By Article");
            listOfParts.add(additionalParts);
        }
        return listOfParts;
    }

    public TableService(TableRepository tableRepository,
                        AdditionalPartsService additionalPartsService, UserService userService) {
        this.tableRepository = tableRepository;
        this.additionalPartsService = additionalPartsService;
        this.userService = userService;
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
        object.setArticle(dto.getArticle());
        object.setTimeAssembly(dto.getTimeAssembly());
        object.setQrCode(dto.getQrCode());
        object.setTableTop(dto.getTableTop());
        object.setMarketPlaceId(dto.getMarketPlaceId());
        object.setUser(dto.getUser());
        return tableRepository.save(object);
    }


}
