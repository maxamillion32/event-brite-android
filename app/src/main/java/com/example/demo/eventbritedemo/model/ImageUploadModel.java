package com.example.demo.eventbritedemo.model;

/**
 * Created by webonise on 25/05/16.
 */
public class ImageUploadModel {

    private String upload_method;
    private String upload_token;
    private String upload_url;
    private UploadDataEntity upload_data;
    private String file_parameter_name;

    public String getUpload_method() {
        return upload_method;
    }

    public void setUpload_method(String upload_method) {
        this.upload_method = upload_method;
    }

    public String getUpload_token() {
        return upload_token;
    }

    public void setUpload_token(String upload_token) {
        this.upload_token = upload_token;
    }

    public String getUpload_url() {
        return upload_url;
    }

    public void setUpload_url(String upload_url) {
        this.upload_url = upload_url;
    }

    public UploadDataEntity getUpload_data() {
        return upload_data;
    }

    public void setUpload_data(UploadDataEntity upload_data) {
        this.upload_data = upload_data;
    }

    public String getFile_parameter_name() {
        return file_parameter_name;
    }

    public void setFile_parameter_name(String file_parameter_name) {
        this.file_parameter_name = file_parameter_name;
    }

    public static class UploadDataEntity {
        private String AWSAccessKeyId;
        private String bucket;
        private String acl;
        private String key;
        private String signature;
        private String policy;

        public String getAWSAccessKeyId() {
            return AWSAccessKeyId;
        }

        public void setAWSAccessKeyId(String AWSAccessKeyId) {
            this.AWSAccessKeyId = AWSAccessKeyId;
        }

        public String getBucket() {
            return bucket.trim();
        }

        public void setBucket(String bucket) {
            this.bucket = bucket;
        }

        public String getAcl() {
            return acl;
        }

        public void setAcl(String acl) {
            this.acl = acl;
        }

        public String getKey() {
            return key.trim();
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getSignature() {
            return signature.trim();
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getPolicy() {
            return policy.trim();
        }

        public void setPolicy(String policy) {
            this.policy = policy;
        }
    }
}