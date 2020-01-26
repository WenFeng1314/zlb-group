package com.zlb.product.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class ProductCashSale implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 商品库ID
     */
    private Long libraryId;

    /**
     * 商品编码SKU/SPU
     */
    private String code;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 0数据迁移,其他代表公司ID
     */
    private Integer source;

    /**
     * 分类ID
     */
    private Integer classifyId;

    /**
     * 产品品牌ID
     */
    private Integer brandId;

    /**
     * 产品型号ID
     */
    private Integer modelId;

    /**
     * 0表示SPU SKU存SPU ID
     */
    private Long pid;

    /**
     * 销量
     */
    private Long saleCount;

    /**
     * 浏览次数
     */
    private Long viewsCount;

    /**
     * 审核与否:0-待审核 1-已上架 2-审核不通过 3-已下架 4简易新增未修改 5.待上架 6.待发布
     */
    private Byte verify;

    /**
     * 包装规格
     */
    private String spec;

    /**
     * 库存及销售单位
     */
    private String unit;

    /**
     * 最小起订量
     */
    private Integer purchaseQuantity;

    /**
     * 重量
     */
    private String weight;

    /**
     * 保质期
     */
    private String shelfLife;

    /**
     * 使用行业
     */
    private String industry;

    /**
     * 创建公司
     */
    private Integer companyId;

    /**
     * 创建/修改人
     */
    private Integer memberId;

    /**
     * 发布时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Byte del;

    /**
     * 详细信息
     */
    private String vendorDescribe;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Long saleCount) {
        this.saleCount = saleCount;
    }

    public Long getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Long viewsCount) {
        this.viewsCount = viewsCount;
    }

    public Byte getVerify() {
        return verify;
    }

    public void setVerify(Byte verify) {
        this.verify = verify;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(Integer purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getDel() {
        return del;
    }

    public void setDel(Byte del) {
        this.del = del;
    }

    public String getVendorDescribe() {
        return vendorDescribe;
    }

    public void setVendorDescribe(String vendorDescribe) {
        this.vendorDescribe = vendorDescribe;
    }
}