package com.app.itserv.jparser;

import java.util.List;

/**
 * Created by EVA on 2017/9/22.
 */
public class PhoneListBean {

    /**
     * contacts : [{"name":"靳春雨","owner":"zhouzuowei","phone":"13718681217"}]
     * phone : 18311401842
     * username : zhouzuowei
     */

    private String phone;
    private String username;
    private List<ContactsBean> contacts;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ContactsBean> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactsBean> contacts) {
        this.contacts = contacts;
    }

    public static class ContactsBean {
        /**
         * name : 靳春雨
         * owner : zhouzuowei
         * phone : 13718681217
         */

        private String name;
        private String owner;
        private String phone;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
