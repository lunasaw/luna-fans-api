package com.luna.baidu.dto.face;

import com.luna.baidu.dto.location.LocationDO;
import lombok.Data;

/**
 * @Package: com.luna.baidu.dto
 * @ClassName: FaceDTO
 * @Author: luna
 * @CreateTime: 2020/8/10 17:05
 * @Description:
 */
@Data
public class FaceResultDTO {

    private String     faceToken;

    private LocationDO locationDO;

    private LiveNess   liveness;

    private String     spoofing;

    private Double     age;

    private Integer    beauty;

    private String     faceProbability;

    @Data
    public static class LiveNess {
        private String livemapscore;
    }

}
