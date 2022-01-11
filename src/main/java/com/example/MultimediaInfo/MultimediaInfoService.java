package com.example.MultimediaInfo;

import com.example.MultimediaInfo.models.Description;
import com.example.MultimediaInfo.models.File;
import com.example.MultimediaInfo.models.Keyword;
import com.example.MultimediaInfo.models.Title;
import com.example.MultimediaInfo.repositories.DescriptionRepository;
import com.example.MultimediaInfo.repositories.FileRepository;
import com.example.MultimediaInfo.repositories.KeywordRepository;
import com.example.MultimediaInfo.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MultimediaInfoService {
    @Autowired
    FileRepository filesRepository;
    @Autowired
    TitleRepository titlesRepository;
    @Autowired
    DescriptionRepository descriptionRepository;
    @Autowired
    KeywordRepository keywordRepository;

    //File methods
    public List<File> findAllFiles() {
        return filesRepository.findAll();
    }

    public File findFileByHash(String hash) {
        return filesRepository.findByHashEquals(hash);
    }

    public void postFile(String hash) {
        File file = this.findFileByHash(hash);
        if(file == null) {
            File newFile = new File(hash);
            filesRepository.save(newFile);
        }
    }

    public void deleteAllFiles() {
        titlesRepository.deleteAll();
        descriptionRepository.deleteAll();
        keywordRepository.deleteAll();
        filesRepository.deleteAll();
    }

    public void deleteFileByHash(String hash) {
        titlesRepository.deleteByHashEquals(hash);
        descriptionRepository.deleteByHashEquals(hash);
        keywordRepository.deleteByHashEquals(hash);
        filesRepository.deleteByHashEquals(hash);
    }

    //Title methods
    public List<Title> findAllTitles() {
        return titlesRepository.findAll();
    }

    public List<Title> findTitlesByHash(String hash) {
        return titlesRepository.findByHashEquals(hash);
    }

    public List<Title> findTitlesByContent(String title) {
        return titlesRepository.findByContent(title);
    }

    public void postFileTitle(String hash, String title) {
        File file = this.findFileByHash(hash);
        if(file.getHash() != null && !Objects.equals(file.getHash(), "")) {
            Title newTitle = new Title(hash, title);
            titlesRepository.save(newTitle);
        }
    }

    public void deleteAllTitles() {
        titlesRepository.deleteAll();
    }

    public void deleteTitlesByHash(String hash) {
        titlesRepository.deleteByHashEquals(hash);
    }

    public void deleteTitlesByContent(String title) {
        titlesRepository.deleteByContent(title);
    }

    //Description methods
    public List<Description> findAllDescriptions() {
        return descriptionRepository.findAll();
    }

    public List<Description> findDescriptionsByHash(String hash) {
        return descriptionRepository.findByHashEquals(hash);
    }

    public List<Description> findDescriptionsByContent(String description) {
        return descriptionRepository.findByContent(description);
    }

    public void postFileDescription(String hash, String description) {
        File file = this.findFileByHash(hash);
        if(file.getHash() != null && !Objects.equals(file.getHash(), "")) {
            Description newDescription = new Description(hash, description);
            descriptionRepository.save(newDescription);
        }
    }

    public void deleteAllDescriptions() {
        descriptionRepository.deleteAll();
    }

    public void deleteDescriptionsByHash(String hash) {
        descriptionRepository.deleteByHashEquals(hash);
    }

    public void deleteDescriptionsByContent(String description) {
        descriptionRepository.deleteByContent(description);
    }

    //Keyword methods
    public List<Keyword> findAllKeywords() {
        return keywordRepository.findAll();
    }

    public List<Keyword> findKeywordsByHash(String hash) {
        return keywordRepository.findByHashEquals(hash);
    }

    public List<Keyword> findKeywordsByContent(String keyword) {
        return keywordRepository.findByContent(keyword);
    }

    public void postFileKeyword(String hash, String keyword) {
        File file = this.findFileByHash(hash);
        if(file != null) {
            Keyword newKeyword = new Keyword(hash, keyword);
            keywordRepository.save(newKeyword);
        }
    }

    public void deleteAllKeywords() {
        keywordRepository.deleteAll();
    }

    public void deleteKeywordsByHash(String hash) {
        keywordRepository.deleteByHashEquals(hash);
    }

    public void deleteKeywordsByContent(String keyword) {
        keywordRepository.deleteByContent(keyword);
    }
}