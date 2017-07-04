package com.haiersmart.voice.bean;

import java.util.List;

/**
 * Create with Android Studio.
 *
 * @author Hsueh
 * @email i@hsueh.top
 * @date 2017/2/12 15:28
 */

public class BaiduOtherResultBeanOld extends BaiduBaseResultBean {


    /**
     * result : {"bot_id":"duer_music","bot_meta":{"description":"返回音乐类结果","version":"1.0.0","type":"音乐"},"views":[{"type":"list","list":[{"title":"周杰伦是谁啊?_百度知道","summary":"真好笑,你不是叫叶湘伦么?看过不能说的秘密,会不认识杰伦?笑话","url":"http://zhidao.baidu.com/question/326322529.html","image":"http://t10.baidu.com/it/u=816128931,2214506684&fm=58"},{"title":"周杰伦_百度百科","summary":"周杰伦（Jay Chou），1979年1月18日出生于台湾省新北市，中国台湾流行乐男歌手、音乐人、演员、导演、编剧、监制、商人。2000年发行首张个人专辑《Jay》。2001年发行的专辑《范特西》奠定其融合中西方音乐的风格。2002年举行\u201cThe One\u201d世界巡回演唱会。2003年成为美国《时代周刊》封面人物。2004年获得世界音乐大奖中国区最畅销艺人奖。2005年凭借动作片《头文字D》获得台湾电影金马奖、香港电影金像奖最佳新人奖。2006年起连续三年获得世界音乐大奖中国区最畅销艺人奖。2007年自编自导的文艺片《不能说的秘密》获得台湾电影金马奖年度台湾杰出电影奖。2008年凭借歌曲《青花瓷》获得第19届金曲奖最佳作曲人奖。2009年入选美国CNN评出的\u201c25位亚洲最具影响力的人物\u201d；同年凭借专辑《魔杰座》获得第20届金曲奖最佳国语男歌手奖。2010年入选美国《Fast Company》评","url":"http://baike.baidu.com/item/%E5%91%A8%E6%9D%B0%E4%BC%A6/129156?fr=aladdin","image":"http://t10.baidu.com/it/u=816128931,2214506684&fm=58"},{"title":"周杰伦巅峰到底有多强?下一个\u201c周杰伦\u201d又是谁?-搜狐","summary":"2010,出道十年的周杰伦推出专辑《跨时代》。为此,我在几个网站发布相关乐评,对每首歌详尽分析...","url":"http://m.sohu.com/n/417318760/","image":"http://cdn01.baidu-img.cn/timg?wisealaddin&size=u200_200&quality=80&sec=1486898239&di=0537663fef75eb8c246d7c1af33b771b&src=http%3A%2F%2Fi7.baidu.com%2Fit%2Fu%3D3999816903%2C1267214845%26fm%3D78%26s%3D07503BCA844287F79DE0683803008092"},{"title":"众明星对周杰伦的评价_百度文库","summary":"宋柯---如果你现在非让我说现在谁的唱片可以破百万 那只有周杰伦。 许茹芸---周杰伦走红是必然的。 王刚...","url":"http://wenku.baidu.com/view/e939668183d049649b665829.html"},{"title":"周杰伦和权志龙,谁才是亚洲第一歌手???_台湾_天涯论坛","summary":"周杰伦在华人地区牛逼,GD在世界范围牛逼,后者为后起之秀,潜力巨大,实话实说。  举报 | 收藏 | 4楼 |...","url":"http://bbs.tianya.cn/m/post-333-358532-1.shtml","image":"http://cdn01.baidu-img.cn/timg?wisealaddin&size=u200_200&quality=80&sec=1486898239&di=1354e5eabbe243bd986c0ef5fdc2758a&src=http%3A%2F%2Fi8.baidu.com%2Fit%2Fu%3D3056594164%2C3961482137%26fm%3D78%26s%3D2DC0B240208E0D5FD2C9B81F0300C0C1"},{"title":"周杰伦退位谁接班? - 知乎","summary":"曾经拒绝听周杰伦的歌,因为太小想要与众不同,因为周围人都在听他的歌。 可是你想与不想,他的歌就在那个...","url":"http://www.zhihu.com/question/27124158"},{"title":"周董是谁?_百度知道","summary":"是周杰伦 小天王周杰伦有个挺有分量的称呼\u201c周董\u201d。对于这个称呼的来源,有多种版本的解释。 一...","url":"http://zhidao.baidu.com/question/42044561.html"},{"title":"周杰伦是谁?_nba吧_百度贴吧","summary":"周杰伦是谁? 只看楼主 收藏 回复依然我忙  球队核心 9  回复 1楼 2009-08-10 00:04 举报 |依然...","url":"http://tieba.baidu.com/p/625034855"}]}],"directives":[{"header":{"namespace":"AudioPlayer","name":"Play"},"payload":{"play_behavior":"REPLACE_ALL","audio_item":{"audio_item_id":"156","stream":{"url":"http://yinyueshiting.baidu.com/data2/music/124088643/124088643.mp3?xcode=57462f29cfc176f86a37d80a2c02fc5b","stream_format":"AUDIO_MP3","offset_ms":0,"token":"156","progress_report_interval_ms":10000}}}}],"nlu":{"domain":"music","intent":"music","slots":{"action_type":"1","singer":"周杰伦"}},"resource":{"type":"music_ref","data":{"api":{"method":"GET","url":"http: //s.xiaodu.baidu.com/v20161223/resource/music?user_id=888"}}},"speech":{"type":"Text","content":"为您播放周杰伦的歌曲"}}
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
         * bot_id : duer_music
         * bot_meta : {"description":"返回音乐类结果","version":"1.0.0","type":"音乐"}
         * views : [{"type":"list","list":[{"title":"周杰伦是谁啊?_百度知道","summary":"真好笑,你不是叫叶湘伦么?看过不能说的秘密,会不认识杰伦?笑话","url":"http://zhidao.baidu.com/question/326322529.html"},{"title":"周杰伦_百度百科","summary":"周杰伦（Jay Chou），1979年1月18日出生于台湾省新北市，中国台湾流行乐男歌手、音乐人、演员、导演、编剧、监制、商人。2000年发行首张个人专辑《Jay》。2001年发行的专辑《范特西》奠定其融合中西方音乐的风格。2002年举行\u201cThe One\u201d世界巡回演唱会。2003年成为美国《时代周刊》封面人物。2004年获得世界音乐大奖中国区最畅销艺人奖。2005年凭借动作片《头文字D》获得台湾电影金马奖、香港电影金像奖最佳新人奖。2006年起连续三年获得世界音乐大奖中国区最畅销艺人奖。2007年自编自导的文艺片《不能说的秘密》获得台湾电影金马奖年度台湾杰出电影奖。2008年凭借歌曲《青花瓷》获得第19届金曲奖最佳作曲人奖。2009年入选美国CNN评出的\u201c25位亚洲最具影响力的人物\u201d；同年凭借专辑《魔杰座》获得第20届金曲奖最佳国语男歌手奖。2010年入选美国《Fast Company》评","url":"http://baike.baidu.com/item/%E5%91%A8%E6%9D%B0%E4%BC%A6/129156?fr=aladdin","image":"http://t10.baidu.com/it/u=816128931,2214506684&fm=58"},{"title":"周杰伦巅峰到底有多强?下一个\u201c周杰伦\u201d又是谁?-搜狐","summary":"2010,出道十年的周杰伦推出专辑《跨时代》。为此,我在几个网站发布相关乐评,对每首歌详尽分析...","url":"http://m.sohu.com/n/417318760/","image":"http://cdn01.baidu-img.cn/timg?wisealaddin&size=u200_200&quality=80&sec=1486898239&di=0537663fef75eb8c246d7c1af33b771b&src=http%3A%2F%2Fi7.baidu.com%2Fit%2Fu%3D3999816903%2C1267214845%26fm%3D78%26s%3D07503BCA844287F79DE0683803008092"},{"title":"众明星对周杰伦的评价_百度文库","summary":"宋柯---如果你现在非让我说现在谁的唱片可以破百万 那只有周杰伦。 许茹芸---周杰伦走红是必然的。 王刚...","url":"http://wenku.baidu.com/view/e939668183d049649b665829.html"},{"title":"周杰伦和权志龙,谁才是亚洲第一歌手???_台湾_天涯论坛","summary":"周杰伦在华人地区牛逼,GD在世界范围牛逼,后者为后起之秀,潜力巨大,实话实说。  举报 | 收藏 | 4楼 |...","url":"http://bbs.tianya.cn/m/post-333-358532-1.shtml","image":"http://cdn01.baidu-img.cn/timg?wisealaddin&size=u200_200&quality=80&sec=1486898239&di=1354e5eabbe243bd986c0ef5fdc2758a&src=http%3A%2F%2Fi8.baidu.com%2Fit%2Fu%3D3056594164%2C3961482137%26fm%3D78%26s%3D2DC0B240208E0D5FD2C9B81F0300C0C1"},{"title":"周杰伦退位谁接班? - 知乎","summary":"曾经拒绝听周杰伦的歌,因为太小想要与众不同,因为周围人都在听他的歌。 可是你想与不想,他的歌就在那个...","url":"http://www.zhihu.com/question/27124158"},{"title":"周董是谁?_百度知道","summary":"是周杰伦 小天王周杰伦有个挺有分量的称呼\u201c周董\u201d。对于这个称呼的来源,有多种版本的解释。 一...","url":"http://zhidao.baidu.com/question/42044561.html"},{"title":"周杰伦是谁?_nba吧_百度贴吧","summary":"周杰伦是谁? 只看楼主 收藏 回复依然我忙  球队核心 9  回复 1楼 2009-08-10 00:04 举报 |依然...","url":"http://tieba.baidu.com/p/625034855"}]}]
         * directives : [{"header":{"namespace":"AudioPlayer","name":"Play"},"payload":{"play_behavior":"REPLACE_ALL","audio_item":{"audio_item_id":"156","stream":{"url":"http://yinyueshiting.baidu.com/data2/music/124088643/124088643.mp3?xcode=57462f29cfc176f86a37d80a2c02fc5b","stream_format":"AUDIO_MP3","offset_ms":0,"token":"156","progress_report_interval_ms":10000}}}}]
         * nlu : {"domain":"music","intent":"music","slots":{"action_type":"1","singer":"周杰伦"}}
         * resource : {"type":"music_ref","data":{"api":{"method":"GET","url":"http: //s.xiaodu.baidu.com/v20161223/resource/music?user_id=888"}}}
         * speech : {"type":"Text","content":"为您播放周杰伦的歌曲"}
         */

