package com.haiersmart.voice.bean;

import java.util.List;

/**
 * Created with Android Studio.
 *
 * @author : Hsueh
 * @email : i@hsueh.top
 * @date : 2017-03-01 16:12
 */
public class TimerResultBean extends BaiduBaseResultBean {


    /**
     * result : {"views":[{"type":"txt","content":"未登录"}],"speech":{"type":"Text","content":"未登录"},"bot_id":"remind","bot_meta":{"version":"1.0.0","type":"其他","description":"desc"},"nlu":{"domain":"remind","intent":"timer","slots":{"intent":"create","minute":"15","__intent__":"timer"}}}
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
         * views : [{"type":"txt","content":"未登录"}]
         * speech : {"type":"Text","content":"未登录"}
         * bot_id : remind
         * bot_meta : {"version":"1.0.0","type":"其他","description":"desc"}
         * nlu : {"domain":"remind","intent":"timer","slots":{"intent":"create","minute":"15","__intent__":"timer"}}
         */

        private SpeechBean speech;
        private String bot_id;
        private BotMetaBean bot_meta;
        private NluBean nlu;
        private List<ViewsBean> views;

        public SpeechBean getSpeech() {
            return speech;
        }

        public void setSpeech(SpeechBean speech) {
            this.speech = speech;
        }

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

        public List<ViewsBean> getViews() {
            return views;
        }

        public void setViews(List<ViewsBean> views) {
            this.views = views;
        }

        public static class SpeechBean {
            /**
             * type : Text
             * content : 未登录
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
             * domain : remind
             * intent : timer
             * slots : {"intent":"create","minute":"15","__intent__":"timer"}
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
                 * intent : create
                 * minute : 15
                 * __intent__ : timer
                 */

                private String intent;
                private String second;
                private String minute;
                private String hour;
                private String __intent__;

                public String getSecond() {
                    return second;
                }

                public void setSecond(String second) {
                    this.second = second;
                }

                public String getHour() {
                    return hour;
                }

                public void setHour(String hour) {
                    this.hour = hour;
                }

                public String getIntent() {
                    return intent;
                }

                public void setIntent(String intent) {
                    this.intent = intent;
                }

                public String getMinute() {
                    return minute;
                }

                public void setMinute(String minute) {
                    this.minute = minute;
                }

                public String get__intent__() {
                    return __intent__;
                }

                public void set__intent__(String __intent__) {
                    this.__intent__ = __intent__;
                }
            }
        }

        public static class ViewsBean {
            /**
             * type : txt
             * content : 未登录
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
