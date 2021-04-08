package com.example.mybooks;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksHolder> {
    private final ArrayList<Book> books;
    private Context context;
    private int itemResource;
    private static final String log_tag = BooksAdapter.class.getSimpleName();

    public BooksAdapter (Context context, int itemResource, ArrayList<Book> books)
    {
        this.books = books;
        this.context = context;
        this.itemResource = itemResource;
        // Debugging
        // Log.i(log_tag, "Inside BooksAdapter constructor, books: \n");
    }

    /**
     * Is called when RecyclerView creates a new element to display
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BooksHolder onCreateViewHolder (ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(this.itemResource, parent, false);
        return new BooksHolder(this.context, view);
    }

    /**
     * Is called to bind the data to the View for the item at the given position
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder (BooksHolder holder, int position)
    {
        // Use position to access the correct book object
        Book b = this.books.get(position);

        // Bind the book object to the holder
        holder.bindBook(b);
    }

    /**
     *
     * @return
     */
    public int getItemCount ()
    {
        return this.books.size();
    }


}
