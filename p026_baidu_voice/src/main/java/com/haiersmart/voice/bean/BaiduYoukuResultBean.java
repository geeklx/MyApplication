package com.haiersmart.voice.bean;

import java.util.List;

/**
 * 优酷视频类解析数据
 * Created by JefferyLeng on 2017/3/2.
 */
public class BaiduYoukuResultBean extends BaiduBaseResultBean {

    /**
     * result : {"nlu":{"domain":"FILM","intent":"SEARCH_FILM","slots":{"film_type":"动画"}},"bot_meta":{"version":"1.0.0","type":"其他","description":"desc"},"views":[{"type":"txt","content":"tv"}],"speech":{"type":"Text","content":"tv"}}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * nlu : {"domain":"FILM","intent":"SEARCH_FILM","slots":{"film_type":"动画"}}
         * bot_meta : {"version":"1.0.0","type":"其他","description":"desc"}
         * views : [{"type":"txt","content":"tv"}]
         * speech : {"type":"Text","content":"tv"}
         */

        private NluBean nlu;
        private BotMetaBean bot_meta;
        private SpeechBean speech;
        private List<ViewsBean> views;

        public NluBean getNlu() {
            return nlu;
        }

        public void setNlu(NluBean nlu) {
            this.nlu = nlu;
        }

        public BotMetaBean getBot_meta() {
            return bot_meta;
        }

        public void setBot_meta(BotMetaBean bot_meta) {
            this.bot_meta = bot_meta;
        }

        public SpeechBean getSpeech() {
            return speech;
        }

        public void setSpeech(SpeechBean speech) {
            this.speech = speech;
        }

        public List<ViewsBean> getViews() {
            return views;
        }

        public void setViews(List<ViewsBean> views) {
            this.views = views;
        }

        public static class NluBean {
            /**
             * domain : FILM
             * intent : SEARCH_FILM
             * slots : {"film_type":"动画"}
             */

            private String domain;
            private String intent;
            private SlotsBean slots;

            public String getDomain() {
                return domain;
            }

            public void setDomain(String domain) {
                this.domain = domain;
            }

            public String getIntent() {
                return intent;
            }

            public void setIntent(String intent) {
                this.intent = intent;
            }

            public SlotsBean getSlots() {
                return slots;
            }

            public void setSlots(SlotsBean slots) {
                this.slots = slots;
            }

            public static class SlotsBean {
                /**
                 * film_type : 动画
                 */

                private String film_type;

                public String getFilm_type() {
                    return film_type;
                }

                public void setFilm_type(String film_type) {
                    this.film_type = film_type;
                }
            }
        }

        public static class BotMetaBean {
            /**
             * version : 1.0.0
             * type : 其他
             * description : desc
             */

            private String version;
            private String type;
            private String description;

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

        public static class SpeechBean {
            /**
             * type : Text
             * content : tv
             */

            private String type;
            private String content;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public static class ViewsBean {
            /**
             * type : txt
             * content : tv
             */

            private String type;
            private String content;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
