package com.algo.apinotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public String create(String title, String text, String code){
        NoteModel note = new NoteModel(title, text, code);
        return noteRepository.createNote(note);
    }

    public NoteModel getNoteById(String id){
        return noteRepository.getNoteById((id));
    }

    public List<NoteModel> getAllNotes(){
        return noteRepository.getAllNotes();
    }

    public NoteModel updateNote(String id, NoteModel note){
        return noteRepository.updateNote(id, note);
    }

    public String deleteNote(String id){
       return  noteRepository.deleteNoteById(id);
    }


}
