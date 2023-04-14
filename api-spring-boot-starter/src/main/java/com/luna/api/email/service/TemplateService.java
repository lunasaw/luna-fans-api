package com.luna.api.email.service;

import com.luna.api.email.dto.TemplateDTO;
import com.luna.api.email.entity.TemplateDO;
import com.luna.api.email.mapper.TemplateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author chenzhangyue@luna.com
 * 2021/8/20
 */
@Service
public class TemplateService {

    @Autowired
    private TemplateDAO templateDAO;

    public List<TemplateDTO> listTemplate() {
        return templateDAO.list().stream().map(TemplateService::templateDO2TemplateDTO).collect(Collectors.toList());
    }

    public List<TemplateDTO> listTemplateSubject() {
        return templateDAO.subjectList().stream().map(TemplateService::templateDO2TemplateDTO).collect(Collectors.toList());
    }

    public Integer addTemplate(TemplateDTO templateDTO) {
        return templateDAO.insert(TemplateService.templateDTO2TemplateDO(templateDTO));
    }

    public TemplateDTO getTemplateById(long id) {
        return TemplateService.templateDO2TemplateDTO(templateDAO.get(id));
    }

    public TemplateDTO getTemplateBySubject(String subject) {
        return TemplateService.templateDO2TemplateDTO(templateDAO.getBySubject(subject));
    }

    public Integer updateTemplate(Long id, TemplateDTO templateDTO) {
        TemplateDO templateDO = templateDAO.get(id);
        if (Objects.isNull(templateDO)) {
            return 0;
        }
        templateDTO.setId(id);
        return templateDAO.update(TemplateService.templateDTO2TemplateDO(templateDTO));
    }

    public Integer deleteById(Long id) {
        return templateDAO.delete(id);
    }

    public static TemplateDO templateDTO2TemplateDO(TemplateDTO templateDTO) {
        if (templateDTO == null) {
            return null;
        }
        TemplateDO templateDO = new TemplateDO();
        templateDO.setId(templateDTO.getId());
        templateDO.setCreateTime(templateDTO.getCreateTime());
        templateDO.setModifiedTime(templateDTO.getModifiedTime());
        templateDO.setSubject(templateDTO.getSubject());
        templateDO.setContent(templateDTO.getContent());
        return templateDO;
    }

    public static TemplateDTO templateDO2TemplateDTO(TemplateDO templateDO) {
        if (templateDO == null) {
            return null;
        }
        TemplateDTO templateDTO = new TemplateDTO();
        templateDTO.setId(templateDO.getId());
        templateDTO.setCreateTime(templateDO.getCreateTime());
        templateDTO.setModifiedTime(templateDO.getModifiedTime());
        templateDTO.setSubject(templateDO.getSubject());
        templateDTO.setContent(templateDO.getContent());
        return templateDTO;
    }
}
