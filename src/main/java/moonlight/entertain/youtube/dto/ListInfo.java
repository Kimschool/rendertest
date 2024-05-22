package moonlight.entertain.youtube.dto;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "listinfo")
public class ListInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // テーブル番号
    private String tableNumber;

    // 動画URL
    private String videoUrl;

}
