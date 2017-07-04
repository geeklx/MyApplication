package com.haiersmart.voice.bean;

import java.util.List;

/**
 * 视频相关
 * Created by JefferyLeng on 2017/2/20.
 */
public class BaiduMovieResultBean extends BaiduBaseResultBean {

    /**
     * result : {"hint":["想看欢乐颂电视剧","哪部好看","免费看","韩国真实事件改编电影","家庭动物电影","高智商犯罪悬疑电影","真人童话电影","欧美经典犯罪电影"],"bot_id":"movie_satisfy","bot_meta":{"version":"1.0.0","type":"其他","description":"desc"},"views":[{"type":"txt","content":"这些《欢乐颂》深受好评喔","list":[{"title":"复制贝多芬","summary":"类型：剧情/音乐/传记\t评分：7.3\n导演：阿格涅丝卡·霍兰\n主演：艾德·哈里斯/黛安·克鲁格\n2006-10-19\n简介：本片讲述了\"音乐巨人\"贝多芬丧失听力后的夕阳韵事,旨在披露《命运》交响曲背后不为人知的美丽乐章.身心...","url":"http://duer.baidu.com/midpage/movie_detail?sid=014hy4ds","image":"http://hiphotos.baidu.com/xiaodu/pic/item/622762d0f703918f0d6671dc593d269759eec475.jpg"}]},{"type":"list","list":[{"title":"复制贝多芬","summary":"类型：剧情/音乐/传记\t评分：7.3\n导演：阿格涅丝卡·霍兰\n主演：艾德·哈里斯/黛安·克鲁格\n2006-10-19\n简介：本片讲述了\"音乐巨人\"贝多芬丧失听力后的夕阳韵事,旨在披露《命运》交响曲背后不为人知的美丽乐章.身心...","url":"http://duer.baidu.com/midpage/movie_detail?sid=014hy4ds","image":"http://hiphotos.baidu.com/xiaodu/pic/item/622762d0f703918f0d6671dc593d269759eec475.jpg"}]},{"type":"list","list":[{"title":"喜悦","summary":"类型：剧情\t评分：7.3\n导演：英格玛·伯格曼\n主演：Maj-Britt Nilsson/斯蒂格·奥林/Birger Malmsten\n1950-02-20\n简介：斯迪格·爱立信和玛莎同是交响乐团的小提琴演奏者，在相处中，爱情之花不知不觉在他们心中绽放，两人步入了...","url":"http://duer.baidu.com/midpage/movie_detail?sid=01fgvlq6","image":"http://hiphotos.baidu.com/xiaodu/pic/item/79f0f736afc379317fcacf3fe3c4b74543a91101.jpg"}]}],"nlu":{"domain":"movie_satisfy","intent":"movie","slots":{"FreeNeed":"false","HD":"false","HighScoreNeed":"false","OnlineNeed":"false","PreRelease":"false","Releasing":"false","Title":"欢乐颂"}},"speech":{"type":"Text","content":"这些《欢乐颂》深受好评喔"}}
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
         * hint : ["想看欢乐颂电视剧","哪部好看","免费看","韩国真实事件改编电影","家庭动物电影","高智商犯罪悬疑电影","真人童话电影","欧美经典犯罪电影"]
         * bot_id : movie_satisfy
         * bot_meta : {"version":"1.0.0","type":"其他","description":"desc"}
         * views : [{"type":"txt","content":"这些《欢乐颂》深受好评喔"},{"type":"list","list":[{"title":"复制贝多芬","summary":"类型：剧情/音乐/传记\t评分：7.3\n导演：阿格涅丝卡·霍兰\n主演：艾德·哈里斯/黛安·克鲁格\n2006-10-19\n简介：本片讲述了\"音乐巨人\"贝多芬丧失听力后的夕阳韵事,旨在披露《命运》交响曲背后不为人知的美丽乐章.身心...","url":"http://duer.baidu.com/midpage/movie_detail?sid=014hy4ds","image":"http://hiphotos.baidu.com/xiaodu/pic/item/622762d0f703918f0d6671dc593d269759eec475.jpg"}]},{"type":"list","list":[{"title":"喜悦","summary":"类型：剧情\t评分：7.3\n导演：英格玛·伯格曼\n主演：Maj-Britt Nilsson/斯蒂格·奥林/Birger Malmsten\n1950-02-20\n简介：斯迪格·爱立信和玛莎同是交响乐团的小提琴演奏者，在相处中，爱情之花不知不觉在他们心中绽放，两人步入了...","url":"http://duer.baidu.com/midpage/movie_detail?sid=01fgvlq6","image":"http://hiphotos.baidu.com/xiaodu/pic/item/79f0f736afc379317fcacf3fe3c4b74543a91101.jpg"}]}]
         * nlu : {"domain":"movie_satisfy","intent":"movie","slots":{"FreeNeed":"false","HD":"false","HighScoreNeed":"false","OnlineNeed":"false","PreRelease":"false","Releasing":"false","Title":"欢乐颂"}}
         * speech : {"type":"Text","content":"这些《欢乐颂》深受好评喔"}
         */

