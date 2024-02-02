package edu.iu.c322.test1.controllers;

import edu.iu.c322.test1.model.Question;
import edu.iu.c322.test1.repository.FileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private FileRepository fileRepository;

    public QuestionController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @PostMapping
    public boolean add(@RequestBody Question question) {
        try {
            return fileRepository.add(question);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<Question> findAll() {
        try {
            return fileRepository.findAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/search")
    public List<Question> find( String answer) {
        try {
            return fileRepository.find(answer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public Question get( int id) {
        try {
            return fileRepository.get(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/{id}/image")
    public boolean updateImage(@PathVariable Integer id,
                                MultipartFile file) {
        try {
            return fileRepository.updateImage(id, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<?> getImage(@PathVariable Integer id) {
        try {
            byte[] image = fileRepository.getImage(id);
            MediaType imagePngType = MediaType.IMAGE_PNG;
            assert imagePngType != null; // This line is to ensure non-nullness explicitly.
            return ResponseEntity.status(HttpStatus.FOUND)
                    .contentType(imagePngType)
                    .body(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
