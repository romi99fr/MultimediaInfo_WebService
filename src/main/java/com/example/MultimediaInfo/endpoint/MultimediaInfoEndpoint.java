package com.example.MultimediaInfo.endpoint;

import com.example.MultimediaInfo.MultimediaInfoService;
import com.example.MultimediaInfo.models.Description;
import com.example.MultimediaInfo.models.File;
import com.example.MultimediaInfo.models.Keyword;
import com.example.MultimediaInfo.models.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/")
public class MultimediaInfoEndpoint {
    @Autowired
    MultimediaInfoService multimediaInfoService;

    //GET ALL FILES AND CONTENT
    @GetMapping("/files")
    public ResponseEntity<String> getAllFilesContent() {
        StringBuilder content = new StringBuilder();
        List<File> files = multimediaInfoService.findAllFiles();
        for(File file : files) {
            List<Title> fileTitles = multimediaInfoService.findTitlesByHash(file.hash);
            List<Description> fileDescriptions = multimediaInfoService.findDescriptionsByHash(file.hash);
            List<Keyword> fileKeywords = multimediaInfoService.findKeywordsByHash(file.hash);
            content.append("Content for file with hash: ").append(file.hash).append("\n");
            content.append("Titles:");
            for(Title title : fileTitles) {
                content.append(" ").append(title.title).append(" ");
            }
            content.append("\n");
            content.append("Descriptions: ");
            for(Description description : fileDescriptions) {
                content.append(" ").append(description.description).append(" ");
            }
            content.append("\n");
            content.append("Keywords: ");
            for(Keyword keyword : fileKeywords) {
                content.append(" ").append(keyword.keyword).append(" ");
            }
            content.append("\n");
        }
        return new ResponseEntity<>(content.toString(), HttpStatus.OK);
    }

    //GET ALL CONTENT FROM SPECIFIC FILE
    @GetMapping("/files/{hash}")
    public ResponseEntity<String> getFileContent(@PathVariable("hash") String hash) {
        StringBuilder content = new StringBuilder();
        List<Title> fileTitles = multimediaInfoService.findTitlesByHash(hash);
        List<Description> fileDescriptions = multimediaInfoService.findDescriptionsByHash(hash);
        List<Keyword> fileKeywords = multimediaInfoService.findKeywordsByHash(hash);
        content.append("Content for file with hash: ").append(hash).append("\n");
        content.append("Titles:");
        for(Title title : fileTitles) {
            content.append(" ").append(title.title).append(" ");
        }
        content.append("\n");
        content.append("Descriptions: ");
        for(Description description : fileDescriptions) {
            content.append(" ").append(description.description).append(" ");
        }
        content.append("\n");
        content.append("Keywords: ");
        for(Keyword keyword : fileKeywords) {
            content.append(" ").append(keyword.keyword).append(" ");
        }
        content.append("\n");
        return new ResponseEntity<>(content.toString(), HttpStatus.OK);
    }

    //GET CONTENT FROM FILES BY CONTENT TYPE
    @GetMapping("/files/{hash}/titles")
    public ResponseEntity<String> getFileTitles(@PathVariable("hash") String hash) {
        StringBuilder content = new StringBuilder();
        List<Title> fileTitles = multimediaInfoService.findTitlesByHash(hash);
        content.append("Titles for file with hash: ").append(hash).append("\n");
        content.append("Titles:");
        for(Title title : fileTitles) {
            content.append(" ").append(title.title).append(" ");
        }
        content.append("\n");
        return new ResponseEntity<>(content.toString(), HttpStatus.OK);
    }

    @GetMapping("/files/{hash}/descriptions")
    public ResponseEntity<String> getFileDescriptions(@PathVariable("hash") String hash) {
        StringBuilder content = new StringBuilder();
        List<Description> fileDescriptions = multimediaInfoService.findDescriptionsByHash(hash);
        content.append("Descriptions for file with hash: ").append(hash).append("\n");
        content.append("Descriptions: ");
        for(Description description : fileDescriptions) {
            content.append(" ").append(description.description).append(" ");
        }
        content.append("\n");
        return new ResponseEntity<>(content.toString(), HttpStatus.OK);
    }

    @GetMapping("/files/{hash}/keywords")
    public ResponseEntity<String> getFileKeywords(@PathVariable("hash") String hash) {
        StringBuilder content = new StringBuilder();
        List<Keyword> fileKeywords = multimediaInfoService.findKeywordsByHash(hash);
        content.append("Keywords for file with hash: ").append(hash).append("\n");
        content.append("Keywords: ");
        for(Keyword keyword : fileKeywords) {
            content.append(" ").append(keyword.keyword).append(" ");
        }
        content.append("\n");
        return new ResponseEntity<>(content.toString(), HttpStatus.OK);
    }

    //GET CONTENT FROM FILES BY CONTENT ID
    @GetMapping("/files/{hash}/titles/{id}")
    public ResponseEntity<String> getFileTitle(@PathVariable("hash") String hash, @PathVariable("id") String id) {
        List<Title> fileTitles = multimediaInfoService.findTitlesByHash(hash);
        return new ResponseEntity<>(fileTitles.get(Integer.parseInt(id)).title, HttpStatus.OK);
    }

