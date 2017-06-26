package com.shining.p024_wifidemoactivity.widget;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shining.p024_wifidemoactivity.R;
import com.shining.p024_wifidemoactivity.util.WifiUtil;


/**
 * @function: 设置wifi中，wifi密码弹窗
 * @description:
 * @history: 1.  date:2016/2/16 14:43
 * author:PengLiang
 * modification:
 */
public class SetApPwdDialog extends DialogFragment implements View.OnClickListener {
    private EditText mEdPassword;
    private IConnectWifi mConnectWifi = null;
    private String SSID;
    private String pwd;
    private String capabilities;
    private WifiUtil.WifiCipherType mType;
    private FragmentActivity mActivity;
    private ImageView iv_eyes;


    public SetApPwdDialog() {
        // TODO Auto-generated constructor stub
    }


    public interface IConnectWifi {

        void onConnectClick(String SSID, String pwd, WifiUtil.WifiCipherType mType);
    }


    public static SetApPwdDialog newInstance(IConnectWifi mConnectWifi, String SSID, WifiUtil.WifiCipherType mType, String capabilities) {

        SetApPwdDialog mFragment = new SetApPwdDialog();

        mFragment.SSID = SSID;

        mFragment.mType = mType;

        mFragment.mConnectWifi = mConnectWifi;
        mFragment.capabilities = capabilities;

        return mFragment;
    }

    int num = 8;

    Button btSure;
    Button btCancel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.from(mActivity).inflate(R.layout.set_ap_pwd_page, null);
        TextView ssidName = (TextView) view.findViewById(R.id.textView60);
        ssidName.setText(SSID);
        mEdPassword = (EditText) view.findViewById(R.id.editText1);
        iv_eyes = (ImageView) view.findViewById(R.id.iv_eyes);
        btCancel = (Button) view.findViewById(R.id.button12);
        btSure = (Button) view.findViewById(R.id.button13);
        btCancel.setOnClickListener(this);
        btSure.setOnClickListener(this);
        iv_eyes.setOnClickListener(this);
        setPasswordListen();
        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button12://button cancel
                hideKeyBoard();
                this.dismiss();
                break;
            case R.id.button13://button sure
                String pass = mEdPassword.getText().toString().trim();
                if (mConnectWifi != null) {
                    mConnectWifi.onConnectClick(SSID, pass, mType);
                }
//                WifiDatabaseUtils.save(SSID, capabilities, pass);
                hideKeyBoard();
                this.dismiss();

                break;
            case R.id.iv_eyes:
                if (iv_eyes.isSelected()) {
                    mEdPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    iv_eyes.setSelected(false);
                    String password = mEdPassword.getText().toString();
                    mEdPassword.setSelection(password.length());
                } else {
                    mEdPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    iv_eyes.setSelected(true);
                    String password = mEdPassword.getText().toString();
                    mEdPassword.setSelection(password.length());
                }

                break;
        }
    }

    private void setPasswordListen() {
        mEdPassword.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >= num) {
                    btSure.setBackgroundResource(R.drawable.btn_common_enable);
                    btSure.setEnabled(true);

                } else {
                    btSure.setEnabled(false);
                    btSure.setBackgroundResource(R.drawable.btn_common_disenable);

                }
            }
        });

    }

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        try {
            mActivity = (FragmentActivity) activity;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void show(FragmentActivity mActivity, IConnectWifi mConnectWifi, String SSID, WifiUtil.WifiCipherType mType, String capabilities) {
        FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();

        Fragment mBefore = mActivity.getSupportFragmentManager().findFragmentByTag(SetApPwdDialog.class.getSimpleName());

        if (mBefore != null) {

            ((DialogFragment) mBefore).dismiss();

            ft.remove(mBefore);
        }

//        ft.addToBackStack(null);

        DialogFragment mNow = SetApPwdDialog.newInstance(mConnectWifi, SSID, mType, capabilities);

        mNow.show(ft, SetApPwdDialog.class.getSimpleName());
    }

    public void hideKeyBoard() {
//        if (mActivity.getCurrentFocus() != null) {
//            InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(),
//                    InputMethodManager.HIDE_NOT_ALWAYS);
//        }
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
