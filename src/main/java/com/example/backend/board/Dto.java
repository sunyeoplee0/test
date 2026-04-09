package com.example.backend.board;

import lombok.Builder;

public class Dto {

    @Builder
    public record BoardListRes(Long idx, String title, String content) {
        public static BoardListRes from(Board b) {
            return BoardListRes.builder()
                    .idx(b.getIdx())
                    .title(b.getTitle())
                    .content(b.getContent())
                    .build();
        }
    }

    @Builder
    public record BoardDetailRes(Long idx, String title, String content) {
        public static BoardDetailRes from(Board b) {
            return BoardDetailRes.builder()
                    .idx(b.getIdx())
                    .title(b.getTitle())
                    .content(b.getContent())
                    .build();
        }
    }

    @Builder
    public record BoardRegReq(String title, String content) {
        public Board toEntity() {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .build();
        }
    }

    @Builder
    public record BoardRegRes(String status, String message, Long idx) {
        public static BoardRegRes from(Board b) {
            return BoardRegRes.builder()
                    .status("success")
                    .message("게시글이 성공적으로 등록되었습니다.")
                    .idx(b.getIdx())
                    .build();
        }
    }
}
