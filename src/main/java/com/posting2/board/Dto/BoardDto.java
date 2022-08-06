package com.posting2.board.Dto;

import com.posting2.board.domain.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

/*
Controller와 Service 사이에서 데이터를 주고 받는 DTo(Data Access Object)를 구현합니다.

Dto 패키지를 만들고, 그 안에 BoardDto 클래스를 만들어줍니다.
아래 코드의 toEntity()는 DTO에서 필요한 부분을 빌더 패턴을 통해 Entity로 만드는 일을 합니다.
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String author;
    private String content;
    private String title;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Board toEntity() {
        Board build = Board.builder()
                .id(id)
                .author(author)
                .title(title)
                .content(content)
                .build();
        return build;
    }

    @Builder
    public BoardDto(Long id, String author, String title,
                    String content, LocalDateTime createdDate,
                    LocalDateTime modifiedDate){
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
