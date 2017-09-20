package com.example.shining.p041_uppicture.uploadimg.domain;

import java.io.Serializable;
import java.util.List;

public class UlDaoContentModel implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int dataId;
    private String dataContent;
    private List<UlDataImgModel> dataImageList;

    public UlDaoContentModel() {
    }

    public UlDaoContentModel(int dataId, String dataContent, List<UlDataImgModel> dataImageList) {
        this.dataId = dataId;
        this.dataContent = dataContent;
        this.dataImageList = dataImageList;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public String getDataContent() {
        return dataContent;
    }

    public void setDataContent(String dataContent) {
        this.dataContent = dataContent;
    }

    public List<UlDataImgModel> getDataImageList() {
        return dataImageList;
    }

    public void setDataImageList(List<UlDataImgModel> dataImageList) {
        this.dataImageList = dataImageList;
    }
}
