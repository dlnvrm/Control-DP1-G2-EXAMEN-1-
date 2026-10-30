package es.us.dp1.chess.tournament.match;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.us.dp1.chess.tournament.exceptions.UnfeasibleAttemptException;

@Service
public class ChessProblemAttemptService {

    ChessProblemAttemptRepository repo;    

    @Autowired
    public ChessProblemAttemptService(ChessProblemAttemptRepository repo) {
        this.repo = repo;        
    }

    @Transactional(readOnly = true)
    public Optional<ChessProblemAttempt> getMatchById(Integer id) {
        return repo.findById(id);
    }

    @Transactional(rollbackFor = UnfeasibleAttemptException.class)
    public ChessProblemAttempt save(ChessProblemAttempt m) throws UnfeasibleAttemptException {
        List<ChessProblemAttempt> ongoing = repo.findOngoingAttemptsByUserAndProblem(m.getPlayer(), m.getProblem());
        for (ChessProblemAttempt other : ongoing) {
            if (!Objects.equals(other.getId(), m.getId())) {
                throw new UnfeasibleAttemptException("There is another ongoing attempt");
            }
        }
        if (m.getFinish() != null && m.getStart() != null && !m.getStart().isBefore(m.getFinish())) {
            throw new UnfeasibleAttemptException("The start must be before the finish");
        }
        return repo.save(m);
    }

    @Transactional(readOnly = true)
    public List<ChessProblemAttempt> getAttempts() {
        return repo.findAll();
    }    

}
