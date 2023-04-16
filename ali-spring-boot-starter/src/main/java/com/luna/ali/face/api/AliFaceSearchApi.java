package com.luna.ali.face.api;

import com.aliyun.facebody20191230.Client;
import com.aliyun.facebody20191230.models.*;
import com.luna.ali.face.AliFaceBodyClientSupport;
import com.luna.ali.face.enums.FaceTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author weidian
 * @description
 * @date 2023/4/16
 */
@Component
public class AliFaceSearchApi {

    @Autowired
    private AliFaceBodyClientSupport aliFaceBodyClientSupport;

    private Client getClient() {
        return (Client)aliFaceBodyClientSupport.getClient(FaceTypeEnum.FACE_DETECTION);
    }

    /**
     * 创建人脸库
     *
     * @param name
     * @return
     */
    public CreateFaceDbResponse createFaceDbWithOptions(String name) {
        try {
            return getClient().createFaceDbWithOptions(new CreateFaceDbRequest().setName(name), aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询人脸库列表
     *
     * @param limit 每页最多显示的样本数目，范围1~200，默认200。
     * @param offset
     * @return
     */
    public ListFaceDbsResponse listFaceDbsWithOptions(Long offset, Long limit) {
        try {
            ListFaceDbsRequest request = new ListFaceDbsRequest().setLimit(limit).setOffset(offset);
            return getClient().listFaceDbsWithOptions(request, aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ListFaceEntitiesResponse listFaceEntitiesWithOptions(ListFaceEntitiesRequest request) {
        try {
            return getClient().listFaceEntitiesWithOptions(request, aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除人脸库
     * 
     * @param dbName
     * @return
     */
    public DeleteFaceDbResponse deleteFaceDbWithOptions(String dbName) {
        try {
            DeleteFaceDbRequest request = new DeleteFaceDbRequest().setName(dbName);
            return getClient().deleteFaceDbWithOptions(request, aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 人脸列表。
     *
     * @param dbName 数据库名称 【必须】
     * @param offset 起始记录
     * @param limit 每页最多显示的样本数目
     * @param token 当查询结果超过2000条时，需要使用上一次请求返回的Token作为请求下一页的参数
     * @param labels 标签，最多支持10个标签，多个标签以英文逗号（,）分割
     * @param entityIdPrefix 样本ID，包含数字、字母和下划线
     * @param order 排列方式。包括asc（升序）和desc（降序）
     */
    public ListFaceEntitiesResponse listFaceEntitiesWithOptions(String dbName, Integer offset, Integer limit, String token, String labels,
        String entityIdPrefix, String order) {
        ListFaceEntitiesRequest request = new ListFaceEntitiesRequest();
        request.setDbName(dbName);
        request.setOffset(offset);
        request.setLimit(limit);
        request.setToken(token);
        request.setLabels(labels);
        request.setEntityIdPrefix(entityIdPrefix);
        request.setOrder(order);
        return listFaceEntitiesWithOptions(request);
    }

    public ListFaceEntitiesResponse listFaceEntitiesWithOptions(String dbName, Integer offset, Integer limit, String token, String labels,
        String entityIdPrefix) {
        return listFaceEntitiesWithOptions(dbName, offset, limit, token, labels, entityIdPrefix, "asc");
    }

    public ListFaceEntitiesResponse listFaceEntitiesWithOptions(String dbName, Integer pageNum, Integer pageSize, String token,
        String entityIdPrefix) {
        return listFaceEntitiesWithOptions(dbName, (pageNum - 1) * pageSize, pageSize, token, null, entityIdPrefix, "desc");
    }

    public ListFaceEntitiesResponse listFaceEntitiesWithOptions(String dbName, Integer pageNum, Integer pageSize, String token) {
        return listFaceEntitiesWithOptions(dbName, (pageNum - 1) * pageSize, pageSize, token, null, null, "desc");
    }

    /**
     * 新增人脸库人脸数据
     * 
     * @param request
     * @return
     */
    public AddFaceEntityResponse addFaceEntityWithOptions(AddFaceEntityRequest request) {
        try {
            return getClient().addFaceEntityWithOptions(request, aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 新增人脸库人脸数据。
     *
     * @param dbName 数据库名称
     * @param entityId 实体ID，可以包含数字、字母和下划线
     * @param labels 输入标签，最多可以添加10个标签，标签之间使用英文逗号（,）分隔
     */
    public AddFaceEntityResponse addFaceEntityWithOptions(String dbName, String entityId, String labels) {
        return addFaceEntityWithOptions(new AddFaceEntityRequest().setDbName(dbName).setEntityId(entityId).setLabels(labels));
    }

    public AddFaceEntityResponse addFaceEntityWithOptions(String dbName, String entityId) {
        return addFaceEntityWithOptions(dbName, entityId, null);
    }

    /**
     * 查询人脸库
     * 
     * @param request
     * @return
     */
    public GetFaceEntityResponse getFaceEntityWithOptions(GetFaceEntityRequest request) {
        try {
            return getClient().getFaceEntityWithOptions(request, aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GetFaceEntityResponse getFaceEntityWithOptions(String dbName, String entityId) {
        return getFaceEntityWithOptions(new GetFaceEntityRequest().setDbName(dbName).setEntityId(entityId));
    }

    /**
     * 更新人脸库人脸数据
     *
     * @param request
     * @return
     */
    public UpdateFaceEntityResponse updateFaceEntityWithOptions(UpdateFaceEntityRequest request) {
        try {
            return getClient().updateFaceEntityWithOptions(request, aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public UpdateFaceEntityResponse updateFaceEntityWithOptions(String dbName, String entityId, String labels) {
        return updateFaceEntityWithOptions(new UpdateFaceEntityRequest().setDbName(dbName).setEntityId(entityId).setLabels(labels));
    }

    /**
     * 添加人脸库人脸数据
     * 
     * @param request
     * @return
     */
    public AddFaceResponse addFaceWithOptions(AddFaceRequest request) {
        try {
            return getClient().addFaceWithOptions(request, aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 方法描述。
     *
     * @param dbName 数据库名称。支持小写字母、数字、下划线的组合，长度1~64
     * @param imageUrl 人脸图片地址，人脸必须是正面无遮挡单人人脸
     * @param entityId 实体ID，用来标识用户的唯一性，如用户ID、员工ID等。每个实体可包含多张人脸图片。支持大小写字母、数字、下划线、减号的组合，长度为1~64 字符
     * @param extraData 自定义信息。支持字母、数字、标点符号和汉字。不超过512个字符
     * @param qualityScoreThreshold
     * 质量分阈值。对新增人脸图片进行质量分析判断，得到的质量分小于该阈值则表示新增人脸图片质量不符合要求，添加失败。取值范围[0.0,100.0]。0.0或空则表示不做质量分判断逻辑
     * @param similarityScoreThresholdInEntity
     * 类内相似度阈值。将新增人脸图片与EntityId内已有人脸进行相似比对，小于该阈值则表示与其他人脸图片差异较大，添加失败.；若EntityId内没有人脸存在，则添加成功。取值范围[0.0,100.0]。0.0或空则表示不做类内相似度判断逻辑。参考值见人脸比对1:1接口的返回结果字段Thresholds
     * @param similarityScoreThresholdBetweenEntity
     * 类间相似度阈值。将新增人脸图片与其他所有EntityId内已有人脸进行相似比对，大于等于该阈值则表示其他EntityId内存在相似人脸，出现重复，添加失败。取值范围[0.0,100.0]。0.0或空则表示不做类间相似度判断逻辑。参考值见人脸比对1:1接口的返回结果字段Thresholds
     */
    public AddFaceResponse addFaceWithOptions(String dbName, String imageUrl, String entityId, String extraData, Float qualityScoreThreshold,
        Float similarityScoreThresholdInEntity, Float similarityScoreThresholdBetweenEntity) {
        return addFaceWithOptions(new AddFaceRequest()
            .setDbName(dbName)
            .setImageUrl(imageUrl)
            .setEntityId(entityId)
            .setExtraData(extraData)
            .setQualityScoreThreshold(qualityScoreThreshold)
            .setSimilarityScoreThresholdInEntity(similarityScoreThresholdInEntity)
            .setSimilarityScoreThresholdBetweenEntity(similarityScoreThresholdBetweenEntity));
    }

    public AddFaceResponse addFaceWithOptions(String dbName, String imageUrl, String entityId, String extraData, Float qualityScoreThreshold) {
        return addFaceWithOptions(dbName, imageUrl, entityId, extraData, qualityScoreThreshold, null, null);
    }

    public AddFaceResponse addFaceWithOptions(String dbName, String imageUrl, String entityId, String extraData) {
        return addFaceWithOptions(dbName, imageUrl, entityId, extraData, null, null, null);
    }

    /**
     * 批量添加人脸库人脸数据
     * 
     * @param request
     * @return
     */
    public BatchAddFacesResponse batchAddFacesWithOptions(BatchAddFacesRequest request) {
        try {
            return getClient().batchAddFacesWithOptions(request, aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BatchAddFacesResponse batchAddFacesWithOptions(String dbName, List<BatchAddFacesRequest.BatchAddFacesRequestFaces> faces, String entityId,
        Float qualityScoreThreshold,
        Float similarityScoreThresholdInEntity, Float similarityScoreThresholdBetweenEntity) {
        return batchAddFacesWithOptions(new BatchAddFacesRequest()
            .setDbName(dbName)
            .setFaces(faces)
            .setEntityId(entityId)
            .setQualityScoreThreshold(qualityScoreThreshold)
            .setSimilarityScoreThresholdInEntity(similarityScoreThresholdInEntity)
            .setSimilarityScoreThresholdBetweenEntity(similarityScoreThresholdBetweenEntity));
    }

    public BatchAddFacesResponse batchAddFacesWithOptions(String dbName, List<BatchAddFacesRequest.BatchAddFacesRequestFaces> faces, String entityId,
        Float qualityScoreThreshold) {
        return batchAddFacesWithOptions(dbName, faces, entityId, qualityScoreThreshold, null, null);
    }

    public BatchAddFacesResponse batchAddFacesWithOptions(String dbName, Map<String/*URL*/, String/*extraData*/> faces, String entityId,
        Float qualityScoreThreshold) {
        List<BatchAddFacesRequest.BatchAddFacesRequestFaces> facesList = faces.entrySet().stream()
            .map(entry -> new BatchAddFacesRequest.BatchAddFacesRequestFaces().setImageURL(entry.getKey()).setExtraData(entry.getValue()))
            .collect(Collectors.toList());

        return batchAddFacesWithOptions(dbName, facesList, entityId, qualityScoreThreshold);
    }

    /**
     * 删除人脸库人脸
     * 
     * @param dbName
     * @param faceId
     * @return
     */
    public DeleteFaceResponse deleteFaceWithOptions(String dbName, String faceId) {
        try {
            DeleteFaceRequest request = new DeleteFaceRequest().setDbName(dbName).setFaceId(faceId);
            return getClient().deleteFaceWithOptions(request, aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除人脸库人脸数据
     * 
     * @param dbName
     * @param entityId
     * @return
     */
    public DeleteFaceEntityResponse deleteFaceEntityWithOptions(String dbName, String entityId) {
        try {
            DeleteFaceEntityRequest request = new DeleteFaceEntityRequest().setDbName(dbName).setEntityId(entityId);
            return getClient().deleteFaceEntityWithOptions(request, aliFaceBodyClientSupport.getRuntimeOptions());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
