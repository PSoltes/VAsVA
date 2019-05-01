package vava.soltesvasko.lezeckastena;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vava.soltesvasko.lezeckastena.Data.ClimberRepository;
import vava.soltesvasko.lezeckastena.Data.ProblemRepository;

@Component
public class DemoData {


        @Autowired
        private ProblemRepository repo;
        @Autowired
        private ClimberRepository cRepo;

       /* @EventListener
        public void appReady(ApplicationReadyEvent event) {
                Climber Palo = new Climber("Pavol Šoltés", "soltes.pavol@gmail.com", "1234", "Som ciciak idem domov cya", 21, 'M', "soltes.pavol@gmail.com", "6a", "PALINO BOOOIII", "Boulder4lyfe" );
                cRepo.save(Palo);
                List<Problem> problemList = new ArrayList<Problem>();
                problemList.add(new Problem("Stena xx", "6-", "A2", 2.5, "stena"));
                problemList.add(new Problem("Booooooulder xx", "6a", "C3", 35.4, "boulder"));
                problemList.add(new Problem("Stena xx2", "6-", "A2", 2.5, "stena"));
                problemList.add(new Problem("Booooooulder xx1", "6a", "C3", 35.4, "boulder"));
                List<ClimberProblem> cpList = new ArrayList<ClimberProblem>();
                for(Problem p : problemList)
                {
                        repo.save(p);
                        ClimberProblem temp = new ClimberProblem(Palo, p, 0, false);
                       cpList.add(temp);
                       p.setMyClimbers(new ArrayList<ClimberProblem>(Arrays.asList(temp)));
                       repo.save(p);
                }
                Palo.setMyProblems(cpList);
                cRepo.save(Palo);



        }*/
}
