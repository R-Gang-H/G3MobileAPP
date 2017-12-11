package com.app.itserv.jparser;

import java.util.List;

/**
 * attributes : {"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantDetailSize":1,"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
 * msg : OK
 * success : true
 * obj : {"tenant":{"id":"402883fd5c8fd7be015c8fde6fd90003","status":"ACTIVE","updateDate":null,"updateName":null,"updateBy":null,"tenantCode":"","headName":"程晓梅","headBy":"402883fd5c8fd7be015c8fe0a84e000a","tenantType":"PERSON","createName":"管理员","createDate":1497062338000,"createBy":"admin","tenantEnglishName":"","tenantFullname":"北京平谷大棚基地","tenantShortname":"平谷大棚"},"tenantDetail":[{"id":"402883fd5c8fd7be015c8fde6fd90004","description":"","updateDate":null,"updateName":"","updateBy":"","regionAddr":"","postcode":"100000","createName":"管理员","createDate":1497062338000,"createBy":"admin","regionIdCountry":"中国","regionIdDistrict":"平谷区","attachment1":"","regionIdCity":"市辖区","regionIdProvince":"北京市","registerAddrDistrict":"","businessAddrProvince":"","registerAddrCountry":"","businessAddrCountry":"","businessAddrDistrict":"","businessAddrCity":"","registerAddrCity":"","registerAddrProvince":"","saleRegion":"REGIONWIDE","openStatus":"OPENING","tanentId":"402883fd5c8fd7be015c8fde6fd90003","contactStatus":"SIGNED","marketLevel":"VOLKSWAGEN","licenceMerged":"Y","busiLicenceCode":"","orgLicenceCode":"","registerAddrDetail":"","taxLicenceCode":"","businessAddrDetail":"","busiTimeBegin":null,"busiTimeEnd":null}]}
 */

/**
 * @project name：yyshed
 * @type name：JsonMerchantInfoObject
 * @description：商户信息json报文解析类
 * @author：gang
 * @date time：2017-6-12 下午12:51:00
 */
public class JsonMerchantInfoObject {

    private AttributesBean attributes;
    private String msg;
    private boolean success;
    private ObjBean obj;

