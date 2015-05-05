package adam.quickreturnrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Administrator on 2015/5/5.
 */
public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
     private List<String>  mList;
    private  View mView;
    private final int TYPE_HEADER=0;
    private  final int TYPE_CONTENT=1;


    public MyListAdapter(List<String> mList) {
        this.mList = mList;
    }


    /**
     *
     * 判断第0位的话 header
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return position==0?TYPE_HEADER:TYPE_CONTENT;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mView!=null&&viewType==TYPE_HEADER){
            return new ViewHolder(mView);
        }
        else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            ViewHolder holder = new ViewHolder(v);
            holder.content = (TextView) v.findViewById(R.id.tv_content);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(holder.getItemViewType()==TYPE_HEADER) return;
        if(mView!=null){
            position=position-1;
        }
        final  String string=mList.get(position);
        holder.content.setText(string);

    }

    @Override
    public int getItemCount() {
        return mView!=null?mList.size()+1:mList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView content;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                       Toast.makeText(v.getContext(),getLayoutPosition()+"",Toast.LENGTH_LONG).show();
                }
            });

        }


    }
    public void addHeader(View view){
        mView=view;
    }
}