        private String bot_id;
        private BotMetaBean bot_meta;
        private NluBean nlu;
        private ResourceBean resource;
        private SpeechBean speech;
        private List<ViewsBean> views;
        private List<DirectivesBean> directives;

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

        public ResourceBean getResource() {
            return resource;
        }

        public void setResource(ResourceBean resource) {
            this.resource = resource;
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

        public List<DirectivesBean> getDirectives() {
            return directives;
        }

        public void setDirectives(List<DirectivesBean> directives) {
            this.directives = directives;
        }

        public static class BotMetaBean {
            /**
             * description : 返回音乐类结果
             * version : 1.0.0
             * type : 音乐
             */

            private String description;
            private String version;
            private String type;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

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
        }

        public static class NluBean {
            /**
             * domain : music
             * intent : music
             * slots : {"action_type":"1","singer":"周杰伦"}
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
                 * action_type : 1
                 * singer : 周杰伦
                 */

                private String action_type;
                private String singer;

                public String getAction_type() {
                    return action_type;
                }

                public void setAction_type(String action_type) {
                    this.action_type = action_type;
                }

                public String getSinger() {
                    return singer;
                }

                public void setSinger(String singer) {
                    this.singer = singer;
                }
            }
        }

        public static class ResourceBean {
            /**
             * type : music_ref
             * data : {"api":{"method":"GET","url":"http: //s.xiaodu.baidu.com/v20161223/resource/music?user_id=888"}}
             */

