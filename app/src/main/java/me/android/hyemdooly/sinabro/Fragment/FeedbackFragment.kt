package me.android.hyemdooly.sinabro.Fragment

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_feedback.view.*

import me.android.hyemdooly.sinabro.R

class FeedbackFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feedback, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.btn_send.setOnClickListener {
            val email = Intent(Intent.ACTION_SENDTO)
            email.type = "plain/text"
            email.data = Uri.parse("mailto:hyemdooly@gmail.com")
            email.putExtra(Intent.EXTRA_SUBJECT, "[시나브로] 피드백 전송")
            email.putExtra(Intent.EXTRA_TEXT, "시나브로 피드백 전송 메일입니다.\n")
            startActivity(email)
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
            FeedbackFragment().apply {}
    }
}
