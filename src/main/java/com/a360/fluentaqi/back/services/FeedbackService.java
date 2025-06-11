package com.a360.fluentaqi.back.services;

import com.a360.fluentaqi.back.aqiabouts.Feedback;

public interface FeedbackService {
    /**
     * 添加反馈信息
     * @param afb
     */
    public void saveFeedBack(Feedback afb);
    /**
     * 指派网格员,修改反馈信息中网格员和状态
     */
    public void assignGridder(String afId,String realName);
    /**
     * 提交实测AQI数据
     * @param afb
     */
    public void confirmData(Feedback afb);
}