            private String type;
            private DataBean data;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public DataBean getData() {
                return data;
            }

            public void setData(DataBean data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * api : {"method":"GET","url":"http: //s.xiaodu.baidu.com/v20161223/resource/music?user_id=888"}
                 */

                private ApiBean api;

                public ApiBean getApi() {
                    return api;
                }

                public void setApi(ApiBean api) {
                    this.api = api;
                }

                public static class ApiBean {
                    /**
                     * method : GET
                     * url : http: //s.xiaodu.baidu.com/v20161223/resource/music?user_id=888
                     */

                    private String method;
                    private String url;

                    public String getMethod() {
                        return method;
                    }

                    public void setMethod(String method) {
                        this.method = method;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }
                }
            }
        }

        public static class SpeechBean {
            /**
             * type : Text
             * content : 为您播放周杰伦的歌曲
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
             * type : list
             * list : [{"title":"周杰伦是谁啊?_百度知道","summary":"真好笑,你不是叫叶湘伦么?看过不能说的秘密,会不认识杰伦?笑话","url":"http://zhidao.baidu.com/question/326322529.html"},{"title":"周杰伦_百度百科","summary":"周杰伦（Jay Chou），1979年1月18日出生于台湾省新北市，中国台湾流行乐男歌手、音乐人、演员、导演、编剧、监制、商人。2000年发行首张个人专辑《Jay》。2001年发行的专辑《范特西》奠定其融合中西方音乐的风格。2002年举行\u201cThe One\u201d世界巡回演唱会。2003年成为美国《时代周刊》封面人物。2004年获得世界音乐大奖中国区最畅销艺人奖。2005年凭借动作片《头文字D》获得台湾电影金马奖、香港电影金像奖最佳新人奖。2006年起连续三年获得世界音乐大奖中国区最畅销艺人奖。2007年自编自导的文艺片《不能说的秘密》获得台湾电影金马奖年度台湾杰出电影奖。2008年凭借歌曲《青花瓷》获得第19届金曲奖最佳作曲人奖。2009年入选美国CNN评出的\u201c25位亚洲最具影响力的人物\u201d；同年凭借专辑《魔杰座》获得第20届金曲奖最佳国语男歌手奖。2010年入选美国《Fast Company》评","url":"http://baike.baidu.com/item/%E5%91%A8%E6%9D%B0%E4%BC%A6/129156?fr=aladdin","image":"http://t10.baidu.com/it/u=816128931,2214506684&fm=58"},{"title":"周杰伦巅峰到底有多强?下一个\u201c周杰伦\u201d又是谁?-搜狐","summary":"2010,出道十年的周杰伦推出专辑《跨时代》。为此,我在几个网站发布相关乐评,对每首歌详尽分析...","url":"http://m.sohu.com/n/417318760/","image":"http://cdn01.baidu-img.cn/timg?wisealaddin&size=u200_200&quality=80&sec=1486898239&di=0537663fef75eb8c246d7c1af33b771b&src=http%3A%2F%2Fi7.baidu.com%2Fit%2Fu%3D3999816903%2C1267214845%26fm%3D78%26s%3D07503BCA844287F79DE0683803008092"},{"title":"众明星对周杰伦的评价_百度文库","summary":"宋柯---如果你现在非让我说现在谁的唱片可以破百万 那只有周杰伦。 许茹芸---周杰伦走红是必然的。 王刚...","url":"http://wenku.baidu.com/view/e939668183d049649b665829.html"},{"title":"周杰伦和权志龙,谁才是亚洲第一歌手???_台湾_天涯论坛","summary":"周杰伦在华人地区牛逼,GD在世界范围牛逼,后者为后起之秀,潜力巨大,实话实说。  举报 | 收藏 | 4楼 |...","url":"http://bbs.tianya.cn/m/post-333-358532-1.shtml","image":"http://cdn01.baidu-img.cn/timg?wisealaddin&size=u200_200&quality=80&sec=1486898239&di=1354e5eabbe243bd986c0ef5fdc2758a&src=http%3A%2F%2Fi8.baidu.com%2Fit%2Fu%3D3056594164%2C3961482137%26fm%3D78%26s%3D2DC0B240208E0D5FD2C9B81F0300C0C1"},{"title":"周杰伦退位谁接班? - 知乎","summary":"曾经拒绝听周杰伦的歌,因为太小想要与众不同,因为周围人都在听他的歌。 可是你想与不想,他的歌就在那个...","url":"http://www.zhihu.com/question/27124158"},{"title":"周董是谁?_百度知道","summary":"是周杰伦 小天王周杰伦有个挺有分量的称呼\u201c周董\u201d。对于这个称呼的来源,有多种版本的解释。 一...","url":"http://zhidao.baidu.com/question/42044561.html"},{"title":"周杰伦是谁?_nba吧_百度贴吧","summary":"周杰伦是谁? 只看楼主 收藏 回复依然我忙  球队核心 9  回复 1楼 2009-08-10 00:04 举报 |依然...","url":"http://tieba.baidu.com/p/625034855"}]
             */

