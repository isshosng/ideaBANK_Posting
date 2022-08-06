package com.posting2.board.Controller;

import com.posting2.board.Dto.BoardDto;
import com.posting2.board.Service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {
    /*
    글쓰기 Form에서 받은 데이터는 '글쓰기' 버튼을 누르면 /post로 Post 요청을 하게 됩니다.
    Entity, Repository, DTO, Service를 구현했으니 BoardController에 Post로 받은 데이터를 데이터베이스에 추가하는 것을 추가해줍니다.
     */
    private BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }


    /*
    게시물의 목록을 가져오는 getBoardList()를 만들었으므로, 가져온 데이터를 통해 View에 전달해야 함
    model.addAttribute("postList", boardDtoList);를 통하여 boardDtoList를 board/list.html에 postList로 전달해줍니다.
     */
    @GetMapping("/")
    public String list(Model model) {
        List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("postList", boardDtoList);
        return "board/list.html";
    }

    @GetMapping("/post")
    public String post() {
        return "board/post.html";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/";
    }
}