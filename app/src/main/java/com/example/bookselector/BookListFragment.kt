package com.example.bookselector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class BookListFragment : Fragment() {

    private var selectedGenre: String? = null
    private lateinit var selectedGenreTextView: TextView
    private lateinit var bookListContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            selectedGenre = it.getString("genre")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedGenreTextView = view.findViewById(R.id.selectedGenreTextView)
        bookListContainer = view.findViewById(R.id.bookListContainer)

        selectedGenreTextView.text = "Selected Genre: $selectedGenre"
        displayBooks(selectedGenre)
    }

    private fun displayBooks(genre: String?) {
        val books = when (genre) {
            "Fiction" -> listOf(
                "To Kill a Mockingbird" to R.drawable.to_kill_a_mockingbird,
                "1984" to R.drawable.nineteen_eighty_four,
                "The Great Gatsby" to R.drawable.the_great_gatsby
            )
            "Non-Fiction" -> listOf(
                "Sapiens" to R.drawable.sapiens,
                "Educated" to R.drawable.educated,
                "Becoming" to R.drawable.becoming
            )
            "Science Fiction" -> listOf(
                "Dune" to R.drawable.dune,
                "Ender's Game" to R.drawable.enders_game,
                "The Martian" to R.drawable.the_martian
            )
            "Fantasy" -> listOf(
                "Harry Potter" to R.drawable.harry_potter,
                "The Hobbit" to R.drawable.the_hobbit,
                "The Lord of the Rings" to R.drawable.lord_of_the_rings
            )
            else -> emptyList()
        }

        bookListContainer.removeAllViews()
        for ((title, imageId) in books) {
            val bookLayout = LayoutInflater.from(context).inflate(R.layout.book_item, bookListContainer, false)
            val bookTitleTextView: TextView = bookLayout.findViewById(R.id.bookTitleTextView)
            val bookImageView: ImageView = bookLayout.findViewById(R.id.bookImageView)
            bookTitleTextView.text = title
            bookImageView.setImageResource(imageId)
            bookListContainer.addView(bookLayout)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(genre: String) =
            BookListFragment().apply {
                arguments = Bundle().apply {
                    putString("genre", genre)
                }
            }
    }
}
