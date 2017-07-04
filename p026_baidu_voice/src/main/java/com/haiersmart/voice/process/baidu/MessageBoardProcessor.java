package com.haiersmart.voice.process.baidu;

import com.haiersmart.voice.bean.MessageBoardResultBean;
import com.haiersmart.voice.process.engine.SpeechHandleIntermediary;

/**
 * Create with Android Studio.
 *
 * @author Hsueh
 * @email i@hsueh.top
 * @date 2017/2/26 14:12
 */

public class MessageBoardProcessor extends BaiduBaseProcessor<MessageBoardResultBean> {

    public static final String READ = "read";
    public static final String DELETE = "delete";
    public static final String CREATE = "create";
    public static final String TIME_TAG = "timeTag";
    public static final String DELETE_ALL = "delete_all";
    public static final String READ_ALL = "read_all";

    @Override
    protected void process(MessageBoardResultBean commonResultBean, SpeechHandleIntermediary speechHandle) {
        speechHandle.ttsPlay("这个暂时不支持呦");

//        if (commonResultBean != null) {
//            MessageBoardResultBean.ResultBean result = commonResultBean.getResult();
//            String content = result.getSpeech().getContent();
//            if (result != null) {
//                MessageBoardResultBean.ResultBean.NluBean nlu = result.getNlu();
//                if (nlu != null) {
//                    MessageBoardResultBean.ResultBean.NluBean.SlotsBean slots = nlu.getSlots();
//                    if (slots != null) {
//                        String keyword = slots.getKeyword();
//                        String id__ = slots.get__id__();
//                        String memorycontent = slots.getMemorycontent();
//                        String intent = slots.getIntent();
//
//                        if (!TextUtils.isEmpty(keyword) && keyword.equals("留言")){
//                            if (!TextUtils.isEmpty(memorycontent)){
////                                addMessage(memorycontent);
//                                speechHandle.ttsPlay(content);
//                            }else {
//                                speechHandle.ttsPlay(content);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        Intent intentreceived = new Intent("com.haier.messageboard.message.received");
//        mContext.sendBroadcast(intentreceived);
    }



//    //删除
//    private void deleteMessage(String text, String time, SpeechHandleIntermediary speechHelper) {
//        String formatedTimeTag = Message_630.getFormatedTimeTag(Long.parseLong(time));
//        try {
//            Message_630.deleteAll(Message_630.class, "TIME_TAG = ?", formatedTimeTag);
//            speechHelper.ttsPlay(text);
//        } catch (Exception e) {
//            speechHelper.ttsPlay("删除失败,请确认删除内容");
//        }
//    }
//
//    //查看
//    private void queryMessage(SpeechHandleIntermediary speechHelper, List<Message_630> messageByDate) {
//        if (messageByDate != null && messageByDate.size() > 0) {
//            String content = "";
//            if (messageByDate.size() > 1) {
//                content += "多条留言,最后一条";
//            }
//            Message_630 message_630 = messageByDate.get(messageByDate.size() - 1);
//            int msgType = message_630.getMsgType();
//            if (msgType == 0) {
//                content = content + "留言" + message_630.getMsgContent();
//                speechHelper.ttsPlay(content);
//            } else if (msgType == 1) {
//                content += "留言内容是图片";
//                speechHelper.ttsPlay(content);
//            } else if (msgType == 2) {
//                content += "留言内容是语音";
//                speechHelper.ttsPlay(content);
//            } else if (msgType == 3) {
//                content += "留言内容是视频";
//                speechHelper.ttsPlay(content);
//            }
//        } else {
//            speechHelper.ttsPlay("没有留言内容");
//        }
//    }
//
//    //添加留言
//    public void addMessage(String msg) {
//        Message_630 message = new Message_630();
//        message.setMsgContent(msg);
//        message.setAudioPath("");
//        message.setVideoPath("");
//        message.setTimeTag(Message_630.getFormatedTimeTag(System.currentTimeMillis()));
//        message.setMsgType(0);//文本
//        message.setCreateTime(new Date());
//        Message_630.save(message);
//    }
//
//    //查询留言-日期
//    public List<Message_630> getMessageByDate(String date) {
//        String formatedTimeTag = Message_630.getFormatedTimeTag(Long.parseLong(date));
//        List<Message_630> message_630s = null;
//        try {
////			message_630s = Message_630.findWithQuery(Message_630.class, "Select * from MESSAGE630 where TIME_TAG = ?", formatedTimeTag);
//            message_630s = Message_630.find(Message_630.class, "TIME_TAG = ?", formatedTimeTag);
//        } catch (Exception e) {
//            if (message_630s == null) {
//                return new ArrayList<Message_630>();
//            }
//        }
//        return message_630s;
//    }


}
