package kz.docs.documentupload.repository;


import kz.docs.documentupload.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<FileData, Integer> {

    Optional<FileData> findByName(String name);
}
