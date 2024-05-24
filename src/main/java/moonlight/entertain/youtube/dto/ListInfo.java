package moonlight.entertain.youtube.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

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

    // ステータス
    // default値："0"
    @Column(columnDefinition = "varchar(1) default '0'")
    private String status;

}
