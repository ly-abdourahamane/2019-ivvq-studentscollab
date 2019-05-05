package fr.univtlse3.m2dl.studentscollab.studentscollab.service;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.*;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.EtudiantRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.EvaluationRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.MatiereRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.NoteCoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitialisationService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private MatiereRepository matiereRepository;

    @Autowired
    private NoteCoursRepository noteCoursRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;

    private Matiere matiere1,matiere2,matiere3;
    private Etudiant abdourahamane, meriem, maxime, alexia;
    private NoteCours noteMeriem, noteMaxime;
    private Evaluation evalMaxime;

    public  void init() {
        initEtudiants();
        initMatieres();
    }

    private void initEvals() {
        evalMaxime = new Evaluation(maxime, noteMeriem, EvalType.LIKE);
        evaluationRepository.save(evalMaxime);
    }

    private void initMatieres() {
        matiere1 = new Matiere("matiere1");
        matiere2 = new Matiere("matiere2");
        matiere3 = new Matiere("matiere3");
        matiereRepository.save(matiere1);
        matiereRepository.save(matiere2);
        matiereRepository.save(matiere3);

        initEvals();
    }

    private void initNotesCours() {
        noteMeriem = new NoteCours("note meriem", "contenu note meriem", meriem);
        noteMaxime = new NoteCours("note maxime", "contenu note maxime", maxime);

        noteCoursRepository.save(noteMeriem);
        noteCoursRepository.save(noteMaxime);
    }

    private void initEtudiants() {
        abdourahamane = new Etudiant("ly", "abdou", "abdourahamane.ly1@gmail.com","123456");
        abdourahamane.setEstValide(true);
        etudiantRepository.save(abdourahamane);

        meriem = new Etudiant("ferouj", "meriem", "meriem@gmail.com","123456");
        etudiantRepository.save(meriem);

        maxime = new Etudiant("rouillon", "maxime", "maxime@gmail.com","123456");
        etudiantRepository.save(maxime);

        alexia = new Etudiant("fernandes", "alexia", "alexia@gmail.com","123456");
        etudiantRepository.save(alexia);

        initNotesCours();
    }

    // repositories
    public EtudiantRepository getEtudiantRepository() {
        return etudiantRepository;
    }
    public void setEtudiantRepository(EtudiantRepository etudiantRepository) { this.etudiantRepository = etudiantRepository; }

    public EvaluationRepository getEvaluationRepository() { return evaluationRepository; }
    public void setEvaluationRepository(EvaluationRepository evaluationRepository) { this.evaluationRepository = evaluationRepository;}

    public NoteCoursRepository getNoteCoursRepository() {
        return noteCoursRepository;
    }
    public void setNoteCoursRepository(NoteCoursRepository noteCoursRepository) { this.noteCoursRepository = noteCoursRepository; }

    public MatiereRepository getMatiereRepository() {return matiereRepository;}
    public void setMatiereRepository(MatiereRepository matiereRepository) {this.matiereRepository = matiereRepository;}

    // etudiants
    public Etudiant getAbdourahamane() { return abdourahamane; }
    public void setAbdourahamane(Etudiant abdourahamane) {
        this.abdourahamane = abdourahamane;
    }

    public Etudiant getMaxime() { return maxime; }
    public void setMaxime(Etudiant maxime) {
        this.maxime = maxime;
    }

    public Etudiant getAlexia() {
        return alexia;
    }
    public void setAlexia(Etudiant alexia) {
        this.alexia = alexia;
    }

    public Etudiant getMeriem() {
        return meriem;
    }
    public void setMeriem(Etudiant meriem) {
        this.meriem = meriem;
    }

    // notes cours
    public NoteCours getNoteMeriem() {return noteMeriem;}
    public void setNoteMeriem(NoteCours noteMeriem) {this.noteMeriem = noteMeriem;}

    public NoteCours getNoteMaxime() {return noteMaxime;}
    public void setNoteMaxime(NoteCours noteMaxime) {this.noteMaxime = noteMaxime;}
}

