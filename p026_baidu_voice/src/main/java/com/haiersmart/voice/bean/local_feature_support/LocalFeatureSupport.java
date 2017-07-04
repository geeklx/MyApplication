package com.haiersmart.voice.bean.local_feature_support;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by JefferyLeng on 2017/2/25.
 */

public class LocalFeatureSupport {


    /**
     * model : 658
     * apps : {"app":[{"target":"message_board","class":"com.haier.message.messageboard.activity.LookMessage_658","operation-list":{"operation":[{"attr":"open","query-list":{"query":["打开留言板","我要打开留言板","我想看看留言板","进入留言板","进入留言板"]}},{"attr":"close","query-list":{"query":["关闭留言板","我要关闭留言板","帮我关闭留言板","不想看留言板了","把留言板关掉"]}}]}},{"target":"cook_book","class":"com.haiersmart.sfnation630.ui.activity.cookbook.CookBookMainPageActivty_630","operation-list":{"operation":[{"attr":"open","query-list":{"query":["打开菜谱","进入菜谱","我要打开菜谱","我想看看菜谱","帮我打开菜谱"]}},{"attr":"close","query-list":{"query":["关闭菜谱","我要关闭菜谱","不想看菜谱了","把菜谱关掉"]}}]}},{"target":"food_management","class":"com.haiersmart.sfnation630.ui.activity.foodManage.FoodManagementActivity_630","operation-list":{"operation":[{"attr":"open","query-list":{"query":["打开食材管理","打开石材管理","我要打开食材管理","我要打开石材管理","我想看看食材管理","进入食材管理"]}},{"attr":"close","query-list":{"query":["关闭食材管理","帮我关闭食材管理","不想看食材管理了","关闭石材管理","帮我关闭石材管理","不想看石材管理了"]}}]}},{"target":"fm","class":"com.haiersmart.sfnation630.ui.activity.entertainment.Entertainment_FM_Activity_630","operation-list":{"operation":[{"attr":"open","query-list":{"query":["打开电台","我要打开电台","我想听电台","帮我打开电台","进入电台"]}},{"attr":"close","query-list":{"query":["关闭电台","帮我关闭电台","不想听电台了"]}}]}},{"target":"video","class":"com.haiersmart.sfnation630.ui.activity.entertainment.youku.YouKuHomePageActivity","operation-list":{"operation":[{"attr":"open","query-list":{"query":["打开视频","我要打开视频","我想看视频","帮我打开视频","进入视频"]}},{"attr":"close","query-list":{"query":["关闭视频","帮我关闭视频","不想看视频了"]}}]}},{"target":"nutrition","class":"com.haiersmart.sfnation630.ui.activity.foodManage.FoodNutritionActivity_630","operation-list":{"operation":[{"attr":"open","query-list":{"query":["打开营养曲线","我要打开营养曲线","我想看营养曲线","帮我打开营养曲线","进入营养曲线"]}},{"attr":"close","query-list":{"query":["关闭营养曲线","帮我关闭营养曲线","不想看营养曲线了"]}}]}},{"target":"shop","class":"com.haiersmart.sfnation630.ui.dianshang.fragmentframelayout.ShopIndexActivity","operation-list":{"operation":[{"attr":"open","query-list":{"query":["打开商城","我要打开商城","我想买水果","帮我打开商城","看看最近有什么好吃的"]}},{"attr":"close","query-list":{"query":["关闭商城","帮我关闭商城","不想买水果了"]}}]}}]}
     */

    private int model;
    private AppsBean apps;

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public AppsBean getApps() {
        return apps;
    }

    public void setApps(AppsBean apps) {
        this.apps = apps;
    }

    public static class AppsBean {
        private List<AppBean> app;

        public List<AppBean> getApp() {
            return app;
        }

        public void setApp(List<AppBean> app) {
            this.app = app;
        }

        public static class AppBean {
            /**
             * target : message_board
             * class : com.haier.message.messageboard.activity.LookMessage_658
             * operation-list : {"operation":[{"attr":"open","query-list":{"query":["打开留言板","我要打开留言板","我想看看留言板","进入留言板","进入留言板"]}},{"attr":"close","query-list":{"query":["关闭留言板","我要关闭留言板","帮我关闭留言板","不想看留言板了","把留言板关掉"]}}]}
             */

            private String target;
            @SerializedName("class")
            private String classX;
            @SerializedName("operation-list")
            private OperationlistBean operationlist;

            public String getTarget() {
                return target;
            }

            public void setTarget(String target) {
                this.target = target;
            }

            public String getClassX() {
                return classX;
            }

            public void setClassX(String classX) {
                this.classX = classX;
            }

            public OperationlistBean getOperationlist() {
                return operationlist;
            }

            public void setOperationlist(OperationlistBean operationlist) {
                this.operationlist = operationlist;
            }

            public static class OperationlistBean {
                private List<OperationBean> operation;

                public List<OperationBean> getOperation() {
                    return operation;
                }

                public void setOperation(List<OperationBean> operation) {
                    this.operation = operation;
                }

                public static class OperationBean {
                    /**
                     * attr : open
                     * query-list : {"query":["打开留言板","我要打开留言板","我想看看留言板","进入留言板","进入留言板"]}
                     */

                    private String attr;
                    @SerializedName("query-list")
                    private QuerylistBean querylist;

                    public String getAttr() {
                        return attr;
                    }

                    public void setAttr(String attr) {
                        this.attr = attr;
                    }

                    public QuerylistBean getQuerylist() {
                        return querylist;
                    }

                    public void setQuerylist(QuerylistBean querylist) {
                        this.querylist = querylist;
                    }

                    public static class QuerylistBean {
                        private List<String> query;

                        public List<String> getQuery() {
                            return query;
                        }

                        public void setQuery(List<String> query) {
                            this.query = query;
                        }
                    }
                }
            }
        }
    }
}
