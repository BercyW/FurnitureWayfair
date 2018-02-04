package security.bercy.com.furniturewayfair.view.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import security.bercy.com.furniturewayfair.R;

public class DetailActivity extends AppCompatActivity {

    ViewPager mLeaderImg;

    AutoLinearLayout mLeaderCircle;
    ImageView viewInRoom;
    ImageView mLeaderRed;


    private List<View> mViewList;
    private View mView;
    private int left;
    public static DetailActivity instance = null;
    private ImageView simple;
    private TextView continueAsGuest;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mLeaderImg = findViewById(R.id.activity_leader_img);
        mLeaderCircle = findViewById(R.id.activity_circle);
        mLeaderRed = findViewById(R.id.activity_red);
        instance = this;
        mLeaderImg.setOffscreenPageLimit(1);
        init();
        viewInRoom = findViewById(R.id.view_in_room);
        viewInRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this,ViewInRoomActivity.class);
                startActivity(intent);
            }
        });


    }


    private void init() {
        initCircles();
        simple = mViewList.get(0).findViewById(R.id.leader_img);
        simple.setImageResource(R.drawable.detail_bed);
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
                        simple.setImageResource(R.drawable.detail_bed);
                        break;
                    case 1:
                        simple.setImageResource(R.drawable.detail_bed);
                        break;
                    case 2:
                        simple.setImageResource(R.drawable.detail_bed2);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mLeaderImg.setAdapter(new DetailActivity.LeaderAdapter());
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

            View view=mViewList.get(position);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            view.setLayoutParams(params);
            container.addView(view,0);


           // container.addView(mViewList.get(position));
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView(mViewList.get(position));
        }
    }
}
