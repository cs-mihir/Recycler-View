package com.example.mihir.recview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Mihir on 31-05-2016.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.QuestionObjectHolder> {

    private static ArrayList<ListDataObject> quizOrQuestion;
    private static Context parent;

    private static MyClickListener myClickListener;

    public static class QuestionObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView label;
        TextView description;
        public QuestionObjectHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView.findViewById(R.id.textView);
            description = (TextView) itemView.findViewById(R.id.description_text_views);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent i = new Intent(parent,QuestionActivity.class);
            int position = quizOrQuestion.get(getAdapterPosition()).getQuestionID();
            i.putExtra("position",position);
            v.getContext().startActivity(i);

        }
    }

    public MyRecyclerViewAdapter(ArrayList<ListDataObject> dataList,Context c){
        this.quizOrQuestion=dataList;
        this.parent=c;
    }

    @Override
    public void onBindViewHolder(QuestionObjectHolder holder, int position) {
        holder.label.setText(quizOrQuestion.get(position).getQuestionLabel());
        holder.description.setText(quizOrQuestion.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return quizOrQuestion.size();
    }

    public interface MyClickListener{

        public void onItemClick(int pos,View v);
    }
    @Override
    public QuestionObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_row, parent, false);

        QuestionObjectHolder questionObject = new QuestionObjectHolder(view);

        return questionObject;
    }
    public void setOnItemClickListener(MyClickListener myClickListener) {

        this.myClickListener = myClickListener;

    }


    public void addItem(ListDataObject dataObj, int index) {

        quizOrQuestion.add(dataObj);
        notifyItemInserted(index);

    }

    public void deleteItem(int index) {

        quizOrQuestion.remove(index);
        notifyItemRemoved(index);

    }
}
