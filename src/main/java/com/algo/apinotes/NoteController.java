package com.algo.apinotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController()
@RequestMapping("/note/v1")
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createNote(@RequestBody NoteModel request){
        String noteId = noteService.create(request.getTitle(), request.getText(), request.getCode());
        return ResponseEntity.ok("Note created successfully" + noteId);
    }
    @GetMapping("/{id}")
    public ResponseEntity<NoteModel> getNoteById(@PathVariable String id){
        NoteModel note = noteService.getNoteById(id);
        if(note != null){
            return ResponseEntity.ok(note);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("update/{id}")
    public ResponseEntity<NoteModel> updateNote(@PathVariable String id, @RequestBody NoteModel request) {
        NoteModel updateNote = noteService.updateNote(id, new NoteModel(request.getTitle(), request.getText(), request.getCode()));
        if(updateNote != null){
            return ResponseEntity.ok(updateNote);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable String id){
        String result = noteService.deleteNote(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all-notes")
    public ResponseEntity<List<NoteModel>> getAllNotes(){
         List<NoteModel> notes = noteService.getAllNotes();
         return ResponseEntity.ok(notes);
    }



}
