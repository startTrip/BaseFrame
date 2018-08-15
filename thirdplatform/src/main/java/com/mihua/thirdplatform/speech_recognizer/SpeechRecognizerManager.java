package com.mihua.thirdplatform.speech_recognizer;

import android.content.Context;

import com.alibaba.idst.nls.NlsClient;
import com.alibaba.idst.nls.NlsListener;
import com.alibaba.idst.nls.StageListener;
import com.alibaba.idst.nls.internal.protocol.NlsRequest;
import com.alibaba.idst.nls.internal.protocol.NlsRequestProto;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/06/08
 *     desc   :
 * </pre>
 */
public class SpeechRecognizerManager {


    //设置申请到的Appkey
    public static final String appkey = "nls-service-shopping";

    public static final String accessKey = "LTAIti8ldFE1D7eQ";

    public static final String accessSecret = "cDYwRSt0eFxX2VbAAqJ4rGZzWhPLQr";

    private volatile static SpeechRecognizerManager mSpeechRecognizerManager;

    private NlsRequest mNlsRequest;
    private NlsClient mNlsClient;

    private SpeechRecognizerManager(){

    }

    public static SpeechRecognizerManager getInstance(){

        if(mSpeechRecognizerManager==null){
            synchronized (SpeechRecognizerManager.class){
                if(mSpeechRecognizerManager==null){
                    mSpeechRecognizerManager = new SpeechRecognizerManager();
                }
            }
        }
        return mSpeechRecognizerManager;
    }

    /**
     * 第一个执行的方法,最好在程序入口入执行
     *
     * @param context
     */
    private static void initSDK(Context context){

        NlsClient.configure(context);
    }

    /**
     *  初始化 NlsClient 对象
     * @param context  正文对象
     * @param recognitionListener   语音识别结果监听器，需要在调用的地方实现
     * @param stageListener  状态监听器，需要在调用的地方实现里面的监听方法
     */
    private void initNlsClient(Context context, NlsListener recognitionListener, StageListener stageListener){

        mNlsRequest = initNlsRequest(context);

        mNlsRequest.setApp_key(appkey);    //appkey请从 "快速开始" 帮助页面的appkey列表中获取
        mNlsRequest.setAsr_sc("opu");      //设置语音格式

        /*设置热词相关属性*/
        mNlsRequest.setAsrUserId("userid");
        mNlsRequest.setAsrVocabularyId("vocabid");
        /*设置热词相关属性*/
        // 打开log
        openLog(true);

        mNlsClient = NlsClient.newInstance(context, recognitionListener, stageListener,mNlsRequest);                          //实例化NlsClient

        mNlsClient.setMaxRecordTime(60000);  //设置最长语音
        mNlsClient.setMaxStallTime(1000);    //设置最短语音
        mNlsClient.setMinRecordTime(500);    //设置最大录音中断时间
        mNlsClient.setRecordAutoStop(false);  //设置VAD
        mNlsClient.setMinVoiceValueInterval(200); //设置音量回调时长

    }

    private NlsRequest initNlsRequest(Context context){
        NlsRequestProto proto = new NlsRequestProto(context.getApplicationContext());
        proto.setApp_user_id("xxx"); //设置在应用中的用户名，可选
        return new NlsRequest(proto);

    }

    private void openLog(boolean b){
        NlsClient.openLog(b);
    }

    /**
     *  开始语音识别
     */
    private void startRecognize(){
        //请替换为用户申请到的数加认证key和密钥
        mNlsRequest.authorize(accessKey,accessSecret);
        mNlsClient.start();
    }

    /**
     *  停止语音识别
     */
    private void stopRecognize(){
        mNlsClient.stop();
    }
}
