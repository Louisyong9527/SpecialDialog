/*
 * Copyright (C) 2016 venshine.cn@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.special.wheelview;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.special.wheelview.adapter.ArrayWheelAdapter;
import com.special.wheelview.common.WheelConstants;
import com.special.wheelview.widget.WheelView;

import java.util.Arrays;
import java.util.List;


/**
 * 滚轮对话框
 *
 * @author venshine
 */
public class SpcialDialog<T> {

    private TextView mTitle;

    private ImageView mLine1, mLine2;

    private WheelView<T> mWheelView;

    private WheelView.WheelViewStyle mStyle;

    private TextView mButtonOk;

    private AlertDialog mDialog;

    private Context mContext;

    private OnDialogItemClickListener mOnDialogItemClickListener;

    private int mSelectedPos;

    private T mSelectedText;
    private Button mButtonCancle;

    public SpcialDialog(Context context) {
        mContext = context;
        init();
    }

    private void init() {

        mDialog = new AlertDialog.Builder(mContext).create();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.dialog_layout, null);

        mTitle = (TextView) view.findViewById(R.id.tv_title);
        mWheelView = (WheelView) view.findViewById(R.id.wv_wheelview);
        mButtonOk = (Button) view.findViewById(R.id.btn_button_ok);
        mButtonCancle = (Button) view.findViewById(R.id.btn_button_cancle);
        mWheelView.setWheelAdapter(new ArrayWheelAdapter(mContext));
        mWheelView.setSkin(WheelView.Skin.Holo);
        mStyle = new WheelView.WheelViewStyle();
        mStyle.textColor = WheelConstants.WHEEL_TEXT_COLOR;
        //mStyle.selectedTextColor=R.color.gold_1;
        mStyle.selectedTextZoom = 1.2f;
        mStyle.textAlpha = 1.0f;
        mWheelView.setStyle(mStyle);
        mWheelView.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener<T>() {
            @Override
            public void onItemSelected(int position, T text) {
                mSelectedPos = position;
                mSelectedText = text;
            }
        });

        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //确定按钮
                dismiss();

                if (null != mOnDialogItemClickListener) {
                    mOnDialogItemClickListener.onItemClick(mSelectedPos, mSelectedText,true);
                }
            }
        });
        mButtonCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //取消按钮
                dismiss();

                if (null != mOnDialogItemClickListener) {
                    mOnDialogItemClickListener.onItemClick(mSelectedPos, mSelectedText,false);
                }

            }
        });


        mDialog.setView(view);
        mDialog.setCanceledOnTouchOutside(true);
    }

    /**
     * 点击事件
     *
     * @param onDialogItemClickListener
     * @return
     */
    public SpcialDialog setOnDialogItemClickListener(OnDialogItemClickListener onDialogItemClickListener) {
        mOnDialogItemClickListener = onDialogItemClickListener;
        return this;
    }

    /**
     * 设置dialog外观颜色
     *
     * @param color
     * @return
     */
    public SpcialDialog setDialogStyle(int color) {
        mTitle.setTextColor(color);
        //mLine1.setBackgroundColor(color);
        //mLine2.setBackgroundColor(color);
        mStyle.selectedTextColor = color;

        return this;
    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public SpcialDialog setTitle(String title) {
        mTitle.setText(title);
        return this;
    }

    /**
     * 设置标题颜色
     *
     * @param color
     * @return
     */
    public SpcialDialog setTextColor(int color) {
        mTitle.setTextColor(color);
        return this;
    }

    /**
     * 设置标题大小
     *
     * @param size
     * @return
     */
    public SpcialDialog setTextSize(int size) {
        mTitle.setTextSize(size);
        return this;
    }

    /**
     * 设置确定按钮文本
     *
     * @param text
     * @return
     */
    public SpcialDialog setButtonText(String text) {
        mButtonOk.setText(text);
        return this;
    }

    /**
     * 设置取消按钮文本
     *
     * @param text
     * @return
     */
    public SpcialDialog setCancleButtonText(String text) {
        mButtonCancle.setText(text);
        return this;
    }

    /**
     * 设置按钮文本颜色
     *
     * @param color
     * @return
     */
    public SpcialDialog setButtonColor(int color) {
        mButtonOk.setTextColor(color);
        return this;
    }

    /**
     * 设置按钮文本尺寸
     *
     * @param size
     * @return
     */
    public SpcialDialog setButtonSize(int size) {
        mButtonOk.setTextSize(size);
        return this;
    }

    /**
     * 设置数据项显示个数
     *
     * @param count
     */
    public SpcialDialog setCount(int count) {
        mWheelView.setWheelSize(count);
        return this;
    }

    /**
     * 数据项是否循环显示
     *
     * @param loop
     */
    public SpcialDialog setLoop(boolean loop) {
        mWheelView.setLoop(loop);
        return this;
    }

    /**
     * 设置数据项显示位置
     *
     * @param selection
     */
    public SpcialDialog setSelection(int selection) {
        mWheelView.setSelection(selection);
        return this;
    }

    /**
     * 设置数据项
     *
     * @param arrays
     */
    public SpcialDialog setItems(T[] arrays) {
        return setItems(Arrays.asList(arrays));
    }

    /**
     * 设置数据项
     *
     * @param list
     */
    public SpcialDialog setItems(List<T> list) {
        mWheelView.setWheelData(list);
        return this;
    }

    /**
     * 显示
     */
    public SpcialDialog show() {
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
        return this;
    }

    /**
     * 隐藏
     */
    public SpcialDialog dismiss() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
        return this;
    }

//    @Override
//    public void onClick(View v) {
//        dismiss();
//
//        if (null != mOnDialogItemClickListener) {
//            mOnDialogItemClickListener.onItemClick(mSelectedPos, mSelectedText);
//        }
//    }

    public interface OnDialogItemClickListener<T> {
        void onItemClick(int position, T s,boolean clickType);
    }
}
