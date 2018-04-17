package com.cerner.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cerner.example.exception.ResourceNotFoundException;
import com.cerner.example.model.Note;
import com.cerner.example.repository.NoteRepository;

@RestController
public class NoteController {

	@Autowired
	private NoteRepository noteRepository;
	
	@RequestMapping(value = "/notes", method = RequestMethod.GET)
	public List<Note> getAllNotes(){
		return noteRepository.findAll();
	}
	
	@RequestMapping(value = "/notes", method = RequestMethod.POST)
	public Note createNote(@Valid @RequestBody Note note) {
		return noteRepository.save(note);
	}
	
	@RequestMapping(value = "/notes/{id}")
	public Note getNoteById(@PathVariable(value = "id") Long noteId) {
		return noteRepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
	}
	
	@RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT)
	public Note updateNote(@PathVariable(value = "id") Long noteId, @Valid @RequestBody Note newNote) {
		Note note = noteRepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
		
		note.setTitle(newNote.getTitle());
		note.setContent(newNote.getContent());
		
		Note updatedNote = noteRepository.save(note);
		
		return updatedNote;
	}
	
	@RequestMapping(value = "/notes/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId){
		Note note = noteRepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
		
		noteRepository.delete(note);
		
		return ResponseEntity.ok().build();
	}
}
