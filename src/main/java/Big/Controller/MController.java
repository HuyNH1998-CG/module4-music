package Big.Controller;

import Big.Model.Category;
import Big.Model.Music;
import Big.Model.MusicForm;
import Big.Service.CustomerS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;

@Controller
public class MController {
    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    private CustomerS customerS;

    @GetMapping("/home")
    public ModelAndView index() {
        List<Music> list = customerS.findAll();
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("musics", list);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(@ModelAttribute("musicForm") MusicForm form) {
        List<Category> categoryList = customerS.findAllCat();
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("categories", categoryList);
        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView showEditForm(@ModelAttribute("musicForm") MusicForm form, @RequestParam int id) {
        Music music = customerS.findById(id);
        MusicForm formEdit = new MusicForm();
        formEdit.setId(music.getId());
        formEdit.setName(music.getName());
        formEdit.setSinger(music.getSinger());
        formEdit.setType(music.getType());
        List<Category> categoryList = customerS.findAllCat();
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("categories", categoryList);
        modelAndView.addObject("musicForm", formEdit);
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveMusic(@ModelAttribute("musicForm") MusicForm form) {
        String fileName = processFile(form);
        Music music = new Music(form.getId(), form.getName(), form.getSinger(), form.getType(), fileName);
        customerS.save(music);
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        modelAndView.addObject("message", "Create success");
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam int id){
        customerS.remove(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        modelAndView.addObject("message", "Create success");
        return modelAndView;
    }

    private String processFile(MusicForm form) {
        MultipartFile multipartFile = form.getLink();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(form.getLink().getBytes(), new File(fileUpload, fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
