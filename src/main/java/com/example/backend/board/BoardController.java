package com.example.backend.board;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public List<Dto.BoardListRes> list() {
        return boardService.list();
    }

    @GetMapping("/{idx}")
    public Dto.BoardDetailRes detail(@PathVariable Long idx) {
        return boardService.detail(idx);
    }

    @PostMapping("/reg")
    public ResponseEntity<Dto.BoardRegRes> register(@RequestBody Dto.BoardRegReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(boardService.register(req));
    }
}
