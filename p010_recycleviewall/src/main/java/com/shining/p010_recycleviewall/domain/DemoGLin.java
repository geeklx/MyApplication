package com.shining.p010_recycleviewall.domain;

import java.util.List;

/**
 * Created by shining on 2017/12/27.
 */

public class DemoGLin {


    /**
     * code : 200
     * datas : {"order_list":[{"order_id":"5084","order_sn":"9000000000514201","add_time_str":"2017-12-23 20:25","order_amount":"0.01","goods_amount":"0.01","order_goods":[{"goods_image":"http://192.168.200.69/data/upload/shop/store/goods/1/1_05621790523681059_240.png","goods_name":"欢欢测试酒","goods_price":"0.01","market_price":"0.01","goods_num":"1"}],"order_state_str":"已完成"}],"number":{"all":1,"waiting_send":0,"waiting_receive":0,"complete":1},"total_page":1}
     */

    private int code;
    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * order_list : [{"order_id":"5084","order_sn":"9000000000514201","add_time_str":"2017-12-23 20:25","order_amount":"0.01","goods_amount":"0.01","order_goods":[{"goods_image":"http://192.168.200.69/data/upload/shop/store/goods/1/1_05621790523681059_240.png","goods_name":"欢欢测试酒","goods_price":"0.01","market_price":"0.01","goods_num":"1"}],"order_state_str":"已完成"}]
         * number : {"all":1,"waiting_send":0,"waiting_receive":0,"complete":1}
         * total_page : 1
         */

        private NumberBean number;
        private int total_page;
        private List<OrderListBean> order_list;

        public NumberBean getNumber() {
            return number;
        }

        public void setNumber(NumberBean number) {
            this.number = number;
        }

        public int getTotal_page() {
            return total_page;
        }

        public void setTotal_page(int total_page) {
            this.total_page = total_page;
        }

        public List<OrderListBean> getOrder_list() {
            return order_list;
        }

        public void setOrder_list(List<OrderListBean> order_list) {
            this.order_list = order_list;
        }

        public static class NumberBean {
            /**
             * all : 1
             * waiting_send : 0
             * waiting_receive : 0
             * complete : 1
             */

            private int all;
            private int waiting_send;
            private int waiting_receive;
            private int complete;

            public int getAll() {
                return all;
            }

            public void setAll(int all) {
                this.all = all;
            }

            public int getWaiting_send() {
                return waiting_send;
            }

            public void setWaiting_send(int waiting_send) {
                this.waiting_send = waiting_send;
            }

            public int getWaiting_receive() {
                return waiting_receive;
            }

            public void setWaiting_receive(int waiting_receive) {
                this.waiting_receive = waiting_receive;
            }

            public int getComplete() {
                return complete;
            }

            public void setComplete(int complete) {
                this.complete = complete;
            }
        }

        public static class OrderListBean {
            /**
             * order_id : 5084
             * order_sn : 9000000000514201
             * add_time_str : 2017-12-23 20:25
             * order_amount : 0.01
             * goods_amount : 0.01
             * order_goods : [{"goods_image":"http://192.168.200.69/data/upload/shop/store/goods/1/1_05621790523681059_240.png","goods_name":"欢欢测试酒","goods_price":"0.01","market_price":"0.01","goods_num":"1"}]
             * order_state_str : 已完成
             */

            private String order_id;
            private String order_sn;
            private String add_time_str;
            private String order_amount;
            private String goods_amount;
            private String order_state_str;
            private List<OrderGoodsBean> order_goods;

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getOrder_sn() {
                return order_sn;
            }

            public void setOrder_sn(String order_sn) {
                this.order_sn = order_sn;
            }

            public String getAdd_time_str() {
                return add_time_str;
            }

            public void setAdd_time_str(String add_time_str) {
                this.add_time_str = add_time_str;
            }

            public String getOrder_amount() {
                return order_amount;
            }

            public void setOrder_amount(String order_amount) {
                this.order_amount = order_amount;
            }

            public String getGoods_amount() {
                return goods_amount;
            }

            public void setGoods_amount(String goods_amount) {
                this.goods_amount = goods_amount;
            }

            public String getOrder_state_str() {
                return order_state_str;
            }

            public void setOrder_state_str(String order_state_str) {
                this.order_state_str = order_state_str;
            }

            public List<OrderGoodsBean> getOrder_goods() {
                return order_goods;
            }

            public void setOrder_goods(List<OrderGoodsBean> order_goods) {
                this.order_goods = order_goods;
            }

            public static class OrderGoodsBean {
                /**
                 * goods_image : http://192.168.200.69/data/upload/shop/store/goods/1/1_05621790523681059_240.png
                 * goods_name : 欢欢测试酒
                 * goods_price : 0.01
                 * market_price : 0.01
                 * goods_num : 1
                 */

                private String goods_image;
                private String goods_name;
                private String goods_price;
                private String market_price;
                private String goods_num;

                public String getGoods_image() {
                    return goods_image;
                }

                public void setGoods_image(String goods_image) {
                    this.goods_image = goods_image;
                }

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public String getGoods_price() {
                    return goods_price;
                }

                public void setGoods_price(String goods_price) {
                    this.goods_price = goods_price;
                }

                public String getMarket_price() {
                    return market_price;
                }

                public void setMarket_price(String market_price) {
                    this.market_price = market_price;
                }

                public String getGoods_num() {
                    return goods_num;
                }

                public void setGoods_num(String goods_num) {
                    this.goods_num = goods_num;
                }
            }
        }
    }
}
