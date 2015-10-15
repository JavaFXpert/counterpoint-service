package com.culturedear.counterpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@SpringBootApplication
public class CounterpointService {

    public static void main(String[] args) {
        SpringApplication.run(CounterpointService.class, args);
    }
}


@RestController
@RequestMapping("/counterpoint")
class XmlRestController {

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    ResponseEntity<Object> write(@RequestBody CounterpointModel counterpointModel) {

        // prepare response XML out
        CounterpointGenerator cg = new CounterpointGenerator();
        cg.fillRhyPat();
        int[] cf = counterpointModel.getMainMelody();
        cg.setCantusFirmus(cf);
        int[] vbs = counterpointModel.getPartsInitialNotes();

        CounterpointSolution counterpointSolution = cg.anySpecies(counterpointModel.getScaleMode(),
                vbs, cf.length, counterpointModel.getCounterpointSpecies());

        ScorePartwise scorePartwise = null;

        try {
            scorePartwise = counterpointSolution.toScorePartwise();
        } catch (NullPointerException ex) {
            scorePartwise = null;
        }

        return Optional.ofNullable(scorePartwise)
                .map(cm -> new ResponseEntity<>((Object)cm, HttpStatus.OK))
                .orElse(new ResponseEntity<>("Could not compute the counterpoint.",HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
