package com.example.api1.api.service;

import com.example.api1.api.model.FilePost;
import com.example.api1.api.repository.FileRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.UUID;



@Service
public class FileService {

    private FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository repo){
        fileRepository = repo;
    }



    @Value("${upload.path}")
    private String uploadPath;

    public Path load(String filename) {
        Path up = Path.of(uploadPath);
        return up.resolve(filename);
    }

    public String Store(MultipartFile file) throws IOException {

        File uploadDir = new File(uploadPath);

        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }



        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + FilenameUtils.getExtension(file.getOriginalFilename());

        file.transferTo(new File(uploadPath + "/" + resultFilename));

        FilePost photo = new FilePost();
        photo.setId(uuidFile);
        photo.setUri(resultFilename);
        photo.setFileType(file.getContentType());
        fileRepository.save(photo);

        return resultFilename;
    }

    public Resource loadAsResource(String filename) throws MalformedURLException {

        Path file = load(filename);
        Resource resource = new UrlResource(file.toUri());
        if (resource.exists() || resource.isReadable()) {
            return resource;
        }
        return null;
    }
}


/*package com.attacomsian.uploadfiles.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

 private final Path rootLocation;

 @Autowired
 public FileSystemStorageService(StorageProperties properties) {
 this.rootLocation = Paths.get(properties.getLocation());
 }

 @Override
 @PostConstruct
 public void init() {
 try {
 Files.createDirectories(rootLocation);
 } catch (IOException e) {
 throw new StorageException("Could not initialize storage location", e);
 }
 }

 @Override
 public String store(MultipartFile file) {
 String filename = StringUtils.cleanPath(file.getOriginalFilename());
 try {
 if (file.isEmpty()) {
 throw new StorageException("Failed to store empty file " + filename);
 }
 if (filename.contains("..")) {
 // This is a security check
 throw new StorageException(
 "Cannot store file with relative path outside current directory "
 + filename);
 }
 try (InputStream inputStream = file.getInputStream()) {
 Files.copy(inputStream, this.rootLocation.resolve(filename),
 StandardCopyOption.REPLACE_EXISTING);
 }
 }
 catch (IOException e) {
 throw new StorageException("Failed to store file " + filename, e);
 }

 return filename;
 }

 @Override
 public Stream<Path> loadAll() {
 try {
 return Files.walk(this.rootLocation, 1)
 .filter(path -> !path.equals(this.rootLocation))
 .map(this.rootLocation::relativize);
 }
 catch (IOException e) {
 throw new StorageException("Failed to read stored files", e);
 }

 }

 @Override
 public Path load(String filename) {
 return rootLocation.resolve(filename);
 }

 @Override
 public Resource loadAsResource(String filename) {
 try {
 Path file = load(filename);
 Resource resource = new UrlResource(file.toUri());
 if (resource.exists() || resource.isReadable()) {
 return resource;
 }
 else {
 throw new FileNotFoundException(
 "Could not read file: " + filename);
 }
 }
 catch (MalformedURLException e) {
 throw new FileNotFoundException("Could not read file: " + filename, e);
 }
 }

 @Override
 public void deleteAll() {
 FileSystemUtils.deleteRecursively(rootLocation.toFile());
 }
}*/

