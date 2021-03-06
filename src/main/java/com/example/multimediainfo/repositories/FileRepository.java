package com.example.multimediainfo.repositories;

import com.example.multimediainfo.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    @Query("select f from File f where f.hash = ?1")
    File findByHashEquals(String hash);

    @Modifying
    @Query("delete from File f where f.hash = ?1")
    void deleteByHashEquals(String hash);
}
