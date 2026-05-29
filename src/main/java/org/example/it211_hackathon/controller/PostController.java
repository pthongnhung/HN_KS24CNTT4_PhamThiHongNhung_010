package org.example.it211_hackathon.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.it211_hackathon.dto.PostRequestDTO;
import org.example.it211_hackathon.dto.PostResponseDTO;
import org.example.it211_hackathon.dto.PostUpdateRequestDTO;
import org.example.it211_hackathon.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDTO> create(
            @Valid @RequestBody PostRequestDTO request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.create(request));
    }

    @GetMapping
    public ResponseEntity<Page<PostResponseDTO>> getAll(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ResponseEntity.ok(postService.getAll(keyword, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(postService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody PostRequestDTO request
    ) {
        return ResponseEntity.ok(postService.update(id, request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PostResponseDTO> patchUpdate(
            @PathVariable Long id,
            @Valid @RequestBody PostUpdateRequestDTO request
    ) {
        return ResponseEntity.ok(postService.patchUpdate(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}