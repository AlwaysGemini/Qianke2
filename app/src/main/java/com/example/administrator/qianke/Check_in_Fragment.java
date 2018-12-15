package com.example.administrator.qianke;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Check_in_Fragment extends Fragment implements View.OnClickListener {

    private QMUIRoundButton check_in;
    private SimpleDateFormat simpleDateFormat;
    //时间刷新记时器
    private Timer timeTimer;
    //时间记时器任务
    private TimerTask timeTimerTask;
    //消息助手
    private Handler messageHandler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.check_in_fragment, null);

        check_in = view.findViewById(R.id.check_in);
        check_in.setOnClickListener(this);

        simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        messageHandler = new MessageHandler();
        initTimeTimer();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 初始化时间计时器
     */
    public void initTimeTimer() {
        timeTimer = new Timer(true);
        timeTimerTask = new TimeTimerTask();
        if (null != timeTimer) {
            timeTimer.schedule(timeTimerTask, 1 * 1000);
        }
    }

    /**
     * 取消时间计时器
     */
    public void removeTimeTimerTask() {
        if (null != timeTimer && null != timeTimerTask) {
            timeTimerTask.cancel();
        }
    }

    /**
     * 时间刷新任务
     */
    class TimeTimerTask extends TimerTask {
        @Override
        public void run() {
            Message message = new Message();
            Bundle bundle = new Bundle();// 存放数据
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            ;//设置日期格式
            String currencyTime = df.format(new Date());
            bundle.putString("currencyTime", currencyTime);
            message.setData(bundle);
            messageHandler.sendMessage(message);
        }
    }

    int flag = 1;

    /**
     * 刷新时间
     *
     * @author Administrator
     */
    class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            // 此处可以更新UI
            Bundle bundle = msg.getData();
            if (flag == 1) {
                check_in.setText("签到\n" + simpleDateFormat.format(new Date()));
            } else {
                check_in.setText("签退\n" + simpleDateFormat.format(new Date()));
            }

            check_in.invalidate();
            initTimeTimer();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_in:
                new QMUIDialog.MessageDialogBuilder(getActivity())
                        .setTitle("标题")
                        .setMessage("确定要发送吗？")
                        .addAction("取消", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                dialog.dismiss();
                            }
                        })
                        .addAction("确定", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                dialog.dismiss();
                                Toast.makeText(getActivity(), "发送成功", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                break;
        }
    }
}
