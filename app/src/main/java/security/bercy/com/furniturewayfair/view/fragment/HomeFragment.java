package security.bercy.com.furniturewayfair.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import security.bercy.com.furniturewayfair.R;


public class HomeFragment extends Fragment {

    ViewPager mLeaderImg;
    AutoLinearLayout mLeaderCircle;
    ImageView mLeaderRed;
    private ImageView simple;
    private View mView;
    private int left;
    private List<View> mViewList;
    public static HomeFragment instance = null;
    private View view;

    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);
        mLeaderImg = view.findViewById(R.id.fragment_leader_img);
        mLeaderCircle = view.findViewById(R.id.fragment_circle);
        mLeaderRed = view.findViewById(R.id.fragment_red);
        instance = this;
        init();
        return view;
    }


    private void init() {
        initCircles();
        simple = mViewList.get(0).findViewById(R.id.leader_img);



        simple.setImageResource(R.drawable.homebanner1);
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
                        simple.setImageResource(R.drawable.homebanner1);
                        break;
                    case 1:
                        simple.setImageResource(R.drawable.homebanner2);
                        break;
                    case 2:
                        simple.setImageResource(R.drawable.homebanner3);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mLeaderImg.setAdapter(new fragmentAdapter());
    }

    private void initCircles() {
        mViewList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mView = LayoutInflater.from(view.getContext()).inflate(R.layout.item_leader_viewpager, null);
            mViewList.add(mView);
        }

        mLeaderRed.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                left = mLeaderCircle.getChildAt(1).getLeft() - mLeaderCircle.getChildAt(0).getLeft();
            }
        });

    }

    private class fragmentAdapter extends PagerAdapter {
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


}
