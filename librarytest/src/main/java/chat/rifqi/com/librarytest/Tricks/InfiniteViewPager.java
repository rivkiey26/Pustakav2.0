package chat.rifqi.com.librarytest.Tricks;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;

/**
 * Created by Muhammad Rifqi on 04/07/2017.
 */

public class InfiniteViewPager extends ViewPagerEx {
    public InfiniteViewPager(Context context) {
        super(context);
    }

    public InfiniteViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
    }
}
