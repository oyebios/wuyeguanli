package test.cn.sjz.testproject.testsystem;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.testsystem.http.bean.RecordListBean;
import test.cn.sjz.testproject.testsystem.view.RecordInDayActivity;

/**
 * 记录适配器
 */

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.MyViewHolder> {
    private Context mContext;
    private List<RecordListBean> mListData;
    public RecordAdapter(Context context,List<RecordListBean> list) {
        mContext = context;
        mListData = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType ) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_record,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final RecordListBean recordListBean = mListData.get(position);
        holder.mTvCount.setText(String.valueOf(recordListBean.getNum())+ "次");
        holder.mTvTime.setText(recordListBean.getTime());
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, RecordInDayActivity.class);
                intent.putExtra("time", recordListBean.getTime());
                mContext.startActivity(intent);
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mTvTime,mTvCount,mTvGo;
        View rootView;
        public MyViewHolder(View itemView) {
            super(itemView);
            rootView =itemView;
            mTvCount = (TextView)itemView.findViewById(R.id.tv_count);
            mTvTime = (TextView)itemView.findViewById(R.id.tv_time);
            mTvGo = (TextView)itemView.findViewById(R.id.tv_go);
        }
    }
}
