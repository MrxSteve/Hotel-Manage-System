package com.devsteve.hotel_manage_system.application.ports.output;

public interface ImageStoragePort {
    String uploadImage(String filename, byte[] content, String contentType);
    void deleteImage(String key);
}
