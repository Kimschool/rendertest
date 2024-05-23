package moonlight.entertain.youtube.controller;

import moonlight.entertain.youtube.dto.ListInfo;
import moonlight.entertain.youtube.repo.ListInfoRepo;
import moonlight.entertain.youtube.vo.RequestInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class InitController {

    @Autowired
    private ListInfoRepo listInfoRepo;


    /**
     * 初期表示
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(Model model) {
        // request.htmlを表示
        return "index";
    }

    /**
     * リクエスト情報を登録する
     * @param model
     * @param tableNumber
     * @param videoUrl
     * @return
     */
    @PostMapping("/request")
    public String request(Model model, String tableNumber, String videoUrl) {

        // リクエスト情報を登録
        ListInfo listInfo = new ListInfo();
        listInfo.setTableNumber(tableNumber);
        listInfo.setVideoUrl(videoUrl);
        listInfoRepo.save(listInfo);

        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        // リクエスト情報を取得
        List<ListInfo> listInfoList = listInfoRepo.findAll();

        // リクエスト情報を画面に表示
        model.addAttribute("listInfoList", listInfoList);

        // list.htmlを表示
        return "list";
    }

    /**
     * マネージャー画面を表示する
     * @param model
     * @return
     */
    @GetMapping("/manager")
    public String manager(Model model) {

        // リクエスト情報を取得
        List<ListInfo> listInfoList = listInfoRepo.findAll();

        // リクエスト情報を画面に表示
        model.addAttribute("listInfoList", listInfoList);

        // request.htmlを表示
        return "list";
    }



}