            private String type;
            private List<ListBean> list;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * title : 周杰伦是谁啊?_百度知道
                 * summary : 真好笑,你不是叫叶湘伦么?看过不能说的秘密,会不认识杰伦?笑话
                 * url : http://zhidao.baidu.com/question/326322529.html
                 * image : http://t10.baidu.com/it/u=816128931,2214506684&fm=58
                 */

                private String title;
                private String summary;
                private String url;
                private String image;
                private String src;

                public String getSrc() {
                    return src;
                }

                public void setSrc(String src) {
                    this.src = src;
                }

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

        public static class DirectivesBean {
            /**
             * header : {"namespace":"AudioPlayer","name":"Play"}
             * payload : {"play_behavior":"REPLACE_ALL","audio_item":{"audio_item_id":"156","stream":{"url":"http://yinyueshiting.baidu.com/data2/music/124088643/124088643.mp3?xcode=57462f29cfc176f86a37d80a2c02fc5b","stream_format":"AUDIO_MP3","offset_ms":0,"token":"156","progress_report_interval_ms":10000}}}
             */

            private HeaderBean header;
            private PayloadBean payload;

            public HeaderBean getHeader() {
                return header;
            }

            public void setHeader(HeaderBean header) {
                this.header = header;
            }

            public PayloadBean getPayload() {
                return payload;
            }

