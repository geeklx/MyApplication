package com.haiersmart.voice.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Create with Android Studio.
 *
 * @author Hsueh
 * @email i@hsueh.top
 * @date 2017/2/10 20:00
 */

public class BaiduWeatherResultBean extends BaiduBaseResultBean {

    /**
     * result : {"resource":{"type":"weather","data":{"city":"北京","current_temp":"3℃","pm25":"25","temp":"-5℃~4℃","time":"周五 02月10日","weather":"晴","weather_all":"晴，-5℃~4℃，北风3-4级","wind":"北风3-4级","weather_info":[{"current_temp":"3℃","icon":"http://s1.bdstatic.com/r/www/aladdin/img/new_weath/bigicon/2.png","pm25":"25","pm_level":"优","temp":"-5℃~4℃","time":"周五 02月10日","weather":"晴","wind":"北风3-4级"},{"icon":"http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/1.png","temp":"-4℃~7℃","time":"周六 02月11日","weather":"晴","wind":"南风微风"},{"icon":"http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/1.png","temp":"-4℃~8℃","time":"周日 02月12日","weather":"晴","wind":"南风微风"},{"icon":"http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/1.png","temp":"-4℃~10℃","time":"周一 02月13日","weather":"晴","wind":"南风微风"},{"icon":"http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/1.png","temp":"-3℃~8℃","time":"周二 02月14日","weather":"晴","wind":"南风微风"}]}},"bot_id":"duer_weather","bot_meta":{"version":"1.0.0","type":"其他","description":"desc"},"views":[{"type":"txt","content":"实时：3℃\n温度：-5℃~4℃\n风力：北风3-4级\n空气质量指数：25，优\n来源：中国天气网","url":"https://m.baidu.com/from=2001a/s?word=北京市天气"}],"nlu":{"domain":"duer_weather","intent":"sys_weather","slots":{"loc_city":"北京市","loc_province":"北京市","time":"2017-02-10,2017-02-10"}},"speech":{"type":"Text","content":"北京今天晴，-5℃~4℃，北风3-4级。空气质量指数为25，优。"}}
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
         * resource : {"type":"weather","data":{"city":"北京","current_temp":"3℃","pm25":"25","temp":"-5℃~4℃","time":"周五 02月10日","weather":"晴","weather_all":"晴，-5℃~4℃，北风3-4级","wind":"北风3-4级","weather_info":[{"current_temp":"3℃","icon":"http://s1.bdstatic.com/r/www/aladdin/img/new_weath/bigicon/2.png","pm25":"25","pm_level":"优","temp":"-5℃~4℃","time":"周五 02月10日","weather":"晴","wind":"北风3-4级"},{"icon":"http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/1.png","temp":"-4℃~7℃","time":"周六 02月11日","weather":"晴","wind":"南风微风"},{"icon":"http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/1.png","temp":"-4℃~8℃","time":"周日 02月12日","weather":"晴","wind":"南风微风"},{"icon":"http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/1.png","temp":"-4℃~10℃","time":"周一 02月13日","weather":"晴","wind":"南风微风"},{"icon":"http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/1.png","temp":"-3℃~8℃","time":"周二 02月14日","weather":"晴","wind":"南风微风"}]}}
         * bot_id : duer_weather
         * bot_meta : {"version":"1.0.0","type":"其他","description":"desc"}
         * views : [{"type":"txt","content":"实时：3℃\n温度：-5℃~4℃\n风力：北风3-4级\n空气质量指数：25，优\n来源：中国天气网","url":"https://m.baidu.com/from=2001a/s?word=北京市天气"}]
         * nlu : {"domain":"duer_weather","intent":"sys_weather","slots":{"loc_city":"北京市","loc_province":"北京市","time":"2017-02-10,2017-02-10"}}
         * speech : {"type":"Text","content":"北京今天晴，-5℃~4℃，北风3-4级。空气质量指数为25，优。"}
         */

        private ResourceBean resource;
        private String bot_id;
        private BotMetaBean bot_meta;
        private NluBean nlu;
        private SpeechBean speech;
        private List<ViewsBean> views;

        public ResourceBean getResource() {
            return resource;
        }

