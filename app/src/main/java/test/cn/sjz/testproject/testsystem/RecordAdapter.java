package test.cn.sjz.testproject.testsystem;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * 记录适配器
 */

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.MyViewHolder> {



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType ) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
