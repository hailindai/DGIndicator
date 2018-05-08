package com.dreamguard.dgindicator;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

public class MainActivity extends AppCompatActivity {

    DGIndicator indicador;
    ViewPager viewPager;

    MagicIndicator magicIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        indicador = (DGIndicator) findViewById(R.id.indicador);
        magicIndicator = (MagicIndicator)findViewById(R.id.magic_indicator);
        final String[] titles = new String[]{"推荐", "热血街舞团", "3D大片", "VR", "福利","电影","电视剧","综艺","动漫"};
        indicador.setTitles(titles);

        viewPager = (ViewPager) findViewById(R.id.pager);

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            //返回的是fragment对象的个数
            @Override
            public int getCount() {
                return titles.length;
            }
            //根据索引去获取fragment对象
            @Override
            public Fragment getItem(int arg0) {
                Fragment baseFragment = null;
                Bundle bundle = new Bundle();

                switch (arg0) {
                    case 0:
                        BlankFragment blankFragment = new BlankFragment();
                        bundle.putCharSequence("key","1");
                        blankFragment.setArguments(new Bundle());
                        return blankFragment;
                    case 1:
                        BlankFragment blankFragment2 = new BlankFragment();
                        bundle.putCharSequence("key","2");
                        blankFragment2.setArguments(new Bundle());
                        return blankFragment2;
                    case 2:
                        BlankFragment blankFragment3 = new BlankFragment();
                        bundle.putCharSequence("key","3");
                        blankFragment3.setArguments(new Bundle());
                        return blankFragment3;
                    case 3:
                        BlankFragment blankFragment4 = new BlankFragment();
                        bundle.putCharSequence("key","4");
                        blankFragment4.setArguments(new Bundle());
                        return blankFragment4;
                    case 4:
                        BlankFragment blankFragment5 = new BlankFragment();
                        bundle.putCharSequence("key","5");
                        blankFragment5.setArguments(new Bundle());
                        return blankFragment5;

                }
                BlankFragment blankFragment = new BlankFragment();
                bundle.putCharSequence("key","6");
                blankFragment.setArguments(new Bundle());
                return blankFragment;
            }
        });
        indicador.setViewPager(viewPager);


        magicIndicator.setBackgroundColor(Color.parseColor("#fafafa"));
        CommonNavigator commonNavigator7 = new CommonNavigator(this);
        commonNavigator7.setScrollPivotX(0.65f);
        commonNavigator7.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(titles[index]);
                simplePagerTitleView.setTextSize(18);
                simplePagerTitleView.setNormalColor(Color.BLACK);
                simplePagerTitleView.setSelectedColor(Color.parseColor("#00c853"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 3));
                indicator.setLineWidth(UIUtil.dip2px(context, 10));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(Color.parseColor("#00c853"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator7);
        ViewPagerHelper.bind(magicIndicator, viewPager);

    }
}
