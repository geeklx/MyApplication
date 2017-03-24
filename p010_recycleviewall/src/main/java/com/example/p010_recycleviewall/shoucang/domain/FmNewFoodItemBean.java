package com.example.p010_recycleviewall.shoucang.domain;

import java.io.Serializable;

/**
 * Created by qibin on 2016/8/4.
 */

public class FmNewFoodItemBean implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * food_definition_id : 84
     * food_name : 鸡肉
     * fridge_food_id : 201605182146050173
     * food_image : http://192.168.200.95/group1/M00/00/00/wKjIYFcDizaEKPLaAAAAAAAAAAA958.png
     * food_image_big : http://192.168.200.95/group1/M00/00/00/wKjIYFcDizaEKPLaAAAAAAAAAAA958.png
     * food_fresh : 102
     * food_effect : 低脂、降血糖
     * food_instruction : 鸡肉用药膳炖煮，营养更全面。带皮的鸡肉含有较多的脂类物质，所以比较肥的鸡应该去掉鸡皮再烹制
     * food_nutrition : 鸡肉和猪肉、牛肉比较，其蛋白质含量较高，脂肪含量较低
     * food_choose : 活鸡的选购：在选购鲜活鸡时，可将鸡的翅膀提起，如其挣扎有力，双腿收起
     * food_store_way : 活鸡肉：家庭购买鲜活鸡可让服务人员宰杀，如果需要长时间的保存，可把光鸡擦去表面水分
     * food_aliases :
     * food_suitable : 一般人群均可食用。老人、病人、体弱者更宜食用
     * food_taboo : 鸡肉性温，助火，肝阳上亢及口腔糜烂、皮肤疖肿、大便秘结者不宜食用
     * food_desc : 顾名思义，鸡肉就是鸡身上的肉，鸡的肉质细嫩，滋味鲜美，适合多种烹调方法，并富有营养，有滋补养身的作用
     * food_category_id : 6
     * food_overdue_day : 离过期4天
     * food_unit : 5-d
     * food_have_state : 1
     * food_start_time : 2016-07-25
     * food_end_time : 2017-01-06
     * food_have_label : 推荐
     * food_time_label : 已过期
     * food_rfid : 007600000000450087000093
     * food_cabin_id : 3
     * food_third_category_id : 1
     * fridge_cabin : 1
     * fridge_cabin_name : 冷藏室
     * tag_color : #BBB
     */

    private String food_definition_id;
    private String food_name;
    private String fridge_food_id;
    private String food_image;
    private String food_image_big;
    private String food_fresh;
    private String food_fresh_rate;
    private String food_effect;
    private String food_instruction;
    private String food_nutrition;
    private String food_choose;
    private String food_store_way;
    private String food_aliases;
    private String food_suitable;
    private String food_taboo;
    private String food_desc;
    private String food_category_id;
    private String food_overdue_day;
    private String food_unit;
    private String food_have_state;
    private String food_start_time;
    private String food_end_time;
    private String food_have_label;
    private String food_time_label;
    private String food_rfid;
    private String food_cabin_id;
    private String food_third_category_id;
    private String fridge_cabin;
    private String fridge_cabin_name;
    private String tag_color;
    private long create_time;

    //add
    private boolean ischoose;

    public boolean ischoose() {
        return ischoose;
    }

    public void setIschoose(boolean ischoose) {
        this.ischoose = ischoose;
    }

    public String getFood_definition_id() {
        return food_definition_id;
    }

    public void setFood_definition_id(String food_definition_id) {
        this.food_definition_id = food_definition_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFridge_food_id() {
        return fridge_food_id;
    }

    public void setFridge_food_id(String fridge_food_id) {
        this.fridge_food_id = fridge_food_id;
    }

    public String getFood_image() {
        return food_image;
    }

    public void setFood_image(String food_image) {
        this.food_image = food_image;
    }

    public String getFood_image_big() {
        return food_image_big;
    }

    public void setFood_image_big(String food_image_big) {
        this.food_image_big = food_image_big;
    }

    public String getFood_fresh() {
        return food_fresh;
    }

    public void setFood_fresh(String food_fresh) {
        this.food_fresh = food_fresh;
    }

    public String getFood_fresh_rate() {
        return food_fresh_rate;
    }

    public void setFood_fresh_rate(String food_fresh_rate) {
        this.food_fresh_rate = food_fresh_rate;
    }

    public String getFood_effect() {
        return food_effect;
    }

    public void setFood_effect(String food_effect) {
        this.food_effect = food_effect;
    }

    public String getFood_instruction() {
        return food_instruction;
    }

    public void setFood_instruction(String food_instruction) {
        this.food_instruction = food_instruction;
    }

    public String getFood_nutrition() {
        return food_nutrition;
    }

    public void setFood_nutrition(String food_nutrition) {
        this.food_nutrition = food_nutrition;
    }

    public String getFood_choose() {
        return food_choose;
    }

    public void setFood_choose(String food_choose) {
        this.food_choose = food_choose;
    }

    public String getFood_store_way() {
        return food_store_way;
    }

    public void setFood_store_way(String food_store_way) {
        this.food_store_way = food_store_way;
    }

    public String getFood_aliases() {
        return food_aliases;
    }

    public void setFood_aliases(String food_aliases) {
        this.food_aliases = food_aliases;
    }

    public String getFood_suitable() {
        return food_suitable;
    }

    public void setFood_suitable(String food_suitable) {
        this.food_suitable = food_suitable;
    }

    public String getFood_taboo() {
        return food_taboo;
    }

    public void setFood_taboo(String food_taboo) {
        this.food_taboo = food_taboo;
    }

    public String getFood_desc() {
        return food_desc;
    }

    public void setFood_desc(String food_desc) {
        this.food_desc = food_desc;
    }

    public String getFood_category_id() {
        return food_category_id;
    }

    public void setFood_category_id(String food_category_id) {
        this.food_category_id = food_category_id;
    }

    public String getFood_overdue_day() {
        return food_overdue_day;
    }

    public void setFood_overdue_day(String food_overdue_day) {
        this.food_overdue_day = food_overdue_day;
    }

    public String getFood_unit() {
        return food_unit;
    }

    public void setFood_unit(String food_unit) {
        this.food_unit = food_unit;
    }

    public String getFood_have_state() {
        return food_have_state;
    }

    public void setFood_have_state(String food_have_state) {
        this.food_have_state = food_have_state;
    }

    public String getFood_start_time() {
        return food_start_time;
    }

    public void setFood_start_time(String food_start_time) {
        this.food_start_time = food_start_time;
    }

    public String getFood_end_time() {
        return food_end_time;
    }

    public void setFood_end_time(String food_end_time) {
        this.food_end_time = food_end_time;
    }

    public String getFood_have_label() {
        return food_have_label;
    }

    public void setFood_have_label(String food_have_label) {
        this.food_have_label = food_have_label;
    }

    public String getFood_time_label() {
        return food_time_label;
    }

    public void setFood_time_label(String food_time_label) {
        this.food_time_label = food_time_label;
    }

    public String getFood_rfid() {
        return food_rfid;
    }

    public void setFood_rfid(String food_rfid) {
        this.food_rfid = food_rfid;
    }

    public String getFood_cabin_id() {
        return food_cabin_id;
    }

    public void setFood_cabin_id(String food_cabin_id) {
        this.food_cabin_id = food_cabin_id;
    }

    public String getFood_third_category_id() {
        return food_third_category_id;
    }

    public void setFood_third_category_id(String food_third_category_id) {
        this.food_third_category_id = food_third_category_id;
    }

    public String getFridge_cabin() {
        return fridge_cabin;
    }

    public void setFridge_cabin(String fridge_cabin) {
        this.fridge_cabin = fridge_cabin;
    }

    public String getFridge_cabin_name() {
        return fridge_cabin_name;
    }

    public void setFridge_cabin_name(String fridge_cabin_name) {
        this.fridge_cabin_name = fridge_cabin_name;
    }

    public String getTag_color() {
        return tag_color;
    }

    public void setTag_color(String tag_color) {
        this.tag_color = tag_color;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }
}
