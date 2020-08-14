/*
 * Copyright (c) 2017-2018 THL A29 Limited, a Tencent company. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.luna.tencent.dto.personGroup;

public class GroupCandidateDTO {

    /**
     * 人员库ID 。
     */
    private String         GroupId;

    /**
     * 识别出的最相似候选人。
     */
    private CandidateDTO[] Candidates;

    /**
     * Get 人员库ID 。
     * 
     * @return GroupId 人员库ID 。
     */
    public String getGroupId() {
        return this.GroupId;
    }

    /**
     * Set 人员库ID 。
     * 
     * @param GroupId 人员库ID 。
     */
    public void setGroupId(String GroupId) {
        this.GroupId = GroupId;
    }

    /**
     * Get 识别出的最相似候选人。
     * 
     * @return Candidates 识别出的最相似候选人。
     */
    public CandidateDTO[] getCandidates() {
        return Candidates;
    }

    /**
     * Set 识别出的最相似候选人。
     * 
     * @param Candidates 识别出的最相似候选人。
     */
    public void setCandidates(CandidateDTO[] candidates) {
        Candidates = candidates;
    }
}
