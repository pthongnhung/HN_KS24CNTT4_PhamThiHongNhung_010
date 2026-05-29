package org.example.it211_hackathon.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;
import org.example.it211_hackathon.entity.PostStatus;

@Data
public class PostUpdateRequestDTO {

    private String username;

    private String content;

    @Min(value = 0, message = "Likes phải lớn hơn hoặc bằng 0")
    private Integer likes;

    private PostStatus status;
}