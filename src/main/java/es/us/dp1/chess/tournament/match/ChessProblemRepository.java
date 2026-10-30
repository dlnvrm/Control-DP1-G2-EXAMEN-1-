package es.us.dp1.chess.tournament.match;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ChessProblemRepository extends CrudRepository<ChessProblem, Integer> {

    @Query("SELECT p FROM ChessProblem p WHERE p.difficultyLevel >= ?1 AND SIZE(p.board.pieces) < ?2")
    List<ChessProblem> findByDifficultyAndNumPieces(Integer difficultyLevel, Integer numPieces);

    List<ChessProblem> findAll();
    Optional<ChessProblem> findById(Integer i);
    void delete(ChessProblem piece);
}