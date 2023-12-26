package sookook.daybyday.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sookook.daybyday.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {
}
