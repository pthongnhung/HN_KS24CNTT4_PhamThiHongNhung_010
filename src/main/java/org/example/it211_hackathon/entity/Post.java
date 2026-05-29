package org.example.it211_hackathon.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String content;

    private Integer likes;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    private Boolean isDeleted;
}