package com.example.projectmetis.controllers;

import com.example.projectmetis.dto.AdditionalPartsDto;
import com.example.projectmetis.dto.UserDto;
import com.example.projectmetis.models.AdditionalParts;
import com.example.projectmetis.models.User;
import com.example.projectmetis.service.Implements.AdditionalPartsService;
import com.example.projectmetis.service.Implements.TableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(path = "/adsParts")
public class AdditionalPartsController {
    private final AdditionalPartsService additionalPartsService;
    private final TableService tableService;

    public AdditionalPartsController(AdditionalPartsService additionalPartsService,
                                     TableService tableService) {
        this.additionalPartsService = additionalPartsService;
        this.tableService = tableService;
    }

    @PostMapping("/create")
    public ResponseEntity<AdditionalPartsDto> createAdditionalParts(@RequestParam Long timeAssembly,
                                                         @RequestParam String article,
                                                         @RequestParam Long userId) {
        return new ResponseEntity<>(
                new AdditionalPartsDto(additionalPartsService.create(timeAssembly, article, userId)),
                HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<AdditionalPartsDto> getAdditionalParts(@RequestParam Long id) {
        AdditionalParts additionalParts = additionalPartsService.getById(id);
        // возможно надо поменять BAD_REQUEST на другое
        return additionalParts == null ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(new AdditionalPartsDto(additionalParts), HttpStatus.OK);
    }

    @GetMapping("/getByArticleAndTable")
    public ResponseEntity<AdditionalPartsDto> getAdditionalParts(@RequestParam String article,
                                                                  @RequestParam Long tableId) {

        AdditionalParts adsParts = additionalPartsService.getByArticleAndTable(article,
                                                        tableService.getById(tableId));
        // возможно надо поменять BAD_REQUEST на другое
        return adsParts == null ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(new AdditionalPartsDto(adsParts), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AdditionalPartsDto>> getAllAdditionalParts() {
        return new ResponseEntity<>(
                additionalPartsService.getAll().stream().map(AdditionalPartsDto::new).toList()
                , HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<AdditionalPartsDto> deleteAdditionalParts(@RequestParam Long id) {
        AdditionalParts additionalParts = additionalPartsService.deleteById(id);
        // возможно надо поменять BAD_REQUEST на другое
        return additionalParts == null ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(new AdditionalPartsDto(additionalParts), HttpStatus.OK);
    }

    @PatchMapping("/edit")
    public ResponseEntity<AdditionalPartsDto> editAdditionalParts(@RequestParam Long id,
                                            @RequestParam Long timeAssembly,
                                            @RequestParam String article) {
        AdditionalPartsDto additionalParts = new AdditionalPartsDto();
        additionalParts.setId(id);
        additionalParts.setTimeAssembly(timeAssembly);
        additionalParts.setArticle(article);

        AdditionalParts additionalPartsUpd = additionalPartsService.edit(additionalParts);
        return additionalPartsUpd == null ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(new AdditionalPartsDto(additionalPartsUpd), HttpStatus.OK);
    }
}
