package com.example.mybooks;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BooksHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView bookTitle;
    private Book book;
    private Context context;

    public BooksHolder (Context context, View itemView)
    {
        super(itemView);

        this.context = context;
        this.bookTitle = (TextView) itemView.findViewById(R.id.fetched_book_name);

        itemView.setOnClickListener(this);
    }

    /**
     *
     * @param book
     */
    public void bindBook (Book book)
    {
        // Bind the data to the ViewHolder
        this.book = book;
        this.bookTitle.setText(book.getTitle());
    }

    /**
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        // Sanity check
        if (this.book != null) {

            Intent bookDetails = new Intent(itemView.getContext(), BookDetails.class);
            bookDetails.putExtra("book", this.book);
            itemView.getContext().startActivity(bookDetails);
        }
    }
}
