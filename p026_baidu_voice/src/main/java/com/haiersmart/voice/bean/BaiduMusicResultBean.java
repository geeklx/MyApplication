package com.haiersmart.voice.bean;

import java.util.List;

/**
 * Create with Android Studio.
 *
 * @author Hsueh
 * @email i@hsueh.top
 * @date 2017/2/10 20:24
 */

public class BaiduMusicResultBean extends BaiduBaseResultBean {


    /**
     * result : {"directives":[{"header":{"message_id":"14851538172760","name":"Play","namespace":"AudioPlayer"},"payload":{"audio_item":{"audio_item_id":"846803671","stream":{"offset_ms":0,"progress_report_interval_ms":1000,"stream_format":"AUDIO_MP3","token":"846803671","url":"http://zhangmenshiting.baidu.com/data2/music/64333976/64333976.mp3?xcode=0411b74c6de0c453a0d51cfc739c1004"}},"play_behavior":"REPLACE_ALL"}}],"resource":{"data":{"api":{"method":"GET","url":"http://s.xiaodu.baidu.com/v20161223/resource/music?user_id=DAFABB5EBE29BC13C7CBAADBEF6BD166|234418820138768"}},"type":"music_ref"},"speech":{"content":"周杰伦 晴天","message_id":"14851538172760","type":"Text"},"bot_id":"audio_music","bot_meta":{"version":"1.0.0","type":"其他","description":"desc"},"views":[{"type":"txt","content":"周杰伦 晴天"}],"nlu":{"domain":"audio.music","intent":"audio.music.play","slots":{"singer":"周杰伦","unit":"歌曲"}}}
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
         * directives : [{"header":{"message_id":"14851538172760","name":"Play","namespace":"AudioPlayer"},"payload":{"audio_item":{"audio_item_id":"846803671","stream":{"offset_ms":0,"progress_report_interval_ms":1000,"stream_format":"AUDIO_MP3","token":"846803671","url":"http://zhangmenshiting.baidu.com/data2/music/64333976/64333976.mp3?xcode=0411b74c6de0c453a0d51cfc739c1004"}},"play_behavior":"REPLACE_ALL"}}]
         * resource : {"data":{"api":{"method":"GET","url":"http://s.xiaodu.baidu.com/v20161223/resource/music?user_id=DAFABB5EBE29BC13C7CBAADBEF6BD166|234418820138768"}},"type":"music_ref"}
         * speech : {"content":"周杰伦 晴天","message_id":"14851538172760","type":"Text"}
         * bot_id : audio_music
         * bot_meta : {"version":"1.0.0","type":"其他","description":"desc"}
         * views : [{"type":"txt","content":"周杰伦 晴天"}]
         * nlu : {"domain":"audio.music","intent":"audio.music.play","slots":{"singer":"周杰伦","unit":"歌曲"}}
         */

        private ResourceBean resource;
        private SpeechBean speech;
        private String bot_id;
        private BotMetaBean bot_meta;
        private NluBean nlu;
        private List<DirectivesBean> directives;
        private List<ViewsBean> views;

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

        public List<DirectivesBean> getDirectives() {
            return directives;
        }

        public void setDirectives(List<DirectivesBean> directives) {
            this.directives = directives;
        }

        public List<ViewsBean> getViews() {
            return views;
        }

        public void setViews(List<ViewsBean> views) {
            this.views = views;
        }

        public static class ResourceBean {
            /**
             * data : {"api":{"method":"GET","url":"http://s.xiaodu.baidu.com/v20161223/resource/music?user_id=DAFABB5EBE29BC13C7CBAADBEF6BD166|234418820138768"}}
             * type : music_ref
             */

            private DataBean data;
            private String type;

            public DataBean getData() {
                return data;
            }

