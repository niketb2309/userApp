package com.web.userlogin.repository;

import com.web.userlogin.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Note,Long> {

    @Query(value= "FROM Note note WHERE note.userName = :userName")
    List<Note> findByUsername(@Param("userName") String userName);

}
