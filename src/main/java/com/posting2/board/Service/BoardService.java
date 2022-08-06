package com.posting2.board.Service;

import com.posting2.board.Dto.BoardDto;
import com.posting2.board.domain.Repository.BoardRepository;
import com.posting2.board.domain.entity.Board;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/*
BoardRepository를 사용하여 Service를 구현합니다.
글쓰기 Form에서 내용을 입력한 뒤, '글쓰기' 버튼을 누르면 Post 형식으로 요청이 오고,
BoardService의 savePost()를 실행하게 됩니다.
service 패키지를 만들고, 그 안에 BoardService 클래스를 만듭니다.
 */
@Service
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }



    @Transactional
    public Long savePost(BoardDto boardDto){
        return boardRepository.save(boardDto.toEntity()).getId();
    }
    /*
    Service에서 게시물의 목록을 가져오는 getBoardList()를 구현합니다.
    Repository에서 모든 데이터를 조회하여, BoardDto List에 데이터를 넣어 반환합니다.
    BoardService 클래스에 아래와 같이 getBoardList()를 추가합니다.
     */
    @Transactional
    public List<BoardDto> getBoardList(){
        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(Board board : boardList){
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .author(board.getAuthor())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createdDate(board.getCreatedDate())
                    .build();
            boardDtoList.add(boardDto);

        }
        return boardDtoList;
    } // 게시물의 목록을 가져오는 getBoardList()를 만들었음
}