            public void setData(DataBean data) {
                this.data = data;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public static class DataBean {
                /**
                 * api : {"method":"GET","url":"http://s.xiaodu.baidu.com/v20161223/resource/music?user_id=DAFABB5EBE29BC13C7CBAADBEF6BD166|234418820138768"}
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
                     * url : http://s.xiaodu.baidu.com/v20161223/resource/music?user_id=DAFABB5EBE29BC13C7CBAADBEF6BD166|234418820138768
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
             * content : 周杰伦 晴天
             * message_id : 14851538172760
             * type : Text
             */

            private String content;
            private String message_id;
            private String type;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getMessage_id() {
                return message_id;
            }

            public void setMessage_id(String message_id) {
                this.message_id = message_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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
             * domain : audio.music
             * intent : audio.music.play
             * slots : {"singer":"周杰伦","unit":"歌曲"}
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
                 * singer : 周杰伦
                 * unit : 歌曲
                 */

                private String singer;
                private String unit;

                public String getSinger() {
                    return singer;
                }

                public void setSinger(String singer) {
                    this.singer = singer;
                }

                public String getUnit() {
                    return unit;
                }

                public void setUnit(String unit) {
                    this.unit = unit;
                }
            }
        }

        public static class DirectivesBean {
            /**
             * header : {"message_id":"14851538172760","name":"Play","namespace":"AudioPlayer"}
             * payload : {"audio_item":{"audio_item_id":"846803671","stream":{"offset_ms":0,"progress_report_interval_ms":1000,"stream_format":"AUDIO_MP3","token":"846803671","url":"http://zhangmenshiting.baidu.com/data2/music/64333976/64333976.mp3?xcode=0411b74c6de0c453a0d51cfc739c1004"}},"play_behavior":"REPLACE_ALL"}
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
                 * message_id : 14851538172760
                 * name : Play
                 * namespace : AudioPlayer
                 */

                private String message_id;
                private String name;
                private String namespace;

                public String getMessage_id() {
                    return message_id;
                }

                public void setMessage_id(String message_id) {
                    this.message_id = message_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getNamespace() {
                    return namespace;
                }

                public void setNamespace(String namespace) {
                    this.namespace = namespace;
                }
            }

            public static class PayloadBean {
                /**
                 * audio_item : {"audio_item_id":"846803671","stream":{"offset_ms":0,"progress_report_interval_ms":1000,"stream_format":"AUDIO_MP3","token":"846803671","url":"http://zhangmenshiting.baidu.com/data2/music/64333976/64333976.mp3?xcode=0411b74c6de0c453a0d51cfc739c1004"}}
                 * play_behavior : REPLACE_ALL
                 */

                private AudioItemBean audio_item;
                private String play_behavior;

                public AudioItemBean getAudio_item() {
                    return audio_item;
                }

                public void setAudio_item(AudioItemBean audio_item) {
                    this.audio_item = audio_item;
                }

                public String getPlay_behavior() {
                    return play_behavior;
                }

                public void setPlay_behavior(String play_behavior) {
                    this.play_behavior = play_behavior;
                }

                public static class AudioItemBean {
                    /**
                     * audio_item_id : 846803671
                     * stream : {"offset_ms":0,"progress_report_interval_ms":1000,"stream_format":"AUDIO_MP3","token":"846803671","url":"http://zhangmenshiting.baidu.com/data2/music/64333976/64333976.mp3?xcode=0411b74c6de0c453a0d51cfc739c1004"}
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
                         * offset_ms : 0
                         * progress_report_interval_ms : 1000
                         * stream_format : AUDIO_MP3
                         * token : 846803671
                         * url : http://zhangmenshiting.baidu.com/data2/music/64333976/64333976.mp3?xcode=0411b74c6de0c453a0d51cfc739c1004
                         */

                        private int offset_ms;
                        private int progress_report_interval_ms;
                        private String stream_format;
                        private String token;
                        private String url;

                        public int getOffset_ms() {
                            return offset_ms;
                        }

                        public void setOffset_ms(int offset_ms) {
                            this.offset_ms = offset_ms;
                        }

                        public int getProgress_report_interval_ms() {
                            return progress_report_interval_ms;
                        }

                        public void setProgress_report_interval_ms(int progress_report_interval_ms) {
                            this.progress_report_interval_ms = progress_report_interval_ms;
                        }

                        public String getStream_format() {
                            return stream_format;
                        }

                        public void setStream_format(String stream_format) {
                            this.stream_format = stream_format;
                        }

                        public String getToken() {
                            return token;
                        }

                        public void setToken(String token) {
                            this.token = token;
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
        }

        public static class ViewsBean {
            /**
             * type : txt
             * content : 周杰伦 晴天
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
