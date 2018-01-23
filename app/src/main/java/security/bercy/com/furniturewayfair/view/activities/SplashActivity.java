package security.bercy.com.furniturewayfair.view.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import security.bercy.com.furniturewayfair.R;

public class SplashActivity extends AppCompatActivity {


    @BindView(R.id.leader_img)
    ViewPager mLeaderImg;
    @BindView(R.id.leader_circle)
    AutoLinearLayout mLeaderCircle;
    @BindView(R.id.leader_red)
    ImageView mLeaderRed;

    private List<View> mViewList;
    private View mView;
    private int left;
    public static SplashActivity instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);
        instance = this;
        init();
    }
    private void init()
    {
        initCircles();
        mLeaderImg.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {
                float leftMargin = left * (position + positionOffset);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mLeaderRed.getLayoutParams();
                params.leftMargin = Math.round(leftMargin);
                mLeaderRed.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                final ImageView simple = mViewList.get(position).findViewById(R.id.leader_img);
                switch (position)
                {
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
            public void onPageScrollStateChanged(int state)
            {

            }
        });


    }

    private void initCircles()
    {
        mViewList = new ArrayList<>();
        for (int i = 0; i < 4; i++)
        {
            mView = LayoutInflater.from(this).inflate(R.layout.item_leader_viewpager, null);
            mViewList.add(mView);
        }

        mLeaderRed.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                left = mLeaderCircle.getChildAt(1).getLeft() - mLeaderCircle.getChildAt(0).getLeft();
            }
        });

    }
}
