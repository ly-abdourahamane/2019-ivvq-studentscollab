package fr.univtlse3.m2dl.studentscollab.studentscollab.exception;

public class NoteCoursNotFoundException extends Exception {

    public NoteCoursNotFoundException () {

    }

    public NoteCoursNotFoundException (String message) {
        super (message);
    }

    public NoteCoursNotFoundException (Throwable cause) {
        super (cause);
    }

    public NoteCoursNotFoundException (String message, Throwable cause) {
        super (message, cause);
    }
}
