package io.github.adrianogba.desafioconductor

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RootFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.root_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transaction = fragmentManager?.beginTransaction()

		 // When this container fragment is created, we fill it with our first "real" fragment
        transaction?.replace(R.id.root_frame, FirstFragment())
        transaction?.commit()
    }
}
