package test.cn.sjz.testproject.ui.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import test.cn.sjz.testproject.MainActivity;
import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.model.BoxTypeCapBean;
import test.cn.sjz.testproject.ui.ui.RadioGroupLayout;


/**
 * Created by lp on 2019/3/8.
 *
 */

public class ZeroCapabilityAdapter extends BaseAdapter {

    Context mContext;
    List<BoxTypeCapBean> mList;
    OnCheckedListener mOnCheckedListener;

    public ZeroCapabilityAdapter(Context context, List list, OnCheckedListener onCheckedListener){
        this.mContext = context;
        this.mList = list;
        this.mOnCheckedListener = onCheckedListener;
    }
    @Override
    public int getCount() {
        return this.mList.size();
    }
    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        final BoxTypeCapBean boxTypeBean = mList.get(position);
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.zero_item_capability, null);
            viewHolder = new ViewHolder();
            viewHolder.ivBoxLogo = (ImageView) convertView.findViewById(R.id.iv_box_logo);
            viewHolder.tvBoxType = (TextView) convertView.findViewById(R.id.tv_box_type);
            viewHolder.tvBoxSize = (TextView) convertView.findViewById(R.id.tv_box_size);
            viewHolder.llCount = (RadioGroupLayout) convertView.findViewById(R.id.rg_1);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvBoxType.setText(boxTypeBean.getSpecsType());
        viewHolder.tvBoxSize.setText(boxTypeBean.getBoxSize());
        viewHolder.llCount.setTvStyle(R.style.text_item,null);
        viewHolder.llCount.setData(boxTypeBean.getBoxCapability());
        viewHolder.llCount.initView();
        viewHolder.llCount.setOnSelectListener(new RadioGroupLayout.onSelectListener() {
            @Override
            public void onSelect(int selectIndex) {

                mOnCheckedListener.onChecked(position, selectIndex);
            }
        });

        return convertView;
    }



    class ViewHolder{
        ImageView ivBoxLogo;
        TextView tvBoxType;
        TextView tvBoxSize;
        RadioGroupLayout llCount;
    }

    public interface OnCheckedListener{
        void onChecked(int position, int selectedIndex);
    }
}
