package security.bercy.com.furniturewayfair.view.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import security.bercy.com.furniturewayfair.R;
import security.bercy.com.furniturewayfair.view.fragment.AccountFragment;
import security.bercy.com.furniturewayfair.view.fragment.HomeFragment;
import security.bercy.com.furniturewayfair.view.fragment.MyboardFragment;
import security.bercy.com.furniturewayfair.view.fragment.SalesFragment;

public class MainActivity extends AppCompatActivity {

    //bindview
    @BindView(R.id.img_home)
    ImageView tv_home;
    @BindView(R.id.img_sales)
    ImageView tv_sales;
    @BindView(R.id.img_myboard)
    ImageView tv_myboard;
    @BindView(R.id.img_account)
    ImageView tv_account;


    //4 fragments
    private HomeFragment homeFragment;
    private SalesFragment salesFragment;
    private MyboardFragment myboardFragment;
    private AccountFragment accountFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        init();


    }



    private void init() {
        showFragment(0);
        tv_home.setBackgroundResource(R.drawable.home);


    }

    @OnClick({R.id.parent_home, R.id.parent_sales, R.id.parent_myboard, R.id.parent_account})
    public void onClickViewed(View view) {
        reSetBackground();
        switch (view.getId()) {
            case R.id.parent_home:
                showFragment(0);
                tv_home.setBackgroundResource(R.drawable.home);
                break;
            case R.id.parent_sales:
                showFragment(1);

                tv_sales.setBackgroundResource(R.drawable.salestouming);
                break;
            case R.id.parent_myboard:
                showFragment(2);
                tv_myboard.setBackgroundResource(R.drawable.myboardtouming);
                break;
            case R.id.parent_account:
                showFragment(3);
                tv_account.setBackgroundResource(R.drawable.accounttouming);
                break;
            default:
                break;
        }


    }


    private void showFragment(int position) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);

        switch (position) {
            case 0:
                if (homeFragment != null) {
                    fragmentTransaction.show(homeFragment);
                } else {
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.main_fragment, homeFragment);
                }
                break;
            case 1:
                if (salesFragment != null) {
                    fragmentTransaction.show(salesFragment);
                } else {
                    salesFragment = new SalesFragment();
                    fragmentTransaction.add(R.id.main_fragment, salesFragment);
                }
                break;
            case 2:
                if (myboardFragment != null) {
                    fragmentTransaction.show(myboardFragment);
                } else {
                    myboardFragment = new MyboardFragment();
                    fragmentTransaction.add(R.id.main_fragment, myboardFragment);
                }
                break;
            case 3:
                if (accountFragment != null) {
                    fragmentTransaction.show(accountFragment);
                } else {
                    accountFragment = new AccountFragment();
                    fragmentTransaction.add(R.id.main_fragment, accountFragment);
                }
                break;
            default:
                break;

        }
        fragmentTransaction.commit();


    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (salesFragment != null) {
            fragmentTransaction.hide(salesFragment);
        }
        if (myboardFragment != null) {
            fragmentTransaction.hide(myboardFragment);
        }
        if (accountFragment != null) {
            fragmentTransaction.hide(accountFragment);
        }

    }

    private void reSetBackground() {
        tv_home.setBackgroundResource(R.drawable.hometouming);
        tv_sales.setBackgroundResource(R.drawable.sales);
        tv_myboard.setBackgroundResource(R.drawable.myboard);
        tv_account.setBackgroundResource(R.drawable.account);

    }
}
