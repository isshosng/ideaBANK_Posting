package com.posting2.board.Controller;

import com.posting2.board.Dto.BoardDto;
import com.posting2.board.Service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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

    @GetMapping("/post/{id}")
    public String detail(@PathVariable("id") Long id, Model model){
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "board/detail.html";
    }
    /*
    각 게시글을 클릭하면 /post/{id}으로 Get 요청을 합니다. (만일 1번 글을 클릭하면 /post/1로 접속됨)
    BoardController에 detail()을 아래와 같이 구현하며, 요청받았을 때 해당 id의 데이터가 View로 전달되도록 만들어줍니다.
     */

    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "board/edit.html";
    }

    /*
    수정 버튼을 누르면 Put 형식으로 /post/edit/{id}로 서버에게 요청이 가게 됩니다. 서버에서 Put 요청이 오게 되면, 데이터베이스에 변경된 데이터를 저장해야 합니다.
    BoardController 클래스에 update()를 구현하여 구현해보겠습니다.
     */
    @PutMapping("/post/edit/{id}")
    public String update(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/";
    }
    /*
    위 작업을 마치면 HiddenhttpMethodFilter를 Bean으로 등록하여, @PutMapping과 @DeleteMapping이 작동할 수 있도록 해줍니다.
     */
}