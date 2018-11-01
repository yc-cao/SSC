package com.example.hongkaixiang.kuaihuozedan.net;

public class WebUrlBean {

    /**
     * status : 1
     * content : {"web_url":"http://wap.minpion.com"}
     * error :
     */

    private String status;
    private ContentBean content;
    private String      error;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static class ContentBean {
        /**
         * web_url : http://wap.minpion.com
         */

        private String web_url;

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }
    }
}
