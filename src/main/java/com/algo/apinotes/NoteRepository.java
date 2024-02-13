package com.algo.apinotes;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.IDynamoDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoteRepository {

    //This is the bean we configured
    final private DynamoDBMapper dynamoDBMapper;

    @Autowired
    public NoteRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public String createNote(NoteModel note){
        dynamoDBMapper.save(note);
        return note.getId();
    }

    public NoteModel getNoteById(String id){
        return dynamoDBMapper.load(NoteModel.class, id);
    }

    public List<NoteModel> getAllNotes(){
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return dynamoDBMapper.scan(NoteModel.class, scanExpression);

    }

    public NoteModel updateNote(String id, NoteModel note){
        NoteModel loadedNote = dynamoDBMapper.load(NoteModel.class, id);
        loadedNote.setTitle(note.getTitle());
        loadedNote.setText(note.getText());
        loadedNote.setCode(note.getCode());
        dynamoDBMapper.save(loadedNote);
        return dynamoDBMapper.load(NoteModel.class, id);
    }

    public  String deleteNoteById(String id){
        NoteModel loadedNote = dynamoDBMapper.load(NoteModel.class, id);
        dynamoDBMapper.delete(loadedNote);
        return  loadedNote.getId() + " has been deleted";
    }



}
