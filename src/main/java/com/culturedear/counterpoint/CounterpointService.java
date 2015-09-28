package com.culturedear.counterpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class CounterpointService {

  public static void main(String[] args) {
    SpringApplication.run(CounterpointService.class, args);
  }
}

class IntWrapper {

  private int[] ints;

  public IntWrapper(int... ints) {
      this.ints = ints;
  }

  public IntWrapper() {
  }

  public int[] getInts() {
      return ints;
  }
}

@RestController
@RequestMapping("/counterpoint")
class XmlRestController {
  @RequestMapping(method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_XML_VALUE
  )
            
  ScorePartwise write(@RequestBody CounterpointModel counterpointModel) throws Exception {

    // prepare response XML out
    CounterpointGenerator cg = new CounterpointGenerator();
    cg.fillRhyPat();
    int[] cf = counterpointModel.getMainMelody();
    cg.setCantusFirmus(cf);
    int[] vbs = counterpointModel.getPartsInitialNotes();

    CounterpointSolution counterpointSolution = new CounterpointSolution();
    counterpointSolution = cg.anySpecies(counterpointModel.getScaleMode(),
            vbs, cf.length, counterpointModel.getCounterpointSpecies());

    ScorePartwise scorePartwise = counterpointSolution.toScorePartwise();

    return scorePartwise;
  }
}
