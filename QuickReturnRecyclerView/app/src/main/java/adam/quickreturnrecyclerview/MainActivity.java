package adam.quickreturnrecyclerview;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.AbsListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    View mHeader;

    private int mFlexibleSpaceOffset;
    private MyListAdapter myListAdapter;
    private ArrayList<String>  mDataList;
    private boolean mHeaderIsShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFlexibleSpaceOffset=getResources().getDimensionPixelOffset(R.dimen.header_height);
        initView();
        setUpRecyclerView();

    }

    private void setUpRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mDataList = new ArrayList<>();
        View paddingView = new View(this);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, mFlexibleSpaceOffset
        );
        paddingView.setLayoutParams(params);
        paddingView.setBackgroundColor(Color.WHITE);
        myListAdapter = new MyListAdapter(mDataList);
        myListAdapter.addHeader(paddingView);
        mRecyclerView.setAdapter(myListAdapter);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean isIdle;
            int mScrollY;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                isIdle = newState == RecyclerView.SCROLL_STATE_IDLE;
                if (isIdle) {
                    mScrollY = 0;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollY += dy;
                if (mScrollY > 12) {
                      HideHeader();
                } else {
                    ShowHeader();
                }
            }
        });

    }
    private void initView(){
        mHeader = findViewById(R.id.view_header);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getData();

    }

    private void getData() {
        for (int i = 0; i < 512; i++) {
            mDataList.add("你好你好" + i);
        }
        // post
        myListAdapter.notifyDataSetChanged();
    }

    public  void ShowHeader(){
        if(!mHeaderIsShow){
            com.nineoldandroids.view.ViewPropertyAnimator.animate(mHeader).cancel();
            com.nineoldandroids.view.ViewPropertyAnimator.animate(mHeader).translationY(0).setDuration(200).start();
            mHeaderIsShow=true;
        }
    }

    public  void HideHeader(){
        if(mHeaderIsShow){
            com.nineoldandroids.view.ViewPropertyAnimator.animate(mHeader).cancel();
            com.nineoldandroids.view.ViewPropertyAnimator.animate(mHeader).translationY(-mFlexibleSpaceOffset).setDuration(200).start();
            mHeaderIsShow=false;
        }
    }
}
