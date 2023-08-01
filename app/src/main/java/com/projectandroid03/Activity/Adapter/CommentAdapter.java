package com.projectandroid03.Activity.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projectandroid03.Activity.Handler.UserHandler;
import com.projectandroid03.Activity.Model.Comment;
import com.projectandroid03.Activity.Model.User;
import com.projectandroid03.R;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private  static Context context;
    private  static List<Comment> comments;
    private  List<User> userList;

    public CommentAdapter(@NonNull Context context, List<Comment> comments, List<User> userList){
        this.context = context;
        this.comments = comments;
        this.userList = userList;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = comments.get(position);
        int userId = comment.getUser_id();
        UserHandler userHandler = new UserHandler(context);
        String userPhone = userHandler.getUserPhoneById(userId);



        holder.commentPhone.setText(String.valueOf(userPhone));
        holder.commentDesc.setText(comment.getComment_desc());

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(itemView);
    }



    @Override
    public int getItemCount() {
        return comments.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView commentPhone, commentDesc;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            commentPhone = itemView.findViewById(R.id.commentPhone);
            commentDesc = itemView.findViewById(R.id.commentDesc);
        }
    }
    public void updateData(List<Comment> updatedComments) {
        comments.clear();
        comments.addAll(updatedComments);
        notifyDataSetChanged();
    }
}
