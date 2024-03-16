package com.example.projectmetis.controllers;

import com.example.projectmetis.dto.TableDto;
import com.example.projectmetis.models.Table;
import com.example.projectmetis.service.Implements.TableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(path = "/table")
public class TableController {
    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping("/create")
    public ResponseEntity<TableDto> createTable(@RequestParam String article,
                                                @RequestParam Long timeAssembly,
                                                @RequestParam List<Byte> qrCode,
                                                @RequestParam Long tableTopId,
                                                @RequestParam Long marketPlaceId,
                                                @RequestParam Long userId,
                                                @RequestParam String ... parts_articles) {
        return new ResponseEntity<>(new TableDto(tableService.create(article,
                timeAssembly,
                qrCode,
                tableTopId,
                marketPlaceId,
                userId,
                parts_articles)),
                HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<TableDto> getTable(@RequestParam Long id) {
        Table table = tableService.getById(id);
        // можно заменить BAD_REQUEST на NO_CONTENT или NOT_FOUND
        return table == null ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(new TableDto(table),
                        HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TableDto>> getAllTable() {
        return new ResponseEntity<>(
                tableService.getAll().stream().map(TableDto::new).toList(),
                HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<TableDto> deleteTable(@RequestParam Long id) {
        Table table = tableService.deleteById(id);
        // можно заменить BAD_REQUEST на NO_CONTENT или NOT_FOUND
        return table == null ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(new TableDto(table),
                        HttpStatus.OK);
    }

    @PatchMapping("/edit")
    public ResponseEntity<TableDto> editTable(@RequestParam Long id,
                                              @RequestParam Long timeAssembly,
                                              @RequestParam String article,
                                              @RequestParam List<Byte> qrCode,
                                              @RequestParam Long marketPlaceId) {
        TableDto table = new TableDto();
        table.setId(id);
        table.setTimeAssembly(timeAssembly);
        table.setArticle(article);
        table.setQrCode(qrCode);
        table.setMarketPlaceId(marketPlaceId);

        Table tableEdit = tableService.edit(table);
        // можно заменить BAD_REQUEST на NO_CONTENT или NOT_FOUND
        return tableEdit == null ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(new TableDto(tableEdit),
                        HttpStatus.OK);
    }
}
