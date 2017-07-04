package com.haiersmart.voice.bean;

import java.util.List;

/**
 * demo query: 播放音乐
 * Created by JefferyLeng on 2017/2/14.
 */

public class BaiduAudioResultBean extends BaiduBaseResultBean {


    /**
     * result : {"directives":[{"header":{"namespace":"AudioPlayer","name":"Play","message_id":"1487315050_683cbfpee"},"payload":{"play_behavior":"REPLACE_ALL","audio_item":{"audio_item_id":"55690583215","stream":{"url":"http://fdfs.xmcdn.com/group10/M09/29/D8/wKgDaVbyFHOijZf5AB_wpdWUk_8276.mp3","stream_format":"mp3","offset_ms":0,"token":"55690583215","progress_report_interval_ms":1000}}}}],"speech":{"type":"Text","content":"播放 【高清】你没听过的郭德纲相声（岳云鹏 德云社 于谦）  曲协主席 来自喜马拉雅"},"bot_id":"audio_unicast","bot_meta":{"version":"1.0.0","type":"其他","description":"desc"},"views":[{"type":"txt","content":"播放 【高清】你没听过的郭德纲相声（岳云鹏 德云社 于谦）  曲协主席 来自喜马拉雅"}],"nlu":{"domain":"audio.unicast","intent":"audio.unicast.play","slots":{"artist":"郭德纲","first_category":"相声"}}}
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
         * directives : [{"header":{"namespace":"AudioPlayer","name":"Play","message_id":"1487315050_683cbfpee"},"payload":{"play_behavior":"REPLACE_ALL","audio_item":{"audio_item_id":"55690583215","stream":{"url":"http://fdfs.xmcdn.com/group10/M09/29/D8/wKgDaVbyFHOijZf5AB_wpdWUk_8276.mp3","stream_format":"mp3","offset_ms":0,"token":"55690583215","progress_report_interval_ms":1000}}}}]
         * speech : {"type":"Text","content":"播放 【高清】你没听过的郭德纲相声（岳云鹏 德云社 于谦）  曲协主席 来自喜马拉雅"}
         * bot_id : audio_unicast
         * bot_meta : {"version":"1.0.0","type":"其他","description":"desc"}
         * views : [{"type":"txt","content":"播放 【高清】你没听过的郭德纲相声（岳云鹏 德云社 于谦）  曲协主席 来自喜马拉雅"}]
         * nlu : {"domain":"audio.unicast","intent":"audio.unicast.play","slots":{"artist":"郭德纲","first_category":"相声"}}
         */

        private SpeechBean speech;
        private String bot_id;
        private BotMetaBean bot_meta;
        private NluBean nlu;
        private List<DirectivesBean> directives;
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

        public static class SpeechBean {
            /**
             * type : Text
             * content : 播放 【高清】你没听过的郭德纲相声（岳云鹏 德云社 于谦）  曲协主席 来自喜马拉雅
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
             * domain : audio.unicast
             * intent : audio.unicast.play
             * slots : {"artist":"郭德纲","first_category":"相声"}
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
                 * artist : 郭德纲
                 * first_category : 相声
                 */

                private String artist;
                private String first_category;
                private String track_name;

                public String getTrack_name() {
                    return track_name;
                }

                public void setTrack_name(String track_name) {
                    this.track_name = track_name;
                }

                public String getArtist() {
                    return artist;
                }

                public void setArtist(String artist) {
                    this.artist = artist;
                }

                public String getFirst_category() {
                    return first_category;
                }

                public void setFirst_category(String first_category) {
                    this.first_category = first_category;
                }
            }
        }

        public static class DirectivesBean {
            /**
             * header : {"namespace":"AudioPlayer","name":"Play","message_id":"1487315050_683cbfpee"}
             * payload : {"play_behavior":"REPLACE_ALL","audio_item":{"audio_item_id":"55690583215","stream":{"url":"http://fdfs.xmcdn.com/group10/M09/29/D8/wKgDaVbyFHOijZf5AB_wpdWUk_8276.mp3","stream_format":"mp3","offset_ms":0,"token":"55690583215","progress_report_interval_ms":1000}}}
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
                 * message_id : 1487315050_683cbfpee
                 */

                private String namespace;
                private String name;
                private String message_id;

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

                public String getMessage_id() {
                    return message_id;
                }

                public void setMessage_id(String message_id) {
                    this.message_id = message_id;
                }
            }

            public static class PayloadBean {
                /**
                 * play_behavior : REPLACE_ALL
                 * audio_item : {"audio_item_id":"55690583215","stream":{"url":"http://fdfs.xmcdn.com/group10/M09/29/D8/wKgDaVbyFHOijZf5AB_wpdWUk_8276.mp3","stream_format":"mp3","offset_ms":0,"token":"55690583215","progress_report_interval_ms":1000}}
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
                     * audio_item_id : 55690583215
                     * stream : {"url":"http://fdfs.xmcdn.com/group10/M09/29/D8/wKgDaVbyFHOijZf5AB_wpdWUk_8276.mp3","stream_format":"mp3","offset_ms":0,"token":"55690583215","progress_report_interval_ms":1000}
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
                         * url : http://fdfs.xmcdn.com/group10/M09/29/D8/wKgDaVbyFHOijZf5AB_wpdWUk_8276.mp3
                         * stream_format : mp3
                         * offset_ms : 0
                         * token : 55690583215
                         * progress_report_interval_ms : 1000
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

        public static class ViewsBean {
            /**
             * type : txt
             * content : 播放 【高清】你没听过的郭德纲相声（岳云鹏 德云社 于谦）  曲协主席 来自喜马拉雅
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
