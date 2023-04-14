package com.luna.baidu.dto.face.facecheck;

import lombok.Data;

import java.util.List;

/**
 * author: luna
 * 2020/8/10 17:05
 * 
 * @author luna
 */
@Data
public class FaceDetailDTO {

    private String                  faceToken;

    private List<FaceListResultDTO> faceList;

}
