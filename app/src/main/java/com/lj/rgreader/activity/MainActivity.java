package com.lj.rgreader.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.lj.rgreader.R;
import com.lj.rgreader.module.ArticlesTabLayout;
import com.lj.rgreader.module.NovelTabLayout;
import com.lj.rgreader.module.UserTabLayout;
import com.lj.rgreader.module.VideoTabLayout;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "MainActivity";
    private int position;


    private BottomNavigationView bottomNavigationView;

    private static final int FRAGMENT_NOVEL = 0;
    private static final int FRAGMENT_ARTICLES = 1;
    private static final int FRAGMENT_VIDEO = 2;
    private static final int FRAGMENT_USER = 3;

    private UserTabLayout userTabLayout;
    private ArticlesTabLayout articlesTabLayout;
    private NovelTabLayout novelTabLayout;
    private VideoTabLayout videoTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        showFragment(FRAGMENT_NOVEL);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_novel:
                    showFragment(FRAGMENT_NOVEL);
                    break;
                case R.id.action_articles:
                    showFragment(FRAGMENT_ARTICLES);
                    break;
                case R.id.action_video:
                    showFragment(FRAGMENT_VIDEO);
                    break;
                case R.id.action_user:
                    showFragment(FRAGMENT_USER);
                    break;
            }
            return true;
        });
    }

    private void initView() {
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
    }

    private void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        position = index;
        switch (index) {
            case FRAGMENT_NOVEL:
                /**
                 * 如果Fragment为空，就新建一个实例
                 * 如果不为空，就将它从栈中显示出来
                 */
                if (novelTabLayout == null) {
                    novelTabLayout = NovelTabLayout.getInstance();
                    ft.add(R.id.container, novelTabLayout, NovelTabLayout.class.getName());
                } else {
                    ft.show(novelTabLayout);
                }
                break;

            case FRAGMENT_ARTICLES:
                if (articlesTabLayout == null) {
                    articlesTabLayout = ArticlesTabLayout.getInstance();
                    ft.add(R.id.container, articlesTabLayout, ArticlesTabLayout.class.getName());
                } else {
                    ft.show(articlesTabLayout);
                }
                break;

            case FRAGMENT_VIDEO:
                if (videoTabLayout == null) {
                    videoTabLayout = VideoTabLayout.getInstance();
                    ft.add(R.id.container, videoTabLayout, VideoTabLayout.class.getName());
                } else {
                    ft.show(videoTabLayout);
                }
                break;

            case FRAGMENT_USER:
                if (userTabLayout == null) {
                    userTabLayout = UserTabLayout.getInstance();
                    ft.add(R.id.container, userTabLayout, UserTabLayout.class.getName());
                } else {
                    ft.show(userTabLayout);
                }
        }

        ft.commit();
    }


}
