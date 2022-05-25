package com.luna.baidu.dto.face;

import com.luna.baidu.dto.word.FaceDTO;
import lombok.Data;

import java.util.List;


/**
 * 2020/8/10 17:05
 * 
 * @author luna
 */
@Data
public class FaceLiveResultDTO {

    /**
     * 活体分数
     */
    private Float         faceLiveness;

    /**
     * 脸部信息
     */
    private List<FaceResultDTO> faceList;

}
