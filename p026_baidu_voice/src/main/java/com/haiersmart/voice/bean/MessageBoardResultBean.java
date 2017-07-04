package com.haiersmart.voice.bean;

import java.util.List;

/**
 * Create with Android Studio.
 *
 * @author Hsueh
 * @email i@hsueh.top
 * @date 2017/2/26 14:36
 */

public class MessageBoardResultBean extends BaiduBaseResultBean {

    /**
     * result : {"bot_id":"user_profile","bot_meta":{"version":"1.0.0","type":"其他","description":"desc"},"views":[{"type":"txt","content":"你想记录什么内容?"}],"nlu":{"domain":"memory","intent":"memory","slots":{"keyword":"留言","__id__":"876cc15bc42141979ff1c3ff1363b67c","intent":"store"}},"speech":{"type":"Text","content":"你想记录什么内容?"},"directives":null}
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
         * bot_id : user_profile
         * bot_meta : {"version":"1.0.0","type":"其他","description":"desc"}
         * views : [{"type":"txt","content":"你想记录什么内容?"}]
         * nlu : {"domain":"memory","intent":"memory","slots":{"keyword":"留言","__id__":"876cc15bc42141979ff1c3ff1363b67c","intent":"store"}}
         * speech : {"type":"Text","content":"你想记录什么内容?"}
         * directives : null
         */

        private String bot_id;
        private BotMetaBean bot_meta;
        private NluBean nlu;
        private SpeechBean speech;
        private Object directives;
        private List<ViewsBean> views;

        public String getBot_id() {
            return bot_id;
        }

        public void setBot_id(String bot_id) {
            this.bot_id = bot_id;
        }

        public BotMetaBean getBot_meta() {
            return bot_meta;
        }

        public void setBot_meta(BotMetaBean bot_meta) {
            this.bot_meta = bot_meta;
        }

        public NluBean getNlu() {
            return nlu;
        }

        public void setNlu(NluBean nlu) {
            this.nlu = nlu;
        }

        public SpeechBean getSpeech() {
            return speech;
        }

        public void setSpeech(SpeechBean speech) {
            this.speech = speech;
        }

        public Object getDirectives() {
            return directives;
        }

        public void setDirectives(Object directives) {
            this.directives = directives;
        }

        public List<ViewsBean> getViews() {
            return views;
        }

        public void setViews(List<ViewsBean> views) {
            this.views = views;
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

        public static class NluBean {
            /**
             * domain : memory
             * intent : memory
             * slots : {"keyword":"留言","__id__":"876cc15bc42141979ff1c3ff1363b67c","intent":"store"}
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
                 * keyword : 留言
                 * __id__ : 876cc15bc42141979ff1c3ff1363b67c
                 * intent : store
                 */

                private String keyword;
                private String __id__;
                private String intent;
                private String memorycontent;

                public String getMemorycontent() {
                    return memorycontent;
                }

                public void setMemorycontent(String memorycontent) {
                    this.memorycontent = memorycontent;
                }

                public String getKeyword() {
                    return keyword;
                }

                public void setKeyword(String keyword) {
                    this.keyword = keyword;
                }

                public String get__id__() {
                    return __id__;
                }

                public void set__id__(String __id__) {
                    this.__id__ = __id__;
                }

                public String getIntent() {
                    return intent;
                }

                public void setIntent(String intent) {
                    this.intent = intent;
                }
            }
        }

        public static class SpeechBean {
            /**
             * type : Text
             * content : 你想记录什么内容?
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
             * content : 你想记录什么内容?
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
