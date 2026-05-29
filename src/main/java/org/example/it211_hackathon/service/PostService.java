package org.example.it211_hackathon.service;

import org.example.it211_hackathon.dto.PostRequestDTO;
import org.example.it211_hackathon.dto.PostResponseDTO;
import org.example.it211_hackathon.dto.PostUpdateRequestDTO;
import org.springframework.data.domain.Page;

public interface PostService {

    PostResponseDTO create(PostRequestDTO request);

    Page<PostResponseDTO> getAll(String keyword, int page, int size);

    PostResponseDTO getById(Long id);

    PostResponseDTO update(Long id, PostRequestDTO request);

    PostResponseDTO patchUpdate(Long id, PostUpdateRequestDTO request);

    void delete(Long id);
}