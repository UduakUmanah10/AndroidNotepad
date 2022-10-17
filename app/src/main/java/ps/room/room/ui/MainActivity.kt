package ps.room.room.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import ps.room.room.R
import ps.room.room.adapter.NoteAdapter
import ps.room.room.database.NotedataBase
import ps.room.room.databinding.ActivityMainBinding
import ps.room.room.utils.Constants

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val noteDB: NotedataBase by lazy{

        Room.databaseBuilder(this, NotedataBase::class.java, Constants.Note_Data_Base_Table)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    }

    private val noteAdapter by lazy { NoteAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnAddNote.setOnClickListener{
            startActivity(Intent(this, addnoteActivity::class.java))
        }
        checklistItem()
    }

    override fun onResume() {
        super.onResume()
        checklistItem()
    }


    private fun checklistItem(){
        binding.apply{
            if(noteDB.dao().getAllNotes().isNotEmpty()){

                rvNoteList.visibility = View.VISIBLE
                tvEmptyText.visibility = View.GONE
                noteAdapter.differ.submitList(noteDB.dao().getAllNotes())
                setupRecyclerView()
            }

            else{
                rvNoteList.visibility = View.GONE
                tvEmptyText.visibility=View.VISIBLE
            }

            }

        }

    private fun setupRecyclerView(){

        binding.rvNoteList.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = noteAdapter

        }

    }


}


