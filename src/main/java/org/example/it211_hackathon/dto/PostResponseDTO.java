package org.example.it211_hackathon.dto;

import lombok.Builder;
import lombok.Data;
import org.example.it211_hackathon.entity.PostStatus;

@Data
@Builder
public class PostResponseDTO {

    private Long id;
    private String username;
    private String content;
    private Integer likes;
    private PostStatus status;
}