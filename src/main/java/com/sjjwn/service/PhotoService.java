package com.sjjwn.service;

import com.sjjwn.model.dto.PageResultDTO;
import com.sjjwn.model.dto.PhotoAdminDTO;
import com.sjjwn.model.dto.PhotoDTO;
import com.sjjwn.entity.Photo;
import com.sjjwn.model.vo.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PhotoService extends IService<Photo> {

    PageResultDTO<PhotoAdminDTO> listPhotos(ConditionVO conditionVO);

    void updatePhoto(PhotoInfoVO photoInfoVO);

    void savePhotos(PhotoVO photoVO);

    void updatePhotosAlbum(PhotoVO photoVO);

    void updatePhotoDelete(DeleteVO deleteVO);

    void deletePhotos(List<Integer> photoIds);

    PhotoDTO listPhotosByAlbumId(Integer albumId);

}
