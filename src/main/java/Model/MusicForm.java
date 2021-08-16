package Model;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class MusicForm {
    private int id;
    private String name;
    private String singer;
    private List<String> type;
    private MultipartFile link;

    public MusicForm() {
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public MusicForm(int id, String name, String singer, List<String> type, MultipartFile link) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.type = type;
        this.link = link;
    }

    public MusicForm(int id, String name, List<String> type, MultipartFile link) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public MultipartFile getLink() {
        return link;
    }

    public void setLink(MultipartFile link) {
        this.link = link;
    }
}
