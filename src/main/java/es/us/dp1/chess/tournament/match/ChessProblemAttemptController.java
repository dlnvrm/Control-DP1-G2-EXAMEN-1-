package es.us.dp1.chess.tournament.match;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.us.dp1.chess.tournament.exceptions.ResourceNotFoundException;
import es.us.dp1.chess.tournament.user.UserService;

@RestController
@RequestMapping("/api/v1/attempts")
public class ChessProblemAttemptController {

    UserService userService;
    ChessProblemAttemptService matchService;

    @Autowired
    public ChessProblemAttemptController(ChessProblemAttemptService service, UserService userService) {
        this.matchService = service;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<ChessProblemAttempt>> findAll() {
        return new ResponseEntity<>(matchService.getAttempts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChessProblemAttempt> findById(@PathVariable("id") Integer id) {
        ChessProblemAttempt attempt = matchService.getMatchById(id)
            .orElseThrow(() -> new ResourceNotFoundException("ChessProblemAttempt", "id", id));
        return new ResponseEntity<>(attempt, HttpStatus.OK);
    }
}