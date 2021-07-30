package cn.example.myapplication.utils;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 基于AudioRecord的录音工具类
 * 使用方法：
 * 先调用{@link #init(String, String)}设置生成的录音文件目录
 * 再调用{@link #start()}开始录音
 * 调用{@link #stop()}停止录音
 */
public class AudioRecordTool {
    private AudioRecord audioRecord;
    private int recordBufSize = 0;
    private byte data[];
    private PcmToWavTool tool;
    private boolean isRecording = false;
    //录音得到的文件 的储存位置及文件名
    private String pcmFileName = Environment.getExternalStorageDirectory() + "/Download/record.pcm";
    //转换成wav文件后新文件的存储位置及文件名
    private String wavFileName = Environment.getExternalStorageDirectory() + "/Download/record1.wav";
    // 音频源：音频输入-麦克风
    private final static int AUDIO_INPUT = MediaRecorder.AudioSource.MIC;
    // 采样率：音频的采样频率，每秒钟能够采样的次数，采样率越高，音质越高
    // 44100是目前的标准，但是某些设备仍然支持22050，16000，11025
    // 采样频率一般共分为22.05KHz、44.1KHz、48KHz三个等级
    private final static int AUDIO_SAMPLE_RATE = 44100;

    // 声道设置：android支持双声道立体声和单声道。MONO单声道，STEREO立体声
    private final static int AUDIO_CHANNEL = AudioFormat.CHANNEL_IN_MONO;

    // 编码制式和采样大小：采集来的数据当然使用PCM编码
    // (脉冲代码调制编码，即PCM编码。PCM通过抽样、量化、编码三个步骤将连续变化的模拟信号转换为数字编码。)
    // android支持的采样大小16bit 或者8bit。当然采样大小越大，那么信息量越多，音质也越高，现在主流的采样
    // 大小都是16bit，在低质量的语音传输的时候8bit 足够了。
    private final static int AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;

    /**
     * 录音文件的完整路径名
     * @param pcmFilePath pcm文件路径  例如：Environment.getExternalStorageDirectory() + "/Download/record.pcm";
     * @param wavFilePath pcm转的wav文件路径  例如：Environment.getExternalStorageDirectory() + "/Download/record1.wav"
     */
    public void init(String pcmFilePath, String wavFilePath){
        pcmFileName = pcmFilePath;
        wavFileName = wavFilePath;
    }

    //初始化
    private void createAudioRecord() {
        recordBufSize = AudioRecord.getMinBufferSize(AUDIO_SAMPLE_RATE, AUDIO_CHANNEL, AUDIO_ENCODING);  //audioRecord能接受的最小的buffer大小
        //构造方法，传入的参数上面在有解析
        audioRecord = new AudioRecord(AUDIO_INPUT, AUDIO_SAMPLE_RATE, AUDIO_CHANNEL, AUDIO_ENCODING, recordBufSize);
        data = new byte[recordBufSize];
        tool = new PcmToWavTool(AUDIO_SAMPLE_RATE, AUDIO_CHANNEL, AUDIO_ENCODING);
    }

    //开始录音
    public void start() {
        Log.i("AUDIO", "开始录音");
        if(audioRecord == null){
            createAudioRecord();
        }
        isRecording = true;
        //调用startRecording()方法开始录制
        audioRecord.startRecording();
        MyThread myThread = new MyThread();
        myThread.start();
    }


    /**
     * 停止录音
     */
    public void stop() {
        isRecording = false;
        if (audioRecord != null) {
            Log.i("AUDIO", "停止录音");
            //调用stop()方法停止录制
            audioRecord.stop();
            //调用release() 释放本机录音资源。
            audioRecord.release();
            audioRecord = null;

            //利用自定义工具类将pcm格式的文件转换为wav格式文件才能进行播放
            tool.pcmToWav(pcmFileName, wavFileName);
        }
    }

    private class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            FileOutputStream os = null;
            try {
                //如果文件不存在，就创建文件
                if (!new File(pcmFileName).exists()) {
                    new File(pcmFileName).createNewFile();
                }
                os = new FileOutputStream(pcmFileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (null != os) {
                while (isRecording) {
                    //调用read(@NonNull byte[] audioData, int offsetInBytes, int sizeInBytes)方法
                    // 从音频硬件读取音频数据，以便记录到字节数组中。
                    int read = audioRecord.read(data, 0, recordBufSize);

                    // 如果读取音频数据没有出现错误，就将数据写入到文件
                    if (AudioRecord.ERROR_INVALID_OPERATION != read) {
                        try {
                            os.write(data);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                try {
                    //关闭文件
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}