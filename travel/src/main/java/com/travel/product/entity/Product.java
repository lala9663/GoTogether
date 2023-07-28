package com.travel.product.entity;

import com.travel.global.entity.BaseEntity;
import com.travel.image.entity.Image;
import com.travel.image.entity.ProductImage;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    //상품 이름(제목)
    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private Integer productPrice;

    @Column(name = "product_status")
    @Enumerated(EnumType.STRING)
    private Status productStatus;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PeriodOption> periodOptions = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ProductCategory> productCategories = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImages = new ArrayList<>();

    //내용
    @Column(name = "product_content")
    private String productContent;

    //내용 상세
    @Column(name = "content_detail")
    private String contentDetail;

    @Setter
    @Column(name = "wishlist_count")
    private Long wishlistCount = 0L;

    public void addPeriodOption(PeriodOption periodOption) {
        periodOption.setProduct(this);
        periodOptions.add(periodOption);
    }

    public List<ProductImage> addImages(List<Image> images) {
        for (Image image : images) {
            ProductImage productImage = image.toProductImage();
            productImage.setProduct(this);
            this.productImages.add(productImage);
        }
        return this.productImages;
    }

    public void changeStatusToHidden(Product product) {
        product.productStatus = Status.HIDDEN;
    }

    public void updateProduct(Product product) {
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.productStatus = product.getProductStatus();
        this.productContent = product.getProductContent();
        this.contentDetail = product.getContentDetail();
    }

    @Builder
    public Product(String productName, Integer productPrice, Status productStatus, String productContent, String contentDetail) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStatus = productStatus;
        this.productContent = productContent;
        this.contentDetail = contentDetail;
    }

    public PurchasedProduct toPurchase(PeriodOption periodOption, Integer quantity) {
        return PurchasedProduct.builder()
                .product(this)
                .periodOption(periodOption)
                .quantity(quantity)
                .build();
    }
}
