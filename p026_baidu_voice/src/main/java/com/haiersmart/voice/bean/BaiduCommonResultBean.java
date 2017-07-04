package com.haiersmart.voice.bean;


/**
 * baidu 统一返回的result
 * Created by JefferyLeng on 2016/12/19.
 */
public class BaiduCommonResultBean extends BaiduBaseResultBean {


    /**
     * result : {"nlu":{"domain":"music","intent":"music"},"speech":{"type":"Text","content":"为您播放周杰伦的歌曲"}}
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
         * nlu : {"domain":"music","intent":"music"}
         * speech : {"type":"Text","content":"为您播放周杰伦的歌曲"}
         */

        private NluBean nlu;
        private SpeechBean speech;

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

        public static class NluBean {
            /**
             * domain : music
             * intent : music
             */

            private String domain;
            private String intent;

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
    }
}
