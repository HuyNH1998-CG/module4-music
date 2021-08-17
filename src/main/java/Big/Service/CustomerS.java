package Big.Service;

import Big.Model.Category;
import Big.Model.Music;

import java.util.List;

public interface CustomerS {
    List<Music> findAll();
    List<Category> findAllCat();

    Music save(Music music);

    Music findById(int id);

    Music update(int id, Music music);

    void remove(int id);
}