        private String bot_id;
        private BotMetaBean bot_meta;
        private NluBean nlu;
        private SpeechBean speech;
        private List<String> hint;
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

        public List<String> getHint() {
            return hint;
        }

        public void setHint(List<String> hint) {
            this.hint = hint;
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
             * domain : movie_satisfy
             * intent : movie
             * slots : {"FreeNeed":"false","HD":"false","HighScoreNeed":"false","OnlineNeed":"false","PreRelease":"false","Releasing":"false","Title":"欢乐颂"}
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
                 * FreeNeed : false
                 * HD : false
                 * HighScoreNeed : false
                 * OnlineNeed : false
                 * PreRelease : false
                 * Releasing : false
                 * Title : 欢乐颂
                 */

                private String FreeNeed;
                private String HD;
                private String HighScoreNeed;
                private String OnlineNeed;
                private String PreRelease;
                private String Releasing;
                private String Title;

                public String getFreeNeed() {
                    return FreeNeed;
                }

                public void setFreeNeed(String FreeNeed) {
                    this.FreeNeed = FreeNeed;
                }

                public String getHD() {
                    return HD;
                }

                public void setHD(String HD) {
                    this.HD = HD;
                }

                public String getHighScoreNeed() {
                    return HighScoreNeed;
                }

                public void setHighScoreNeed(String HighScoreNeed) {
                    this.HighScoreNeed = HighScoreNeed;
                }

                public String getOnlineNeed() {
                    return OnlineNeed;
                }

                public void setOnlineNeed(String OnlineNeed) {
                    this.OnlineNeed = OnlineNeed;
                }

                public String getPreRelease() {
                    return PreRelease;
                }

                public void setPreRelease(String PreRelease) {
                    this.PreRelease = PreRelease;
                }

                public String getReleasing() {
                    return Releasing;
                }

                public void setReleasing(String Releasing) {
                    this.Releasing = Releasing;
                }

                public String getTitle() {
                    return Title;
                }

                public void setTitle(String Title) {
                    this.Title = Title;
                }
            }
        }

        public static class SpeechBean {
            /**
             * type : Text
             * content : 这些《欢乐颂》深受好评喔
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
             * content : 这些《欢乐颂》深受好评喔
             * list : [{"title":"复制贝多芬","summary":"类型：剧情/音乐/传记\t评分：7.3\n导演：阿格涅丝卡·霍兰\n主演：艾德·哈里斯/黛安·克鲁格\n2006-10-19\n简介：本片讲述了\"音乐巨人\"贝多芬丧失听力后的夕阳韵事,旨在披露《命运》交响曲背后不为人知的美丽乐章.身心...","url":"http://duer.baidu.com/midpage/movie_detail?sid=014hy4ds","image":"http://hiphotos.baidu.com/xiaodu/pic/item/622762d0f703918f0d6671dc593d269759eec475.jpg"}]
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
                 * title : 复制贝多芬
                 * summary : 类型：剧情/音乐/传记	评分：7.3
                 导演：阿格涅丝卡·霍兰
                 主演：艾德·哈里斯/黛安·克鲁格
                 2006-10-19
                 简介：本片讲述了"音乐巨人"贝多芬丧失听力后的夕阳韵事,旨在披露《命运》交响曲背后不为人知的美丽乐章.身心...
                 * url : http://duer.baidu.com/midpage/movie_detail?sid=014hy4ds
                 * image : http://hiphotos.baidu.com/xiaodu/pic/item/622762d0f703918f0d6671dc593d269759eec475.jpg
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
