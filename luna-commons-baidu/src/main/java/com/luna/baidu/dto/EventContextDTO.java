package com.luna.baidu.dto;

import java.util.List;

/**
 * @Package: com.luna.baidu.dto
 * @ClassName: EventContextDTO
 * @Author: luna
 * @CreateTime: 2020/8/10 16:33
 * @Description:
 */
public class EventContextDTO {

    private String        event_name;

    private String        first_vein_time;

    private List<VeinDTO> vein;

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getFirst_vein_time() {
        return first_vein_time;
    }

    public void setFirst_vein_time(String first_vein_time) {
        this.first_vein_time = first_vein_time;
    }

    public List<VeinDTO> getVein() {
        return vein;
    }

    public void setVein(List<VeinDTO> vein) {
        this.vein = vein;
    }
}
