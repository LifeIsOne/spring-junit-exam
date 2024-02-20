package shop.mtcoding.blog.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@Import(BoardRepository.class)  // 내가 만든
@DataJpaTest // DB 관련 객체들이 IoC에 뜬다.
public class BoardRepositoryTest {

    @Autowired  // Test에서 DI하는 코드
    private BoardRepository boardRepository;

    @Test
    public void selectOne_test() {
        // given
        int id = 1;

        // when
        Board board = boardRepository.selectOne(id);
        // then (상태 검사)
        // System.out.println(board);
        Assertions.assertThat(board.getTitle()).isEqualTo("제목1");
        Assertions.assertThat(board.getContent()).isEqualTo("내용1");
        Assertions.assertThat(board.getAuthor()).isEqualTo("장보고");
    }
    @Test
    public void selectAll_test() {
        // given
        int id = 1;

        // when
        List<Board> boardList = boardRepository.selectALL(id);
        // then (상태 검사)
        // System.out.println(board);
        Assertions.assertThat(boardList.get(0).getTitle()).isEqualTo("제목1");
        Assertions.assertThat(boardList.get(0).getContent()).isEqualTo("내용1");
        Assertions.assertThat(boardList.get(0).getAuthor()).isEqualTo("장보고");
        Assertions.assertThat(boardList.size()).isEqualTo(8);   // boardList의 크기가 8인지 검증
    }

    @Rollback(value = false)
    @Test
    public void insert_test(){  // Test 메서드는 파라미터가 없다.. return도 없다.
        // given
        String title = "제목10";
        String content = "내용10";
        String author = "이순신";

        // when
        boardRepository.insert(title, content, author);

        // then -> 눈으로 확인 (쿼리)
    } // Rollback (자동)
}