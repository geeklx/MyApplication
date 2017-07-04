package com.haiersmart.voice.bean;

import java.util.List;

/**
 * Create with Android Studio.
 *
 * @author Hsueh
 * @email i@hsueh.top
 * @date 2017/2/17 15:47
 */

public class TrainResultBean extends BaiduBaseResultBean {

    /**
     * result : {"bot_id":"sac","bot_meta":{"version":"1.0.0","type":"其他","description":"desc"},"views":[{"type":"txt","content":"度秘为您推荐以下优质资源，请选择需要的车次。","list":[{"title":"火车票 \n02月18日   北京 \u2014 北京","summary":"巨头打造，最权威的在线火车票订票平台，支持全国性火车票实时查询及在线预订。","url":"http://kuai.baidu.com/webapp/train/list.html?startdatetime=2017-02-18&startname=北京&arrivename=北京&us=dumi","image":"http://zhanzhang.bj.bcebos.com/files/mis_066541458718757baidukuaixing.png"}]},{"type":"list","list":[{"title":"火车票 \n02月18日   北京 \u2014 北京","summary":"巨头打造，最权威的在线火车票订票平台，支持全国性火车票实时查询及在线预订。","url":"http://kuai.baidu.com/webapp/train/list.html?startdatetime=2017-02-18&startname=北京&arrivename=北京&us=dumi","image":"http://zhanzhang.bj.bcebos.com/files/mis_066541458718757baidukuaixing.png"}]}],"nlu":{"domain":"22","intent":"22","slots":{"end_point":"北京","start_time":"2017-02-18","sugs":"买火车票"}},"speech":{"type":"Text","content":"度秘为您推荐以下优质资源，请选择需要的车次。"}}
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
         * bot_id : sac
         * bot_meta : {"version":"1.0.0","type":"其他","description":"desc"}
         * views : [{"type":"txt","content":"度秘为您推荐以下优质资源，请选择需要的车次。"},{"type":"list","list":[{"title":"火车票 \n02月18日   北京 \u2014 北京","summary":"巨头打造，最权威的在线火车票订票平台，支持全国性火车票实时查询及在线预订。","url":"http://kuai.baidu.com/webapp/train/list.html?startdatetime=2017-02-18&startname=北京&arrivename=北京&us=dumi","image":"http://zhanzhang.bj.bcebos.com/files/mis_066541458718757baidukuaixing.png"}]}]
         * nlu : {"domain":"22","intent":"22","slots":{"end_point":"北京","start_time":"2017-02-18","sugs":"买火车票"}}
         * speech : {"type":"Text","content":"度秘为您推荐以下优质资源，请选择需要的车次。"}
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
             * domain : 22
             * intent : 22
             * slots : {"end_point":"北京","start_time":"2017-02-18","sugs":"买火车票"}
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
                 * end_point : 北京
                 * start_time : 2017-02-18
                 * sugs : 买火车票
                 */

                private String end_point;
                private String start_time;
                private String sugs;

                public String getEnd_point() {
                    return end_point;
                }

                public void setEnd_point(String end_point) {
                    this.end_point = end_point;
                }

                public String getStart_time() {
                    return start_time;
                }

                public void setStart_time(String start_time) {
                    this.start_time = start_time;
                }

                public String getSugs() {
                    return sugs;
                }

                public void setSugs(String sugs) {
                    this.sugs = sugs;
                }
            }
        }

        public static class SpeechBean {
            /**
             * type : Text
             * content : 度秘为您推荐以下优质资源，请选择需要的车次。
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
             * content : 度秘为您推荐以下优质资源，请选择需要的车次。
             * list : [{"title":"火车票 \n02月18日   北京 \u2014 北京","summary":"巨头打造，最权威的在线火车票订票平台，支持全国性火车票实时查询及在线预订。","url":"http://kuai.baidu.com/webapp/train/list.html?startdatetime=2017-02-18&startname=北京&arrivename=北京&us=dumi","image":"http://zhanzhang.bj.bcebos.com/files/mis_066541458718757baidukuaixing.png"}]
             */

            private String type;
            private String content;
            private List<ListBean> list;

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

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * title : 火车票
                 02月18日   北京 — 北京
                 * summary : 巨头打造，最权威的在线火车票订票平台，支持全国性火车票实时查询及在线预订。
                 * url : http://kuai.baidu.com/webapp/train/list.html?startdatetime=2017-02-18&startname=北京&arrivename=北京&us=dumi
                 * image : http://zhanzhang.bj.bcebos.com/files/mis_066541458718757baidukuaixing.png
                 */

                private String title;
                private String summary;
                private String url;
                private String image;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getSummary() {
                    return summary;
                }

                public void setSummary(String summary) {
                    this.summary = summary;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }
            }
        }
    }
}
