package ps.room.room.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import ps.room.room.R
import ps.room.room.adapter.NoteAdapter
import ps.room.room.database.NoteEntity
import ps.room.room.database.NotedataBase
import ps.room.room.databinding.ActivityAddnoteBinding
import ps.room.room.utils.Constants.Note_Data_Base_Table

class addnoteActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddnoteBinding

    private val noteDB: NotedataBase by lazy{

        Room.databaseBuilder(this, NotedataBase::class.java, Note_Data_Base_Table)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()


    }



    private lateinit var noteEntity: NoteEntity

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityAddnoteBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.apply{

            btnSave.setOnClickListener{
                val title = edtTitle.text.toString()
                val desc  = edtDesc.text.toString()

                if (title.isNotEmpty() || desc.isNotEmpty()){

                    noteEntity = NoteEntity(0,title, desc)
                    noteDB.dao().insertNote(noteEntity)
                    finish()

                }
                else{
                    Snackbar.make(
                        it,
                        "Title and Description can not be Empty",
                        Snackbar.LENGTH_LONG
                    ).show()
                }


            }

        }
    }
}