package com.example.projectmetis.service.Implements;

import com.example.projectmetis.dto.AdditionalPartsDto;
import com.example.projectmetis.models.AdditionalParts;
import com.example.projectmetis.models.Table;
import com.example.projectmetis.repos.AdditionalPartsRepository;
import com.example.projectmetis.service.ServiceInterface;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdditionalPartsService
        implements ServiceInterface<AdditionalParts, AdditionalPartsDto> {
    private final AdditionalPartsRepository additionalPartsRepository;
    private final UserService userService;

    public AdditionalPartsService(AdditionalPartsRepository additionalPartsRepository,
                                  UserService userService) {
        this.additionalPartsRepository = additionalPartsRepository;
        this.userService = userService;
    }

    public AdditionalParts create(Long timeAssembly, String article, Long userId){
        AdditionalParts additionalParts = new AdditionalParts();
        additionalParts.setTimeAssembly(timeAssembly);
        additionalParts.setArticle(article);
        // добавление того, кто изготовил
        additionalParts.setUser(userService.getById(userId));
        return additionalPartsRepository.save(additionalParts);
    }
    @Override
    public @NotNull List<AdditionalParts> getAll() {
        return additionalPartsRepository.findAll();
    }

    @Override
    public @Nullable AdditionalParts getByName(@NotNull String name) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public @Nullable AdditionalParts getById(@NotNull Long id) {
        return additionalPartsRepository.findAdditionalPartsById(id);
    }

    @Override
    public @Nullable AdditionalParts deleteById(@NotNull Long id) {
        Optional<AdditionalParts> additionalParts = additionalPartsRepository.findById(id);
        additionalPartsRepository.deleteById(id);
        return additionalParts.orElse(null);
    }

    @Override
    public @Nullable AdditionalParts deleteByName(@NotNull String name) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public void deleteAll() {
        additionalPartsRepository.deleteAll();
    }

    @Override
    public @Nullable AdditionalParts edit(@NotNull AdditionalPartsDto dto) {
        AdditionalParts additionalParts = additionalPartsRepository.findById(dto.getId()).orElse(null);
        if(additionalParts == null) return null;
        additionalParts.setTimeAssembly(additionalParts.getTimeAssembly());
        additionalParts.setArticle(additionalParts.getArticle());
        return additionalPartsRepository.save(additionalParts);
    }

    public AdditionalParts getByArticleAndTable(String article, Table table){
        return additionalPartsRepository.findByArticleAndTable(article,table);
    }
}
