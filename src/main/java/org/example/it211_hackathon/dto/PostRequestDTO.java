package org.example.it211_hackathon.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.it211_hackathon.entity.PostStatus;

@Data
public class PostRequestDTO {

    @NotBlank(message = "Username không được để trống")
    private String username;

    @NotBlank(message = "Content không được để trống")
    private String content;

    @NotNull(message = "Likes không được để trống")
    @Min(value = 0, message = "Likes phải lớn hơn hoặc bằng 0")
    private Integer likes;

    @NotNull(message = "Status không được để trống")
    private PostStatus status;
}