    @GetMapping("/files/{hash}/description/{id}")
    public ResponseEntity<String> getFileDescription(@PathVariable("hash") String hash, @PathVariable("id") String id) {
        List<Description> fileDescriptions = multimediaInfoService.findDescriptionsByHash(hash);
        return new ResponseEntity<>(fileDescriptions.get(Integer.parseInt(id)).description, HttpStatus.OK);
    }

    @GetMapping("/files/{hash}/keyword/{id}")
    public ResponseEntity<String> getFileKeyword(@PathVariable("hash") String hash, @PathVariable("id") String id) {
        List<Keyword> fileKeywords = multimediaInfoService.findKeywordsByHash(hash);
        return new ResponseEntity<>(fileKeywords.get(Integer.parseInt(id)).keyword, HttpStatus.OK);
    }

    //POST FILES AND CONTENT TO FILES
    @PostMapping("/files")
    public ResponseEntity<String> addFile(@RequestBody String hash) {
        multimediaInfoService.postFile(hash);
        return new ResponseEntity<>("File created", HttpStatus.CREATED);
    }

    @PostMapping("/files/{hash}/title")
    public ResponseEntity<String> addFileTitle(@PathVariable("hash") String hash, @RequestBody String title) {
        if(title.isBlank()) {
            return new ResponseEntity<>("Empty body content", HttpStatus.BAD_REQUEST);
        }
        multimediaInfoService.postFileTitle(hash, title);
        return new ResponseEntity<>("Title added", HttpStatus.CREATED);
    }

    @PostMapping("/files/{hash}/description")
    public ResponseEntity<String> addFileDescription(@PathVariable("hash") String hash, @RequestBody String description) {
        if(description.isBlank()) {
            return new ResponseEntity<>("Empty body content", HttpStatus.BAD_REQUEST);
        }
        multimediaInfoService.postFileDescription(hash, description);
        return new ResponseEntity<>("Description added", HttpStatus.CREATED);
    }

    @PostMapping("/files/{hash}/keyword")
    public ResponseEntity<String> addFileKeyword(@PathVariable("hash") String hash, @RequestBody String keyword) {
        if(keyword.isBlank()) {
            return new ResponseEntity<>("Empty body content", HttpStatus.BAD_REQUEST);
        }
        multimediaInfoService.postFileKeyword(hash, keyword);
        return new ResponseEntity<>("Keyword added", HttpStatus.CREATED);
    }

    //SEARCH BY CONTENT
    @GetMapping("/files/search")
    public ResponseEntity<String> searchByAnytype(@RequestBody String anytype) {
        StringBuilder content = new StringBuilder();
        List<Title> foundTitles = multimediaInfoService.findTitlesByContent(anytype);
        List<Description> foundDescriptions = multimediaInfoService.findDescriptionsByContent(anytype);
        List<Keyword> foundKeywords = multimediaInfoService.findKeywordsByContent(anytype);
        content.append("Found files with string ").append(anytype).append(" as title:");
        for(Title title : foundTitles) {
            content.append(" ").append(title.title).append(" ");
        }
        content.append("\n");
        content.append("Found files with string ").append(anytype).append(" as description:");
        for(Description description : foundDescriptions) {
            content.append(" ").append(description.description).append(" ");
        }
        content.append("\n");
        content.append("Found files with string ").append(anytype).append(" as keyword:");
        for(Keyword keyword : foundKeywords) {
            content.append(" ").append(keyword.keyword).append(" ");
        }
        content.append("\n");
        return new ResponseEntity<>(content.toString(), HttpStatus.OK);
    }

    @GetMapping("/files/search/title")
    public ResponseEntity<String> searchByTitle(@RequestBody String title) {
        StringBuilder content = new StringBuilder();
        List<Title> foundTitles = multimediaInfoService.findTitlesByContent(title);
        content.append("Found files with string ").append(title).append(" as title:");
        for(Title title1 : foundTitles) {
            content.append(" ").append(title1.title).append(" ");
        }
        content.append("\n");
        return new ResponseEntity<>(content.toString(), HttpStatus.OK);
    }

    @GetMapping("/files/search/description")
    public ResponseEntity<String> searchByDescription(@RequestBody String description) {
        StringBuilder content = new StringBuilder();
        List<Description> foundDescriptions = multimediaInfoService.findDescriptionsByContent(description);
        content.append("Found files with string ").append(description).append(" as description:");
        for(Description description1 : foundDescriptions) {
            content.append(" ").append(description1.description).append(" ");
        }
        content.append("\n");
        return new ResponseEntity<>(content.toString(), HttpStatus.OK);
    }

    @GetMapping("/files/search/keyword")
    public ResponseEntity<String> searchByKeyword(@RequestBody String keyword) {
        StringBuilder content = new StringBuilder();
        List<Keyword> foundKeywords = multimediaInfoService.findKeywordsByContent(keyword);
        content.append("Found files with string ").append(keyword).append(" as keyword:");
        for(Keyword keyword1 : foundKeywords) {
            content.append(" ").append(keyword1.keyword).append(" ");
        }
        content.append("\n");
        return new ResponseEntity<>(content.toString(), HttpStatus.OK);
    }
}
