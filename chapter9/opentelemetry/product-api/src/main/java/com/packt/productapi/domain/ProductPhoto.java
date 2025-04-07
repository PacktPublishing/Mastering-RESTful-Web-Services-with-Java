package com.packt.productapi.domain;

import jakarta.xml.bind.DatatypeConverter;
import org.springframework.util.DigestUtils;

import java.util.Objects;

public class ProductPhoto {

    private String productId;
    private String photoHash;

    private byte[] photo;

    private String photoContentType;

    public ProductPhoto() {
        super();
    }

    public ProductPhoto(String productId, String photoHash, byte[] photo, String photoContentType) {
        this.productId = productId;
        this.photo = photo;
        this.photoHash = photoHash;
        this.photoContentType = photoContentType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPhotoHash() {
        return photoHash;
    }

    public void setPhotoHash(String photoHash) {
        this.photoHash = photoHash;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        setPhotoHash(DatatypeConverter.printHexBinary(DigestUtils.md5Digest(photo)));
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPhoto that = (ProductPhoto) o;
        return Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}

