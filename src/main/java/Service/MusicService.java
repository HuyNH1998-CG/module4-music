package Service;

import Model.Category;
import Model.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicService {
    private static List<Music> musicList = new ArrayList<>();
    private static List<Category> categoryList = new ArrayList<>();
    static {
        categoryList.add(new Category("Jazz"));
        categoryList.add(new Category("Rock"));
        categoryList.add(new Category("Pop"));
        categoryList.add(new Category("US"));
        categoryList.add(new Category("JP"));
        categoryList.add(new Category("KR"));
    }
    public List<Music> findAll(){
        return musicList;
    }
    public List<Category> findAllCat(){
        return categoryList;
    }
    public void save(Music music){
        musicList.add(music);
    }

    public Music findById(int id){
        return musicList.get(id);
    }

    public void update(int id, Music music){
        for(Music m : musicList){
            if(m.getId() == id){
                m = music;
                break;
            }
        }
    }

    public void remove(int id){
        for (int i = 0; i < musicList.size(); i++) {
            if (musicList.get(i).getId() == id) {
                musicList.remove(i);
                break;
            }
        }
    }
}