            public void setPayload(PayloadBean payload) {
                this.payload = payload;
            }

            public static class HeaderBean {
                /**
                 * namespace : AudioPlayer
                 * name : Play
                 */

                private String namespace;
                private String name;

                public String getNamespace() {
                    return namespace;
                }

                public void setNamespace(String namespace) {
                    this.namespace = namespace;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            public static class PayloadBean {
                /**
                 * play_behavior : REPLACE_ALL
                 * audio_item : {"audio_item_id":"156","stream":{"url":"http://yinyueshiting.baidu.com/data2/music/124088643/124088643.mp3?xcode=57462f29cfc176f86a37d80a2c02fc5b","stream_format":"AUDIO_MP3","offset_ms":0,"token":"156","progress_report_interval_ms":10000}}
                 */

                private String play_behavior;
                private AudioItemBean audio_item;

                public String getPlay_behavior() {
                    return play_behavior;
                }

                public void setPlay_behavior(String play_behavior) {
                    this.play_behavior = play_behavior;
                }

                public AudioItemBean getAudio_item() {
                    return audio_item;
                }

                public void setAudio_item(AudioItemBean audio_item) {
                    this.audio_item = audio_item;
                }

                public static class AudioItemBean {
                    /**
                     * audio_item_id : 156
                     * stream : {"url":"http://yinyueshiting.baidu.com/data2/music/124088643/124088643.mp3?xcode=57462f29cfc176f86a37d80a2c02fc5b","stream_format":"AUDIO_MP3","offset_ms":0,"token":"156","progress_report_interval_ms":10000}
                     */

                    private String audio_item_id;
                    private StreamBean stream;

                    public String getAudio_item_id() {
                        return audio_item_id;
                    }

                    public void setAudio_item_id(String audio_item_id) {
                        this.audio_item_id = audio_item_id;
                    }

                    public StreamBean getStream() {
                        return stream;
                    }

                    public void setStream(StreamBean stream) {
                        this.stream = stream;
                    }

                    public static class StreamBean {
                        /**
                         * url : http://yinyueshiting.baidu.com/data2/music/124088643/124088643.mp3?xcode=57462f29cfc176f86a37d80a2c02fc5b
                         * stream_format : AUDIO_MP3
                         * offset_ms : 0
                         * token : 156
                         * progress_report_interval_ms : 10000
                         */

                        private String url;
                        private String stream_format;
                        private int offset_ms;
                        private String token;
                        private int progress_report_interval_ms;

                        public String getUrl() {
                            return url;
                        }

                        public void setUrl(String url) {
                            this.url = url;
                        }

                        public String getStream_format() {
                            return stream_format;
                        }

                        public void setStream_format(String stream_format) {
                            this.stream_format = stream_format;
                        }

                        public int getOffset_ms() {
                            return offset_ms;
                        }

                        public void setOffset_ms(int offset_ms) {
                            this.offset_ms = offset_ms;
                        }

                        public String getToken() {
                            return token;
                        }

                        public void setToken(String token) {
                            this.token = token;
                        }

                        public int getProgress_report_interval_ms() {
                            return progress_report_interval_ms;
                        }

                        public void setProgress_report_interval_ms(int progress_report_interval_ms) {
                            this.progress_report_interval_ms = progress_report_interval_ms;
                        }
                    }
                }
            }
        }
    }
}
