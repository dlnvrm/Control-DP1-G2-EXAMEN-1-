package es.us.dp1.chess.tournament.match;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChessProblemService {

    ChessProblemRepository repository;

    @Autowired
    public ChessProblemService(Optional<ChessProblemRepository> repository) {
        this.repository = repository.orElse(null);
    }

    @Transactional(readOnly = true)
    public List<ChessProblem> getProblemsByDifficultyAndNumPieces(Integer difficulty, Integer numPieces) {
        return repository.findByDifficultyAndNumPieces(difficulty, numPieces);
    }

    @Transactional(readOnly = true)
    public List<ChessProblem> getAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public ChessProblem getById(Integer i) {
        return repository.findById(i).orElse(null);
    }

    @Transactional
    public void delete(ChessProblem piece) {
        repository.delete(piece);
    }
}