package me.android.hyemdooly.sinabro.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_translation.*
import kotlinx.android.synthetic.main.fragment_translation.view.*

import me.android.hyemdooly.sinabro.R

class TranslationFragment : androidx.fragment.app.Fragment() {

    private var listener: OnFragmentInteractionListener? = null
    private var translationState = true // nam -> book : true, book -> nam : false
    private var collectionPath = "south_to_north_words"
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_translation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.btn_swap.setOnClickListener {
            var swap = text_left.text
            text_left.text = text_right.text
            text_right.text = swap
            if(translationState){
                collectionPath = "north_to_south_words"
                translationState = false
            }else{
                collectionPath = "south_to_north_words"
                translationState = true
            }
        }

        view.edit_text_input.setOnEditorActionListener { v, actionId, event ->
            when(actionId){
                EditorInfo.IME_ACTION_SEARCH -> {
                    if(view.edit_text_input.text.toString() != "") {
                        val inputMethodManager: InputMethodManager = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        inputMethodManager.hideSoftInputFromWindow(view.edit_text_input.windowToken, 0)

                        var docRef = db.collection(collectionPath).document(view.edit_text_input.text.toString().replace(" ", ""))
                        docRef.get().addOnCompleteListener {
                            var reverse :String
                            if (it.isSuccessful) {
                                val document = it.result
                                reverse = if (document != null) {
                                    document.getString("reverse").toString()
                                } else {
                                    "null"
                                }
                            } else {
                                Toast.makeText(context, "오류가 발생했습니다.", Toast.LENGTH_LONG).show()
                                reverse = "ERROR"
                            }
                            view.text_output.text = when (reverse) {
                                "null" -> "단어 정보가 없습니다."
                                "ERROR" -> "오류가 발생했습니다. 다시 한번 시도해주세요."
                                else -> reverse
                            }
                        }

                        true
                    } else false
                }
                else -> false
            }
        }




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
            TranslationFragment().apply {}
    }
}
