package com.devsteve.hotel_manage_system.shared.dto.req.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class RoomImageRequest {
    private MultipartFile image;
}
