package com.example.demo.eventbritedemo.model;

import java.util.List;

public class UserDetailModel {

    private String id;
    private String name;
    private Object first_name;
    private Object last_name;
    private Object image_id;

    private List<EmailsEntity> emails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getFirst_name() {
        return first_name;
    }

    public void setFirst_name(Object first_name) {
        this.first_name = first_name;
    }

    public Object getLast_name() {
        return last_name;
    }

    public void setLast_name(Object last_name) {
        this.last_name = last_name;
    }

    public Object getImage_id() {
        return image_id;
    }

    public void setImage_id(Object image_id) {
        this.image_id = image_id;
    }

    public List<EmailsEntity> getEmails() {
        return emails;
    }

    public void setEmails(List<EmailsEntity> emails) {
        this.emails = emails;
    }

    public static class EmailsEntity {
        private String email;
        private boolean verified;
        private boolean primary;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public boolean isVerified() {
            return verified;
        }

        public void setVerified(boolean verified) {
            this.verified = verified;
        }

        public boolean isPrimary() {
            return primary;
        }

        public void setPrimary(boolean primary) {
            this.primary = primary;
        }
    }
}
