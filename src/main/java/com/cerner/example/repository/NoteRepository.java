package com.cerner.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cerner.example.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}
