package ps.room.room.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import ps.room.room.R
import ps.room.room.database.NoteEntity
import ps.room.room.database.NotedataBase
import ps.room.room.databinding.ActivityUpdateNote2Binding
import ps.room.room.utils.Constants
import ps.room.room.utils.Constants.BUNDLE_NOTE_ID

class UpdateNoteActivity2 : AppCompatActivity() {
    private val noteDB: NotedataBase by lazy{

        Room.databaseBuilder(this, NotedataBase::class.java, Constants.Note_Data_Base_Table)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()


    }

    private lateinit var noteEntity: NoteEntity
    private var noteId = 0
    private var defaultDesc=""
    private var defaultTitle=""



    private lateinit var binding: ActivityUpdateNote2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUpdateNote2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let{

            noteId = it.getInt(BUNDLE_NOTE_ID)

        }

        binding.apply {

            defaultDesc= noteDB.dao().getNote(noteId).noteTitle
            defaultTitle= noteDB.dao().getNote(noteId).noteDescription

            edtTitle.setText(defaultTitle)
            edtDesc.setText( defaultDesc)

            btnDelete.setOnClickListener{

                noteEntity = NoteEntity(noteId,defaultTitle,defaultDesc)
                noteDB.dao().deleteNote(noteEntity)

            }
            btnSave.setOnClickListener{

                val title = edtTitle.text.toString()
                val desc = edtDesc.text.toString()
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