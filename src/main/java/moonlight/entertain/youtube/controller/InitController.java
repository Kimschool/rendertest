package moonlight.entertain.youtube.controller;

import moonlight.entertain.youtube.dto.ListInfo;
import moonlight.entertain.youtube.repo.ListInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @param tableNumber
     * @param videoUrl
     * @return
     */
    @PostMapping("/request")
    public ResponseEntity<Map<String, String>>  request(String tableNumber, String videoUrl) {

        // リクエスト情報を登録
        ListInfo listInfo = new ListInfo();
        listInfo.setTableNumber(tableNumber);
        listInfo.setVideoUrl(videoUrl);
        listInfo.setStatus("0");
        listInfoRepo.save(listInfo);

        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", "/");

        // index画面を再表示
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public String list(Model model) {
        // リクエスト情報を取得
        List<ListInfo> listInfoList = listInfoRepo.findAll();

        // リストを降順に並び替え
        listInfoList.sort((a, b) -> b.getId().compareTo(a.getId()));


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


    /**
     * リクエスト情報を削除する
     */
    @GetMapping("/clear")
    public String clear() {

        // リクエスト情報を削除
        listInfoRepo.deleteAll();

        return "redirect:/list";
    }
    @PostMapping("/changeStatus")
    public ResponseEntity<Map<String, String>> changeStatus(Long id) {
        // リクエスト情報を取得
        ListInfo listInfo = listInfoRepo.findById(id).orElse(null);
        if(listInfo.getStatus().equals("1"))
            listInfo.setStatus("0");
        else
            listInfo.setStatus("1");

        listInfoRepo.save(listInfo);

        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", "/list");

        // list画面を再表示
        return ResponseEntity.ok(response);
    }

}
