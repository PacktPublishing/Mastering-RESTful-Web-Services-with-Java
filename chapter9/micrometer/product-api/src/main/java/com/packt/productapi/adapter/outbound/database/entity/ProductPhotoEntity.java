package com.packt.productapi.adapter.outbound.database.entity;

import com.packt.productapi.domain.ProductPhoto;
import jakarta.persistence.*;

@Entity
@Table(name = "TB_PRODUCT_PHOTO")
public class ProductPhotoEntity extends ProductPhoto {

    @Id
    @Column(name = "CO_PRODUCT")
    @Override
    public String getProductId() {
        return super.getProductId();
    }

    @Column(name = "PHOTO_HASH")
    @Override
    public String getPhotoHash() {
        return super.getPhotoHash();
    }

    @Column(name = "PHOTO")
    @Lob
    @Override
    public byte[] getPhoto() { return super.getPhoto(); }

    @Column(name = "PHOTO_CONTENT_TYPE")
    @Override
    public String getPhotoContentType() {
        return super.getPhotoContentType();
    }

}

