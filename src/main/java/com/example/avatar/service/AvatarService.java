package com.example.avatar.service;


import com.example.avatar.dto.AvatarDto;
import com.example.avatar.model.Avatar;
import com.example.avatar.model.Student;
import com.example.avatar.repository.AvatarRepository;
import com.example.avatar.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;
    @Value("${path.to.avatars.folder}")
    private Path pathToAvatars;

    public AvatarService(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }
    public  Long save(Long studentId, MultipartFile multipartFile) throws IOException {
        String absolutePath =saveToDisk(studentId, multipartFile);
        Avatar avatar=saveToDb(studentId,multipartFile,absolutePath);
        return  avatar.getId();
    }

    private   String saveToDisk(Long studentId, MultipartFile multipartFile) throws IOException {
        Files.createDirectories(pathToAvatars);
        String originalFilename = multipartFile.getOriginalFilename();
        int dotIndex = originalFilename.lastIndexOf(".");
        String extension = originalFilename.substring(dotIndex);
        String fileName = studentId + extension;
        String absolutePath = pathToAvatars.toAbsolutePath() + "/" + fileName;
        FileOutputStream fos = new FileOutputStream(absolutePath);
        multipartFile.getInputStream().transferTo(fos);
        fos.close();
        return absolutePath;
    }
    private  Avatar saveToDb(Long studentId, MultipartFile multipartFile,String absolutePath)
            throws  IOException {
        Student studentReference =studentRepository.getReferenceById(studentId);
        Avatar avatar=avatarRepository.findByStudent(studentReference)
                .orElse(new Avatar());

        avatar.setStudent(studentReference);
        avatar.setFilePath(absolutePath);
        avatar.setMediaType(multipartFile.getContentType());
        avatar.setFileSize(multipartFile.getSize());
        avatar.setData(multipartFile.getBytes());
        avatarRepository.save(avatar);
        return avatar;
    }
    public List<AvatarDto> getPage(int pageNum){
        PageRequest pageRequest = PageRequest.of(pageNum, 3);
        List<Avatar> avatars = avatarRepository.findAll(pageRequest).getContent();
        return avatars.stream().map(AvatarDto::fromEntity).collect(Collectors.toList());
    }

    public Avatar getById(Long id) {
        return avatarRepository.findById(id).orElseThrow();
    }
}


