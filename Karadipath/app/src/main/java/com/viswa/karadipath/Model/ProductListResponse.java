package com.viswa.karadipath.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductListResponse {

    @SerializedName("SearchList")
    @Expose
    private List<SearchList> searchList = null;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("ResultCnt")
    @Expose
    private Integer resultCnt;
    @SerializedName("errcode")
    @Expose
    private String errcode;

    public List<SearchList> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<SearchList> searchList) {
        this.searchList = searchList;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getResultCnt() {
        return resultCnt;
    }

    public void setResultCnt(Integer resultCnt) {
        this.resultCnt = resultCnt;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public class SearchList {

        @SerializedName("ProductId")
        @Expose
        private String productId;
        @SerializedName("ThumbImg")
        @Expose
        private String thumbImg;
        @SerializedName("FinalPrice")
        @Expose
        private String finalPrice;
        @SerializedName("OriginalPrice")
        @Expose
        private String originalPrice;
        @SerializedName("ProductTitle")
        @Expose
        private String productTitle;
        @SerializedName("SKU")
        @Expose
        private String sKU;
        @SerializedName("StockStatus")
        @Expose
        private String stockStatus;
        @SerializedName("CategoryId")
        @Expose
        private String categoryId;
        @SerializedName("CategoryType")
        @Expose
        private String categoryType;
        @SerializedName("AgeGroup")
        @Expose
        private String ageGroup;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getThumbImg() {
            return thumbImg;
        }

        public void setThumbImg(String thumbImg) {
            this.thumbImg = thumbImg;
        }

        public String getFinalPrice() {
            return finalPrice;
        }

        public void setFinalPrice(String finalPrice) {
            this.finalPrice = finalPrice;
        }

        public String getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(String originalPrice) {
            this.originalPrice = originalPrice;
        }

        public String getProductTitle() {
            return productTitle;
        }

        public void setProductTitle(String productTitle) {
            this.productTitle = productTitle;
        }

        public String getSKU() {
            return sKU;
        }

        public void setSKU(String sKU) {
            this.sKU = sKU;
        }

        public String getStockStatus() {
            return stockStatus;
        }

        public void setStockStatus(String stockStatus) {
            this.stockStatus = stockStatus;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryType() {
            return categoryType;
        }

        public void setCategoryType(String categoryType) {
            this.categoryType = categoryType;
        }

        public String getAgeGroup() {
            return ageGroup;
        }

        public void setAgeGroup(String ageGroup) {
            this.ageGroup = ageGroup;
        }

    }
}
