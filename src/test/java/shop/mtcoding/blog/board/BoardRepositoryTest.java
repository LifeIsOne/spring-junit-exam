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
    public void upadete_test(){
        int id = 1;

        boardRepository.update("아자아자","화이팅",id);

        List<Board> boardList = boardRepository.selectALL(id);
        Assertions.assertThat(boardList.get(0).getTitle()).isEqualTo("아자아자");
        Assertions.assertThat(boardList.get(0).getContent()).isEqualTo("화이팅");
        Assertions.assertThat(boardList.get(0).getAuthor()).isEqualTo("홍길동");

        System.out.println(boardList);
    }

    @Test
    public void delete_test() {
        int id = 1;

        boardRepository.delete(id);

        List<Board> boardList = boardRepository.selectALL(id);
        //  id 1번을 DELETE, 0번지가 "제목 2"이다
        Assertions.assertThat(boardList.get(0).getTitle()).isEqualTo("제목2");
        Assertions.assertThat(boardList.get(0).getContent()).isEqualTo("내용2");
        Assertions.assertThat(boardList.get(0).getAuthor()).isEqualTo("홍길동");

        System.out.println(boardList);
    }

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
        Assertions.assertThat(board.getAuthor()).isEqualTo("홍길동");
    }
    @Test
    public void selectAll_test() {
        // given
        int id = 1;

        // when
        // then (상태 검사)
        // System.out.println(board);
        List<Board> boardList = boardRepository.selectALL(id);
        Assertions.assertThat(boardList.get(0).getTitle()).isEqualTo("제목1");
        Assertions.assertThat(boardList.get(0).getContent()).isEqualTo("내용1");
        Assertions.assertThat(boardList.get(0).getAuthor()).isEqualTo("홍길동");
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