package com.lifehelper.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.lifehelper.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jsion on 16/3/17.
 */
public class RouteLineLocationFragment extends BaseFragment {
    @Bind(R.id.iv_switch_location)
    ImageView mSwitchLocation;
    @Bind(R.id.et_start_location)
    EditText mStartAddress;
    @Bind(R.id.et_end_location)
    EditText mTargetAddress;

    @OnClick(R.id.et_start_location)
    void startAddGetFocus() {
        mStartAddress.setFocusable(true);
        mStartAddress.setFocusableInTouchMode(true);
        mTargetAddress.setFocusable(false);
        mTargetAddress.setFocusableInTouchMode(false);
    }

    @OnClick(R.id.et_end_location)
    void endAddGetFocus() {
        mStartAddress.setFocusable(false);
        mStartAddress.setFocusableInTouchMode(false);
        mTargetAddress.setFocusable(true);
        mTargetAddress.setFocusableInTouchMode(true);
    }


    public interface OnGetFragmentValueListener {
        void startAddChanged(String startAdd);

        void targetAddChanged(String targetAdd);
    }

    private OnGetFragmentValueListener onGetFragmentValueListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_route_line_location, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        mTargetAddress.setTag(ET_TAG._TARGET);
        mStartAddress.setTag(ET_TAG._START);

        mTargetAddress.addTextChangedListener(new MyTextWatcher(mTargetAddress));
        mStartAddress.addTextChangedListener(new MyTextWatcher(mStartAddress));
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnGetFragmentValueListener) {
            onGetFragmentValueListener = (OnGetFragmentValueListener) activity;
        } else {
            throw new IllegalStateException("Your activity must impl the callback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onGetFragmentValueListener = null;
    }

    class MyTextWatcher implements TextWatcher {
        private EditText editText;

        public MyTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch ((int) editText.getTag()) {
                case ET_TAG._START:
                    onGetFragmentValueListener.startAddChanged(s.toString());
                    break;
                case ET_TAG._TARGET:
                    onGetFragmentValueListener.targetAddChanged(s.toString());
                    break;
            }
        }
    }

    public static class ET_TAG {
        public static final int _START = 44;
        public static final int _TARGET = 45;
    }


}
