package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public void update(String title, String content, int id){
        Query query = em.createNativeQuery("UPDATE board_tb " +
                "SET title = ?, content =? WHERE id = ?");

        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, id);

        query.executeUpdate();
    }

    public void delete(int id){
        Query query = em.createNativeQuery("DELETE FROM board_tb " +
                "WHERE id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }

    public List<Board> selectALL (int id){
        Query query = em.createNativeQuery("SELECT * FROM board_tb ", Board.class);

        List<Board> boardList =  query.getResultList();

        return boardList;
    }

    public Board selectOne(int id){
        Query query = em.createNativeQuery("SELECT * FROM board_tb " +
                "WHERE id = ?", Board.class);
        query.setParameter(1, id);

        try {
            Board board = (Board) query.getSingleResult();
            return board;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public void insert(String title, String content, String author){
        Query query = em.createNativeQuery("INSERT INTO board_tb(title, content, author) " +
                "VALUES (?, ?, ?)");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, author);

        query.executeUpdate();
    }
}