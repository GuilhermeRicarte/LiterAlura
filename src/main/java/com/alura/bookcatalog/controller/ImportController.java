package com.alura.bookcatalog.controller;

import com.alura.bookcatalog.service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/import")
@CrossOrigin(origins = "*")
public class ImportController {

    @Autowired
    private GutendexService gutendexService;

    @PostMapping("/page/{page}")
    public ResponseEntity<Map<String, String>> importBooksFromPage(@PathVariable Integer page) {
        try {
            gutendexService.importBooksFromApiPage(page);
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Books imported from page " + page);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "healthy");
        response.put("api", "Gutendex API is ready to use");
        return ResponseEntity.ok(response);
    }
}
