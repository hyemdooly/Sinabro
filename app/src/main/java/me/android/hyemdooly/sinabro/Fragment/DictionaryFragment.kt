package me.android.hyemdooly.sinabro.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_dictionary.*
import kotlinx.android.synthetic.main.fragment_dictionary.view.*
import kotlinx.android.synthetic.main.fragment_translation.*

import me.android.hyemdooly.sinabro.R

class DictionaryFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dictionary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.edit_text_input.setOnEditorActionListener { v, actionId, event ->
            when(actionId){
                EditorInfo.IME_ACTION_SEARCH -> {
                    if(view.edit_text_input.text.toString() != "") {
                        var docRef = db.collection("north_word_dict").document(view.edit_text_input.text.toString().replace(" ", ""))
                        docRef.get().addOnCompleteListener {
                            var description :String
                            if (it.isSuccessful) {
                                val document = it.result
                                description = if (document != null) {
                                    document.getString("mean").toString()
                                } else {
                                    "null"
                                }
                            } else {
                                Toast.makeText(context, "오류가 발생했습니다.", Toast.LENGTH_LONG)
                                description = "ERROR"
                            }
                            view.text_description_output.text = when (description) {
                                "null" -> "뜻 정보가 없습니다."
                                "ERROR" -> "오류가 발생했습니다. 다시 한번 시도해주세요."
                                else -> description
                            }
                        }
                        true
                    } else false
                }
                else -> false
            }
        }

    }

    fun getDescriptionFromDoc(documentPath: String) :String {
        var returnVal = "asdf"

        return returnVal
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            DictionaryFragment().apply {}
    }
}
