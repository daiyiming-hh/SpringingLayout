package dym.unique.com.springinglayoutsample;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import dym.unique.com.springinglayoutlibrary.handler.SpringTouchRippleHandler;
import dym.unique.com.springinglayoutlibrary.handler.SpringingAlphaHideHandler;
import dym.unique.com.springinglayoutlibrary.handler.SpringingAlphaShowHandler;
import dym.unique.com.springinglayoutlibrary.handler.SpringingNotificationJumpHandler;
import dym.unique.com.springinglayoutlibrary.handler.SpringingNotificationRotateHandler;
import dym.unique.com.springinglayoutlibrary.handler.SpringingTouchDragHandler;
import dym.unique.com.springinglayoutlibrary.handler.SpringingTouchPointHandler;
import dym.unique.com.springinglayoutlibrary.handler.SpringingTouchScaleHandler;
import dym.unique.com.springinglayoutlibrary.handler.SpringingTranslationHideHandler;
import dym.unique.com.springinglayoutlibrary.handler.SpringingTranslationShowHandler;
import dym.unique.com.springinglayoutlibrary.view.SpringingEditText;
import dym.unique.com.springinglayoutlibrary.view.SpringingImageView;
import dym.unique.com.springinglayoutlibrary.view.SpringingTextView;
import dym.unique.com.springinglayoutlibrary.viewgroup.SpringingLinearLayout;
import dym.unique.com.springinglayoutlibrary.viewgroup.SpringingRelativeLayout;


public class RegisterActivity extends Activity implements View.OnClickListener {

    private SpringingRelativeLayout srl_actionBar = null;
    private SpringingImageView simg_back = null;
    private SpringingLinearLayout sll_mainContainer = null;
    private SpringingImageView simg_avatarMan = null;
    private SpringingEditText sedt_account = null;
    private SpringingEditText sedt_password = null;
    private SpringingTextView stv_regist = null;
    private SpringingTextView stv_login = null;

    private boolean isContentShow = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findView();
        initSpringLayout();
        initEvent();
        showViews();
    }

    private void findView() {
        srl_actionBar = (SpringingRelativeLayout) findViewById(R.id.srl_actionBar);
        simg_back = (SpringingImageView) findViewById(R.id.simg_back);
        sll_mainContainer = (SpringingLinearLayout) findViewById(R.id.sll_mainContainer);
        simg_avatarMan = ((SpringingImageView) findViewById(R.id.simg_avatarMan)).setIsCircleImage(true);
        sedt_account = (SpringingEditText) findViewById(R.id.sedt_account);
        sedt_password = (SpringingEditText) findViewById(R.id.sedt_password);
        stv_regist = (SpringingTextView) findViewById(R.id.stv_regist);
        stv_login = (SpringingTextView) findViewById(R.id.stv_login);
    }

    private void initSpringLayout() {
        srl_actionBar.getSpringingHandlerController().addSpringingHandler(new SpringTouchRippleHandler(this, srl_actionBar).setOnlyOnChildren(true, simg_back));
        simg_back.getSpringingHandlerController().addSpringingHandler(new SpringingTouchPointHandler(this, simg_back).setAngle(SpringingTouchPointHandler.ANGLE_LEFT));
        sll_mainContainer.getSpringingHandlerController().addSpringingHandler(new SpringingTouchDragHandler(this, sll_mainContainer).setBackInterpolator(new OvershootInterpolator()).setBackDuration(SpringingTouchDragHandler.DURATION_LONG).setDirection(SpringingTouchDragHandler.DIRECTOR_BOTTOM | SpringingTouchDragHandler.DIRECTOR_TOP).setMinDistance(0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics())));
        sll_mainContainer.getSpringingHandlerController().addSpringingHandler(new SpringTouchRippleHandler(this, sll_mainContainer).setOnlyOnChildren(true, sedt_account, sedt_password));
        simg_avatarMan.getSpringingHandlerController().addSpringingHandler(new SpringingTouchScaleHandler(this, simg_avatarMan));
        stv_regist.getSpringingHandlerController().addSpringingHandler(new SpringTouchRippleHandler(this, stv_regist));
        stv_login.getSpringingHandlerController().addSpringingHandler(new SpringTouchRippleHandler(this, stv_login));
    }

    private void initEvent() {
        stv_regist.setOnClickListener(this);
        stv_login.setOnClickListener(this);
        simg_avatarMan.setOnClickListener(this);
        simg_back.setOnClickListener(this);
    }

    private void showViews() {
        new SpringingAlphaShowHandler(this, sll_mainContainer).showChildrenSequence(500, 100);
        new SpringingTranslationShowHandler(this, sll_mainContainer).showChildrenSequence(500, 100);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stv_regist: {
                if (sedt_account.getText().toString().equals("")) {
                    new SpringingNotificationJumpHandler(this, sedt_account).start(1);
                    return;
                }
                if (sedt_password.getText().toString().equals("")) {
                    new SpringingNotificationJumpHandler(this, sedt_password).start(1);
                    return;
                }
                new SpringingNotificationRotateHandler(this, simg_avatarMan).start(1);
            }
            break;
            case R.id.stv_login: {
                startActivity(new Intent(this, ListActivity.class));
            }
            break;
            case R.id.simg_avatarMan: {
                Toast.makeText(this, "设置头像", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.simg_back: {
                if (isContentShow) {
                    new SpringingAlphaHideHandler(this, sll_mainContainer).hideChildrenSequence(View.INVISIBLE, 100);
                    new SpringingTranslationHideHandler(this, sll_mainContainer).hideChildrenSequence(View.INVISIBLE, 100);
                } else {
                    new SpringingAlphaShowHandler(this, sll_mainContainer).showChildrenSequence(100);
                    new SpringingTranslationShowHandler(this, sll_mainContainer).showChildrenSequence(100);
                }
                isContentShow = !isContentShow;
            }
            break;
        }
    }
}






















