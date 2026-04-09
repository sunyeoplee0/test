package com.example.backend.board;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Dto.BoardListRes> list() {
        return boardRepository.findAllByOrderByIdxDesc().stream()
                .map(Dto.BoardListRes::from)
                .toList();
    }

    public Dto.BoardDetailRes detail(Long idx) {
        return Dto.BoardDetailRes.from(boardRepository.findById(idx).orElseThrow());
    }

    public Dto.BoardRegRes register(Dto.BoardRegReq req) {
        return Dto.BoardRegRes.from(boardRepository.save(req.toEntity()));
    }
}
