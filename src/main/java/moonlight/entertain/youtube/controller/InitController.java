package moonlight.entertain.youtube.controller;

import moonlight.entertain.youtube.vo.RequestInfoVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class InitController {

    List<RequestInfoVo> requestInfoList = new ArrayList<>();

    /**
     * 初期表示
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(Model model) {
        // request.htmlを表示
        return "request";
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

        // リクエスト情報をリストに追加
        RequestInfoVo requestInfoVo = new RequestInfoVo();
        requestInfoVo.setTableNumber(tableNumber);
        requestInfoVo.setVideoUrl(videoUrl);

        requestInfoList.add(requestInfoVo);

        // リクエスト情報を画面に表示
        model.addAttribute("requestInfoList", requestInfoList);

        // request.htmlを表示
        return "list";
    }

    @GetMapping("/manager")
    public String manager(Model model) {

        // リクエスト情報を画面に表示
        model.addAttribute("requestInfoList", requestInfoList);

        // request.htmlを表示
        return "list";
    }



}