        public void setResource(ResourceBean resource) {
            this.resource = resource;
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

        public static class ResourceBean {
            /**
             * type : weather
             * data : {"city":"北京","current_temp":"3℃","pm25":"25","temp":"-5℃~4℃","time":"周五 02月10日","weather":"晴","weather_all":"晴，-5℃~4℃，北风3-4级","wind":"北风3-4级","weather_info":[{"current_temp":"3℃","icon":"http://s1.bdstatic.com/r/www/aladdin/img/new_weath/bigicon/2.png","pm25":"25","pm_level":"优","temp":"-5℃~4℃","time":"周五 02月10日","weather":"晴","wind":"北风3-4级"},{"icon":"http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/1.png","temp":"-4℃~7℃","time":"周六 02月11日","weather":"晴","wind":"南风微风"},{"icon":"http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/1.png","temp":"-4℃~8℃","time":"周日 02月12日","weather":"晴","wind":"南风微风"},{"icon":"http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/1.png","temp":"-4℃~10℃","time":"周一 02月13日","weather":"晴","wind":"南风微风"},{"icon":"http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/1.png","temp":"-3℃~8℃","time":"周二 02月14日","weather":"晴","wind":"南风微风"}]}
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
                 * city : 北京
                 * current_temp : 3℃
                 * pm25 : 25
                 * temp : -5℃~4℃
                 * time : 周五 02月10日
                 * weather : 晴
                 * weather_all : 晴，-5℃~4℃，北风3-4级
                 * wind : 北风3-4级
                 * weather_info : [{"current_temp":"3℃","icon":"http://s1.bdstatic.com/r/www/aladdin/img/new_weath/bigicon/2.png","pm25":"25","pm_level":"优","temp":"-5℃~4℃","time":"周五 02月10日","weather":"晴","wind":"北风3-4级"},{"icon":"http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/1.png","temp":"-4℃~7℃","time":"周六 02月11日","weather":"晴","wind":"南风微风"},{"icon":"http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/1.png","temp":"-4℃~8℃","time":"周日 02月12日","weather":"晴","wind":"南风微风"},{"icon":"http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/1.png","temp":"-4℃~10℃","time":"周一 02月13日","weather":"晴","wind":"南风微风"},{"icon":"http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/1.png","temp":"-3℃~8℃","time":"周二 02月14日","weather":"晴","wind":"南风微风"}]
                 */

                private String city;
                private String current_temp;
                private String pm25;
                private String temp;
                @SerializedName("time")
                private String timeX;
                private String weather;
                private String weather_all;
                private String wind;
                private List<WeatherInfoBean> weather_info;

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getCurrent_temp() {
                    return current_temp;
                }

                public void setCurrent_temp(String current_temp) {
                    this.current_temp = current_temp;
                }

                public String getPm25() {
                    return pm25;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public String getTemp() {
                    return temp;
                }

                public void setTemp(String temp) {
                    this.temp = temp;
                }

                public String getTimeX() {
                    return timeX;
                }

                public void setTimeX(String timeX) {
                    this.timeX = timeX;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public String getWeather_all() {
                    return weather_all;
                }

                public void setWeather_all(String weather_all) {
                    this.weather_all = weather_all;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public List<WeatherInfoBean> getWeather_info() {
                    return weather_info;
                }

                public void setWeather_info(List<WeatherInfoBean> weather_info) {
                    this.weather_info = weather_info;
                }

                public static class WeatherInfoBean {
                    /**
                     * current_temp : 3℃
                     * icon : http://s1.bdstatic.com/r/www/aladdin/img/new_weath/bigicon/2.png
                     * pm25 : 25
                     * pm_level : 优
                     * temp : -5℃~4℃
                     * time : 周五 02月10日
                     * weather : 晴
                     * wind : 北风3-4级
                     */

                    private String current_temp;
                    private String icon;
                    private String pm25;
                    private String pm_level;
                    private String temp;
                    @SerializedName("time")
                    private String timeX;
                    private String weather;
                    private String wind;

                    public String getCurrent_temp() {
                        return current_temp;
                    }

                    public void setCurrent_temp(String current_temp) {
                        this.current_temp = current_temp;
                    }

                    public String getIcon() {
                        return icon;
                    }

                    public void setIcon(String icon) {
                        this.icon = icon;
                    }

                    public String getPm25() {
                        return pm25;
                    }

                    public void setPm25(String pm25) {
                        this.pm25 = pm25;
                    }

                    public String getPm_level() {
                        return pm_level;
                    }

                    public void setPm_level(String pm_level) {
                        this.pm_level = pm_level;
                    }

                    public String getTemp() {
                        return temp;
                    }

                    public void setTemp(String temp) {
                        this.temp = temp;
                    }

                    public String getTimeX() {
                        return timeX;
                    }

                    public void setTimeX(String timeX) {
                        this.timeX = timeX;
                    }

                    public String getWeather() {
                        return weather;
                    }

                    public void setWeather(String weather) {
                        this.weather = weather;
                    }

                    public String getWind() {
                        return wind;
                    }

                    public void setWind(String wind) {
                        this.wind = wind;
                    }
                }
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
             * domain : duer_weather
             * intent : sys_weather
             * slots : {"loc_city":"北京市","loc_province":"北京市","time":"2017-02-10,2017-02-10"}
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
                 * loc_city : 北京市
                 * loc_province : 北京市
                 * time : 2017-02-10,2017-02-10
                 */

                private String loc_city;
                private String loc_province;
                @SerializedName("time")
                private String timeX;

                public String getLoc_city() {
                    return loc_city;
                }

                public void setLoc_city(String loc_city) {
                    this.loc_city = loc_city;
                }

                public String getLoc_province() {
                    return loc_province;
                }

                public void setLoc_province(String loc_province) {
                    this.loc_province = loc_province;
                }

                public String getTimeX() {
                    return timeX;
                }

                public void setTimeX(String timeX) {
                    this.timeX = timeX;
                }
            }
        }

        public static class SpeechBean {
            /**
             * type : Text
             * content : 北京今天晴，-5℃~4℃，北风3-4级。空气质量指数为25，优。
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
             * content : 实时：3℃
             温度：-5℃~4℃
             风力：北风3-4级
             空气质量指数：25，优
             来源：中国天气网
             * url : https://m.baidu.com/from=2001a/s?word=北京市天气
             */

            private String type;
            private String content;
            private String url;

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

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
