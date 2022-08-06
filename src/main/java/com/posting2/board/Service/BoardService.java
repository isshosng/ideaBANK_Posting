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

    /*
    게시글을 클릭하면 게시물의 내용이 화면에 출력되어야 합니다.
    그러려면 게시글의 id를 받아 해당 게시글의 데이터만 가져와 화면에 뿌려주어야 합니다.
    이를 getPost()를 구현하여 해결합니다.
     */
    @Transactional
    public BoardDto getPost(Long id){
        Board board = boardRepository.findById(id).get();

        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .author(board.getAuthor())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .build();
        return boardDto;
    }

    @Transactional
    public void deletePost(Long id){
        boardRepository.deleteById(id);
    }
    /*
    글을 조회하는 페이지에서 '삭제' 버튼을 누르면, /post/{id}으로 delete 요청을 합니다. (만약 1번 글에서 '삭제' 버튼을 클릭하면 /post/1로 접속됩니다.)
    id 값을 사용하여, 해당 글을 데이터베이스에서 삭제하는 것을 구현. BoardController 클래스에 delete()를 추가해야함
     */
}
