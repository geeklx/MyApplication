package com.example.shining.p041_uppicture.uploadimg.domain;

import java.io.Serializable;

/**
 * 一个图片对象
 */
public class UlImageItem implements Serializable {
    public String imageId;
    public String thumbnailPath;
    public String imagePath;
    public boolean isSelected = false;
}
