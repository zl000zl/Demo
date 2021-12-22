package com.example.myapp.start;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapp.MainActivity;
import com.example.myapp.R;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {

    private ViewPager ydViewPager;
    private TextView ydSet;
    private Button ydTiyan;
    private LinearLayout ydDots;

    private int currentIndex = 0;
    private int[] image = {R.drawable.yingdao_1,R.drawable.yingdao_2,R.drawable.yingdao_3
            ,R.drawable.yingdao_4,R.drawable.yingdao_5};
    ArrayList<ImageView> imageViews = new ArrayList<>();

    int a=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        getSupportActionBar().hide();
        initView();
        initData();
    }

    private void initData() {
        for (int img: image){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(img);
            //用来设置imageView 图片
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
        }
        ydViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageViews.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                //将图片进行缓存
                ImageView imageView = imageViews.get(position);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                //只缓存了三张图片，如果滑动的图片超出了缓存的范围的话  就销毁
                container.removeView(imageViews.get(position));
            }
        });
        for (int i = 0;i<imageViews.size();i++){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30,30);
            View view = new View(this);
            view.setBackgroundResource(R.drawable.select_dots);
            //是用来设置View的背景图片
            view.setEnabled(false);
            if (i !=0){
                //滑动的时候i就有了值 所以设置点与点的距离为15dp
                layoutParams.leftMargin=15;
            }else view.setEnabled(true);{
                ydDots.addView(view,layoutParams);
            }
        }
        ydViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //当界面滑动的时候调用这个方法
                if (position==imageViews.size()-1){
                    //一共有5张图片 而下标是从0开始的所以要减一
                    ydSet.setVisibility(View.VISIBLE);
                    ydTiyan.setVisibility(View.VISIBLE);
                }else {
                    ydSet.setVisibility(View.INVISIBLE);
                    ydTiyan.setVisibility(View.INVISIBLE);
                }
                //这一步是设置小圆点根据你滑动而滑动
                ydDots.getChildAt(currentIndex).setEnabled(false);
                //第一张图片的小圆点为不显示
                ydDots.getChildAt(position).setEnabled(true);
                //根据滑动显示
                currentIndex=position;
                //定义的下标就等于ViewPager的下标值

                ydTiyan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (a==1){
                            Toast.makeText(GuideActivity.this, "请先设置网络！！！", Toast.LENGTH_SHORT).show();
                        }else {
                            startActivity(new Intent(GuideActivity.this, MainActivity.class));
                        }
                    }
                });
                ydSet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        a=a+1;
                        startActivity(new Intent(GuideActivity.this,Port_Activity.class));
                    }
                });
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        ydViewPager = (ViewPager) findViewById(R.id.yd_viewPager);
        ydSet = (TextView) findViewById(R.id.yd_set);
        ydTiyan = (Button) findViewById(R.id.yd_tiyan);
        ydDots = (LinearLayout) findViewById(R.id.yd_dots);
    }
}