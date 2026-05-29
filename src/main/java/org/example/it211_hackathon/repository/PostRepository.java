package org.example.it211_hackathon.repository;

import org.example.it211_hackathon.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByIsDeletedFalse(Pageable pageable);

    Page<Post> findByIsDeletedFalseAndUsernameContainingIgnoreCaseOrIsDeletedFalseAndContentContainingIgnoreCase(
            String username,
            String content,
            Pageable pageable
    );
}