    public AttributesBean getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributesBean attributes) {
        this.attributes = attributes;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public static class AttributesBean {
        /**
         * tenantId : 402883fd5c8fd7be015c8fde6fd90003 tenantDetailSize : 1
         * currUserId : 402883fd5c8fd7be015c8fe0a84e000a currTenantId :
         * 402883fd5c8fd7be015c8fde6fd90003
         */

        private String tenantId;
        private int tenantDetailSize;
        private String currUserId;
        private String currTenantId;

        public String getTenantId() {
            return tenantId;
        }

        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }

        public int getTenantDetailSize() {
            return tenantDetailSize;
        }

        public void setTenantDetailSize(int tenantDetailSize) {
            this.tenantDetailSize = tenantDetailSize;
        }

        public String getCurrUserId() {
            return currUserId;
        }

        public void setCurrUserId(String currUserId) {
            this.currUserId = currUserId;
        }

        public String getCurrTenantId() {
            return currTenantId;
        }

        public void setCurrTenantId(String currTenantId) {
            this.currTenantId = currTenantId;
        }
    }

    public static class ObjBean {
        /**
         * tenant : {"id":"402883fd5c8fd7be015c8fde6fd90003","status":"ACTIVE",
         * "updateDate"
         * :null,"updateName":null,"updateBy":null,"tenantCode":"","headName"
         * :"程晓梅"
         * ,"headBy":"402883fd5c8fd7be015c8fe0a84e000a","tenantType":"PERSON"
         * ,"createName":"管理员","createDate":1497062338000,"createBy":"admin",
         * "tenantEnglishName"
         * :"","tenantFullname":"北京平谷大棚基地","tenantShortname":"平谷大棚"}
         * tenantDetail :
         * [{"id":"402883fd5c8fd7be015c8fde6fd90004","description"
         * :"","updateDate"
         * :null,"updateName":"","updateBy":"","regionAddr":"","postcode"
         * :"100000"
         * ,"createName":"管理员","createDate":1497062338000,"createBy":"admin"
         * ,"regionIdCountry"
         * :"中国","regionIdDistrict":"平谷区","attachment1":"","regionIdCity"
         * :"市辖区","regionIdProvince"
         * :"北京市","registerAddrDistrict":"","businessAddrProvince"
         * :"","registerAddrCountry"
         * :"","businessAddrCountry":"","businessAddrDistrict"
         * :"","businessAddrCity"
         * :"","registerAddrCity":"","registerAddrProvince"
         * :"","saleRegion":"REGIONWIDE","openStatus":"OPENING","tanentId":
         * "402883fd5c8fd7be015c8fde6fd90003"
         * ,"contactStatus":"SIGNED","marketLevel"
         * :"VOLKSWAGEN","licenceMerged":"Y"
         * ,"busiLicenceCode":"","orgLicenceCode"
         * :"","registerAddrDetail":"","taxLicenceCode"
         * :"","businessAddrDetail":"","busiTimeBegin":null,"busiTimeEnd":null}]
         */

        private TenantBean tenant;
        private List<TenantDetailBean> tenantDetail;

        public TenantBean getTenant() {
            return tenant;
        }

        public void setTenant(TenantBean tenant) {
            this.tenant = tenant;
        }

        public List<TenantDetailBean> getTenantDetail() {
            return tenantDetail;
        }

        public void setTenantDetail(List<TenantDetailBean> tenantDetail) {
            this.tenantDetail = tenantDetail;
        }

        public static class TenantBean {
            /**
             * id : 402883fd5c8fd7be015c8fde6fd90003 status : ACTIVE updateDate
             * : null updateName : null updateBy : null tenantCode : headName :
             * 程晓梅 headBy : 402883fd5c8fd7be015c8fe0a84e000a tenantType : PERSON
             * createName : 管理员 createDate : 1497062338000 createBy : admin
             * tenantEnglishName : tenantFullname : 北京平谷大棚基地 tenantShortname :
             * 平谷大棚
             */

            private String id;
            private String status;
            private Object updateDate;
            private Object updateName;
            private Object updateBy;
            private String tenantCode;
            private String headName;
            private String headBy;
            private String tenantType;
            private String createName;
            private long createDate;
            private String createBy;
            private String tenantEnglishName;
            private String tenantFullname;
            private String tenantShortname;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(Object updateDate) {
                this.updateDate = updateDate;
            }

            public Object getUpdateName() {
                return updateName;
            }

            public void setUpdateName(Object updateName) {
                this.updateName = updateName;
            }

            public Object getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(Object updateBy) {
                this.updateBy = updateBy;
            }

            public String getTenantCode() {
                return tenantCode;
            }

            public void setTenantCode(String tenantCode) {
                this.tenantCode = tenantCode;
            }

            public String getHeadName() {
                return headName;
            }

            public void setHeadName(String headName) {
                this.headName = headName;
            }

            public String getHeadBy() {
                return headBy;
            }

            public void setHeadBy(String headBy) {
                this.headBy = headBy;
            }

            public String getTenantType() {
                return tenantType;
            }

            public void setTenantType(String tenantType) {
                this.tenantType = tenantType;
            }

            public String getCreateName() {
                return createName;
            }

            public void setCreateName(String createName) {
                this.createName = createName;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public String getCreateBy() {
                return createBy;
            }

            public void setCreateBy(String createBy) {
                this.createBy = createBy;
            }

            public String getTenantEnglishName() {
                return tenantEnglishName;
            }

            public void setTenantEnglishName(String tenantEnglishName) {
                this.tenantEnglishName = tenantEnglishName;
            }

            public String getTenantFullname() {
                return tenantFullname;
            }

            public void setTenantFullname(String tenantFullname) {
                this.tenantFullname = tenantFullname;
            }

            public String getTenantShortname() {
                return tenantShortname;
            }

            public void setTenantShortname(String tenantShortname) {
                this.tenantShortname = tenantShortname;
            }
        }

        public static class TenantDetailBean {
            /**
             * id : 402883fd5c8fd7be015c8fde6fd90004 description : updateDate :
             * null updateName : updateBy : regionAddr : postcode : 100000
             * createName : 管理员 createDate : 1497062338000 createBy : admin
             * regionIdCountry : 中国 regionIdDistrict : 平谷区 attachment1 :
             * regionIdCity : 市辖区 regionIdProvince : 北京市 registerAddrDistrict :
             * businessAddrProvince : registerAddrCountry : businessAddrCountry
             * : businessAddrDistrict : businessAddrCity : registerAddrCity :
             * registerAddrProvince : saleRegion : REGIONWIDE openStatus :
             * OPENING tanentId : 402883fd5c8fd7be015c8fde6fd90003 contactStatus
             * : SIGNED marketLevel : VOLKSWAGEN licenceMerged : Y
             * busiLicenceCode : orgLicenceCode : registerAddrDetail :
             * taxLicenceCode : businessAddrDetail : busiTimeBegin : null
             * busiTimeEnd : null
             */

            private String id;
            private String description;
            private Object updateDate;
            private String updateName;
            private String updateBy;
            private String regionAddr;
            private String postcode;
            private String createName;
            private long createDate;
            private String createBy;
            private String regionIdCountry;
            private String regionIdDistrict;
            private String attachment1;
            private String regionIdCity;
            private String regionIdProvince;
            private String registerAddrDistrict;
            private String businessAddrProvince;
            private String registerAddrCountry;
            private String businessAddrCountry;
            private String businessAddrDistrict;
            private String businessAddrCity;
            private String registerAddrCity;
            private String registerAddrProvince;
            private String saleRegion;
            private String openStatus;
            private String tanentId;
            private String contactStatus;
            private String marketLevel;
            private String licenceMerged;
            private String busiLicenceCode;
            private String orgLicenceCode;
            private String registerAddrDetail;
            private String taxLicenceCode;
            private String businessAddrDetail;
            private long busiTimeBegin;
            private long busiTimeEnd;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public Object getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(Object updateDate) {
                this.updateDate = updateDate;
            }

            public String getUpdateName() {
                return updateName;
            }

            public void setUpdateName(String updateName) {
                this.updateName = updateName;
            }

            public String getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(String updateBy) {
                this.updateBy = updateBy;
            }

            public String getRegionAddr() {
                return regionAddr;
            }

            public void setRegionAddr(String regionAddr) {
                this.regionAddr = regionAddr;
            }

            public String getPostcode() {
                return postcode;
            }

            public void setPostcode(String postcode) {
                this.postcode = postcode;
            }

            public String getCreateName() {
                return createName;
            }

            public void setCreateName(String createName) {
                this.createName = createName;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public String getCreateBy() {
                return createBy;
            }

            public void setCreateBy(String createBy) {
                this.createBy = createBy;
            }

            public String getRegionIdCountry() {
                return regionIdCountry;
            }

            public void setRegionIdCountry(String regionIdCountry) {
                this.regionIdCountry = regionIdCountry;
            }

            public String getRegionIdDistrict() {
                return regionIdDistrict;
            }

            public void setRegionIdDistrict(String regionIdDistrict) {
                this.regionIdDistrict = regionIdDistrict;
            }

            public String getAttachment1() {
                return attachment1;
            }

            public void setAttachment1(String attachment1) {
                this.attachment1 = attachment1;
            }

            public String getRegionIdCity() {
                return regionIdCity;
            }

            public void setRegionIdCity(String regionIdCity) {
                this.regionIdCity = regionIdCity;
            }

            public String getRegionIdProvince() {
                return regionIdProvince;
            }

            public void setRegionIdProvince(String regionIdProvince) {
                this.regionIdProvince = regionIdProvince;
            }

            public String getRegisterAddrDistrict() {
                return registerAddrDistrict;
            }

            public void setRegisterAddrDistrict(String registerAddrDistrict) {
                this.registerAddrDistrict = registerAddrDistrict;
            }

            public String getBusinessAddrProvince() {
                return businessAddrProvince;
            }

            public void setBusinessAddrProvince(String businessAddrProvince) {
                this.businessAddrProvince = businessAddrProvince;
            }

            public String getRegisterAddrCountry() {
                return registerAddrCountry;
            }

            public void setRegisterAddrCountry(String registerAddrCountry) {
                this.registerAddrCountry = registerAddrCountry;
            }

            public String getBusinessAddrCountry() {
                return businessAddrCountry;
            }

            public void setBusinessAddrCountry(String businessAddrCountry) {
                this.businessAddrCountry = businessAddrCountry;
            }

            public String getBusinessAddrDistrict() {
                return businessAddrDistrict;
            }

            public void setBusinessAddrDistrict(String businessAddrDistrict) {
                this.businessAddrDistrict = businessAddrDistrict;
            }

            public String getBusinessAddrCity() {
                return businessAddrCity;
            }

            public void setBusinessAddrCity(String businessAddrCity) {
                this.businessAddrCity = businessAddrCity;
            }

            public String getRegisterAddrCity() {
                return registerAddrCity;
            }

            public void setRegisterAddrCity(String registerAddrCity) {
                this.registerAddrCity = registerAddrCity;
            }

            public String getRegisterAddrProvince() {
                return registerAddrProvince;
            }

            public void setRegisterAddrProvince(String registerAddrProvince) {
                this.registerAddrProvince = registerAddrProvince;
            }

            public String getSaleRegion() {
                return saleRegion;
            }

            public void setSaleRegion(String saleRegion) {
                this.saleRegion = saleRegion;
            }

            public String getOpenStatus() {
                return openStatus;
            }

            public void setOpenStatus(String openStatus) {
                this.openStatus = openStatus;
            }

            public String getTanentId() {
                return tanentId;
            }

            public void setTanentId(String tanentId) {
                this.tanentId = tanentId;
            }

            public String getContactStatus() {
                return contactStatus;
            }

            public void setContactStatus(String contactStatus) {
                this.contactStatus = contactStatus;
            }

            public String getMarketLevel() {
                return marketLevel;
            }

            public void setMarketLevel(String marketLevel) {
                this.marketLevel = marketLevel;
            }

            public String getLicenceMerged() {
                return licenceMerged;
            }

            public void setLicenceMerged(String licenceMerged) {
                this.licenceMerged = licenceMerged;
            }

            public String getBusiLicenceCode() {
                return busiLicenceCode;
            }

            public void setBusiLicenceCode(String busiLicenceCode) {
                this.busiLicenceCode = busiLicenceCode;
            }

            public String getOrgLicenceCode() {
                return orgLicenceCode;
            }

            public void setOrgLicenceCode(String orgLicenceCode) {
                this.orgLicenceCode = orgLicenceCode;
            }

            public String getRegisterAddrDetail() {
                return registerAddrDetail;
            }

            public void setRegisterAddrDetail(String registerAddrDetail) {
                this.registerAddrDetail = registerAddrDetail;
            }

            public String getTaxLicenceCode() {
                return taxLicenceCode;
            }

            public void setTaxLicenceCode(String taxLicenceCode) {
                this.taxLicenceCode = taxLicenceCode;
            }

            public String getBusinessAddrDetail() {
                return businessAddrDetail;
            }

            public void setBusinessAddrDetail(String businessAddrDetail) {
                this.businessAddrDetail = businessAddrDetail;
            }

            public long getBusiTimeBegin() {
                return busiTimeBegin;
            }

            public void setBusiTimeBegin(long busiTimeBegin) {
                this.busiTimeBegin = busiTimeBegin;
            }

            public long getBusiTimeEnd() {
                return busiTimeEnd;
            }

            public void setBusiTimeEnd(long busiTimeEnd) {
                this.busiTimeEnd = busiTimeEnd;
            }
        }
    }
}
