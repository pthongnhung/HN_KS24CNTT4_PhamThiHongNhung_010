package org.example.it211_hackathon.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.it211_hackathon.dto.PostRequestDTO;
import org.example.it211_hackathon.dto.PostResponseDTO;
import org.example.it211_hackathon.dto.PostUpdateRequestDTO;
import org.example.it211_hackathon.entity.Post;
import org.example.it211_hackathon.exception.ResourceNotFoundException;
import org.example.it211_hackathon.repository.PostRepository;
import org.example.it211_hackathon.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public PostResponseDTO create(PostRequestDTO request) {
        Post post = Post.builder()
                .username(request.getUsername())
                .content(request.getContent())
                .likes(request.getLikes())
                .status(request.getStatus())
                .isDeleted(false)
                .build();

        return toResponse(postRepository.save(post));
    }

    @Override
    public Page<PostResponseDTO> getAll(String keyword, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page-1, size);

        Page<Post> posts;

        if (keyword == null || keyword.isBlank()) {
            posts = postRepository.findByIsDeletedFalse(pageRequest);
        } else {
            posts = postRepository
                    .findByIsDeletedFalseAndUsernameContainingIgnoreCaseOrIsDeletedFalseAndContentContainingIgnoreCase(
                            keyword,
                            keyword,
                            pageRequest
                    );
        }

        return posts.map(this::toResponse);
    }

    @Override
    public PostResponseDTO getById(Long id) {
        Post post = findPost(id);
        return toResponse(post);
    }

    @Override
    public PostResponseDTO update(Long id, PostRequestDTO request) {
        Post post = findPost(id);

        post.setUsername(request.getUsername());
        post.setContent(request.getContent());
        post.setLikes(request.getLikes());
        post.setStatus(request.getStatus());

        return toResponse(postRepository.save(post));
    }

    @Override
    public PostResponseDTO patchUpdate(Long id, PostUpdateRequestDTO request) {
        Post post = findPost(id);

        if (request.getUsername() != null) {
            post.setUsername(request.getUsername());
        }

        if (request.getContent() != null) {
            post.setContent(request.getContent());
        }

        if (request.getLikes() != null) {
            post.setLikes(request.getLikes());
        }

        if (request.getStatus() != null) {
            post.setStatus(request.getStatus());
        }

        return toResponse(postRepository.save(post));
    }

    @Override
    public void delete(Long id) {
        Post post = findPost(id);
        post.setIsDeleted(true);
        postRepository.save(post);
    }

    private Post findPost(Long id) {
        return postRepository.findById(id)
                .filter(post -> !post.getIsDeleted())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bài đăng có id: " + id));
    }

    private PostResponseDTO toResponse(Post post) {
        return PostResponseDTO.builder()
                .id(post.getId())
                .username(post.getUsername())
                .content(post.getContent())
                .likes(post.getLikes())
                .status(post.getStatus())
                .build();
    }
}