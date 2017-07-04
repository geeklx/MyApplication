package com.haiersmart.voice.bean;

import java.util.List;

/**
 * Create with Android Studio.
 *
 * @author Hsueh
 * @email i@hsueh.top
 * @date 2017/2/13 14:26
 */

public class BaiduCookbookResultBean extends BaiduBaseResultBean {

    /**
     * result : {"bot_id":"cookbook","bot_meta":{"version":"1.0.0","type":"其他","description":"desc"},"views":[{"type":"txt","content":"度秘为你找到如下结果"}],"nlu":{"domain":"cookbook","intent":"cookbook.open","slots":{"dish":"西红柿炒鸡蛋"}},"speech":{"type":"Text","content":"度秘为你找到如下结果"}}
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
         * bot_id : cookbook
         * bot_meta : {"version":"1.0.0","type":"其他","description":"desc"}
         * views : [{"type":"txt","content":"度秘为你找到如下结果"}]
         * nlu : {"domain":"cookbook","intent":"cookbook.open","slots":{"dish":"西红柿炒鸡蛋"}}
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
             * domain : cookbook
             * intent : cookbook.open
             * slots : {"dish":"西红柿炒鸡蛋"}
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
                 * dish : 西红柿炒鸡蛋
                 */

//                material	食材
//                system	菜系
//                dish	菜名
                private String dish;
                private String system;
                private String material;
                private String tag;
                private String season;
                private String flavor;
                private String holiday;
                private String people;
                private String symptom;

                public String getTag() {
                    return tag;
                }

                public void setTag(String tag) {
                    this.tag = tag;
                }

                public String getSeason() {
                    return season;
                }

                public void setSeason(String season) {
                    this.season = season;
                }

                public String getFlavor() {
                    return flavor;
                }

                public void setFlavor(String flavor) {
                    this.flavor = flavor;
                }

                public String getHoliday() {
                    return holiday;
                }

                public void setHoliday(String holiday) {
                    this.holiday = holiday;
                }

                public String getPeople() {
                    return people;
                }

                public void setPeople(String people) {
                    this.people = people;
                }

                public String getSymptom() {
                    return symptom;
                }

                public void setSymptom(String symptom) {
                    this.symptom = symptom;
                }

                public String getSystem() {
                    return system;
                }

                public void setSystem(String system) {
                    this.system = system;
                }

                public String getMaterial() {
                    return material;
                }

                public void setMaterial(String material) {
                    this.material = material;
                }

                public String getDish() {
                    return dish;
                }

                public void setDish(String dish) {
                    this.dish = dish;
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
