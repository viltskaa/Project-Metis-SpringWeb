package com.example.projectmetis.controllers;

import com.example.projectmetis.dto.TableTopDto;
import com.example.projectmetis.models.TableTop;
import com.example.projectmetis.service.Implements.TableTopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(path = "/tableTop")
public class TableTopController {
    private final TableTopService tableTopService;

    public TableTopController(TableTopService tableTopService) {
        this.tableTopService = tableTopService;
    }

    @PostMapping("/create")
    public ResponseEntity<TableTopDto> createTableTop(@RequestParam double width,
                                                      @RequestParam double height,
                                                      @RequestParam double perimeter,
                                                      @RequestParam double depth,
                                                      @RequestParam String colorMain,
                                                      @RequestParam String colorEdge,
                                                      @RequestParam String material,
                                                      @RequestParam String article,
                                                      @RequestParam Long timeAssembly,
                                                      @RequestParam Long userId) {
        return new ResponseEntity<>(new TableTopDto(tableTopService.create(width,
                height,
                perimeter,
                depth,
                colorMain,
                colorEdge,
                material,
                article,
                timeAssembly,
                userId)),
                HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<TableTopDto> getTableTop(@RequestParam Long id) {
        TableTop tableTop = tableTopService.getById(id);
        // можно заменить BAD_REQUEST на NO_CONTENT или NOT_FOUND
        return tableTop == null ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(new TableTopDto(tableTop),
                        HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TableTopDto>> getAllTableTop() {
        return new ResponseEntity<>(
                tableTopService.getAll().stream().map(TableTopDto::new).toList(),
                HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<TableTopDto> deleteTableTop(@RequestParam Long id) {
        TableTop tableTop = tableTopService.deleteById(id);
        // можно заменить BAD_REQUEST на NO_CONTENT или NOT_FOUND
        return tableTop == null ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(new TableTopDto(tableTop),
                        HttpStatus.OK);
    }

    @PatchMapping("/edit")
    public ResponseEntity<TableTopDto> editTableTop(@RequestParam Long id,
                                                    @RequestParam Long timeAssembly,
                                                    @RequestParam String article,
                                                    @RequestParam double perimeter,
                                                    @RequestParam double depth,
                                                    @RequestParam double width,
                                                    @RequestParam double height,
                                                    @RequestParam String colorMain,
                                                    @RequestParam String colorEdge,
                                                    @RequestParam String material) {
        TableTopDto tableTop = new TableTopDto();
        tableTop.setId(id);
        tableTop.setTimeAssembly(timeAssembly);
        tableTop.setArticle(article);
        tableTop.setHeight(height);
        tableTop.setMaterial(material);
        tableTop.setDepth(depth);
        tableTop.setWidth(width);
        tableTop.setPerimeter(perimeter);
        tableTop.setColorMain(colorMain);
        tableTop.setColorEdge(colorEdge);

        TableTop tableTopEdit = tableTopService.edit(tableTop);
        // можно заменить BAD_REQUEST на NO_CONTENT или NOT_FOUND
        return tableTopEdit == null ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(new TableTopDto(tableTopEdit),
                        HttpStatus.OK);
    }
}
