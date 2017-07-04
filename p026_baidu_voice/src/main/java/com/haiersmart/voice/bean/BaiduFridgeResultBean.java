package com.haiersmart.voice.bean;

import java.util.List;

/**
 * Create with Android Studio.
 *
 * @author Hsueh
 * @email i@hsueh.top
 * @date 2017/2/13 18:46
 */

public class BaiduFridgeResultBean extends BaiduBaseResultBean {

    /**
     * result : {"bot_id":"fridge","bot_meta":{"version":"1.0.0","type":"其他","description":"desc"},"views":[{"type":"txt","content":"度秘为你找到如下结果"}],"nlu":{"domain":"fridge","intent":"fridge.setting.temperature","slots":{"room":"冷藏室","set_temp":"1"}},"speech":{"type":"Text","content":"度秘为你找到如下结果"}}
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
         * bot_id : fridge
         * bot_meta : {"version":"1.0.0","type":"其他","description":"desc"}
         * views : [{"type":"txt","content":"度秘为你找到如下结果"}]
         * nlu : {"domain":"fridge","intent":"fridge.setting.temperature","slots":{"room":"冷藏室","set_temp":"1"}}
         * speech : {"type":"Text","content":"度秘为你找到如下结果"}
         */

        private String bot_id;
        private BotMetaBean bot_meta;
        private NluBean nlu;
        private SpeechBean speech;
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
             * domain : fridge
             * intent : fridge.setting.temperature
             * slots : {"room":"冷藏室","set_temp":"1"}
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
                 * room : 冷藏室
                 * set_temp : 1
                 */

                private String room;
                private String set_temp;
                private String temp_down;
                private String temp_down_size;
                private String temp_up;
                private String temp_up_size;
                private String timeout;
                private String name;
                private String mode;
                private String close;
                private String temp_negative;
                private String action;
                private String category_2;

                public String getCategory_2() {
                    return category_2;
                }

                public void setCategory_2(String category_2) {
                    this.category_2 = category_2;
                }

                //新鲜
                private String fresh;
                //正常
                private String normal;
                //快过期
                private String expiring;
                //已经过期
                private String expired;

                private String not_expire;


                public String getClose() {
                    return close;
                }

                public void setClose(String close) {
                    this.close = close;
                }

                public String getNot_expire() {
                    return not_expire;
                }

                public void setNot_expire(String not_expire) {
                    this.not_expire = not_expire;
                }

                public String getAction() {
                    return action;
                }

                public void setAction(String action) {
                    this.action = action;
                }

                public String getFresh() {
                    return fresh;
                }

                public void setFresh(String fresh) {
                    this.fresh = fresh;
                }

                public String getNormal() {
                    return normal;
                }

                public void setNormal(String normal) {
                    this.normal = normal;
                }

                public String getExpiring() {
                    return expiring;
                }

                public void setExpiring(String expiring) {
                    this.expiring = expiring;
                }

                public String getExpired() {
                    return expired;
                }

                public void setExpired(String expired) {
                    this.expired = expired;
                }

                public String getTemp_negative() {
                    return temp_negative;
                }

                public void setTemp_negative(String temp_negative) {
                    this.temp_negative = temp_negative;
                }

                public String getMode() {
                    return mode;
                }

                public void setMode(String mode) {
                    this.mode = mode;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getTimeout() {
                    return timeout;
                }

                public void setTimeout(String timeout) {
                    this.timeout = timeout;
                }

                public String getTemp_down_size() {
                    return temp_down_size;
                }

                public void setTemp_down_size(String temp_down_size) {
                    this.temp_down_size = temp_down_size;
                }

                public String getTemp_up() {
                    return temp_up;
                }

                public void setTemp_up(String temp_up) {
                    this.temp_up = temp_up;
                }

                public String getTemp_up_size() {
                    return temp_up_size;
                }

                public void setTemp_up_size(String temp_up_size) {
                    this.temp_up_size = temp_up_size;
                }

                public String getTemp_down() {
                    return temp_down;
                }

                public void setTemp_down(String temp_down) {
                    this.temp_down = temp_down;
                }

                public String getRoom() {
                    return room;
                }

                public void setRoom(String room) {
                    this.room = room;
                }

                public String getSet_temp() {
                    return set_temp;
                }

                public void setSet_temp(String set_temp) {
                    this.set_temp = set_temp;
                }
            }
        }

        public static class SpeechBean {
            /**
             * type : Text
             * content : 度秘为你找到如下结果
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
             * content : 度秘为你找到如下结果
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
