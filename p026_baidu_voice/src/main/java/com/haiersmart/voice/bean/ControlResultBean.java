package com.haiersmart.voice.bean;

import java.util.List;

/**
 * Create with Android Studio.
 *
 * @author Hsueh
 * @email i@hsueh.top
 * @date 2017/2/16 20:11
 */

public class ControlResultBean extends BaiduBaseResultBean{

    /**
     * result : {"directives":[{"header":{"namespace":"Speaker","name":"AdjustVolume","message_id":"1487143420_639epx4xe"},"payload":{"volume":-10}}],"speech":{"type":"Text","content":""},"bot_id":"speaker_hardware","bot_meta":{"version":"1.0.0","type":"其他","description":"desc"},"views":[{"type":"txt","content":"音量"}],"nlu":{"domain":"control.hardware","intent":"control.hardware.volume","slots":{"vol_down":"小"}}}
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
         * directives : [{"header":{"namespace":"Speaker","name":"AdjustVolume","message_id":"1487143420_639epx4xe"},"payload":{"volume":-10}}]
         * speech : {"type":"Text","content":""}
         * bot_id : speaker_hardware
         * bot_meta : {"version":"1.0.0","type":"其他","description":"desc"}
         * views : [{"type":"txt","content":"音量"}]
         * nlu : {"domain":"control.hardware","intent":"control.hardware.volume","slots":{"vol_down":"小"}}
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
             * content :
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
             * domain : control.hardware
             * intent : control.hardware.volume
             * slots : {"vol_down":"小"}
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
                 * vol_down : 小
                 */

                private String vol_down;
                private String vol_up;
                private String vol_mute;
                private String up;
                private String down;

                public String getUp() {
                    return up;
                }

                public void setUp(String up) {
                    this.up = up;
                }

                public String getDown() {
                    return down;
                }

                public void setDown(String down) {
                    this.down = down;
                }

                public String getVol_mute() {
                    return vol_mute;
                }

                public void setVol_mute(String vol_mute) {
                    this.vol_mute = vol_mute;
                }

                public String getVol_up() {
                    return vol_up;
                }

                public void setVol_up(String vol_up) {
                    this.vol_up = vol_up;
                }

                public String getVol_down() {
                    return vol_down;
                }

                public void setVol_down(String vol_down) {
                    this.vol_down = vol_down;
                }
            }
        }

        public static class DirectivesBean {
            /**
             * header : {"namespace":"Speaker","name":"AdjustVolume","message_id":"1487143420_639epx4xe"}
             * payload : {"volume":-10}
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
                 * namespace : Speaker
                 * name : AdjustVolume
                 * message_id : 1487143420_639epx4xe
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
                 * volume : -10
                 */

                private int volume;

                public int getVolume() {
                    return volume;
                }

                public void setVolume(int volume) {
                    this.volume = volume;
                }
            }
        }

        public static class ViewsBean {
            /**
             * type : txt
             * content : 音量
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
