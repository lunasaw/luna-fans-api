package com.luna.common.utils.location;

import com.google.common.collect.Lists;

import com.luna.common.dto.BodyDTO;
import com.luna.common.dto.FaceDTO;
import com.luna.common.dto.WordDTO;
import com.luna.common.entity.Location;

import java.util.List;

/**
 * @Package: com.luna.common.utils
 * @ClassName: FaceUtils
 * @Author: luna
 * @CreateTime: 2020/8/10 17:11
 * @Description:
 */
public class LocationUtils {

    public static List<Location> faceDTOList2LocationList(List<FaceDTO> list) {
        List<Location> locationlist = Lists.newArrayList();
        for (FaceDTO faceDTO : list) {
            locationlist.add(faceDTO.getLocation());
        }
        return locationlist;
    }

    public static List<Location> wordDTOList2LocationList(List<WordDTO> list) {
        List<Location> locationlist = Lists.newArrayList();
        for (WordDTO wordDTO : list) {
            locationlist.add(wordDTO.getLocation());
        }
        return locationlist;
    }

    public static List<Location> bodyDTOList2LocationList(List<BodyDTO> list) {
        List<Location> locationlist = Lists.newArrayList();
        for (BodyDTO wordDTO : list) {
            locationlist.add(wordDTO.getLocation());
        }
        return locationlist;
    }
}
