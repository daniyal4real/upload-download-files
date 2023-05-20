package kz.docs.documentupload.service;


import jakarta.transaction.Transactional;
import kz.docs.documentupload.entity.FileData;
import kz.docs.documentupload.repository.StorageRepository;
import kz.docs.documentupload.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StorageService {

    private final StorageRepository storageRepository;

    public String uploadFile(MultipartFile file) throws IOException {
        FileData fileData = storageRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .fileData(FileUtils.compressFile(file.getBytes())).build());

        if (fileData == null) {
            return null;
        }
        return "File was uploaded successfully: " + file.getOriginalFilename();
    }


    public byte[] downloadFile(String fileName) {
        Optional<FileData> dbFileName = storageRepository.findByName(fileName);
        byte[] files = FileUtils.decompressFile(dbFileName.get().getFileData());
        return files;
    }
}
