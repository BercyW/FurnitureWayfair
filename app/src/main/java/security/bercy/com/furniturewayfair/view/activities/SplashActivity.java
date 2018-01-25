package security.bercy.com.furniturewayfair.view.activities;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import security.bercy.com.furniturewayfair.R;

public class SplashActivity extends AppCompatActivity {


    ViewPager mLeaderImg;

    AutoLinearLayout mLeaderCircle;

    ImageView mLeaderRed;

    private List<View> mViewList;
    private View mView;
    private int left;
    public static SplashActivity instance = null;
    private ImageView simple;
    private TextView continueAsGuest;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mLeaderImg = findViewById(R.id.leader_img);
        mLeaderCircle = findViewById(R.id.leader_circle);
        mLeaderRed = findViewById(R.id.leader_red);
        continueAsGuest = findViewById(R.id.leader_continue_as_guest);
        instance = this;
        init();

    }


    private void init() {
        initCircles();
        simple = mViewList.get(0).findViewById(R.id.leader_img);
        simple.setImageResource(R.drawable.splash0);
        mLeaderImg.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                float leftMargin = left * (position + positionOffset);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mLeaderRed.getLayoutParams();
                params.leftMargin = Math.round(leftMargin);
                mLeaderRed.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {

                simple = mViewList.get(position).findViewById(R.id.leader_img);
                switch (position) {
                    case 0:
                        simple.setImageResource(R.drawable.splash0);
                        break;
                    case 1:
                        simple.setImageResource(R.drawable.splash2);
                        break;
                    case 2:
                        simple.setImageResource(R.drawable.splash3);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mLeaderImg.setAdapter(new LeaderAdapter());
    }

    private void initCircles() {
        mViewList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mView = LayoutInflater.from(this).inflate(R.layout.item_leader_viewpager, null);
            mViewList.add(mView);
        }

        mLeaderRed.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                left = mLeaderCircle.getChildAt(1).getLeft() - mLeaderCircle.getChildAt(0).getLeft();
            }
        });

    }

    private class LeaderAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    @OnClick({R.id.leader_register, R.id.leader_login, R.id.leader_continue_as_guest})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.leader_register:
                Log.d("main", "onViewClicked: ");
                break;
            case R.id.leader_login:
                break;
            case R.id.leader_continue_as_guest:
                Log.d("splash", "onViewClicked: ");
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                break;
            default:
                break;

        }

    }


}
